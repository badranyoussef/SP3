package Entity;

import Utility.IO;
import Utility.UI;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Application {
    private List<User> users;
    private Set<Media> medias;
    private IO io;
    private UI ui;

    public void launchApplication(){


    private IO io = new IO();
    private UI ui = new UI();
    public void launchApplication(){
        startMenu();
    }
    public void startMenu(){
        String input = ui.getInput("Do you want to 1. Create user or 2. Login");
        if(input.equals("1")){
            createUser();
        } else if(input.equals(2)){
            login();
        } else{
            ui.displayMessage("Try again");
            startMenu();
        }
    }
    public void createUser(){

    }
    public void login(){

    }
    public void mainMenu(){

    }
    public Media search(String input){
        //todo: add return statement
    }
    public Set<Media> filter(int i){
        //todo: add return statement
    }
    public void chooseMedia(){

    }
    public void playMedia(){

    }
}
