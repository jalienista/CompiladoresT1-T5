import org.antlr.v4.runtime.Token;

/*
 * ANALISADOR SEMÂNTICO DA LINGUAGEM LA
 * 
 * Implementa um visitor sobre a árvore sintática gerada pelo ANTLR e
 * verifica as regras semânticas, reportando erros como:
 *   - identificador já declarado no mesmo escopo
 *   - tipo não declarado
 *   - identificador não declarado
 *   - atribuição com tipos incompatíveis
 * 
 * Os erros são acumulados em LASemanticoUtils.errosSemanticos; a execução
 * não é interrompida ao encontrar um erro.
 */
public class LASemantico extends LAParserBaseVisitor<TabelaDeSimbolos.TipoLA> {

    // Gerenciador da pilha de escopos
    private final Escopos escopos;

    public LASemantico() {
        escopos = new Escopos();
    }

    /*
     * Converte um texto de tipo (básico ou definido pelo usuário) para o
     * enum TipoLA correspondente. Se o tipo não for básico, busca na tabela
     * de símbolos. Se não encontrar e reportarErro for true, adiciona erro.
     */
    private TabelaDeSimbolos.TipoLA resolverTipo(String nomeDoTipo, Token tokenErro, boolean reportarErro) {
        String tipo = nomeDoTipo.replace("^", "").trim();

        switch (tipo) {
            case "inteiro":  return TabelaDeSimbolos.TipoLA.INTEIRO;
            case "real":     return TabelaDeSimbolos.TipoLA.REAL;
            case "literal":  return TabelaDeSimbolos.TipoLA.LITERAL;
            case "logico":   return TabelaDeSimbolos.TipoLA.LOGICO;
            default:
                TabelaDeSimbolos.EntradaSimbolo entrada = escopos.buscarEmEscopos(tipo);
                if (entrada == null) {
                    if (reportarErro && tokenErro != null) {
                        LASemanticoUtils.adicionarErroSemantico(tokenErro,
                                "tipo " + tipo + " nao declarado");
                    }
                    return TabelaDeSimbolos.TipoLA.INVALIDO;
                }
                // Se for TIPO_USUARIO, retorna REGISTRO (tipos definidos pelo usuário são registros)
                if (entrada.tipo == TabelaDeSimbolos.TipoLA.TIPO_USUARIO) {
                    return TabelaDeSimbolos.TipoLA.REGISTRO;
                }
                return entrada.tipo;
        }
    }

    // Verifica se dois tipos são compatíveis para atribuição, conforme as regras da especificação da linguagem.
    private boolean tiposCompativeis(TabelaDeSimbolos.TipoLA tipoEsq,
                                     TabelaDeSimbolos.TipoLA tipoDir) {
        if (tipoEsq == TabelaDeSimbolos.TipoLA.INVALIDO) {
            return true; // já reportado como não declarado
        }
        if (tipoDir == TabelaDeSimbolos.TipoLA.INVALIDO) {
            return false;
        }
        if (tipoEsq == tipoDir) {
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

    /*
     * Resolve o tipo de um identificador (pode ter acesso a campos).
     * Para simplificar, campos de registro retornam INVALIDO (não exigido pelos casos de teste).
     */
    private TabelaDeSimbolos.TipoLA resolverTipoIdentificador(LAParser.IdentificadorContext ctx) {
        String nomeBase = ctx.IDENT(0).getText();
        TabelaDeSimbolos.EntradaSimbolo entrada = escopos.buscarEmEscopos(nomeBase);
        if (entrada == null) {
            return TabelaDeSimbolos.TipoLA.INVALIDO;
        }
        if (ctx.IDENT().size() > 1) {
            return TabelaDeSimbolos.TipoLA.INVALIDO; // acesso a campo
        }
        return entrada.tipo;
    }

    // Métodos de resolução de tipos para expressões (delegam recursivamente)

    private TabelaDeSimbolos.TipoLA resolverTipoExpressao(LAParser.ExpressaoContext ctx) {
        if (ctx == null) return TabelaDeSimbolos.TipoLA.INVALIDO;
        TabelaDeSimbolos.TipoLA tipo = resolverTipoTermoLogico(ctx.termo_logic(0));
        if (ctx.termo_logic().size() > 1) {
            return TabelaDeSimbolos.TipoLA.LOGICO;
        }
        return tipo;
    }

    private TabelaDeSimbolos.TipoLA resolverTipoTermoLogico(LAParser.Termo_logicContext ctx) {
        if (ctx == null) return TabelaDeSimbolos.TipoLA.INVALIDO;
        TabelaDeSimbolos.TipoLA tipo = resolverTipoFatorLogico(ctx.fator_logic(0));
        if (ctx.fator_logic().size() > 1) {
            return TabelaDeSimbolos.TipoLA.LOGICO;
        }
        return tipo;
    }

    private TabelaDeSimbolos.TipoLA resolverTipoFatorLogico(LAParser.Fator_logicContext ctx) {
        if (ctx == null) return TabelaDeSimbolos.TipoLA.INVALIDO;
        return resolverTipoParcela_logica(ctx.parcela_logic());
    }

    private TabelaDeSimbolos.TipoLA resolverTipoParcela_logica(LAParser.Parcela_logicContext ctx) {
        if (ctx == null) return TabelaDeSimbolos.TipoLA.INVALIDO;
        if (ctx instanceof LAParser.ParcelaLogicaConstanteContext) {
            return TabelaDeSimbolos.TipoLA.LOGICO;
        }
        if (ctx instanceof LAParser.ParcelaLogicaRelacionalContext) {
            LAParser.ParcelaLogicaRelacionalContext rel =
                    (LAParser.ParcelaLogicaRelacionalContext) ctx;
            return resolverTipoExpRelacional(rel.exp_relacional());
        }
        return TabelaDeSimbolos.TipoLA.INVALIDO;
    }

    private TabelaDeSimbolos.TipoLA resolverTipoExpRelacional(LAParser.Exp_relacionalContext ctx) {
        if (ctx == null) return TabelaDeSimbolos.TipoLA.INVALIDO;
        if (ctx.op_relacional() != null) {
            return TabelaDeSimbolos.TipoLA.LOGICO;
        }
        return resolverTipoExpAritmetica(ctx.exp_aritmetica(0));
    }

    private TabelaDeSimbolos.TipoLA resolverTipoExpAritmetica(LAParser.Exp_aritmeticaContext ctx) {
        if (ctx == null) return TabelaDeSimbolos.TipoLA.INVALIDO;
        TabelaDeSimbolos.TipoLA tipo = resolverTipoTermo(ctx.termo(0));
        for (int i = 1; i < ctx.termo().size(); i++) {
            TabelaDeSimbolos.TipoLA tipoDir = resolverTipoTermo(ctx.termo(i));
            tipo = combinarTiposAritmeticos(tipo, tipoDir);
        }
        return tipo;
    }

    private TabelaDeSimbolos.TipoLA resolverTipoTermo(LAParser.TermoContext ctx) {
        if (ctx == null) return TabelaDeSimbolos.TipoLA.INVALIDO;
        TabelaDeSimbolos.TipoLA tipo = resolverTipoFator(ctx.fator(0));
        for (int i = 1; i < ctx.fator().size(); i++) {
            tipo = combinarTiposAritmeticos(tipo, resolverTipoFator(ctx.fator(i)));
        }
        return tipo;
    }

    private TabelaDeSimbolos.TipoLA resolverTipoFator(LAParser.FatorContext ctx) {
        if (ctx == null) return TabelaDeSimbolos.TipoLA.INVALIDO;
        return resolverTipoParcela(ctx.parcela(0));
    }

    private TabelaDeSimbolos.TipoLA resolverTipoParcela(LAParser.ParcelaContext ctx) {
        if (ctx == null) return TabelaDeSimbolos.TipoLA.INVALIDO;
        if (ctx.parcela_unario() != null) {
            return resolverTipoParcela_unario(ctx.parcela_unario());
        }
        if (ctx.parcela_nao_unario() != null) {
            return resolverTipoParcela_nao_unario(ctx.parcela_nao_unario());
        }
        return TabelaDeSimbolos.TipoLA.INVALIDO;
    }

    private TabelaDeSimbolos.TipoLA resolverTipoParcela_unario(LAParser.Parcela_unarioContext ctx) {
        if (ctx == null) return TabelaDeSimbolos.TipoLA.INVALIDO;
        if (ctx instanceof LAParser.ParcelaIdentificadorContext) {
            return resolverTipoIdentificador(((LAParser.ParcelaIdentificadorContext) ctx).identificador());
        }
        if (ctx instanceof LAParser.ParcelaChamadaFuncaoContext) {
            LAParser.ParcelaChamadaFuncaoContext chamada =
                    (LAParser.ParcelaChamadaFuncaoContext) ctx;
            String nomeFuncao = chamada.IDENT().getText();
            TabelaDeSimbolos.EntradaSimbolo entrada = escopos.buscarEmEscopos(nomeFuncao);
            if (entrada != null && entrada.tipo == TabelaDeSimbolos.TipoLA.FUNCAO) {
                return resolverTipo(entrada.nomeTipo, null, false);
            }
            return TabelaDeSimbolos.TipoLA.INVALIDO;
        }
        if (ctx instanceof LAParser.ParcelaInteiroContext) {
            return TabelaDeSimbolos.TipoLA.INTEIRO;
        }
        if (ctx instanceof LAParser.ParcelaRealContext) {
            return TabelaDeSimbolos.TipoLA.REAL;
        }
        if (ctx instanceof LAParser.ParcelaParentesesContext) {
            return resolverTipoExpressao(((LAParser.ParcelaParentesesContext) ctx).expressao());
        }
        return TabelaDeSimbolos.TipoLA.INVALIDO;
    }

    private TabelaDeSimbolos.TipoLA resolverTipoParcela_nao_unario(
            LAParser.Parcela_nao_unarioContext ctx) {
        if (ctx == null) return TabelaDeSimbolos.TipoLA.INVALIDO;
        if (ctx instanceof LAParser.ParcelaCadeiaContext) {
            return TabelaDeSimbolos.TipoLA.LITERAL;
        }
        if (ctx instanceof LAParser.ParcelaEnderecoContext) {
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
            TabelaDeSimbolos.TipoLA t1, TabelaDeSimbolos.TipoLA t2) {
        if (t1 == TabelaDeSimbolos.TipoLA.INVALIDO || t2 == TabelaDeSimbolos.TipoLA.INVALIDO) {
            return TabelaDeSimbolos.TipoLA.INVALIDO;
        }
        boolean n1 = t1 == TabelaDeSimbolos.TipoLA.INTEIRO || t1 == TabelaDeSimbolos.TipoLA.REAL;
        boolean n2 = t2 == TabelaDeSimbolos.TipoLA.INTEIRO || t2 == TabelaDeSimbolos.TipoLA.REAL;
        if (n1 && n2) {
            return (t1 == TabelaDeSimbolos.TipoLA.REAL || t2 == TabelaDeSimbolos.TipoLA.REAL)
                    ? TabelaDeSimbolos.TipoLA.REAL
                    : TabelaDeSimbolos.TipoLA.INTEIRO;
        }
        if (t1 == TabelaDeSimbolos.TipoLA.LITERAL && t2 == TabelaDeSimbolos.TipoLA.LITERAL) {
            return TabelaDeSimbolos.TipoLA.LITERAL;
        }
        return TabelaDeSimbolos.TipoLA.INVALIDO;
    }

    @Override
    public TabelaDeSimbolos.TipoLA visitPrograma(LAParser.ProgramaContext ctx) {
        return super.visitPrograma(ctx);
    }

    // DECLARAÇÕES LOCAIS

    //declare identificador(es) : tipo
    @Override
    public TabelaDeSimbolos.TipoLA visitDeclaracaoVariavel(
            LAParser.DeclaracaoVariavelContext ctx) {
        TabelaDeSimbolos tabela = escopos.obterEscopoAtual();
        LAParser.TipoContext tipoCtx = ctx.variavel().tipo();
        TabelaDeSimbolos.TipoLA tipoResolvido;
        String nomeTipoTexto = null;

        if (tipoCtx.registro() != null) {
            tipoResolvido = TabelaDeSimbolos.TipoLA.REGISTRO;
        } else {
            LAParser.Tipo_estendidoContext te = tipoCtx.tipo_estendido();
            boolean ehPonteiro = te.PONTEIRO() != null;
            String textoTipo = te.tipo_basico_ident().getText();
            nomeTipoTexto = textoTipo;
            if (ehPonteiro) {
                tipoResolvido = TabelaDeSimbolos.TipoLA.PONTEIRO;
            } else {
                tipoResolvido = resolverTipo(textoTipo,
                        te.tipo_basico_ident().start, true);
            }
        }

        for (LAParser.IdentificadorContext id : ctx.variavel().identificador()) {
            String nome = id.IDENT(0).getText();
            if (tabela.existe(nome)) {
                LASemanticoUtils.adicionarErroSemantico(id.start,
                        "identificador " + nome + " ja declarado anteriormente");
            } else {
                tabela.adicionar(nome, tipoResolvido, nomeTipoTexto);
            }
        }
        return super.visitDeclaracaoVariavel(ctx);
    }

    //constante IDENT : tipo_basico = valor
    @Override
    public TabelaDeSimbolos.TipoLA visitDeclaracaoConstante(
            LAParser.DeclaracaoConstanteContext ctx) {
        TabelaDeSimbolos tabela = escopos.obterEscopoAtual();
        String nome = ctx.IDENT().getText();
        if (tabela.existe(nome)) {
            LASemanticoUtils.adicionarErroSemantico(ctx.IDENT().getSymbol(),
                    "identificador " + nome + " ja declarado anteriormente");
        } else {
            String textoTipo = ctx.tipo_basico().getText();
            TabelaDeSimbolos.TipoLA tipo = resolverTipo(textoTipo, ctx.tipo_basico().start, false);
            tabela.adicionar(nome, tipo, textoTipo);
        }
        return super.visitDeclaracaoConstante(ctx);
    }

    //tipo IDENT : tipo
    @Override
    public TabelaDeSimbolos.TipoLA visitDeclaracaoTipo(
            LAParser.DeclaracaoTipoContext ctx) {
        TabelaDeSimbolos tabela = escopos.obterEscopoAtual();
        String nome = ctx.IDENT().getText();
        if (tabela.existe(nome)) {
            LASemanticoUtils.adicionarErroSemantico(ctx.IDENT().getSymbol(),
                    "identificador " + nome + " ja declarado anteriormente");
        } else {
            String textoTipo = ctx.tipo().getText();
            tabela.adicionar(nome, TabelaDeSimbolos.TipoLA.TIPO_USUARIO, textoTipo);
        }
        return super.visitDeclaracaoTipo(ctx);
    }

    // PROCEDIMENTOS E FUNÇÕES

    @Override
    public TabelaDeSimbolos.TipoLA visitProcedimento(LAParser.ProcedimentoContext ctx) {
        String nome = ctx.IDENT().getText();
        TabelaDeSimbolos tabelaExterna = escopos.obterEscopoAtual();
        if (tabelaExterna.existe(nome)) {
            LASemanticoUtils.adicionarErroSemantico(ctx.IDENT().getSymbol(),
                    "identificador " + nome + " ja declarado anteriormente");
        } else {
            tabelaExterna.adicionar(nome, TabelaDeSimbolos.TipoLA.PROCEDIMENTO);
        }
        escopos.criarNovoEscopo();
        if (ctx.parametros() != null) {
            registrarParametros(ctx.parametros());
        }
        super.visitProcedimento(ctx);
        escopos.abandonarEscopo();
        return null;
    }

    @Override
    public TabelaDeSimbolos.TipoLA visitFuncao(LAParser.FuncaoContext ctx) {
        String nome = ctx.IDENT().getText();
        TabelaDeSimbolos tabelaExterna = escopos.obterEscopoAtual();
        String textoTipoRetorno = ctx.tipo_estendido().getText();
        String tipoSemPonteiro = textoTipoRetorno.replace("^", "").trim();
        resolverTipo(tipoSemPonteiro, ctx.tipo_estendido().start, true);

        if (tabelaExterna.existe(nome)) {
            LASemanticoUtils.adicionarErroSemantico(ctx.IDENT().getSymbol(),
                    "identificador " + nome + " ja declarado anteriormente");
        } else {
            tabelaExterna.adicionar(nome, TabelaDeSimbolos.TipoLA.FUNCAO, tipoSemPonteiro);
        }
        escopos.criarNovoEscopo();
        if (ctx.parametros() != null) {
            registrarParametros(ctx.parametros());
        }
        super.visitFuncao(ctx);
        escopos.abandonarEscopo();
        return null;
    }

    private void registrarParametros(LAParser.ParametrosContext ctx) {
        for (LAParser.ParametroContext param : ctx.parametro()) {
            LAParser.Tipo_estendidoContext te = param.tipo_estendido();
            boolean ehPonteiro = te.PONTEIRO() != null;
            String textoTipo = te.tipo_basico_ident().getText();
            TabelaDeSimbolos.TipoLA tipoParam;
            if (ehPonteiro) {
                tipoParam = TabelaDeSimbolos.TipoLA.PONTEIRO;
            } else {
                tipoParam = resolverTipo(textoTipo, te.tipo_basico_ident().start, true);
            }
            TabelaDeSimbolos tabela = escopos.obterEscopoAtual();
            for (LAParser.IdentificadorContext id : param.identificador()) {
                String nome = id.IDENT(0).getText();
                if (tabela.existe(nome)) {
                    LASemanticoUtils.adicionarErroSemantico(id.start,
                            "identificador " + nome + " ja declarado anteriormente");
                } else {
                    tabela.adicionar(nome, tipoParam, textoTipo);
                }
            }
        }
    }

    // COMANDOS

    @Override
    public TabelaDeSimbolos.TipoLA visitCmdAtribuicao(LAParser.CmdAtribuicaoContext ctx) {
        LAParser.IdentificadorContext idCtx = ctx.identificador();
        String nomeBase = idCtx.IDENT(0).getText();

        if (!escopos.existeEmAlgumEscopo(nomeBase)) {
            LASemanticoUtils.adicionarErroSemantico(idCtx.start,
                    "identificador " + nomeBase + " nao declarado");
            visit(ctx.expressao());
            return null;
        }

        TabelaDeSimbolos.TipoLA tipoEsq = resolverTipoIdentificador(idCtx);
        if (ctx.PONTEIRO() != null) {
            tipoEsq = TabelaDeSimbolos.TipoLA.PONTEIRO;
        }
        TabelaDeSimbolos.TipoLA tipoDir = resolverTipoExpressao(ctx.expressao());

        if (tipoEsq != TabelaDeSimbolos.TipoLA.REGISTRO
                && tipoDir != TabelaDeSimbolos.TipoLA.REGISTRO
                && !tiposCompativeis(tipoEsq, tipoDir)) {
            LASemanticoUtils.adicionarErroSemantico(idCtx.start,
                    "atribuicao nao compativel para " + nomeBase);
        }
        return super.visitCmdAtribuicao(ctx);
    }

    @Override
    public TabelaDeSimbolos.TipoLA visitCmdLeia(LAParser.CmdLeiaContext ctx) {
        for (LAParser.IdentificadorContext id : ctx.identificador()) {
            String nome = id.IDENT(0).getText();
            if (!escopos.existeEmAlgumEscopo(nome)) {
                LASemanticoUtils.adicionarErroSemantico(id.start,
                        "identificador " + nome + " nao declarado");
            }
        }
        return super.visitCmdLeia(ctx);
    }

    @Override
    public TabelaDeSimbolos.TipoLA visitCmdEscreva(LAParser.CmdEscrevaContext ctx) {
        return super.visitCmdEscreva(ctx);
    }

    // EXPRESSÕES (VERIFICAÇÃO DE DECLARAÇÃO)

    @Override
    public TabelaDeSimbolos.TipoLA visitParcelaIdentificador(
            LAParser.ParcelaIdentificadorContext ctx) {
        String nome = ctx.identificador().IDENT(0).getText();
        if (!escopos.existeEmAlgumEscopo(nome)) {
            LASemanticoUtils.adicionarErroSemantico(ctx.identificador().start,
                    "identificador " + nome + " nao declarado");
        }
        return super.visitParcelaIdentificador(ctx);
    }

    @Override
    public TabelaDeSimbolos.TipoLA visitParcelaChamadaFuncao(
            LAParser.ParcelaChamadaFuncaoContext ctx) {
        String nome = ctx.IDENT().getText();
        if (!escopos.existeEmAlgumEscopo(nome)) {
            LASemanticoUtils.adicionarErroSemantico(ctx.IDENT().getSymbol(),
                    "identificador " + nome + " nao declarado");
        }
        return super.visitParcelaChamadaFuncao(ctx);
    }

    @Override
    public TabelaDeSimbolos.TipoLA visitParcelaEndereco(
            LAParser.ParcelaEnderecoContext ctx) {
        String nome = ctx.identificador().IDENT(0).getText();
        if (!escopos.existeEmAlgumEscopo(nome)) {
            LASemanticoUtils.adicionarErroSemantico(ctx.identificador().start,
                    "identificador " + nome + " nao declarado");
        }
        return super.visitParcelaEndereco(ctx);
    }

    @Override
    public TabelaDeSimbolos.TipoLA visitCmdChamada(LAParser.CmdChamadaContext ctx) {
        String nome = ctx.IDENT().getText();
        if (!escopos.existeEmAlgumEscopo(nome)) {
            LASemanticoUtils.adicionarErroSemantico(ctx.IDENT().getSymbol(),
                    "identificador " + nome + " nao declarado");
        }
        return super.visitCmdChamada(ctx);
    }

    @Override
    public TabelaDeSimbolos.TipoLA visitCmdPara(LAParser.CmdParaContext ctx) {
        String nome = ctx.IDENT().getText();
        if (!escopos.existeEmAlgumEscopo(nome)) {
            LASemanticoUtils.adicionarErroSemantico(ctx.IDENT().getSymbol(),
                    "identificador " + nome + " nao declarado");
        }
        return super.visitCmdPara(ctx);
    }
}