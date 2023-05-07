package actors.Attachtments;

import actors.User;
import control.shop_items.Cart;

public class Order {
    private double totalPrice = 0;
    private int loyaltyPoints;
    private Cart cart;
    private User customer;
    private Address address;

    public Order(User customer) {
        this.cart = customer.getCart();
        this.customer = customer;
        this.loyaltyPoints = customer.getLoyaltyPoints();
        this.address = customer.getAddress();
        for (int i = 0; i < cart.getItemsList().size(); ++i) {
            totalPrice += cart.getItemsList().get(i).getPrice();
        }
    }
    public void showOrderDetails() {
        //Show Order details here in an ordered manner
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

