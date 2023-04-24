package Utility;

import java.util.Scanner;

public class UI {
    Scanner scan = new Scanner(System.in);

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
}
