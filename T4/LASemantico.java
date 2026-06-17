import org.antlr.v4.runtime.Token;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * ANALISADOR SEMÂNTICO DA LINGUAGEM LA
 * 
 * Estende o T3 com verificações adicionais:
 *   - Incompatibilidade de argumentos em chamadas de procedimentos/funções
 *   - Atribuição com ponteiros e registros
 *   - Uso do comando 'retorne' apenas dentro de funções
 *   - Declaração duplicada abrangendo todas as categorias
 *   - Identificador não declarado em todos os contextos
 */
public class LASemantico extends LAParserBaseVisitor<TabelaDeSimbolos.TipoLA>{

    // Gerenciador da pilha de escopos
    private final Escopos escopos;
    private boolean dentroDeFuncao = false;

    public LASemantico(){
        escopos = new Escopos();
    }

    private static class ResolucaoIdentificador{
        TabelaDeSimbolos.TipoLA tipo;
        String nomeTipo;
        String nomeCompleto;
    }

    // Resolve o tipo de um identificador (possivelmente com campos de registro). Retorna um objeto com tipo, nomeTipo e o nome completo.
    private ResolucaoIdentificador resolverIdentificadorCompleto(LAParser.IdentificadorContext ctx){
        ResolucaoIdentificador res = new ResolucaoIdentificador();
        res.nomeCompleto = ctx.getText();

        String nomeBase = ctx.IDENT(0).getText();
        TabelaDeSimbolos.EntradaSimbolo entrada = buscarEntradaCaseInsensitive(nomeBase);
        if (entrada == null){
            res.tipo = TabelaDeSimbolos.TipoLA.INVALIDO;
            return res;
        }

        // Se não houver campos, retorna o tipo do identificador
        if (ctx.IDENT().size() == 1){
            res.tipo = entrada.tipo;
            res.nomeTipo = entrada.nomeTipo;
            return res;
        }

        // Percorre os campos
        TabelaDeSimbolos.TipoLA tipoAtual = entrada.tipo;
        String nomeTipoAtual = entrada.nomeTipo;
        Map<String, TabelaDeSimbolos.EntradaSimbolo> camposAtuais = entrada.campos;

        for (int i = 1; i < ctx.IDENT().size(); i++){
            String nomeCampo = ctx.IDENT(i).getText();
            if (camposAtuais == null || !camposAtuais.containsKey(nomeCampo)){
                res.tipo = TabelaDeSimbolos.TipoLA.INVALIDO;
                return res;
            }
            TabelaDeSimbolos.EntradaSimbolo campo = camposAtuais.get(nomeCampo);
            tipoAtual = campo.tipo;
            nomeTipoAtual = campo.nomeTipo;
            camposAtuais = campo.campos;
        }
        res.tipo = tipoAtual;
        res.nomeTipo = nomeTipoAtual;
        return res;
    }

    // Retorna o mapa de campos de um tipo nomeado (registro ou tipo definido pelo usuário).
    private Map<String, TabelaDeSimbolos.EntradaSimbolo> obterCamposDeTipo(String nomeTipo){
        if (nomeTipo == null) return null;
        TabelaDeSimbolos.EntradaSimbolo entrada = escopos.buscarEmEscopos(nomeTipo);
        if (entrada != null && (entrada.tipo == TabelaDeSimbolos.TipoLA.TIPO_USUARIO ||
                                entrada.tipo == TabelaDeSimbolos.TipoLA.REGISTRO)){
            return entrada.campos;
        }
        return null;
    }

    /*
     * Converte um texto de tipo (básico ou definido pelo usuário) para o
     * enum TipoLA correspondente. Se o tipo não for básico, busca na tabela
     * de símbolos. Se não encontrar e reportarErro for true, adiciona erro.
     */
    private TabelaDeSimbolos.TipoLA resolverTipo(String nomeDoTipo, Token tokenErro, boolean reportarErro){
        String tipo = nomeDoTipo.replace("^", "").trim();

        switch (tipo){
            case "inteiro":  return TabelaDeSimbolos.TipoLA.INTEIRO;
            case "real":     return TabelaDeSimbolos.TipoLA.REAL;
            case "literal":  return TabelaDeSimbolos.TipoLA.LITERAL;
            case "logico":   return TabelaDeSimbolos.TipoLA.LOGICO;
            default:
                TabelaDeSimbolos.EntradaSimbolo entrada = escopos.buscarEmEscopos(tipo);
                if (entrada == null){
                    if (reportarErro && tokenErro != null){
                        LASemanticoUtils.adicionarErroSemantico(tokenErro,
                                "tipo " + tipo + " nao declarado");
                    }
                    return TabelaDeSimbolos.TipoLA.INVALIDO;
                }
                // Se for TIPO_USUARIO, retorna REGISTRO (tipos definidos pelo usuário são registros)
                if (entrada.tipo == TabelaDeSimbolos.TipoLA.TIPO_USUARIO){
                    return TabelaDeSimbolos.TipoLA.REGISTRO;
                }
                return entrada.tipo;
        }
    }

    // Verifica se dois tipos são compatíveis para atribuição, conforme as regras da especificação da linguagem.
    private boolean tiposCompativeis(TabelaDeSimbolos.TipoLA tipoEsq,
                                     TabelaDeSimbolos.TipoLA tipoDir,
                                     String nomeTipoEsq, String nomeTipoDir){

        if (tipoEsq == TabelaDeSimbolos.TipoLA.INVALIDO) return true;
        if (tipoDir == TabelaDeSimbolos.TipoLA.INVALIDO) return false;

        // Ponteiro ← endereço (ambos PONTEIRO)
        if (tipoEsq == TabelaDeSimbolos.TipoLA.PONTEIRO && tipoDir == TabelaDeSimbolos.TipoLA.PONTEIRO){
            return true;
        }

        // Registro ← registro (mesmo nome de tipo)
        if (tipoEsq == TabelaDeSimbolos.TipoLA.REGISTRO && tipoDir == TabelaDeSimbolos.TipoLA.REGISTRO){
            return nomeTipoEsq != null && nomeTipoEsq.equals(nomeTipoDir);
        }

        if (tipoEsq == TabelaDeSimbolos.TipoLA.INVALIDO){
            return true; // já reportado como não declarado
        }
        if (tipoDir == TabelaDeSimbolos.TipoLA.INVALIDO){
            return false;
        }
        if (tipoEsq == tipoDir){
            return true;
        }
        // inteiro e real são mutuamente compatíveis
        boolean esqNumerico = tipoEsq == TabelaDeSimbolos.TipoLA.INTEIRO
                           || tipoEsq == TabelaDeSimbolos.TipoLA.REAL;
        boolean dirNumerico = tipoDir == TabelaDeSimbolos.TipoLA.INTEIRO
                           || tipoDir == TabelaDeSimbolos.TipoLA.REAL;
        if (esqNumerico && dirNumerico) return true;
        return false;
    }

    // Sobrecarga para manter compatibilidade com chamadas antigas (sem nomeTipo)
    private boolean tiposCompativeis(TabelaDeSimbolos.TipoLA tipoEsq, TabelaDeSimbolos.TipoLA tipoDir){
        return tiposCompativeis(tipoEsq, tipoDir, null, null);
    }

    /*
     * Resolve o tipo de um identificador (pode ter acesso a campos).
     * Para simplificar, campos de registro retornam INVALIDO (não exigido pelos casos de teste).
     */
    private TabelaDeSimbolos.TipoLA resolverTipoIdentificador(LAParser.IdentificadorContext ctx){
        ResolucaoIdentificador res = resolverIdentificadorCompleto(ctx);
        return res.tipo;
    }

    // Métodos de resolução de tipos para expressões (delegam recursivamente)

    private TabelaDeSimbolos.TipoLA resolverTipoExpressao(LAParser.ExpressaoContext ctx){
        if (ctx == null) return TabelaDeSimbolos.TipoLA.INVALIDO;
        TabelaDeSimbolos.TipoLA tipo = resolverTipoTermoLogico(ctx.termo_logic(0));
        if (ctx.termo_logic().size() > 1){
            return TabelaDeSimbolos.TipoLA.LOGICO;
        }
        return tipo;
    }

    private TabelaDeSimbolos.TipoLA resolverTipoTermoLogico(LAParser.Termo_logicContext ctx){
        if (ctx == null) return TabelaDeSimbolos.TipoLA.INVALIDO;
        TabelaDeSimbolos.TipoLA tipo = resolverTipoFatorLogico(ctx.fator_logic(0));
        if (ctx.fator_logic().size() > 1){
            return TabelaDeSimbolos.TipoLA.LOGICO;
        }
        return tipo;
    }

    private TabelaDeSimbolos.TipoLA resolverTipoFatorLogico(LAParser.Fator_logicContext ctx){
        if (ctx == null) return TabelaDeSimbolos.TipoLA.INVALIDO;
        return resolverTipoParcela_logica(ctx.parcela_logic());
    }

    private TabelaDeSimbolos.TipoLA resolverTipoParcela_logica(LAParser.Parcela_logicContext ctx){
        if (ctx == null) return TabelaDeSimbolos.TipoLA.INVALIDO;
        if (ctx instanceof LAParser.ParcelaLogicaConstanteContext){
            return TabelaDeSimbolos.TipoLA.LOGICO;
        }
        if (ctx instanceof LAParser.ParcelaLogicaRelacionalContext){
            LAParser.ParcelaLogicaRelacionalContext rel =
                    (LAParser.ParcelaLogicaRelacionalContext) ctx;
            return resolverTipoExpRelacional(rel.exp_relacional());
        }
        return TabelaDeSimbolos.TipoLA.INVALIDO;
    }

    private TabelaDeSimbolos.TipoLA resolverTipoExpRelacional(LAParser.Exp_relacionalContext ctx){
        if (ctx == null) return TabelaDeSimbolos.TipoLA.INVALIDO;
        if (ctx.op_relacional() != null){
            return TabelaDeSimbolos.TipoLA.LOGICO;
        }
        return resolverTipoExpAritmetica(ctx.exp_aritmetica(0));
    }

    private TabelaDeSimbolos.TipoLA resolverTipoExpAritmetica(LAParser.Exp_aritmeticaContext ctx){
        if (ctx == null) return TabelaDeSimbolos.TipoLA.INVALIDO;
        TabelaDeSimbolos.TipoLA tipo = resolverTipoTermo(ctx.termo(0));
        for (int i = 1; i < ctx.termo().size(); i++){
            TabelaDeSimbolos.TipoLA tipoDir = resolverTipoTermo(ctx.termo(i));
            tipo = combinarTiposAritmeticos(tipo, tipoDir);
        }
        return tipo;
    }

    private TabelaDeSimbolos.TipoLA resolverTipoTermo(LAParser.TermoContext ctx){
        if (ctx == null) return TabelaDeSimbolos.TipoLA.INVALIDO;
        TabelaDeSimbolos.TipoLA tipo = resolverTipoFator(ctx.fator(0));
        for (int i = 1; i < ctx.fator().size(); i++){
            tipo = combinarTiposAritmeticos(tipo, resolverTipoFator(ctx.fator(i)));
        }
        return tipo;
    }

    private TabelaDeSimbolos.TipoLA resolverTipoFator(LAParser.FatorContext ctx){
        if (ctx == null) return TabelaDeSimbolos.TipoLA.INVALIDO;
        return resolverTipoParcela(ctx.parcela(0));
    }

    private TabelaDeSimbolos.TipoLA resolverTipoParcela(LAParser.ParcelaContext ctx){
        if (ctx == null) return TabelaDeSimbolos.TipoLA.INVALIDO;
        if (ctx.parcela_unario() != null){
            return resolverTipoParcela_unario(ctx.parcela_unario());
        }
        if (ctx.parcela_nao_unario() != null){
            return resolverTipoParcela_nao_unario(ctx.parcela_nao_unario());
        }
        return TabelaDeSimbolos.TipoLA.INVALIDO;
    }

    private TabelaDeSimbolos.TipoLA resolverTipoParcela_unario(LAParser.Parcela_unarioContext ctx){
        if (ctx == null) return TabelaDeSimbolos.TipoLA.INVALIDO;
        if (ctx instanceof LAParser.ParcelaIdentificadorContext){
            return resolverTipoIdentificador(((LAParser.ParcelaIdentificadorContext) ctx).identificador());
        }
        if (ctx instanceof LAParser.ParcelaChamadaFuncaoContext){
            LAParser.ParcelaChamadaFuncaoContext chamada =
                    (LAParser.ParcelaChamadaFuncaoContext) ctx;
            String nomeFuncao = chamada.IDENT().getText();
            TabelaDeSimbolos.EntradaSimbolo entrada = escopos.buscarEmEscopos(nomeFuncao);
            if (entrada != null && entrada.tipo == TabelaDeSimbolos.TipoLA.FUNCAO){
                return resolverTipo(entrada.nomeTipo, null, false);
            }
            return TabelaDeSimbolos.TipoLA.INVALIDO;
        }
        if (ctx instanceof LAParser.ParcelaInteiroContext){
            return TabelaDeSimbolos.TipoLA.INTEIRO;
        }
        if (ctx instanceof LAParser.ParcelaRealContext){
            return TabelaDeSimbolos.TipoLA.REAL;
        }
        if (ctx instanceof LAParser.ParcelaParentesesContext){
            return resolverTipoExpressao(((LAParser.ParcelaParentesesContext) ctx).expressao());
        }
        return TabelaDeSimbolos.TipoLA.INVALIDO;
    }

    private TabelaDeSimbolos.TipoLA resolverTipoParcela_nao_unario(
            LAParser.Parcela_nao_unarioContext ctx){
        if (ctx == null) return TabelaDeSimbolos.TipoLA.INVALIDO;
        if (ctx instanceof LAParser.ParcelaCadeiaContext){
            return TabelaDeSimbolos.TipoLA.LITERAL;
        }
        if (ctx instanceof LAParser.ParcelaEnderecoContext){
            return TabelaDeSimbolos.TipoLA.PONTEIRO;
        }
        return TabelaDeSimbolos.TipoLA.INVALIDO;
    }

    /*
     * Combina dois tipos em operações aritméticas (+, -, *, /, %):
     * - inteiro com inteiro -> inteiro
     * - real com real -> real
     * - inteiro com real -> real
     * - literal com literal -> literal (concatenação com '+')
     * - outros -> INVALIDO
     */
    private TabelaDeSimbolos.TipoLA combinarTiposAritmeticos(
            TabelaDeSimbolos.TipoLA t1, TabelaDeSimbolos.TipoLA t2){
        if (t1 == TabelaDeSimbolos.TipoLA.INVALIDO || t2 == TabelaDeSimbolos.TipoLA.INVALIDO){
            return TabelaDeSimbolos.TipoLA.INVALIDO;
        }
        boolean n1 = t1 == TabelaDeSimbolos.TipoLA.INTEIRO || t1 == TabelaDeSimbolos.TipoLA.REAL;
        boolean n2 = t2 == TabelaDeSimbolos.TipoLA.INTEIRO || t2 == TabelaDeSimbolos.TipoLA.REAL;
        if (n1 && n2){
            return (t1 == TabelaDeSimbolos.TipoLA.REAL || t2 == TabelaDeSimbolos.TipoLA.REAL)
                    ? TabelaDeSimbolos.TipoLA.REAL
                    : TabelaDeSimbolos.TipoLA.INTEIRO;
        }
        if (t1 == TabelaDeSimbolos.TipoLA.LITERAL && t2 == TabelaDeSimbolos.TipoLA.LITERAL){
            return TabelaDeSimbolos.TipoLA.LITERAL;
        }
        return TabelaDeSimbolos.TipoLA.INVALIDO;
    }

    /*
    * Extrai os campos de um registro (definição inline).
    * Retorna um mapa nome -> EntradaSimbolo.
    */
    private Map<String, TabelaDeSimbolos.EntradaSimbolo> extrairCamposRegistro(LAParser.RegistroContext ctx){
        Map<String, TabelaDeSimbolos.EntradaSimbolo> campos = new HashMap<>();
        for (LAParser.VariavelContext var : ctx.variavel()){
            LAParser.TipoContext tipoCtx = var.tipo();
            TabelaDeSimbolos.TipoLA tipoCampo;
            String nomeTipoCampo = null;
            Map<String, TabelaDeSimbolos.EntradaSimbolo> camposInternos = null;
            if (tipoCtx.registro() != null){
                tipoCampo = TabelaDeSimbolos.TipoLA.REGISTRO;
                camposInternos = extrairCamposRegistro(tipoCtx.registro());
            } else{
                LAParser.Tipo_estendidoContext te = tipoCtx.tipo_estendido();
                boolean ehPonteiro = te.PONTEIRO() != null;
                String textoTipo = te.tipo_basico_ident().getText();
                nomeTipoCampo = textoTipo;
                if (ehPonteiro){
                    tipoCampo = TabelaDeSimbolos.TipoLA.PONTEIRO;
                } else{
                    tipoCampo = resolverTipo(textoTipo, te.tipo_basico_ident().start, true);
                    if (tipoCampo == TabelaDeSimbolos.TipoLA.REGISTRO){
                        TabelaDeSimbolos.EntradaSimbolo entradaTipo = escopos.buscarEmEscopos(textoTipo);
                        if (entradaTipo != null && entradaTipo.campos != null){
                            camposInternos = entradaTipo.campos;
                        }
                    }
                }
            }
            for (LAParser.IdentificadorContext id : var.identificador()){
                String nomeCampo = id.IDENT(0).getText();
                campos.put(nomeCampo, new TabelaDeSimbolos.EntradaSimbolo(tipoCampo, nomeTipoCampo, null, camposInternos));
            }
        }
        return campos;
    }

    @Override
    public TabelaDeSimbolos.TipoLA visitPrograma(LAParser.ProgramaContext ctx){
        return super.visitPrograma(ctx);
    }

    // DECLARAÇÕES LOCAIS

    //declare identificador(es) : tipo
    @Override
    public TabelaDeSimbolos.TipoLA visitDeclaracaoVariavel(
            LAParser.DeclaracaoVariavelContext ctx){
        TabelaDeSimbolos tabela = escopos.obterEscopoAtual();
        LAParser.TipoContext tipoCtx = ctx.variavel().tipo();
        TabelaDeSimbolos.TipoLA tipoResolvido;
        String nomeTipoTexto = null;
        Map<String, TabelaDeSimbolos.EntradaSimbolo> campos = null;

        if (tipoCtx.registro() != null){
            tipoResolvido = TabelaDeSimbolos.TipoLA.REGISTRO;
            campos = extrairCamposRegistro(tipoCtx.registro());
        } else{
            LAParser.Tipo_estendidoContext te = tipoCtx.tipo_estendido();
            boolean ehPonteiro = te.PONTEIRO() != null;
            String textoTipo = te.tipo_basico_ident().getText();
            nomeTipoTexto = textoTipo;
            if (ehPonteiro){
                tipoResolvido = TabelaDeSimbolos.TipoLA.PONTEIRO;
            } else{
                tipoResolvido = resolverTipo(textoTipo, te.tipo_basico_ident().start, true);
                if (tipoResolvido == TabelaDeSimbolos.TipoLA.REGISTRO){
                    TabelaDeSimbolos.EntradaSimbolo entradaTipo = escopos.buscarEmEscopos(textoTipo);
                    if (entradaTipo != null && entradaTipo.campos != null){
                        campos = entradaTipo.campos;
                    }
                }
            }
        }

        for (LAParser.IdentificadorContext id : ctx.variavel().identificador()){
            String nome = id.IDENT(0).getText();
            if (tabela.existe(nome)){
                LASemanticoUtils.adicionarErroSemantico(id.start,
                        "identificador " + nome + " ja declarado anteriormente");
            } else{
                tabela.adicionar(nome, tipoResolvido, nomeTipoTexto, null, campos);
            }
        }
        return super.visitDeclaracaoVariavel(ctx);
    }

    //constante IDENT : tipo_basico = valor
    @Override
    public TabelaDeSimbolos.TipoLA visitDeclaracaoConstante(
            LAParser.DeclaracaoConstanteContext ctx){
        TabelaDeSimbolos tabela = escopos.obterEscopoAtual();
        String nome = ctx.IDENT().getText();
        if (tabela.existe(nome)){
            LASemanticoUtils.adicionarErroSemantico(ctx.IDENT().getSymbol(),
                    "identificador " + nome + " ja declarado anteriormente");
        } else{
            String textoTipo = ctx.tipo_basico().getText();
            TabelaDeSimbolos.TipoLA tipo = resolverTipo(textoTipo, ctx.tipo_basico().start, false);
            tabela.adicionar(nome, tipo, textoTipo);
        }
        return super.visitDeclaracaoConstante(ctx);
    }

    //tipo IDENT : tipo
    @Override
    public TabelaDeSimbolos.TipoLA visitDeclaracaoTipo(
            LAParser.DeclaracaoTipoContext ctx){
        TabelaDeSimbolos tabela = escopos.obterEscopoAtual();
        String nome = ctx.IDENT().getText();
        if (tabela.existe(nome)){
            LASemanticoUtils.adicionarErroSemantico(ctx.IDENT().getSymbol(),
                    "identificador " + nome + " ja declarado anteriormente");
        } else{
            LAParser.TipoContext tipoCtx = ctx.tipo();
            Map<String, TabelaDeSimbolos.EntradaSimbolo> campos = null;
            if (tipoCtx.registro() != null){
                campos = extrairCamposRegistro(tipoCtx.registro());
            }
            tabela.adicionar(nome, TabelaDeSimbolos.TipoLA.TIPO_USUARIO, tipoCtx.getText(), null, campos);
        }
        return super.visitDeclaracaoTipo(ctx);
    }

    // PROCEDIMENTOS E FUNÇÕES

    @Override
    public TabelaDeSimbolos.TipoLA visitProcedimento(LAParser.ProcedimentoContext ctx){
        String nome = ctx.IDENT().getText();
        TabelaDeSimbolos tabelaExterna = escopos.obterEscopoAtual();

        // Coleta parâmetros formais
        List<TabelaDeSimbolos.ParametroInfo> params = new ArrayList<>();
        if (ctx.parametros() != null){
            params = coletarParametros(ctx.parametros());
        }

        // Verifica duplicidade
        if (tabelaExterna.existe(nome)){
            LASemanticoUtils.adicionarErroSemantico(ctx.IDENT().getSymbol(),
                    "identificador " + nome + " ja declarado anteriormente");
        } else{
            // Armazena procedimento com a lista de parâmetros
            tabelaExterna.adicionar(nome, TabelaDeSimbolos.TipoLA.PROCEDIMENTO, null, params);
        }

        // Abre escopo e registra parâmetros no escopo interno
        escopos.criarNovoEscopo();
        registrarParametrosNoEscopo(params);
        super.visitProcedimento(ctx);
        escopos.abandonarEscopo();
        return null;
    }

    @Override
    public TabelaDeSimbolos.TipoLA visitFuncao(LAParser.FuncaoContext ctx){
        String nome = ctx.IDENT().getText();
        TabelaDeSimbolos tabelaExterna = escopos.obterEscopoAtual();

        // Resolve tipo de retorno
        String textoTipoRetorno = ctx.tipo_estendido().getText();
        String tipoSemPonteiro = textoTipoRetorno.replace("^", "").trim();
        resolverTipo(tipoSemPonteiro, ctx.tipo_estendido().start, true);

        // Coleta parâmetros
        List<TabelaDeSimbolos.ParametroInfo> params = new ArrayList<>();
        if (ctx.parametros() != null){
            params = coletarParametros(ctx.parametros());
        }

        if (tabelaExterna.existe(nome)){
            LASemanticoUtils.adicionarErroSemantico(ctx.IDENT().getSymbol(),
                    "identificador " + nome + " ja declarado anteriormente");
        } else{
            tabelaExterna.adicionar(nome, TabelaDeSimbolos.TipoLA.FUNCAO, tipoSemPonteiro, params);
        }

        // Abre escopo e registra parâmetros
        escopos.criarNovoEscopo();
        registrarParametrosNoEscopo(params);

        // Marca que estamos dentro de uma função para permitir 'retorne'
        boolean antigo = dentroDeFuncao;
        dentroDeFuncao = true;
        super.visitFuncao(ctx);
        dentroDeFuncao = antigo;

        escopos.abandonarEscopo();
        return null;
    }

    // Coleta parâmetros formais de um contexto de parâmetros.
    private List<TabelaDeSimbolos.ParametroInfo> coletarParametros(LAParser.ParametrosContext ctx){
        List<TabelaDeSimbolos.ParametroInfo> lista = new ArrayList<>();
        for (LAParser.ParametroContext param : ctx.parametro()){
            LAParser.Tipo_estendidoContext te = param.tipo_estendido();
            boolean ehPonteiro = te.PONTEIRO() != null;
            String textoTipo = te.tipo_basico_ident().getText();
            TabelaDeSimbolos.TipoLA tipo;
            if (ehPonteiro){
                tipo = TabelaDeSimbolos.TipoLA.PONTEIRO;
            } else{
                tipo = resolverTipo(textoTipo, te.tipo_basico_ident().start, true);
            }
            // Cada identificador neste parâmetro
            for (LAParser.IdentificadorContext id : param.identificador()){
                String nome = id.IDENT(0).getText();
                lista.add(new TabelaDeSimbolos.ParametroInfo(nome, tipo, textoTipo));
            }
        }
        return lista;
    }

    // Registra os parâmetros no escopo atual (interno).
    private void registrarParametrosNoEscopo(List<TabelaDeSimbolos.ParametroInfo> params){
        TabelaDeSimbolos tabela = escopos.obterEscopoAtual();
        for (TabelaDeSimbolos.ParametroInfo p : params){
            if (tabela.existe(p.nome)){
                // Duplicidade de parâmetro não é foco, mas podemos ignorar
            }
            // Se for registro, busca os campos do tipo
            Map<String, TabelaDeSimbolos.EntradaSimbolo> campos = null;
            if (p.tipo == TabelaDeSimbolos.TipoLA.REGISTRO){
                campos = obterCamposDeTipo(p.nomeTipo);
            }
            tabela.adicionar(p.nome, p.tipo, p.nomeTipo, null, campos);
        }
    }

    // COMANDOS

    // Verifica chamada de procedimento (como comando).
    @Override
    public TabelaDeSimbolos.TipoLA visitCmdChamada(LAParser.CmdChamadaContext ctx){
        String nome = ctx.IDENT().getText();
        TabelaDeSimbolos.EntradaSimbolo entrada = escopos.buscarEmEscopos(nome);
        if (entrada == null){
            LASemanticoUtils.adicionarErroSemantico(ctx.IDENT().getSymbol(),
                    "identificador " + nome + " nao declarado");
            return super.visitCmdChamada(ctx);
        }

        // Verifica se é procedimento (ou função)
        if (entrada.tipo != TabelaDeSimbolos.TipoLA.PROCEDIMENTO &&
            entrada.tipo != TabelaDeSimbolos.TipoLA.FUNCAO){
            LASemanticoUtils.adicionarErroSemantico(ctx.IDENT().getSymbol(),
                    "identificador " + nome + " nao é um procedimento ou funcao");
            return super.visitCmdChamada(ctx);
        }

        // Verifica argumentos
        verificarArgumentos(ctx.expressao(), entrada.parametros, ctx.IDENT().getSymbol(), nome);
        return super.visitCmdChamada(ctx);
    }

    // Verifica chamada de função (como parcela de expressão).
    @Override
    public TabelaDeSimbolos.TipoLA visitParcelaChamadaFuncao(LAParser.ParcelaChamadaFuncaoContext ctx){
        String nome = ctx.IDENT().getText();
        TabelaDeSimbolos.EntradaSimbolo entrada = escopos.buscarEmEscopos(nome);
        if (entrada == null){
            LASemanticoUtils.adicionarErroSemantico(ctx.IDENT().getSymbol(),
                    "identificador " + nome + " nao declarado");
            return TabelaDeSimbolos.TipoLA.INVALIDO;
        }

        if (entrada.tipo != TabelaDeSimbolos.TipoLA.FUNCAO){
            LASemanticoUtils.adicionarErroSemantico(ctx.IDENT().getSymbol(),
                    "identificador " + nome + " nao é uma funcao");
            return TabelaDeSimbolos.TipoLA.INVALIDO;
        }

        verificarArgumentos(ctx.expressao(), entrada.parametros, ctx.IDENT().getSymbol(), nome);
        // O tipo de retorno já é resolvido em outro método (resolverTipoParcela_unario)
        return super.visitParcelaChamadaFuncao(ctx);
    }

    /*
     * Verifica número, ordem e tipos dos argumentos.
     * Os argumentos estão em uma lista de expressões (pode ser null se não houver).
     */
    private void verificarArgumentos(List<LAParser.ExpressaoContext> args,
                                    List<TabelaDeSimbolos.ParametroInfo> params,
                                    Token tokenErro, String nomeFuncao){
        if (params == null) params = new ArrayList<>();
        if (args == null) args = new ArrayList<>();

        // Número de argumentos
        if (args.size() != params.size()){
            LASemanticoUtils.adicionarErroSemantico(tokenErro,
                    "incompatibilidade de parametros na chamada de " + nomeFuncao);
            return;
        }

        // Verifica cada argumento
        for (int i = 0; i < args.size(); i++){
            LAParser.ExpressaoContext argCtx = args.get(i);
            TabelaDeSimbolos.ParametroInfo param = params.get(i);
            TabelaDeSimbolos.TipoLA tipoArg = resolverTipoExpressao(argCtx);
            boolean compativel = false;
            if (tipoArg == param.tipo){
                compativel = true;
            } else if (param.tipo == TabelaDeSimbolos.TipoLA.PONTEIRO && tipoArg == TabelaDeSimbolos.TipoLA.PONTEIRO){
                compativel = true;
            } else if (param.tipo == TabelaDeSimbolos.TipoLA.REGISTRO && tipoArg == TabelaDeSimbolos.TipoLA.REGISTRO){
                compativel = true; // simplificação
            }
            if (!compativel){
                LASemanticoUtils.adicionarErroSemantico(tokenErro,
                        "incompatibilidade de parametros na chamada de " + nomeFuncao);
                return;
            }
        }
    }
    
    @Override
    public TabelaDeSimbolos.TipoLA visitCmdAtribuicao(LAParser.CmdAtribuicaoContext ctx){
        LAParser.IdentificadorContext idCtx = ctx.identificador();
        ResolucaoIdentificador resEsq = resolverIdentificadorCompleto(idCtx);
        String nomeBase = idCtx.IDENT(0).getText();
        String nomeCompleto = resEsq.nomeCompleto;
        if (ctx.PONTEIRO() != null){
            nomeCompleto = "^" + nomeCompleto;
        }

        // Verifica se a base existe
        if (!escopos.existeEmAlgumEscopo(nomeBase)){
            LASemanticoUtils.adicionarErroSemantico(idCtx.start,
                    "identificador " + nomeBase + " nao declarado");
            visit(ctx.expressao());
            return null;
        }

        // Se o identificador completo não existe (campo não declarado)
        if (resEsq.tipo == TabelaDeSimbolos.TipoLA.INVALIDO){
            LASemanticoUtils.adicionarErroSemantico(idCtx.start,
                    "identificador " + nomeCompleto + " nao declarado");
            visit(ctx.expressao());
            return null;
        }

        TabelaDeSimbolos.TipoLA tipoEsq = resEsq.tipo;
        String nomeTipoEsq = resEsq.nomeTipo;

        if (ctx.PONTEIRO() != null){
            tipoEsq = TabelaDeSimbolos.TipoLA.PONTEIRO;
            nomeTipoEsq = null;
        }

        TabelaDeSimbolos.TipoLA tipoDir = resolverTipoExpressao(ctx.expressao());

        // Para registro, tentamos obter nomeTipoDir (simplificado)
        String nomeTipoDir = null;
        if (tipoDir == TabelaDeSimbolos.TipoLA.REGISTRO){
            nomeTipoDir = obterNomeTipoDaExpressao(ctx.expressao());
        }


        boolean compativel = tiposCompativeis(tipoEsq, tipoDir, nomeTipoEsq, nomeTipoDir);

        if (!compativel){
            LASemanticoUtils.adicionarErroSemantico(idCtx.start,
                    "atribuicao nao compativel para " + nomeCompleto);
        }

        return super.visitCmdAtribuicao(ctx);
    }

    @Override
    public TabelaDeSimbolos.TipoLA visitCmdLeia(LAParser.CmdLeiaContext ctx){
        for (LAParser.IdentificadorContext id : ctx.identificador()){
            ResolucaoIdentificador res = resolverIdentificadorCompleto(id);
            if (res.tipo == TabelaDeSimbolos.TipoLA.INVALIDO){
                String nomeBase = id.IDENT(0).getText();
                if (escopos.existeEmAlgumEscopo(nomeBase)){
                    LASemanticoUtils.adicionarErroSemantico(id.start,
                            "identificador " + res.nomeCompleto + " nao declarado");
                } else{
                    LASemanticoUtils.adicionarErroSemantico(id.start,
                            "identificador " + nomeBase + " nao declarado");
                }
            }
        }
        return super.visitCmdLeia(ctx);
    }

    @Override
    public TabelaDeSimbolos.TipoLA visitCmdEscreva(LAParser.CmdEscrevaContext ctx){
        return super.visitCmdEscreva(ctx);
    }

    @Override
    public TabelaDeSimbolos.TipoLA visitCmdRetorne(LAParser.CmdRetorneContext ctx){
        if (!dentroDeFuncao){
            LASemanticoUtils.adicionarErroSemantico(ctx.RETORNE().getSymbol(),
                    "comando retorne nao permitido nesse escopo");
        }
        // Visita a expressão para verificar identificadores declarados
        return super.visitCmdRetorne(ctx);
    }

    // EXPRESSÕES (VERIFICAÇÃO DE DECLARAÇÃO)

    @Override
    public TabelaDeSimbolos.TipoLA visitParcelaIdentificador(
            LAParser.ParcelaIdentificadorContext ctx){
        ResolucaoIdentificador res = resolverIdentificadorCompleto(ctx.identificador());
        if (res.tipo == TabelaDeSimbolos.TipoLA.INVALIDO){
            LASemanticoUtils.adicionarErroSemantico(ctx.identificador().start,
                    "identificador " + res.nomeCompleto + " nao declarado");
        }
        return super.visitParcelaIdentificador(ctx);
    }

    @Override
    public TabelaDeSimbolos.TipoLA visitParcelaEndereco(
            LAParser.ParcelaEnderecoContext ctx){
        String nome = ctx.identificador().IDENT(0).getText();
        if (!escopos.existeEmAlgumEscopo(nome)){
            LASemanticoUtils.adicionarErroSemantico(ctx.identificador().start,
                    "identificador " + nome + " nao declarado");
        }
        return super.visitParcelaEndereco(ctx);
    }

    @Override
    public TabelaDeSimbolos.TipoLA visitCmdPara(LAParser.CmdParaContext ctx){
        String nome = ctx.IDENT().getText();
        if (!escopos.existeEmAlgumEscopo(nome)){
            LASemanticoUtils.adicionarErroSemantico(ctx.IDENT().getSymbol(),
                    "identificador " + nome + " nao declarado");
        }
        return super.visitCmdPara(ctx);
    }

    private TabelaDeSimbolos.EntradaSimbolo buscarEntradaCaseInsensitive(String nome){
        // A linguagem LA diferencia maiúsculas de minúsculas,
        // portanto usamos equals (não equalsIgnoreCase).
        for (TabelaDeSimbolos tabela : escopos.percorrerEscoposAninhados()){
            for (Map.Entry<String, TabelaDeSimbolos.EntradaSimbolo> entry : tabela.entradas()){
                if (entry.getKey().equals(nome)){
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    private String obterNomeTipoDaExpressao(LAParser.ExpressaoContext ctx){
        if (ctx == null) return null;
        // Navega pela árvore da expressão para encontrar um identificador
        if (!ctx.termo_logic().isEmpty()){
            LAParser.Termo_logicContext tl = ctx.termo_logic(0);
            if (!tl.fator_logic().isEmpty()){
                LAParser.Fator_logicContext fl = tl.fator_logic(0);
                LAParser.Parcela_logicContext pl = fl.parcela_logic();
                if (pl instanceof LAParser.ParcelaLogicaRelacionalContext){
                    LAParser.ParcelaLogicaRelacionalContext rel = (LAParser.ParcelaLogicaRelacionalContext) pl;
                    LAParser.Exp_relacionalContext er = rel.exp_relacional();
                    if (er != null && !er.exp_aritmetica().isEmpty()){
                        LAParser.Exp_aritmeticaContext ea = er.exp_aritmetica(0);
                        if (!ea.termo().isEmpty()){
                            LAParser.TermoContext t = ea.termo(0);
                            if (!t.fator().isEmpty()){
                                LAParser.FatorContext f = t.fator(0);
                                if (!f.parcela().isEmpty()){
                                    LAParser.ParcelaContext p = f.parcela(0);
                                    if (p.parcela_unario() != null){
                                        LAParser.Parcela_unarioContext pu = p.parcela_unario();
                                        if (pu instanceof LAParser.ParcelaIdentificadorContext){
                                            LAParser.ParcelaIdentificadorContext pid = (LAParser.ParcelaIdentificadorContext) pu;
                                            ResolucaoIdentificador res = resolverIdentificadorCompleto(pid.identificador());
                                            return res.nomeTipo;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
}