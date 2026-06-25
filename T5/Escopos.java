import java.util.LinkedList;
import java.util.List;

/*
 * GERENCIADOR DE PILHA DE ESCOPOS
 * 
 * A linguagem LA permite escopos aninhados (procedimentos e funções).
 * Esta classe mantém uma pilha de tabelas de símbolos; o topo é o escopo
 * mais interno. A busca por um símbolo percorre do topo até a base (global).
 */
public class Escopos {

    // Pilha de tabelas (topo = escopo atual)
    private final LinkedList<TabelaDeSimbolos> pilhaEscopos;

    public Escopos() {
        pilhaEscopos = new LinkedList<>();
        criarNovoEscopo(); // escopo global
    }

    // Empilha um novo escopo vazio.
    public void criarNovoEscopo() {
        pilhaEscopos.push(new TabelaDeSimbolos());
    }

    // Remove o escopo atual (desempilha). 
    public void abandonarEscopo() {
        pilhaEscopos.pop();
    }

    // Retorna a tabela do escopo atual (topo).
    public TabelaDeSimbolos obterEscopoAtual() {
        return pilhaEscopos.peek();
    }

    // Retorna a lista de todos os escopos (do mais interno ao mais externo).
    public List<TabelaDeSimbolos> percorrerEscoposAninhados() {
        return pilhaEscopos;
    }

    // Verifica se um identificador existe em algum escopo visível, percorrendo do atual até o global.
    public boolean existeEmAlgumEscopo(String nome) {
        for (TabelaDeSimbolos tabela : pilhaEscopos) {
            if (tabela.existe(nome)) {
                return true;
            }
        }
        return false;
    }

    // Busca a entrada de um identificador no escopo mais interno que o contém, ou retorna null se não for encontrado em nenhum.
    public TabelaDeSimbolos.EntradaSimbolo buscarEmEscopos(String nome) {
        for (TabelaDeSimbolos tabela : pilhaEscopos) {
            TabelaDeSimbolos.EntradaSimbolo entrada = tabela.verificar(nome);
            if (entrada != null) {
                return entrada;
            }
        }
        return null;
    }
}