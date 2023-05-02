package Entity;
import java.util.*;
public class Series extends Media {
    //A Series have a Map with seasons as key and list of episodes as value
    private Map<Season, List<Episode>> seriesMap = new HashMap<>();
    private int seasons;
    private Episode episode;
    private Season season;

    //Public constructor to initialize movie instances with 6 parameters that extends from its super class (media)
    public Series(String movieTitle, ArrayList<String> movieCategories, float movieRating, String movieReleaseYear, int seasons, List<Integer> episodes) {
        super(movieTitle, movieCategories, movieRating, movieReleaseYear);
        this.seasons = seasons;
        //When reading our Series data we get:
        //1. A number for how many seasons exists
        //2. A list of ints for how many episodes in each season
        //For every season in a Series we initialize a new Season()
        for (int i = 0; i < seasons; i++) {
            season = new Season();
            List<Episode> listOfEpisodes = new ArrayList<>(); //Initializing a new list to hold all episodes og a Season
            //For every episode in every season in a Series create a new Episode() and add to listOfEpisodes
            for (int k = 0; k < episodes.get(i); k++) {
                episode = new Episode();
                listOfEpisodes.add(episode);
            }
            //Everytime a season is created, put season with the belonging episodes in our seriesMap
            this.seriesMap.put(season, listOfEpisodes);
            //Reset episode counter, so it start from 1 in next season
            episode.setCounter(1);
        }
        //Reset season counter so it start from 1 in next series
        season.setSeasonCounter(1);
    }


    //Method to get Seasons in a serie
    public Set getSeasons(){
    return seriesMap.keySet();
    }

    //Method to get episodes in a season
    public List<Episode> getEpisodes(Season s){
        return seriesMap.get(s);
    }

    @Override
    public String toString() {
        return super.toString() + ", Seasons: "+Integer.toString(seasons);
    }

}
