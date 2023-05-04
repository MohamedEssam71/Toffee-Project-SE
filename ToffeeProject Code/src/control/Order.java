package control;
import actors.Address;
import actors.User;
import control.shop_items.Cart;
import java.util.Date;

public class Order {
    private double totalPrice;
    private int loyaltyPoints;
    private Cart cart;
    private User customer;
    private Address address;
    private Date dateOfCheckout;

    public Order(Cart cart, User customer, Address address, Date dateOfCheckout) {
        this.cart = cart;
        //Should do a loop here to initialize the totalPrice!
        this.customer = customer;
        this.loyaltyPoints = customer.getLoyaltyPoints();
        this.address = address;
        this.dateOfCheckout = dateOfCheckout;
    }
    public void showOrder() {
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
