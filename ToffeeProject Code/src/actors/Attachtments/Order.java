package actors.Attachtments;

import actors.User;
import control.shop_items.Item;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * <h1>Order of a User</h1>
 * The Order program implements an application that
 * simply has the order details of a user and Prints
 * the output on the screen.
 *
 * @author  Maya Ayman
 */
public class Order {
    private Double totalPrice = 0.0;
    private User customer;

    /**
     * The constructor initializes the customer and the totalPrice.
     * @param customer This is the only parameter to the constructor
     */
    public Order(@NotNull User customer) {
        this.customer = customer;
        for (Map.Entry<Item, Integer> entry : customer.getCart().getItemsList().entrySet()) {
            totalPrice += entry.getKey().getPrice();
        }
        totalPrice += 30;
//        adjustLoyaltyPoints();
    }
    /**
     * This method prints all the details of the order: items,
     * quantity, price/unit, delivery, totalCost, new loyalty points balance,
     * and shipping address.
     */
    public void showOrderDetails() {
        System.out.println('\n');
        String dashes = "-".repeat(14), line = "-".repeat(35);
        System.out.println("   " + dashes + "Receipt" + dashes);
        System.out.printf("   %-13s %-10s %-10s%n", "Item", "Qty.", "Price/Unit");
        System.out.println("   " + line);
        int cnt = 0;
        for (Map.Entry<Item, Integer> entry : customer.getCart().getItemsList().entrySet()) {
            Item item = entry.getKey();
            int qty = entry.getValue();
            System.out.print(++cnt);
            System.out.print(". ");
            System.out.printf("%-14s %-13d LE %.2f%n", item.getName(), qty, item.getPrice());
        }
        System.out.println('\n');
//        System.out.println("   " + line);
//        System.out.println("Delivery: 30 LE");
//        System.out.println("Total Cost: " + totalPrice.toString() + " LE");
//        System.out.println("\nNew Loyalty Points Balance: " + customer.getLoyaltyPoints().toString());
//        System.out.println("Shipping To: " + customer.getAddress().toString());
    }

//    private void adjustLoyaltyPoints() {
//        int loyaltyPointsAdded = 50;
//        this.customer.setLoyaltyPoints(this.customer.getLoyaltyPoints() + loyaltyPointsAdded);
//    }
    public Double getTotalPrice() {
        return totalPrice;
    }
}

