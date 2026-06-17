import org.antlr.v4.runtime.*;
import java.io.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;

/**
 * Executa em sequência:
 * Análise léxica  — detecta tokens inválidos (ERRO_CADEIA, ERRO_COMENTARIO, ERRO_SIMBOLO)
 * Análise sintática — detecta erros de estrutura gramatical
 * Análise semântica — detecta erros de tipos, declarações e compatibilidade
 */
public class main {

    public static void main(String[] args) {
        // Validação dos argumentos obrigatórios
        if (args.length < 2) {
            System.err.println("Uso: java -jar compilador.jar entrada.txt saida.txt");
            return;
        }

        String arquivoEntrada = args[0];
        String arquivoSaida   = args[1];

        try (PrintWriter pw = new PrintWriter(new FileWriter(arquivoSaida))) {

            // Começo da análise léxica
            CharStream cs = CharStreams.fromFileName(arquivoEntrada);
            LALexer lexer = new LALexer(cs);

            // Remove o listener padrão do ANTLR (evita mensagens no stderr)
            lexer.removeErrorListeners();

            CommonTokenStream tokens = new CommonTokenStream(lexer);
            tokens.fill();

            for (Token t : tokens.getTokens()) {
                if (t.getType() == Token.EOF) break;

                String nomeToken = LALexer.VOCABULARY.getSymbolicName(t.getType());

                if ("ERRO_CADEIA".equals(nomeToken)) {
                    pw.println("Linha " + t.getLine() + ": cadeia literal nao fechada");
                    pw.println("Fim da compilacao");
                    pw.flush();
                    return;
                }

                if ("ERRO_COMENTARIO".equals(nomeToken)) {
                    pw.println("Linha " + t.getLine() + ": comentario nao fechado");
                    pw.println("Fim da compilacao");
                    pw.flush();
                    return;
                }

                if ("ERRO_SIMBOLO".equals(nomeToken)) {
                    pw.println("Linha " + t.getLine() + ": " + t.getText() + " - simbolo nao identificado");
                    pw.println("Fim da compilacao");
                    pw.flush();
                    return;
                }
            }

            // Começo da análise sintática

            // Reinicia o lexer para o parser ler os tokens desde o início
            cs = CharStreams.fromFileName(arquivoEntrada);
            lexer = new LALexer(cs);
            lexer.removeErrorListeners();
            tokens = new CommonTokenStream(lexer);

            LAParser parser = new LAParser(tokens);
            parser.removeErrorListeners();

            // Flag para saber se houve erro sintático
            final boolean[] erroSintatico = {false};

            parser.addErrorListener(new BaseErrorListener() {
                @Override
                public void syntaxError(Recognizer<?, ?> recognizer,
                                        Object offendingSymbol,
                                        int line, int charPositionInLine,
                                        String msg, RecognitionException e) {
                    String lexema = "";
                    if (offendingSymbol instanceof Token) {
                        Token tok = (Token) offendingSymbol;
                        lexema = tok.getText();
                        if ("<EOF>".equals(lexema) || tok.getType() == Token.EOF) {
                            lexema = "EOF";
                        }
                    }

                    pw.println("Linha " + line + ": erro sintatico proximo a " + lexema);
                    pw.println("Fim da compilacao");
                    pw.flush();

                    erroSintatico[0] = true;
                    throw new ParseCancellationException("erro sintatico");
                }
            });

            LAParser.ProgramaContext arvore;
            try {
                // Faz o parse e guarda a árvore para reutilizar na fase 3
                arvore = parser.programa();
            } catch (ParseCancellationException e) {
                // Erro sintático já foi escrito no arquivo de saída
                return;
            }

            if (erroSintatico[0]) {
                return;
            }

            // Começo da análise semântica

            // Limpa erros de execuções anteriores (importante em testes)
            LASemanticoUtils.limpar();

            // Visita a árvore sintática coletando os erros semânticos
            LASemantico semantico = new LASemantico();
            semantico.visit(arvore);

            // Escreve todos os erros encontrados
            for (String erro : LASemanticoUtils.errosSemanticos) {
                pw.println(erro);
            }

            // "Fim da compilacao" é sempre impresso, mesmo sem erros
            pw.println("Fim da compilacao");
            pw.flush();

        } catch (IOException e) {
            System.err.println("Erro ao processar arquivos: " + e.getMessage());
        }
    }
}