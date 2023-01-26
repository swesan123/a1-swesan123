package pk;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;

public class Dice {

    ArrayList<Faces> die = new ArrayList<Faces>();
    public final int NUMBER_FACES = Faces.values().length;
    public Dice() {
        this.die.ensureCapacity(1);
    }
    public Dice(int numOfDice) {
        this.die.ensureCapacity(numOfDice);
    }

    public Faces roll() {

        Random bag = new Random();
        int rand = bag.nextInt(NUMBER_FACES);
        Faces rolledFace = Faces.values()[rand];
        return rolledFace;
    }

    public ArrayList<Faces> rollEight() {

        for (int i = 8; i > 0; i--)
            die.add(roll());
        return die;

    }
    
}
