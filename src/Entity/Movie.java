package Entity;
import java.util.ArrayList;

public class Movie extends Media {
    //Public constructor to initialize movie instances with 4 parameters that extends from its super class (media)
    public Movie(String movieTitle, ArrayList<String> movieCategories, float movieRating, String movieReleaseYear) {
        super(movieTitle, movieCategories, movieRating, movieReleaseYear);
    }
}
