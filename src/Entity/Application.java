package Entity;

import Utility.IO;
import Utility.UI;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

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
        this.medias = io.readMediaData("src/Data/movies.txt");
        Set<Media> series = io.readMediaData("src/Data/series.txt");
        this.medias.addAll(series);
    }

    //Method to launch application
    public void launchApplication() {
        startMenu();
    }

    public void startMenu() {
        boolean isValidOption = true;
        ui.displayMessage("Welcome to " + appName + "'s streaming service!\n\nChoose one of the following options:\n");
        ui.displayMessage("1) to login.\n2) to sign up.\n3) to close the streaming service.\n\nType in your choice below:");

        while (isValidOption) {
            String input = ui.getInput("");
            switch (input) {
                case "1":
                    login();
                    isValidOption = false;
                    break;
                case "2":
                    createUser();
                    isValidOption = false;
                    break;
                case "3":
                    ui.displayMessage("Thanks for using our service, have a nice day!");
                    isValidOption = false;
                    System.exit(0);
                    break;
                default:
                    ui.displayMessage("Invalid input. Please try again.\n");
            }
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
        ui.displayMessage("Welcome onboard " + name + ". Thanks for choosing our service\n");
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

    public void login() {
        String u = "";
        String p = "";
        boolean validInput = false;

        while (!validInput) {
            u = ui.getInput("Type username: (Back to Start Menu type: X)");
            if (u.equalsIgnoreCase("x")) {
                startMenu();
                validInput = true;
            }
            p = ui.getInput("Type password: (Back to Start Menu type: X)");
            if (p.equalsIgnoreCase("x")) {
                startMenu();
                validInput = true;
            }

            // Check if both u and p have non-zero length
            if (!u.isEmpty() && !p.isEmpty()) {
                validInput = true;
            } else {
                ui.displayMessage("Please enter a valid username and password.\n");
            }
        }


        if (loginValidator(u, p)) {
            System.out.println("\nWelcome back " + u + ". You are now logged in!");
            this.onlineUser = findUser(u);
            mainMenu();
        } else {
            String retry = ui.getInput("\nInvalid username or password. Do you want to try again? (Y/N)");
            if (retry.equalsIgnoreCase("n")) {
                System.out.println("Login canceled.\n");
                startMenu();
            } else if (retry.equalsIgnoreCase("y")) {
                // Continue the outer while loop to prompt for username and password again
            } else {
                ui.displayMessage("I don't understand: " + retry);
            }
        }
    }


    //Main menu after logging in (under construction)
    public void mainMenu() {
        ui.displayMessage("\n(Main menu)\nWhich of the following do you want to do?\n" +
                "1) See all media available\n" +
                "2) Pick a category\n" +
                "3) Search for a movie or series\n" +
                "4) See personal list\n" +
                "5) see watched media\n" +
                "6) Logout\n");
        boolean validInput = false;
        while (!validInput) {
            String input = ui.getInput("");
            switch (input) {
                case "1":
                    for (Media m : medias) {
                        if (m instanceof Series) {
                            System.out.println((Series)m);
                        }
                         else{System.out.println(m);}
                    }
                    chooseMedia();
                    validInput = true;
                    break;

                case "2":
                    int addCategoryID = 1;
                    for (String s : categories) {
                        ui.displayMessage(addCategoryID + ") " + s);
                        addCategoryID++;
                    }
                    chooseCategory();
                    validInput = true;
                    break;
                case "3":
                    String searchQuery = ui.getInput("Type media title:");
                    Media mediaFound = search(searchQuery);
                    mediaOptions(mediaFound);
                    validInput = true;
                    break;
                case "4":
                    printMediaList(this.onlineUser.getSavedMedia());
                    chooseMedia();
                    validInput = true;
                    break;
                case "5":
                    printMediaList(this.onlineUser.getWatched());
                    mainMenu();
                    validInput = true;
                    break;
                case "6":
                    logout();
                    validInput = true;
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
                    break;
            }
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
        String input;
        boolean validInput = false;
        while (!validInput) {
            input = ui.getInput("Which would you like to choose?" + "\n" + "Use numbers please shown left for the movie! (Back to Main Menu type: X");
            if (input.equalsIgnoreCase("x")) {
                mainMenu();
                return;
            }
            try {
                int selectedId = Integer.parseInt(input);
                for (Media m : medias) {
                    if (m.getId() == selectedId) {
                        ui.displayMessage("The following have been chosen: " + m);
                        mediaOptions(m);
                        validInput = true;
                        break;
                    }
                }
                if (!validInput) {
                    ui.displayMessage("Invalid selection. Please try again.");
                }
            } catch (NumberFormatException e) {
                ui.displayMessage("Invalid input. Please enter a number or 'X'.");
            }
        }
    }

    public void chooseCategory() {
        boolean isOptionValid = true;
        while (isOptionValid) {
            try {
                String input = ui.getInput("Type category ID: 1-"+categories.size());

                if (input.equalsIgnoreCase("x")) {
                    mainMenu();
                    isOptionValid = false;
                }
                int categoryID = Integer.parseInt(input);
                if (categoryID >= 1 && categoryID <= categories.size()) {
                    String category = categories.get(categoryID - 1);
                    for (Media m : medias) {
                        for (String s : m.getCategory()) {
                            if (s.equals(category)) {
                                ui.displayMessage(m.toString());
                            }
                        }
                    }
                    chooseMedia();
                    isOptionValid = false;
                    break;
                }
            } catch (Exception e) {
                ui.displayMessage("Invalid input. Please type a number between 1 and " + categories.size() + ", or X to go back to Main Menu.");
                chooseCategory();
            }
        }
    }

    public void mediaOptions(Media m) {
        ui.displayMessage(
                "1) Start movie/serie\n" +
                        "2) Add movie to personal list\n" +
                        "3) Go back to Main Menu\n"+
                        "4) Show seasons");
        boolean stopAsking = true;
        while (stopAsking) {
            String input = ui.getInput("");
            if (input.equals("1")) {
                playMedia(m);
                ui.displayMessage("the movie has finished");
                mainMenu();
                stopAsking = false;
                break;
            } else if (input.equals("2")) {
                addMediaToPersonalList(m);
                stopAsking = false;
                break;
            } else if (input.equals("3")) {
                mainMenu();
                stopAsking = false;
                break;
            } else if (input.equals("4")) {

                if(m instanceof Series){
                    System.out.println(((Series) m).getSeasons());
                    boolean stop = true;
                    while (stop) {
                        input = ui.getInput("\nWhich season would you like to watch? To go to main menu type X\n");

                        Set<Season> seasonSet = ((Series) m).getSeasons();
                        for(Season s: seasonSet) {
                            int i = Integer.parseInt(input);


                            if (i == (s.getSeasonNumber())){
                                System.out.println("You have now chosen the following season: "+s.getSeasonNumber());
                                System.out.println("The following season contains the following episodes: "+((Series) m).getEpisodes(s));

                                List <Episode> chooseEpisode = ((Series) m).getEpisodes(s);
                                input = ui.getInput("Which one?");


                                int ii = Integer.parseInt(input);

                                System.out.println("The following episode have been chosen: "+chooseEpisode.get(ii-1));
                                playMedia(m);
                                stop = false;
                            }
                        }
                    }
                }
                mainMenu();
                stopAsking = false;

                break;
            }else {
                ui.displayMessage("I don't understand: " + input + ". Try again.");
            }
        }
    }


    //Method ot add media to personal list
    public void addMediaToPersonalList(Media m) {
        this.onlineUser.addSavedMedia(m);
        ui.displayMessage("The following have been added: " + "\n" + m.getTitle());
        mediaOptions(m);
    }

    //Method to play the media
    public void playMedia(Media m) {

        System.out.println("The following media:" + m.getTitle() + " is playing!");
        onlineUser.addWatchedMedia(m);

        System.out.println("The following media is done: "+m.getTitle());
        mainMenu();
    }

    private Set<User> getUsers() {
        return users;
    }
}
