import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<Plant> plant = new ArrayList<>();
        plant.add(new Plant("Little red rose", "My first flower", LocalDate.of(2024, 10, 1), LocalDate.of(2024, 10, 9), (short) 7));
        plant.add(new Plant("Avocado", "For now on the stick", LocalDate.of(2024, 10, 10), LocalDate.of(2024, 10, 25), (short) 7));
        plant.add(new Plant("Herbs", "Set of small herbs", LocalDate.of(2024, 10, 20), LocalDate.of(2024, 10, 25), (short) 7));
        plant.add(new Plant("Palm", "Small little young palm", LocalDate.of(2024, 10, 29), LocalDate.of(2024, 10, 30), (short) 7));

        for (int i = 1; i <= 10; i += 1) {
            plant.add(new Plant("Tulip " + (i), "Tulip for sale", LocalDate.now(), LocalDate.now(), (short) 14));
        }

        //plantInventory.removePlant(3);

        PlantInventory plantInventory = new PlantInventory();
        plantInventory.setPlants(plant);

        System.out.println("--> Save: Done");

        PlantInventory saver = plantInventory;
        saver.savePlantsToFile("/Users/flame-lame/Desktop/Flowers-save.txt");

        System.out.println("--> Load");

        System.out.println("--> Need watering plants");
        System.out.println(plantInventory.printAllPlantsToWatering());

        System.out.println("--> Sorted by name");
        System.out.println(plantInventory.getSortedNames());

        System.out.println("--> Sorted by last watering");
        System.out.println(plantInventory.getSortedLastWatering());

        System.out.println("--> All plants");
        System.out.println(plantInventory.printAllPlants());

        /*
        System.out.println("--> Copy of Plant Inventory");
        ArrayList<Plant> copiedPlants = plantInventory.copyPlants();
        for (Plant plantCopy : copiedPlants) {
            System.out.println(plantCopy);
        }
        */

    }
}