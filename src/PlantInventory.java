import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.time.temporal.ChronoUnit;
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

        // Simple load - working but without solid exception handling
        public static void loadPlantsFromFile(String filePath) {
            try (Scanner scanner = new Scanner(new File(filePath))) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    System.out.println(line);
                }
            } catch (FileNotFoundException e) {
                System.out.println("No file found: " + filePath);
                e.printStackTrace();
            }
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
