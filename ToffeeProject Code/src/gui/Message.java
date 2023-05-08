package gui;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class Message {
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String CYAN = "\u001B[36m";
    private static final String RESET = "\u001B[0m";

    public void createMessage(@NotNull String message, char color) {
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


//    public static void main(String[] args) {
//        Message message = new Message();
//        message.createMessage("You Are a loser",false);
//    }
}
