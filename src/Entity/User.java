package Entity;

import java.util.ArrayList;
import java.util.List;

public class User {

    //Three private attributes for the User.
    private String name;
    private String userName;
    private String password;
    private List<User> userList = new ArrayList<>();


    //Public constructor to create a user.
    public User(String name, String userName, String password) {
        this.name = name;
        checkUserName(userName);
        checkPassword(password);
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
}
