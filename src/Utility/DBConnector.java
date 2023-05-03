package Utility;

import Entity.Media;
import Entity.Movie;
import Entity.Series;
import Entity.User;

import java.sql.*;
import java.util.*;

public class DBConnector implements IO {

    // database URL
    static final String DB_URL = "jdbc:mysql://localhost/media";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "indtast din kode her";

    private Set<Media> setOfMedia = new HashSet<>();

    public int readPopulation(String city) {

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            //STEP 1: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 2: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 3: Execute a query
            System.out.println("Creating statement...");
            String sql = "SELECT population FROM world.city WHERE name = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, city);

            ResultSet rs = stmt.executeQuery();

            //STEP 4: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name

                return rs.getInt("Population");

            }
            //STEP 5: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        return 0;


    }

    @Override
    public List<String> readData(String path) {
        return null;
    }

    @Override
    public Set<User> readUserData(String path) {
        return null;
    }

    @Override
    public void saveUsers(String path, Set<User> userList) {

    }

    @Override
    public Set<Media> readMediaData() {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            //STEP 1: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 2: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 3: Execute a query
            System.out.println("Creating statement...");
            String sql = "SELECT * FROM media.movies UNION select * from media.series;";
            stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery(sql);

            //STEP 4: Extract data from result set

            while (rs.next()) {
                //Retrieve by column name

                String id = rs.getString("id");
                String title = rs.getString("Title");
                String releaseYear = rs.getString("ReleaseYear");

                String stringOfCategories = rs.getString("categories");
                String[] arrCategories = stringOfCategories.split(". "); //Split categories at comma+space
                ArrayList<String> categories = new ArrayList<>(Arrays.asList(arrCategories));

                String stringRating = rs.getString(5);
                float rating = Float.parseFloat(stringRating);


                String stringOfSeasons = rs.getString("seasons&episodes");

                if (stringOfSeasons.equals("")) {
                    //Or create new Movie()
                    Media m = new Movie(title, categories, rating, releaseYear);
                    setOfMedia.add(m);
                } else {
                    //Create new Series()
                    String[] splitSeasons = stringOfSeasons.trim().split(". "); //Split seasons from fifth index by comma+space
                    int seasons = splitSeasons.length; //Save number of seasons
                    List<Integer> numOfEpisodes = new ArrayList<>();

                    //For every season separate the number of episodes
                    for (int i = 0; i < seasons; i++) {
                        String[] splitEpisode = splitSeasons[i].split("-"); //Split number of episodes from season number
                        numOfEpisodes.add(Integer.parseInt(splitEpisode[1])); //add number of episodes to ArrayList

                    }
                    Media m = new Series(title, categories, rating, releaseYear, seasons, numOfEpisodes);
                    setOfMedia.add(m);
                }

                //System.out.println("ID:" + id + ") " + title + ": " + releaseYear + ": " + stringOfCategories + ": " + stringRating);
                //return this.setOfMedia;
            }
            //catch (Exception e) {
            //  System.out.println("The file movies.csv was not found: " + e.getMessage());
            //}

            //STEP 5: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
            return setOfMedia;
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (
                Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try

        return null;
    }
}