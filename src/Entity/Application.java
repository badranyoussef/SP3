package Entity;

import Utility.IO;
import Utility.UI;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Application {
    private List<User> users;
    private Set<Media> medias = new HashSet<>();
    private IO io = new IO();
    private UI ui = new UI();

    public Application() {
        this.users = io.readUserData("src/Data/userdata.csv"); //ny app skal instantieres med eksisterende brugerdata.
    }

    public void launchApplication() {
        startMenu();
    }
    /*public Set<Media> saveMovies(){
        return medias = io.readMovieData();
    }*/

    public void startMenu() {
        String input = ui.getInput("Do you want to 1. Create user or 2. Login");
        if (input.equals("1")) {
            createUser();
        } else if (input.equals("2")) {
            login();
        } else {
            ui.displayMessage("Try again");
            startMenu();
        }
    }

    public void createUser() {
        String name = ui.getInput("Enter your name:");
        String username = "";
        String password = "";

        while (true) {
            username = ui.getInput("Create a username (must be at least 6 characters long):");
            if (username.length() < 6) {
                System.out.println("That username is too short. Please try again.");
            } else {
                break;
            }
        }

        while (true) {
            password = ui.getInput("Create a password (must be at least 8 characters long):");
            if (password.length() < 8) {
                System.out.println("That password is too short. Please try again.");
            } else {
                break;
            }
        }
        getUsers().add(new User(name, username, password));
        io.saveUsers("src/Data/userdata.csv", this.users);
    }

    public boolean loginValidator(String username, String password) {
        for (User u : this.users) {
            if (u.getUserName().equals(username) && u.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
    public void login() {
        while (true) {
            String u = ui.getInput("Type username:");
            String p = ui.getInput("Type password:");
            if (loginValidator(u, p)) {
                System.out.println("Login successful!");
                return;
            } else {
                System.out.println("Invalid username or password. Please try again.");
                String retry = ui.getInput("Do you want to try again? (Y/N)");
                if (retry.equals("N")) {
                    System.out.println("Login canceled.");
                    return;
                }
            }
        }
    }

    public void mainMenu() {
        List<Media> m = io.readMovieData();

        String input = ui.getInput("Welcome to main menu! Which of the following do you want to do?" +
                " 1) See all movies available" +
                " 2) Pick a category" +
                " 3) Search for a movie");
        if (input.equals("1")) {
            for (int i = 0; i < m.size(); i++) {
                System.out.println(m.get(i));
            }
        } else if (input.equals(2)) {
        } else {
        }
    }

    /*public Media search(String input){
        //todo: add return statement
    }
    public Set<Media> filter(int i){
        //todo: add return statement
    }*/
    public void chooseMedia() {
        int i = Integer.parseInt(ui.getInput("Which would you like to choose? Use numbers please shown left for the movie"));
        List<Media> m = io.readMovieData();
        System.out.println("The following have been chosen " + m.get(i - 1));
    }

    public void playMedia() {

    }

    public List<User> getUsers() {
        return users;
    }

}
