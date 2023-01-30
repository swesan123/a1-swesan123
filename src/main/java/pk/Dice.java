package pk;
import org.apache.logging.log4j.Level;

import java.util.ArrayList;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Dice {

    public static Logger logger = LogManager.getLogger(Dice.class);
    private ArrayList<Faces> dieRolls = new ArrayList<>();

    private final int NUM_FACES = Faces.values().length;
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
    public void keepDice(Player player, String strategy) {
        int keepLimit = 1;
        if (!strategy.equalsIgnoreCase("random combo")){
            if (player.getNumDie() > 2) {
                for (int i = 0; i < keepLimit; i++) {
                    player.setDieStorage(dieRolls.get(i));
                    dieRolls.remove(i);
                    player.decrementNumDie(1);
                }
            } else if (player.getDieStorage().size() > 0) {
                dieRolls.add(player.getDieStorage().get(0));
                player.getDieStorage().remove(0);
            }
        }
        else {
            if(player.getDieStorage().size() == 0) {
                player.getDieStorage().addAll(dieRolls.subList(0, 6));
                player.setNumDie(2);
            }
            else if (player.getNumDie() < 2) {
                dieRolls.add(player.getDieStorage().get(0));
                player.getDieStorage().remove(0);
                player.incrementNumDie(1);

            }

        }
        logger.printf(Level.INFO,"\t\t\t\t\t%s die storage: %s\n", player.getName(), player.getDieStorage().toString());

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
        logger.printf(Level.INFO,"\t\t\t\tNumber of skulls: %d\n", player.getSkullCounter());

        for (int i = removalCounter; i > 0; i--) {
            dieRolls.remove(Faces.SKULL);
            if (player.getNumDie() > 1)
                player.decrementNumDie(1);
        }



        return false;
    }

    
}
