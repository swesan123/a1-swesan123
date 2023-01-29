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
    public void setDieStorage(Faces face) {
        this.dieStorage.add(face);

    }

    public void calcScore(Dice dice) {
        int dimaond = 0;
        int gold = 0;
        int monkey = 0;
        int saber = 0;
        int parrot = 0;


        for (Faces face: dice.getDieRolls()) {
            switch (face) {
                case DIAMOND -> {
                    dimaond += 1;
                    score += 100;
                }
                case GOLD -> {
                    gold += 1;
                    score += 100;
                }
                case MONKEY -> monkey += 1;
                case SABER ->  saber += 1;
                case PARROT -> parrot += 1;


            }
        }
        System.out.printf("\t\t\t\tDiamonds: %d\n", dimaond);
        System.out.printf("\t\t\t\tGolds: %d\n", gold);
        System.out.printf("\t\t\t\tMonkeys: %d\n", monkey);
        System.out.printf("\t\t\t\tSabers: %d\n", saber);
        System.out.printf("\t\t\t\tParrots: %d\n", parrot);

        dieCombo(dimaond);
        dieCombo(gold);
        dieCombo(monkey);
        dieCombo(saber);
        dieCombo(parrot);


    }


    public void dieCombo (int val) {
        switch (val) {
            case 3 -> {
                score += 100;
                System.out.println("\t\t\t\tThree in a row! ");
            }
            case 4 -> {
                score += 200;
                System.out.println("\t\t\t\tFour in a row! ");
            }
            case 5 -> {
                score += 500;
                System.out.println("\t\t\t\tFive in a row! ");
            }
            case 6 -> {
                score += 1000;
                System.out.println("\t\t\t\tSix in a row! ");
            }
            case 7 -> {
                score += 2000;
                System.out.println("\t\t\t\tSeven in a row! ");
            }
            case 8 -> {
                score += 4000;
                System.out.println("\t\t\t\tEight in a row! ");
            }

        }

    }

    public void incrementSkullCounter(int increVal) {
        this.skullCounter+=increVal;
    }

    public void incrementNumDie(int increVal){
        this.numDie += increVal;
    }
    public void decrementNumDie(int decreVal) {
        this.numDie -= decreVal;
    }


    public void gameReset() {
        this.score = 0;
        this.numDie = 8;
        this.dieStorage.clear();
        this.skullCounter = 0;
    }

    public void isWinner(Player player) {

        boolean winner;
        System.out.printf("\t\t\t\t\t\tscore: %d\n", this.score);
        System.out.printf("\t\t\t\t\t\tscore: %d\n", player.score);
        if (this.score > player.score) {
            this.wins++;
            System.out.printf("\t\t\t\t\t\tThe winner is %s!\n", this.name);
        } else if (this.score == player.score){
            System.out.println("\t\t\t\t\t\tThe players tied!");
        } else {
            player.wins++;
            System.out.printf("\t\t\t\t\t\tThe winner is %s!\n", player.name);
        }

    }
    public double winPercent() {

        return (wins/42) * 100;

    }

    public void strategy1() {
        if (numDie == 2) {
            numDie++;
            dieStorage.remove(0);
        }


    }



}
