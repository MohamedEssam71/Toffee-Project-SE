package gui;

import actors.Address;
import actors.User;
import control.AuthenticationService;
import control.InputOutput;

import java.util.Scanner;

public class UserInterface {
    private InputOutput inputOutput = new InputOutput();
    public User register(){
        User user = inputOutput.takeUserInput();
        return user;
    }

    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface();
        userInterface.register();
    }

}
