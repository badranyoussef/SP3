package Entity;

import java.util.Objects;

public class Season {
    private int seasonNumber = seasonCounter;
    private static int seasonCounter = 1;

    //Public construc
    public Season() {
        this.seasonNumber = seasonCounter;
        seasonCounter++;
    }

    //Method to get the season
    public int getSeasonNumber() {
        return seasonNumber;
    }

    @Override
    public String toString() {
        return "Season: " + this.seasonNumber;
    }

    //Method to reset the season counter everytime we start creating a new series
    public void setSeasonCounter(int s){
        this.seasonCounter = s;
    }
    //Override eaquals() and hashCode() to sort season by seasonNumber when printing
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
