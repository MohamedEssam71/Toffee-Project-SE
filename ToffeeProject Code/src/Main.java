import gui.UserInterface;

/**
 * <H2>Toffee Project</H2>
 * Main Class of Toffee Project System.
 * This System has a main menu to log in,register, and forgetPassword.<br>
 * It has a catalog page, item page, check out page, order receipt, and address page.<br>
 * @author Mohamed Essam
 * @author Maya Ayman
 * @author Rawan Younis
 * @Client Dr.Mohammed El-Ramly.
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