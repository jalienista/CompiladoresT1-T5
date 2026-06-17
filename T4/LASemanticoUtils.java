import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.Token;

/*
 * UTILITÁRIOS PARA ANÁLISE SEMÂNTICA
 * 
 * Centraliza o armazenamento das mensagens de erro semântico encontradas
 * durante a visita à árvore sintática. Evita duplicatas exatas.
 */
public class LASemanticoUtils{

    // Lista com todos os erros semânticos reportados.
    public static final List<String> errosSemanticos = new ArrayList<>();

    /*
     * Adiciona um erro semântico, obtendo o número da linha a partir do token.
     * A mensagem é formatada como "Linha X: mensagem".
     * Duplicatas (mesmo texto) são ignoradas.
     */
    public static void adicionarErroSemantico(Token t, String mensagem){
        String erro = "Linha " + t.getLine() + ": " + mensagem;
        if (!errosSemanticos.contains(erro)){
            errosSemanticos.add(erro);
        }
    }

    // Limpa a lista de erros (útil para testes).
    public static void limpar(){
        errosSemanticos.clear();
    }
}