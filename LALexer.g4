lexer grammar LALexer;

/* * GRUPO 1: PALAVRAS-CHAVE 
 * Estas regras têm prioridade sobre IDENT porque aparecem primeiro.
 * Devem ser escritas exatamente como na especificação da linguagem LA.
 */
ALGORITMO      : 'algoritmo';
FIM_ALGORITMO  : 'fim_algoritmo';
DECLARE        : 'declare';
CONSTANTE      : 'constante';
TIPO           : 'tipo';
REGISTRO       : 'registro';
FIM_REGISTRO   : 'fim_registro';
PROCEDIMENTO   : 'procedimento';
FIM_PROCEDIMENTO : 'fim_procedimento';
FUNCAO         : 'funcao';
FIM_FUNCAO     : 'fim_funcao';
VAR            : 'var';
LEIA           : 'leia';
ESCREVA        : 'escreva';
SE             : 'se';
ENTAO          : 'entao';
SENAO          : 'senao';
FIM_SE         : 'fim_se';
CASO           : 'caso';
SEJA           : 'seja';
FIM_CASO       : 'fim_caso';
PARA           : 'para';
ATE            : 'ate';
FACA           : 'faca';
FIM_PARA       : 'fim_para';
ENQUANTO       : 'enquanto';
FIM_ENQUANTO   : 'fim_enquanto';
RETORNE        : 'retorne';

/* GRUPO 2: TIPOS BÁSICOS E VALORES LÓGICOS */
LITERAL        : 'literal';
INTEIRO        : 'inteiro';
REAL           : 'real';
LOGICO         : 'logico';
VERDADEIRO     : 'verdadeiro';
FALSO          : 'falso';

/* GRUPO 3: OPERADORES E SÍMBOLOS DE PONTUAÇÃO */
OP_ATRIBUICAO  : '<-';
OP_REL         : '=' | '<>' | '>=' | '<=' | '>' | '<' ;
OP_ARIT_1      : '+' | '-' ;
OP_ARIT_2      : '*' | '/' ;
OP_ARIT_3      : '%' ;
OP_LOGICO_1    : 'ou' ;
OP_LOGICO_2    : 'e' ;
NAO            : 'nao' ;
PONTO_PONTO    : '..' ;
PONTEIRO       : '^' ;
ENDERECO       : '&' ;
LPAR           : '(';
RPAR           : ')';
LBRACK         : '[';
RBRACK         : ']';
COMMA          : ',';
COLON          : ':';
DOT            : '.';

/* GRUPO 4: REGRAS DINÂMICAS (IDENTIFICADORES E LITERAIS) */
NUM_INT        : [0-9]+ ;
NUM_REAL       : [0-9]+ '.' [0-9]+ ;
// IDENT deve vir após as palavras-chave para não as "engolir"
IDENT          : [a-zA-Z_] [a-zA-Z0-9_]* ;
// CADEIA captura texto entre aspas duplas, permitindo escape de caracteres
CADEIA         : '"' ( ~["\r\n\\] | '\\' . )* '"' ;

/* GRUPO 5: ELEMENTOS IGNORADOS PELO ANALISADOR */
// WS: Ignora espaços, tabs e quebras de linha enviando para o canal skip
WS             : [ \t\r\n]+ -> skip ;
// COMENTARIO: Ignora blocos entre { } de forma não-gulosa (.*?)
COMENTARIO : '{' ( ~'}' )* '}' -> skip ;

/* * GRUPO 6: TRATAMENTO DE ERROS LÉXICOS 
 * Estas regras devem ser as ÚLTIMAS. Se nenhum token acima casar,
 * o ANTLR cairá nestas regras de erro.
 */
// ERRO_CADEIA: Pega uma aspa que não foi fechada até o fim da linha/arquivo
ERRO_CADEIA    : '"' ( ~["\r\n\\] | '\\' . )* ; 
// ERRO_COMENTARIO: Pega uma chave que não foi fechada
ERRO_COMENTARIO : '{' ( ~'}' )* ;

// ERRO_SIMBOLO: Qualquer caractere único não previsto nas regras acima
ERRO_SIMBOLO   : . ;