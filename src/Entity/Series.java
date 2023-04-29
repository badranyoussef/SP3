package Entity;


import java.util.*;

public class Series extends Media {
    private Map<Season, List<Episode>> seriesMap = new HashMap<>();
    private int seasons;
    private Episode e;
    private Season s;

    //Public constructor to initialize movie instances with 6 parameters that extends from its super class (media)
    public Series(String movieTitle, ArrayList<String> movieCategories, float movieRating, String movieReleaseYear, int seasons, List<Integer> episodes) {
        super(movieTitle, movieCategories, movieRating, movieReleaseYear);
        this.seasons = seasons;
        for (int i = 0; i < seasons; i++) {
            s = new Season();
            List<Episode> listOfEpisode = new ArrayList<>();
            for (int k = 0; k < episodes.get(i); k++) {
                e = new Episode();
                listOfEpisode.add(e);
            }
            this.seriesMap.put(s, listOfEpisode);
            e.setCounter(1);
        }
        s.setSeasonCounter(1);
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
