package Entity;

import Utility.DBConnector;
import Utility.FileIO;
import Utility.UI;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
public class Application {
    private Set<User> users; //We use set to be able get users in constant time
    private Set<Media> medias; //We use set to be able get medias in constant time
    private FileIO io = new FileIO();
    private UI ui = new UI();

    private DBConnector dbConnector = new DBConnector();

    private User onlineUser; //To recognize which user is online and load/add from/to their lists
    //private List<String> categories = io.readCategoryList("src/Data/categories.txt"); //We read from file/DB so we can add new categories in future
    private List<String> categories = dbConnector.readCategoryList();
    private String appName;
    //A new application for now just need a name in the constructor
    //Maybe it should take a set of data, as data can be different from app to app
    public Application(String appName) {
        this.appName = appName;

        //this.users = io.readUserData("src/Data/userdata.csv");
        //this.medias = io.readMediaData("src/Data/movies.csv"); //First read movie data and add to our Media set
        //Set<Media> series = io.readMediaData("src/Data/series.csv"); //Then read series data and add to temp set
        //this.medias.addAll(series); //Then add all series to our Media set


        this.users = dbConnector.readUserData();
        this.medias = dbConnector.readMediaData();

    }

    //Method to launch application
    public void launchApplication() {
        //When launching only the startMenu is available
        startMenu();
    }
    //In startMenu you can choose to login/logout/close app
    private void startMenu() {
        boolean isValidOption = true;
        ui.displayMessage("Welcome to " + appName + "'s streaming service!\n\nChoose one of the following options:\n");
        ui.displayMessage("1) to login.\n2) to sign up.\n3) to close the streaming service.\n\nType in your choice below:");
        //Checks if input option is a valid option and asks again if not
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
    private void createUser() {
        String name = ui.getInput("Enter your name: (Back to Start Menu type: X)");
        if (name.equalsIgnoreCase("x")) {
            startMenu();
        }
        //Variables to store user values in the global scope, so they can be added later to a new user instance
        String username = "";
        String password = "";
        //Loop that keeps asking for username if input is not valid
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
        //Loop that keeps asking for password if input is not valid
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
        //Creates new user with valid input and initializes onlineUser
        this.onlineUser = new User(name, username, password);
        getUsers().add(onlineUser); //Adds new user to User Set
        io.saveUsers("src/Data/userdata.csv", this.users); //Adds new user to file/DB
        ui.displayMessage("Welcome onboard " + name + ". Thanks for choosing our service\n");
        mainMenu();
    }

    //Validator to check if login is possible
    private boolean loginValidator(String username, String password) {
        //This method runs through our User Set to validate that the user exists
        for (User u : this.users) {
            if (u.getUserName().equals(username) && u.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    //Method to find user
    private User findUser(String username) {
        for (User u : this.users) {
            if (u.getUserName().equals(username)) {
                return u;
            }
        }
        return this.onlineUser;
    }

    private void login() {
        //Declare global scope variables to hold username/password input, so they can be validated
        String username = "";
        String password = "";
        boolean validInput = false;
        //Loop that keeps asking user for input, if input is not valid
        while (!validInput) {
            username = ui.getInput("Type username: (Back to Start Menu type: X)");
            if (username.equalsIgnoreCase("x")) {
                startMenu();
                validInput = true;
            }
            password = ui.getInput("Type password: (Back to Start Menu type: X)");
            if (password.equalsIgnoreCase("x")) {
                startMenu();
                validInput = true;
            }

            // Check if both u and p have non-zero length
            if (!username.isEmpty() && !password.isEmpty()) {
                validInput = true;
            } else {
                ui.displayMessage("Please enter a valid username and password.\n");
            }
        }
        //Validation login, if valid this.onlineUser is initialized with the user that logged in
        if (loginValidator(username, password)) {
            System.out.println("\nWelcome back " + username + ". You are now logged in!");
            this.onlineUser = findUser(username);
            mainMenu();
        } else { //If login is not valid ask if user wants to try again. Either go back to startMenu() or login()
            String retry = ui.getInput("\nInvalid username or password. Do you want to try again? (Y/N)");
            if (retry.equalsIgnoreCase("n")) {
                System.out.println("Login canceled.\n");
                startMenu();
            } else if (retry.equalsIgnoreCase("y")) {
                login();
            } else {
                ui.displayMessage("I don't understand: " + retry);
            }
        }
    }


    //Main menu after logging in
    private void mainMenu() {
        //Display list of options in the mainMenu()
        ui.displayMessage("\n(Main menu)\nWhich of the following do you want to do?\n" +
                "1) See all media available\n" +
                "2) Pick a category\n" +
                "3) Search for a movie or series\n" +
                "4) See personal list\n" +
                "5) see watched media\n" +
                "6) Logout\n");
        boolean validInput = false;
        //Loop keeps asking onlineUser for valid input
        while (!validInput) {
            String input = ui.getInput("");
            switch (input) {
                case "1": //See all medias available
                    for (Media m : medias) {
                        ui.displayMessage(m.toString());
                        /*if (m instanceof Series) {
                            System.out.println((Series)m);
                        }
                         else{System.out.println(m);}*/
                    }
                    chooseMedia(); //When list of medias is shown, this enables you to choose one
                    validInput = true; //Sets valid input to true and exits while loop
                    break;

                case "2": //Display list of available categories
                    int addCategoryID = 1;
                    //Runs through our List of categories and gives them an ID to choose from
                    for (String s : categories) {
                        ui.displayMessage(addCategoryID + ") " + s);
                        addCategoryID++;
                    }
                    chooseCategory(); //When categories are shown, this enables user to choose one
                    validInput = true;
                    break;
                case "3": //Enables search for media title
                    String searchQuery = ui.getInput("Type media title:");
                    Media mediaFound = search(searchQuery); //Return the media if found or null if not
                    mediaOptions(mediaFound); //Display options for the media found
                    validInput = true;
                    break;
                case "4": //See personal list for onlineUser
                    printMediaList(this.onlineUser.getSavedMedia());
                    chooseMedia();
                    validInput = true;
                    break;
                case "5": //See list of watched medias for onlineUser
                    printMediaList(this.onlineUser.getWatched());
                    //then displayes mainMenu, maybe a fix?
                    mainMenu();
                    validInput = true;
                    break;
                case "6": //Logout and go to startMenu()
                    logout();
                    validInput = true;
                    break;
                default:
                    ui.displayMessage("Invalid input. Please try again.");
                    break;
            }
        }
    }

    //Method to print out the saved and watched personal media list for onlineUser
    private void printMediaList(ArrayList<Media> mediaList) {
        System.out.println(this.onlineUser.getName() + "'s  is shown below: ");
        for (Media m : mediaList) {
            ui.displayMessage(m.toString());
        }
    }

    //Method to search for a media
    private Media search(String input) {
        Media notFound = null;
        //Search Media Set for searchQuery and return Media if found
        for (Media m : medias) {
            if (m.getTitle().equalsIgnoreCase(input)) {
                ui.displayMessage("Found movie: " + m.getTitle());
                return m;
            }
        }
        //If Media not found return null
        ui.displayMessage("The movie you are looking for does not exist");
        return notFound;
    }

    //Method to logout from mainMenu()
    private void logout() {
        ui.displayMessage("Thank you for using our service! See you soon!");
        startMenu();
    }

    //Method to choose a media
    private void chooseMedia() {
        String input;
        boolean validInput = false;
        //Loop that keeps asking user for a valid input if input is not valid
        while (!validInput) {
            input = ui.getInput("Which would you like to choose?" + "\n" + "Use numbers please shown left for the movie! (Back to Main Menu type: X");
            if (input.equalsIgnoreCase("x")) {
                mainMenu();
                return;
            }
            //Try to parse input to number and choose Media
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
            //If input can't be parsed, tell user input is not valid and try again
            } catch (NumberFormatException e) {
                ui.displayMessage("Invalid input. Please enter a number or 'X'.");
            }
        }
    }

    //Method to choose category
    private void chooseCategory() {
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

    //Method to get media options
    private void mediaOptions(Media m) {
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
            } else if (input.equals("4")) { //Only works if chosen Media is a Series
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
    private void addMediaToPersonalList(Media m) {
        //Adds the chosen media to saved list for onlineUser
        this.onlineUser.addSavedMedia(m);
        ui.displayMessage("The following have been added: " + "\n" + m.getTitle());
        mediaOptions(m); //Display Media options again if you want to playMedia
    }

    //Method to play the media
    private void playMedia(Media m) {
        System.out.println("The following media:" + m.getTitle() + " is playing!");
        onlineUser.addWatchedMedia(m);
        System.out.println("The following media is done playing: "+m.getTitle());
        mainMenu();
    }

    //Method to get all the users
    private Set<User> getUsers() {
        return users;
    }
}
