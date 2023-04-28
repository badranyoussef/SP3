package Entity;

import java.util.Objects;

public class Season {

    private int seasonNumber = seasonCounter;
    private static int seasonCounter = 1;

    public Season() {
        this.seasonNumber = seasonCounter;
        seasonCounter++;
    }


    public int getSeasonNumber() {
        return seasonNumber;
    }

    @Override
    public String toString() {
        return "Season: " + this.seasonNumber;
    }

    public void setSeasonCounter(int s){
        this.seasonCounter = s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Season season)) return false;
        return seasonNumber == season.seasonNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(seasonNumber);
    }

}
