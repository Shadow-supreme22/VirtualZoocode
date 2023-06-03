import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


public class VirtualZoo {
    public void beginSimulation() {
        displayWelcome();
        ArrayList<Animal> zooAnimals = animalSelection();
        weekCycle(zooAnimals);

    }

    static void displayWelcome() {
        System.out.println("+----------------------------------------------------------------------+");
        System.out.println("|                     Welcome to the Virtual Zoo!                      |");
        System.out.println("|   Use this application to simulate running a zoo with many animals   |");
        System.out.println("|           This program is intended for zoo employees only            |");
        System.out.println("+----------------------------------------------------------------------+");
    }

    private static Scanner scan = new Scanner(System.in);
    private static ArrayList<String> usedNames = new ArrayList<>();



    private static int totalCost = 0;



    private static int numOfAnimals = 0;
    private static String previousItem="";
    private static ArrayList<String> previousNames = new ArrayList<>();



    private static Set<String> AllAnimal = new HashSet<>();
    public static String getPreviousItem() {
        return previousItem;
    }
public static int getTotalCost() {
    return totalCost;
}
public static boolean setTotalCost(int Cost){
        totalCost=Cost;

    return false;
}


    private static boolean isValidSpecies(String species) {
        String[] validSpecies = {"tiger", "elephant", "rhino", "panda", "monkey"};
        for (String valid : validSpecies) {
            if (valid.equalsIgnoreCase(species)) {
                return true;
            }
        }
        return false;
    }
    private static String capitalizeFirstLetter(String species) {
        return Character.toUpperCase(species.charAt(0)) + species.substring(1);
    }


    public static ArrayList<Animal> animalSelection() {

        int numberOfAnimals = askNumberAnimals();
        System.out.println();
        ArrayList<Animal> allZooAnimals = new ArrayList<>();
        for (int i = 0; i <numberOfAnimals; i++) {
            String name = askAnimalName(i+1);
            Animal animal = askAnimalSpecies(name);
            allZooAnimals.add(animal);

            usedNames.add(name);
        }
        return allZooAnimals;
    }

    private static String getSpecies(String species) {
        return species;
    }


    public static int askNumberAnimals() {
        int numAnimals = 0;
        boolean validInput = false;

        // Continue asking until a valid positive number is entered
        while (scan.hasNextLine() != validInput) {
            System.out.println("How many animals are at your zoo?");
            String input = scan.nextLine();

            try {
                numAnimals = Integer.parseInt(input);

                if (numAnimals <= 0) {
                    System.out.println("Please enter a positive number of animals");
                } else {
//                    return numAnimals;
                    validInput = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number of animals");
            }
        }
        return numAnimals;
    }
    //2nd askAnimalName() method
    public static String askAnimalName(int id) {
        String name = "";

        // Continue asking until a unique name is entered
        while (true) {
            System.out.println("What is the name of animal #" + id + "?");
            name = scan.nextLine();

            if (usedNames.contains(name)) {
                System.out.println("That name is already taken");
            } else {
                break;
            }
        }
        return name;
    }
    public static Animal askAnimalSpecies(String name) {
        Animal animal = null;
        while (animal == null) {

            System.out.println("What is the species of " + name + "?");
            String species = scan.nextLine().trim().toLowerCase();
            if (isValidSpecies(species)) {
                String capitalizedSpecies = capitalizeFirstLetter(species);
                animal = new Animal(name, capitalizedSpecies,Animal.giveFood(),Animal.giveToy(),Animal.giveWater());
                return animal;
            } else {
                System.out.println("That is not a valid species");
            }
        }
        return animal;
    }

//


    public static void askItem(Animal animal) {
        Scanner scan = new Scanner(System.in);
        String item;
        System.out.println("What item would you like to give to " + animal.getName() + "? ");
        while (scan.hasNextLine()) {
                item = scan.nextLine().toLowerCase();
                if (!item.equals("food") && !item.equals("water") && !item.equals("toy")) {
                    System.out.println("That is not a valid item");
                } else if (item.equalsIgnoreCase(previousItem)) {
                    System.out.println("You cannot give the same item as yesterday");
                    System.out.println("What item would you like to give to " + animal.getName() + "? ");
                } else {
                    previousItem = item;

                    if (item.equals("food")) {
                        animal.giveFood();
                        totalCost += animal.getFoodCost();
                        System.out.println(animal.toString());
                    } else if (item.equals("water")) {
                        animal.giveWater();
                        totalCost += animal.getWaterCost();
                        System.out.println(totalCost);
                    } else if (item.equals("toy")) {
                        animal.giveToy();
                        totalCost += animal.getToyCost();
                        System.out.println(totalCost);
                    }
                    if (animal.isDead()) {
                        totalCost += 1000;
                        System.out.println(animal.getName() + "(Dead)");
                    }


                }
        }
    }

    public static String getDayOfWeek(int day) {
        String Day = "";
        switch (day) {
            case 1:
                Day = "Monday";
                return Day;
            case 2:
                Day = "Tuesday";
                return Day;
            case 3:
                Day = "Wednesday";
                return Day;
            case 4:
                Day = "Thursday";
                return Day;
            case 5:
                Day = "Friday";
                return Day;
            case 6:
                Day = "Saturday";
                return Day;
            case 7:
                Day = "Sunday";
                return Day;
        }

        return Day;

    }

    public static void weekCycle(ArrayList<Animal> zooAnimals) {

        for (int day = 1; day <= 7; day++) {
            String dayOfWeek = getDayOfWeek(day);

            System.out.println("The current day is " + dayOfWeek);
            System.out.println();
            // Print the status of each animal in zooAnimals
            for (Animal animal:zooAnimals) {
System.out.println(animal.toString());
//                System.out.println(animal.getBoredom());
//                System.out.println(animal.getHunger());
//                System.out.println(animal.getThirst());
                System.out.println();
            }
        }
        // Ask the user what item they want to give to each animal
        for (Animal animal : zooAnimals) {
            if (!animal.isDead()) {
                askItem(animal);
            }
        }

        System.out.println();

        System.out.println("Total week cost = $" + Animal.getHunger()+Animal.getThirst()+Animal.getBoredom());


    }
//    import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;

//    public class VirtualZooTests {
//
//        @Test
//        public void testAnimalSelection() {
//            // Prepare test data
//            VirtualZoo virtualZoo = new VirtualZoo();
//            virtualZoo.setUsedNames(new ArrayList<>()); // Initialize usedNames list
//
//            // Simulate user input
//            InputStream inputStream = new ByteArrayInputStream("2\nAndy\nPanda\nHenry\nRhino\n".getBytes());
//            System.setIn(inputStream);
//
//            // Invoke the method
//            ArrayList<Animal> selectedAnimals = virtualZoo.animalSelection();
//
//            // Verify the output
//            Assertions.assertEquals(2, selectedAnimals.size(), "Number of selected animals should be 2");
//            Assertions.assertEquals("Andy", selectedAnimals.get(0).getName(), "First animal name should be Andy");
//            Assertions.assertEquals("Panda", selectedAnimals.get(0).getSpecies(), "First animal species should be Panda");
//            Assertions.assertEquals("Henry", selectedAnimals.get(1).getName(), "Second animal name should be Henry");
//            Assertions.assertEquals("Rhino", selectedAnimals.get(1).getSpecies(), "Second animal species should be Rhino");
//        }
//    }
//
//    private void setUsedNames(ArrayList<String> usedNames) {
//        this.usedNames = usedNames;
//    }



    public static  <allZooAnimals>  void main(String[] args) {
        ArrayList<Animal> zooAnimals = VirtualZoo.animalSelection();
        for(int i=0;i<7;i++) {
            ArrayList<Animal> allZooAnimals = animalSelection();
            System.out.println("\n" + allZooAnimals.get(i).getName());
            System.out.println("\n" + allZooAnimals.get(i).getSpecies());
            System.out.println("\n" + allZooAnimals.get(i).getHunger());
            System.out.println("\n" + allZooAnimals.get(i).getThirst());
            System.out.println("\n" + allZooAnimals.get(i).getBoredom());
        }
//        VirtualZoo.weekCycle(zooAnimals);
    }


}












