import java.util.LinkedList;
import java.util.Queue;

/**
 * Converts a raw input line into a Queue of Tokens.
 *
 * DATA STRUCTURE FOCUS: Queue (implemented with LinkedList)
 * Tokens are naturally consumed in the same order they appear in the
 * source line (FIFO), which is exactly what a Queue models. The
 * Interpreter later polls tokens off the front of this queue one at a time.
 */
public class Lexer {

    public Queue<Token> tokenize(String line) {
        Queue<Token> tokens = new LinkedList<>();
        int i = 0;
        int n = line.length();

        while (i < n) {
            char c = line.charAt(i);

            if (Character.isWhitespace(c)) {
                i++;
                continue;
            }

            if (Character.isDigit(c) || c == '.') {
                int start = i;
                while (i < n && (Character.isDigit(line.charAt(i)) || line.charAt(i) == '.')) {
                    i++;
                }
                String numText = line.substring(start, i);
                tokens.add(new Token(Token.Type.NUMBER, numText, Double.parseDouble(numText)));
                continue;
            }

            if (Character.isLetter(c)) {
                int start = i;
                while (i < n && Character.isLetterOrDigit(line.charAt(i))) {
                    i++;
                }
                String idText = line.substring(start, i);
                tokens.add(new Token(Token.Type.IDENTIFIER, idText));
                continue;
            }

            switch (c) {
                case '+':
                    tokens.add(new Token(Token.Type.PLUS, "+"));
                    i++;
                    break;
                case '-':
                    tokens.add(new Token(Token.Type.MINUS, "-"));
                    i++;
                    break;
                case '*':
                    tokens.add(new Token(Token.Type.STAR, "*"));
                    i++;
                    break;
                case '/':
                    tokens.add(new Token(Token.Type.SLASH, "/"));
                    i++;
                    break;
                case '(':
                    tokens.add(new Token(Token.Type.LPAREN, "("));
                    i++;
                    break;
                case ')':
                    tokens.add(new Token(Token.Type.RPAREN, ")"));
                    i++;
                    break;
                case '=':
                    tokens.add(new Token(Token.Type.ASSIGN, "="));
                    i++;
                    break;
                default:
                    throw new RuntimeException("Unexpected character: '" + c + "'");
            }
        }

        tokens.add(new Token(Token.Type.EOF, ""));
        return tokens;
    }
}
