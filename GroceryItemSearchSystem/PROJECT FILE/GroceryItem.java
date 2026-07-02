/**
 * GroceryItem.java
 *
 * Represents a single grocery item record used by the Grocery Item Search
 * System. Stores item name, purchase/sale details, and automatically
 * calculates profit using:
 *
 *      Profit = (Sale Price - Purchase Price) x Sale Quantity
 */
public class GroceryItem {

    private String itemName;
    private String purchaseDate;
    private int quantity;
    private double purchasePrice;
    private String saleDate;
    private double salePrice;
    private int saleQuantity;
    private double profit;

    public GroceryItem(String itemName, String purchaseDate, int quantity,
                        double purchasePrice, String saleDate, double salePrice,
                        int saleQuantity) {
        this.itemName = itemName;
        this.purchaseDate = purchaseDate;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
        this.saleDate = saleDate;
        this.salePrice = salePrice;
        this.saleQuantity = saleQuantity;
        this.profit = (salePrice - purchasePrice) * saleQuantity;
    }

    // ----------------- Getters -----------------
    public String getItemName() {
        return itemName;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public int getSaleQuantity() {
        return saleQuantity;
    }

    public double getProfit() {
        return profit;
    }

    /**
     * Displays the full details of the grocery item in a formatted block,
     * matching the console output shown in the report (Figure 3).
     */
    public void displayDetails() {
        System.out.println("---------------------------------------------");
        System.out.println("Item Name      : " + itemName);
        System.out.println("Purchase Date  : " + purchaseDate);
        System.out.println("Quantity       : " + quantity);
        System.out.printf ("Purchase Price : IDR %.0f%n", purchasePrice);
        System.out.println("Sale Date      : " + saleDate);
        System.out.printf ("Sale Price     : IDR %.0f%n", salePrice);
        System.out.println("Sale Quantity  : " + saleQuantity);
        System.out.printf ("Profit         : IDR %.0f%n", profit);
        System.out.println("---------------------------------------------");
    }

    @Override
    public String toString() {
        return itemName + " | Qty: " + quantity + " | Profit: IDR " + String.format("%.0f", profit);
    }
}
