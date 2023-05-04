package gui;

import actors.Attachtments.Order;
import actors.User;
import control.InputOutput;
import control.PaymentMethod;

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
       Order order = new Order();
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

    }
    public void pay(){};

    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface();
        userInterface.checkOut();
//        InputOutput inputOutput1 = new InputOutput();
//        inputOutput1.takeAddressInput();
    }

}
