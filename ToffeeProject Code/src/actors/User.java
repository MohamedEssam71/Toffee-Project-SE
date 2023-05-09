package actors;


import actors.Attachtments.Address;
import control.shop_items.Cart;


/**
 * <h3>User Model Class</h3>
 * It handle all the user data with cart, and address.
 * Contains setters, and getters for the user.
 * @author Mohamed Essam
 */
public class User {
    private String userName;
    private String email;
    private String password;
    private String phoneNumber;
    private Integer loyaltyPoints = 0;
    private boolean isLoggedIn;
    private Address address = new Address();
    private Cart cart = new Cart();

    /**
     * Constructor that initializes the user data.
     * @param userName
     * @param email
     * @param password
     * @param phoneNumber
     */
    public User(String userName, String email, String password, String phoneNumber){
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.loyaltyPoints = 0;
    }

    /**
     * Default Constructor
     */
    public User(){}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(Integer loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
