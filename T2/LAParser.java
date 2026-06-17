// Generated from LAParser.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class LAParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ALGORITMO=1, FIM_ALGORITMO=2, DECLARE=3, CONSTANTE=4, TIPO=5, REGISTRO=6, 
		FIM_REGISTRO=7, PROCEDIMENTO=8, FIM_PROCEDIMENTO=9, FUNCAO=10, FIM_FUNCAO=11, 
		VAR=12, LEIA=13, ESCREVA=14, SE=15, ENTAO=16, SENAO=17, FIM_SE=18, CASO=19, 
		SEJA=20, FIM_CASO=21, PARA=22, ATE=23, FACA=24, FIM_PARA=25, ENQUANTO=26, 
		FIM_ENQUANTO=27, RETORNE=28, LITERAL=29, INTEIRO=30, REAL=31, LOGICO=32, 
		VERDADEIRO=33, FALSO=34, OP_ATRIBUICAO=35, OP_REL=36, OP_ARIT_1=37, OP_ARIT_2=38, 
		OP_ARIT_3=39, OP_LOGICO_1=40, OP_LOGICO_2=41, NAO=42, PONTO_PONTO=43, 
		PONTEIRO=44, ENDERECO=45, LPAR=46, RPAR=47, LBRACK=48, RBRACK=49, COMMA=50, 
		COLON=51, DOT=52, NUM_INT=53, NUM_REAL=54, IDENT=55, CADEIA=56, WS=57, 
		COMENTARIO=58, ERRO_COMENTARIO=59, ERRO_CADEIA=60, ERRO_SIMBOLO=61;
	public static final int
		RULE_programa = 0, RULE_declaracoes = 1, RULE_decl_local_global = 2, RULE_declaracao_local = 3, 
		RULE_variavel = 4, RULE_identificador = 5, RULE_dimensao = 6, RULE_tipo = 7, 
		RULE_tipo_basico = 8, RULE_tipo_basico_ident = 9, RULE_tipo_estendido = 10, 
		RULE_valor_constante = 11, RULE_registro = 12, RULE_declaracao_global = 13, 
		RULE_parametro = 14, RULE_parametros = 15, RULE_corpo = 16, RULE_cmd = 17, 
		RULE_cmdLeia = 18, RULE_cmdEscreva = 19, RULE_cmdSe = 20, RULE_cmdCaso = 21, 
		RULE_selecao = 22, RULE_item_selecao = 23, RULE_constantes = 24, RULE_numero_intervalo = 25, 
		RULE_op_unario = 26, RULE_cmdPara = 27, RULE_cmdEnquanto = 28, RULE_cmdFaca = 29, 
		RULE_cmdAtribuicao = 30, RULE_cmdChamada = 31, RULE_cmdRetorne = 32, RULE_exp_aritmetica = 33, 
		RULE_termo = 34, RULE_fator = 35, RULE_op1 = 36, RULE_op2 = 37, RULE_op3 = 38, 
		RULE_parcela = 39, RULE_parcela_unario = 40, RULE_parcela_nao_unario = 41, 
		RULE_exp_relacional = 42, RULE_op_relacional = 43, RULE_expressao = 44, 
		RULE_termo_logic = 45, RULE_fator_logic = 46, RULE_parcela_logic = 47, 
		RULE_op_logic_1 = 48, RULE_op_logic_2 = 49;
	private static String[] makeRuleNames() {
		return new String[] {
			"programa", "declaracoes", "decl_local_global", "declaracao_local", "variavel", 
			"identificador", "dimensao", "tipo", "tipo_basico", "tipo_basico_ident", 
			"tipo_estendido", "valor_constante", "registro", "declaracao_global", 
			"parametro", "parametros", "corpo", "cmd", "cmdLeia", "cmdEscreva", "cmdSe", 
			"cmdCaso", "selecao", "item_selecao", "constantes", "numero_intervalo", 
			"op_unario", "cmdPara", "cmdEnquanto", "cmdFaca", "cmdAtribuicao", "cmdChamada", 
			"cmdRetorne", "exp_aritmetica", "termo", "fator", "op1", "op2", "op3", 
			"parcela", "parcela_unario", "parcela_nao_unario", "exp_relacional", 
			"op_relacional", "expressao", "termo_logic", "fator_logic", "parcela_logic", 
			"op_logic_1", "op_logic_2"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'algoritmo'", "'fim_algoritmo'", "'declare'", "'constante'", "'tipo'", 
			"'registro'", "'fim_registro'", "'procedimento'", "'fim_procedimento'", 
			"'funcao'", "'fim_funcao'", "'var'", "'leia'", "'escreva'", "'se'", "'entao'", 
			"'senao'", "'fim_se'", "'caso'", "'seja'", "'fim_caso'", "'para'", "'ate'", 
			"'faca'", "'fim_para'", "'enquanto'", "'fim_enquanto'", "'retorne'", 
			"'literal'", "'inteiro'", "'real'", "'logico'", "'verdadeiro'", "'falso'", 
			"'<-'", null, null, null, "'%'", "'ou'", "'e'", "'nao'", "'..'", "'^'", 
			"'&'", "'('", "')'", "'['", "']'", "','", "':'", "'.'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "ALGORITMO", "FIM_ALGORITMO", "DECLARE", "CONSTANTE", "TIPO", "REGISTRO", 
			"FIM_REGISTRO", "PROCEDIMENTO", "FIM_PROCEDIMENTO", "FUNCAO", "FIM_FUNCAO", 
			"VAR", "LEIA", "ESCREVA", "SE", "ENTAO", "SENAO", "FIM_SE", "CASO", "SEJA", 
			"FIM_CASO", "PARA", "ATE", "FACA", "FIM_PARA", "ENQUANTO", "FIM_ENQUANTO", 
			"RETORNE", "LITERAL", "INTEIRO", "REAL", "LOGICO", "VERDADEIRO", "FALSO", 
			"OP_ATRIBUICAO", "OP_REL", "OP_ARIT_1", "OP_ARIT_2", "OP_ARIT_3", "OP_LOGICO_1", 
			"OP_LOGICO_2", "NAO", "PONTO_PONTO", "PONTEIRO", "ENDERECO", "LPAR", 
			"RPAR", "LBRACK", "RBRACK", "COMMA", "COLON", "DOT", "NUM_INT", "NUM_REAL", 
			"IDENT", "CADEIA", "WS", "COMENTARIO", "ERRO_COMENTARIO", "ERRO_CADEIA", 
			"ERRO_SIMBOLO"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "LAParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public LAParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramaContext extends ParserRuleContext {
		public DeclaracoesContext declaracoes() {
			return getRuleContext(DeclaracoesContext.class,0);
		}
		public TerminalNode ALGORITMO() { return getToken(LAParser.ALGORITMO, 0); }
		public CorpoContext corpo() {
			return getRuleContext(CorpoContext.class,0);
		}
		public TerminalNode FIM_ALGORITMO() { return getToken(LAParser.FIM_ALGORITMO, 0); }
		public ProgramaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_programa; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterPrograma(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitPrograma(this);
		}
	}

	public final ProgramaContext programa() throws RecognitionException {
		ProgramaContext _localctx = new ProgramaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_programa);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			declaracoes();
			setState(101);
			match(ALGORITMO);
			setState(102);
			corpo();
			setState(103);
			match(FIM_ALGORITMO);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclaracoesContext extends ParserRuleContext {
		public List<Decl_local_globalContext> decl_local_global() {
			return getRuleContexts(Decl_local_globalContext.class);
		}
		public Decl_local_globalContext decl_local_global(int i) {
			return getRuleContext(Decl_local_globalContext.class,i);
		}
		public DeclaracoesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaracoes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterDeclaracoes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitDeclaracoes(this);
		}
	}

	public final DeclaracoesContext declaracoes() throws RecognitionException {
		DeclaracoesContext _localctx = new DeclaracoesContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_declaracoes);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1336L) != 0)) {
				{
				{
				setState(105);
				decl_local_global();
				}
				}
				setState(110);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Decl_local_globalContext extends ParserRuleContext {
		public Declaracao_localContext declaracao_local() {
			return getRuleContext(Declaracao_localContext.class,0);
		}
		public Declaracao_globalContext declaracao_global() {
			return getRuleContext(Declaracao_globalContext.class,0);
		}
		public Decl_local_globalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl_local_global; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterDecl_local_global(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitDecl_local_global(this);
		}
	}

	public final Decl_local_globalContext decl_local_global() throws RecognitionException {
		Decl_local_globalContext _localctx = new Decl_local_globalContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_decl_local_global);
		try {
			setState(113);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DECLARE:
			case CONSTANTE:
			case TIPO:
				enterOuterAlt(_localctx, 1);
				{
				setState(111);
				declaracao_local();
				}
				break;
			case PROCEDIMENTO:
			case FUNCAO:
				enterOuterAlt(_localctx, 2);
				{
				setState(112);
				declaracao_global();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Declaracao_localContext extends ParserRuleContext {
		public Declaracao_localContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaracao_local; }
	 
		public Declaracao_localContext() { }
		public void copyFrom(Declaracao_localContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DeclaracaoVariavelContext extends Declaracao_localContext {
		public TerminalNode DECLARE() { return getToken(LAParser.DECLARE, 0); }
		public VariavelContext variavel() {
			return getRuleContext(VariavelContext.class,0);
		}
		public DeclaracaoVariavelContext(Declaracao_localContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterDeclaracaoVariavel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitDeclaracaoVariavel(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DeclaracaoConstanteContext extends Declaracao_localContext {
		public TerminalNode CONSTANTE() { return getToken(LAParser.CONSTANTE, 0); }
		public TerminalNode IDENT() { return getToken(LAParser.IDENT, 0); }
		public TerminalNode COLON() { return getToken(LAParser.COLON, 0); }
		public Tipo_basicoContext tipo_basico() {
			return getRuleContext(Tipo_basicoContext.class,0);
		}
		public TerminalNode OP_REL() { return getToken(LAParser.OP_REL, 0); }
		public Valor_constanteContext valor_constante() {
			return getRuleContext(Valor_constanteContext.class,0);
		}
		public DeclaracaoConstanteContext(Declaracao_localContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterDeclaracaoConstante(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitDeclaracaoConstante(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DeclaracaoTipoContext extends Declaracao_localContext {
		public TerminalNode TIPO() { return getToken(LAParser.TIPO, 0); }
		public TerminalNode IDENT() { return getToken(LAParser.IDENT, 0); }
		public TerminalNode COLON() { return getToken(LAParser.COLON, 0); }
		public TipoContext tipo() {
			return getRuleContext(TipoContext.class,0);
		}
		public DeclaracaoTipoContext(Declaracao_localContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterDeclaracaoTipo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitDeclaracaoTipo(this);
		}
	}

	public final Declaracao_localContext declaracao_local() throws RecognitionException {
		Declaracao_localContext _localctx = new Declaracao_localContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_declaracao_local);
		try {
			setState(128);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DECLARE:
				_localctx = new DeclaracaoVariavelContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(115);
				match(DECLARE);
				setState(116);
				variavel();
				}
				break;
			case CONSTANTE:
				_localctx = new DeclaracaoConstanteContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(117);
				match(CONSTANTE);
				setState(118);
				match(IDENT);
				setState(119);
				match(COLON);
				setState(120);
				tipo_basico();
				setState(121);
				match(OP_REL);
				setState(122);
				valor_constante();
				}
				break;
			case TIPO:
				_localctx = new DeclaracaoTipoContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(124);
				match(TIPO);
				setState(125);
				match(IDENT);
				setState(126);
				match(COLON);
				setState(127);
				tipo();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VariavelContext extends ParserRuleContext {
		public List<IdentificadorContext> identificador() {
			return getRuleContexts(IdentificadorContext.class);
		}
		public IdentificadorContext identificador(int i) {
			return getRuleContext(IdentificadorContext.class,i);
		}
		public TerminalNode COLON() { return getToken(LAParser.COLON, 0); }
		public TipoContext tipo() {
			return getRuleContext(TipoContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(LAParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LAParser.COMMA, i);
		}
		public VariavelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variavel; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterVariavel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitVariavel(this);
		}
	}

	public final VariavelContext variavel() throws RecognitionException {
		VariavelContext _localctx = new VariavelContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_variavel);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			identificador();
			setState(135);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(131);
				match(COMMA);
				setState(132);
				identificador();
				}
				}
				setState(137);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(138);
			match(COLON);
			setState(139);
			tipo();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IdentificadorContext extends ParserRuleContext {
		public List<TerminalNode> IDENT() { return getTokens(LAParser.IDENT); }
		public TerminalNode IDENT(int i) {
			return getToken(LAParser.IDENT, i);
		}
		public DimensaoContext dimensao() {
			return getRuleContext(DimensaoContext.class,0);
		}
		public List<TerminalNode> DOT() { return getTokens(LAParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(LAParser.DOT, i);
		}
		public IdentificadorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identificador; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterIdentificador(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitIdentificador(this);
		}
	}

	public final IdentificadorContext identificador() throws RecognitionException {
		IdentificadorContext _localctx = new IdentificadorContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_identificador);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(141);
			match(IDENT);
			setState(146);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(142);
				match(DOT);
				setState(143);
				match(IDENT);
				}
				}
				setState(148);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(149);
			dimensao();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DimensaoContext extends ParserRuleContext {
		public List<TerminalNode> LBRACK() { return getTokens(LAParser.LBRACK); }
		public TerminalNode LBRACK(int i) {
			return getToken(LAParser.LBRACK, i);
		}
		public List<Exp_aritmeticaContext> exp_aritmetica() {
			return getRuleContexts(Exp_aritmeticaContext.class);
		}
		public Exp_aritmeticaContext exp_aritmetica(int i) {
			return getRuleContext(Exp_aritmeticaContext.class,i);
		}
		public List<TerminalNode> RBRACK() { return getTokens(LAParser.RBRACK); }
		public TerminalNode RBRACK(int i) {
			return getToken(LAParser.RBRACK, i);
		}
		public DimensaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dimensao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterDimensao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitDimensao(this);
		}
	}

	public final DimensaoContext dimensao() throws RecognitionException {
		DimensaoContext _localctx = new DimensaoContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_dimensao);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(157);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LBRACK) {
				{
				{
				setState(151);
				match(LBRACK);
				setState(152);
				exp_aritmetica();
				setState(153);
				match(RBRACK);
				}
				}
				setState(159);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TipoContext extends ParserRuleContext {
		public RegistroContext registro() {
			return getRuleContext(RegistroContext.class,0);
		}
		public Tipo_estendidoContext tipo_estendido() {
			return getRuleContext(Tipo_estendidoContext.class,0);
		}
		public TipoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterTipo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitTipo(this);
		}
	}

	public final TipoContext tipo() throws RecognitionException {
		TipoContext _localctx = new TipoContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_tipo);
		try {
			setState(162);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case REGISTRO:
				enterOuterAlt(_localctx, 1);
				{
				setState(160);
				registro();
				}
				break;
			case LITERAL:
			case INTEIRO:
			case REAL:
			case LOGICO:
			case PONTEIRO:
			case IDENT:
				enterOuterAlt(_localctx, 2);
				{
				setState(161);
				tipo_estendido();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Tipo_basicoContext extends ParserRuleContext {
		public TerminalNode LITERAL() { return getToken(LAParser.LITERAL, 0); }
		public TerminalNode INTEIRO() { return getToken(LAParser.INTEIRO, 0); }
		public TerminalNode REAL() { return getToken(LAParser.REAL, 0); }
		public TerminalNode LOGICO() { return getToken(LAParser.LOGICO, 0); }
		public Tipo_basicoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipo_basico; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterTipo_basico(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitTipo_basico(this);
		}
	}

	public final Tipo_basicoContext tipo_basico() throws RecognitionException {
		Tipo_basicoContext _localctx = new Tipo_basicoContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_tipo_basico);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 8053063680L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Tipo_basico_identContext extends ParserRuleContext {
		public Tipo_basicoContext tipo_basico() {
			return getRuleContext(Tipo_basicoContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(LAParser.IDENT, 0); }
		public Tipo_basico_identContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipo_basico_ident; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterTipo_basico_ident(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitTipo_basico_ident(this);
		}
	}

	public final Tipo_basico_identContext tipo_basico_ident() throws RecognitionException {
		Tipo_basico_identContext _localctx = new Tipo_basico_identContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_tipo_basico_ident);
		try {
			setState(168);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LITERAL:
			case INTEIRO:
			case REAL:
			case LOGICO:
				enterOuterAlt(_localctx, 1);
				{
				setState(166);
				tipo_basico();
				}
				break;
			case IDENT:
				enterOuterAlt(_localctx, 2);
				{
				setState(167);
				match(IDENT);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Tipo_estendidoContext extends ParserRuleContext {
		public Tipo_basico_identContext tipo_basico_ident() {
			return getRuleContext(Tipo_basico_identContext.class,0);
		}
		public TerminalNode PONTEIRO() { return getToken(LAParser.PONTEIRO, 0); }
		public Tipo_estendidoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipo_estendido; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterTipo_estendido(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitTipo_estendido(this);
		}
	}

	public final Tipo_estendidoContext tipo_estendido() throws RecognitionException {
		Tipo_estendidoContext _localctx = new Tipo_estendidoContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_tipo_estendido);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(171);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PONTEIRO) {
				{
				setState(170);
				match(PONTEIRO);
				}
			}

			setState(173);
			tipo_basico_ident();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Valor_constanteContext extends ParserRuleContext {
		public TerminalNode CADEIA() { return getToken(LAParser.CADEIA, 0); }
		public TerminalNode NUM_INT() { return getToken(LAParser.NUM_INT, 0); }
		public TerminalNode NUM_REAL() { return getToken(LAParser.NUM_REAL, 0); }
		public TerminalNode VERDADEIRO() { return getToken(LAParser.VERDADEIRO, 0); }
		public TerminalNode FALSO() { return getToken(LAParser.FALSO, 0); }
		public Valor_constanteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_valor_constante; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterValor_constante(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitValor_constante(this);
		}
	}

	public final Valor_constanteContext valor_constante() throws RecognitionException {
		Valor_constanteContext _localctx = new Valor_constanteContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_valor_constante);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 99079217571954688L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RegistroContext extends ParserRuleContext {
		public TerminalNode REGISTRO() { return getToken(LAParser.REGISTRO, 0); }
		public TerminalNode FIM_REGISTRO() { return getToken(LAParser.FIM_REGISTRO, 0); }
		public List<VariavelContext> variavel() {
			return getRuleContexts(VariavelContext.class);
		}
		public VariavelContext variavel(int i) {
			return getRuleContext(VariavelContext.class,i);
		}
		public RegistroContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_registro; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterRegistro(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitRegistro(this);
		}
	}

	public final RegistroContext registro() throws RecognitionException {
		RegistroContext _localctx = new RegistroContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_registro);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(177);
			match(REGISTRO);
			setState(181);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IDENT) {
				{
				{
				setState(178);
				variavel();
				}
				}
				setState(183);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(184);
			match(FIM_REGISTRO);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Declaracao_globalContext extends ParserRuleContext {
		public Declaracao_globalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaracao_global; }
	 
		public Declaracao_globalContext() { }
		public void copyFrom(Declaracao_globalContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FuncaoContext extends Declaracao_globalContext {
		public TerminalNode FUNCAO() { return getToken(LAParser.FUNCAO, 0); }
		public TerminalNode IDENT() { return getToken(LAParser.IDENT, 0); }
		public TerminalNode LPAR() { return getToken(LAParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(LAParser.RPAR, 0); }
		public TerminalNode COLON() { return getToken(LAParser.COLON, 0); }
		public Tipo_estendidoContext tipo_estendido() {
			return getRuleContext(Tipo_estendidoContext.class,0);
		}
		public TerminalNode FIM_FUNCAO() { return getToken(LAParser.FIM_FUNCAO, 0); }
		public ParametrosContext parametros() {
			return getRuleContext(ParametrosContext.class,0);
		}
		public List<Declaracao_localContext> declaracao_local() {
			return getRuleContexts(Declaracao_localContext.class);
		}
		public Declaracao_localContext declaracao_local(int i) {
			return getRuleContext(Declaracao_localContext.class,i);
		}
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public FuncaoContext(Declaracao_globalContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterFuncao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitFuncao(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ProcedimentoContext extends Declaracao_globalContext {
		public TerminalNode PROCEDIMENTO() { return getToken(LAParser.PROCEDIMENTO, 0); }
		public TerminalNode IDENT() { return getToken(LAParser.IDENT, 0); }
		public TerminalNode LPAR() { return getToken(LAParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(LAParser.RPAR, 0); }
		public TerminalNode FIM_PROCEDIMENTO() { return getToken(LAParser.FIM_PROCEDIMENTO, 0); }
		public ParametrosContext parametros() {
			return getRuleContext(ParametrosContext.class,0);
		}
		public List<Declaracao_localContext> declaracao_local() {
			return getRuleContexts(Declaracao_localContext.class);
		}
		public Declaracao_localContext declaracao_local(int i) {
			return getRuleContext(Declaracao_localContext.class,i);
		}
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public ProcedimentoContext(Declaracao_globalContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterProcedimento(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitProcedimento(this);
		}
	}

	public final Declaracao_globalContext declaracao_global() throws RecognitionException {
		Declaracao_globalContext _localctx = new Declaracao_globalContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_declaracao_global);
		int _la;
		try {
			setState(229);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PROCEDIMENTO:
				_localctx = new ProcedimentoContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(186);
				match(PROCEDIMENTO);
				setState(187);
				match(IDENT);
				setState(188);
				match(LPAR);
				setState(190);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VAR || _la==IDENT) {
					{
					setState(189);
					parametros();
					}
				}

				setState(192);
				match(RPAR);
				setState(196);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 56L) != 0)) {
					{
					{
					setState(193);
					declaracao_local();
					}
					}
					setState(198);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(202);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 36046389562105856L) != 0)) {
					{
					{
					setState(199);
					cmd();
					}
					}
					setState(204);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(205);
				match(FIM_PROCEDIMENTO);
				}
				break;
			case FUNCAO:
				_localctx = new FuncaoContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(206);
				match(FUNCAO);
				setState(207);
				match(IDENT);
				setState(208);
				match(LPAR);
				setState(210);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VAR || _la==IDENT) {
					{
					setState(209);
					parametros();
					}
				}

				setState(212);
				match(RPAR);
				setState(213);
				match(COLON);
				setState(214);
				tipo_estendido();
				setState(218);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 56L) != 0)) {
					{
					{
					setState(215);
					declaracao_local();
					}
					}
					setState(220);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(224);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 36046389562105856L) != 0)) {
					{
					{
					setState(221);
					cmd();
					}
					}
					setState(226);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(227);
				match(FIM_FUNCAO);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParametroContext extends ParserRuleContext {
		public List<IdentificadorContext> identificador() {
			return getRuleContexts(IdentificadorContext.class);
		}
		public IdentificadorContext identificador(int i) {
			return getRuleContext(IdentificadorContext.class,i);
		}
		public TerminalNode COLON() { return getToken(LAParser.COLON, 0); }
		public Tipo_estendidoContext tipo_estendido() {
			return getRuleContext(Tipo_estendidoContext.class,0);
		}
		public TerminalNode VAR() { return getToken(LAParser.VAR, 0); }
		public List<TerminalNode> COMMA() { return getTokens(LAParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LAParser.COMMA, i);
		}
		public ParametroContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parametro; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterParametro(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitParametro(this);
		}
	}

	public final ParametroContext parametro() throws RecognitionException {
		ParametroContext _localctx = new ParametroContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_parametro);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(232);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==VAR) {
				{
				setState(231);
				match(VAR);
				}
			}

			setState(234);
			identificador();
			setState(239);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(235);
				match(COMMA);
				setState(236);
				identificador();
				}
				}
				setState(241);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(242);
			match(COLON);
			setState(243);
			tipo_estendido();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParametrosContext extends ParserRuleContext {
		public List<ParametroContext> parametro() {
			return getRuleContexts(ParametroContext.class);
		}
		public ParametroContext parametro(int i) {
			return getRuleContext(ParametroContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(LAParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LAParser.COMMA, i);
		}
		public ParametrosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parametros; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterParametros(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitParametros(this);
		}
	}

	public final ParametrosContext parametros() throws RecognitionException {
		ParametrosContext _localctx = new ParametrosContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_parametros);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(245);
			parametro();
			setState(250);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(246);
				match(COMMA);
				setState(247);
				parametro();
				}
				}
				setState(252);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CorpoContext extends ParserRuleContext {
		public List<Declaracao_localContext> declaracao_local() {
			return getRuleContexts(Declaracao_localContext.class);
		}
		public Declaracao_localContext declaracao_local(int i) {
			return getRuleContext(Declaracao_localContext.class,i);
		}
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CorpoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_corpo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterCorpo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitCorpo(this);
		}
	}

	public final CorpoContext corpo() throws RecognitionException {
		CorpoContext _localctx = new CorpoContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_corpo);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(256);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 56L) != 0)) {
				{
				{
				setState(253);
				declaracao_local();
				}
				}
				setState(258);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(262);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 36046389562105856L) != 0)) {
				{
				{
				setState(259);
				cmd();
				}
				}
				setState(264);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdContext extends ParserRuleContext {
		public CmdLeiaContext cmdLeia() {
			return getRuleContext(CmdLeiaContext.class,0);
		}
		public CmdEscrevaContext cmdEscreva() {
			return getRuleContext(CmdEscrevaContext.class,0);
		}
		public CmdSeContext cmdSe() {
			return getRuleContext(CmdSeContext.class,0);
		}
		public CmdCasoContext cmdCaso() {
			return getRuleContext(CmdCasoContext.class,0);
		}
		public CmdParaContext cmdPara() {
			return getRuleContext(CmdParaContext.class,0);
		}
		public CmdEnquantoContext cmdEnquanto() {
			return getRuleContext(CmdEnquantoContext.class,0);
		}
		public CmdFacaContext cmdFaca() {
			return getRuleContext(CmdFacaContext.class,0);
		}
		public CmdAtribuicaoContext cmdAtribuicao() {
			return getRuleContext(CmdAtribuicaoContext.class,0);
		}
		public CmdChamadaContext cmdChamada() {
			return getRuleContext(CmdChamadaContext.class,0);
		}
		public CmdRetorneContext cmdRetorne() {
			return getRuleContext(CmdRetorneContext.class,0);
		}
		public CmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterCmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitCmd(this);
		}
	}

	public final CmdContext cmd() throws RecognitionException {
		CmdContext _localctx = new CmdContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_cmd);
		try {
			setState(275);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(265);
				cmdLeia();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(266);
				cmdEscreva();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(267);
				cmdSe();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(268);
				cmdCaso();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(269);
				cmdPara();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(270);
				cmdEnquanto();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(271);
				cmdFaca();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(272);
				cmdAtribuicao();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(273);
				cmdChamada();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(274);
				cmdRetorne();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdLeiaContext extends ParserRuleContext {
		public TerminalNode LEIA() { return getToken(LAParser.LEIA, 0); }
		public TerminalNode LPAR() { return getToken(LAParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(LAParser.RPAR, 0); }
		public List<IdentificadorContext> identificador() {
			return getRuleContexts(IdentificadorContext.class);
		}
		public IdentificadorContext identificador(int i) {
			return getRuleContext(IdentificadorContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(LAParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LAParser.COMMA, i);
		}
		public List<TerminalNode> PONTEIRO() { return getTokens(LAParser.PONTEIRO); }
		public TerminalNode PONTEIRO(int i) {
			return getToken(LAParser.PONTEIRO, i);
		}
		public CmdLeiaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdLeia; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterCmdLeia(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitCmdLeia(this);
		}
	}

	public final CmdLeiaContext cmdLeia() throws RecognitionException {
		CmdLeiaContext _localctx = new CmdLeiaContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_cmdLeia);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(277);
			match(LEIA);
			setState(278);
			match(LPAR);
			{
			setState(280);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PONTEIRO) {
				{
				setState(279);
				match(PONTEIRO);
				}
			}

			setState(282);
			identificador();
			}
			setState(291);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(284);
				match(COMMA);
				setState(286);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==PONTEIRO) {
					{
					setState(285);
					match(PONTEIRO);
					}
				}

				setState(288);
				identificador();
				}
				}
				setState(293);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(294);
			match(RPAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdEscrevaContext extends ParserRuleContext {
		public TerminalNode ESCREVA() { return getToken(LAParser.ESCREVA, 0); }
		public TerminalNode LPAR() { return getToken(LAParser.LPAR, 0); }
		public List<ExpressaoContext> expressao() {
			return getRuleContexts(ExpressaoContext.class);
		}
		public ExpressaoContext expressao(int i) {
			return getRuleContext(ExpressaoContext.class,i);
		}
		public TerminalNode RPAR() { return getToken(LAParser.RPAR, 0); }
		public List<TerminalNode> COMMA() { return getTokens(LAParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LAParser.COMMA, i);
		}
		public CmdEscrevaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdEscreva; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterCmdEscreva(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitCmdEscreva(this);
		}
	}

	public final CmdEscrevaContext cmdEscreva() throws RecognitionException {
		CmdEscrevaContext _localctx = new CmdEscrevaContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_cmdEscreva);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(296);
			match(ESCREVA);
			setState(297);
			match(LPAR);
			setState(298);
			expressao();
			setState(303);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(299);
				match(COMMA);
				setState(300);
				expressao();
				}
				}
				setState(305);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(306);
			match(RPAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdSeContext extends ParserRuleContext {
		public TerminalNode SE() { return getToken(LAParser.SE, 0); }
		public ExpressaoContext expressao() {
			return getRuleContext(ExpressaoContext.class,0);
		}
		public TerminalNode ENTAO() { return getToken(LAParser.ENTAO, 0); }
		public TerminalNode FIM_SE() { return getToken(LAParser.FIM_SE, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public TerminalNode SENAO() { return getToken(LAParser.SENAO, 0); }
		public CmdSeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdSe; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterCmdSe(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitCmdSe(this);
		}
	}

	public final CmdSeContext cmdSe() throws RecognitionException {
		CmdSeContext _localctx = new CmdSeContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_cmdSe);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(308);
			match(SE);
			setState(309);
			expressao();
			setState(310);
			match(ENTAO);
			setState(314);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 36046389562105856L) != 0)) {
				{
				{
				setState(311);
				cmd();
				}
				}
				setState(316);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(324);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SENAO) {
				{
				setState(317);
				match(SENAO);
				setState(321);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 36046389562105856L) != 0)) {
					{
					{
					setState(318);
					cmd();
					}
					}
					setState(323);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(326);
			match(FIM_SE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdCasoContext extends ParserRuleContext {
		public TerminalNode CASO() { return getToken(LAParser.CASO, 0); }
		public Exp_aritmeticaContext exp_aritmetica() {
			return getRuleContext(Exp_aritmeticaContext.class,0);
		}
		public TerminalNode SEJA() { return getToken(LAParser.SEJA, 0); }
		public SelecaoContext selecao() {
			return getRuleContext(SelecaoContext.class,0);
		}
		public TerminalNode FIM_CASO() { return getToken(LAParser.FIM_CASO, 0); }
		public TerminalNode SENAO() { return getToken(LAParser.SENAO, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdCasoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdCaso; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterCmdCaso(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitCmdCaso(this);
		}
	}

	public final CmdCasoContext cmdCaso() throws RecognitionException {
		CmdCasoContext _localctx = new CmdCasoContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_cmdCaso);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(328);
			match(CASO);
			setState(329);
			exp_aritmetica();
			setState(330);
			match(SEJA);
			setState(331);
			selecao();
			setState(339);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SENAO) {
				{
				setState(332);
				match(SENAO);
				setState(336);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 36046389562105856L) != 0)) {
					{
					{
					setState(333);
					cmd();
					}
					}
					setState(338);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(341);
			match(FIM_CASO);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SelecaoContext extends ParserRuleContext {
		public List<Item_selecaoContext> item_selecao() {
			return getRuleContexts(Item_selecaoContext.class);
		}
		public Item_selecaoContext item_selecao(int i) {
			return getRuleContext(Item_selecaoContext.class,i);
		}
		public SelecaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selecao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterSelecao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitSelecao(this);
		}
	}

	public final SelecaoContext selecao() throws RecognitionException {
		SelecaoContext _localctx = new SelecaoContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_selecao);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(346);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP_ARIT_1 || _la==NUM_INT) {
				{
				{
				setState(343);
				item_selecao();
				}
				}
				setState(348);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Item_selecaoContext extends ParserRuleContext {
		public ConstantesContext constantes() {
			return getRuleContext(ConstantesContext.class,0);
		}
		public TerminalNode COLON() { return getToken(LAParser.COLON, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public Item_selecaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_item_selecao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterItem_selecao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitItem_selecao(this);
		}
	}

	public final Item_selecaoContext item_selecao() throws RecognitionException {
		Item_selecaoContext _localctx = new Item_selecaoContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_item_selecao);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(349);
			constantes();
			setState(350);
			match(COLON);
			setState(354);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 36046389562105856L) != 0)) {
				{
				{
				setState(351);
				cmd();
				}
				}
				setState(356);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ConstantesContext extends ParserRuleContext {
		public List<Numero_intervaloContext> numero_intervalo() {
			return getRuleContexts(Numero_intervaloContext.class);
		}
		public Numero_intervaloContext numero_intervalo(int i) {
			return getRuleContext(Numero_intervaloContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(LAParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LAParser.COMMA, i);
		}
		public ConstantesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constantes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterConstantes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitConstantes(this);
		}
	}

	public final ConstantesContext constantes() throws RecognitionException {
		ConstantesContext _localctx = new ConstantesContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_constantes);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(357);
			numero_intervalo();
			setState(362);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(358);
				match(COMMA);
				setState(359);
				numero_intervalo();
				}
				}
				setState(364);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Numero_intervaloContext extends ParserRuleContext {
		public List<TerminalNode> NUM_INT() { return getTokens(LAParser.NUM_INT); }
		public TerminalNode NUM_INT(int i) {
			return getToken(LAParser.NUM_INT, i);
		}
		public List<Op_unarioContext> op_unario() {
			return getRuleContexts(Op_unarioContext.class);
		}
		public Op_unarioContext op_unario(int i) {
			return getRuleContext(Op_unarioContext.class,i);
		}
		public TerminalNode PONTO_PONTO() { return getToken(LAParser.PONTO_PONTO, 0); }
		public Numero_intervaloContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numero_intervalo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterNumero_intervalo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitNumero_intervalo(this);
		}
	}

	public final Numero_intervaloContext numero_intervalo() throws RecognitionException {
		Numero_intervaloContext _localctx = new Numero_intervaloContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_numero_intervalo);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(366);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OP_ARIT_1) {
				{
				setState(365);
				op_unario();
				}
			}

			setState(368);
			match(NUM_INT);
			setState(374);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PONTO_PONTO) {
				{
				setState(369);
				match(PONTO_PONTO);
				setState(371);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OP_ARIT_1) {
					{
					setState(370);
					op_unario();
					}
				}

				setState(373);
				match(NUM_INT);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Op_unarioContext extends ParserRuleContext {
		public TerminalNode OP_ARIT_1() { return getToken(LAParser.OP_ARIT_1, 0); }
		public Op_unarioContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_op_unario; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterOp_unario(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitOp_unario(this);
		}
	}

	public final Op_unarioContext op_unario() throws RecognitionException {
		Op_unarioContext _localctx = new Op_unarioContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_op_unario);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(376);
			match(OP_ARIT_1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdParaContext extends ParserRuleContext {
		public TerminalNode PARA() { return getToken(LAParser.PARA, 0); }
		public TerminalNode IDENT() { return getToken(LAParser.IDENT, 0); }
		public TerminalNode OP_ATRIBUICAO() { return getToken(LAParser.OP_ATRIBUICAO, 0); }
		public List<Exp_aritmeticaContext> exp_aritmetica() {
			return getRuleContexts(Exp_aritmeticaContext.class);
		}
		public Exp_aritmeticaContext exp_aritmetica(int i) {
			return getRuleContext(Exp_aritmeticaContext.class,i);
		}
		public TerminalNode ATE() { return getToken(LAParser.ATE, 0); }
		public TerminalNode FACA() { return getToken(LAParser.FACA, 0); }
		public TerminalNode FIM_PARA() { return getToken(LAParser.FIM_PARA, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdParaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdPara; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterCmdPara(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitCmdPara(this);
		}
	}

	public final CmdParaContext cmdPara() throws RecognitionException {
		CmdParaContext _localctx = new CmdParaContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_cmdPara);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(378);
			match(PARA);
			setState(379);
			match(IDENT);
			setState(380);
			match(OP_ATRIBUICAO);
			setState(381);
			exp_aritmetica();
			setState(382);
			match(ATE);
			setState(383);
			exp_aritmetica();
			setState(384);
			match(FACA);
			setState(388);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 36046389562105856L) != 0)) {
				{
				{
				setState(385);
				cmd();
				}
				}
				setState(390);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(391);
			match(FIM_PARA);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdEnquantoContext extends ParserRuleContext {
		public TerminalNode ENQUANTO() { return getToken(LAParser.ENQUANTO, 0); }
		public ExpressaoContext expressao() {
			return getRuleContext(ExpressaoContext.class,0);
		}
		public TerminalNode FACA() { return getToken(LAParser.FACA, 0); }
		public TerminalNode FIM_ENQUANTO() { return getToken(LAParser.FIM_ENQUANTO, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdEnquantoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdEnquanto; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterCmdEnquanto(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitCmdEnquanto(this);
		}
	}

	public final CmdEnquantoContext cmdEnquanto() throws RecognitionException {
		CmdEnquantoContext _localctx = new CmdEnquantoContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_cmdEnquanto);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(393);
			match(ENQUANTO);
			setState(394);
			expressao();
			setState(395);
			match(FACA);
			setState(399);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 36046389562105856L) != 0)) {
				{
				{
				setState(396);
				cmd();
				}
				}
				setState(401);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(402);
			match(FIM_ENQUANTO);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdFacaContext extends ParserRuleContext {
		public TerminalNode FACA() { return getToken(LAParser.FACA, 0); }
		public TerminalNode ATE() { return getToken(LAParser.ATE, 0); }
		public ExpressaoContext expressao() {
			return getRuleContext(ExpressaoContext.class,0);
		}
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdFacaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdFaca; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterCmdFaca(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitCmdFaca(this);
		}
	}

	public final CmdFacaContext cmdFaca() throws RecognitionException {
		CmdFacaContext _localctx = new CmdFacaContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_cmdFaca);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(404);
			match(FACA);
			setState(408);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 36046389562105856L) != 0)) {
				{
				{
				setState(405);
				cmd();
				}
				}
				setState(410);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(411);
			match(ATE);
			setState(412);
			expressao();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdAtribuicaoContext extends ParserRuleContext {
		public IdentificadorContext identificador() {
			return getRuleContext(IdentificadorContext.class,0);
		}
		public TerminalNode OP_ATRIBUICAO() { return getToken(LAParser.OP_ATRIBUICAO, 0); }
		public ExpressaoContext expressao() {
			return getRuleContext(ExpressaoContext.class,0);
		}
		public TerminalNode PONTEIRO() { return getToken(LAParser.PONTEIRO, 0); }
		public CmdAtribuicaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdAtribuicao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterCmdAtribuicao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitCmdAtribuicao(this);
		}
	}

	public final CmdAtribuicaoContext cmdAtribuicao() throws RecognitionException {
		CmdAtribuicaoContext _localctx = new CmdAtribuicaoContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_cmdAtribuicao);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(415);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PONTEIRO) {
				{
				setState(414);
				match(PONTEIRO);
				}
			}

			setState(417);
			identificador();
			setState(418);
			match(OP_ATRIBUICAO);
			setState(419);
			expressao();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdChamadaContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(LAParser.IDENT, 0); }
		public TerminalNode LPAR() { return getToken(LAParser.LPAR, 0); }
		public List<ExpressaoContext> expressao() {
			return getRuleContexts(ExpressaoContext.class);
		}
		public ExpressaoContext expressao(int i) {
			return getRuleContext(ExpressaoContext.class,i);
		}
		public TerminalNode RPAR() { return getToken(LAParser.RPAR, 0); }
		public List<TerminalNode> COMMA() { return getTokens(LAParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LAParser.COMMA, i);
		}
		public CmdChamadaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdChamada; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterCmdChamada(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitCmdChamada(this);
		}
	}

	public final CmdChamadaContext cmdChamada() throws RecognitionException {
		CmdChamadaContext _localctx = new CmdChamadaContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_cmdChamada);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(421);
			match(IDENT);
			setState(422);
			match(LPAR);
			setState(423);
			expressao();
			setState(428);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(424);
				match(COMMA);
				setState(425);
				expressao();
				}
				}
				setState(430);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(431);
			match(RPAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdRetorneContext extends ParserRuleContext {
		public TerminalNode RETORNE() { return getToken(LAParser.RETORNE, 0); }
		public ExpressaoContext expressao() {
			return getRuleContext(ExpressaoContext.class,0);
		}
		public CmdRetorneContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdRetorne; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterCmdRetorne(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitCmdRetorne(this);
		}
	}

	public final CmdRetorneContext cmdRetorne() throws RecognitionException {
		CmdRetorneContext _localctx = new CmdRetorneContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_cmdRetorne);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(433);
			match(RETORNE);
			setState(434);
			expressao();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Exp_aritmeticaContext extends ParserRuleContext {
		public List<TermoContext> termo() {
			return getRuleContexts(TermoContext.class);
		}
		public TermoContext termo(int i) {
			return getRuleContext(TermoContext.class,i);
		}
		public List<Op1Context> op1() {
			return getRuleContexts(Op1Context.class);
		}
		public Op1Context op1(int i) {
			return getRuleContext(Op1Context.class,i);
		}
		public Exp_aritmeticaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp_aritmetica; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterExp_aritmetica(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitExp_aritmetica(this);
		}
	}

	public final Exp_aritmeticaContext exp_aritmetica() throws RecognitionException {
		Exp_aritmeticaContext _localctx = new Exp_aritmeticaContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_exp_aritmetica);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(436);
			termo();
			setState(442);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,43,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(437);
					op1();
					setState(438);
					termo();
					}
					} 
				}
				setState(444);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,43,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TermoContext extends ParserRuleContext {
		public List<FatorContext> fator() {
			return getRuleContexts(FatorContext.class);
		}
		public FatorContext fator(int i) {
			return getRuleContext(FatorContext.class,i);
		}
		public List<Op2Context> op2() {
			return getRuleContexts(Op2Context.class);
		}
		public Op2Context op2(int i) {
			return getRuleContext(Op2Context.class,i);
		}
		public TermoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterTermo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitTermo(this);
		}
	}

	public final TermoContext termo() throws RecognitionException {
		TermoContext _localctx = new TermoContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_termo);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(445);
			fator();
			setState(451);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP_ARIT_2) {
				{
				{
				setState(446);
				op2();
				setState(447);
				fator();
				}
				}
				setState(453);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FatorContext extends ParserRuleContext {
		public List<ParcelaContext> parcela() {
			return getRuleContexts(ParcelaContext.class);
		}
		public ParcelaContext parcela(int i) {
			return getRuleContext(ParcelaContext.class,i);
		}
		public List<Op3Context> op3() {
			return getRuleContexts(Op3Context.class);
		}
		public Op3Context op3(int i) {
			return getRuleContext(Op3Context.class,i);
		}
		public FatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterFator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitFator(this);
		}
	}

	public final FatorContext fator() throws RecognitionException {
		FatorContext _localctx = new FatorContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_fator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(454);
			parcela();
			setState(460);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP_ARIT_3) {
				{
				{
				setState(455);
				op3();
				setState(456);
				parcela();
				}
				}
				setState(462);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Op1Context extends ParserRuleContext {
		public TerminalNode OP_ARIT_1() { return getToken(LAParser.OP_ARIT_1, 0); }
		public Op1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_op1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterOp1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitOp1(this);
		}
	}

	public final Op1Context op1() throws RecognitionException {
		Op1Context _localctx = new Op1Context(_ctx, getState());
		enterRule(_localctx, 72, RULE_op1);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(463);
			match(OP_ARIT_1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Op2Context extends ParserRuleContext {
		public TerminalNode OP_ARIT_2() { return getToken(LAParser.OP_ARIT_2, 0); }
		public Op2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_op2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterOp2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitOp2(this);
		}
	}

	public final Op2Context op2() throws RecognitionException {
		Op2Context _localctx = new Op2Context(_ctx, getState());
		enterRule(_localctx, 74, RULE_op2);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(465);
			match(OP_ARIT_2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Op3Context extends ParserRuleContext {
		public TerminalNode OP_ARIT_3() { return getToken(LAParser.OP_ARIT_3, 0); }
		public Op3Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_op3; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterOp3(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitOp3(this);
		}
	}

	public final Op3Context op3() throws RecognitionException {
		Op3Context _localctx = new Op3Context(_ctx, getState());
		enterRule(_localctx, 76, RULE_op3);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(467);
			match(OP_ARIT_3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParcelaContext extends ParserRuleContext {
		public Parcela_unarioContext parcela_unario() {
			return getRuleContext(Parcela_unarioContext.class,0);
		}
		public Op_unarioContext op_unario() {
			return getRuleContext(Op_unarioContext.class,0);
		}
		public Parcela_nao_unarioContext parcela_nao_unario() {
			return getRuleContext(Parcela_nao_unarioContext.class,0);
		}
		public ParcelaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parcela; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterParcela(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitParcela(this);
		}
	}

	public final ParcelaContext parcela() throws RecognitionException {
		ParcelaContext _localctx = new ParcelaContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_parcela);
		int _la;
		try {
			setState(474);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OP_ARIT_1:
			case PONTEIRO:
			case LPAR:
			case NUM_INT:
			case NUM_REAL:
			case IDENT:
				enterOuterAlt(_localctx, 1);
				{
				setState(470);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OP_ARIT_1) {
					{
					setState(469);
					op_unario();
					}
				}

				setState(472);
				parcela_unario();
				}
				break;
			case ENDERECO:
			case CADEIA:
				enterOuterAlt(_localctx, 2);
				{
				setState(473);
				parcela_nao_unario();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Parcela_unarioContext extends ParserRuleContext {
		public Parcela_unarioContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parcela_unario; }
	 
		public Parcela_unarioContext() { }
		public void copyFrom(Parcela_unarioContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParcelaParentesesContext extends Parcela_unarioContext {
		public TerminalNode LPAR() { return getToken(LAParser.LPAR, 0); }
		public ExpressaoContext expressao() {
			return getRuleContext(ExpressaoContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(LAParser.RPAR, 0); }
		public ParcelaParentesesContext(Parcela_unarioContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterParcelaParenteses(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitParcelaParenteses(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParcelaChamadaFuncaoContext extends Parcela_unarioContext {
		public TerminalNode IDENT() { return getToken(LAParser.IDENT, 0); }
		public TerminalNode LPAR() { return getToken(LAParser.LPAR, 0); }
		public List<ExpressaoContext> expressao() {
			return getRuleContexts(ExpressaoContext.class);
		}
		public ExpressaoContext expressao(int i) {
			return getRuleContext(ExpressaoContext.class,i);
		}
		public TerminalNode RPAR() { return getToken(LAParser.RPAR, 0); }
		public List<TerminalNode> COMMA() { return getTokens(LAParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LAParser.COMMA, i);
		}
		public ParcelaChamadaFuncaoContext(Parcela_unarioContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterParcelaChamadaFuncao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitParcelaChamadaFuncao(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParcelaRealContext extends Parcela_unarioContext {
		public TerminalNode NUM_REAL() { return getToken(LAParser.NUM_REAL, 0); }
		public ParcelaRealContext(Parcela_unarioContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterParcelaReal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitParcelaReal(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParcelaIdentificadorContext extends Parcela_unarioContext {
		public IdentificadorContext identificador() {
			return getRuleContext(IdentificadorContext.class,0);
		}
		public TerminalNode PONTEIRO() { return getToken(LAParser.PONTEIRO, 0); }
		public ParcelaIdentificadorContext(Parcela_unarioContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterParcelaIdentificador(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitParcelaIdentificador(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParcelaInteiroContext extends Parcela_unarioContext {
		public TerminalNode NUM_INT() { return getToken(LAParser.NUM_INT, 0); }
		public ParcelaInteiroContext(Parcela_unarioContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterParcelaInteiro(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitParcelaInteiro(this);
		}
	}

	public final Parcela_unarioContext parcela_unario() throws RecognitionException {
		Parcela_unarioContext _localctx = new Parcela_unarioContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_parcela_unario);
		int _la;
		try {
			setState(498);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,50,_ctx) ) {
			case 1:
				_localctx = new ParcelaIdentificadorContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(477);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==PONTEIRO) {
					{
					setState(476);
					match(PONTEIRO);
					}
				}

				setState(479);
				identificador();
				}
				break;
			case 2:
				_localctx = new ParcelaChamadaFuncaoContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(480);
				match(IDENT);
				setState(481);
				match(LPAR);
				setState(482);
				expressao();
				setState(487);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(483);
					match(COMMA);
					setState(484);
					expressao();
					}
					}
					setState(489);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(490);
				match(RPAR);
				}
				break;
			case 3:
				_localctx = new ParcelaInteiroContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(492);
				match(NUM_INT);
				}
				break;
			case 4:
				_localctx = new ParcelaRealContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(493);
				match(NUM_REAL);
				}
				break;
			case 5:
				_localctx = new ParcelaParentesesContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(494);
				match(LPAR);
				setState(495);
				expressao();
				setState(496);
				match(RPAR);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Parcela_nao_unarioContext extends ParserRuleContext {
		public Parcela_nao_unarioContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parcela_nao_unario; }
	 
		public Parcela_nao_unarioContext() { }
		public void copyFrom(Parcela_nao_unarioContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParcelaCadeiaContext extends Parcela_nao_unarioContext {
		public TerminalNode CADEIA() { return getToken(LAParser.CADEIA, 0); }
		public ParcelaCadeiaContext(Parcela_nao_unarioContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterParcelaCadeia(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitParcelaCadeia(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParcelaEnderecoContext extends Parcela_nao_unarioContext {
		public TerminalNode ENDERECO() { return getToken(LAParser.ENDERECO, 0); }
		public IdentificadorContext identificador() {
			return getRuleContext(IdentificadorContext.class,0);
		}
		public ParcelaEnderecoContext(Parcela_nao_unarioContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterParcelaEndereco(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitParcelaEndereco(this);
		}
	}

	public final Parcela_nao_unarioContext parcela_nao_unario() throws RecognitionException {
		Parcela_nao_unarioContext _localctx = new Parcela_nao_unarioContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_parcela_nao_unario);
		try {
			setState(503);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ENDERECO:
				_localctx = new ParcelaEnderecoContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(500);
				match(ENDERECO);
				setState(501);
				identificador();
				}
				break;
			case CADEIA:
				_localctx = new ParcelaCadeiaContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(502);
				match(CADEIA);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Exp_relacionalContext extends ParserRuleContext {
		public List<Exp_aritmeticaContext> exp_aritmetica() {
			return getRuleContexts(Exp_aritmeticaContext.class);
		}
		public Exp_aritmeticaContext exp_aritmetica(int i) {
			return getRuleContext(Exp_aritmeticaContext.class,i);
		}
		public Op_relacionalContext op_relacional() {
			return getRuleContext(Op_relacionalContext.class,0);
		}
		public Exp_relacionalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp_relacional; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterExp_relacional(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitExp_relacional(this);
		}
	}

	public final Exp_relacionalContext exp_relacional() throws RecognitionException {
		Exp_relacionalContext _localctx = new Exp_relacionalContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_exp_relacional);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(505);
			exp_aritmetica();
			setState(509);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OP_REL) {
				{
				setState(506);
				op_relacional();
				setState(507);
				exp_aritmetica();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Op_relacionalContext extends ParserRuleContext {
		public TerminalNode OP_REL() { return getToken(LAParser.OP_REL, 0); }
		public Op_relacionalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_op_relacional; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterOp_relacional(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitOp_relacional(this);
		}
	}

	public final Op_relacionalContext op_relacional() throws RecognitionException {
		Op_relacionalContext _localctx = new Op_relacionalContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_op_relacional);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(511);
			match(OP_REL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressaoContext extends ParserRuleContext {
		public List<Termo_logicContext> termo_logic() {
			return getRuleContexts(Termo_logicContext.class);
		}
		public Termo_logicContext termo_logic(int i) {
			return getRuleContext(Termo_logicContext.class,i);
		}
		public List<Op_logic_1Context> op_logic_1() {
			return getRuleContexts(Op_logic_1Context.class);
		}
		public Op_logic_1Context op_logic_1(int i) {
			return getRuleContext(Op_logic_1Context.class,i);
		}
		public ExpressaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterExpressao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitExpressao(this);
		}
	}

	public final ExpressaoContext expressao() throws RecognitionException {
		ExpressaoContext _localctx = new ExpressaoContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_expressao);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(513);
			termo_logic();
			setState(519);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP_LOGICO_1) {
				{
				{
				setState(514);
				op_logic_1();
				setState(515);
				termo_logic();
				}
				}
				setState(521);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Termo_logicContext extends ParserRuleContext {
		public List<Fator_logicContext> fator_logic() {
			return getRuleContexts(Fator_logicContext.class);
		}
		public Fator_logicContext fator_logic(int i) {
			return getRuleContext(Fator_logicContext.class,i);
		}
		public List<Op_logic_2Context> op_logic_2() {
			return getRuleContexts(Op_logic_2Context.class);
		}
		public Op_logic_2Context op_logic_2(int i) {
			return getRuleContext(Op_logic_2Context.class,i);
		}
		public Termo_logicContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termo_logic; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterTermo_logic(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitTermo_logic(this);
		}
	}

	public final Termo_logicContext termo_logic() throws RecognitionException {
		Termo_logicContext _localctx = new Termo_logicContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_termo_logic);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(522);
			fator_logic();
			setState(528);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP_LOGICO_2) {
				{
				{
				setState(523);
				op_logic_2();
				setState(524);
				fator_logic();
				}
				}
				setState(530);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Fator_logicContext extends ParserRuleContext {
		public Parcela_logicContext parcela_logic() {
			return getRuleContext(Parcela_logicContext.class,0);
		}
		public TerminalNode NAO() { return getToken(LAParser.NAO, 0); }
		public Fator_logicContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fator_logic; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterFator_logic(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitFator_logic(this);
		}
	}

	public final Fator_logicContext fator_logic() throws RecognitionException {
		Fator_logicContext _localctx = new Fator_logicContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_fator_logic);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(532);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NAO) {
				{
				setState(531);
				match(NAO);
				}
			}

			setState(534);
			parcela_logic();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Parcela_logicContext extends ParserRuleContext {
		public Parcela_logicContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parcela_logic; }
	 
		public Parcela_logicContext() { }
		public void copyFrom(Parcela_logicContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParcelaLogicaConstanteContext extends Parcela_logicContext {
		public TerminalNode VERDADEIRO() { return getToken(LAParser.VERDADEIRO, 0); }
		public TerminalNode FALSO() { return getToken(LAParser.FALSO, 0); }
		public ParcelaLogicaConstanteContext(Parcela_logicContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterParcelaLogicaConstante(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitParcelaLogicaConstante(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParcelaLogicaRelacionalContext extends Parcela_logicContext {
		public Exp_relacionalContext exp_relacional() {
			return getRuleContext(Exp_relacionalContext.class,0);
		}
		public ParcelaLogicaRelacionalContext(Parcela_logicContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterParcelaLogicaRelacional(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitParcelaLogicaRelacional(this);
		}
	}

	public final Parcela_logicContext parcela_logic() throws RecognitionException {
		Parcela_logicContext _localctx = new Parcela_logicContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_parcela_logic);
		try {
			setState(539);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VERDADEIRO:
				_localctx = new ParcelaLogicaConstanteContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(536);
				match(VERDADEIRO);
				}
				break;
			case FALSO:
				_localctx = new ParcelaLogicaConstanteContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(537);
				match(FALSO);
				}
				break;
			case OP_ARIT_1:
			case PONTEIRO:
			case ENDERECO:
			case LPAR:
			case NUM_INT:
			case NUM_REAL:
			case IDENT:
			case CADEIA:
				_localctx = new ParcelaLogicaRelacionalContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(538);
				exp_relacional();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Op_logic_1Context extends ParserRuleContext {
		public TerminalNode OP_LOGICO_1() { return getToken(LAParser.OP_LOGICO_1, 0); }
		public Op_logic_1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_op_logic_1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterOp_logic_1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitOp_logic_1(this);
		}
	}

	public final Op_logic_1Context op_logic_1() throws RecognitionException {
		Op_logic_1Context _localctx = new Op_logic_1Context(_ctx, getState());
		enterRule(_localctx, 96, RULE_op_logic_1);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(541);
			match(OP_LOGICO_1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Op_logic_2Context extends ParserRuleContext {
		public TerminalNode OP_LOGICO_2() { return getToken(LAParser.OP_LOGICO_2, 0); }
		public Op_logic_2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_op_logic_2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).enterOp_logic_2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LAParserListener ) ((LAParserListener)listener).exitOp_logic_2(this);
		}
	}

	public final Op_logic_2Context op_logic_2() throws RecognitionException {
		Op_logic_2Context _localctx = new Op_logic_2Context(_ctx, getState());
		enterRule(_localctx, 98, RULE_op_logic_2);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(543);
			match(OP_LOGICO_2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001=\u0222\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002"+
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007\'\u0002"+
		"(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007+\u0002,\u0007,\u0002"+
		"-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u00070\u00021\u00071\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0005"+
		"\u0001k\b\u0001\n\u0001\f\u0001n\t\u0001\u0001\u0002\u0001\u0002\u0003"+
		"\u0002r\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0003\u0003\u0081\b\u0003\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0005\u0004\u0086\b\u0004\n\u0004\f\u0004\u0089\t\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0005\u0005\u0091\b\u0005\n\u0005\f\u0005\u0094\t\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0005\u0006\u009c"+
		"\b\u0006\n\u0006\f\u0006\u009f\t\u0006\u0001\u0007\u0001\u0007\u0003\u0007"+
		"\u00a3\b\u0007\u0001\b\u0001\b\u0001\t\u0001\t\u0003\t\u00a9\b\t\u0001"+
		"\n\u0003\n\u00ac\b\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\f\u0001"+
		"\f\u0005\f\u00b4\b\f\n\f\f\f\u00b7\t\f\u0001\f\u0001\f\u0001\r\u0001\r"+
		"\u0001\r\u0001\r\u0003\r\u00bf\b\r\u0001\r\u0001\r\u0005\r\u00c3\b\r\n"+
		"\r\f\r\u00c6\t\r\u0001\r\u0005\r\u00c9\b\r\n\r\f\r\u00cc\t\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0003\r\u00d3\b\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0005\r\u00d9\b\r\n\r\f\r\u00dc\t\r\u0001\r\u0005\r\u00df\b\r\n\r\f"+
		"\r\u00e2\t\r\u0001\r\u0001\r\u0003\r\u00e6\b\r\u0001\u000e\u0003\u000e"+
		"\u00e9\b\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0005\u000e\u00ee\b"+
		"\u000e\n\u000e\f\u000e\u00f1\t\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0005\u000f\u00f9\b\u000f\n\u000f"+
		"\f\u000f\u00fc\t\u000f\u0001\u0010\u0005\u0010\u00ff\b\u0010\n\u0010\f"+
		"\u0010\u0102\t\u0010\u0001\u0010\u0005\u0010\u0105\b\u0010\n\u0010\f\u0010"+
		"\u0108\t\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0003\u0011"+
		"\u0114\b\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0003\u0012\u0119\b"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0003\u0012\u011f"+
		"\b\u0012\u0001\u0012\u0005\u0012\u0122\b\u0012\n\u0012\f\u0012\u0125\t"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0005\u0013\u012e\b\u0013\n\u0013\f\u0013\u0131\t\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0005\u0014\u0139\b\u0014\n\u0014\f\u0014\u013c\t\u0014\u0001\u0014\u0001"+
		"\u0014\u0005\u0014\u0140\b\u0014\n\u0014\f\u0014\u0143\t\u0014\u0003\u0014"+
		"\u0145\b\u0014\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0005\u0015\u014f\b\u0015\n\u0015"+
		"\f\u0015\u0152\t\u0015\u0003\u0015\u0154\b\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0016\u0005\u0016\u0159\b\u0016\n\u0016\f\u0016\u015c\t\u0016\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0005\u0017\u0161\b\u0017\n\u0017\f\u0017"+
		"\u0164\t\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0005\u0018\u0169\b"+
		"\u0018\n\u0018\f\u0018\u016c\t\u0018\u0001\u0019\u0003\u0019\u016f\b\u0019"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0003\u0019\u0174\b\u0019\u0001\u0019"+
		"\u0003\u0019\u0177\b\u0019\u0001\u001a\u0001\u001a\u0001\u001b\u0001\u001b"+
		"\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b"+
		"\u0005\u001b\u0183\b\u001b\n\u001b\f\u001b\u0186\t\u001b\u0001\u001b\u0001"+
		"\u001b\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0005\u001c\u018e"+
		"\b\u001c\n\u001c\f\u001c\u0191\t\u001c\u0001\u001c\u0001\u001c\u0001\u001d"+
		"\u0001\u001d\u0005\u001d\u0197\b\u001d\n\u001d\f\u001d\u019a\t\u001d\u0001"+
		"\u001d\u0001\u001d\u0001\u001d\u0001\u001e\u0003\u001e\u01a0\b\u001e\u0001"+
		"\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001f\u0001\u001f\u0001"+
		"\u001f\u0001\u001f\u0001\u001f\u0005\u001f\u01ab\b\u001f\n\u001f\f\u001f"+
		"\u01ae\t\u001f\u0001\u001f\u0001\u001f\u0001 \u0001 \u0001 \u0001!\u0001"+
		"!\u0001!\u0001!\u0005!\u01b9\b!\n!\f!\u01bc\t!\u0001\"\u0001\"\u0001\""+
		"\u0001\"\u0005\"\u01c2\b\"\n\"\f\"\u01c5\t\"\u0001#\u0001#\u0001#\u0001"+
		"#\u0005#\u01cb\b#\n#\f#\u01ce\t#\u0001$\u0001$\u0001%\u0001%\u0001&\u0001"+
		"&\u0001\'\u0003\'\u01d7\b\'\u0001\'\u0001\'\u0003\'\u01db\b\'\u0001(\u0003"+
		"(\u01de\b(\u0001(\u0001(\u0001(\u0001(\u0001(\u0001(\u0005(\u01e6\b(\n"+
		"(\f(\u01e9\t(\u0001(\u0001(\u0001(\u0001(\u0001(\u0001(\u0001(\u0001("+
		"\u0003(\u01f3\b(\u0001)\u0001)\u0001)\u0003)\u01f8\b)\u0001*\u0001*\u0001"+
		"*\u0001*\u0003*\u01fe\b*\u0001+\u0001+\u0001,\u0001,\u0001,\u0001,\u0005"+
		",\u0206\b,\n,\f,\u0209\t,\u0001-\u0001-\u0001-\u0001-\u0005-\u020f\b-"+
		"\n-\f-\u0212\t-\u0001.\u0003.\u0215\b.\u0001.\u0001.\u0001/\u0001/\u0001"+
		"/\u0003/\u021c\b/\u00010\u00010\u00011\u00011\u00011\u0000\u00002\u0000"+
		"\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c"+
		"\u001e \"$&(*,.02468:<>@BDFHJLNPRTVXZ\\^`b\u0000\u0002\u0001\u0000\u001d"+
		" \u0003\u0000!\"5688\u0235\u0000d\u0001\u0000\u0000\u0000\u0002l\u0001"+
		"\u0000\u0000\u0000\u0004q\u0001\u0000\u0000\u0000\u0006\u0080\u0001\u0000"+
		"\u0000\u0000\b\u0082\u0001\u0000\u0000\u0000\n\u008d\u0001\u0000\u0000"+
		"\u0000\f\u009d\u0001\u0000\u0000\u0000\u000e\u00a2\u0001\u0000\u0000\u0000"+
		"\u0010\u00a4\u0001\u0000\u0000\u0000\u0012\u00a8\u0001\u0000\u0000\u0000"+
		"\u0014\u00ab\u0001\u0000\u0000\u0000\u0016\u00af\u0001\u0000\u0000\u0000"+
		"\u0018\u00b1\u0001\u0000\u0000\u0000\u001a\u00e5\u0001\u0000\u0000\u0000"+
		"\u001c\u00e8\u0001\u0000\u0000\u0000\u001e\u00f5\u0001\u0000\u0000\u0000"+
		" \u0100\u0001\u0000\u0000\u0000\"\u0113\u0001\u0000\u0000\u0000$\u0115"+
		"\u0001\u0000\u0000\u0000&\u0128\u0001\u0000\u0000\u0000(\u0134\u0001\u0000"+
		"\u0000\u0000*\u0148\u0001\u0000\u0000\u0000,\u015a\u0001\u0000\u0000\u0000"+
		".\u015d\u0001\u0000\u0000\u00000\u0165\u0001\u0000\u0000\u00002\u016e"+
		"\u0001\u0000\u0000\u00004\u0178\u0001\u0000\u0000\u00006\u017a\u0001\u0000"+
		"\u0000\u00008\u0189\u0001\u0000\u0000\u0000:\u0194\u0001\u0000\u0000\u0000"+
		"<\u019f\u0001\u0000\u0000\u0000>\u01a5\u0001\u0000\u0000\u0000@\u01b1"+
		"\u0001\u0000\u0000\u0000B\u01b4\u0001\u0000\u0000\u0000D\u01bd\u0001\u0000"+
		"\u0000\u0000F\u01c6\u0001\u0000\u0000\u0000H\u01cf\u0001\u0000\u0000\u0000"+
		"J\u01d1\u0001\u0000\u0000\u0000L\u01d3\u0001\u0000\u0000\u0000N\u01da"+
		"\u0001\u0000\u0000\u0000P\u01f2\u0001\u0000\u0000\u0000R\u01f7\u0001\u0000"+
		"\u0000\u0000T\u01f9\u0001\u0000\u0000\u0000V\u01ff\u0001\u0000\u0000\u0000"+
		"X\u0201\u0001\u0000\u0000\u0000Z\u020a\u0001\u0000\u0000\u0000\\\u0214"+
		"\u0001\u0000\u0000\u0000^\u021b\u0001\u0000\u0000\u0000`\u021d\u0001\u0000"+
		"\u0000\u0000b\u021f\u0001\u0000\u0000\u0000de\u0003\u0002\u0001\u0000"+
		"ef\u0005\u0001\u0000\u0000fg\u0003 \u0010\u0000gh\u0005\u0002\u0000\u0000"+
		"h\u0001\u0001\u0000\u0000\u0000ik\u0003\u0004\u0002\u0000ji\u0001\u0000"+
		"\u0000\u0000kn\u0001\u0000\u0000\u0000lj\u0001\u0000\u0000\u0000lm\u0001"+
		"\u0000\u0000\u0000m\u0003\u0001\u0000\u0000\u0000nl\u0001\u0000\u0000"+
		"\u0000or\u0003\u0006\u0003\u0000pr\u0003\u001a\r\u0000qo\u0001\u0000\u0000"+
		"\u0000qp\u0001\u0000\u0000\u0000r\u0005\u0001\u0000\u0000\u0000st\u0005"+
		"\u0003\u0000\u0000t\u0081\u0003\b\u0004\u0000uv\u0005\u0004\u0000\u0000"+
		"vw\u00057\u0000\u0000wx\u00053\u0000\u0000xy\u0003\u0010\b\u0000yz\u0005"+
		"$\u0000\u0000z{\u0003\u0016\u000b\u0000{\u0081\u0001\u0000\u0000\u0000"+
		"|}\u0005\u0005\u0000\u0000}~\u00057\u0000\u0000~\u007f\u00053\u0000\u0000"+
		"\u007f\u0081\u0003\u000e\u0007\u0000\u0080s\u0001\u0000\u0000\u0000\u0080"+
		"u\u0001\u0000\u0000\u0000\u0080|\u0001\u0000\u0000\u0000\u0081\u0007\u0001"+
		"\u0000\u0000\u0000\u0082\u0087\u0003\n\u0005\u0000\u0083\u0084\u00052"+
		"\u0000\u0000\u0084\u0086\u0003\n\u0005\u0000\u0085\u0083\u0001\u0000\u0000"+
		"\u0000\u0086\u0089\u0001\u0000\u0000\u0000\u0087\u0085\u0001\u0000\u0000"+
		"\u0000\u0087\u0088\u0001\u0000\u0000\u0000\u0088\u008a\u0001\u0000\u0000"+
		"\u0000\u0089\u0087\u0001\u0000\u0000\u0000\u008a\u008b\u00053\u0000\u0000"+
		"\u008b\u008c\u0003\u000e\u0007\u0000\u008c\t\u0001\u0000\u0000\u0000\u008d"+
		"\u0092\u00057\u0000\u0000\u008e\u008f\u00054\u0000\u0000\u008f\u0091\u0005"+
		"7\u0000\u0000\u0090\u008e\u0001\u0000\u0000\u0000\u0091\u0094\u0001\u0000"+
		"\u0000\u0000\u0092\u0090\u0001\u0000\u0000\u0000\u0092\u0093\u0001\u0000"+
		"\u0000\u0000\u0093\u0095\u0001\u0000\u0000\u0000\u0094\u0092\u0001\u0000"+
		"\u0000\u0000\u0095\u0096\u0003\f\u0006\u0000\u0096\u000b\u0001\u0000\u0000"+
		"\u0000\u0097\u0098\u00050\u0000\u0000\u0098\u0099\u0003B!\u0000\u0099"+
		"\u009a\u00051\u0000\u0000\u009a\u009c\u0001\u0000\u0000\u0000\u009b\u0097"+
		"\u0001\u0000\u0000\u0000\u009c\u009f\u0001\u0000\u0000\u0000\u009d\u009b"+
		"\u0001\u0000\u0000\u0000\u009d\u009e\u0001\u0000\u0000\u0000\u009e\r\u0001"+
		"\u0000\u0000\u0000\u009f\u009d\u0001\u0000\u0000\u0000\u00a0\u00a3\u0003"+
		"\u0018\f\u0000\u00a1\u00a3\u0003\u0014\n\u0000\u00a2\u00a0\u0001\u0000"+
		"\u0000\u0000\u00a2\u00a1\u0001\u0000\u0000\u0000\u00a3\u000f\u0001\u0000"+
		"\u0000\u0000\u00a4\u00a5\u0007\u0000\u0000\u0000\u00a5\u0011\u0001\u0000"+
		"\u0000\u0000\u00a6\u00a9\u0003\u0010\b\u0000\u00a7\u00a9\u00057\u0000"+
		"\u0000\u00a8\u00a6\u0001\u0000\u0000\u0000\u00a8\u00a7\u0001\u0000\u0000"+
		"\u0000\u00a9\u0013\u0001\u0000\u0000\u0000\u00aa\u00ac\u0005,\u0000\u0000"+
		"\u00ab\u00aa\u0001\u0000\u0000\u0000\u00ab\u00ac\u0001\u0000\u0000\u0000"+
		"\u00ac\u00ad\u0001\u0000\u0000\u0000\u00ad\u00ae\u0003\u0012\t\u0000\u00ae"+
		"\u0015\u0001\u0000\u0000\u0000\u00af\u00b0\u0007\u0001\u0000\u0000\u00b0"+
		"\u0017\u0001\u0000\u0000\u0000\u00b1\u00b5\u0005\u0006\u0000\u0000\u00b2"+
		"\u00b4\u0003\b\u0004\u0000\u00b3\u00b2\u0001\u0000\u0000\u0000\u00b4\u00b7"+
		"\u0001\u0000\u0000\u0000\u00b5\u00b3\u0001\u0000\u0000\u0000\u00b5\u00b6"+
		"\u0001\u0000\u0000\u0000\u00b6\u00b8\u0001\u0000\u0000\u0000\u00b7\u00b5"+
		"\u0001\u0000\u0000\u0000\u00b8\u00b9\u0005\u0007\u0000\u0000\u00b9\u0019"+
		"\u0001\u0000\u0000\u0000\u00ba\u00bb\u0005\b\u0000\u0000\u00bb\u00bc\u0005"+
		"7\u0000\u0000\u00bc\u00be\u0005.\u0000\u0000\u00bd\u00bf\u0003\u001e\u000f"+
		"\u0000\u00be\u00bd\u0001\u0000\u0000\u0000\u00be\u00bf\u0001\u0000\u0000"+
		"\u0000\u00bf\u00c0\u0001\u0000\u0000\u0000\u00c0\u00c4\u0005/\u0000\u0000"+
		"\u00c1\u00c3\u0003\u0006\u0003\u0000\u00c2\u00c1\u0001\u0000\u0000\u0000"+
		"\u00c3\u00c6\u0001\u0000\u0000\u0000\u00c4\u00c2\u0001\u0000\u0000\u0000"+
		"\u00c4\u00c5\u0001\u0000\u0000\u0000\u00c5\u00ca\u0001\u0000\u0000\u0000"+
		"\u00c6\u00c4\u0001\u0000\u0000\u0000\u00c7\u00c9\u0003\"\u0011\u0000\u00c8"+
		"\u00c7\u0001\u0000\u0000\u0000\u00c9\u00cc\u0001\u0000\u0000\u0000\u00ca"+
		"\u00c8\u0001\u0000\u0000\u0000\u00ca\u00cb\u0001\u0000\u0000\u0000\u00cb"+
		"\u00cd\u0001\u0000\u0000\u0000\u00cc\u00ca\u0001\u0000\u0000\u0000\u00cd"+
		"\u00e6\u0005\t\u0000\u0000\u00ce\u00cf\u0005\n\u0000\u0000\u00cf\u00d0"+
		"\u00057\u0000\u0000\u00d0\u00d2\u0005.\u0000\u0000\u00d1\u00d3\u0003\u001e"+
		"\u000f\u0000\u00d2\u00d1\u0001\u0000\u0000\u0000\u00d2\u00d3\u0001\u0000"+
		"\u0000\u0000\u00d3\u00d4\u0001\u0000\u0000\u0000\u00d4\u00d5\u0005/\u0000"+
		"\u0000\u00d5\u00d6\u00053\u0000\u0000\u00d6\u00da\u0003\u0014\n\u0000"+
		"\u00d7\u00d9\u0003\u0006\u0003\u0000\u00d8\u00d7\u0001\u0000\u0000\u0000"+
		"\u00d9\u00dc\u0001\u0000\u0000\u0000\u00da\u00d8\u0001\u0000\u0000\u0000"+
		"\u00da\u00db\u0001\u0000\u0000\u0000\u00db\u00e0\u0001\u0000\u0000\u0000"+
		"\u00dc\u00da\u0001\u0000\u0000\u0000\u00dd\u00df\u0003\"\u0011\u0000\u00de"+
		"\u00dd\u0001\u0000\u0000\u0000\u00df\u00e2\u0001\u0000\u0000\u0000\u00e0"+
		"\u00de\u0001\u0000\u0000\u0000\u00e0\u00e1\u0001\u0000\u0000\u0000\u00e1"+
		"\u00e3\u0001\u0000\u0000\u0000\u00e2\u00e0\u0001\u0000\u0000\u0000\u00e3"+
		"\u00e4\u0005\u000b\u0000\u0000\u00e4\u00e6\u0001\u0000\u0000\u0000\u00e5"+
		"\u00ba\u0001\u0000\u0000\u0000\u00e5\u00ce\u0001\u0000\u0000\u0000\u00e6"+
		"\u001b\u0001\u0000\u0000\u0000\u00e7\u00e9\u0005\f\u0000\u0000\u00e8\u00e7"+
		"\u0001\u0000\u0000\u0000\u00e8\u00e9\u0001\u0000\u0000\u0000\u00e9\u00ea"+
		"\u0001\u0000\u0000\u0000\u00ea\u00ef\u0003\n\u0005\u0000\u00eb\u00ec\u0005"+
		"2\u0000\u0000\u00ec\u00ee\u0003\n\u0005\u0000\u00ed\u00eb\u0001\u0000"+
		"\u0000\u0000\u00ee\u00f1\u0001\u0000\u0000\u0000\u00ef\u00ed\u0001\u0000"+
		"\u0000\u0000\u00ef\u00f0\u0001\u0000\u0000\u0000\u00f0\u00f2\u0001\u0000"+
		"\u0000\u0000\u00f1\u00ef\u0001\u0000\u0000\u0000\u00f2\u00f3\u00053\u0000"+
		"\u0000\u00f3\u00f4\u0003\u0014\n\u0000\u00f4\u001d\u0001\u0000\u0000\u0000"+
		"\u00f5\u00fa\u0003\u001c\u000e\u0000\u00f6\u00f7\u00052\u0000\u0000\u00f7"+
		"\u00f9\u0003\u001c\u000e\u0000\u00f8\u00f6\u0001\u0000\u0000\u0000\u00f9"+
		"\u00fc\u0001\u0000\u0000\u0000\u00fa\u00f8\u0001\u0000\u0000\u0000\u00fa"+
		"\u00fb\u0001\u0000\u0000\u0000\u00fb\u001f\u0001\u0000\u0000\u0000\u00fc"+
		"\u00fa\u0001\u0000\u0000\u0000\u00fd\u00ff\u0003\u0006\u0003\u0000\u00fe"+
		"\u00fd\u0001\u0000\u0000\u0000\u00ff\u0102\u0001\u0000\u0000\u0000\u0100"+
		"\u00fe\u0001\u0000\u0000\u0000\u0100\u0101\u0001\u0000\u0000\u0000\u0101"+
		"\u0106\u0001\u0000\u0000\u0000\u0102\u0100\u0001\u0000\u0000\u0000\u0103"+
		"\u0105\u0003\"\u0011\u0000\u0104\u0103\u0001\u0000\u0000\u0000\u0105\u0108"+
		"\u0001\u0000\u0000\u0000\u0106\u0104\u0001\u0000\u0000\u0000\u0106\u0107"+
		"\u0001\u0000\u0000\u0000\u0107!\u0001\u0000\u0000\u0000\u0108\u0106\u0001"+
		"\u0000\u0000\u0000\u0109\u0114\u0003$\u0012\u0000\u010a\u0114\u0003&\u0013"+
		"\u0000\u010b\u0114\u0003(\u0014\u0000\u010c\u0114\u0003*\u0015\u0000\u010d"+
		"\u0114\u00036\u001b\u0000\u010e\u0114\u00038\u001c\u0000\u010f\u0114\u0003"+
		":\u001d\u0000\u0110\u0114\u0003<\u001e\u0000\u0111\u0114\u0003>\u001f"+
		"\u0000\u0112\u0114\u0003@ \u0000\u0113\u0109\u0001\u0000\u0000\u0000\u0113"+
		"\u010a\u0001\u0000\u0000\u0000\u0113\u010b\u0001\u0000\u0000\u0000\u0113"+
		"\u010c\u0001\u0000\u0000\u0000\u0113\u010d\u0001\u0000\u0000\u0000\u0113"+
		"\u010e\u0001\u0000\u0000\u0000\u0113\u010f\u0001\u0000\u0000\u0000\u0113"+
		"\u0110\u0001\u0000\u0000\u0000\u0113\u0111\u0001\u0000\u0000\u0000\u0113"+
		"\u0112\u0001\u0000\u0000\u0000\u0114#\u0001\u0000\u0000\u0000\u0115\u0116"+
		"\u0005\r\u0000\u0000\u0116\u0118\u0005.\u0000\u0000\u0117\u0119\u0005"+
		",\u0000\u0000\u0118\u0117\u0001\u0000\u0000\u0000\u0118\u0119\u0001\u0000"+
		"\u0000\u0000\u0119\u011a\u0001\u0000\u0000\u0000\u011a\u011b\u0003\n\u0005"+
		"\u0000\u011b\u0123\u0001\u0000\u0000\u0000\u011c\u011e\u00052\u0000\u0000"+
		"\u011d\u011f\u0005,\u0000\u0000\u011e\u011d\u0001\u0000\u0000\u0000\u011e"+
		"\u011f\u0001\u0000\u0000\u0000\u011f\u0120\u0001\u0000\u0000\u0000\u0120"+
		"\u0122\u0003\n\u0005\u0000\u0121\u011c\u0001\u0000\u0000\u0000\u0122\u0125"+
		"\u0001\u0000\u0000\u0000\u0123\u0121\u0001\u0000\u0000\u0000\u0123\u0124"+
		"\u0001\u0000\u0000\u0000\u0124\u0126\u0001\u0000\u0000\u0000\u0125\u0123"+
		"\u0001\u0000\u0000\u0000\u0126\u0127\u0005/\u0000\u0000\u0127%\u0001\u0000"+
		"\u0000\u0000\u0128\u0129\u0005\u000e\u0000\u0000\u0129\u012a\u0005.\u0000"+
		"\u0000\u012a\u012f\u0003X,\u0000\u012b\u012c\u00052\u0000\u0000\u012c"+
		"\u012e\u0003X,\u0000\u012d\u012b\u0001\u0000\u0000\u0000\u012e\u0131\u0001"+
		"\u0000\u0000\u0000\u012f\u012d\u0001\u0000\u0000\u0000\u012f\u0130\u0001"+
		"\u0000\u0000\u0000\u0130\u0132\u0001\u0000\u0000\u0000\u0131\u012f\u0001"+
		"\u0000\u0000\u0000\u0132\u0133\u0005/\u0000\u0000\u0133\'\u0001\u0000"+
		"\u0000\u0000\u0134\u0135\u0005\u000f\u0000\u0000\u0135\u0136\u0003X,\u0000"+
		"\u0136\u013a\u0005\u0010\u0000\u0000\u0137\u0139\u0003\"\u0011\u0000\u0138"+
		"\u0137\u0001\u0000\u0000\u0000\u0139\u013c\u0001\u0000\u0000\u0000\u013a"+
		"\u0138\u0001\u0000\u0000\u0000\u013a\u013b\u0001\u0000\u0000\u0000\u013b"+
		"\u0144\u0001\u0000\u0000\u0000\u013c\u013a\u0001\u0000\u0000\u0000\u013d"+
		"\u0141\u0005\u0011\u0000\u0000\u013e\u0140\u0003\"\u0011\u0000\u013f\u013e"+
		"\u0001\u0000\u0000\u0000\u0140\u0143\u0001\u0000\u0000\u0000\u0141\u013f"+
		"\u0001\u0000\u0000\u0000\u0141\u0142\u0001\u0000\u0000\u0000\u0142\u0145"+
		"\u0001\u0000\u0000\u0000\u0143\u0141\u0001\u0000\u0000\u0000\u0144\u013d"+
		"\u0001\u0000\u0000\u0000\u0144\u0145\u0001\u0000\u0000\u0000\u0145\u0146"+
		"\u0001\u0000\u0000\u0000\u0146\u0147\u0005\u0012\u0000\u0000\u0147)\u0001"+
		"\u0000\u0000\u0000\u0148\u0149\u0005\u0013\u0000\u0000\u0149\u014a\u0003"+
		"B!\u0000\u014a\u014b\u0005\u0014\u0000\u0000\u014b\u0153\u0003,\u0016"+
		"\u0000\u014c\u0150\u0005\u0011\u0000\u0000\u014d\u014f\u0003\"\u0011\u0000"+
		"\u014e\u014d\u0001\u0000\u0000\u0000\u014f\u0152\u0001\u0000\u0000\u0000"+
		"\u0150\u014e\u0001\u0000\u0000\u0000\u0150\u0151\u0001\u0000\u0000\u0000"+
		"\u0151\u0154\u0001\u0000\u0000\u0000\u0152\u0150\u0001\u0000\u0000\u0000"+
		"\u0153\u014c\u0001\u0000\u0000\u0000\u0153\u0154\u0001\u0000\u0000\u0000"+
		"\u0154\u0155\u0001\u0000\u0000\u0000\u0155\u0156\u0005\u0015\u0000\u0000"+
		"\u0156+\u0001\u0000\u0000\u0000\u0157\u0159\u0003.\u0017\u0000\u0158\u0157"+
		"\u0001\u0000\u0000\u0000\u0159\u015c\u0001\u0000\u0000\u0000\u015a\u0158"+
		"\u0001\u0000\u0000\u0000\u015a\u015b\u0001\u0000\u0000\u0000\u015b-\u0001"+
		"\u0000\u0000\u0000\u015c\u015a\u0001\u0000\u0000\u0000\u015d\u015e\u0003"+
		"0\u0018\u0000\u015e\u0162\u00053\u0000\u0000\u015f\u0161\u0003\"\u0011"+
		"\u0000\u0160\u015f\u0001\u0000\u0000\u0000\u0161\u0164\u0001\u0000\u0000"+
		"\u0000\u0162\u0160\u0001\u0000\u0000\u0000\u0162\u0163\u0001\u0000\u0000"+
		"\u0000\u0163/\u0001\u0000\u0000\u0000\u0164\u0162\u0001\u0000\u0000\u0000"+
		"\u0165\u016a\u00032\u0019\u0000\u0166\u0167\u00052\u0000\u0000\u0167\u0169"+
		"\u00032\u0019\u0000\u0168\u0166\u0001\u0000\u0000\u0000\u0169\u016c\u0001"+
		"\u0000\u0000\u0000\u016a\u0168\u0001\u0000\u0000\u0000\u016a\u016b\u0001"+
		"\u0000\u0000\u0000\u016b1\u0001\u0000\u0000\u0000\u016c\u016a\u0001\u0000"+
		"\u0000\u0000\u016d\u016f\u00034\u001a\u0000\u016e\u016d\u0001\u0000\u0000"+
		"\u0000\u016e\u016f\u0001\u0000\u0000\u0000\u016f\u0170\u0001\u0000\u0000"+
		"\u0000\u0170\u0176\u00055\u0000\u0000\u0171\u0173\u0005+\u0000\u0000\u0172"+
		"\u0174\u00034\u001a\u0000\u0173\u0172\u0001\u0000\u0000\u0000\u0173\u0174"+
		"\u0001\u0000\u0000\u0000\u0174\u0175\u0001\u0000\u0000\u0000\u0175\u0177"+
		"\u00055\u0000\u0000\u0176\u0171\u0001\u0000\u0000\u0000\u0176\u0177\u0001"+
		"\u0000\u0000\u0000\u01773\u0001\u0000\u0000\u0000\u0178\u0179\u0005%\u0000"+
		"\u0000\u01795\u0001\u0000\u0000\u0000\u017a\u017b\u0005\u0016\u0000\u0000"+
		"\u017b\u017c\u00057\u0000\u0000\u017c\u017d\u0005#\u0000\u0000\u017d\u017e"+
		"\u0003B!\u0000\u017e\u017f\u0005\u0017\u0000\u0000\u017f\u0180\u0003B"+
		"!\u0000\u0180\u0184\u0005\u0018\u0000\u0000\u0181\u0183\u0003\"\u0011"+
		"\u0000\u0182\u0181\u0001\u0000\u0000\u0000\u0183\u0186\u0001\u0000\u0000"+
		"\u0000\u0184\u0182\u0001\u0000\u0000\u0000\u0184\u0185\u0001\u0000\u0000"+
		"\u0000\u0185\u0187\u0001\u0000\u0000\u0000\u0186\u0184\u0001\u0000\u0000"+
		"\u0000\u0187\u0188\u0005\u0019\u0000\u0000\u01887\u0001\u0000\u0000\u0000"+
		"\u0189\u018a\u0005\u001a\u0000\u0000\u018a\u018b\u0003X,\u0000\u018b\u018f"+
		"\u0005\u0018\u0000\u0000\u018c\u018e\u0003\"\u0011\u0000\u018d\u018c\u0001"+
		"\u0000\u0000\u0000\u018e\u0191\u0001\u0000\u0000\u0000\u018f\u018d\u0001"+
		"\u0000\u0000\u0000\u018f\u0190\u0001\u0000\u0000\u0000\u0190\u0192\u0001"+
		"\u0000\u0000\u0000\u0191\u018f\u0001\u0000\u0000\u0000\u0192\u0193\u0005"+
		"\u001b\u0000\u0000\u01939\u0001\u0000\u0000\u0000\u0194\u0198\u0005\u0018"+
		"\u0000\u0000\u0195\u0197\u0003\"\u0011\u0000\u0196\u0195\u0001\u0000\u0000"+
		"\u0000\u0197\u019a\u0001\u0000\u0000\u0000\u0198\u0196\u0001\u0000\u0000"+
		"\u0000\u0198\u0199\u0001\u0000\u0000\u0000\u0199\u019b\u0001\u0000\u0000"+
		"\u0000\u019a\u0198\u0001\u0000\u0000\u0000\u019b\u019c\u0005\u0017\u0000"+
		"\u0000\u019c\u019d\u0003X,\u0000\u019d;\u0001\u0000\u0000\u0000\u019e"+
		"\u01a0\u0005,\u0000\u0000\u019f\u019e\u0001\u0000\u0000\u0000\u019f\u01a0"+
		"\u0001\u0000\u0000\u0000\u01a0\u01a1\u0001\u0000\u0000\u0000\u01a1\u01a2"+
		"\u0003\n\u0005\u0000\u01a2\u01a3\u0005#\u0000\u0000\u01a3\u01a4\u0003"+
		"X,\u0000\u01a4=\u0001\u0000\u0000\u0000\u01a5\u01a6\u00057\u0000\u0000"+
		"\u01a6\u01a7\u0005.\u0000\u0000\u01a7\u01ac\u0003X,\u0000\u01a8\u01a9"+
		"\u00052\u0000\u0000\u01a9\u01ab\u0003X,\u0000\u01aa\u01a8\u0001\u0000"+
		"\u0000\u0000\u01ab\u01ae\u0001\u0000\u0000\u0000\u01ac\u01aa\u0001\u0000"+
		"\u0000\u0000\u01ac\u01ad\u0001\u0000\u0000\u0000\u01ad\u01af\u0001\u0000"+
		"\u0000\u0000\u01ae\u01ac\u0001\u0000\u0000\u0000\u01af\u01b0\u0005/\u0000"+
		"\u0000\u01b0?\u0001\u0000\u0000\u0000\u01b1\u01b2\u0005\u001c\u0000\u0000"+
		"\u01b2\u01b3\u0003X,\u0000\u01b3A\u0001\u0000\u0000\u0000\u01b4\u01ba"+
		"\u0003D\"\u0000\u01b5\u01b6\u0003H$\u0000\u01b6\u01b7\u0003D\"\u0000\u01b7"+
		"\u01b9\u0001\u0000\u0000\u0000\u01b8\u01b5\u0001\u0000\u0000\u0000\u01b9"+
		"\u01bc\u0001\u0000\u0000\u0000\u01ba\u01b8\u0001\u0000\u0000\u0000\u01ba"+
		"\u01bb\u0001\u0000\u0000\u0000\u01bbC\u0001\u0000\u0000\u0000\u01bc\u01ba"+
		"\u0001\u0000\u0000\u0000\u01bd\u01c3\u0003F#\u0000\u01be\u01bf\u0003J"+
		"%\u0000\u01bf\u01c0\u0003F#\u0000\u01c0\u01c2\u0001\u0000\u0000\u0000"+
		"\u01c1\u01be\u0001\u0000\u0000\u0000\u01c2\u01c5\u0001\u0000\u0000\u0000"+
		"\u01c3\u01c1\u0001\u0000\u0000\u0000\u01c3\u01c4\u0001\u0000\u0000\u0000"+
		"\u01c4E\u0001\u0000\u0000\u0000\u01c5\u01c3\u0001\u0000\u0000\u0000\u01c6"+
		"\u01cc\u0003N\'\u0000\u01c7\u01c8\u0003L&\u0000\u01c8\u01c9\u0003N\'\u0000"+
		"\u01c9\u01cb\u0001\u0000\u0000\u0000\u01ca\u01c7\u0001\u0000\u0000\u0000"+
		"\u01cb\u01ce\u0001\u0000\u0000\u0000\u01cc\u01ca\u0001\u0000\u0000\u0000"+
		"\u01cc\u01cd\u0001\u0000\u0000\u0000\u01cdG\u0001\u0000\u0000\u0000\u01ce"+
		"\u01cc\u0001\u0000\u0000\u0000\u01cf\u01d0\u0005%\u0000\u0000\u01d0I\u0001"+
		"\u0000\u0000\u0000\u01d1\u01d2\u0005&\u0000\u0000\u01d2K\u0001\u0000\u0000"+
		"\u0000\u01d3\u01d4\u0005\'\u0000\u0000\u01d4M\u0001\u0000\u0000\u0000"+
		"\u01d5\u01d7\u00034\u001a\u0000\u01d6\u01d5\u0001\u0000\u0000\u0000\u01d6"+
		"\u01d7\u0001\u0000\u0000\u0000\u01d7\u01d8\u0001\u0000\u0000\u0000\u01d8"+
		"\u01db\u0003P(\u0000\u01d9\u01db\u0003R)\u0000\u01da\u01d6\u0001\u0000"+
		"\u0000\u0000\u01da\u01d9\u0001\u0000\u0000\u0000\u01dbO\u0001\u0000\u0000"+
		"\u0000\u01dc\u01de\u0005,\u0000\u0000\u01dd\u01dc\u0001\u0000\u0000\u0000"+
		"\u01dd\u01de\u0001\u0000\u0000\u0000\u01de\u01df\u0001\u0000\u0000\u0000"+
		"\u01df\u01f3\u0003\n\u0005\u0000\u01e0\u01e1\u00057\u0000\u0000\u01e1"+
		"\u01e2\u0005.\u0000\u0000\u01e2\u01e7\u0003X,\u0000\u01e3\u01e4\u0005"+
		"2\u0000\u0000\u01e4\u01e6\u0003X,\u0000\u01e5\u01e3\u0001\u0000\u0000"+
		"\u0000\u01e6\u01e9\u0001\u0000\u0000\u0000\u01e7\u01e5\u0001\u0000\u0000"+
		"\u0000\u01e7\u01e8\u0001\u0000\u0000\u0000\u01e8\u01ea\u0001\u0000\u0000"+
		"\u0000\u01e9\u01e7\u0001\u0000\u0000\u0000\u01ea\u01eb\u0005/\u0000\u0000"+
		"\u01eb\u01f3\u0001\u0000\u0000\u0000\u01ec\u01f3\u00055\u0000\u0000\u01ed"+
		"\u01f3\u00056\u0000\u0000\u01ee\u01ef\u0005.\u0000\u0000\u01ef\u01f0\u0003"+
		"X,\u0000\u01f0\u01f1\u0005/\u0000\u0000\u01f1\u01f3\u0001\u0000\u0000"+
		"\u0000\u01f2\u01dd\u0001\u0000\u0000\u0000\u01f2\u01e0\u0001\u0000\u0000"+
		"\u0000\u01f2\u01ec\u0001\u0000\u0000\u0000\u01f2\u01ed\u0001\u0000\u0000"+
		"\u0000\u01f2\u01ee\u0001\u0000\u0000\u0000\u01f3Q\u0001\u0000\u0000\u0000"+
		"\u01f4\u01f5\u0005-\u0000\u0000\u01f5\u01f8\u0003\n\u0005\u0000\u01f6"+
		"\u01f8\u00058\u0000\u0000\u01f7\u01f4\u0001\u0000\u0000\u0000\u01f7\u01f6"+
		"\u0001\u0000\u0000\u0000\u01f8S\u0001\u0000\u0000\u0000\u01f9\u01fd\u0003"+
		"B!\u0000\u01fa\u01fb\u0003V+\u0000\u01fb\u01fc\u0003B!\u0000\u01fc\u01fe"+
		"\u0001\u0000\u0000\u0000\u01fd\u01fa\u0001\u0000\u0000\u0000\u01fd\u01fe"+
		"\u0001\u0000\u0000\u0000\u01feU\u0001\u0000\u0000\u0000\u01ff\u0200\u0005"+
		"$\u0000\u0000\u0200W\u0001\u0000\u0000\u0000\u0201\u0207\u0003Z-\u0000"+
		"\u0202\u0203\u0003`0\u0000\u0203\u0204\u0003Z-\u0000\u0204\u0206\u0001"+
		"\u0000\u0000\u0000\u0205\u0202\u0001\u0000\u0000\u0000\u0206\u0209\u0001"+
		"\u0000\u0000\u0000\u0207\u0205\u0001\u0000\u0000\u0000\u0207\u0208\u0001"+
		"\u0000\u0000\u0000\u0208Y\u0001\u0000\u0000\u0000\u0209\u0207\u0001\u0000"+
		"\u0000\u0000\u020a\u0210\u0003\\.\u0000\u020b\u020c\u0003b1\u0000\u020c"+
		"\u020d\u0003\\.\u0000\u020d\u020f\u0001\u0000\u0000\u0000\u020e\u020b"+
		"\u0001\u0000\u0000\u0000\u020f\u0212\u0001\u0000\u0000\u0000\u0210\u020e"+
		"\u0001\u0000\u0000\u0000\u0210\u0211\u0001\u0000\u0000\u0000\u0211[\u0001"+
		"\u0000\u0000\u0000\u0212\u0210\u0001\u0000\u0000\u0000\u0213\u0215\u0005"+
		"*\u0000\u0000\u0214\u0213\u0001\u0000\u0000\u0000\u0214\u0215\u0001\u0000"+
		"\u0000\u0000\u0215\u0216\u0001\u0000\u0000\u0000\u0216\u0217\u0003^/\u0000"+
		"\u0217]\u0001\u0000\u0000\u0000\u0218\u021c\u0005!\u0000\u0000\u0219\u021c"+
		"\u0005\"\u0000\u0000\u021a\u021c\u0003T*\u0000\u021b\u0218\u0001\u0000"+
		"\u0000\u0000\u021b\u0219\u0001\u0000\u0000\u0000\u021b\u021a\u0001\u0000"+
		"\u0000\u0000\u021c_\u0001\u0000\u0000\u0000\u021d\u021e\u0005(\u0000\u0000"+
		"\u021ea\u0001\u0000\u0000\u0000\u021f\u0220\u0005)\u0000\u0000\u0220c"+
		"\u0001\u0000\u0000\u00009lq\u0080\u0087\u0092\u009d\u00a2\u00a8\u00ab"+
		"\u00b5\u00be\u00c4\u00ca\u00d2\u00da\u00e0\u00e5\u00e8\u00ef\u00fa\u0100"+
		"\u0106\u0113\u0118\u011e\u0123\u012f\u013a\u0141\u0144\u0150\u0153\u015a"+
		"\u0162\u016a\u016e\u0173\u0176\u0184\u018f\u0198\u019f\u01ac\u01ba\u01c3"+
		"\u01cc\u01d6\u01da\u01dd\u01e7\u01f2\u01f7\u01fd\u0207\u0210\u0214\u021b";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}