import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MovieDatabaseManager {

    String databaseUrl;
    String username;
    String password;

    public MovieDatabaseManager(String databaseUrl, String username, String password) {

        this.databaseUrl = databaseUrl;
        this.username = username;
        this.password = password;

    }

    public Connection getDatabaseConnection() throws SQLException {

        return DriverManager.getConnection(databaseUrl, username, password);

    }

    public boolean registerMovie(String title, String director, float rating) {

        String insertQuery = "INSERT INTO movie (title, director, rating) VALUES (?, ?, ?)";
        try (Connection conn = getDatabaseConnection();

                PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {

            pstmt.setString(1, title);
            pstmt.setString(2, director);
            pstmt.setFloat(3, rating);

            int rowsAffected = pstmt.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error registering student: " + e.getMessage());
            return false;
        }
    }

    public Movie retrieveMovie(int id) {
     
        try (Connection conn = getDatabaseConnection();
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM movie WHERE id =?")) {
                    
            pstmt.setInt(1, id);
            try (ResultSet resultSet = pstmt.executeQuery()) {

                if (resultSet.next()) {

                    int idnum = resultSet.getInt("id");
                    String title = resultSet.getString("title");
                    String director = resultSet.getString("director");
                    float rating = resultSet.getFloat("rating");

                    return new Movie(idnum, title, director, rating);
                    
                }
            }
        }

        catch (SQLException e) {
            System.err.println("Error retrieving movie: " + e.getMessage());
        }
 
        return null;
    }

    public List<Movie> retrieveMovies() {
        List<Movie> movies = new ArrayList<>();

        String selectQuery = "SELECT * FROM movie";
        try (Connection conn = getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(selectQuery);
             ResultSet resultSet = pstmt.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String director = resultSet.getString("director");
                float rating = resultSet.getFloat("rating");

                Movie movie = new Movie(id, title, director, rating);
                movies.add(movie);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving movies: " + e.getMessage());
        }

        return movies;
    }
}
