package Entity;
import java.util.ArrayList;

public class User {

    //Private attributes for the User.
    private String name;
    private String userName;
    private String password;
    private ArrayList<Media> watchedMedia;
    private ArrayList<Media> savedMedia;


    //Public constructor to create a user.
    public User(String name, String userName, String password) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.watchedMedia = new ArrayList<>();
        this.savedMedia = new ArrayList<>();
    }

    //Public method to get the name.
    public String getName() {
        return name;
    }

    //Public method to get username.
    public String getUserName() {
        return userName;
    }

    //Public method to get password.
    public String getPassword() {
        return password;
    }


    //Public method to check which medias have been watched
    public ArrayList<Media> getWatched() {
        return this.watchedMedia;
    }

    //Public method to check which medias have been saved
    public ArrayList<Media> getSavedMedia() {
        return savedMedia;
    }

    //Public method to add a watched media
    public void addWatchedMedia(Media m) {
        this.watchedMedia.add(m);
    }

    //Public method to add saved media
    public void addSavedMedia(Media m) {
        this.savedMedia.add(m);
    }
}
