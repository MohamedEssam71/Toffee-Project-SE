package control.Authentication;

import actors.User;
import gui.Message;
import model.UserDataBase;

import java.io.*;
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
        Pattern emailPattern = Pattern.compile("[\\w]+(.|_)?(\\w*[^.]([.]?\\w+)?)@(\\w+)(.[\\w]+)?");
        Matcher emailMatcher = emailPattern.matcher(email);
        return emailMatcher.matches();
    }



    public boolean validatePhone(String phoneNumber) {
        return Pattern.matches("01[0|1|2|5]\\d{8}", phoneNumber);
    }



    public boolean register(User user) {
        UserDataBase userDataBase = new UserDataBase();
        boolean isFound = userDataBase.checkIfUserFound(user);
        if (!isFound) {
            userDataBase.addUser(user);
            return true;
        } else {
            return false;
        }
    }

    public void forgotPassword(String email) {
        // OTP Manager Class
        OTPManager otpManager = new OTPManager();
        otpManager.generateOTP();
//        otpManager.sendOTP("");
        if (otpManager.verifyOTP()) {
            System.out.println("OTPs Match!");
//            System.out.println("Please Enter a New Password:");
//            Scanner scanner = new Scanner(System.in);
            //Should I get the user here and reset the password or shall another part handle this?

        } else {
            System.out.println("OTPs Don't Match!");
            //How should I proceed here?
        }
    }
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