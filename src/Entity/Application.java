package Entity;

import Utility.IO;
import Utility.UI;

import java.util.*;

public class Application {
    private List<User> users;
    private Set<Media> medias = new HashSet<>();
    private IO io = new IO();
    private UI ui;

    public void launchApplication(){

    Movie movie;

    public void launchApplication() {
        String title = "";
        int releaseYear = 0;
        String category = "";
        float rating = 0;

        List<String> movies = io.readUserData("/Users/youssefbadran/Documents/GitHub/SP3/data/movies.txt");

        for (String m : movies){
            String line = scan.nextLine();
            String [] arrLine = line.split(";",5);
            if (arrLine.length >= 4) { // Check the length of arrLine before accessing its elements
                title = arrLine[0];
                releaseYear = Integer.parseInt(arrLine[1]);
                category = arrLine[2];
                rating = Float.parseFloat(arrLine[3]);
                System.out.println(title+category+rating);
                //movie = new Movie(title, category, rating, releaseYear);
                //medias.add(movie);
            } else {
                System.out.println("Invalid input: " + line); // Print an error message if arrLine is too short
            }
        }

        /*String title = "";
        int releaseYear = 0;
        String category = "";
        float rating = 0;

        List<String> movies = io.readUserData("/Users/youssefbadran/Documents/GitHub/SP3/data/movies.txt");
        Scanner scan = new Scanner("/Users/youssefbadran/Documents/GitHub/SP3/data/movies.txt");
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] arrLine = line.split(";", 5);
            System.out.println(arrLine.length);

            title = arrLine[0];
            releaseYear = Integer.parseInt(arrLine[1]);
            category = arrLine[2];
            rating = Float.parseFloat(arrLine[3]);
            System.out.println(title + category + rating);
            //movie = new Movie(title, category, rating, releaseYear);
            //medias.add(movie);*/

        //System.out.println(medias);
            

    }
/*
    public void startMenu() {

    }

    public void createUser() {

    }

    public void login() {

    }

    public void mainMenu() {

    }

    public Media search(String input) {

    }

    public Set<Media> filter(int i) {

    }

    public void chooseMedia() {

    }

    public void playMedia() {

    }
*/

}