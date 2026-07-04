import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

/**
 * Evaluates a line of input: either a variable assignment ("x = 3 + 4")
 * or a bare expression ("x * (2 + 1)").
 *
 * DATA STRUCTURE FOCUS:
 *  - Stack<Double>     : holds operand values during evaluation.
 *  - Stack<Token.Type> : holds pending operators, enforcing precedence
 *                        and parenthesis grouping (classic shunting-yard style).
 *  - HashMap<String,Double> : the symbol table mapping variable names to values.
 */
public class Interpreter {

    private final Map<String, Double> variables = new HashMap<>();
    private final Lexer lexer = new Lexer();

    /**
     * Runs one line of input and returns the resulting value.
     */
    public double run(String line) {
        Queue<Token> tokens = lexer.tokenize(line);

        if (isAssignment(tokens)) {
            Token varToken = tokens.poll();  // identifier
            tokens.poll();                   // '=' sign, discarded
            double result = evaluateExpression(tokens);
            variables.put(varToken.getText(), result);
            return result;
        }

        return evaluateExpression(tokens);
    }

    private boolean isAssignment(Queue<Token> tokens) {
        if (tokens.size() < 2) return false;
        Token[] arr = tokens.toArray(new Token[0]);
        return arr[0].getType() == Token.Type.IDENTIFIER && arr[1].getType() == Token.Type.ASSIGN;
    }

    /**
     * Evaluates the remaining tokens as an arithmetic expression using
     * two stacks: one for numeric values, one for pending operators.
     */
    private double evaluateExpression(Queue<Token> tokens) {
        Stack<Double> values = new Stack<>();
        Stack<Token.Type> ops = new Stack<>();

        Token token;
        while ((token = tokens.poll()) != null && token.getType() != Token.Type.EOF) {
            switch (token.getType()) {
                case NUMBER:
                    values.push(token.getValue());
                    break;

                case IDENTIFIER:
                    values.push(lookupVariable(token.getText()));
                    break;

                case LPAREN:
                    ops.push(Token.Type.LPAREN);
                    break;

                case RPAREN:
                    while (!ops.isEmpty() && ops.peek() != Token.Type.LPAREN) {
                        applyOperator(values, ops.pop());
                    }
                    if (!ops.isEmpty()) {
                        ops.pop(); // discard the matching '('
                    }
                    break;

                case PLUS:
                case MINUS:
                case STAR:
                case SLASH:
                    while (!ops.isEmpty() && precedence(ops.peek()) >= precedence(token.getType())) {
                        applyOperator(values, ops.pop());
                    }
                    ops.push(token.getType());
                    break;

                default:
                    throw new RuntimeException("Unexpected token: " + token);
            }
        }

        while (!ops.isEmpty()) {
            applyOperator(values, ops.pop());
        }

        if (values.isEmpty()) {
            throw new RuntimeException("Empty expression");
        }

        return values.pop();
    }

    private double lookupVariable(String name) {
        if (!variables.containsKey(name)) {
            throw new RuntimeException("Undefined variable: " + name);
        }
        return variables.get(name);
    }

    private int precedence(Token.Type type) {
        switch (type) {
            case PLUS:
            case MINUS:
                return 1;
            case STAR:
            case SLASH:
                return 2;
            default:
                return 0;
        }
    }

    private void applyOperator(Stack<Double> values, Token.Type op) {
        if (values.size() < 2) {
            throw new RuntimeException("Malformed expression");
        }
        double b = values.pop();
        double a = values.pop();
        switch (op) {
            case PLUS:
                values.push(a + b);
                break;
            case MINUS:
                values.push(a - b);
                break;
            case STAR:
                values.push(a * b);
                break;
            case SLASH:
                if (b == 0) {
                    throw new RuntimeException("Division by zero");
                }
                values.push(a / b);
                break;
            default:
                throw new RuntimeException("Unknown operator: " + op);
        }
    }

    public Map<String, Double> getVariables() {
        return variables;
    }
}
