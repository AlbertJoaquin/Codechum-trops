import java.sql.*;

public class PetDatabaseManager {
    private String databaseUrl;
    private String username;
    private String password;

    public PetDatabaseManager(String databaseUrl, String username, String password) {
        this.databaseUrl = databaseUrl;
        this.username = username;
        this.password = password;
    }

    public Connection getDatabaseConnection() throws SQLException {
        return DriverManager.getConnection(databaseUrl, username, password);
    }

    public boolean registerPet(String name, String type) {
        try (Connection connection = getDatabaseConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO pet (name, type) VALUES (?, ?)")) {
            statement.setString(1, name);
            statement.setString(2, type);
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Pet retrievePet(int id) {
        try (Connection connection = getDatabaseConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM pet WHERE id = ?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int petId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String type = resultSet.getString("type");
                boolean isVaccinated = resultSet.getBoolean("is_vaccinated");
                Date dateVaccinated = resultSet.getDate("date_vaccinated");
                return new Pet(petId, name, type, isVaccinated, dateVaccinated);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean vaccinatePet(int id) {
        try (Connection connection = getDatabaseConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE pet SET is_vaccinated = ?, date_vaccinated = ? WHERE id = ?")) {
            statement.setBoolean(1, true);
            statement.setDate(2, new Date(System.currentTimeMillis()));
            statement.setInt(3, id);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
