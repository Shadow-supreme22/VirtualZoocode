import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 *
 * @author Your name, student number and FAN here
 */
public class VirtualZoo {
    static int totalCost=0;
    private static String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    public static int getTotalCost() {
        return totalCost;
    }
    public static void setTotalCost(int totalCost) {
        VirtualZoo.totalCost = totalCost;
    }

    static HashSet<String> previousNames = new HashSet<>();
    private static Scanner scan = null;

    public VirtualZoo() {
        scan = new Scanner(System.in);
    }
    public void weekCycle(ArrayList<Animal> zooAnimals) {

        for (String day : daysOfWeek) {
            System.out.println("The current day is " + day);
            for (Animal animal : zooAnimals) {
                System.out.println("Name = " + animal.getName());
                System.out.println("Species = " + animal.getSpecies());
                System.out.println("Hunger = " + animal.getHunger());
                System.out.println("Thirst = " + animal.getThirst());
                System.out.println("Boredom = " + animal.getBoredom());
                System.out.println();
            }
            for (Animal animal : zooAnimals) {
                if (!animal.isDead()) {
                    //System.out.println(animal.toString());
                    askItem(animal);
                }
            }
        }

        System.out.println("Total week cost = $" + getTotalCost());
    }

    /**
     *Number of animals
     */
    public static int askNumberAnimals() {
        int numAnimals = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.println("How many animals are at your zoo?");
            String input = scan.nextLine();

            if (input.matches("\\d+")) {
                numAnimals = Integer.parseInt(input);

                if (numAnimals > 0) {
                    validInput = true;
                } else {
                    System.out.println("Please enter a positive number of animals");
                }
            } else {
                System.out.println("Please enter a valid number of animals");
            }
        }

        return numAnimals;
    }

    /**
     *Name of animals
     */
    public static String askAnimalName(int id) {
        Scanner scan = new Scanner(System.in);
        String animalName;

        while (true) {
            System.out.println("What is the name of animal #" + id + "?");
            animalName = scan.nextLine();

            if (previousNames.contains(animalName)) {
                System.out.println("That name is already taken");
            } else {
                previousNames.add(animalName);
                break;
            }
        }

        return animalName;
    }
    /**
     *species of animals
     */
    public static Animal askAnimalSpecies(String name) {
        Scanner scan = new Scanner(System.in);
        String species;

        while (true) {
            System.out.println("What is the species of " + name + "?");
            species = scan.nextLine().toLowerCase();

            if (species.equals("tiger") || species.equals("elephant") ||
                    species.equals("rhino") || species.equals("panda") || species.equals("monkey")) {
                break;
            } else {
                System.out.println("That is not a valid species");
            }
        }

        Animal animal;
        switch (species) {
            case "tiger":
                animal = new Tiger(name);
                break;
            case "elephant":
                animal = new Elephant(name);
                break;
            case "rhino":
                animal = new Rhino(name);
                break;
            case "panda":
                animal = new Panda(name);
                break;
            case "monkey":
                animal = new Monkey(name);
                break;
            default:
                animal = null;
        }

        return animal;
    }

   // public static Animal createAnimal(String name, String species) {
//        switch (species) {
//            case "tiger":
//                return new Tiger(name);
//            case "elephant":
//                return new Elephant(name);
//            case "rhino":
//                return new Rhino(name);
//            case "panda":
//                return new Panda(name);
//            case "monkey":
//                return new Monkey(name);
//            default:
//                return null;
//        }
   // }
   public static ArrayList<Animal> animalSelection() {
       int numAnimals = askNumberAnimals();
       ArrayList<Animal> animals = new ArrayList<>();

       for (int i = 0; i < numAnimals; i++) {
           String animalName = askAnimalName(i + 1);
           Animal animal = askAnimalSpecies(animalName);
           animals.add(animal);
       }

       return animals;
   }
    public static void askItem(Animal animal) {
        String animalName = animal.getName();
        String itemName;

        while (true) {
            System.out.println("What item would you like to give to " + animalName + "?");
            itemName = scan.nextLine().toLowerCase();

            if (!isValidItem(itemName)) {
                System.out.println("That is not a valid item");
            } else if (isSameItemAsPrevious(animal, itemName)) {
                System.out.println("You cannot give the same item as yesterday");
            } else {
                break;
            }
        }

        performAction(animal, itemName);

        if (animal.isDead()) {
            VirtualZoo.setTotalCost(VirtualZoo.getTotalCost() + 1000);
            System.out.println(animalName + " has died");
        }
    }

    private static boolean isValidItem(String itemName) {
        return itemName.equals("food") || itemName.equals("water") || itemName.equals("toy");
    }

    private static boolean isSameItemAsPrevious(Animal animal, String itemName) {
        String previousItem = animal.getPreviousItem();
        return previousItem != null && previousItem.equalsIgnoreCase(itemName);
    }

    private static void performAction(Animal animal, String itemName) {
        animal.setPreviousItem(itemName);

        if (itemName.equals("food")) {
            animal.giveFood();
        } else if (itemName.equals("water")) {
            animal.giveWater();
        } else if (itemName.equals("toy")) {
            animal.giveToy();
        }
    }



    public void beginSimulation() {
        displayWelcome();
        ArrayList<Animal> zooAnimals = animalSelection();
        weekCycle(zooAnimals);
    }

    //-------------------------operational methods------------------------------

    /**
     * Defines the Welcome Message text
     */
    private void displayWelcome() {
        System.out.println("+----------------------------------------------------------------------+");
        System.out.println("|                     Welcome to the Virtual Zoo!                      |");
        System.out.println("|   Use this application to simulate running a zoo with many animals   |");
        System.out.println("|           This program is intended for zoo employees only            |");
        System.out.println("+----------------------------------------------------------------------+");
    }

}

abstract class Animal {
    String name;
    String species;
    int hunger = 50;
    int thirst = 50;
    int boredom = 50;
    private String previousItem;
    //int totalCost=0;
    //public static int totalCost = 0;
//    public static int getTotalCost() {
//        return totalCost;
//    }
    public Animal(String name, String species) {
        this.name = name;
        this.species = species;
    }


    public String toString() {
        String statusMessage = isDead() ? "Name = " + getName() + " (Dead)\n" : "Name = " + getName() + "\n";
        statusMessage += "Species = " + getSpecies() + "\n";
        statusMessage += "Hunger = " + getHunger() + "\n";
        statusMessage += "Thirst = " + getThirst() + "\n";
        statusMessage += "Boredom = " + getBoredom() + "\n";
        return statusMessage;
    }

    // Getters and setters for name, species, hunger, thirst, boredom, and totalCost
    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public int getBoredom() {
        return boredom;
    }

    public int getThirst() {
        return thirst;
    }

    public int getHunger() {
        return hunger;
    }





    public void setBoredom(int boredom) {
        this.boredom=boredom;
    }

    public void setHunger(int hunger) {
        this.hunger=hunger;
    }

    public void setName(String name) {
        this.name= name;
    }

    public void setThirst(int thirst) {
        this.thirst=thirst;
    }
    public boolean isDead() {
        return thirst >= 100 || hunger >= 100 || boredom >= 100;
    }

    public void giveFood() {
        if(0<=hunger-30){
            hunger-=30;
        }
        else{
            hunger=0;
        }

        thirst += 15;
        boredom += 15;
        VirtualZoo.totalCost += 30;
    }

    public void giveWater() {
        if(0<=thirst-30){
            thirst -= 30;
        }
        else{
            thirst=0;
        }
        hunger += 15;
        boredom += 15;
        VirtualZoo.totalCost += 20;
    }

    public void giveToy() {
        if(0<=boredom-30){
            boredom -= 30;
        }
        else{
            boredom=0;
        }
        hunger += 15;
        thirst += 15;
        VirtualZoo.totalCost += 40;
    }

    public String getPreviousItem() {
        return previousItem;
    }

    public void setPreviousItem(String previousItem) {
        this.previousItem = previousItem;
    }
}

class Tiger extends Animal {
    public Tiger(String name) {
        super(name, "Tiger");
    }

    @Override
    public void giveFood() {
        if(0<=hunger-30){
            hunger-=30;
        }
        else{
            hunger=0;
        }

        thirst += 15;
        boredom += 15;
        VirtualZoo.totalCost += 30;
    }
    @Override
    public void giveWater() {
        if(0<=thirst-30){
            thirst -= 30;
        }
        else{
            thirst=0;
        }
        hunger += 15;
        boredom += 15;
        VirtualZoo.totalCost += 20;
    }
    @Override
    public void giveToy() {
        if(0<=boredom-30){
            boredom -= 30;
        }
        else{
            boredom=0;
        }
        hunger += 15;
        thirst += 15;
        VirtualZoo.totalCost += 40;
    }
}

class Elephant extends Animal {
    public Elephant(String name) {
        super(name, "Elephant");
    }

    @Override
    public void giveFood() {
        if(0<=hunger-50){
            hunger-=50;
        }
        else{
            hunger=0;
        }

        thirst += 30;
        boredom += 20;
        VirtualZoo.totalCost += 100;
    }
    @Override
    public void giveWater() {
        if(0<=thirst-50){
            thirst -= 50;
        }
        else{
            thirst=0;
        }
        hunger += 20;
        boredom += 30;
        VirtualZoo.totalCost += 40;
    }
    @Override
    public void giveToy() {
        if(0<=boredom-40){
            boredom -= 40;
        }
        else{
            boredom=0;
        }
        hunger += 30;
        thirst += 10;
        VirtualZoo.totalCost += 75;
    }
}

class Rhino extends Animal {
    public Rhino(String name) {
        super(name, "Rhino");
    }

    @Override
    public void giveFood() {
        if(0<=hunger-25){
            hunger-=25;
        }
        else{
            hunger=0;
        }

        thirst += 5;
        boredom += 25;
        VirtualZoo.totalCost += 50;
    }
    @Override
    public void giveWater() {
        if(0<=thirst-25){
            thirst -= 25;
        }
        else{
            thirst=0;
        }
        hunger += 5;
        boredom += 25;
        VirtualZoo.totalCost += 50;
    }
    @Override
    public void giveToy() {
        if(0<=boredom-50){
            boredom -= 50;
        }
        else{
            boredom=0;
        }
        hunger += 20;
        thirst += 20;
        VirtualZoo.totalCost += 20;
    }
}
        class Panda extends Animal {
            public Panda(String name) {
                super(name, "Panda");
            }

            @Override
            public void giveFood() {
                if(0<=hunger-25){
                    hunger-=25;
                }
                else{
                    hunger=0;
                }

                thirst += 15;
                boredom += 15;
                VirtualZoo.totalCost += 60;
            }
            @Override
            public void giveWater() {
                if(0<=thirst-80){
                    thirst -= 80;
                }
                else{
                    thirst=0;
                }
                hunger += 40;
                boredom += 10;
                VirtualZoo.totalCost += 5;
            }
            @Override
            public void giveToy() {
                if(0<=boredom-40){
                    boredom -= 40;
                }
                else{
                    boredom=0;
                }
                hunger += 40;
                thirst += 15;
                VirtualZoo.totalCost += 10;
            }
        }

        class Monkey extends Animal {
            public Monkey(String name) {
                super(name, "Monkey");
            }

            @Override
            public void giveFood() {
                if(0<=hunger-30){
                    hunger-=30;
                }
                else{
                    hunger=0;
                }

                thirst += 10;
                boredom += 10;
                VirtualZoo.totalCost += 5;
            }
            @Override
            public void giveWater() {
                if(0<=thirst-40){
                    thirst -= 40;
                }
                else{
                    thirst=0;
                }
                hunger += 10;
                boredom += 10;
                VirtualZoo.totalCost += 5;
            }
            @Override
            public void giveToy() {
                if(0<=boredom-15){
                    boredom -= 15;
                }
                else{
                    boredom=0;
                }
                hunger += 10;
                thirst += 10;
                VirtualZoo.totalCost += 15;
            }
        }

