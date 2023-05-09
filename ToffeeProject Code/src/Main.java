import gui.UserInterface;

/**
 * Main Class of Toffee Project System.
 * This System has a main menu to log in,register, and forgetPassword.<br>
 * It has a catalog page, item page, check out page, order receipt, and address page.<br>
 * @author Mohamed Essam
 * @author Maya Ayman
 * @author Rawan Younis
 * @client Dr.Mohammed El-Ramly.
 */
public class Main {
    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface();
        try {
            userInterface.systemSteps();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}