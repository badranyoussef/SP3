
package Utility;

import Entity.Media;
import Entity.Movie;
import Entity.Series;
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
    public List<String> readData(String path) {
        file = new File(path);
        List<String> data = new ArrayList<>();
        try {
            scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                data.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("User data file was not found");
        }
        return data;
    }

    public Set<User> readUserData(String path) {
        file = new File(path);
        Set<User> data = new HashSet<>();
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
    public void saveUsers(String path, Set<User> userList) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(path);
            writer.write("Name,Username,Password\n");
            for (User u : userList) {
                writer.write(u.getName() + "," + u.getUserName() + "," + u.getPassword() + "\n");
            }
            writer.close();
        } catch (IOException e) {
        }
    }

    public Set<Media> readMediaData(String path) {
        File file = new File(path); // /src/Data/movies.txt
        try (Scanner scan = new Scanner(file)) {
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] arrLine = line.split(";");
                if (arrLine.length >= 2) {
                    String title = arrLine[0].trim();
                    String releaseYear = (arrLine[1].trim());
                    String stringOfCategories = arrLine[2].trim();
                    String[] arrCategories = stringOfCategories.split(", ", 5);
                    ArrayList<String> categories = new ArrayList<>(Arrays.asList(arrCategories));
                    String ratingStr = arrLine[3].trim().replace(",", ".");
                    float rating = Float.parseFloat(ratingStr);
                    if (arrLine.length > 4) {
                        String[] splitSeasons = arrLine[4].trim().split(", ");
                        int seasons = splitSeasons.length;
                        List<Integer> intOfEpisodes = new ArrayList<>();
                        for(int i = 0 ; i < seasons; i++) {
                            String[] splitEpisode = splitSeasons[i].split("-");
                            intOfEpisodes.add(Integer.parseInt(splitEpisode[1]));
                        }
                        Media m = new Series(title, categories, rating, releaseYear, seasons, intOfEpisodes);
                        setOfMedia.add(m);
                    }else {
                        Media m = new Movie(title, categories, rating, releaseYear);
                        setOfMedia.add(m);
                    }
                }
            }
            return this.setOfMedia;
        } catch (FileNotFoundException e) {
            System.out.println("The file movies.txt was not found: " + e.getMessage());
        }
        return setOfMedia;
    }


}
