package gui;

import actors.Address;
import actors.User;
import control.AuthenticationService;

import java.util.Scanner;

public class UserInterface {

    public User register(){
        Message messageBox = new Message();
        Scanner scanner = new Scanner(System.in);
        User user = new User();
        AuthenticationService authenticationService = new AuthenticationService();

        String form = "Welcome to Registration Form\n";
        form += "Some Notes\n Email Specification: Two dots can't appear after each other" +
                " \n Password Rules: at least one small, capital, number, symbol is needed" +
                " \n Phone Rules: must start with valid prefixes eg:{010,011,012,015} \n";



        boolean isRegistered, firstTime = true;
        do {
            messageBox.createMessage(form,'W');
            System.out.print("Enter UserName: ");
            String userName = scanner.nextLine();
            user.setUserName(userName);

            boolean isValidEmail;
            do {
                System.out.print("Enter Email: ");
                String email = scanner.nextLine();
                isValidEmail = authenticationService.validateEmail(email);

                if (!isValidEmail) {
                    messageBox.createMessage("Email is not Valid", 'R');
                } else {
                    user.setEmail(email);
                }
            } while (!isValidEmail);

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
                    user.setPassword(pass);
                }
            } while (!pass.equals(passConfirm));


            boolean isValidPhone;
            do {
                System.out.print("Enter Phone Number: ");
                String phoneNumber = scanner.nextLine();
                isValidPhone = authenticationService.validatePhone(phoneNumber);
                if (!isValidPhone) {
                    messageBox.createMessage("Phone number isn't Correct", 'R');
                } else {
                    user.setPhoneNumber(phoneNumber);
                }
            } while (!isValidPhone);


            System.out.print("-".repeat(35));
            System.out.println();

            while(firstTime){
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

                user.setAddress(address);
                firstTime = false;
            }

            isRegistered = authenticationService.register(user);
            if (!isRegistered) {
                messageBox.createMessage("User is Already Registered in System ", 'R');
            }
        }while(!isRegistered);


        messageBox.createMessage("User is Finally Registered",'G');
        return user;
    }

    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface();
        userInterface.register();
    }

}
