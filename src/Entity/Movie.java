package Entity;

import java.util.ArrayList;

public class Movie extends Media{
    public Movie(String title, ArrayList<String> categories, float rating, int releaseYear) {
        super(title, categories, rating, releaseYear);
    }

}
