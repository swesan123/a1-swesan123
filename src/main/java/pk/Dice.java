package pk;
import java.util.ArrayList;
import java.util.Random;

public class Dice {

    ArrayList<Faces> dieRolls = new ArrayList<>();
    ArrayList<Faces> dieStorage = new ArrayList<>();
    public int score = 0;
//    ArrayList<Faces> dieHistory = new ArrayList<Faces>();
    private int getNumDie = 8;
    private int skullCounter = 0;
    public final int NUM_FACES = Faces.values().length;
    public Dice() {
        this.dieRolls.ensureCapacity(1);
    }
    public Dice(int numOfDice) {
        this.dieRolls.ensureCapacity(numOfDice);
    }

    public int getNumDie() {
        return this.getNumDie;
    }
    public int getSkullCounter() {
        return this.skullCounter;
    }

    public void setSkullCounter(int value) {
        this.skullCounter = value;
    }
    public ArrayList<Faces> getDieStorage() {
        return this.dieStorage;

    }
    public Faces roll() {

        Random bag = new Random();
        int rand = bag.nextInt(NUM_FACES);
        return Faces.values()[rand];
    }
    public ArrayList<Faces>  roll(int numOfDie) {
        this.dieRolls.clear();
        for (int i = numOfDie; i > 0; i--)
            this.dieRolls.add(roll());
        return this.dieRolls;
    }
    public void KeepDice() {
        
        if (this.getNumDie > 2) {
            Random randDie = new Random();
            ArrayList<Faces> tempDie = this.dieRolls;
            int randAmount = randDie.nextInt(this.getNumDie);
            for (int i = randAmount; i > 0; i--) {
                int randIndex = randDie.nextInt(this.getNumDie);
                dieStorage.add(tempDie.get(randIndex));
                tempDie.remove(randIndex);
                this.getNumDie--;
            }
        }

    }

    public boolean threeSkull () {

        boolean endgame = false;
        for (Faces face : this.dieRolls) {
            if (face == Faces.SKULL) {
                this.skullCounter++;
            }
        }
        if (skullCounter >= 3) {
            endgame = true;
            this.skullCounter = 0;
        }

        return endgame;
    }

//    public ArrayList<Faces> rollEight() {
//        this.dieRolls.clear();
//        for (int i = this.getNumDie; i > 0; i--)
//            this.dieRolls.add(roll());
//        return this.dieRolls;
//
//    }

    public int getScore() {

        int numSaber = 0;
        int numMonkey = 0;
        int numParrot = 0;

        for (Faces face: this.dieRolls) {
            switch(face) {
                case GOLD, DIAMOND -> this.score += 100;
                case SABER -> ++numSaber;
                case MONKEY -> ++numMonkey;
                case PARROT -> ++numParrot;
            }

        }
        if (numSaber == 3)
            this.score += 100;
        else if (numMonkey == 3)
            this.score += 100;
        else if (numParrot == 3)
            this.score += 100;

        return this.score;
    }


    
}
