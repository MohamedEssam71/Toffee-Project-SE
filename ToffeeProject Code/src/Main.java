import actors.User;
import gui.UserInterface;

public class Main {
    public static void main(String[] args) {
        User user = new User("name", "email", "pass", "012");
        UserInterface userInterface = new UserInterface();
        userInterface.forgotPassword(user);
        System.out.println(user.getPassword());
    }
}