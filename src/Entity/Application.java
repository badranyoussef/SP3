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
        this.medias = io.readMovieData("src/Data/movies.txt");
    }

    public void launchApplication() {
        startMenu();
    }
    /*public Set<Media> saveMovies(){
        return medias = io.readMovieData();
    }*/

    //Start menu
    public void startMenu() {
        String input = ui.getInput("Do you want to 1. Create user or 2. Login 3. Close program");
        if (input.equals("1")) {
            createUser();
        } else if (input.equals("2")) {
            login();
        } else if (input.equals("3")) {
            System.out.println("Goodbye!");
        } else {
            ui.displayMessage("Try again");
            startMenu();
        }
    }

    //Create user method
    public void createUser() {
        String name = ui.getInput("Enter your name: (Go back by typing: back");
        if (name.equals("back")) {
            startMenu();
            return;
        }
        String username = "";
        String password = "";

        while (true) {
            username = ui.getInput("Create a username (must be at least 6 characters long) Go back by typing back:");
            if (username.equals("back")) {
                startMenu();
                return;
            }
            if (username.length() < 6) {
                System.out.println("That username is too short. Please try again. (Go back by typing: back");
            } else {
                break;
            }
        }

        while (true) {
            password = ui.getInput("Create a password (must be at least 8 characters long GO back by typing: back):");
            if (password.equals("back")) {
                startMenu();
                return;
            }
            if (password.length() < 8) {
                System.out.println("That password is too short. Please try again.(Go back by typing: back");
            } else {
                break;
            }
        }
        getUsers().add(new User(name, username, password));
        io.saveUsers("src/Data/userdata.csv", this.users);
    }

    //Validator to login if possible
    public boolean loginValidator(String username, String password) {
        for (User u : this.users) {
            if (u.getUserName().equals(username) && u.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }


    //Method to use when loging in
    public void login() {
        while (true) {
            String u = ui.getInput("Type username:back");
            if (u.equals("back")) {
                startMenu();
                return;
            }
            String p = ui.getInput("Type password:back");
            if (p.equals("back")) {
                startMenu();
                return;
            }
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

    //Main menu after logging in (under construction)
    public void mainMenu() {
        String input = ui.getInput("Welcome to main menu! Which of the following do you want to do?" +
                " 1) See all movies available" +
                " 2) Pick a category" +
                " 3) Search for a movie" +
                " 4) Logout");
        if (input.equals("4")) {
            logout();
            return;
        }
        if (input.equals("1")) {
            for (Media m : medias) {
                System.out.println(m);
            }
        } else if (input.equals(2)) {
        } else {
        }
    }

    public void logout() {
        //TOD IKKE BRUG SOUT
        System.out.println("Thank you for using our service! See you soon!");
    }

    /*public Media search(String input){
        //todo: add return statement
    }
    public Set<Media> filter(int i){
        //todo: add return statement
    }*/
    public void chooseMedia() {
        int i = Integer.parseInt(ui.getInput("Which would you like to choose? Use numbers please shown left for the movie"));
        System.out.println("The following have been chosen " + medias.equals(i));
    }

    public void playMedia() {

    }

    public List<User> getUsers() {

        return users;
    }

}
