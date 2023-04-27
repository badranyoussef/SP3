package Entity;

public class Episode {

    private int episodeNumber = episodeCounter;
    private static int episodeCounter = 1;
    public Episode(){
        this.episodeNumber = episodeCounter;
        episodeCounter++;
    }

    @Override
    public String toString(){
        return "Episode: "+this.episodeNumber;
    }
}
