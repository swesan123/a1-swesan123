package pk;
import java.util.ArrayList;
import java.util.Random;

public class Dice {

    ArrayList<Faces> die = new ArrayList<Faces>();
    ArrayList<Faces> dieStorage = new ArrayList<Faces>();
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

        Random randDie = new Random();
        int randAmount = randDie.nextInt(this.numDie);
        for (int i = randAmount; i > 0; i--) {
            int randIndex = randDie.nextInt(this.numDie);
            dieStorage.add(die.get(randIndex));
            this.numDie--;
        }




    }
    public ArrayList<Faces> rollEight() {
        die.clear();
        for (int i = this.numDie; i > 0; i--)
            die.add(roll());
        this.KeepDice();
//        dieHistory.addAll(die);
        return die;

    }


    
}
