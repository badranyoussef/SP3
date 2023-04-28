package Entity;

public class Season {

    private int seasonNumber = seasonCounter;
    private static int seasonCounter = 1;

    public Season() {
        this.seasonNumber = seasonCounter;
        seasonCounter++;
    }

    @Override
    public String toString() {
        return "Season: " + this.seasonNumber;
    }
}
