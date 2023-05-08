package control.Authentication;

import actors.Attachtments.Order;
import actors.User;
import control.InputOutput;
import control.shop_items.Cart;
import control.shop_items.Item;
import control.shop_items.ItemStatus;
import gui.Message;
import model.DataBaseQueries;
import model.UserDataBase;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthenticationService {
    public boolean validatePassword(String password) {
        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\-!@#$%^&*_=+/.?<>]).{8,20}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }


    public boolean validateEmail(String email) {
        String pattern = "^(?![.\\-_+])([aA-zZ0-9_+-][.]?)+@[aA-zZ0-9_+-]+\\.(?!\\.)([aA-zZ0-9_+-][.]?)+$";
        return email.matches(pattern);
    }


    public boolean validatePhone(String phoneNumber) {
        return Pattern.matches("01[0|1|2|5]\\d{8}", phoneNumber);
    }



    public boolean register(User user) throws SQLException {
//        UserDataBase userDataBase = new UserDataBase();
        DataBaseQueries dataBaseQueries = new DataBaseQueries();
        boolean isFound = dataBaseQueries.checkIfUserFound(user);
        if (!isFound) {
            dataBaseQueries.addUser(user);
            return true;
        } else {
            return false;
        }
    }


        // OTP Manager Class
    public Boolean forgotPassword(@NotNull User user) {
        OTPManager otpManager = new OTPManager();
        otpManager.generateOTP();
        otpManager.sendOTP(user.getEmail());
        return (otpManager.verifyOTP());
    }

    public void resetPassword(@NotNull User user) {
        InputOutput IO = new InputOutput();
        user.setPassword(IO.takePasswordInput());
        System.out.println("Password has been reset successfully!");
    }

//    public static void main(String[] args) {
//        AuthenticationService authenticationService = new AuthenticationService();
//        if (authenticationService.forgotPassword("")) {
//                System.out.println("OTPs Match!");
////            reset password here
////            System.out.println("Please Enter a New Password:");
////            Scanner scanner = new Scanner(System.in);
//        } else {
//            System.out.println("Sorry...OTPs Don't Match!");
//        }
//    }
}
//    public static void main(String[] args) throws IOException {
//
//    }
//        AuthenticationService  auth = new AuthenticationService();
//        String pass = "MohamedEssam71";
//        System.out.println(auth.checkPassword(pass));

  //------------------------Use Threading to hide password -----------------------------

//        ThreadDisappear td = new ThreadDisappear("Enter your password: ");
//        Thread t = new Thread(td);
//        t.start();
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        try {
//            String password = br.readLine();
//            td.maskEnd();
//            System.out.println("\nYour password is: " + password);
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        }

//------------------------Use Console to hide password -----------------------------
//        Console console = System.console();
//        if (console == null) {
//            System.out.println("Console not available");
//            System.exit(1);
//        }
//
//        char[] passwordArray = console.readPassword("Enter your password: ");
//        String password = new String(passwordArray);
//        Writer writer = new PrintWriter(System.out);
//        writer.append("*".repeat(password.length()));
//        writer.flush();
//
//
//    }


//}


//class ThreadDisappear implements Runnable {
//    private boolean end;
//    public ThreadDisappear(String prompt) {
//        System.out.print(prompt);
//    }
//    public void run() {
//        end = true;
//        while (end) {
//            System.out.print("\010*");
//            try {
//                Thread.currentThread().sleep(1);
//            } catch (InterruptedException ie) {
//                ie.printStackTrace();
//            }
//        }
//    }
//
//    public void maskEnd() {
//        this.end = false;
//
//    }
//}