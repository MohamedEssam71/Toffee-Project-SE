package control.Authentication;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Objects;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

/**
 * This is the OTP manager class; it is responsible for
 * generating OTP, sending OTP, and verifying the OTP.
 *
 * @author Maya Ayman
 * @author Mohamed Essam
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
     * This method sends an OTP to
     * the user's email.
     * @param email: user's email
     * @return boolean
     */
    public boolean sendOTP(String email) {
        String from = "mrmtoffee@gmail.com";
        String password = "xufremtbjjziwyle";

        generateOTP();

        Properties properties = System.getProperties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", 465);

        Session session = Session.getInstance(properties, new Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(from);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("Your One-Time Password (OTP)");
            message.setText("Use this OTP to reset your password: " + OTP);

            Transport.send(message);
        } catch (MessagingException ex) {
            return false;
        }
        return true;
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
