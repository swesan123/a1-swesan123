package pk;
import java.util.ArrayList;
import java.util.Random;

public class Dice {

    ArrayList<Faces> die = new ArrayList<Faces>();
    ArrayList<Faces> dieStorage = new ArrayList<Faces>();
    public int score = 0;
//    ArrayList<Faces> dieHistory = new ArrayList<Faces>();
    public int numDie = 8;
    public final int NUM_FACES = Faces.values().length;
    public Dice() {
        this.die.ensureCapacity(1);
    }
    public Dice(int numOfDice) {
        this.die.ensureCapacity(numOfDice);
    }
    public ArrayList<Faces> getDieStorage() {
        return this.dieStorage;

    }
    public Faces roll() {

        Random bag = new Random();
        int rand = bag.nextInt(NUM_FACES);
        return Faces.values()[rand];
    }
    public void KeepDice() {
        
        if (this.numDie > 2) {
            Random randDie = new Random();
            ArrayList<Faces> tempDie = this.die;
            int randAmount = randDie.nextInt(this.numDie);
            for (int i = randAmount; i > 0; i--) {
                int randIndex = randDie.nextInt(this.numDie);
                dieStorage.add(tempDie.get(randIndex));
                tempDie.remove(randIndex);
                this.numDie--;
            }
        }

    }
    public ArrayList<Faces> rollEight() {
        this.die.clear();
        for (int i = this.numDie; i > 0; i--)
            this.die.add(roll());
//        dieHistory.addAll(die);
        return this.die;

    }

    public int getScore() {

        int numSaber = 0;
        int numMonkey = 0;
        int numParrot = 0;

        for (Faces face: this.die) {
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
