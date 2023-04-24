package Entity;

import java.util.Scanner;

public abstract class Media {
    private Scanner scan = new Scanner(System.in);
    private String title;
    private String category;
    private float rating;
    private int releaseYear;

    Media(String title, String category, float rating, int releaseYear){
        this.title = title;
        this.category = category;
        this.rating = rating;
        this.releaseYear = releaseYear;
    }

    public String getTitle(){
        return title;
    }

    public String getCategory(){
        return category;
    }

    public float getRating(){
        return rating;
    }
    public int getReleaseYear(){
        return releaseYear;
    }




}
