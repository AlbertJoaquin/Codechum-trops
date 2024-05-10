import java.sql.*;

class AccountDatabaseManager {
    private String databaseUrl;
    private String username;
    private String password;

    public AccountDatabaseManager(String databaseUrl, String username, String password) {
        this.databaseUrl = databaseUrl;
        this.username = username;
        this.password = password;
    }

    public Connection getDatabaseConnection() throws SQLException {
        return DriverManager.getConnection(databaseUrl, username, password);
    }

    public boolean registerAccount(String name) {
        try (Connection conn = getDatabaseConnection();
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO account (name) VALUES (?)",
                        Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, name);
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                return false;
            }
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    System.out.println("Successfully registered!\nAccount ID: " + id);
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Account retrieveAccount(int id) {
        try (Connection conn = getDatabaseConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM account WHERE id = ?")) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int accountId = rs.getInt("id");
                    String name = rs.getString("name");
                    double savings = rs.getDouble("savings");
                    Date date = rs.getDate("datetime_updated");
                    return new Account(accountId, name, savings, date);
                } else {
                    System.out.println("No account found with ID: " + id);
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean withdraw(int id, double amount) {
        try (Connection conn = getDatabaseConnection();
                PreparedStatement stmt = conn.prepareStatement(
                        "UPDATE account SET savings = savings - ?, datetime_updated = ? WHERE id = ?")) {
            stmt.setDouble(1, amount);
            stmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            stmt.setInt(3, id);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deposit(int id, double amount) {
        try (Connection conn = getDatabaseConnection();
                PreparedStatement stmt = conn.prepareStatement(
                        "UPDATE account SET savings = savings + ?, datetime_updated = ? WHERE id = ?")) {
            stmt.setDouble(1, amount);
            stmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            stmt.setInt(3, id);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}