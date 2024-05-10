
import java.text.SimpleDateFormat;
import java.sql.*;

public class Account {
    private int id;
    private String name;
    private double savings;
    private Date datetimeUpdated;

    public Account(int id, String name, double savings, Date datetimeUpdated) {
        this.id = id;
        this.name = name;
        this.savings = savings;
        this.datetimeUpdated = datetimeUpdated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSavings() {
        return savings;
    }

    public void setSavings(double savings) {
        this.savings = savings;
    }

    public Date getDatetimeUpdated() {
        return datetimeUpdated;
    }

    public void setDatetimeUpdated(Date datetimeUpdated) {
        this.datetimeUpdated = datetimeUpdated;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        String formattedDate = datetimeUpdated != null ? dateFormat.format(datetimeUpdated) : "N/A";
        return "Account ID: " + id + "\nName: " + name + "\nSavings: " + savings + "\nDate-time Updated: " + formattedDate;
    }
}