package Utility;

import java.util.Scanner;

public class UI {
    // A
    Scanner scan = new Scanner(System.in);

    //A method to display a message to user
    public void displayMessage(String msg){
        System.out.println(msg);
    }

    // A method to get input from user
    public String getInput(String msg){
        displayMessage(msg);
        String input = scan.nextLine();
        return input;
    }
}
