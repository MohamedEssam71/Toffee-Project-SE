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
    private User user = new User();
    private Catalog catalog = new Catalog();

    /**
     * This Method shows the system steps<br>
     * from the main menu page to catalog page,<br>
     * checkout page, receipt page ...
     * @throws InterruptedException
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

                    logIn();

                    Thread.sleep(1000);
                    showCatalog();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            case 3 -> {
                forgotPassword(user);
                systemSteps();
            }
            case 4 -> {
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
     * @return
     * @throws SQLException
     */
    public User register() throws SQLException {
        user = inputOutput.takeUserInput();
        return user;
    }

    /**
     * This method used to log into the system.
     * @return
     */
    public User logIn(){
        user = inputOutput.loginPage();
        return user;
    }

    /**
     * This method used to enter a new password for a registered user.
     * @param user
     */
    public void forgotPassword(User user) {
        inputOutput.forgetPassword(user);
    }

    /**
     * This method contains check-out Logic.<br>
     * It shows how system will react to the user input.
     * @throws InterruptedException
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

        choice = inputOutput.orderOptions();
        if(choice == 1) {
            pay();
            Thread.sleep(1000);
            showCatalog();
        }
        else{
            showCatalog();
        }
    }
    /**
     * This method contains catalog Logic.<br>
     * It shows how system will react to the user input.
     * @throws InterruptedException
     */
    public void showCatalog() throws InterruptedException {
        Integer choice = inputOutput.showCatalogInfo(catalog);
        int cnt, catalogSize = catalog.getItems().size();
        // Show Item Details
        if(choice <= catalog.getItems().size()){
            cnt = 1;
            Item itemNeeded = new Item();
            for(Map.Entry<String,Item> pair : catalog.getItems().entrySet()){
                if(cnt == choice){
                    choice = inputOutput.showItemInfo(pair.getValue());
                    itemNeeded = pair.getValue();
                    break;
                }
                cnt++;
            }
            switch (choice) {
                case 1 -> {
                    user.getCart().addToCart(itemNeeded);
                    inputOutput.itemAdded();
                    Thread.sleep(1000);
                    showCatalog();
                }
                case 2 -> showCatalog();
            }
        }
        else{ // Add from Catalog.
            if(choice == catalogSize+1){
                choice = inputOutput.takeUserItem(catalog.getItems().size());
                cnt = 1;
                Item itemNeeded = new Item();
                for(Map.Entry<String,Item> pair : catalog.getItems().entrySet()) {
                    if (cnt == choice) {
                        itemNeeded = pair.getValue();
                        break;
                    }
                    cnt++;
                }
                user.getCart().addToCart(itemNeeded);
                inputOutput.itemAdded();
                Thread.sleep(1000);
                showCatalog();
            }
            else if(choice == catalogSize + 2){
                if(user.getCart().getItemsList().isEmpty()){
                    inputOutput.emptyCart();
                    Thread.sleep(1000);
                    showCatalog();
                }
                choice = showCart();
                if(choice == 1){
                    checkOut();
                } else if (choice == 2){
                    showCatalog();
                }
                else{
                    user.getCart().getItemsList().clear();
                    inputOutput.clearCart();
                    Thread.sleep(1000);
                    showCatalog();
                }
            }
            else{
                inputOutput.logOut();
                systemSteps();
            }
        }


    }

    /**
     * This method contains Cart Logic.<br>
     * It shows how system will react to the user input.
     * @return Integer
     */
    public Integer showCart(){
        return inputOutput.showCart(user.getUserName(), user.getCart());
    }
    /**
     * This method used to confirm or decline order.<br>
     * and make cart empty after that.
     */
    public void pay(){
        inputOutput.payDelivered();
        Cart cart = new Cart();
        user.setCart(cart);
    }

}
