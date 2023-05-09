package control;

import actors.Attachtments.Address;
import actors.User;
import control.Authentication.AuthenticationService;
import control.shop_items.Cart;
import control.shop_items.Catalog;
import control.shop_items.Item;
import gui.Message;
import model.DataBaseQueries;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

/**
 * <h3>Input and Output Class</h3>
 * This class is used to handle all the inputs and outputs happen in the system.
 * @author Mohamed Essam
 * @author Maya Ayman
 * @author Rawan Younis
 */
public class InputOutput {
    private final Scanner scanner = new Scanner(System.in);
    private final AuthenticationService authenticationService = new AuthenticationService();
    private final Message messageBox = new Message();

    /**
     * This Method takes all the user input as a template method,
     * and check weather he is registered or not.
     * @return User
     * @throws SQLException
     */
    public User takeUserInput() throws SQLException {
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

        messageBox.createMessage("User is Finally Registered",'C');
        scanner.nextLine();
        return user;
    }

    /**
     * This Method is output the registration form to the user
     */
    public void registerForm(){
        String form = "Welcome to Registration Form Page\n";
        form += "Some Notes\n Email Specification: Two dots can't appear after each other" +
                " \n Password Rules: at least one small, capital, number, symbol is needed" +
                " \n Phone Rules: must start with valid prefixes eg:{010,011,012,015} \n";
        messageBox.createMessage(form,'W');

    }

    /**
     * It takes username input from user.
     * @return String
     */
    public String takeUserNameInput(){
        System.out.print("Enter UserName: ");
        String userName = scanner.nextLine();
        return userName;
    }
    /**
     * It takes email input from user.
     * @return String
     */
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
    /**
     * It takes password input from user.
     * and then encode the password.
     * @return String
     */
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
                String encodedPassword = Base64.getEncoder().encodeToString(pass.getBytes());
                return encodedPassword;
            }
        } while (!pass.equals(passConfirm));

        String encodedPassword = Base64.getEncoder().encodeToString(pass.getBytes());
        return encodedPassword;
    }
    /**
     * It takes Phone Number input from user.
     * @return String
     */
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
    /**
     * It takes address input from user.
     * @return Address
     */
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
    /**
     * This Method shows the checkout options menu to the user.
     * <ol>
     * <li>Enter new Address.</li>
     * <li>User Address on the System.</li>
     * </ol>
     * @return Integer
     */
    public Integer checkOutOptions(){
        String info = "Proceeding Check out ... \n\n " +
                "<<< Available Options >>> \n" +
                "1.Enter a new Address. \n" +
                "2.Use Address on System. \n";
        return getInteger(info,2);
    }
    /**
     * This Method shows the Order options menu to the user.
     * <ol>
     * <li>Confirm Order.</li>
     * <li>Decline Order (return to catalog page).</li>
     * </ol>
     * @return Integer
     */
    public Integer orderOptions(){
        String info = "Proceeding Check out ... \n\n " +
                " ".repeat(7)+"<<< Available Options >>> \n" +
                "1.Confirm Order. \n" +
                "2.Decline Order (return to catalog page). \n";
        return getInteger(info,2);
    }

    /**
     * This Method takes integer input from the user<br>
     * and validate that user enters an integer not a string
     * @param info
     * @param optionSize
     * @return Integer
     */
    @NotNull
    public Integer getInteger(String info,int optionSize) {
        messageBox.createMessage(info,'W');

        ArrayList<Integer> optionsNumber = new ArrayList<>();
        for(int i = 1; i <= optionSize; ++i){
            optionsNumber.add(i);
        }

        boolean isValidInput = false;
        while(!isValidInput){
            validateIntegerInput("Enter Option Number: ");
            Integer choice = scanner.nextInt();
            isValidInput = checkCertainNumber(choice,optionsNumber);

            if(isValidInput){
                scanner.nextLine();
                return choice;
            }
        }
        return null;
    }

    /**
     * Validate that the user input is an Integer.
     * @param option
     */
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

    /**
     * This Method check that user enters a valid option number.
     * @param chosenOption
     * @param availableOptions
     * @return boolean
     */
    public boolean checkCertainNumber(int chosenOption, ArrayList<Integer> availableOptions){
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

    /**
     * This Method shows tha catalog menu to the user.<br>
     * @param catalog
     * @return Integer
     */
    public Integer showCatalogInfo(Catalog catalog){
        Integer CatalogSize = catalog.getItems().size();

        String catalogStr = " ".repeat(7)+"<<<Toffee Catalog>>>\n";
        int catalogSize = catalog.getItems().size();
        int cnt = 1;
        for(Map.Entry<String,Item> pair : catalog.getItems().entrySet()){
            catalogStr +=  Integer.toString(cnt++) + "." + pair.getKey() +
                    " ".repeat(28-pair.getKey().length()) + pair.getValue().getPrice() +" L.E";
            catalogStr += "\n";
        }
        catalogStr += "Choose any Item to show in details \n\n" +
                " ".repeat(1)+" <<< Other Available Options >>> \n" +
                (CatalogSize + 1) + ".Add Item to Cart. \n" +
                (CatalogSize + 2) + ".Show Cart. \n" +
                (CatalogSize + 3) + ".Log-Out.";

        return getInteger(catalogStr,CatalogSize+3);
    }

    /**
     * This Method shows the Item specification to the user.<br>
     * @param item
     * @return Integer
     */
    public Integer showItemInfo(Item item){
         String itemStr = " ".repeat(5) +
                 "<<< " + item.getName() + " >>> \n" +
                 "Description: " + item.getDescription() + ".\n" +
                 "Brand: " + item.getBrand() + ".\n" +
                 "Unit Type: " + item.getUnitType() + ".\n" +
                 "Item Status: " + item.getItemStatus() + ".\n" +
                 "Price: " + item.getPrice() + " L.E\n\n";

         itemStr += "<<< Available Options >>> \n" +
                 "1. Add Item to Cart.\n" +
                 "2. Go Back to Catalog.\n";

         return getInteger(itemStr,2);
    }

    /**
     * This Method takes the Item number from the user.<br>
     * @param catalogSize
     * @return Integer
     */
    public Integer takeUserItem(Integer catalogSize){
        return getInteger("Enter Item Number to add in the Cart",catalogSize);
    }

    /**
     * This Method shows tha Cart menu to the user.<br>
     * @param name
     * @param cart
     * @return Integer
     */
    public Integer showCart(String name,Cart cart){
        String cartStr = " ".repeat(15-name.length()) +"<<< " + name + "'s Cart >>> \n";
        int cnt = 1;
        for(Map.Entry<Item,Integer> pair : cart.getItemsList().entrySet()){
            cartStr += Integer.toString(cnt) + ". " + pair.getKey().getName()
                    + " ".repeat(28 - pair.getKey().getName().length())
                    + "Quantity: " + Integer.toString(pair.getValue())
                    + ".\n";
            cnt++;
        }
        cartStr += '\n';
        return cartOptions(cartStr);
    }

    /**
     * This Method shows tha Cart Options to the user.<br>
     * <li>Check Out</li>
     * <li>Return to catalog page</li>
     * <li>Empty the cart</li>
     * @param cartStr
     * @return Integer
     */
    public Integer cartOptions(String cartStr){
        cartStr += " ".repeat(6) + " <<< Available Options >>> \n" +
                "1. Check Out. \n" +
                "2. Go Back to Catalog.\n" +
                "3.Empty Cart.\n";

        return getInteger(cartStr,3);
    }

    /**
     * msg appears when add an item to the cart.
     */
    public void itemAdded(){
        messageBox.createMessage("Item Added Successfully",'C');
    }
    /**
     * msg appears when order is confirmed.
     */
    public void payDelivered(){
        messageBox.createMessage("Your Order has been Confirmed !", 'C');
    }

    /**
     * This Method shows the main menu page to the user.
     * @return Integer
     */
    public Integer mainMenu(){
       messageBox.mainMenuMsg();
       return getInteger("",4);
    }
    /**
     * msg appears when user logs out.
     */
    public void logOut(){
        messageBox.createMessage("You have logged out",'C');
    }
    /**
     * msg appears when application is closed.
     */
    public void exit(){
        String exitStr = "Thank You For Using our Application ! \n" +
                "Authors: \n  " +
                "Mohamed Essam. \n  " +
                "Maya Ayman. \n  " +
                "Rawan Younis. \n";
        messageBox.createMessage(exitStr,'G');
    }
    /**
     * msg appears when cart is cleared.
     */
    public void clearCart(){
        messageBox.createMessage("Cart has been cleared.",'C');
    }
    /**
     * msg appears when cart is empty.
     */
    public void emptyCart(){
        messageBox.createMessage("Cart is Empty !", 'R');
    }

    /**
     * It shows the login page to the user.
     * @return User
     */
    public User loginPage(){
        messageBox.createMessage("Welcome to Login Page",'W');
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes());
        boolean isFound = authenticationService.login(email,encodedPassword);
        if(isFound){
            User user = authenticationService.getLoggedUser(email);
            messageBox.createMessage("Welcome, " + user.getUserName(), 'C');
            return user;
        }
        else{
            loginFailed();
            return null;
        }
    }
    /**
     * msg appears when login failed.
     */
    public void loginFailed(){
        messageBox.createMessage("User isn't Found in the System !",'R');
    }
    /**
     * msg appears when password is changed.
     */
    public void passwordUpdated(){
        messageBox.createMessage("Password has been reset successfully!",'C');
    }

    /**
     * Method is used to make user change his old password.
     * @param user
     */
    public void forgetPassword(User user){
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        DataBaseQueries userDataBase = new DataBaseQueries();
        try {
            user = userDataBase.getUser(email);
            if(user == null){
                loginFailed();
            }
            else{
                if (authenticationService.forgotPassword(user)) {
                    authenticationService.resetPassword(user);
                    userDataBase.removeUser(user);
                    userDataBase.addUser(user);
                    passwordUpdated();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
