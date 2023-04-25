package Entity;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Media {
    private Scanner scan = new Scanner(System.in); //Why?
    private String title;
    private ArrayList<String> categories;
    private float rating;
    private int releaseYear;
    private int i = 1; //Why?
    private static int ii = 1; //Why?

    Media(String title/*, ArrayList<String> categories,*/, float rating) {
        this.title = title;
        this.categories = categories;
        this.rating = rating;
        this.releaseYear = releaseYear;
        this.i = ii;
        ii++;
    }

    public int getI() {
        return this.i;
    }

    public String getTitle() {
        return title;
    }

    /*public String[] getCategory() {
        return categories;
    }*/

    public float getRating() {
        return rating;
    }

    public int getReleaseYear() {
        return releaseYear;
    }
    @Override
    public String toString() {
        return i + ")" + " " + this.title + ", " + this.releaseYear + ", " + categories + ", " + rating;
    }

}
