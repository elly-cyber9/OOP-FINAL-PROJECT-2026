import java.util.List;
import java.util.Scanner;

/**
 * Main.java
 *
 * Entry point of the Grocery Item Search System. Displays the console
 * menu (Figure 3) and handles user interaction, as described in
 * Section 4.3 (User Interface Design) and Section 4.5 (System Workflow).
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GroceryManager manager = new GroceryManager();
        RuntimeTester tester = new RuntimeTester();

        manager.loadItems();

        System.out.println("==========================================");
        System.out.println("      GROCERY ITEM SEARCH SYSTEM");
        System.out.println("==========================================");
        System.out.println("Primary Data Structure     : ArrayList");
        System.out.println("Alternative Data Structure : LinkedList");

        boolean running = true;
        while (running) {
            System.out.println("\n============ MENU ============");
            System.out.println("1. Search using ArrayList");
            System.out.println("2. Search using LinkedList");
            System.out.println("3. Display all grocery items");
            System.out.println("4. Runtime measurement");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            String choiceInput = scanner.nextLine().trim();
            int choice;
            try {
                choice = Integer.parseInt(choiceInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                continue;
            }

            switch (choice) {
                case 1: {
                    System.out.print("Enter item name to search: ");
                    String keyword = scanner.nextLine().trim();

                    long start = System.nanoTime();
                    List<GroceryItem> results = manager.searchArrayList(keyword);
                    long end = System.nanoTime();
                    double runtimeMs = (end - start) / 1_000_000.0;

                    System.out.println("\nSearching using \"" + keyword + "\" (ArrayList)...");
                    if (results.isEmpty()) {
                        System.out.println("No matching item found.");
                    } else {
                        System.out.println("********** SEARCH RESULT FOUND **********");
                        for (GroceryItem item : results) {
                            item.displayDetails();
                        }
                    }
                    System.out.printf("Search Runtime: %.6f ms%n", runtimeMs);
                    break;
                }
                case 2: {
                    System.out.print("Enter item name to search: ");
                    String keyword = scanner.nextLine().trim();

                    long start = System.nanoTime();
                    List<GroceryItem> results = manager.searchLinkedList(keyword);
                    long end = System.nanoTime();
                    double runtimeMs = (end - start) / 1_000_000.0;

                    System.out.println("\nSearching using \"" + keyword + "\" (LinkedList)...");
                    if (results.isEmpty()) {
                        System.out.println("No matching item found.");
                    } else {
                        System.out.println("********** SEARCH RESULT FOUND **********");
                        for (GroceryItem item : results) {
                            item.displayDetails();
                        }
                    }
                    System.out.printf("Search Runtime: %.6f ms%n", runtimeMs);
                    break;
                }
                case 3:
                    manager.displayAllItems();
                    break;
                case 4:
                    tester.runComparison(manager);
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }

        scanner.close();
    }
}
