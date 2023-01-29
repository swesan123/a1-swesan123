package pk;

import java.util.ArrayList;

public class Player {

    private String name = "";
    private int skullCounter;
    private int wins;
    private int numDie;
    private ArrayList<Faces> dieStorage = new ArrayList<>();
    private int score;
    public Player(String name) {
        this.name = name;
        this.skullCounter = 0;
        this.wins = 0;
        this.numDie = 8;
        this.dieStorage.ensureCapacity(2);
        this.score = 0;

    }
    public Player(String name, int skullCounter, int wins, int numDie,  int dieStorageCapacity, int score) {
        this.name = name;
        this.skullCounter = skullCounter;
        this.wins = wins;
        this.numDie = numDie;
        this.dieStorage.ensureCapacity(dieStorageCapacity);
        this.score = score;

    }

//


    public int getSkullCounter() {
        return this.skullCounter;
    }

    public ArrayList<Faces> getDieStorage() {
        return this.dieStorage;

    }

    public String getName() {
        return name;
    }

    public int getScore() {

        return score;
    }

    public int getWins() {
        return wins;
    }

    public int getNumDie() {
        return numDie;
    }

    public void setSkullCounter(int skullCounter) {
        this.skullCounter = skullCounter;
    }

    public void setNumDie(int numDie) {
        this.numDie = numDie;
    }

    public void setScore(int score) {
        this.score = score;
    }
    public void calcScore(Dice dice) {
        int monkey = 0;
        int saber = 0;
        int parrot = 0;


        for (Faces face: dice.getDieRolls()) {
            switch (face) {
                case DIAMOND,GOLD -> score += 100;
                case MONKEY -> monkey += 1;
                case SABER ->  saber += 1;
                case PARROT -> parrot += 1;


            }
        }
        System.out.printf("\t\t\t\tMonkeys: %d\n", monkey);
        System.out.printf("\t\t\t\tSabers: %d\n", saber);
        System.out.printf("\t\t\t\tParrots: %d\n", parrot);
        dieCombo(monkey);
        dieCombo(saber);
        dieCombo(parrot);


    }


    public void dieCombo (int val) {
        int counter = val;
        switch (counter) {
            case 3:
                score += 100;
                System.out.println("\t\t\t\tThree in a row! ");
                break;
            case 4:
                score += 200;
                System.out.println("\t\t\t\tFour in a row! ");
                break;
            case 5:
                score += 500;
                System.out.println("\t\t\t\tFive in a row! ");
                break;
            case 6:
                score += 1000;
                System.out.println("\t\t\t\tSix in a row! ");
                break;
            case 7:
                score += 2000;
                System.out.println("\t\t\t\tSeven in a row! ");
                break;
            case 8:
                score += 4000;
                System.out.println("\t\t\t\tEight in a row! ");
                break;

        }

    }


    public void incrementSkullCounter(int increVal) {
        this.skullCounter+=increVal;
    }

    public void decrementNumDie(int decreVal) {
        this.numDie -= 1;
    }


    public void  setDieStorage(Faces face) {
        this.dieStorage.add(face);

    }


    public void isWinner(Player player) {

        boolean winner;
        if (this.score > player.score) {
            this.wins++;
            System.out.printf("The winner is %s!", this.name);
        } else {
            player.wins++;
            System.out.printf("The winner is %s!", player.name);
        }

    }
    public 


}
