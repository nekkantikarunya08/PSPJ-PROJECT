import java.util.Scanner;

public class WarehouseInventory {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int[] productId = {101, 102, 103, 104, 105};
        String[] productName = {
            "Laptop",
            "Keyboard",
            "Mouse",
            "Monitor",
            "Printer"
        };

        int[] quantity = {15, 8, 25, 4, 12};
        int[] reorderLevel = {5, 10, 10, 5, 8};

        System.out.println("===== WAREHOUSE INVENTORY SYSTEM =====");

        for (int i = 0; i < productId.length; i++) {

            System.out.println("\nProduct ID: " + productId[i]);
            System.out.println("Product Name: " + productName[i]);
            System.out.println("Quantity: " + quantity[i]);
            System.out.println("Reorder Level: " + reorderLevel[i]);

            if (quantity[i] <= reorderLevel[i]) {
                System.out.println("Status: REORDER REQUIRED");
            } else {
                System.out.println("Status: Stock Available");
            }
        }

        sc.close();
    }
}