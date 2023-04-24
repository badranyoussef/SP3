package Entity;

import Utility.IO;
import Utility.UI;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Application {
    private List<User> users = new ArrayList<>();
    private Set<Media> medias;
    private IO io = new IO();
    private UI ui = new UI();
    public Application(){
        this.users = io.readUserData("data/userdata.csv"); //ny app skal instantieres med eksisterende brugerdata.
    }
    public void launchApplication(){
        startMenu();
    }
    public void startMenu(){
        String input = ui.getInput("Do you want to 1. Create user or 2. Login");
        if(input.equals("1")){
            createUser();
        } else if(input.equals(2)){
            String u = ui.getInput("Type username:");
            String p = ui.getInput("Type password:");
            login(u,p);
        } else{
            ui.displayMessage("Try again");
            startMenu();
        }
    }
    public void createUser(){
        String name = ui.getInput("What is your name?");
        String username = ui.getInput("Create username");
        String password = ui.getInput("Create password");
        getUsers().add(new User(name,username,password));
        io.saveData("data/userdata.csv", this.users);
    }
    public boolean login(String username, String password){
        for(User u : this.users){
            if(u.getUserName().equals(username) && u.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
    public void mainMenu(){

    }
    /*public Media search(String input){
        //todo: add return statement
    }
    public Set<Media> filter(int i){
        //todo: add return statement
    }*/
    public void chooseMedia(){

    }
    public void playMedia(){

    }

    public List<User> getUsers() {
        return users;
    }
}
