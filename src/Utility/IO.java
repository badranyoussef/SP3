
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

    //A method to read user data
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

    //A method to read media data
    public Set<Media> readMediaData(String path) {
        File file = new File(path);
        try (Scanner scan = new Scanner(file)) {
            while (scan.hasNextLine()) {
                String line = scan.nextLine(); // Scan next line
                String[] arrLine = line.split(";"); //Split line by semicolon
                if (arrLine.length >= 2) { //Online do this id there were something to split
                    String title = arrLine[0].trim(); //Save Media title from first index
                    String releaseYear = (arrLine[1].trim()); //Save Media releaseYear from second index
                    String stringOfCategories = arrLine[2].trim(); //Save Media String of categories from third index
                    String[] arrCategories = stringOfCategories.split(", "); //Split categories at comma+space
                    ArrayList<String> categories = new ArrayList<>(Arrays.asList(arrCategories)); //Add all categories from String[] to ArrayList
                    String ratingStr = arrLine[3].trim().replace(",", "."); //Save Media rating from fourth index
                    float rating = Float.parseFloat(ratingStr);
                    if (arrLine.length > 4) { //If arrLine longer than 4 = Series, then do:
                        String[] splitSeasons = arrLine[4].trim().split(", "); //Split seasons from fifth index by comma+space
                        int seasons = splitSeasons.length; //Save number of seasons
                        List<Integer> numOfEpisodes = new ArrayList<>();
                        //For every season separate the number of episodes
                        for(int i = 0 ; i < seasons; i++) {
                            String[] splitEpisode = splitSeasons[i].split("-"); //Split number of episodes from season number
                            numOfEpisodes.add(Integer.parseInt(splitEpisode[1])); //add number of episodes to ArrayList
                        }
                        //Create new Series()
                        Media m = new Series(title, categories, rating, releaseYear, seasons, numOfEpisodes);
                        setOfMedia.add(m);
                    }else {
                        //Or create new Movie()
                        Media m = new Movie(title, categories, rating, releaseYear);
                        setOfMedia.add(m);
                    }
                }
            }
            return this.setOfMedia;
        } catch (FileNotFoundException e) {
            System.out.println("The file movies.csv was not found: " + e.getMessage());
        }
        return setOfMedia;
    }
}
