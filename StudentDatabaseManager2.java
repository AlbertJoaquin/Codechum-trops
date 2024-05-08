import java.sql.*;
import java.util.Scanner;

public class StudentDatabaseManager2 {

    String databaseUrl;
    String username;
    String password;

    public StudentDatabaseManager2(String databaseUrl, String username, String password) {

        this.databaseUrl = databaseUrl;
        this.username = username;
        this.password = password;

    }

    public Connection getDatabaseConnection() throws SQLException {
        return DriverManager.getConnection(databaseUrl, username, password);

    }

    public boolean registerStudent(String lastname, String firstname, String address, double tuitionFee) {

        String insertQuery = "INSERT INTO student (last_name, first_name, address, tuition_fee) VALUES (?, ?, ?, ?)";
        try (Connection conn = getDatabaseConnection();

                PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {

            pstmt.setString(1, lastname);
            pstmt.setString(2, firstname);
            pstmt.setString(3, address);
            pstmt.setDouble(4, tuitionFee);

            int rowsAffected = pstmt.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error registering student: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {

        String dburl = "jdbc:mysql://localhost:3306/codechum";
        String dbusername = "root";
        String dbpassword = "";

        Scanner scan = new Scanner(System.in);

        StudentDatabaseManager student = new StudentDatabaseManager(dburl, dbusername, dbpassword);

        System.out.println("Enter Student Details");

        System.out.print("Last Name: ");
        String lname = scan.nextLine();

        System.out.print("First Name: ");
        String fname = scan.nextLine();

        System.out.print("Address: ");
        String address1 = scan.nextLine();

        System.out.print("tuition Fee: ");
        double tuition = scan.nextDouble();

        System.out.print("Proceed to save? (Y/n) ");
        char save = scan.next().charAt(0);

        if (save == 'Y' || save == 'y') {
            student.registerStudent(lname, fname, address1, tuition);
            System.out.println("Succesfully saved! ");
        } else if (save == 'n' || save == 'n') {
            System.out.println("Something went wrong!");
        }

        scan.close();
    }
}