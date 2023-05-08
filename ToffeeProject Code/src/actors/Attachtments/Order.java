package actors.Attachtments;

import actors.User;
import control.shop_items.Cart;
import control.shop_items.Item;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class Order {
    private double totalPrice = 0;
    private int loyaltyPoints;
    private Cart cart;
    private User customer;
    private Address address;

    public Order(@NotNull User customer) {
        this.cart = customer.getCart();
        this.customer = customer;
        this.loyaltyPoints = customer.getLoyaltyPoints();
        this.address = customer.getAddress();
        for (Map.Entry<Item, Integer> entry : cart.getItemsList().entrySet()) {
            totalPrice += entry.getKey().getPrice();
        }
    }
    public void showOrderDetails() {
        String dashes = "-".repeat(14), line = "-".repeat(35);
        System.out.println("   " + dashes + "Receipt" + dashes);
        System.out.printf("   %-13s %-10s %-10s%n", "Item", "Qty.", "Price/Unit");
        System.out.println("   " + line);
        int cnt = 0;
        for (Map.Entry<Item, Integer> entry : cart.getItemsList().entrySet()) {
            Item item = entry.getKey();
            int qty = entry.getValue();
            System.out.print(++cnt);
            System.out.print(". ");
            System.out.printf("%-14s %-13d $%.2f%n", item.getName(), qty, item.getPrice());
        }
    }
    public void adjustLoyaltyPoints() {
        int loyaltyPointsAdded = 50;
        this.loyaltyPoints += loyaltyPointsAdded;
        this.customer.setLoyaltyPoints(loyaltyPoints);
    }
    public double getTotalPrice() {
        return totalPrice;
    }
    public double getLoyaltyPoints() {
        return loyaltyPoints;
    }
}

