package Utility;

import Entity.Media;
import Entity.User;

import java.util.List;
import java.util.Set;

public interface IO {
    public List<String> readData(String path);
    public Set<User> readUserData(String path);
    public void saveUsers(String path, Set<User> userList);
    public Set<Media> readMediaData();

}
