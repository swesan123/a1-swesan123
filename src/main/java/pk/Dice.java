package pk;
import java.util.ArrayList;
import java.util.Random;

public class Dice {


    private ArrayList<Faces> dieRolls = new ArrayList<>();
    private int numDie;;
    public final int NUM_FACES = Faces.values().length;
    public Dice() {
        this.numDie = 8;
        this.dieRolls.ensureCapacity(2);
    }

    public int getNumDie() {
        return this.numDie;
    }

    public ArrayList<Faces> getDieRolls() {
        return this.dieRolls;
    }

    public Faces roll() {

        Random bag = new Random();
        int rand = bag.nextInt(NUM_FACES);
        return Faces.values()[rand];
    }
    public ArrayList<Faces>  roll(int numOfDie) {
        dieRolls.clear();
        for (int i = numOfDie; i > 0; i--)
            dieRolls.add(roll());
        return dieRolls;
    }
    public void keepDice(Player player) {
        
        if (numDie > 2) {
            Random randDie = new Random();
            ArrayList<Faces> tempDie = dieRolls;
            int randAmount = randDie.nextInt(numDie);
            for (int i = randAmount; i > 0; i--) {
                int randIndex = randDie.nextInt(numDie);
                player.setDieStorage(tempDie.get(randIndex));
                tempDie.remove(randIndex);
                numDie--;
            }
        }

    }

    public void removeDice(Faces remove) {
        for(Faces face: dieRolls) {
            if (face == remove)
                dieRolls.remove(remove);
        }

    }



//    public ArrayList<Faces> rollEight() {
//        this.dieRolls.clear();
//        for (int i = this.getNumDie; i > 0; i--)
//            this.dieRolls.add(roll());
//        return this.dieRolls;
//
//    }




    
}
