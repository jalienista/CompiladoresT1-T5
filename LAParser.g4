parser grammar LAParser;

options {
    tokenVocab = LALexer;   // Utiliza os tokens definidos no analisador léxico
}

// REGRAS SINTÁTICAS DA LINGUAGEM LA

// Ponto de entrada do programa
programa : declaracoes ALGORITMO corpo FIM_ALGORITMO ;

// Declarações podem ser locais ou globais (zero ou mais)
declaracoes : decl_local_global* ;

decl_local_global : declaracao_local | declaracao_global ;

// Declarações dentro de escopos (algoritmo, procedimentos, funções)
declaracao_local
    : DECLARE variavel                                                   # declaracaoVariavel
    | CONSTANTE IDENT COLON tipo_basico OP_REL? valor_constante          # declaracaoConstante  // OP_REL? para o sinal de '=', que a gramática chama de "="
    | TIPO IDENT COLON tipo                                              # declaracaoTipo
    ;

// Lista de variáveis seguida de seu tipo
variavel : identificador ( COMMA identificador )* COLON tipo ;

// Um ou mais identificadores, opcionalmente seguidos de dimensões (arrays)
identificador : IDENT ( COMMA IDENT )* dimensao ;

// Dimensões são pares de colchetes com expressões aritméticas (tamanho do array)
dimensao : ( LBRACK exp_aritmetica RBRACK )* ;

// Tipos de dados
tipo : registro | tipo_estendido ;

tipo_basico : LITERAL | INTEIRO | REAL | LOGICO ;

tipo_basico_ident : tipo_basico | IDENT ;

// Tipo estendido pode incluir o modificador '^' (ponteiro)
tipo_estendido : PONTEIRO? tipo_basico_ident ;

// Valores constantes permitidos
valor_constante : CADEIA | NUM_INT | NUM_REAL | VERDADEIRO | FALSO ;

// Definição de registro
registro : REGISTRO variavel* FIM_REGISTRO ;

// Declarações globais: procedimentos e funções
declaracao_global
    : PROCEDIMENTO IDENT LPAR parametros? RPAR declaracao_local* cmd* FIM_PROCEDIMENTO   # procedimento
    | FUNCAO IDENT LPAR parametros? RPAR COLON tipo_estendido declaracao_local* cmd* FIM_FUNCAO # funcao
    ;

// Parâmetros formais
parametro : VAR? identificador ( COMMA identificador )* COLON tipo_estendido ;

parametros : parametro ( COMMA parametro )* ;

// Corpo do algoritmo (declarações locais seguidas de comandos)
corpo : declaracao_local* cmd* ;

// Comandos da linguagem
cmd
    : cmdLeia | cmdEscreva | cmdSe | cmdCaso | cmdPara | cmdEnquanto
    | cmdFaca | cmdAtribuicao | cmdChamada | cmdRetorne
    ;

cmdLeia : LEIA LPAR ( PONTEIRO? identificador ) ( COMMA PONTEIRO? identificador )* RPAR ;

cmdEscreva : ESCREVA LPAR expressao ( COMMA expressao )* RPAR ;

cmdSe : SE expressao ENTAO cmd* ( SENAO cmd* )? FIM_SE ;

cmdCaso : CASO exp_aritmetica SEJA selecao ( SENAO cmd* )? FIM_CASO ;

selecao : item_selecao* ;

item_selecao : constantes COLON cmd* ;

constantes : numero_intervalo ( COMMA numero_intervalo )* ;

numero_intervalo : op_unario? NUM_INT ( PONTO_PONTO op_unario? NUM_INT )? ;

op_unario : OP_ARIT_1 ;   // O sinal '-' é o único operador unário permitido

cmdPara : PARA IDENT OP_ATRIBUICAO exp_aritmetica ATE exp_aritmetica FACA cmd* FIM_PARA ;

cmdEnquanto : ENQUANTO expressao FACA cmd* FIM_ENQUANTO ;

cmdFaca : FACA cmd* ATE expressao ;

cmdAtribuicao : PONTEIRO? identificador OP_ATRIBUICAO expressao ;

cmdChamada : IDENT LPAR expressao ( COMMA expressao )* RPAR ;

cmdRetorne : RETORNE expressao ;

// Expressões aritméticas (com precedência em cascata)
exp_aritmetica : termo ( op1 termo )* ;

termo : fator ( op2 fator )* ;

fator : parcela ( op3 parcela )* ;

op1 : OP_ARIT_1 ;   // '+' ou '-'
op2 : OP_ARIT_2 ;   // '*' ou '/'
op3 : OP_ARIT_3 ;   // '%'

parcela : op_unario? parcela_unario | parcela_nao_unario ;

parcela_unario
    : PONTEIRO? identificador                     # parcelaIdentificador
    | IDENT LPAR expressao ( COMMA expressao )* RPAR  # parcelaChamadaFuncao
    | NUM_INT                                     # parcelaInteiro
    | NUM_REAL                                    # parcelaReal
    | LPAR expressao RPAR                         # parcelaParenteses
    ;

parcela_nao_unario
    : ENDERECO identificador   # parcelaEndereco
    | CADEIA                   # parcelaCadeia
    ;

// Expressões relacionais
exp_relacional : exp_aritmetica ( op_relacional exp_aritmetica )? ;

op_relacional : OP_REL ;   // '=', '<>', '>=', '<=', '>', '<'

// Expressões lógicas
expressao : termo_logic ( op_logic_1 termo_logic )* ;

termo_logic : fator_logic ( op_logic_2 fator_logic )* ;

fator_logic : NAO? parcela_logic ;

parcela_logic
    : VERDADEIRO   # parcelaLogicaConstante
    | FALSO        # parcelaLogicaConstante
    | exp_relacional # parcelaLogicaRelacional
    ;

op_logic_1 : OP_LOGICO_1 ;   // 'ou'
op_logic_2 : OP_LOGICO_2 ;   // 'e'