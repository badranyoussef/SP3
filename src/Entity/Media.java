package Entity;

import java.util.Scanner;

public abstract class Media {
    private Scanner scan = new Scanner(System.in);
    private String title;
    private String[] categories;
    private float rating;
    private int releaseYear;
    private int i = 1; //Why?
    private static int ii = 1; //Why?

    Media(String title, String[] categories, float rating, int releaseYear) {
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

    public String[] getCategory() {
        return categories;
    }

    public float getRating() {
        return rating;
    }

    public int getReleaseYear() {
        return releaseYear;
    }
    @Override
    public String toString() {
        return i + ")" + " " + this.title + ", " + this.releaseYear + ", " + category + ", " + rating;
    }

}
