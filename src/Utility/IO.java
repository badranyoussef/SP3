package Utility;
import Entity.Media;
import Entity.Movie;
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
    private List<Media> list = new ArrayList<>();
    private ArrayList<String> category1 = new ArrayList<>();
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
    public void saveUsers(String path, List<User> userList) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(path, true);
            for (User u : userList) {
                writer.write(u.getName() + "," + u.getUserName() + "," + u.getPassword() + "\n");
            }
            writer.close();
        } catch (IOException e) {
        }
    }

    public List<Media> readMovieData() {
        String title = "";
        int releaseYear = 0;
        String category2 = "";
        float rating = 0;
        File file = new File("src/Data/movies.txt");
        try (Scanner scan = new Scanner(file)) {
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] arrLine = line.split(";", 5);
                if (arrLine.length >= 4) {
                    title = arrLine[0].trim();
                    releaseYear = Integer.parseInt(arrLine[1].trim());
                    category2 = arrLine[2].trim();
                    String s = category2;
                    String ratingStr = arrLine[3].trim().replace(",", ".");
                    rating = Float.parseFloat(ratingStr);
                    Movie movie = new Movie(title /*category1.add(s)*/, rating, releaseYear);
                    list.add(movie);
                }
            }
            return this.list;
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
        return list;
    }
}
