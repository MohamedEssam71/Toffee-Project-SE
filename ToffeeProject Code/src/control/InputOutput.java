package control;

import actors.Address;
import actors.User;
import gui.Message;

import java.util.Scanner;

public class InputOutput {
    private Scanner scanner = new Scanner(System.in);
    private AuthenticationService authenticationService = new AuthenticationService();
    private Message messageBox = new Message();

    public User takeUserInput(){
        User user = new User();

        boolean isRegistered, firstTime = true;
        do {
            registerForm();
            String userName = takeUserNameInput();
            String email = takeEmailInput();
            String password = takePasswordInput();
            String phoneNumber = takePhoneNumberInput();

            System.out.println();

            while(firstTime){
                Address address = takeAddressInput();
                user.setAddress(address);
                firstTime = false;
            }
            user.setUserName(userName);
            user.setEmail(email);
            user.setPassword(password);
            user.setPhoneNumber(phoneNumber);

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

        System.out.print("Enter Building Number: ");
        dataInt = scanner.nextInt();
        address.setBuildingNumber(dataInt);

        System.out.print("Enter Floor Number: ");
        dataInt = scanner.nextInt();
        address.setFloor(dataInt);

        System.out.print("Enter Flat Number: ");
        dataInt = scanner.nextInt();
        address.setFlatNumber(dataInt);

        return address;
    }


}
