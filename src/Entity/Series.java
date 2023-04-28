package Entity;


import java.util.*;

public class Series extends Media {

    private Map<Season, List<Episode>> seriesMap = new HashMap<>();

    private int seasons;

    private Episode e;
    private Season s;

    //Public constructor to initialize movie instances with 4 parameters that extends from its super class (media)
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
        //System.out.println(seriesMap);
    }


    public Set getSeasons(){
    return seriesMap.keySet();
    }

    public List<Episode> getEpisodes(Season s){

        //this.seriesMap.containsKey(s);
        //System.out.println(seriesMap.containsKey(s));


        /*String s = "";
        Collection <List<Episode>> episodes = seriesMap.containsKey(s);
        for (List l : episodes) {
            s = k;
            //System.out.println("value: "+l);
        }
        return s;*/
        return seriesMap.get(s);
    }


    public String getSeasonNumber(Season s){
        return seriesMap.get(s).toString();
    }


    @Override
    public String toString() {
        return super.toString() + ", Seasons: "+Integer.toString(seasons);
    }

}
