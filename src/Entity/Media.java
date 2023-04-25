package Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public abstract class Media {
    private String title;
    private List<String> categories;
    private float rating;
    private int releaseYear;
    private int id;

    private static int counter = 1;

    Media(String title, ArrayList<String> categories, float rating, int releaseYear) {
        this.title = title;
        this.categories = categories;
        this.rating = rating;
        this.releaseYear = releaseYear;
        this.id = counter;
        counter++;
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
        return id + ") "+ this.title + ", " + this.releaseYear + ", " + "categories: " + showCategories() + rating;
    }

    public String showCategories(){
        String cat = "";
        for (String c: categories) {
            cat += c + ", ";
        }
        return cat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Media media)) return false;
        return id == media.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
