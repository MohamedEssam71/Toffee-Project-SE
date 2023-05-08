package gui;

import actors.Attachtments.Order;
import actors.User;
import control.Authentication.AuthenticationService;
import control.InputOutput;
import control.PaymentMethod;
import control.shop_items.Catalog;

public class UserInterface {
    private InputOutput inputOutput = new InputOutput();
    private User user = new User();

    private Catalog catalog = new Catalog();

    public User register(){
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

    public void checkOut(){
        user.getCart();
       Integer choice = inputOutput.checkOutOptions();
       if(choice == 1) {
           user.setAddress(inputOutput.takeAddressInput());
       }
       Order order = new Order(user);
       /*
        order.adjustTotalPrice(cart);
        order.adjustLoyaltyPoint(cart);
        PaymentMethod paymentMethod = new Delivery();
        order.showOrderDetails();
       */
        choice = inputOutput.orderOptions();
        if(choice == 1) {
            pay();
        }
        else{
            showCatalog();
        }
    }

//    public PaymentMethod pay(){}
    public void showCatalog(){
        /*
        String catalogStr = " ".repeat(7)+"<<<Toffee Catalog>>>\n";
        for(int i = 0; i < catalog.getItemList().size(); ++i){
            catalogStr +=  Integer.toString(i+1) + "." + catalog.getItemList().get(i).getName();
            catalogStr += "\n";
        }
        inputOutput.showCatalogInfo(catalogStr,catalog.getItemList().size());
        System.out.println("Enter Option Number: ");
        */
    }
    public void pay(){};

    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface();
//        userInterface.checkOut();
        userInterface.showCatalog();
//        InputOutput inputOutput1 = new InputOutput();
//        inputOutput1.takeAddressInput();
    }

}
