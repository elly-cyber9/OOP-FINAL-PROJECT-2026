import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * GroceryManager.java
 *
 * Manages the grocery item dataset and performs Linear Search operations
 * using both ArrayList (primary data structure) and LinkedList
 * (alternative data structure), as described in Sections 4.4 and 4.5 of
 * the report.
 */
public class GroceryManager {

    private ArrayList<GroceryItem> arrayListItems;
    private LinkedList<GroceryItem> linkedListItems;

    public GroceryManager() {
        arrayListItems = new ArrayList<>();
        linkedListItems = new LinkedList<>();
    }

    /**
     * Loads the base dataset of 30 grocery items into both the ArrayList
     * and the LinkedList so that both structures hold identical data
     * (Section 2.1 - Dataset Preparation).
     */
    public void loadItems() {
        Object[][] data = {
            {"Rice",        "01-Apr-2026", 50, 12000.0, "10-Apr-2026", 14000.0, 20},
            {"Salt",        "01-Apr-2026", 100, 3000.0, "10-Apr-2026", 4000.0, 40},
            {"Toothpaste",  "01-Apr-2026", 40, 8000.0, "12-Apr-2026", 10000.0, 15},
            {"Chocolate",   "02-Apr-2026", 60, 5000.0, "15-Apr-2026", 7000.0, 25},
            {"Cool Drinks", "02-Apr-2026", 80, 6000.0, "16-Apr-2026", 8500.0, 30},
            {"Carrot",      "02-Apr-2026", 70, 3000.0, "18-Apr-2026", 4500.0, 35},
            {"Tomato",      "02-Apr-2026", 22, 24000.0, "20-Apr-2026", 27600.0, 7},
            {"Beans",       "03-Apr-2026", 45, 9000.0, "20-Apr-2026", 11000.0, 20},
            {"Apples",      "03-Apr-2026", 60, 15000.0, "22-Apr-2026", 18000.0, 25},
            {"Bananas",     "03-Apr-2026", 90, 8000.0, "22-Apr-2026", 10000.0, 40},
            {"Milk",        "04-Apr-2026", 55, 13000.0, "24-Apr-2026", 15500.0, 22},
            {"Bread",       "04-Apr-2026", 65, 10000.0, "24-Apr-2026", 12500.0, 30},
            {"Cooking Oil", "04-Apr-2026", 35, 22000.0, "26-Apr-2026", 25000.0, 15},
            {"Sugar",       "05-Apr-2026", 75, 11000.0, "26-Apr-2026", 13000.0, 35},
            {"Tea",         "05-Apr-2026", 50, 9500.0, "28-Apr-2026", 12000.0, 20},
            {"Coffee",      "05-Apr-2026", 40, 18000.0, "28-Apr-2026", 21000.0, 18},
            {"Biscuits",    "06-Apr-2026", 85, 6000.0, "30-Apr-2026", 8000.0, 40},
            {"Butter",      "06-Apr-2026", 30, 21000.0, "01-May-2026", 24000.0, 12},
            {"Cheese",      "06-Apr-2026", 25, 30000.0, "02-May-2026", 34000.0, 10},
            {"Yogurt",      "07-Apr-2026", 48, 9000.0, "03-May-2026", 11000.0, 22},
            {"Eggs",        "07-Apr-2026", 100, 2000.0, "04-May-2026", 2800.0, 50},
            {"Onion",       "07-Apr-2026", 60, 7000.0, "05-May-2026", 9000.0, 28},
            {"Potato",      "08-Apr-2026", 65, 6500.0, "06-May-2026", 8500.0, 30},
            {"Cucumber",    "08-Apr-2026", 40, 4000.0, "07-May-2026", 5500.0, 18},
            {"Lettuce",     "08-Apr-2026", 35, 5000.0, "08-May-2026", 6800.0, 15},
            {"Orange",      "09-Apr-2026", 55, 12000.0, "09-May-2026", 14500.0, 25},
            {"Mango",       "09-Apr-2026", 45, 16000.0, "10-May-2026", 19000.0, 20},
            {"Detergent",   "09-Apr-2026", 30, 25000.0, "11-May-2026", 28000.0, 12},
            {"Notebook",    "10-Apr-2026", 90, 4000.0, "12-May-2026", 5500.0, 40},
            {"Pen",         "10-Apr-2026", 120, 1500.0, "13-May-2026", 2200.0, 60}
        };

        for (Object[] row : data) {
            GroceryItem item = new GroceryItem(
                (String) row[0], (String) row[1], (Integer) row[2],
                (Double) row[3], (String) row[4], (Double) row[5], (Integer) row[6]
            );
            arrayListItems.add(item);
            linkedListItems.add(item);
        }
    }

    public ArrayList<GroceryItem> getArrayListItems() {
        return arrayListItems;
    }

    public LinkedList<GroceryItem> getLinkedListItems() {
        return linkedListItems;
    }

    /**
     * Searches the ArrayList using Linear Search (Section 4.4.2.2).
     * Matches items whose name contains the search keyword (case-insensitive).
     */
    public List<GroceryItem> searchArrayList(String keyword) {
        List<GroceryItem> results = new ArrayList<>();
        for (int i = 0; i < arrayListItems.size(); i++) {
            GroceryItem item = arrayListItems.get(i);
            if (item.getItemName().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(item);
            }
        }
        return results;
    }

    /**
     * Searches the LinkedList using Linear Search (Section 4.5.2.2).
     * Matches items whose name contains the search keyword (case-insensitive).
     */
    public List<GroceryItem> searchLinkedList(String keyword) {
        List<GroceryItem> results = new ArrayList<>();
        for (GroceryItem item : linkedListItems) {
            if (item.getItemName().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(item);
            }
        }
        return results;
    }

    /**
     * Displays all grocery items currently stored (Section 4.4.2.3 / 4.5.2.3).
     */
    public void displayAllItems() {
        System.out.println("\n===== ALL GROCERY ITEMS (" + arrayListItems.size() + " total) =====");
        for (GroceryItem item : arrayListItems) {
            item.displayDetails();
        }
    }

    /**
     * Generates a larger dataset by duplicating the base 30-item dataset
     * until the target size is reached, as described in Section 2.1.
     * Used by RuntimeTester for the 300 / 3,000 / 30,000 item experiments.
     */
    public static ArrayList<GroceryItem> generateArrayListDataset(GroceryManager base, int targetSize) {
        ArrayList<GroceryItem> dataset = new ArrayList<>(targetSize);
        ArrayList<GroceryItem> source = base.getArrayListItems();
        int i = 0;
        while (dataset.size() < targetSize) {
            dataset.add(source.get(i % source.size()));
            i++;
        }
        return dataset;
    }

    public static LinkedList<GroceryItem> generateLinkedListDataset(GroceryManager base, int targetSize) {
        LinkedList<GroceryItem> dataset = new LinkedList<>();
        ArrayList<GroceryItem> source = base.getArrayListItems();
        int i = 0;
        while (dataset.size() < targetSize) {
            dataset.add(source.get(i % source.size()));
            i++;
        }
        return dataset;
    }
}
