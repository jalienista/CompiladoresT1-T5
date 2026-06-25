import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

/*
 * TABELA DE SÍMBOLOS PARA UM ESCOPO DA LINGUAGEM LA
 * 
 * Armazena os identificadores declarados em um determinado escopo,
 * associando cada nome a uma entrada que contém o tipo e, opcionalmente,
 * o nome do tipo definido pelo usuário.
 */
public class TabelaDeSimbolos{
    
    /*
     * Enumeração com todas as categorias de símbolos que podem ser
     * armazenadas na tabela.
     */
    public enum TipoLA{
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

    // Representa um parâmetro formal de procedimento/função.
    public static class ParametroInfo{
        public final String nome;
        public final TipoLA tipo;
        public final String nomeTipo; // para registros
        public final boolean isVar;

        public ParametroInfo(String nome, TipoLA tipo, String nomeTipo, boolean isVar){
            this.nome = nome;
            this.tipo = tipo;
            this.nomeTipo = nomeTipo;
            this.isVar = isVar;
        }
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
    public static class EntradaSimbolo{
        public final TipoLA tipo;
        public final String nomeTipo; // para registros, retorno de função, etc.
        public final List<ParametroInfo> parametros; // para procedimentos/funções
        public final Map<String, EntradaSimbolo> campos; // para registros

        public EntradaSimbolo(TipoLA tipo, String nomeTipo, List<ParametroInfo> parametros, Map<String, EntradaSimbolo> campos){
            this.tipo = tipo;
            this.nomeTipo = nomeTipo;
            this.parametros = parametros != null ? new ArrayList<>(parametros) : null;
            this.campos = campos != null ? new HashMap<>(campos) : null;
        }

        public EntradaSimbolo(TipoLA tipo, String nomeTipo, List<ParametroInfo> parametros){
            this(tipo, nomeTipo, parametros, null);
        }

        public EntradaSimbolo(TipoLA tipo, String nomeTipo){
            this(tipo, nomeTipo, null, null);
        }

        public EntradaSimbolo(TipoLA tipo){
            this(tipo, null, null, null);
        }
    }

    // Mapeamento nome → entrada
    private final Map<String, EntradaSimbolo> tabela;

    public TabelaDeSimbolos(){
        tabela = new HashMap<>();
    }

    // Adiciona um símbolo com tipo e nome de tipo (opcional).
    public void adicionar(String nome, TipoLA tipo, String nomeTipo, List<ParametroInfo> parametros){
        tabela.put(nome, new EntradaSimbolo(tipo, nomeTipo, parametros));
    }

    public void adicionar(String nome, TipoLA tipo, String nomeTipo){
        adicionar(nome, tipo, nomeTipo, null);
    }

    public void adicionar(String nome, TipoLA tipo){
        adicionar(nome, tipo, null, null);
    }

    public void adicionar(String nome, TipoLA tipo, String nomeTipo, List<ParametroInfo> parametros, Map<String, EntradaSimbolo> campos){
        tabela.put(nome, new EntradaSimbolo(tipo, nomeTipo, parametros, campos));
    }

    // Verifica se o símbolo existe neste escopo (apenas o escopo atual).
    public boolean existe(String nome){
        return tabela.containsKey(nome);
    }

    // Retorna a entrada completa, ou null se não existir.
    public EntradaSimbolo verificar(String nome){
        return tabela.get(nome);
    }

    // Retorna apenas o TipoLA do símbolo, ou INVALIDO se não existir.
    public TipoLA verificarTipo(String nome){
        EntradaSimbolo entrada = tabela.get(nome);
        return (entrada != null) ? entrada.tipo : TipoLA.INVALIDO;
    }

    public Set<Map.Entry<String, EntradaSimbolo>> entradas(){
        return tabela.entrySet();
    }
}