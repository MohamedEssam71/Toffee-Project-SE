package control.Authentication;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class OTPManager {
    private String OTP;
    public void generateOTP() {
        String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String Small_chars = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String symbols = "!@#$%^&*_=+-/.?<>";
        String values = Capital_chars + Small_chars + numbers + symbols;

        Random rndm_method = new Random();

        StringBuilder password = new StringBuilder();

        for (int i = 0; i <= 64; i++) {
            password.append(values.charAt(rndm_method.nextInt(values.length())));
        }

        setOTP(password.toString());
    }
    public void sendOTP(String email) {

    }
    public boolean verifyOTP() {
        System.out.println("Please Enter the OTP:");
        Scanner scanner = new Scanner(System.in);
        String inOTP = scanner.nextLine();
        return Objects.equals(inOTP, OTP);
    }

    public String getOTP() {
        return OTP;
    }

    public void setOTP(String OTP) {
        this.OTP = OTP;
    }
}
