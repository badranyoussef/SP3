
package Utility;

import Entity.Media;
import Entity.Movie;
import Entity.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;



public class IO {
    private File file;
    private Scanner scan;
    private Set<Media> setOfMedia = new HashSet<>();
    // A method to read the user data saved in the application
    public List<String> readData(String path){
        file = new File(path);
        List<String> data = new ArrayList<>();
        try{
            scan = new Scanner(file);
            while(scan.hasNextLine()){
                String line = scan.nextLine();
                data.add(line);
            }
        }catch (FileNotFoundException e){
            System.out.println("User data file was not found");
        }
        return data;
    }
    public List<User> readUserData(String path) {
        file = new File(path);
        List<User> data = new ArrayList<>();
        try {
            scan = new Scanner(file);
            scan.nextLine(); // ignore header in csv
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] values = line.split(",");
                data.add(new User(values[0], values[1], values[2]));
            }
        } catch (FileNotFoundException e) {
            System.out.println("User data file was not found");
        }
        return data;
    }

    // A method to save new users
    public void saveUsers(String path, List<User> userList) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(path, true);
            for (User u : userList) {
                writer.write(u.getName() + "," + u.getUserName() + "," + u.getPassword() + u.getWatched() + "\n");
            }
            writer.close();
        } catch (IOException e) {
        }
    }

    public Set<Media> readMovieData(String path) {
        File file = new File(path); // /src/Data/movies.txt
        try (Scanner scan = new Scanner(file)) {
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] arrLine = line.split(";", 5);
                if (arrLine.length >= 4) {
                    String title = arrLine[0].trim();
                    int releaseYear = Integer.parseInt(arrLine[1].trim());
                    String stringOfCategories = arrLine[2].trim();
                    String[] arrCategories = stringOfCategories.split(", ", 5);
                    ArrayList<String> categories = new ArrayList<>(Arrays.asList(arrCategories));
                    String ratingStr = arrLine[3].trim().replace(",", ".");
                    float rating = Float.parseFloat(ratingStr);
                    Movie m = new Movie(title, categories, rating, releaseYear);
                    setOfMedia.add(m);
                }
            }
            return this.setOfMedia;
        } catch (FileNotFoundException e) {
            System.out.println("The file movies.txt was not found: " + e.getMessage());
        }
        return setOfMedia;
    }
}
