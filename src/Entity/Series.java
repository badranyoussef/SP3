package Entity;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Series extends Media {

    private Map<Season, List<Episode>> seriesMap = new HashMap<>();

    private int seasons;


    //Public constructor to initialize movie instances with 4 parameters that extends from its super class (media)
    public Series(String movieTitle, ArrayList<String> movieCategories, float movieRating, String movieReleaseYear, int seasons, List<Integer> episodes) {
        super(movieTitle, movieCategories, movieRating, movieReleaseYear);
        this.seasons = seasons;

        for (int i = 0; i < seasons; i++) {
            Season s1 = new Season();
            List<Episode> listOfEpisode = new ArrayList<>();
            for (int j = 0; j < episodes.size(); j++) {
                Episode e = new Episode();
                listOfEpisode.add(e);
            }
            this.seriesMap.put(s1, listOfEpisode);
        }
    }

    public Map<Season, List<Episode>> getSeriesMap() {
        return seriesMap;
    }

    @Override
    public String toString() {
        return super.toString() + seasons;
    }
}
