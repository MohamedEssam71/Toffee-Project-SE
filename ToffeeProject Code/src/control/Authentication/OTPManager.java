package control.Authentication;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

/**
 * This is the OTP manager class; it is responsible for
 * generating OTP, sending OTP, and verifying the OTP.
 *
 * @author Maya Ayman
 */
public class OTPManager {
    private String OTP;
    /**
     * This method generates an OTP from a mix of
     * uppercase and lowercase letters and numbers
     */
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
    /**
     * This method displays the OTP on the console.
     */
    public void sendOTP(String email) {
        System.out.println(OTP);
    }
    /**
     * This method verifies that the OTP entered by
     * the user matches the one sent.
     * @return boolean
     */
    public boolean verifyOTP() {
        System.out.println("Please Enter the OTP:");
        Scanner scanner = new Scanner(System.in);
        String inOTP = scanner.nextLine();
        return Objects.equals(inOTP, OTP);
    }
}
