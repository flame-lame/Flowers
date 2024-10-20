import java.time.LocalDate;

public class Plant {

    private String name;
    private String notes;
    private LocalDate datePlanted;
    private LocalDate dateWatering;
    private short wateringInterval;

    public Plant(String name, String notes, LocalDate datePlanted, LocalDate dateWatering, short wateringInterval) {

        try {
            name = name.trim();
            if (name.isEmpty()) {
                throw new PlantException("Name cannot be empty!");
            }
        } catch (PlantException e) {
            System.err.println("You didn't set a name: "+ e.getMessage());
        } finally {
            this.name = name;
        }
        this.notes = notes;
        this.datePlanted = datePlanted;
        this.dateWatering = dateWatering;
        this.wateringInterval = wateringInterval;
    }

    public String getName() {
        return name;
    }

    public String getNotes() {
        return notes;
    }

    public LocalDate getDatePlanted() {
        return datePlanted;
    }

    public LocalDate getDateWatering() {
        return dateWatering;
    }

    public short getWateringInterval() {
        return wateringInterval;
    }

    public String getWaterInfo() {
        return "Name : " + getName() + " Last watering: " + getDateWatering() + " Recommended watering interval: " + getWateringInterval() + " days";
    }

    public void doWateringNow() {
        dateWatering = LocalDate.now();
    }

    public void setFlowerName(String name)
        throws PlantException {
        if (name == null || name.isEmpty()) {
            throw new PlantException("You didn't set a name: Name cannot be empty!");
        }
        this.name = name;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setDatePlanted(LocalDate datePlanted) {
        this.datePlanted = datePlanted;
    }

    public void setDateWatering(LocalDate dateWatering) {
        this.dateWatering = dateWatering;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Notes: " + notes + ", Date Planted: " + datePlanted + ", Date Watering: " + dateWatering + ", Watering Interval: " + wateringInterval;
    }


}


