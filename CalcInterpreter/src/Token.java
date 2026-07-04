/**
 * Represents a single lexical token produced by the Lexer.
 * A token has a type (what kind of symbol it is), the raw text it came from,
 * and, for NUMBER tokens, the parsed numeric value.
 */
public class Token {

    public enum Type {
        NUMBER, IDENTIFIER, PLUS, MINUS, STAR, SLASH,
        LPAREN, RPAREN, ASSIGN, EOF
    }

    private final Type type;
    private final String text;
    private final double value;

    public Token(Type type, String text) {
        this(type, text, 0);
    }

    public Token(Type type, String text, double value) {
        this.type = type;
        this.text = text;
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return type + "(" + text + ")";
    }
}
