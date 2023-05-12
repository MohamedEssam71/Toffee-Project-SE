package control.Authentication;

import actors.User;
import control.InputOutput;
import gui.Message;
import model.DataBaseQueries;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <h3>Authentication Service Class</h3>
 * It is used to handle all the authentication logic system.<br>
 * Some of its methods:
 * <ul>
 *     <li>Register</li>
 *     <li>Log-in</li>
 *     <li>Forget Password</li>
 *     <li>Validation methods/li>
 * </ul>
 * @author Maya Ayman
 * @author Rawan Younis
 * @author Mohamed Essam
 */
public class AuthenticationService {

    /**
     * This Method is used to validate Password Input.<br>
     * <h3>Strong Password Specifications:</h3>
     * <ul>
     *     <li>Minimum length 8 characters.</li>
     *     <li>Max length 20 characters.</li>
     *     <li>At least one capital letter.</li>
     *     <li>At least one small letter.</li>
     *     <li>At least one symbol.</li>
     * </ul>
     * @param password
     * @return boolean
     */
    public boolean validatePassword(String password) {
        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\-!@#$%^&*_=+/.?<>]).{8,20}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    /**
     * This Method is used to validate Email Input.<br>
     * <h3>Email input specifications:</h3>
     * <ul>
     *     <li>Must be in the format (localpart@domain).</li>
     *     <li>Must not contain two consecutive dots</li>
     *     <li>Local and domain parts must start with alphanumeric characters only.</li>
     * </ul>
     * @param email
     * @return boolean
     */
    public boolean validateEmail(String email) {
        String pattern =  "^(?![.-_+])([aA-zZ0-9_+-](\\.)?)+" +
                "@(?![.-_+])[aA-zZ0-9_+-]+\\.(?!\\.)[a-zA-Z0-9-]+[a-zA-Z0-9-.]+$";
        return email.matches(pattern);
    }


    /**
     * This Method is used to validate phone number of the user
     * if it follows some certain guidelines.<br>
     * eg: Number should start with {010,011,012,015}.
     * @param phoneNumber
     * @return boolean
     */
    public boolean validatePhone(String phoneNumber) {
        return Pattern.matches("01[0|1|2|5]\\d{8}", phoneNumber);
    }


    /**
     * This Method is used to check if user found in the database or not,
     * Then add user to the database.
     * @param user
     * @return boolean weather the user is registered or not
     * @throws SQLException to handle database errors
     */
    public boolean register(User user) throws SQLException {
        DataBaseQueries userDataBase = new DataBaseQueries();
        boolean isFound = userDataBase.checkIfUserFound(user,false);
        if (!isFound) {
            userDataBase.addUser(user);
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method sends an OTP to allow the user to reset
     * his/her password.
     * @param user This is the only parameter to the method
     * @return boolean
     */
    public Boolean forgotPassword(@NotNull User user) {
        OTPManager otpManager = new OTPManager();
        boolean isSent = otpManager.sendOTP(user.getEmail());
        Message messageBox  = new Message();
        if (isSent) {
            messageBox.createMessage("OTP Sent Successfully!",'C');
        } else {
            messageBox.createMessage("Failed to send OTP code.\n" +
                    "Check your internet connection and try again.",'R');
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return false;
        }
        return (otpManager.verifyOTP());
    }
    
    /**
     * This method allow the user to reset his/her password.
     * @param user This is the only parameter to the method
     */
    public void resetPassword(@NotNull User user) {
        InputOutput IO = new InputOutput();
        user.setPassword(IO.takePasswordInput());
    }

    /**
     * This method checks if a user with the
     * given email and password exists in the database.
     * @param email
     * @param password
     * @return boolean
     * @throws RuntimeException to handle database errors
     */
    public boolean login(String email, String password){
        User tmpUser = new User();
        tmpUser.setPassword(password);
        tmpUser.setEmail(email);

        DataBaseQueries userDataBase = new DataBaseQueries();
        boolean isFound = false;
        try {
            isFound = userDataBase.checkIfUserFound(tmpUser,true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isFound;
    }

    /**
     * This Method returns a user with the given email.
     * @param email: the email of the user who wants to log in
     * @return User
     * @throws RuntimeException to handle database errors
     */
    public User getLoggedUser(String email){
        DataBaseQueries userDataBase = new DataBaseQueries();
        User user;
        try {
            user = userDataBase.getUser(email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public void logout(){
//        TODO: run();
    }
}
