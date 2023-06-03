import java.util.ArrayList;
import java.util.Scanner;

public class VirtualZoo {
//    public void beginSimulation() {
//        displayWelcome();
//    }


//-------------------------operational methods------------------------------

    /**
     * Defines the Welcome Message text
     */
    private static void displayWelcome() {
        System.out.println("+----------------------------------------------------------------------+");
        System.out.println("|                     Welcome to the Virtual Zoo!                      |");
        System.out.println("|   Use this application to simulate running a zoo with many animals   |");
        System.out.println("|           This program is intended for zoo employees only            |");
        System.out.println("+----------------------------------------------------------------------+");
    }

    private static int totalCost = 0;
    private static ArrayList<String> previousNames = new ArrayList<>();
    private static String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    public static int getTotalCost() {
        return totalCost;
    }

    public static void setTotalCost(int totalCost) {
        VirtualZoo.totalCost = totalCost;
    }

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

    public static int askNumberAnimals() {
        Scanner scan = new Scanner(System.in);
        int numAnimals;

        while (true) {
            System.out.println("How many animals are at your zoo?");
            String input = scan.nextLine();

            try {
                numAnimals = Integer.parseInt(input);

                if (numAnimals <= 0) {
                    System.out.println("Please enter a positive number of animals");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number of animals");
            }
        }

        return numAnimals;
    }

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

        return new Animal(name, species);
    }

    public static void askItem(Animal animal) {
        Scanner scan = new Scanner(System.in);
        String itemName;


        while (true) {
            if(animal.isDead()==true){
                return;
            }else{
            System.out.println("What item would you like to give to " + animal.getName() + "?");
            itemName = scan.nextLine().toLowerCase();

            if (itemName.equals("food") || itemName.equals("water") || itemName.equals("toy")) {
                if (itemName.equals(animal.getPreviousItem())) {
                    System.out.println("You cannot give the same item as yesterday");
                } else {
                    animal.setPreviousItem(itemName);

                    switch (itemName) {
                        case "food":
                            animal.giveFood();
                            totalCost += animal.getFoodCost();
                            break;
                        case "water":
                            animal.giveWater();
                            totalCost += animal.getWaterCost();
                            break;
                        case "toy":
                            animal.giveToy();
                            totalCost += animal.getToyCost();
                            break;
                    }

                    if (animal.isDead()) {
                        totalCost += 1000;
                        System.out.println(animal.getName() + " has died");
                    }
                    break;
                }
            } else {
                System.out.println("That is not a valid item");
            }
        }}
    }

    public static void beginSimulation() {
        displayWelcome();
        ArrayList<Animal> animals = animalSelection();

        for (String day : daysOfWeek) {
            System.out.println("The current day is " + day);
            System.out.println();


            for (Animal animal : animals) {
                System.out.println("Name = " + animal.getName());
                System.out.println("Species = " + animal.getSpecies());
                System.out.println("Hunger = " + animal.getHunger());
                System.out.println("Thirst = " + animal.getThirst());
                System.out.println("Boredom = " + animal.getBoredom());
                System.out.println();
            }

            for (Animal animal : animals) {
                askItem(animal);
                System.out.println();
            }
        }

        System.out.println("Total week cost = $" + getTotalCost());
    }

    public static void main(String[] args) {
        beginSimulation();
    }
}

class Animal {
    private String name;
    private String species;
    private int hunger = 50;
    private int thirst = 50;
    private int boredom = 50;
    private String previousItem;

    public Animal(String name, String species) {
        this.name = name;
        this.species = species;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public int getHunger() {
        return hunger;
    }

    public int getThirst() {
        return thirst;
    }

    public int getBoredom() {
        return boredom;
    }

    public String getPreviousItem() {
        return previousItem;
    }

    public void setPreviousItem(String previousItem) {
        this.previousItem = previousItem;
    }

    public boolean isDead() {
        return hunger >= 100 || thirst >= 100 || boredom >= 100;
    }

    public int giveFood() {
        switch (species) {
            case "tiger":
                if(0<=hunger-30){
                    hunger -=30;
                }
                else{
                    hunger=0;}
                thirst += 15;
                boredom += 15;
                break;
            case "elephant":
                if(0<=hunger-50){
                    hunger -=50;
                }
                else{
                    hunger=0;}
                thirst += 30;
                boredom += 20;
                break;
            case "rhino":
                if(0<=hunger-25){
                    hunger -=25;
                }
                else{
                    hunger=0;}
                thirst += 5;
                boredom += 25;
                break;
            case "panda":
                if(0<=hunger-25){
                    hunger -=25;
                }
                else{
                    hunger=0;}
                thirst += 15;
                boredom += 15;
                break;
            case "monkey":
                if(0<=hunger-30){
                    hunger -=30;
                }
                else{
                    hunger=0;}
                thirst += 10;
                boredom += 10;
                break;
        }
        return hunger;
    }

    public int giveWater() {
        switch (species) {
            case "tiger":
                hunger += 15;
                if(0<=thirst-30){
                    thirst -=30;
                }
                else{
                    thirst=0;}
                boredom += 15;
                break;
            case "elephant":
                hunger += 20;
                if(0<=thirst-50){
                    thirst -=50;
                }
                else{
                    thirst=0;}
                boredom += 30;
                break;
            case "rhino":
                hunger += 5;
                if(0<=thirst-25){
                    thirst -=25;
                }
                else{
                    thirst=0;}
                boredom += 25;
                break;
            case "panda":
                hunger += 40;
                if(0<=thirst-80){
                    thirst -=80;
                }
                else{
                    thirst=0;}
                boredom += 10;
                break;
            case "monkey":
                hunger += 10;
                if(0<=thirst-40){
                    thirst -=40;
                }
                else{
                    thirst=0;}
                boredom += 10;
                break;
        }
        return thirst;
    }

    public int giveToy() {
        switch (species) {
            case "tiger":
                hunger += 15;
                thirst += 15;
                if(0<=boredom-30){
                    boredom -=30;
                }
                else{
                    boredom=0;}
                break;
            case "elephant":
                hunger += 30;
                thirst += 10;
                if(0<=boredom-40){
                    boredom -=40;
                }
                else{
                    boredom=0;}
                break;
            case "rhino":
                hunger += 20;
                thirst += 20;
                if(0<=boredom-50){
                    boredom -=50;
                }
                else{
                    boredom=0;}
                break;
            case "panda":
                hunger += 40;
                thirst += 15;
                if(0<=boredom-40){
                    boredom -=40;
                }
                else{
                    boredom=0;}
                break;
            case "monkey":
                hunger += 10;
                thirst += 10;
                if(0<=boredom-15){
                    boredom -=15;
                }
                else{
                    boredom=0;}
                break;
        }
        return boredom;
    }

    public int getFoodCost() {
        int cost = 0;
        switch (species) {
            case "tiger":
                cost = 30;
                break;
            case "elephant":
                cost = 100;
                break;
            case "rhino":
                cost = 50;
                break;
            case "panda":
                cost = 60;
                break;
            case "monkey":
                cost = 5;
                break;
        }
        return cost;
    }

    public int getWaterCost() {
        int cost = 0;
        switch (species) {
            case "tiger":
                cost = 20;
                break;
            case "elephant":
                cost = 40;
                break;
            case "rhino":
                cost = 50;
                break;
            case "panda":
                cost = 5;
                break;
            case "monkey":
                cost = 5;
                break;
        }
        return cost;
    }

    public int getToyCost() {
        int cost = 0;
        switch (species) {
            case "tiger":
                cost = 40;
                break;
            case "elephant":
                cost = 75;
                break;
            case "rhino":
                cost = 20;
                break;
            case "panda":
                cost = 10;
                break;
            case "monkey":
                cost = 15;
                break;
        }
        return cost;
    }
}