package Utility;

import java.util.Scanner;

public class UI {
    private Scanner scan = new Scanner(System.in);

    // A method to display a message to user
    public void displayMessage(String msg) {
        System.out.println(msg);
    }

    // A method to get input from user with a message
    public String getInput(String msg) {
        displayMessage(msg);
        String input = scan.nextLine();
        return input;
    }

    public String processUserInput(String message) {
        Scanner scan = new Scanner(System.in);
        String input;

            displayMessage(message);
        while (true) {
            input = scan.nextLine();

            if (input.equals("1")) {
                return input;
            } else if (input.equals("2")) {
                return input;
            } else if (input.equals("3")) {
                return input;
            } else if (input.equals("4")) {
                return input;
            } else if (input.equals("5")) {
                return input;
            } else if (input.equals("6")) {
                return input;
            } else if (input.equalsIgnoreCase("X")) {
                return input;
            } else if (input.equalsIgnoreCase("Y")) {
                return input;
            } else if (input.equalsIgnoreCase("N")) {
                return input;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }
}
