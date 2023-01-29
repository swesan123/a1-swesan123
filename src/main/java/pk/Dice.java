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
                player.decrementNumDie(1);
            }
        } else if (player.getDieStorage().size() > 0) {
            player.incrementNumDie(1);
            player.getDieStorage().remove(0);

        }
        System.out.printf("\t\t\t\t\t%s die storage: %s\n", player.getName(), player.getDieStorage().toString());

    }
    public boolean threeSkull (Player player) {

        int removalCounter = 0;
        for (Faces face : dieRolls) {
            if (face == Faces.SKULL) {
                player.incrementSkullCounter(1);
                removalCounter++;
            }
        }
//        int skulls = player.getSkullCounter();
        if (player.getSkullCounter() >= 3) {
            player.setSkullCounter(0);
            return true;
        }
        System.out.printf("\t\t\t\tNumber of skulls: %d\n", player.getSkullCounter());

        for (int i = removalCounter; i > 0; i--) {
            dieRolls.remove(Faces.SKULL);
            if (player.getNumDie() > 1)
                player.decrementNumDie(1);
        }



        return false;
    }

    
}
