import java.util.Map;
import java.util.Scanner;

/**
 * Entry point for the Mini Calculator Interpreter.
 * Reads lines from the console in a loop (a REPL: Read-Evaluate-Print-Loop)
 * and feeds each one to the Interpreter.
 *
 * Supported input:
 *   3 + 4 * 2          -> evaluates and prints the result
 *   x = 3 + 4           -> stores 7 into variable x
 *   x * (2 + 1)         -> uses stored variables in an expression
 *   vars                -> lists all variables currently defined
 *   exit                -> quits the program
 */
public class Main {
    public static void main(String[] args) {
        Interpreter interpreter = new Interpreter();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Mini Calculator Interpreter");
        System.out.println("Type an expression, 'vars' to list variables, or 'exit' to quit.");

        while (true) {
            System.out.print("> ");
            if (!scanner.hasNextLine()) {
                break;
            }
            String line = scanner.nextLine().trim();

            if (line.isEmpty()) {
                continue;
            }
            if (line.equalsIgnoreCase("exit")) {
                break;
            }

            if (line.equalsIgnoreCase("vars")) {
                Map<String, Double> vars = interpreter.getVariables();
                if (vars.isEmpty()) {
                    System.out.println("(no variables defined)");
                } else {
                    for (Map.Entry<String, Double> entry : vars.entrySet()) {
                        System.out.println(entry.getKey() + " = " + entry.getValue());
                    }
                }
                continue;
            }

            try {
                double result = interpreter.run(line);
                System.out.println(result);
            } catch (RuntimeException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
        System.out.println("Goodbye!");
    }
}
