import java.util.*;

public class ShoppingCart {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Product prices
        double productAPrice = 20;
        double productBPrice = 40;
        double productCPrice = 50;

        // Quantity and gift wrap status for each product
        int quantityA = getQuantity(scanner, "Product A");
        boolean giftWrapA = getGiftWrapStatus(scanner);

        int quantityB = getQuantity(scanner, "Product B");
        boolean giftWrapB = getGiftWrapStatus(scanner);

        int quantityC = getQuantity(scanner, "Product C");
        boolean giftWrapC = getGiftWrapStatus(scanner);

        // Calculate totals
        double totalAmountA = calculateTotal(productAPrice, quantityA, giftWrapA);
        double totalAmountB = calculateTotal(productBPrice, quantityB, giftWrapB);
        double totalAmountC = calculateTotal(productCPrice, quantityC, giftWrapC);

        double subtotal = totalAmountA + totalAmountB + totalAmountC;

        // Apply discounts
        double discountAmount = calculateDiscount(subtotal, quantityA, quantityB, quantityC);

        // Calculate shipping and gift wrap fees
        double shippingFee = calculateShippingFee(quantityA, quantityB, quantityC);
        double giftWrapFee = calculateGiftWrapFee(quantityA, quantityB, quantityC);

        // Calculate the final total
        double total = subtotal - discountAmount + shippingFee + giftWrapFee;

        // Display the details
        displayDetails("Product A", quantityA, totalAmountA);
        displayDetails("Product B", quantityB, totalAmountB);
        displayDetails("Product C", quantityC, totalAmountC);

        System.out.println("\nSubtotal: $" + subtotal);
        System.out.println("Discount Applied: $" + discountAmount);
        System.out.println("Shipping Fee: $" + shippingFee);
        System.out.println("Gift Wrap Fee: $" + giftWrapFee);
        System.out.println("Total: $" + total);

        scanner.close();
    }

    // Helper method to get quantity from user
    private static int getQuantity(Scanner scanner, String productName) {
        System.out.print("Enter the quantity of " + productName + ": ");
        return scanner.nextInt();
    }

    // Helper method to get gift wrap status from user
    private static boolean getGiftWrapStatus(Scanner scanner) {
        System.out.print("Is this product wrapped as a gift? (true/false): ");
        return scanner.nextBoolean();
    }

    // Helper method to calculate total amount for a product
    private static double calculateTotal(double price, int quantity, boolean giftWrap) {
        double totalAmount = price * quantity;
        if (giftWrap) {
            totalAmount += quantity; // Gift wrap fee: $1 per unit
        }
        return totalAmount;
    }

    // Helper method to calculate discount based on rules
    private static double calculateDiscount(double subtotal, int quantityA, int quantityB, int quantityC) {
        double flat10Discount = (subtotal > 200) ? 10 : 0;
        double bulk5Discount = (quantityA > 10 || quantityB > 10 || quantityC > 10) ? subtotal * 0.05 : 0;
        double bulk10Discount = (quantityA + quantityB + quantityC > 20) ? subtotal * 0.10 : 0;
        double tiered50Discount = (quantityA + quantityB + quantityC > 30 && quantityA > 15) ? subtotal * 0.50 : 0;

        return Math.max(Math.max(flat10Discount, bulk5Discount), Math.max(bulk10Discount, tiered50Discount));
    }

    // Helper method to calculate shipping fee
    private static double calculateShippingFee(int quantityA, int quantityB, int quantityC) {
        int totalUnits = quantityA + quantityB + quantityC;
        return Math.ceil((double) totalUnits / 10) * 5; // 10 units per package, $5 per package
    }

    // Helper method to calculate gift wrap fee
    private static double calculateGiftWrapFee(int quantityA, int quantityB, int quantityC) {
        return (quantityA + quantityB + quantityC); // Gift wrap fee: $1 per unit
    }

    // Helper method to display product details
    private static void displayDetails(String productName, int quantity, double totalAmount) {
        System.out.println(productName + ": Quantity: " + quantity + ", Total: $" + totalAmount);
    }
}
