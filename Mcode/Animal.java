public  class Animal {
    protected String name;
    protected  String species;
    protected static int hunger;
    protected  static int thirst;
    protected  static int boredom;
    private  static String lastItemGiven;
    protected String previousItem;

    public Animal(String name,String species,int a, int b,int c) {
        this.name = name;
        this.species=species;
        hunger=a;
        thirst=b;
        boredom=c;
//
    }
    public Animal(int hunger,int thirst,int boredom){
        this.hunger=hunger;
        this.thirst=thirst;
        this.boredom=boredom;
    }
    public Animal(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }
    public  String getSpecies() {
        return species;
    }
    public static int getHunger() {
        return hunger;
    }

    public static int getThirst() {
        return thirst;
    }

    public static int getBoredom() {
        return boredom;
    }
//    public String getPreviousItem() {
//        return previousItem;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setSpecies(String Species) {
//        species = Species;
//    }
//
//    public void setHunger(int Hunger) {
//        hunger = Hunger;
//
//    }

    public void setThirst(int thirst) {
        this.thirst = thirst;
    }

    public void setBoredom(int boredom) {
        this.boredom = boredom;
    }


    public static int giveFood() {
//       switch(species) {
//            case "tiger":
//                hunger += 15;
//                thirst = Math.max(0, (thirst) - (30));
//                thirst -= 30;
//                boredom += 15;
//                VirtualZoo.setTotalCost((VirtualZoo.getTotalCost()) + (20));
//                break;
//            case "panda":
//                hunger += 15;
//                thirst = Math.max(0, (thirst) - (30));
//                thirst -= 15;
//                boredom += 15;
//                VirtualZoo.setTotalCost((VirtualZoo.getTotalCost()) + (20));
//                break;
//            case "monkey":
//                hunger += 20;
//                thirst = Math.max(0, (thirst) - (30));
//                thirst -= 30;
//                boredom += 15;
//                VirtualZoo.setTotalCost((VirtualZoo.getTotalCost()) + (20));
//                break;
//            case "rhino":
//                hunger += 30;
//                thirst = Math.max(0, (thirst) - (30));
//                thirst -= 30;
//                boredom += 15;
//                VirtualZoo.setTotalCost((VirtualZoo.getTotalCost()) + (20));
//                break;
//        }
        hunger = Math.max(0, (hunger) - (30));
        hunger -= 30;
        thirst += 15;
        boredom += 15;
        VirtualZoo.setTotalCost((VirtualZoo.getTotalCost()) + 30);
////        int cost = 30;
////        int hungerDecrease = Math.min(30, hunger);
////        int thirstIncrease = Math.min(15, hunger - hungerDecrease);
////        int boredomIncrease = Math.min(15, hunger - hungerDecrease - thirstIncrease);
////
////        hunger -= hungerDecrease;
////        thirst += thirstIncrease;
////        boredom += boredomIncrease;
        return (hunger+thirst+boredom);

//        VirtualZoo.setTotalCost(VirtualZoo.getTotalCost() + cost);

    }

    public static int  giveWater() {
//        switch(species) {
//            case "tiger":
//                hunger += 15;
//                thirst = Math.max(0, (thirst) - (30));
//                thirst -= 30;
//                boredom += 15;
//                VirtualZoo.setTotalCost((VirtualZoo.getTotalCost()) + (20));
//                break;
//            case "panda":
//                hunger += 15;
//                thirst = Math.max(0, (thirst) - (30));
//                thirst -= 15;
//                boredom += 15;
//                VirtualZoo.setTotalCost((VirtualZoo.getTotalCost()) + (20));
//                break;
//            case "monkey":
                hunger +=20;
                thirst = Math.max(0, (thirst) - (30));
                thirst -= 30;
                boredom += 15;
                VirtualZoo.setTotalCost((VirtualZoo.getTotalCost()) + (20));
//                break;
//            case "rhino":
//                hunger += 30;
//                thirst = Math.max(0, (thirst) - (30));
//                thirst -= 30;
//                boredom += 15;
//                VirtualZoo.setTotalCost((VirtualZoo.getTotalCost()) + (20));
//                break;
//        }
//




//        int cost = 20;
//        int hungerIncrease = Math.min(15, thirst);
//        int thirstDecrease = Math.min(30, thirst - hungerIncrease);
//        int boredomIncrease = Math.min(15, thirst - hungerIncrease - thirstDecrease);
//
//        hunger += hungerIncrease;
//        thirst -= thirstDecrease;
//        boredom += boredomIncrease;

//        VirtualZoo.setTotalCost(VirtualZoo.getTotalCost() + cost);
        return (hunger+thirst+boredom);

    }

    public static int giveToy() {
//        switch(getSpecies()) {
//            case "tiger":
//                hunger += 15;
//                thirst = Math.max(0, (thirst) - (30));
//                thirst -= 30;
//                boredom += 15;
//                VirtualZoo.setTotalCost((VirtualZoo.getTotalCost()) + (20));
//                break;
//            case "panda":
                hunger += 15;
                thirst = Math.max(0, (thirst) - (30));
                thirst -= 15;
                boredom += 15;
                VirtualZoo.setTotalCost((VirtualZoo.getTotalCost()) + (20));
//                break;
//            case "monkey":
//                hunger += 20;
//                thirst = Math.max(0, (thirst) - (30));
//                thirst -= 30;
//                boredom += 15;
//                VirtualZoo.setTotalCost((VirtualZoo.getTotalCost()) + (20));
//                break;
//            case "rhino":
//                hunger += 30;
//                thirst = Math.max(0, (thirst) - (30));
//                thirst -= 30;
//                boredom += 15;
//                VirtualZoo.setTotalCost((VirtualZoo.getTotalCost()) + (20));
//                break;
//        }
////        hunger += 15;
////        thirst += 15;
////        boredom -=30;
////        boredom = Math.max(0, (boredom) - (30));
////        VirtualZoo.setTotalCost((VirtualZoo.getTotalCost()) + (40));
////        int cost = 40;
////        int hungerIncrease = Math.min(15, boredom);
////        int thirstIncrease = Math.min(15, boredom - hungerIncrease);
////        int boredomDecrease = Math.min(30, boredom - hungerIncrease - thirstIncrease);
//
        return (hunger+boredom+thirst);
//
////        VirtualZoo.setTotalCost(VirtualZoo.getTotalCost() + cost);

    }

    public boolean isDead() {
        if (hunger > 100 || thirst > 100 || boredom > 100) {

            return true;
        }

        return false;
    }

    public int getFoodCost() {
        int cost = 0;
        switch (species) {
            case "Tiger":
                cost = 30;
                break;
            case "Elephant":
                cost = 100;
                break;
            case "Rhino":
                cost = 50;
                break;
            case "Panda":
                cost = 60;
                break;
            case "Monkey":
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

            case "panda", "monkey":
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


    public String toString() {
        return isDead() ? "Name = " + name + "(Dead)\n" : "Name = " + name +
                "\nSpecies = " + species +
                "\nHunger = " + getHunger() +
                "\nThirst = " + getThirst() +
                "\nBoredom = " + getBoredom();

    }

}
