import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/*
 * Executa em sequência:
 * Análise léxica  — detecta tokens inválidos (ERRO_CADEIA, ERRO_COMENTARIO, ERRO_SIMBOLO)
 * Análise sintática — detecta erros de estrutura gramatical
 * Análise semântica — detecta erros de tipos, declarações e compatibilidade
 * Geração de código — produz código C equivalente a partir da árvore sintática
 */
public class main{

    public static void main(String[] args){
        if (args.length != 2){
            System.err.println("Uso: java Main <arquivo_entrada> <arquivo_saida>");
            System.exit(1);
        }

        String entrada = args[0];
        String saida = args[1];

        try{
            // Leitura do arquivo de entrada
            String conteudo = new String(Files.readAllBytes(Paths.get(entrada)));
            CharStream stream = CharStreams.fromString(conteudo);

            // Análise léxica
            LALexer lexer = new LALexer(stream);
            CommonTokenStream tokens = new CommonTokenStream(lexer);

            // Verificar se há tokens de erro léxico
            tokens.fill(); // força a tokenização completa
            boolean erroLexico = false;
            for (Token t : tokens.getTokens()){
                int tipo = t.getType();
                if (tipo == LALexer.ERRO_CADEIA || tipo == LALexer.ERRO_COMENTARIO || tipo == LALexer.ERRO_SIMBOLO){
                    // Mensagem de erro léxico formatada
                    String msg = "Linha " + t.getLine() + ": ";
                    if (tipo == LALexer.ERRO_CADEIA){
                        msg += "cadeia literal nao fechada";
                    } else if (tipo == LALexer.ERRO_COMENTARIO){
                        msg += "comentario nao fechado";
                    } else{ // ERRO_SIMBOLO
                        msg += "simbolo nao identificado: " + t.getText();
                    }
                    escreveErro(saida, msg);
                    return;
                }
            }

            // Análise sintática
            LAParser parser = new LAParser(tokens);
            parser.removeErrorListeners();
            parser.addErrorListener(new BaseErrorListener(){
                @Override
                public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                                        int line, int charPositionInLine, String msg,
                                        RecognitionException e){
                    throw new RuntimeException("Linha " + line + ": erro sintatico proximo a " +
                            ((Token) offendingSymbol).getText());
                }
            });

            ParseTree tree;
            try{
                tree = parser.programa();
            } catch (RuntimeException e){
                escreveErro(saida, e.getMessage());
                return;
            }

            // Análise semântica
            LASemantico semantico = new LASemantico();
            semantico.visit(tree);

            if (!LASemanticoUtils.errosSemanticos.isEmpty()){
                StringBuilder sb = new StringBuilder();
                for (String erro : LASemanticoUtils.errosSemanticos){
                    sb.append(erro).append("\n");
                }
                escreveErro(saida, sb.toString());
                return;
            }

            // Geração de código (T5): passando os escopos preenchidos pelo semântico
            LAGeradorCodigo gerador = new LAGeradorCodigo(semantico.getEscopos());
            gerador.visit(tree);
            String codigoC = gerador.getCodigo();

            // Escreve código C no arquivo de saída
            Files.write(Paths.get(saida), codigoC.getBytes());

        } catch (IOException e){
            System.err.println("Erro de I/O: " + e.getMessage());
            System.exit(1);
        } catch (Exception e){
            // Outros erros (ex: exceções não tratadas)
            escreveErro(saida, e.getMessage());
        }
    }

    private static void escreveErro(String saida, String mensagem){
        try{
            Files.write(Paths.get(saida), mensagem.getBytes());
        } catch (IOException ex){
            System.err.println("Erro ao escrever arquivo de saída: " + ex.getMessage());
        }
    }
}