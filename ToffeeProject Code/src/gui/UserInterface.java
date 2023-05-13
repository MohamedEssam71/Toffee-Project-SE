package gui;

import actors.Attachtments.Order;
import actors.User;
import control.DeliveryPay;
import control.InputOutput;
import control.PaymentMethod;
import control.shop_items.Cart;
import control.shop_items.Catalog;
import control.shop_items.Item;
import model.DataBaseQueries;

import java.sql.SQLException;
import java.util.Map;

/**
 * <h3>User Interface Class</h3>
 * This class takes care of application interface where user
 * can interact with the system from this view class.
 * @author Mohamed Essam
 * @author Maya Ayman
 * @author Rawan Younis
 */
public class UserInterface {
    private InputOutput inputOutput = new InputOutput();
    private User user;
    private Catalog catalog = new Catalog();

    /**
     * This Method shows the system steps<br>
     * from the main menu page to catalog page,<br>
     * checkout page, receipt page ...
     * @throws InterruptedException in case of database error
     */
    public void systemSteps() throws InterruptedException {
        Integer choice = inputOutput.mainMenu();
        switch (choice) {
            case 1 -> {
                logIn();
                Thread.sleep(1000);
                if (user == null) {
                    systemSteps();
                } else {
                    showCatalog();
                }
            }
            case 2 -> {
                try {
                    register();
                    Thread.sleep(1000);

                    if (user == null) {
                        systemSteps();
                    } else {
                        logIn();
                        Thread.sleep(1000);
                        showCatalog();
                    }



                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            case 3 -> {
                forgotPassword(user);
                systemSteps();
            }
            case 4 -> showCatalog();

            case 5 -> {
                inputOutput.exit();
                System.exit(0);
            }
        }
    }

    /**
     * Default Constructor.<br>
     * It loads all the item data from the database,<br>
     * and put it on the catalog page.
     */
    public UserInterface(){
        DataBaseQueries dataBaseQueries = new DataBaseQueries();
        try {
            catalog.setItems(dataBaseQueries.loadItems());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method used to register a new user to the system.
     * @return user to be used later
     * @throws SQLException to handle database errors
     */
    public User register() throws SQLException {
        user = inputOutput.takeUserInput();
        return user;
    }

    /**
     * This method used to log into the system.
     * @return user to be used later
     */
    public User logIn(){
        user = inputOutput.loginPage();
        return user;
    }

    /**
     * This method used to enter a new password for a registered user.
     * @param user that want to change his password
     */
    public void forgotPassword(User user) {
        inputOutput.forgetPassword(user);
    }

    /**
     * This method contains check-out Logic.<br>
     * It shows how system will react to the user input.
     * @throws InterruptedException  in case of database error
     */
    public void checkOut() throws InterruptedException {
        user.getCart();
        Integer choice = inputOutput.checkOutOptions();
        if(choice == 1) {
           user.setAddress(inputOutput.takeAddressInput());
        }
        Order order = new Order(user);
        PaymentMethod paymentMethod = new DeliveryPay();
        order.showOrderDetails();
        inputOutput.showAddress(user);

        choice = inputOutput.orderOptions();
        if(choice == 1) {
            if (paymentMethod.makePayment(order)) {
                boolean confirmed = inputOutput.confirmOrder(user);
                if(confirmed){
                    pay();
                } else{
                    inputOutput.orderCancelled();
                }
                Thread.sleep(1000);
                showCatalog();
            } else {
                inputOutput.invalidPayment();
                Thread.sleep(1000);
                showCatalog();
            }
        } else {
            showCatalog();
        }
    }
    /**
     * This method contains catalog Logic.<br>
     * It shows how system will react to the user input.
     * @throws InterruptedException in case for database error
     */
    public void showCatalog() throws InterruptedException {
        Integer choice = inputOutput.showCatalogInfo(catalog,user);
        int cnt, catalogSize = catalog.getItems().size(),quantity = 1;
        // Show Item Details
        if(choice <= catalog.getItems().size()){
            cnt = 1;
            Item itemNeeded = new Item();
            for(Map.Entry<String,Item> pair : catalog.getItems().entrySet()){
                if(cnt == choice){
                    choice = inputOutput.showItemInfo(pair.getValue());
                    if(user != null){
                        quantity = inputOutput.takeQuantityItem();
                    }
                    itemNeeded = pair.getValue();
                    break;
                }
                cnt++;
            }
            switch (choice) {
                case 1 -> {
                    if(user == null){
                        inputOutput.userNotRegistered();
                    }
                    else {
                        for(int i = 0; i < quantity; ++i){
                            user.getCart().addToCart(itemNeeded);
                        }
                        inputOutput.itemAdded();
                    }
                    Thread.sleep(1000);
                    showCatalog();
                }
                case 2 -> showCatalog();
            }
        }
        else{ // Add from Catalog.
            if(choice == catalogSize+1){
                if(user == null){
                    inputOutput.userNotRegistered();
                }
                else {
                    choice = inputOutput.takeUserItem(catalog.getItems().size());
                    quantity = inputOutput.takeQuantityItem();
                    cnt = 1;
                    Item itemNeeded = new Item();
                    for (Map.Entry<String, Item> pair : catalog.getItems().entrySet()) {
                        if (cnt == choice) {
                            itemNeeded = pair.getValue();
                            break;
                        }
                        cnt++;
                    }
                    for(int i = 0; i < quantity; ++i){
                        user.getCart().addToCart(itemNeeded);
                    }
                    inputOutput.itemAdded();
                }
                Thread.sleep(1000);
                showCatalog();
            }
            else if(choice == catalogSize + 2){
                if(user == null){
                    inputOutput.userNotRegistered();
                    Thread.sleep(1000);
                    showCatalog();
                }
                else {
                    if (user.getCart().getItemsList().isEmpty()) {
                        inputOutput.emptyCart();
                        Thread.sleep(1000);
                        showCatalog();
                    }
                    choice = showCart();
                    if (choice == 1) {
                        checkOut();
                    } else if (choice == 2) {
                        showCatalog();
                    } else {
                        user.getCart().getItemsList().clear();
                        inputOutput.clearCart();
                        Thread.sleep(1000);
                        showCatalog();
                    }
                }
            }
            else{
                if(user == null){
                    inputOutput.returnBackToMainMenu();
                }
                else {
                    inputOutput.logOut();
                    user = null;
                }
                systemSteps();
            }
        }


    }

    /**
     * This method contains Cart Logic.<br>
     * It shows how system will react to the user input.
     * @return the user choice integer
     */
    public Integer showCart(){
        return inputOutput.showCart(user.getUserName(), user.getCart());
    }
    /**
     * This method used to confirm the order,<br>
     * adjust loyalty points,
     * and make cart empty after that.
     */
    public void pay() {
        DataBaseQueries dataBaseQueries = new DataBaseQueries();
        user.setLoyaltyPoints(user.getLoyaltyPoints() + 50);
        try {
            dataBaseQueries.updateLoyaltyPoints(user.getEmail(), user.getLoyaltyPoints());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        inputOutput.payDelivered();
        Cart cart = new Cart();
        user.setCart(cart);
    }

}
