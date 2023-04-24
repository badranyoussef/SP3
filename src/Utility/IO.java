package Utility;

import Entity.User;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IO {

    private File file;
    private Scanner scan;

    // A method to read the user data saved in the application
    public List<User> readUserData(String path) {

        file = new File(path);
        List<User> data = new ArrayList<>();


        try {
            scan = new Scanner(file);

            scan.nextLine(); // ignore header in csv

            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] values = line.split(",");
                data.add(new User(values[0],values[1],values[2]));
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file was not found");

        }

        return data;
    }

    // A method to save new users
    public void saveData(String path, List<User> userList) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(path,true);

            for (User u : userList) {
                writer.write(u.getName() + "," + u.getUserName()+ "," + u.getPassword() + "\n");
            }

            writer.close();


        } catch (IOException e) {


        }

    }

}
