import java.sql.*;
import java.util.Scanner;

public class StudentDatabaseManager {

    String databaseUrl;
    String username;
    String password;

    public StudentDatabaseManager(String databaseUrl, String username, String password) {

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

    public boolean updateStudent(int id, String lastname, String firstname, String address, double tuitionFee) {
        String updateQuery = "UPDATE student SET last_name = ?, first_name = ?, address = ?, tuition_fee = ? WHERE id = ?";
        try (Connection conn = getDatabaseConnection();
                PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {

            pstmt.setString(1, lastname);
            pstmt.setString(2, firstname);
            pstmt.setString(3, address);
            pstmt.setDouble(4, tuitionFee);
            pstmt.setInt(5, id);

            int rowsAffected = pstmt.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error updating student: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {

        String dburl = "jdbc:mysql://localhost:3306/codechum";
        String dbusername = "root";
        String dbpassword = "";

        Scanner scan = new Scanner(System.in);

        StudentDatabaseManager student = new StudentDatabaseManager(dburl, dbusername, dbpassword);

        System.out.println("Choose what to do: ");

        System.out.println("1.) Register Student: ");
        System.out.println("2.) Update Student: ");
        System.out.println("0.) Exit: ");
        System.out.println(" ");

        System.out.print("Choice: ");

        int Choice = scan.nextInt();

        switch (Choice) {
            case 1:

                String name = scan.nextLine();

                System.out.println(" ");
                System.out.println("Enter Student Details");
                System.out.println(" ");

                System.out.print("Last Name: ");
                String lname = scan.nextLine();

                System.out.print("First Name: ");
                String fname = scan.nextLine();

                System.out.print("Address: ");
                String address1 = scan.nextLine();

                System.out.print("tuition Fee: ");
                double tuition = scan.nextDouble();

                System.out.println(" ");

                System.out.print("Proceed to save? (Y/n) ");
                char save = scan.next().charAt(0);

                if (save == 'y' || save == 'Y') {

                    student.registerStudent(lname, fname, address1, tuition);
                    System.out.println("Successfully saved!");

                } else {

                    System.out.println("Something wen wrong!");

                }

                break;

            case 2:

                System.out.println(" ");
                System.out.println("Update Student Details");
                System.out.println(" ");

                System.out.print("Student ID: ");
                int id = scan.nextInt();

                String updatename = scan.nextLine();

                System.out.print("Last Name: ");
                String updatelname = scan.nextLine();

                System.out.print("First Name: ");
                String updatefname = scan.nextLine();

                System.out.print("Address: ");
                String updateaddress1 = scan.nextLine();

                System.out.print("tuition Fee: ");
                double updatetuition = scan.nextDouble();

                System.out.println(" ");

                System.out.print("Proceed to save? (Y/n) ");
                char updatesave = scan.next().charAt(0);

                if (updatesave == 'y' || updatesave == 'Y') {

                    student.updateStudent(id, updatelname, updatefname, updateaddress1, updatetuition);
                    System.out.println("Successfully saved!");

                } else {

                    System.out.println("Something wen wrong!");

                }

                break;

            case 0:
                System.out.println("Exiting the program...");
                return;

            default:

                break;
        }

        scan.close();
    }
}
