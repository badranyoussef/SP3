package Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Media {
    private String title;
    private ArrayList<String> categories;
    private float rating;
    private String releaseYear;
    private int id;

    //This counter will be used to count how many medias gets initialized and assign its value to the media ID
    private static int counter = 1;

    //Constructor for media that ensures initializing all required instance variables when a media is initialized
    Media(String title, ArrayList<String> categories, float rating, String releaseYear) {
        this.title = title;
        this.categories = categories;
        this.rating = rating;
        this.releaseYear = releaseYear;
        this.id = counter;
        counter++;
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<String> getCategory() {
        return categories;
    }


    public float getRating() {
        return rating;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    //Overriding toString to return a nice String all details of a media
    @Override
    public String toString() {
        return id + ") " + this.title + ", " + this.releaseYear + ", " + "categories: " + showCategories() + rating;
    }

    //A method to show all categories
    public String showCategories() {
        String cat = "";
        for (String c : categories) {
            cat += c + ", ";
        }
        return cat;
    }

    //Overriding HashSet to ensure that we the ID of the media wil be returned once the object is called
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