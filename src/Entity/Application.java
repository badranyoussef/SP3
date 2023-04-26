package Entity;

import Utility.IO;
import Utility.UI;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;



public class Application {
    private List<User> users;
    private Set<Media> medias = new HashSet<>();
    private IO io = new IO();
    private UI ui = new UI();
    private User onlineUser;
    private List<String> categories = Arrays.asList("1. Crime", "2. Drama", "3. Biography", "4. Sport", "5. History", "6. Romance", "7. War", "8. Mystery", "9. Adventure", "10. Family", "11. Fantasy", "12. Thriller", "13. Horror", "14. Film-Noir", "15. Action", "16. Sci-fi", "17. Comedy", "18. Musical", "19. Western", "20. Music");

    public Application() {
        this.users = io.readUserData("src/Data/userdata.csv"); //ny app skal instantieres med eksisterende brugerdata.
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


        this.onlineUser = new User(name, username, password);
        getUsers().add(onlineUser);
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

    public User findUser(String username) {
        for (User u : this.users) {
            if (u.getUserName().equals(username)) {
                return u;
            }
        }
        return this.onlineUser;
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
                this.onlineUser = findUser(u);
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
        String input = ui.getInput("Welcome to main menu! Which of the following do you want to do?\n" +
                "1) See all movies available\n" +
                "2) Pick a category\n" +
                "3) Search for a movie or serie\n" +
                "4) Logout\n" +
                "5) See personal list\n" +
                "6) see watched media\n");

        switch (input) {

            case "1":
                for (Media m : medias) {
                    System.out.println(m);
                }
                break;
            case "2":
                for (String s : categories) {
                    ui.displayMessage(s);
                }
                //chooseCategory();
                break;
            case "3":
                String searchQuery = ui.getInput("Type media title:");
                Media mediaFound = search(searchQuery);
                break;
            case "4":
                logout();
                break;
            case "5":
                //Call method see personal list
                break;
            case "6":
                printMediaList(this.onlineUser.getWatched());
                break;
            default:
                ui.displayMessage("you did not choose one og the menues try again");
                mainMenu();
        }
    }


    public void printMediaList(ArrayList<Media> m) {
        System.out.println("User: " + this.onlineUser + ": Watched: ");
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
        for (Media m : medias) {
            if (m.getId() == i) {
                ui.displayMessage("The following have been chosen " + m);
            }
        }
    }

    public void mediaOptions(Media m) {
        String input = ui.getInput
                ("To start movie write 1" + " 2 add movie to personal list");
        if (input.equals("1")) {
            playMedia(m);
        }
    }

    public void addMediaToPersonalList(Media m) {
        this.onlineUser.addSavedMedia(m);
        ui.displayMessage("Følgende er nu blevet tilføjet: " + m.getTitle());
    }

    public void saveWatchlist(User user, List<Media> watchlist) {
        try {
            // Create a file with the user's username or ID
            File file = new File(user + ".txt");

            // Create a FileWriter object to write to the file
            FileWriter writer = new FileWriter(file, true);

            // Write each movie in the watchlist to the file
            for (Media m : watchlist) {
                writer.write(m.getTitle() + "," + m.getReleaseYear() + "\n");
            }

            // Close the FileWriter object
            writer.close();

            System.out.println("Watchlist saved for user: " + user);

        } catch (IOException e) {
            System.out.println("Error saving watchlist for user: " + user);
            e.printStackTrace();
        }
    }

    // Load watchlist for a user
   /* public List<Movie> loadWatchlist(User username) {
        List<Movie> watchlist = new ArrayList<>();

        try {
            // Open the file with the user's username or ID
            File file = new File(username + ".txt");
            // Create a Scanner object to read from the file
            Scanner scanner = new Scanner(file);
            // Read each line in the file and create a new Movie object for each line
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                String title = parts[0];
                int year = Integer.parseInt(parts[1]);
               // Movie movie = new Movie(title, year);
                this.onlineUser.getWatched().add(movie);
                //this.onlineUser.addWatchedMedia(movie);
                watchlist.add(movie);
                System.out.println(this.onlineUser.getWatched());
            }

            // Close the Scanner object
            scanner.close();

            System.out.println("Watchlist loaded for user: " + username);

        } catch (IOException e) {
            System.out.println("Error loading watchlist for user: " + username);
            e.printStackTrace();
        }

        return watchlist;
    }
*/
    public void playMedia(Media m) {
        System.out.println("filmen er i gang");
        onlineUser.addWatchedMedia(m);
        mainMenu();
        //loadWatchlist(this.onlineUser);
        //saveWatchlist(this.onlineUser, this.onlineUser.getWatched());
    }

    private List<User> getUsers() {
        return users;
    }
}
