import java.util.Scanner;
import java.io.*;
import java.nio.file.*;

// ================= CO-4: CLASS & ENCAPSULATION =================
class Product {

    private int productId;
    private String productName;
    private double price;
    private int quantity;

    // Constructor
    Product(int productId, String productName,
            double price, int quantity) {

        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters
    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    // Method
    public double getTotalValue() {
        return price * quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException(
                    "Quantity cannot be negative.");
        }

        this.quantity = quantity;
    }

    public void display() {

        System.out.println(
                productId + " | " +
                productName + " | ₹" +
                price + " | " +
                quantity + " | ₹" +
                getTotalValue());
    }
}


// ================= CO-4: INHERITANCE =================
class ElectronicProduct extends Product {

    private int warrantyYears;

    ElectronicProduct(int productId, String productName,
                      double price, int quantity,
                      int warrantyYears) {

        super(productId, productName, price, quantity);
        this.warrantyYears = warrantyYears;
    }

    // Method overriding
    @Override
    public void display() {

        super.display();

        System.out.println(
                "Warranty: " + warrantyYears + " years");
    }
}


// ================= MAIN CLASS =================
public class WarehouseInventory {

    static Scanner sc = new Scanner(System.in);

    // CO-3: 1D ARRAY
    static Product[] products = new Product[50];

    static int count = 0;

    static final String FILE_NAME = "inventory.txt";


    // ================= CO-3: METHOD =================
    static void addProduct() {

        try {

            if (count >= products.length) {
                throw new IllegalStateException(
                        "Warehouse is full.");
            }

            // CO-1: Scanner + data types
            System.out.print("Enter Product ID: ");
            int id = sc.nextInt();

            sc.nextLine();

            System.out.print("Enter Product Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Price: ");
            double price = sc.nextDouble();

            System.out.print("Enter Quantity: ");
            int quantity = sc.nextInt();

            System.out.print("Enter Warranty Years: ");
            int warranty = sc.nextInt();

            // CO-2: decision making
            if (price <= 0 || quantity < 0) {

                throw new IllegalArgumentException(
                        "Invalid price or quantity.");
            }

            // CO-3: searching array
            if (searchProduct(id) != -1) {

                throw new IllegalArgumentException(
                        "Product ID already exists.");
            }

            // CO-4: object creation
            products[count] =
                    new ElectronicProduct(
                            id,
                            name,
                            price,
                            quantity,
                            warranty
                    );

            count++;

            System.out.println(
                    "Product added successfully.");

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Error: " + e.getMessage());

        } catch (IllegalStateException e) {

            System.out.println(
                    "Error: " + e.getMessage());
        }
    }


    // ================= CO-3: LINEAR SEARCH =================
    static int searchProduct(int id) {

        for (int i = 0; i < count; i++) {

            if (products[i].getProductId() == id) {

                return i;
            }
        }

        return -1;
    }


    // ================= CO-3 + CO-5 =================
    static void searchByName() {

        sc.nextLine();

        System.out.print(
                "Enter product name to search: ");

        String searchName = sc.nextLine();

        boolean found = false;

        // CO-2: loop
        // CO-5: String comparison
        for (int i = 0; i < count; i++) {

            if (products[i]
                    .getProductName()
                    .equalsIgnoreCase(searchName)) {

                System.out.println(
                        "\nProduct Found:");

                products[i].display();

                found = true;
            }
        }

        if (!found) {

            System.out.println(
                    "Product not found.");
        }
    }


    // ================= CO-3: ARRAY SORTING =================
    static void sortProducts() {

        // Bubble sort
        for (int i = 0; i < count - 1; i++) {

            for (int j = 0; j < count - i - 1; j++) {

                if (products[j]
                        .getProductName()
                        .compareToIgnoreCase(
                                products[j + 1]
                                        .getProductName()) > 0) {

                    Product temp = products[j];

                    products[j] = products[j + 1];

                    products[j + 1] = temp;
                }
            }
        }

        System.out.println(
                "Products sorted by name.");
    }


    // ================= CO-2 + CO-3 =================
    static void displayProducts() {

        if (count == 0) {

            System.out.println(
                    "Warehouse is empty.");

            return;
        }

        System.out.println(
                "\n===== INVENTORY =====");

        System.out.println(
                "ID | Name | Price | Quantity | Total");

        System.out.println(
                "------------------------------------------------");

        // CO-2: for loop
        // CO-3: array traversal
        for (int i = 0; i < count; i++) {

            products[i].display();
        }
    }


    // ================= CO-5: FILE WRITING =================
    static void saveToFile() {

        try {

            Path path =
                    Paths.get(FILE_NAME);

            // CO-5: Files + BufferedWriter
            BufferedWriter writer =
                    Files.newBufferedWriter(path);

            for (int i = 0; i < count; i++) {

                writer.write(
                        products[i].getProductId()
                                + "," +
                        products[i].getProductName()
                                + "," +
                        products[i].getPrice()
                                + "," +
                        products[i].getQuantity());

                writer.newLine();
            }

            writer.close();

            System.out.println(
                    "Inventory saved to file.");

        } catch (IOException e) {

            System.out.println(
                    "File error: " +
                    e.getMessage());
        }
    }


    // ================= CO-5: FILE READING =================
    static void readFromFile() {

        Path path =
                Paths.get(FILE_NAME);

        if (!Files.exists(path)) {

            System.out.println(
                    "Inventory file does not exist.");

            return;
        }

        System.out.println(
                "\n===== DATA FROM FILE =====");

        try {

            // CO-5: BufferedReader
            BufferedReader reader =
                    Files.newBufferedReader(path);

            String line;

            // CO-2: while loop
            while ((line = reader.readLine()) != null) {

                // CO-5: String parsing
                String[] data =
                        line.split(",");

                if (data.length == 4) {

                    System.out.println(
                            "ID: " + data[0] +
                            " | Name: " + data[1] +
                            " | Price: " + data[2] +
                            " | Quantity: " + data[3]);
                }
            }

            reader.close();

        } catch (IOException e) {

            System.out.println(
                    "File reading error: " +
                    e.getMessage());
        }
    }


    // ================= CO-3: TOTAL INVENTORY VALUE =================
    static void calculateTotalValue() {

        double total = 0;

        for (int i = 0; i < count; i++) {

            total += products[i].getTotalValue();
        }

        System.out.println(
                "Total Inventory Value: ₹" + total);
    }


    // ================= MAIN =================
    public static void main(String[] args) {

        int choice;

        // CO-2: do-while loop
        do {

            System.out.println(
                    "\n===== WAREHOUSE INVENTORY SYSTEM =====");

            System.out.println("1. Add Product");
            System.out.println("2. Display Products");
            System.out.println("3. Search Product by ID");
            System.out.println("4. Search Product by Name");
            System.out.println("5. Sort Products");
            System.out.println("6. Calculate Total Value");
            System.out.println("7. Save Inventory to File");
            System.out.println("8. Read Inventory from File");
            System.out.println("9. Exit");

            System.out.print(
                    "Enter your choice: ");

            choice = sc.nextInt();


            // CO-2: switch
            switch (choice) {

                case 1:

                    addProduct();

                    break;


                case 2:

                    displayProducts();

                    break;


                case 3:

                    System.out.print(
                            "Enter Product ID: ");

                    int id = sc.nextInt();

                    int index =
                            searchProduct(id);

                    if (index != -1) {

                        System.out.println(
                                "\nProduct Found:");

                        products[index].display();

                    } else {

                        System.out.println(
                                "Product not found.");
                    }

                    break;


                case 4:

                    searchByName();

                    break;


                case 5:

                    sortProducts();

                    displayProducts();

                    break;


                case 6:

                    calculateTotalValue();

                    break;


                case 7:

                    saveToFile();

                    break;


                case 8:

                    readFromFile();

                    break;


                case 9:

                    System.out.println(
                            "Exiting Warehouse Inventory System...");

                    break;


                default:

                    System.out.println(
                            "Invalid choice.");
            }

        } while (choice != 9);


        sc.close();
    }
}
