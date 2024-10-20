import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PlantInventory {

        private ArrayList<Plant> plants = new ArrayList<>();

        public List<String> getSortedNames() {
            if (plants == null) {
                return Collections.emptyList();
            }
            return plants.stream()
                    .map(plant -> plant.getName() + " ")
                    .sorted()
                    .collect(Collectors.toList());
        }

        public List<String> getSortedLastWatering() {
            if (plants == null) {
                return Collections.emptyList();
            }
            return plants.stream()
                    .sorted(Comparator.comparing(Plant::getDateWatering))
                    .map(plant -> plant.getName() + ": " + plant.getDateWatering() + " ")
                    .collect(Collectors.toList());
        }

        public void addPlant(Plant plant) {
            plants.add(plant);
        }

        public void removePlant(int index) {
            plants.remove(index);
        }

        public ArrayList<Plant> getPlants() {
            return plants;
        }

        public void setPlants(ArrayList<Plant> plants) {
            this.plants = plants;
        }

        public ArrayList<Plant> copyPlants() {
            return new ArrayList<>(plants);
        }

        public void savePlantsToFile(String filePath) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                for (Plant plant : plants) {
                    String plantData = String.format("%s, %s, %s, %s",
                            plant.getName(),
                            plant.getNotes(),
                            plant.getDatePlanted(),
                            plant.getDateWatering());
                    writer.write(plantData);
                    writer.newLine();
                }
            } catch (IOException e) {
                System.err.println("An error occurred while writing to the file: " + e.getMessage());
            }
        }
/*
        public void loadPlantsFromFile(String filePath) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(", ");
                    if (data.length == 4) {
                        try {
                            Plant plant = new Plant();
                            plant.setName(data[0].split(": ")[1].trim());
                            plant.setNotes(data[1].split(": ")[1].trim());
                            plant.setDatePlanted(LocalDate.parse(data[2].split(": ")[1].trim()));
                            plant.setDateWatering(LocalDate.parse(data[3].split(": ")[1].trim()));
                            plants.add(plant);
                        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
                            System.err.println("Chyba při zpracování řádku: " + line + " s chybou: " + e.getMessage());
                        }
                    } else {
                        System.err.println("Špatný formát řádku: " + line);
                    }
                }
            } catch (IOException e) {
                System.err.println("Došlo k chybě při čtení ze souboru: " + e.getMessage());
            }
            */

        public String printAllPlants() {
            StringBuilder plantsOutput = new StringBuilder();
            for (Plant plant : plants) {
                plantsOutput.append(plant.toString()).append("\n");
            }
            return plantsOutput.toString();
        }

        public String printAllPlantsToWatering() {
            StringBuilder plantsOutput = new StringBuilder();
            for (Plant plant : plants) {
                LocalDate datePlanted = plant.getDatePlanted();
                LocalDate dateWatering = plant.getDateWatering();
                int wateringInterval = plant.getWateringInterval();
                long daysBetween = ChronoUnit.DAYS.between(datePlanted, dateWatering);
                if (daysBetween > wateringInterval) {
                    long daysOverdue = daysBetween - wateringInterval;
                    plantsOutput.append(plant.getName())
                            .append(" - ")
                            .append(daysOverdue)
                            .append(" days overdue\n");
                }
            }
            return plantsOutput.toString();
        }
        @Override
        public String toString() {
            StringBuilder plantsOutput = new StringBuilder();
            for (Plant plant : plants) {
                plantsOutput.append(plant.toString()).append("\n");
            }
            return plantsOutput.toString();
        }
}
