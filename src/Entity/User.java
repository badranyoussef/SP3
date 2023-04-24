package Entity;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class User {

    //Three private attributes for the User.
    private String name;
    private String userName;
    private String password;

    private Set<Media> watched;
    private Set<Media> saved;


    //Public constructor to create a user.
    public User(String name, String userName, String password) {
        this.name = name;
        checkUserName(userName);
        checkPassword(password);
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

    //Public method to change username.
    public void changeUserName(String userName) {
        this.userName = userName;
    }

    //Public method change password.
    public void changePassword(String password) {
        this.password = password;
    }

    //Private method to check if username can be used.
    private String checkUserName(String userName) {
        if (userName.length() > 6) {
            return this.userName = userName;
        }
        return checkUserName(userName);
    }

    //Private method to check if password can be used.
    private String checkPassword(String password) {
        if (password.length() > 8) {
            return this.password = password;
        }
        return checkPassword(password);
    }
    public Set<Media> getWatched() {
        return watched;
    }

    public Set<Media> getSaved() {
        return saved;
    }
    public void addMedia(Media m){

    }
}
