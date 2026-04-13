import org.antlr.v4.runtime.*;
import java.io.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.BailErrorStrategy;



/**
 * Classe principal para execução do Analisador Léxico e Sintático da Linguagem LA.
 * O objetivo é ler um arquivo fonte e gerar a lista de tokens (T1) ou reportar
 * o primeiro erro léxico ou sintático encontrado (T2).
 * 
 * Para o T2, se o programa estiver sintaticamente correto, o arquivo de saída
 * permanece vazio.
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
            
            // --- FASE 1: VERIFICAÇÃO DE ERROS LÉXICOS (T1) ---
            //Cria um fluxo de tokens a partir do lexer
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            
            //Preenche a lista de tokens para podermos inspecioná-los
            tokens.fill();
            
            //Percorre todos os tokens em busca de tokens de erro
            for (Token t : tokens.getTokens()) {
                //Para ao chegar no fim do arquivo
                if (t.getType() == Token.EOF) {
                    break;
                }
                
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
                    pw.println("Linha " + t.getLine() + ": cadeia literal nao fechada");
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
            }
            
            // --- FASE 2: ANÁLISE SINTÁTICA (T2) ---
            //Se chegou até aqui, não houve erro léxico. 
            //Precisamos reiniciar o lexer para que o parser possa ler os tokens do início.
            cs = CharStreams.fromFileName(arquivoEntrada);
            lexer = new LALexer(cs);
            tokens = new CommonTokenStream(lexer);
            
            //Instancia o Parser gerado a partir do LAParser.g4
            LAParser parser = new LAParser(tokens);
            
            //Remove os ouvintes de erro padrão do ANTLR (que imprimem no console)
            parser.removeErrorListeners();
            
            //Adiciona nosso próprio tratador de erros sintáticos
            parser.addErrorListener(new BaseErrorListener() {
                @Override
                public void syntaxError(Recognizer<?, ?> recognizer,
                                        Object offendingSymbol,
                                        int line, int charPositionInLine,
                                        String msg, RecognitionException e) {
                    //Obtém o lexema que causou o erro
                    String lexema = "";
                    if (offendingSymbol instanceof Token) {
                        lexema = ((Token) offendingSymbol).getText();
                        //Se for EOF, o corretor espera que apareça "EOF" na mensagem
                        if (lexema.equals("<EOF>") || ((Token) offendingSymbol).getType() == Token.EOF) {
                            lexema = "EOF";
                        }
                    }
                    
                    //Escreve a mensagem de erro sintático no formato exigido
                    pw.println("Linha " + line + ": erro sintatico proximo a " + lexema);
                    pw.println("Fim da compilacao");
                    pw.flush();
                    
                    //Lança uma exceção para interromper o parsing imediatamente
                    throw new ParseCancellationException("erro sintatico");
                }
                
            });
            
            try {
                //Tenta fazer o parsing a partir da regra inicial 'programa'
                parser.programa();
                //Se chegou aqui, o programa está sintaticamente correto.
                //O arquivo de saída permanece vazio (comportamento esperado para o T2).
            } catch (ParseCancellationException e) {
                //Erro sintático já foi tratado pelo listener acima.
                //Apenas encerramos silenciosamente.
                return;
            }
            
            //Garante que todo o buffer de escrita seja enviado ao disco
            pw.flush();
        } catch (IOException e) {
            System.err.println("Erro ao processar arquivos: " + e.getMessage());
        }
    }
}