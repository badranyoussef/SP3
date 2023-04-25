package Entity;


import java.util.Set;

public class User {

    //Private attributes for the User.
    private String name;
    private String userName;
    private String password;
    private Set<Media> watched;
    private Set<Media> saved;


    //Public constructor to create a user.
    public User(String name, String userName, String password) {
        this.name = name;
        nameValidator(userName);
        passwordValidator(password);
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

    //Private method to check if username can be used.
    private void nameValidator(String userName) {
        if (userName.length() >= 6) {
            this.userName = userName;
        }
        System.out.println("That name can't be used! Try again!");
        nameValidator(userName);
    }

    //Private method to check if password can be used.
    private void passwordValidator(String password) {
        if (password.length() >= 8) {
            this.password = password;
        }
        System.out.println("That password can't be used! Try again!");
        passwordValidator(password);
    }

    //Public method to check which medias have been watched
    public Set<Media> getWatched() {
        return watched;
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
