package control;

import actors.Attachtments.Address;
import actors.User;
import control.Authentication.AuthenticationService;
import gui.Message;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Scanner;

public class InputOutput {
    private final Scanner scanner = new Scanner(System.in);
    private final AuthenticationService authenticationService = new AuthenticationService();
    private final Message messageBox = new Message();

    public User takeUserInput(){
        User user = new User();

        boolean isRegistered;
        do {
            registerForm();
            String userName = takeUserNameInput();
            String email = takeEmailInput();
            String password = takePasswordInput();
            String phoneNumber = takePhoneNumberInput();
            Address address = takeAddressInput();

            user.setUserName(userName);
            user.setEmail(email);
            user.setPassword(password);
            user.setPhoneNumber(phoneNumber);
            user.setAddress(address);

            isRegistered = authenticationService.register(user);
            if (!isRegistered) {
                messageBox.createMessage("User is Already Registered in System ", 'R');
            }
        }while(!isRegistered);

        messageBox.createMessage("User is Finally Registered",'G');
        return user;
    }
    public void registerForm(){
        String form = "Welcome to Registration Form\n";
        form += "Some Notes\n Email Specification: Two dots can't appear after each other" +
                " \n Password Rules: at least one small, capital, number, symbol is needed" +
                " \n Phone Rules: must start with valid prefixes eg:{010,011,012,015} \n";
        messageBox.createMessage(form,'W');

    }
    public String takeUserNameInput(){
        System.out.print("Enter UserName: ");
        String userName = scanner.nextLine();
        return userName;
    }
    public String takeEmailInput(){
        boolean isValidEmail;
        do {
            System.out.print("Enter Email: ");
            String email = scanner.nextLine();
            isValidEmail = authenticationService.validateEmail(email);

            if (!isValidEmail) {
                messageBox.createMessage("Email is not Valid", 'R');
            } else {
                return email;
            }
        } while (!isValidEmail);
        return null;
    }
    public String takePasswordInput(){
        String pass, passConfirm;
        do {
            boolean isValidPass;
            do {
                System.out.print("Enter Password: ");
                pass = scanner.nextLine();
                isValidPass = authenticationService.validatePassword(pass);
                if (!isValidPass) {
                    messageBox.createMessage("Password is not Strong Enough", 'R');
                }
            } while (!isValidPass);

            System.out.print("Confirm Password: ");
            passConfirm = scanner.nextLine();

            if (!pass.equals(passConfirm)) {
                messageBox.createMessage("Two Password is not matching !", 'R');
            } else {
                return pass;
            }
        } while (!pass.equals(passConfirm));

        return pass;
    }
    public String takePhoneNumberInput(){
        boolean isValidPhone;
        do {
            System.out.print("Enter Phone Number: ");
            String phoneNumber = scanner.nextLine();
            isValidPhone = authenticationService.validatePhone(phoneNumber);
            if (!isValidPhone) {
                messageBox.createMessage("Phone number isn't Correct", 'R');
            } else {
                return phoneNumber;
            }
        } while (!isValidPhone);
        return null;
    }
    public Address takeAddressInput(){
        System.out.println();
        Address address = new Address();
        String dataStr;
        Integer dataInt;
        messageBox.createMessage("Insert Address Information", 'W');
        System.out.print("Enter Governorate: ");
        dataStr = scanner.nextLine();
        address.setGovernorate(dataStr);

        System.out.print("Enter District: ");
        dataStr = scanner.nextLine();
        address.setDistrict(dataStr);

        System.out.print("Enter Land Mark: ");
        dataStr = scanner.nextLine();
        address.setLandmark(dataStr);

        System.out.print("Enter Street: ");
        dataStr = scanner.nextLine();
        address.setStreet(dataStr);

        validateIntegerInput("Enter Building Number: ");
        dataInt = scanner.nextInt();
        address.setBuildingNumber(dataInt);

        validateIntegerInput("Enter Floor Number: ");
        dataInt = scanner.nextInt();
        address.setFloor(dataInt);

        validateIntegerInput("Enter Flat Number: ");
        dataInt = scanner.nextInt();
        address.setFlatNumber(dataInt);

        return address;
    }
    public Integer checkOutOptions(){
        String info = "Proceeding Check out ... \n\n " +
                "<<< Available Options >>> \n" +
                "1.Enter a new Address. \n" +
                "2.Use Address on System. \n";
        return getInteger(info);
    }

    public Integer orderOptions(){
        String info = "Proceeding Check out ... \n\n " +
                " ".repeat(7)+"<<< Available Options >>> \n" +
                "1.Confirm Order. \n" +
                "2.Decline Order (return to catalog page). \n";
        return getInteger(info);
    }

    @NotNull
    public Integer getInteger(String info) {
        messageBox.createMessage(info,'W');

        boolean isValidInput = false;
        while(!isValidInput){
            validateIntegerInput("Enter Option Number: ");
            Integer choice = scanner.nextInt();
            isValidInput = checkCertainNumber(choice,1,2);

            if(isValidInput){
                scanner.nextLine();
                return choice;
            }

        }
        return null;
    }


    public void validateIntegerInput(String option){
        boolean isInt = false;

        while(!isInt){
            try {
                System.out.print(option);
                isInt = scanner.hasNextInt();
                if(!isInt) throw new IOException();
            }
            catch (Exception err){
                scanner.nextLine();
                messageBox.createMessage("Please Enter a Number not String !",'R');

            }
        }
    }

    public boolean checkCertainNumber(int chosenOption,int  ...availableOptions){
        for(int i : availableOptions){
            if(chosenOption != i){
                continue;
            }
            return true;
        }
        scanner.nextLine();
        messageBox.createMessage("Option is not available",'R');
        return false;
    }

    public void showCatalogInfo(String info, Integer CatalogSize){
        info += "Choose any Item to show in details \n\n" +
                " ".repeat(1)+" <<< Other Available Options >>> \n" +
                (CatalogSize + 1) + ".Add Item to Cart. \n" +
                (CatalogSize + 2) + ".Show Cart. \n";

        messageBox.createMessage(info,'W');
    }
    public void catalogOptions(){

    }
}
