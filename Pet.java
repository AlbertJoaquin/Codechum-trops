import java.sql.Date;
import java.text.SimpleDateFormat;

public class Pet {
    private int id;
    private String name;
    private String type;
    private boolean isVaccinated;
    private Date dateVaccinated;

    public Pet(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.isVaccinated = false;
        this.dateVaccinated = null;
    }

    public Pet(int id, String name, String type, boolean isVaccinated, Date dateVaccinated) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.isVaccinated = isVaccinated;
        this.dateVaccinated = dateVaccinated;
    }
    
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        if (!isVaccinated) {
            return String.format("Pet ID: %d\nName: %s\nType: %s\nNot vaccinated.", id, name, type);
        } else {
            return String.format("Pet ID: %d\nName: %s\nType: %s\nDate-time Vaccinated: %s", id, name, type, sdf.format(dateVaccinated));
        }
    }
}
