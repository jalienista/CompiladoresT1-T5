import org.antlr.v4.runtime.*;
import java.io.*;

/**
 * Classe principal para execução do Analisador Léxico da Linguagem LA.
 * O objetivo é ler um arquivo fonte e gerar uma lista de tokens ou reportar erro.
 */
public class main {
    public static void main(String[] args) {
        //Verifica se os caminhos de entrada e saída foram fornecidos via CLI
        if (args.length < 2) {
            System.err.println("Uso: java -jar compilador.jar entrada.txt saida.txt");
            return;
        }

        String arquivoEntrada = args[0];
        String arquivoSaida = args[1];

        //PrintWriter é usado para escrever no arquivo de saída de forma formatada
        try (PrintWriter pw = new PrintWriter(new File(arquivoSaida))) {
            //CharStream lê o conteúdo do arquivo de entrada para o ANTLR
            CharStream cs = CharStreams.fromFileName(arquivoEntrada);
            
            //Instancia o Lexer gerado a partir do LALexer.g4
            LALexer lexer = new LALexer(cs);
            Token t;

            //O laço percorre o arquivo até encontrar o Fim de Arquivo (EOF)
            while ((t = lexer.nextToken()).getType() != Token.EOF) {
                //recupera o nome da regra (ex: IDENT, ALGORITMO) definido no .g4
                String nomeToken = LALexer.VOCABULARY.getSymbolicName(t.getType());
                //recupera o texto literal do token no código fonte
                String texto = t.getText();

                /* * TRATAMENTO DE ERROS LÉXICOS (REQUISITO DOS CASOS 33, 35, 36)
                 * Se o token identificado for uma das regras de erro, imprimimos
                 * a mensagem formatada e ENCERRAMOS o programa com 'return'.
                 * O encerramento imediato evita que tokens posteriores sejam impressos,
                 * o que invalidaria o teste conforme os critérios do professor.
                 */
                if (nomeToken.equals("ERRO_CADEIA")) {
                    pw.println("Linha " + t.getLine() + ": cadeia nao fechada");
                    pw.flush();
                    return; //interrompe a compilação no primeiro erro léxico
                } 
                
                if (nomeToken.equals("ERRO_COMENTARIO")) {
                    pw.println("Linha " + t.getLine() + ": comentario nao fechado");
                    pw.flush();
                    return;
                } 
                
                if (nomeToken.equals("ERRO_SIMBOLO")) {
                    pw.println("Linha " + t.getLine() + ": " + texto + " - simbolo nao identificado");
                    pw.flush();
                    return;
                }

                /*
                 * FORMATAÇÃO DE SAÍDA PARA TOKENS VÁLIDOS
                 * Se for IDENT, CADEIA ou NUMEROS, o formato é <'texto', TIPO>
                 * Se for palavra-chave ou símbolo, o formato é <'texto', 'texto'>
                 */
                String tipoExibicao;
                if (nomeToken.equals("IDENT") || nomeToken.equals("CADEIA") || 
                    nomeToken.equals("NUM_INT") || nomeToken.equals("NUM_REAL")) {
                    tipoExibicao = nomeToken;
                } else {
                    //Palavras reservadas e operadores aparecem entre aspas simples
                    tipoExibicao = "'" + texto + "'";
                }

                pw.println("<'" + texto + "'," + tipoExibicao + ">");
            }
            //Garante que todo o buffer de escrita seja enviado ao disco
            pw.flush();
        } catch (IOException e) {
            System.err.println("Erro ao processar arquivos: " + e.getMessage());
        }
    }
}