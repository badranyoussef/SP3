package Utility;

import Entity.Media;
import Entity.User;

import java.util.List;
import java.util.Set;

public interface IO {
    public List<String> readCategoryList();
    public Set<User> readUserData();
    public void saveUsers(String path, Set<User> userList);
    public Set<Media> readMediaData();

}
