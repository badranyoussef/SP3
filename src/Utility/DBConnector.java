package Utility;

import Entity.Media;
import Entity.Movie;
import Entity.Series;
import Entity.User;

import java.sql.*;
import java.util.*;

public class DBConnector implements IO {

    // database URL
    static final String DB_URL = "jdbc:mysql://34.141.56.218/medias";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "LAHY-Dat23!";

    private Set<Media> setOfMedia = new HashSet<>();


    @Override
    public List<String> readCategoryList() {

        List<String> data = new ArrayList<>();

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            //STEP 1: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 2: Open a connection
            System.out.println("Connecting to category database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 3: Execute a query
            System.out.println("Creating statement...");
            String sql = "SELECT * FROM medias.categories";
            stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery(sql);

            //STEP 4: Extract data from result set

            while (rs.next()) {
                //Retrieve by column name

                String category = rs.getString("categories");
                data.add(category);

            }
            //STEP 5: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
            return data;
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

    @Override
    public Set<User> readUserData() {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            //STEP 1: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 2: Open a connection
            System.out.println("Connecting to user database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 3: Execute a query
            System.out.println("Creating statement...");
            String sql = "SELECT * FROM medias.userdata";
            stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery(sql);

            //STEP 4: Extract data from result set
            Set<User> data = new HashSet<>();

            while (rs.next()) {
                //Retrieve by column name

                String name = rs.getString("name");
                String userName = rs.getString("username");
                String password = rs.getString("password");

                data.add(new User(name, userName, password));

            }
            //STEP 5: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
            return data;
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

    @Override
    public void saveUsers(String name, String userName, String password) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            //STEP 1: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 2: Open a connection
            System.out.println("Connecting to user database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 3: Execute a query
            System.out.println("Creating statement...");

            //stmt = conn.createStatement();
            String query = "INSERT INTO userdata VALUES (?,?,?)";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, userName);
            stmt.setString(3, password);

            //STEP 4: insert data to database from result set
            stmt.execute();

            //STEP 5: Clean-up environment

            stmt.close();
            conn.close();
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
    }

    @Override
    public Set<Media> readMediaData() {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            //STEP 1: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 2: Open a connection
            System.out.println("Connecting to media database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 3: Execute a query
            System.out.println("Creating statement...");
            String sql = "SELECT * FROM medias.movies UNION select * from medias.series;";
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
            }
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