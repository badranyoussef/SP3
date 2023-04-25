package Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Media {
    private String title;
    private List<String> categories;
    private float rating;
    private int releaseYear;
    private static int id = 0;

    Media(String title, ArrayList<String> categories, float rating, int releaseYear) {
        this.title = title;
        this.categories = categories;
        this.rating = rating;
        this.releaseYear = releaseYear;
        this.id++;
    }

    public int getid() {
        return this.id;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getCategory() {
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
        return id + ")" + " " + this.title + ", " + this.releaseYear + ", " + categories + ", " + rating;
    }

}
