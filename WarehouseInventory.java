import java.util.Scanner;

public class WarehouseInventory {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Product details
        int productId;
        String productName;
        double price;
        int quantity;

        // Input
        System.out.println("===== WAREHOUSE INVENTORY SYSTEM =====");

        System.out.print("Enter Product ID: ");
        productId = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter Product Name: ");
        productName = sc.nextLine();

        System.out.print("Enter Product Price: ");
        price = sc.nextDouble();

        System.out.print("Enter Quantity: ");
        quantity = sc.nextInt();

        // Calculations
        double totalValue = price * quantity;

        double averageValue = totalValue / quantity;

        // Output
        System.out.println("\n===== INVENTORY DETAILS =====");

        System.out.println("Product ID    : " + productId);
        System.out.println("Product Name  : " + productName);
        System.out.println("Price         : " + price);
        System.out.println("Quantity      : " + quantity);
        System.out.println("Total Value   : " + totalValue);
        System.out.println("Average Value : " + averageValue);

        // Relational operator
        if (quantity > 0) {
            System.out.println("Stock Status  : Available");
        }

        sc.close();
    }
}
