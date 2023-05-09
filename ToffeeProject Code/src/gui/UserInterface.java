package gui;

import actors.Attachtments.Order;
import actors.User;
import control.Authentication.AuthenticationService;
import control.DeliveryPay;
import control.InputOutput;
import control.PaymentMethod;
import control.shop_items.Cart;
import control.shop_items.Catalog;
import control.shop_items.Item;
import model.DataBaseQueries;

import java.sql.SQLException;
import java.util.Map;

public class UserInterface {
    private InputOutput inputOutput = new InputOutput();
    private User user = new User();

    private Catalog catalog = new Catalog();

    public UserInterface(){
        inputOutput.mainMenu();
        // To be Continued ....33433
    }
    public User register() throws SQLException {
        user = inputOutput.takeUserInput();
        return user;
    }

    public User logIn(){
        return user;
    }

    public void forgotPassword(User user) {
        AuthenticationService authenticationService = new AuthenticationService();
        if (authenticationService.forgotPassword(user)) {
            authenticationService.resetPassword(user);
        }
    }

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

//    public PaymentMethod pay(){}
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
            switch (choice){
                case 1:{
                    user.getCart().addToCart(itemNeeded);
                    inputOutput.itemAdded();
                    Thread.sleep(1000);
                    showCatalog();
                    break;
                }
                case 2:{
                    showCatalog();
                    break;
                }
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
                choice = showCart();
                if(choice == 1){
                    checkOut();
                } else{
                    showCatalog();
                }
            }
            else{

            }
        }


    }

    public Integer showCart(){
        Integer choice = inputOutput.showCart(user.getUserName(), user.getCart());
        return choice;
    }

    public void pay(){
        inputOutput.payDelivered();
        Cart cart = new Cart();
        user.setCart(cart);
    };

    public static void main(String[] args) {

        UserInterface userInterface = new UserInterface();
        DataBaseQueries dataBaseQueries = new DataBaseQueries();
        try {
            userInterface.catalog.setItems(dataBaseQueries.loadItems());
            userInterface.showCatalog();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

//        try {
//            User user = dataBaseQueries.getUser("MRM");
//            System.out.println(user.getUserName());
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
////        userInterface.checkOut();
//        try {
//            userInterface.register();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }



    }

}
