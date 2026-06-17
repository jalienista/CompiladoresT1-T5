import java.util.HashMap;
import java.util.Map;

/*
 * TABELA DE SÍMBOLOS PARA UM ESCOPO DA LINGUAGEM LA
 * 
 * Armazena os identificadores declarados em um determinado escopo,
 * associando cada nome a uma entrada que contém o tipo e, opcionalmente,
 * o nome do tipo definido pelo usuário.
 */
public class TabelaDeSimbolos {
    
    /*
     * Enumeração com todas as categorias de símbolos que podem ser
     * armazenadas na tabela.
     */
    public enum TipoLA {
        INTEIRO,        // tipo numérico inteiro
        REAL,           // tipo numérico real
        LITERAL,        // tipo cadeia de caracteres
        LOGICO,         // tipo booleano (verdadeiro/falso)
        REGISTRO,       // variável cujo tipo é um registro definido pelo usuário
        PONTEIRO,       // variável declarada com o modificador '^'
        TIPO_USUARIO,   // declaração "tipo Nome : ..." (armazena a definição)
        PROCEDIMENTO,   // procedimento (sem retorno)
        FUNCAO,         // função (com retorno)
        INVALIDO        // usado para sinalizar erros semânticos
    }
    
    /*
     * Representa uma linha da tabela, contendo o tipo do símbolo e,
     * quando pertinente, o nome do tipo declarado pelo usuário.
     * 
     * Exemplos:
     *   "declare x : inteiro"        -> tipo=INTEIRO, nomeTipo=null
     *   "declare y : MeuReg"         -> tipo=REGISTRO, nomeTipo="MeuReg"
     *   "tipo MeuReg : registro..."  -> tipo=TIPO_USUARIO, nomeTipo="registro..."
     *   "funcao f() : inteiro"       -> tipo=FUNCAO, nomeTipo="inteiro"
     */
    public static class EntradaSimbolo {
        public final TipoLA tipo;
        public final String nomeTipo; // pode ser null

        public EntradaSimbolo(TipoLA tipo, String nomeTipo) {
            this.tipo = tipo;
            this.nomeTipo = nomeTipo;
        }

        public EntradaSimbolo(TipoLA tipo) {
            this(tipo, null);
        }
    }

    // Mapeamento nome → entrada
    private final Map<String, EntradaSimbolo> tabela;

    public TabelaDeSimbolos() {
        tabela = new HashMap<>();
    }

    // Adiciona um símbolo com tipo e nome de tipo (opcional).
    public void adicionar(String nome, TipoLA tipo, String nomeTipo) {
        tabela.put(nome, new EntradaSimbolo(tipo, nomeTipo));
    }

    public void adicionar(String nome, TipoLA tipo) {
        adicionar(nome, tipo, null);
    }

    // Verifica se o símbolo existe neste escopo (apenas o escopo atual).
    public boolean existe(String nome) {
        return tabela.containsKey(nome);
    }

    // Retorna a entrada completa, ou null se não existir.
    public EntradaSimbolo verificar(String nome) {
        return tabela.get(nome);
    }

    // Retorna apenas o TipoLA do símbolo, ou INVALIDO se não existir.
    public TipoLA verificarTipo(String nome) {
        EntradaSimbolo entrada = tabela.get(nome);
        return (entrada != null) ? entrada.tipo : TipoLA.INVALIDO;
    }
}