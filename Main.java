import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String dburl = "jdbc:mysql://localhost:3306/codechum";
        String dbusername = "root";
        String dbpassword = "";

        MovieDatabaseManager db = new MovieDatabaseManager(dburl, dbusername, dbpassword);

        Scanner scan = new Scanner(System.in);

        while (true) {

            System.out.println("Choose what to do: ");
            System.out.println("1.) Register Movie");
            System.out.println("2.) Retrieve Movie");
            System.out.println("3.) List All Movies");
            System.out.println("0.) Exit");
            System.out.println(" ");

            System.out.print("Choice: ");

            int choice = scan.nextInt();

            switch (choice) {
                case 1:
                    scan.nextLine();
                    System.out.println("\nEnter Movie Details");

                    System.out.print("Title: ");
                    String title = scan.nextLine();

                    System.out.print("Director: ");
                    String director = scan.nextLine();

                    System.out.print("Rating: ");
                    float rating = scan.nextFloat();

                    System.out.println("\nProceed to save? (Y/n) ");
                    char save = scan.next().charAt(0);

                    if (save == 'y' || save == 'Y') {
                        boolean result = db.registerMovie(title, director, rating);
                        if (result) {
                            System.out.println("Successfully saved!");
                        } else {
                            System.out.println("Failed to save the movie.");
                        }
                    } else {
                        System.out.println("Cancelled.");
                    }

                    break;

                case 2:
                    System.out.println("\nEnter Movie ID: ");
                    int movieId = scan.nextInt();

                    System.out.println("\nMovie Details");

                    Movie movie = db.retrieveMovie(movieId);
                    if (movie != null) {
                        System.out.println(movie.toString());
                    } else {
                        System.out.println("Movie not found.");
                    }

                    break;

                case 3:
                    System.out.println("\nAll Movies:");

                    List<Movie> movies = db.retrieveMovies();
                    if (movies.isEmpty()) {
                        System.out.println("No movies found.");
                    } else {
                        for (Movie m : movies) {
                            System.out.println(m);
                            System.out.println("");
                        }
                    }

                    break;

                case 0:
                    System.out.println("Exiting...");
                    scan.close(); // Close the scanner before exiting
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice, try again.");
                    break;
            }

            System.out.println();
        }
    }
}
