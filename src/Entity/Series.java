package Entity;


import java.util.ArrayList;

public class Series extends Media {

    private int seasons;
    private int episodes;

    //Public constructor to initialize movie instances with 4 parameters that extends from its super class (media)
    public Series(String movieTitle, ArrayList<String> movieCategories, float movieRating, String movieReleaseYear, int seasons, int episodes) {
        super(movieTitle, movieCategories, movieRating, movieReleaseYear);
        this.seasons = seasons;
        this.episodes = episodes;
    }



}