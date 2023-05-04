package gui;

import actors.Address;
import actors.User;
import control.AuthenticationService;
import control.InputOutput;
import control.PaymentMethod;
import control.shop_items.Cart;
import control.shop_items.Catalog;

import java.util.Scanner;

public class UserInterface {
    private InputOutput inputOutput = new InputOutput();
    private User user = new User();

    public User register(){
        user = inputOutput.takeUserInput();
        return user;
    }

    public User logIn(){
        return user;
    }

    public void forgetPassword(){}

    public void checkOut(){
        user.getCart();
       Integer choice = inputOutput.checkOutOptions();
       if(choice == 1) {
           user.setAddress(inputOutput.takeAddressInput());
       }
       Order

    }

//    public PaymentMethod pay(){}
    public void showCatalog(){

    }



    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface();
        userInterface.register();
    }

}
