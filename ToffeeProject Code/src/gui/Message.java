package gui;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * <h3>Message Class</h3>
 * This class handles all the msg format that appears to the user.
 * @author Mohamed Essam
 */
public class Message {
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String CYAN = "\u001B[36m";
    private static final String RESET = "\u001B[0m";

    /**
     * This method create a msg format with different colors to the text.
     * @param message the content that will be added inside the box.
     * @param color to modify the text color in the msg box.
     */
    public void createMessage(@NotNull String message, char color) {
        if(message.isEmpty()) {
            return;
        }
        String[] strArr = message.split("\n");
        int size = Arrays.stream(strArr).map(String::length).max(Integer::compare).get();

        String dashedLine = "-".repeat(size + 2);
        System.out.print('+');
        System.out.print(dashedLine);
        System.out.println('+');

        switch (color) {
            case 'R': {
                for (String msg : strArr) {
                    System.out.println("| " + RED + msg + RESET + " ".repeat(size - msg.length()) + " |");
                }
                break;
            }
            case 'G': {
                for (String msg : strArr) {
                    System.out.println("| " + GREEN + msg + RESET + " ".repeat(size - msg.length()) + " |");
                }
                break;
            }
            case 'C': {
                for (String msg : strArr) {
                    System.out.println("| " + CYAN + msg + RESET + " ".repeat(size - msg.length()) + " |");
                }
                break;
            }
            default: {
                for (String msg : strArr) {
                    System.out.println("| " + msg + " ".repeat(size - msg.length()) + " |");
                }
            }
        }

        System.out.print('+');
        System.out.print(dashedLine);
        System.out.println('+');
    }

    /**
     * This method shows the main menu format to the user.
     */
    public void mainMenuMsg(){
        String dashedLine = "-".repeat(28 + 2);
        System.out.print('+');
        System.out.print(dashedLine);
        System.out.println('+');
        String mainMenuStr = "|" + " ".repeat(5) + CYAN + "   TOFFEE PROJECT"
                + RESET + " ".repeat(8) + "|\n" +
                "| 1.Log-In" + " ".repeat(21) + "|\n" +
                "| 2.Register" + " ".repeat(19) + "|\n" +
                "| 3.Forgot Password?" + " ".repeat(11) + "|\n" +
                "| 4.Continue as a Guest" + " ".repeat(8) + "|\n" +
                "| 5.Exit" + " ".repeat(23) + "|\n" ;
        System.out.print(mainMenuStr);
        System.out.print('+');
        System.out.print(dashedLine);
        System.out.println('+');
    }

}
