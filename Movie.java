public class Movie {

    int id;
    String title, director;
    float rating;

    public Movie(int id, String title, String director, float rating) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public float getRating() {
        return rating;
    }

    public void setId(int newID) {
        id = newID;
    }

    public void setTitle(String newTitle) {
        title = newTitle;
    }

    public void setDirector(String newDirector) {
        director = newDirector;
    }

    public void setRating(float newRating) {
        rating = newRating;
    }

    public String toString() {
        return String.format(
                "Movie ID: %d\nTitle: %s\nDirector: %s\nRating: %.1f",
                id, title, director, rating);
    }

}