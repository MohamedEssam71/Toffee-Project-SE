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
            totalPrice += entry.getKey().getPrice() * entry.getValue();
        }
        totalPrice += 30;
    }
    /**
     * This method prints all the details of the order: items,
     * quantity, price/unit, delivery, totalCost, new loyalty points balance,
     * and shipping address.
     */
    public void showOrderDetails() {
        System.out.println();
        String dashes = "-".repeat(20), line = "-".repeat(47);
        System.out.println("   " + dashes + "Receipt" + dashes);
        System.out.printf("   %-21s %-13s %-17s%n", "Item", "Qty.", "Price/Unit");
        System.out.println("   " + line);
        for (Map.Entry<Item, Integer> entry : customer.getCart().getItemsList().entrySet()) {
            Item item = entry.getKey();
            int qty = entry.getValue();
            System.out.print("   ");
            System.out.printf("%-21s %-13d LE %.2f%n", item.getName(), qty, item.getPrice());
        }
        System.out.println("   " + line);
        System.out.println("   Delivery: 30 LE");
        System.out.println("   Total Cost: " + totalPrice.toString() + " LE");
        System.out.println("   New Loyalty Points Balance: " + customer.getLoyaltyPoints().toString());
//        System.out.println("   Shipping To: " + customer.getAddress().toString() + '\n');
    }

    public Double getTotalPrice() {
        return totalPrice;
    }
}

