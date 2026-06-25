import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.ParseTree;
import java.util.*;

/*
 * A partir da árvore sintática gerada pelo analisador, vamos produzir
 * um código C equivalente que pode ser compilado com GCC.
 * 
 * Principais responsabilidades:
 *   - Geração de declarações de variáveis, constantes e tipos
 *   - Geração de comandos (atribuição, leitura, escrita, seleção, repetição)
 *   - Geração de procedimentos e funções
 *   - Mapeamento de tipos LA para tipos C
 *   - Tratamento especial para literais (strings) e registros
 */
public class LAGeradorCodigo extends LAParserBaseVisitor<Void>{

    // Acumula o código C gerado
    private final StringBuilder codigo;
    // Pilha de escopos compartilhada com o analisador semântico
    private final Escopos escopos;
    // Indentação atual para formatação do código gerado
    private String indent = "";
    // Listas para armazenar procedimentos e funções (coletados em primeira passagem)
    private final List<LAParser.ProcedimentoContext> procedimentos = new ArrayList<>();
    private final List<LAParser.FuncaoContext> funcoes = new ArrayList<>();

    public LAGeradorCodigo(Escopos escopos){
        codigo = new StringBuilder();
        this.escopos = escopos;
    }

    public String getCodigo(){
        return codigo.toString();
    }

    /*
     * Gera a estrutura completa do programa C:
     * - Includes necessários
     * - Definição de TAM_LITERAL
     * - Protótipos de procedimentos e funções
     * - Definições de procedimentos e funções
     * - Função main com o corpo do algoritmo
     */
    @Override
    public Void visitPrograma(LAParser.ProgramaContext ctx){
        // Includes padrão
        codigo.append("#include <stdio.h>\n");
        codigo.append("#include <stdlib.h>\n");
        codigo.append("#include <string.h>\n");
        codigo.append("#define TAM_LITERAL 80\n\n");

        // Primeira passagem: coleta procedimentos e funções
        for (LAParser.Decl_local_globalContext dlg : ctx.declaracoes().decl_local_global()){
            if (dlg.declaracao_local() != null){
                visitDeclaracaoLocal(dlg.declaracao_local());
            } else if (dlg.declaracao_global() != null){
                LAParser.Declaracao_globalContext dg = dlg.declaracao_global();
                if (dg instanceof LAParser.ProcedimentoContext){
                    procedimentos.add((LAParser.ProcedimentoContext) dg);
                } else if (dg instanceof LAParser.FuncaoContext){
                    funcoes.add((LAParser.FuncaoContext) dg);
                }
            }
        }

        // Geração de protótipos
        for (LAParser.ProcedimentoContext proc : procedimentos){
            gerarPrototipoProcedimento(proc);
        }
        for (LAParser.FuncaoContext func : funcoes){
            gerarPrototipoFuncao(func);
        }
        codigo.append("\n");

        // Geração das definições de procedimentos e funções
        for (LAParser.ProcedimentoContext proc : procedimentos){
            visitProcedimento(proc);
        }
        for (LAParser.FuncaoContext func : funcoes){
            visitFuncao(func);
        }

        // Geração da função main
        codigo.append("int main(){\n");
        indent = "    ";
        visitCorpo(ctx.corpo());
        codigo.append(indent).append("return 0;\n");
        indent = "";
        codigo.append("}\n");

        return null;
    }

    //  Processa uma declaração local (variável, constante ou tipo).
    public void visitDeclaracaoLocal(LAParser.Declaracao_localContext ctx){
        int tipo = ctx.getStart().getType();
        if (tipo == LAParser.DECLARE){
            // Declaração de variável: DECLARE identificadores : tipo
            LAParser.DeclaracaoVariavelContext dvc = (LAParser.DeclaracaoVariavelContext) ctx;
            LAParser.VariavelContext varCtx = dvc.variavel();
            LAParser.TipoContext tipoCtx = varCtx.tipo();
            String tipoC = tipoParaC(tipoCtx);
            TabelaDeSimbolos.TipoLA tipoLA = obterTipoLA(tipoCtx);
            String nomeTipo = null;
            Map<String, TabelaDeSimbolos.EntradaSimbolo> camposRegistro = null;

            // Verifica se é registro inline ou definido pelo usuário
            if (tipoCtx.registro() != null){
                camposRegistro = extrairCamposRegistro(tipoCtx.registro());
                tipoLA = TabelaDeSimbolos.TipoLA.REGISTRO;
            } else{
                LAParser.Tipo_estendidoContext te = tipoCtx.tipo_estendido();
                if (te.tipo_basico_ident().IDENT() != null){
                    nomeTipo = te.tipo_basico_ident().getText();
                    TabelaDeSimbolos.EntradaSimbolo entradaTipo = escopos.buscarEmEscopos(nomeTipo);
                    if (entradaTipo != null && entradaTipo.campos != null){
                        camposRegistro = entradaTipo.campos;
                    }
                }
            }

            for (LAParser.IdentificadorContext id : varCtx.identificador()){
                String nome = id.IDENT(0).getText();
                String dims = gerarDimensoes(id.dimensao());
                // Literais precisam de tamanho definido
                if (tipoLA == TabelaDeSimbolos.TipoLA.LITERAL && dims.isEmpty()){
                    dims = "[TAM_LITERAL]";
                }
                codigo.append(indent).append(tipoC).append(" ").append(nome).append(dims).append(";\n");
                // Atualiza a tabela de símbolos com os campos do registro
                TabelaDeSimbolos tabela = escopos.obterEscopoAtual();
                tabela.adicionar(nome, tipoLA, nomeTipo, null, camposRegistro);
            }
        } else if (tipo == LAParser.CONSTANTE){
            // Declaração de constante: CONSTANTE IDENT : tipo = valor
            LAParser.DeclaracaoConstanteContext dc = (LAParser.DeclaracaoConstanteContext) ctx;
            String nome = dc.IDENT().getText();
            String valor = dc.valor_constante().getText();
            String tipoC = tipoBasicoParaC(dc.tipo_basico().getText());
            codigo.append(indent).append("const ").append(tipoC).append(" ").append(nome).append(" = ").append(valor).append(";\n");
            TabelaDeSimbolos tabela = escopos.obterEscopoAtual();
            TabelaDeSimbolos.TipoLA tipoLA = mapearTipo(dc.tipo_basico().getText());
            tabela.adicionar(nome, tipoLA, null);
        } else if (tipo == LAParser.TIPO){
            // Declaração de tipo: TIPO IDENT : tipo
            LAParser.DeclaracaoTipoContext dt = (LAParser.DeclaracaoTipoContext) ctx;
            String nome = dt.IDENT().getText();
            LAParser.TipoContext tipoCtx = dt.tipo();
            String def = gerarDefinicaoTipo(nome, tipoCtx);
            codigo.append(indent).append(def).append(";\n");
            // Armazena os campos se for um registro
            Map<String, TabelaDeSimbolos.EntradaSimbolo> campos = null;
            if (tipoCtx.registro() != null){
                campos = extrairCamposRegistro(tipoCtx.registro());
            }
            TabelaDeSimbolos tabela = escopos.obterEscopoAtual();
            tabela.adicionar(nome, TabelaDeSimbolos.TipoLA.TIPO_USUARIO, null, null, campos);
        }
    }

    // Resolve o tipo de um identificador, percorrendo campos de registro se necessário. Utilizado para verificar o tipo de expressões e atribuições.
    private TabelaDeSimbolos.TipoLA resolverTipoIdentificador(LAParser.IdentificadorContext idCtx){
        String nomeBase = idCtx.IDENT(0).getText();
        TabelaDeSimbolos.EntradaSimbolo entrada = escopos.buscarEmEscopos(nomeBase);
        if (entrada == null) return TabelaDeSimbolos.TipoLA.INVALIDO;
        if (idCtx.IDENT().size() == 1){
            return entrada.tipo;
        }
        // Percorre os campos do registro
        Map<String, TabelaDeSimbolos.EntradaSimbolo> camposAtuais = entrada.campos;
        for (int i = 1; i < idCtx.IDENT().size(); i++){
            String nomeCampo = idCtx.IDENT(i).getText();
            if (camposAtuais == null || !camposAtuais.containsKey(nomeCampo)){
                return TabelaDeSimbolos.TipoLA.INVALIDO;
            }
            entrada = camposAtuais.get(nomeCampo);
            camposAtuais = entrada.campos;
        }
        return entrada.tipo;
    }

    // Gera o protótipo (declaração) de um procedimento 
    private void gerarPrototipoProcedimento(LAParser.ProcedimentoContext proc){
        String nome = proc.IDENT().getText();
        String params = gerarParametros(proc.parametros(), true);
        codigo.append("void ").append(nome).append("(").append(params).append(");\n");
    }

    // Gera o protótipo (declaração) de uma função
    private void gerarPrototipoFuncao(LAParser.FuncaoContext func){
        String nome = func.IDENT().getText();
        String tipoRet = tipoBasicoParaC(func.tipo_estendido().getText().replace("^", "").trim());
        if (func.tipo_estendido().PONTEIRO() != null) tipoRet += "*";
        String params = gerarParametros(func.parametros(), true);
        codigo.append(tipoRet).append(" ").append(nome).append("(").append(params).append(");\n");
    }

    // Gera a definição completa de um procedimento 
    @Override
    public Void visitProcedimento(LAParser.ProcedimentoContext ctx){
        String nome = ctx.IDENT().getText();
        escopos.criarNovoEscopo();
        adicionarParametros(ctx.parametros());

        String params = gerarParametros(ctx.parametros(), false);
        codigo.append("void ").append(nome).append("(").append(params).append("){\n");
        indent += "    ";
        for (LAParser.Declaracao_localContext decl : ctx.declaracao_local()){
            visitDeclaracaoLocal(decl);
        }
        for (LAParser.CmdContext cmd : ctx.cmd()){
            visitCmd(cmd);
        }
        indent = indent.substring(0, indent.length() - 4);
        codigo.append(indent).append("}\n\n");
        escopos.abandonarEscopo();
        return null;
    }

    // Gera a definição completa de uma função 
    @Override
    public Void visitFuncao(LAParser.FuncaoContext ctx){
        String nome = ctx.IDENT().getText();
        escopos.criarNovoEscopo();
        adicionarParametros(ctx.parametros());

        String tipoRet = tipoBasicoParaC(ctx.tipo_estendido().getText().replace("^", "").trim());
        if (ctx.tipo_estendido().PONTEIRO() != null) tipoRet += "*";
        String params = gerarParametros(ctx.parametros(), false);
        codigo.append(tipoRet).append(" ").append(nome).append("(").append(params).append("){\n");
        indent += "    ";
        for (LAParser.Declaracao_localContext decl : ctx.declaracao_local()){
            visitDeclaracaoLocal(decl);
        }
        for (LAParser.CmdContext cmd : ctx.cmd()){
            visitCmd(cmd);
        }
        indent = indent.substring(0, indent.length() - 4);
        codigo.append(indent).append("}\n\n");
        escopos.abandonarEscopo();
        return null;
    }

    // Gera o corpo principal (declarações e comandos do algoritmo
    @Override
    public Void visitCorpo(LAParser.CorpoContext ctx){
        for (LAParser.Declaracao_localContext decl : ctx.declaracao_local()){
            visitDeclaracaoLocal(decl);
        }
        for (LAParser.CmdContext cmd : ctx.cmd()){
            visitCmd(cmd);
        }
        return null;
    }

    // Gra comando de atribuição (incluindo strcpy para literais)
    @Override
    public Void visitCmdAtribuicao(LAParser.CmdAtribuicaoContext ctx){
        String nome = ctx.identificador().getText();
        TabelaDeSimbolos.TipoLA tipoDestino = resolverTipoIdentificador(ctx.identificador());
        String expr = avaliarExpressao(ctx.expressao()).codigo;
        if (tipoDestino == TabelaDeSimbolos.TipoLA.LITERAL){
            // Literas usam strcpy em vez de atribuição direta
            codigo.append(indent).append("strcpy(").append(nome).append(", ").append(expr).append(");\n");
        } else{
            if (ctx.PONTEIRO() != null){
                codigo.append(indent).append("*").append(nome).append(" = ").append(expr).append(";\n");
            } else{
                codigo.append(indent).append(nome).append(" = ").append(expr).append(";\n");
            }
        }
        return null;
    }

    // Gera comando de leitura (leia)
    @Override
    public Void visitCmdLeia(LAParser.CmdLeiaContext ctx){
        List<LAParser.IdentificadorContext> ids = ctx.identificador();
        List<TerminalNode> ponteiros = ctx.PONTEIRO();
        for (int i = 0; i < ids.size(); i++){
            LAParser.IdentificadorContext id = ids.get(i);
            boolean isPonteiro = (i < ponteiros.size() && ponteiros.get(i) != null);
            String nome = id.getText();
            TabelaDeSimbolos.EntradaSimbolo entrada = escopos.buscarEmEscopos(id.IDENT(0).getText());
            if (entrada == null) continue;
            TabelaDeSimbolos.TipoLA tipo = entrada.tipo;
            if (tipo == TabelaDeSimbolos.TipoLA.LITERAL){
                // iterais usam fgets para ler espaços
                codigo.append(indent).append("fgets(").append(nome).append(", TAM_LITERAL, stdin);\n");
                codigo.append(indent).append(nome).append("[strcspn(").append(nome).append(", \"\\n\")] = '\\0';\n");
            } else{
                String fmt = tipoParaFormato(tipo);
                String endereco = isPonteiro ? nome : ("&" + nome);
                codigo.append(indent).append("scanf(\"").append(fmt).append("\", ").append(endereco).append(");\n");
            }
        }
        return null;
    }

    // Gera comando de escrita (escreva)
    @Override
    public Void visitCmdEscreva(LAParser.CmdEscrevaContext ctx){
        for (LAParser.ExpressaoContext expr : ctx.expressao()){
            ExprInfo info = avaliarExpressao(expr);
            TabelaDeSimbolos.TipoLA tipo = info.tipo;
            if (tipo == TabelaDeSimbolos.TipoLA.LITERAL){
                codigo.append(indent).append("printf(\"%s\", ").append(info.codigo).append(");\n");
            } else if (tipo == TabelaDeSimbolos.TipoLA.LOGICO){
                codigo.append(indent).append("printf(\"%s\", ").append(info.codigo)
                      .append(" ? \"verdadeiro\" : \"falso\");\n");
            } else if (tipo == TabelaDeSimbolos.TipoLA.INTEIRO){
                codigo.append(indent).append("printf(\"%d\", ").append(info.codigo).append(");\n");
            } else if (tipo == TabelaDeSimbolos.TipoLA.REAL){
                codigo.append(indent).append("printf(\"%lf\", ").append(info.codigo).append(");\n");
            } else{
                codigo.append(indent).append("printf(\"%d\", ").append(info.codigo).append(");\n");
            }
        }
        return null;
    }

    // Gera comando de seleção (se) 
    @Override
    public Void visitCmdSe(LAParser.CmdSeContext ctx){
        String cond = avaliarExpressao(ctx.expressao()).codigo;
        codigo.append(indent).append("if (").append(cond).append("){\n");
        indent += "    ";
        // Comandos do ENTAO
        for (LAParser.CmdContext cmd : ctx.cmd()){
            visitCmd(cmd);
        }
        indent = indent.substring(0, indent.length() - 4);
        codigo.append(indent).append("}");
        if (ctx.SENAO() != null){
            codigo.append(" else{\n");
            indent += "    ";
            // Comandos do SENAO (filhos após o token SENAO)
            List<LAParser.CmdContext> cmdsSenao = new ArrayList<>();
            for (ParseTree child : ctx.children){
                if (child instanceof LAParser.CmdContext){
                    LAParser.CmdContext cmd = (LAParser.CmdContext) child;
                    if (cmd.getStart().getTokenIndex() > ctx.SENAO().getSymbol().getTokenIndex()){
                        cmdsSenao.add(cmd);
                    }
                }
            }
            for (LAParser.CmdContext cmd : cmdsSenao){
                visitCmd(cmd);
            }
            indent = indent.substring(0, indent.length() - 4);
            codigo.append(indent).append("}");
        }
        codigo.append("\n");
        return null;
    }

    // Gera comando de seleção múltipla (caso) usando switch
    @Override
    public Void visitCmdCaso(LAParser.CmdCasoContext ctx){
        String expr = avaliarExpAritmetica(ctx.exp_aritmetica()).codigo;
        codigo.append(indent).append("switch (").append(expr).append("){\n");
        indent += "    ";
        LAParser.SelecaoContext sel = ctx.selecao();
        for (LAParser.Item_selecaoContext item : sel.item_selecao()){
            for (LAParser.Numero_intervaloContext ni : item.constantes().numero_intervalo()){
                int inicio = Integer.parseInt(ni.NUM_INT(0).getText());
                int fim = inicio;
                if (ni.PONTO_PONTO() != null){
                    fim = Integer.parseInt(ni.NUM_INT(1).getText());
                }
                for (int val = inicio; val <= fim; val++){
                    codigo.append(indent).append("case ").append(val).append(":\n");
                    indent += "    ";
                    for (LAParser.CmdContext cmd : item.cmd()){
                        visitCmd(cmd);
                    }
                    codigo.append(indent).append("break;\n");
                    indent = indent.substring(0, indent.length() - 4);
                }
            }
        }
        if (ctx.SENAO() != null){
            codigo.append(indent).append("default:\n");
            indent += "    ";
            List<LAParser.CmdContext> cmdsDefault = new ArrayList<>();
            for (ParseTree child : ctx.children){
                if (child instanceof LAParser.CmdContext){
                    LAParser.CmdContext cmd = (LAParser.CmdContext) child;
                    if (cmd.getStart().getTokenIndex() > ctx.SENAO().getSymbol().getTokenIndex()){
                        cmdsDefault.add(cmd);
                    }
                }
            }
            for (LAParser.CmdContext cmd : cmdsDefault){
                visitCmd(cmd);
            }
            indent = indent.substring(0, indent.length() - 4);
        }
        indent = indent.substring(0, indent.length() - 4);
        codigo.append(indent).append("}\n");
        return null;
    }

    // Gera comando de repetição para (for)
    @Override
    public Void visitCmdPara(LAParser.CmdParaContext ctx){
        String var = ctx.IDENT().getText();
        String inicio = avaliarExpAritmetica(ctx.exp_aritmetica(0)).codigo;
        String fim = avaliarExpAritmetica(ctx.exp_aritmetica(1)).codigo;
        codigo.append(indent).append("for (").append(var).append(" = ").append(inicio)
            .append("; ").append(var).append(" <= ").append(fim).append("; ")
            .append(var).append("++){\n");
        indent += "    ";
        for (LAParser.CmdContext cmd : ctx.cmd()){
            visitCmd(cmd);
        }
        indent = indent.substring(0, indent.length() - 4);
        codigo.append(indent).append("}\n");
        return null;
    }

    // Gera comando de repetição enquanto (while)
    @Override
    public Void visitCmdEnquanto(LAParser.CmdEnquantoContext ctx){
        String cond = avaliarExpressao(ctx.expressao()).codigo;
        codigo.append(indent).append("while (").append(cond).append("){\n");
        indent += "    ";
        for (LAParser.CmdContext cmd : ctx.cmd()){
            visitCmd(cmd);
        }
        indent = indent.substring(0, indent.length() - 4);
        codigo.append(indent).append("}\n");
        return null;
    }

    // Gera comando de repetição faça-ate (do-while)
    @Override
    public Void visitCmdFaca(LAParser.CmdFacaContext ctx){
        codigo.append(indent).append("do{\n");
        indent += "    ";
        for (LAParser.CmdContext cmd : ctx.cmd()){
            visitCmd(cmd);
        }
        indent = indent.substring(0, indent.length() - 4);
        String cond = avaliarExpressao(ctx.expressao()).codigo;
        codigo.append(indent).append("} while (").append(cond).append(");\n");
        return null;
    }

    // Gera chamada de procedimento
    @Override
    public Void visitCmdChamada(LAParser.CmdChamadaContext ctx){
        String nome = ctx.IDENT().getText();
        StringBuilder args = new StringBuilder();
        List<LAParser.ExpressaoContext> exprs = ctx.expressao();

        TabelaDeSimbolos.EntradaSimbolo entrada = escopos.buscarEmEscopos(nome);
        if (entrada != null && entrada.parametros != null){
            for (int i = 0; i < exprs.size(); i++){
                if (i > 0) args.append(", ");
                LAParser.ExpressaoContext expr = exprs.get(i);
                TabelaDeSimbolos.ParametroInfo param = entrada.parametros.get(i);
                String argCod = avaliarExpressao(expr).codigo;

                // Se parâmetro é 'var', passa por referência (com &)
                if (param.isVar){
                    // Verifica se é um identificador simples para adicionar &
                    boolean isIdent = false;
                    if (expr.termo_logic().size() == 1 &&
                        expr.termo_logic(0).fator_logic().size() == 1 &&
                        expr.termo_logic(0).fator_logic(0).parcela_logic() instanceof LAParser.ParcelaLogicaRelacionalContext){
                        LAParser.ParcelaLogicaRelacionalContext rel = 
                            (LAParser.ParcelaLogicaRelacionalContext) expr.termo_logic(0).fator_logic(0).parcela_logic();
                        if (rel.exp_relacional().op_relacional() == null &&
                            rel.exp_relacional().exp_aritmetica().size() == 1){
                            LAParser.Exp_aritmeticaContext ea = rel.exp_relacional().exp_aritmetica(0);
                            if (ea.termo().size() == 1){
                                LAParser.TermoContext t = ea.termo(0);
                                if (t.fator().size() == 1){
                                    LAParser.FatorContext f = t.fator(0);
                                    if (f.parcela().size() == 1){
                                        LAParser.ParcelaContext p = f.parcela(0);
                                        if (p.parcela_unario() instanceof LAParser.ParcelaIdentificadorContext){
                                            isIdent = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (isIdent){
                        argCod = "&" + argCod;
                    }
                }
                args.append(argCod);
            }
        } else{
            for (LAParser.ExpressaoContext expr : exprs){
                if (args.length() > 0) args.append(", ");
                args.append(avaliarExpressao(expr).codigo);
            }
        }
        codigo.append(indent).append(nome).append("(").append(args).append(");\n");
        return null;
    }

    // Gera comando de retorno (retorne
    @Override
    public Void visitCmdRetorne(LAParser.CmdRetorneContext ctx){
        String expr = avaliarExpressao(ctx.expressao()).codigo;
        codigo.append(indent).append("return ").append(expr).append(";\n");
        return null;
    }

    // Classe auxiliar para armazenar o código gerado e o tipo de uma expressão durante a avaliação.
    private static class ExprInfo{
        String codigo;
        TabelaDeSimbolos.TipoLA tipo;
        ExprInfo(String codigo, TabelaDeSimbolos.TipoLA tipo){
            this.codigo = codigo;
            this.tipo = tipo;
        }
    }

    // Avalia uma expressão (lógica, relacional ou aritmética) 
    private ExprInfo avaliarExpressao(LAParser.ExpressaoContext ctx){
        if (ctx.termo_logic().size() == 1){
            return avaliarTermoLogico(ctx.termo_logic(0));
        }
        // Oeração 'ou'
        ExprInfo left = avaliarTermoLogico(ctx.termo_logic(0));
        ExprInfo right = avaliarTermoLogico(ctx.termo_logic(1));
        String cod = left.codigo + " || " + right.codigo;
        return new ExprInfo(cod, TabelaDeSimbolos.TipoLA.LOGICO);
    }

    private ExprInfo avaliarTermoLogico(LAParser.Termo_logicContext ctx){
        if (ctx.fator_logic().size() == 1){
            return avaliarFatorLogico(ctx.fator_logic(0));
        }
        // Operação 'e'
        ExprInfo left = avaliarFatorLogico(ctx.fator_logic(0));
        ExprInfo right = avaliarFatorLogico(ctx.fator_logic(1));
        String cod = left.codigo + " && " + right.codigo;
        return new ExprInfo(cod, TabelaDeSimbolos.TipoLA.LOGICO);
    }

    private ExprInfo avaliarFatorLogico(LAParser.Fator_logicContext ctx){
        if (ctx.NAO() != null){
            ExprInfo inner = avaliarParcelaLogica(ctx.parcela_logic());
            return new ExprInfo("!" + inner.codigo, TabelaDeSimbolos.TipoLA.LOGICO);
        }
        return avaliarParcelaLogica(ctx.parcela_logic());
    }

    private ExprInfo avaliarParcelaLogica(LAParser.Parcela_logicContext ctx){
        if (ctx instanceof LAParser.ParcelaLogicaConstanteContext){
            return new ExprInfo(ctx.getText(), TabelaDeSimbolos.TipoLA.LOGICO);
        } else if (ctx instanceof LAParser.ParcelaLogicaRelacionalContext){
            LAParser.ParcelaLogicaRelacionalContext rel = (LAParser.ParcelaLogicaRelacionalContext) ctx;
            return avaliarExpRelacional(rel.exp_relacional());
        }
        return new ExprInfo("0", TabelaDeSimbolos.TipoLA.INVALIDO);
    }

    // Avalia expressão relacional, convertendo operadores para C
    private ExprInfo avaliarExpRelacional(LAParser.Exp_relacionalContext ctx){
        if (ctx.op_relacional() == null){
            return avaliarExpAritmetica(ctx.exp_aritmetica(0));
        }
        ExprInfo left = avaliarExpAritmetica(ctx.exp_aritmetica(0));
        ExprInfo right = avaliarExpAritmetica(ctx.exp_aritmetica(1));
        String op = ctx.op_relacional().getText();
        switch (op){
            case "=":  op = "=="; break;
            case "<>": op = "!="; break;
            // >, <, >=, <= permanecem iguais
        }
        String cod = left.codigo + " " + op + " " + right.codigo;
        return new ExprInfo(cod, TabelaDeSimbolos.TipoLA.LOGICO);
    }

    // Avalia expressão aritmética (+, -, *, /, %) 
    private ExprInfo avaliarExpAritmetica(LAParser.Exp_aritmeticaContext ctx){
        ExprInfo result = avaliarTermo(ctx.termo(0));
        for (int i = 1; i < ctx.termo().size(); i++){
            ExprInfo next = avaliarTermo(ctx.termo(i));
            String op = ctx.op1(i-1).getText();
            String cod = result.codigo + " " + op + " " + next.codigo;
            TabelaDeSimbolos.TipoLA tipo = combinarTipos(result.tipo, next.tipo);
            result = new ExprInfo(cod, tipo);
        }
        return result;
    }

    private ExprInfo avaliarTermo(LAParser.TermoContext ctx){
        ExprInfo result = avaliarFator(ctx.fator(0));
        for (int i = 1; i < ctx.fator().size(); i++){
            ExprInfo next = avaliarFator(ctx.fator(i));
            String op = ctx.op2(i-1).getText();
            String cod = result.codigo + " " + op + " " + next.codigo;
            TabelaDeSimbolos.TipoLA tipo = combinarTipos(result.tipo, next.tipo);
            result = new ExprInfo(cod, tipo);
        }
        return result;
    }

    private ExprInfo avaliarFator(LAParser.FatorContext ctx){
        ExprInfo result = avaliarParcela(ctx.parcela(0));
        for (int i = 1; i < ctx.parcela().size(); i++){
            ExprInfo next = avaliarParcela(ctx.parcela(i));
            String op = ctx.op3(i-1).getText();
            String cod = result.codigo + " " + op + " " + next.codigo;
            TabelaDeSimbolos.TipoLA tipo = combinarTipos(result.tipo, next.tipo);
            result = new ExprInfo(cod, tipo);
        }
        return result;
    }

    private ExprInfo avaliarParcela(LAParser.ParcelaContext ctx){
        if (ctx.parcela_unario() != null){
            return avaliarParcelaUnario(ctx.parcela_unario());
        } else{
            return avaliarParcelaNaoUnario(ctx.parcela_nao_unario());
        }
    }

    private ExprInfo avaliarParcelaUnario(LAParser.Parcela_unarioContext ctx){
        if (ctx instanceof LAParser.ParcelaIdentificadorContext){
            LAParser.ParcelaIdentificadorContext pid = (LAParser.ParcelaIdentificadorContext) ctx;
            String nome = pid.identificador().getText();
            TabelaDeSimbolos.TipoLA tipo = resolverTipoIdentificador(pid.identificador());
            return new ExprInfo(nome, tipo);
        } else if (ctx instanceof LAParser.ParcelaChamadaFuncaoContext){
            LAParser.ParcelaChamadaFuncaoContext chamada = (LAParser.ParcelaChamadaFuncaoContext) ctx;
            String nome = chamada.IDENT().getText();
            StringBuilder args = new StringBuilder();
            List<LAParser.ExpressaoContext> exprs = chamada.expressao();
            for (int i = 0; i < exprs.size(); i++){
                if (i > 0) args.append(", ");
                args.append(avaliarExpressao(exprs.get(i)).codigo);
            }
            TabelaDeSimbolos.EntradaSimbolo entrada = escopos.buscarEmEscopos(nome);
            TabelaDeSimbolos.TipoLA tipoRet = (entrada != null && entrada.tipo == TabelaDeSimbolos.TipoLA.FUNCAO)
                    ? mapearTipo(entrada.nomeTipo) : TabelaDeSimbolos.TipoLA.INVALIDO;
            return new ExprInfo(nome + "(" + args + ")", tipoRet);
        } else if (ctx instanceof LAParser.ParcelaInteiroContext){
            return new ExprInfo(ctx.getText(), TabelaDeSimbolos.TipoLA.INTEIRO);
        } else if (ctx instanceof LAParser.ParcelaRealContext){
            return new ExprInfo(ctx.getText(), TabelaDeSimbolos.TipoLA.REAL);
        } else if (ctx instanceof LAParser.ParcelaParentesesContext){
            LAParser.ParcelaParentesesContext par = (LAParser.ParcelaParentesesContext) ctx;
            ExprInfo inner = avaliarExpressao(par.expressao());
            return new ExprInfo("(" + inner.codigo + ")", inner.tipo);
        }
        return new ExprInfo("0", TabelaDeSimbolos.TipoLA.INVALIDO);
    }

    private ExprInfo avaliarParcelaNaoUnario(LAParser.Parcela_nao_unarioContext ctx){
        if (ctx instanceof LAParser.ParcelaEnderecoContext){
            LAParser.ParcelaEnderecoContext end = (LAParser.ParcelaEnderecoContext) ctx;
            String nome = end.identificador().getText();
            return new ExprInfo("&" + nome, TabelaDeSimbolos.TipoLA.PONTEIRO);
        } else if (ctx instanceof LAParser.ParcelaCadeiaContext){
            return new ExprInfo(ctx.getText(), TabelaDeSimbolos.TipoLA.LITERAL);
        }
        return new ExprInfo("0", TabelaDeSimbolos.TipoLA.INVALIDO);
    }

    // Extrai os campos de um registro e os armazena em um mapa para uso na tabela de símbolos durante a geração de código.
    private Map<String, TabelaDeSimbolos.EntradaSimbolo> extrairCamposRegistro(LAParser.RegistroContext regCtx){
        Map<String, TabelaDeSimbolos.EntradaSimbolo> campos = new HashMap<>();
        for (LAParser.VariavelContext var : regCtx.variavel()){
            LAParser.TipoContext tipoCtx = var.tipo();
            String tipoC = tipoParaC(tipoCtx);
            TabelaDeSimbolos.TipoLA tipoLA = obterTipoLA(tipoCtx);
            String nomeTipo = null;
            Map<String, TabelaDeSimbolos.EntradaSimbolo> camposInternos = null;

            if (tipoCtx.registro() != null){
                tipoLA = TabelaDeSimbolos.TipoLA.REGISTRO;
                camposInternos = extrairCamposRegistro(tipoCtx.registro());
            } else{
                LAParser.Tipo_estendidoContext te = tipoCtx.tipo_estendido();
                if (te.tipo_basico_ident().IDENT() != null){
                    nomeTipo = te.tipo_basico_ident().getText();
                    TabelaDeSimbolos.EntradaSimbolo entradaTipo = escopos.buscarEmEscopos(nomeTipo);
                    if (entradaTipo != null && entradaTipo.campos != null){
                        camposInternos = entradaTipo.campos;
                    }
                }
            }

            for (LAParser.IdentificadorContext id : var.identificador()){
                String nomeCampo = id.IDENT(0).getText();
                campos.put(nomeCampo, new TabelaDeSimbolos.EntradaSimbolo(tipoLA, nomeTipo, null, camposInternos));
            }
        }
        return campos;
    }

    // Combina dois tipos para operações aritméticas (regras de coerção)
    private TabelaDeSimbolos.TipoLA combinarTipos(TabelaDeSimbolos.TipoLA a, TabelaDeSimbolos.TipoLA b){
        if (a == TabelaDeSimbolos.TipoLA.INVALIDO || b == TabelaDeSimbolos.TipoLA.INVALIDO)
            return TabelaDeSimbolos.TipoLA.INVALIDO;
        if (a == TabelaDeSimbolos.TipoLA.REAL || b == TabelaDeSimbolos.TipoLA.REAL)
            return TabelaDeSimbolos.TipoLA.REAL;
        if (a == TabelaDeSimbolos.TipoLA.INTEIRO && b == TabelaDeSimbolos.TipoLA.INTEIRO)
            return TabelaDeSimbolos.TipoLA.INTEIRO;
        if (a == TabelaDeSimbolos.TipoLA.LITERAL && b == TabelaDeSimbolos.TipoLA.LITERAL)
            return TabelaDeSimbolos.TipoLA.LITERAL;
        return TabelaDeSimbolos.TipoLA.INVALIDO;
    }

    // Rtorna o formato de printf/scanf para um tipo LA
    private String tipoParaFormato(TabelaDeSimbolos.TipoLA tipo){
        switch (tipo){
            case INTEIRO: return "%d";
            case REAL: return "%lf";
            case LOGICO: return "%d";
            default: return "%d";
        }
    }

    // Mapeia um tipo LA (string) para o tipo C correspondente
    private String tipoBasicoParaC(String tipo){
        switch (tipo){
            case "inteiro": return "int";
            case "real": return "double";
            case "literal": return "char";
            case "logico": return "int";
            default: return tipo;
        }
    }

    // Mapeia um tipo LA (string) para o enum TipoLA
    private TabelaDeSimbolos.TipoLA mapearTipo(String tipo){
        switch (tipo){
            case "inteiro": return TabelaDeSimbolos.TipoLA.INTEIRO;
            case "real": return TabelaDeSimbolos.TipoLA.REAL;
            case "literal": return TabelaDeSimbolos.TipoLA.LITERAL;
            case "logico": return TabelaDeSimbolos.TipoLA.LOGICO;
            default: return TabelaDeSimbolos.TipoLA.REGISTRO;
        }
    }

    // Converte um tipo LA para string C, tratando registros e ponteiros
    private String tipoParaC(LAParser.TipoContext tipoCtx){
        if (tipoCtx.registro() != null){
            return gerarRegistroInline(tipoCtx.registro());
        } else{
            LAParser.Tipo_estendidoContext te = tipoCtx.tipo_estendido();
            boolean ehPonteiro = te.PONTEIRO() != null;
            String base = tipoBasicoParaC(te.tipo_basico_ident().getText());
            return ehPonteiro ? base + "*" : base;
        }
    }

    // Gera a definição inline de um registro em C (struct) 
    private String gerarRegistroInline(LAParser.RegistroContext regCtx){
        StringBuilder sb = new StringBuilder("struct{ ");
        for (LAParser.VariavelContext var : regCtx.variavel()){
            LAParser.TipoContext tipoCtx = var.tipo();
            String tipoC = tipoParaC(tipoCtx);
            TabelaDeSimbolos.TipoLA tipoLA = obterTipoLA(tipoCtx);
            for (LAParser.IdentificadorContext id : var.identificador()){
                String nome = id.IDENT(0).getText();
                String dims = gerarDimensoes(id.dimensao());
                if (tipoLA == TabelaDeSimbolos.TipoLA.LITERAL && dims.isEmpty()){
                    dims = "[TAM_LITERAL]";
                }
                sb.append(tipoC).append(" ").append(nome).append(dims).append("; ");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    // Gera a definição de um tipo (typedef) para registros
    private String gerarDefinicaoTipo(String nome, LAParser.TipoContext tipoCtx){
        if (tipoCtx.registro() != null){
            return "typedef " + gerarRegistroInline(tipoCtx.registro()) + " " + nome;
        } else{
            return "typedef " + tipoParaC(tipoCtx) + " " + nome;
        }
    }

    // Obtém o TipoLA a partir de um contexto de tipo
    private TabelaDeSimbolos.TipoLA obterTipoLA(LAParser.TipoContext tipoCtx){
        if (tipoCtx.registro() != null){
            return TabelaDeSimbolos.TipoLA.REGISTRO;
        } else{
            LAParser.Tipo_estendidoContext te = tipoCtx.tipo_estendido();
            String textoTipo = te.tipo_basico_ident().getText();
            return mapearTipo(textoTipo);
        }
    }

    // Gera as dimensões de um array (ex: [10][20])
    private String gerarDimensoes(LAParser.DimensaoContext dim){
        StringBuilder sb = new StringBuilder();
        for (LAParser.Exp_aritmeticaContext exp : dim.exp_aritmetica()){
            sb.append("[").append(avaliarExpAritmetica(exp).codigo).append("]");
        }
        return sb.toString();
    }

    //  gera a lista de parâmetros para protótipo ou definição
    private String gerarParametros(LAParser.ParametrosContext params, boolean prototipo){
        if (params == null) return "";
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (LAParser.ParametroContext p : params.parametro()){
            boolean isVar = p.VAR() != null;
            for (LAParser.IdentificadorContext id : p.identificador()){
                if (!first) sb.append(", ");
                first = false;
                String nome = id.IDENT(0).getText();
                LAParser.Tipo_estendidoContext te = p.tipo_estendido();
                String nomeTipo = te.tipo_basico_ident().getText();
                String tipoC = tipoBasicoParaC(nomeTipo);
                // Parâmetros do tipo literal são passados como char*
                if (nomeTipo.equals("literal") && !isVar && te.PONTEIRO() == null){
                    tipoC = "char*";
                }
                if (te.PONTEIRO() != null) tipoC += "*";
                if (isVar) tipoC += "*";
                if (id.dimensao().exp_aritmetica().size() > 0){
                    if (isVar) sb.append(tipoC).append(" *").append(nome);
                    else sb.append(tipoC).append(" ").append(nome).append("[]");
                } else{
                    if (isVar) sb.append(tipoC).append("*").append(nome);
                    else sb.append(tipoC).append(" ").append(nome);
                }
            }
        }
        return sb.toString();
    }

    // Adiciona os parâmetros à tabela de símbolos do escopo interno
    private void adicionarParametros(LAParser.ParametrosContext params){
        if (params == null) return;
        TabelaDeSimbolos tabela = escopos.obterEscopoAtual();
        for (LAParser.ParametroContext p : params.parametro()){
            boolean isVar = p.VAR() != null;
            LAParser.Tipo_estendidoContext te = p.tipo_estendido();
            String textoTipo = te.tipo_basico_ident().getText();
            TabelaDeSimbolos.TipoLA tipo = mapearTipo(textoTipo);
            if (te.PONTEIRO() != null || isVar) tipo = TabelaDeSimbolos.TipoLA.PONTEIRO;
            for (LAParser.IdentificadorContext id : p.identificador()){
                tabela.adicionar(id.IDENT(0).getText(), tipo, textoTipo);
            }
        }
    }
}