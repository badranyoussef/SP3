package Entity;

import java.util.Scanner;

public abstract class Media {
    Scanner scan = new Scanner(System.in);
    protected String title;
    protected String category;
    protected float rating;
    protected int releaseYear;

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
