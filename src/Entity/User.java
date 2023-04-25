package Entity;


import Utility.UI;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class User {

    //Private attributes for the User.
    private String name;
    private String userName;
    private String password;
    private ArrayList<Media> watched;
    private Set<Media> saved;
    UI ui = new UI();


    //Public constructor to create a user.
    public User(String name, String userName, String password) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.watched = new ArrayList<>();
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

    //Public method to change name.
    public void changeName(String name) {
        this.name = name;
    }

    //Public method change password.
    public void changePassword(String password) {
        this.password = password;
    }

    //Public method to check which medias have been watched
    public ArrayList<Media> getWatched() {
        return this.watched;
    }

    //Public method to check which medias have been saved
    public Set<Media> getSaved() {
        return saved;
    }

    //Public method to add a watched media
    public void addWatchedMedia(Media m) {
        this.watched.add(m);
    }

    //Public method to add saved media
    public void addSavedMedia(Media m) {
        this.saved.add(m);
    }

}
