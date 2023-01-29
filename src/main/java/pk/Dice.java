package pk;
import java.util.ArrayList;
import java.util.Random;

public class Dice {


    private ArrayList<Faces> dieRolls = new ArrayList<>();

    public final int NUM_FACES = Faces.values().length;
    public Dice() {
        this.dieRolls.ensureCapacity(2);
    }


    public ArrayList<Faces> getDieRolls() {
        return this.dieRolls;
    }

//    public Faces roll() {
//
//        Random bag = new Random();
//        int rand = bag.nextInt(NUM_FACES);
//        return Faces.values()[rand];
//    }
    public void roll(Player player) {
        String name = player.getName();
        dieRolls.clear();
        int rand = 0;
        Random bag = new Random();
        for (int i = player.getNumDie(); i > 0; i--) {
            rand = bag.nextInt(NUM_FACES);
            dieRolls.add(Faces.values()[rand]);
        }
        System.out.printf("\t\t\t\t%s rolled: %s\n", name, dieRolls.toString());
        player.calcScore(this);
        System.out.printf("\t\t\t\t\t%s score: %d\n", name, player.getScore());


    }
    public void keepDice(Player player) {

        int keepLimit = 2;
        if (player.getNumDie() > 2) {
            Random randDie = new Random();
            int randIndex;
            for (int i = keepLimit; i > 0; i--) {
                randIndex = randDie.nextInt(player.getNumDie());
                player.setDieStorage(dieRolls.get(randIndex));
                dieRolls.remove(randIndex);
                player.setNumDie(dieRolls.size());
            }
        }

    }
    public boolean threeSkull (Player player) {

        boolean isEndRound = false;
        for (Faces face : dieRolls) {
            if (face == Faces.SKULL) {
                player.incrementSkullCounter(1);
            }
        }

        int skulls = player.getSkullCounter();
        System.out.printf("\t\t\t\t\tNumber of skulls %d\n", skulls);

        for (int i = skulls; i > 0; i--) {
            dieRolls.remove(Faces.SKULL);
            if (player.getNumDie() > 1)
                player.decrementNumDie(1);
        }

        if (player.getSkullCounter() >= 3) {
            isEndRound = true;
            player.setSkullCounter(0);


        }

        return isEndRound;
    }

    public void removeDice(ArrayList<Faces> remove) {
        dieRolls.removeAll(remove);
        System.out.println(dieRolls.toString());


    }



//    public ArrayList<Faces> rollEight() {
//        this.dieRolls.clear();
//        for (int i = this.getNumDie; i > 0; i--)
//            this.dieRolls.add(roll());
//        return this.dieRolls;
//
//    }




    
}
