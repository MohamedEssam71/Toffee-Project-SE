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
        String values = Capital_chars + Small_chars + numbers;

        Random random = new Random();
        StringBuilder password = new StringBuilder();
        for (int i = 0; i <= 20; i++) {
            password.append(values.charAt(random.nextInt(values.length())));
        }

        OTP = password.toString();
    }
    public void sendOTP(String email) {
        System.out.println(OTP);
    }
    public boolean verifyOTP() {
        System.out.println("Please Enter the OTP:");
        Scanner scanner = new Scanner(System.in);
        String inOTP = scanner.nextLine();
        return Objects.equals(inOTP, OTP);
    }
}
