package Entity;

import Utility.IO;
import Utility.UI;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

//TESTETESTSTESTS

public class Application {
    private Set<User> users;
    private Set<Media> medias;
    private IO io = new IO();
    private UI ui = new UI();
    private User onlineUser; //To recognize which user is online and load/add from/to their lists
    private List<String> categories = io.readData("src/Data/categories.txt");
    private String appName;
    public Application(String appName) {
        this.appName = appName;
        this.users = io.readUserData("src/Data/userdata.csv");
        this.medias = io.readMovieData("src/Data/movies.txt");
    }
    //Method to launch application
    public void launchApplication() {
        startMenu();
    }

    //Start menu
    public void startMenu() {
        ui.displayMessage("Welcome to "+ appName +"'s streaming service!\n\nChoose one of the following options:\n");
        String input = ui.getInput("1) to login.\n2) to sign up.\n3) to close the streaming service.\n\nType in your choice below:");
        switch (input){
            case "1":
                login();
                break;
            case "2":
                createUser();
                break;
            case "3":
                ui.displayMessage("Thanks for using our service, have a nice day!");
                break;
            default :
                ui.displayMessage("You did not type one of the above options. Please try again");
                startMenu();
        }
    }

    //Method that creates new User to Application
    public void createUser() {
        String name = ui.getInput("Enter your name: (Back to Start Menu type: X)");
        if (name.equalsIgnoreCase("x")) {
            startMenu();
        }
        String username = "";
        String password = "";

        while (true) {
            username = ui.getInput("Create a username - it must be at least 6 characters long. (Back to Start Menu type: X):");
            if (username.equalsIgnoreCase("x")) {
                startMenu();
            }
            if (username.length() < 6) {
                System.out.println("That username is too short. Please try again.");
            } else {
                break;
            }
        }
        while (true) {
            password = ui.getInput("Create a password - it must be at least 8 characters. (Back to Start Menu type: X)");
            if (password.equalsIgnoreCase("x")) {
                startMenu();
            }
            if (password.length() < 8) {
                System.out.println("That password is too short. Please try again.");
            } else {
                break;
            }
        }
        this.onlineUser = new User(name, username, password);
        getUsers().add(onlineUser);
        io.saveUsers("src/Data/userdata.csv", this.users);
        ui.displayMessage("Welcome onboard "+name+". Thanks for choosing our service\n");
        mainMenu();
    }

    //Validator to check if login is possible
    public boolean loginValidator(String username, String password) {
        for (User u : this.users) {
            if (u.getUserName().equals(username) && u.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public User findUser(String username) {
        for (User u : this.users) {
            if (u.getUserName().equals(username)) {
                return u;
            }
        }
        return this.onlineUser;
    }

    //Method to login
    public void login() {
        while (true) {
            String u = ui.getInput("Type username: (Back to Start Menu type: X)");
            if (u.equalsIgnoreCase("x")) {
                startMenu();
            }
            String p = ui.getInput("Type password: (Back to Start Menu type: X)");
            if (p.equalsIgnoreCase("x")) {
                startMenu();
            }
            if (loginValidator(u, p)) {
                System.out.println("\nWelcome back "+u+". You are now logged in!");
                this.onlineUser = findUser(u);
                mainMenu();
            } else {
                String retry = ui.getInput("\nInvalid username or password. Do you want to try again? (Y/N)");
                if (retry.equalsIgnoreCase("n")) {
                    System.out.println("Login canceled.\n");
                    startMenu();
                } else if(retry.equalsIgnoreCase("y")){
                    login();
                } else {
                    ui.displayMessage("I don't understand: " + retry);
                    retry = ui.getInput("Try again");
                }
            }
        }
    }

    //Main menu after logging in (under construction)
    public void mainMenu() {
        String input = ui.getInput("\n(Main menu)\nWhich of the following do you want to do?\n" +
                "1) See all media available\n" +
                "2) Pick a category\n" +
                "3) Search for a movie or series\n" +
                "4) See personal list\n" +
                "5) see watched media\n" +
                "6) Logout\n");
        switch (input) {
            case "1":
                for (Media m : medias) {
                    System.out.println(m);
                }
                chooseMedia();
                break;
            case "2":
                int addCategoryID = 1;
                for (String s : categories) {
                    ui.displayMessage(addCategoryID + ") " + s);
                    addCategoryID++;
                }
                chooseCategory();
                break;
            case "3":
                String searchQuery = ui.getInput("Type media title:");
                Media mediaFound = search(searchQuery);
                mediaOptions(mediaFound);
                break;
            case "4":
                printMediaList(this.onlineUser.getSavedMedia());
                chooseMedia();
                break;
            case "5":
                printMediaList(this.onlineUser.getWatched());
                mainMenu();
                break;
            case "6":
                logout();
                break;
            default:
                ui.displayMessage("you did not choose one og the menus try again");
                mainMenu();
        }
    }
    public void printMediaList(ArrayList<Media> m) {
        System.out.println(this.onlineUser.getName() + "'s  is shown below: ");
        for (Media mm : m) {
            System.out.println(mm);
        }
    }
    public Media search(String input) {
        Media notFound = null;
        for (Media m : medias) {
            if (m.getTitle().equalsIgnoreCase(input)) {
                ui.displayMessage("Found movie: " + m.getTitle());
                return m;
            }
        }
        ui.displayMessage("The movie you are looking for does not exist");
        return notFound;
    }
    public void logout() {
        ui.displayMessage("Thank you for using our service! See you soon!");
        startMenu();
    }
    //Method to choose a media
    public void chooseMedia() {
        String input = ui.getInput("Which would you like to choose?" + "\n" + "Use numbers please shown left for the movie! (Back to Main Menu type: X");
        for (Media m : medias) {
            if (m.getId() == Integer.parseInt(input)) {
                ui.displayMessage("The following have been chosen: " + m);
                mediaOptions(m);
            } else if(Integer.parseInt(input) < 1 || Integer.parseInt(input)> medias.size()){
                input = ui.getInput("Please type a number between 1-" + medias.size() + "or X to go back to Main Menu."); //todo: only works once
            } else if(input.equalsIgnoreCase("x")){
                mainMenu();
            }
        }
    }
    public void chooseCategory(){
        String input = ui.getInput("Type category ID:");
        String category = categories.get(Integer.parseInt(input)-1);
        for(Media m : medias){
            for(String s : m.getCategory()){
                if(s.equals(category)){
                    ui.displayMessage(m.toString());
                }
            }
        }
        if(Integer.parseInt(input) < 1 || Integer.parseInt(input)> categories.size()){
            input = ui.getInput("Please type a number between 1-" + categories.size() + "or X to go back to Main Menu."); //todo: only works once
        } else if(input.equalsIgnoreCase("x")){
            mainMenu();
        }
        chooseMedia();
    }
    //Method to have options with the media
    public void mediaOptions(Media m) {
        String input = ui.getInput
                ("1) Start movie\n" +
                        "2) Add movie to personal list\n" +
                        "3) Go back to Main Menu");
        if (input.equals("1")) {
            playMedia(m);
        } else if (input.equals("2")) {
            addMediaToPersonalList(m);
        } else if(input.equals("3")){
            mainMenu();
        } else {
            input = ui.getInput("I don't understand: " + input + ". Try again."); //todo: only works once
        }
    }

    //Method ot add media to personal list
    public void addMediaToPersonalList(Media m) {
        this.onlineUser.addSavedMedia(m);
        ui.displayMessage("The following have been added: " + "\n"+ m.getTitle());
        mediaOptions(m);
    }
    //Method to play the media
    public void playMedia(Media m) {
        System.out.println("The following media:"+m.getTitle()+" is playing!");
        onlineUser.addWatchedMedia(m);
        mediaOptions(m);
    }
    private Set<User> getUsers() {
        return users;
    }
}
