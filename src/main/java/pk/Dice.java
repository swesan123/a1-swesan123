/**
 * The dice class can roll any number of die, a player can set aside a certain number of die and implements skull
 * functionality.
 */
package pk;
import org.apache.logging.log4j.Level;
import java.util.ArrayList;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Dice {

    // Initialize logger
    public static Logger logger = LogManager.getLogger(Dice.class);
    private ArrayList<Faces> dieRolls = new ArrayList<>(); // The die that the player rolls.
    private final int NUM_FACES = Faces.values().length; // The number of faces on the die.

    /**
     * Getter method to get the die rolled during a turn by a player.
     * @return the ArrayList<Faces> of the die rolls.
     */
    public ArrayList<Faces> getDieRolls() {
        return this.dieRolls;
    }

    /**
     * Rolls the die and assigned faces to each die.
     * @param player The player who is rolling the die.
     */
    public void roll(Player player) {

        String name = player.getName(); // Gets name of player.
        dieRolls.clear(); // Clears previous die on board to roll.

        // random number to randomize dice roll
        int rand = 0;
        Random bag = new Random();

        // rolls num of die the player has
        for (int i = player.getNumDie(); i > 0; i--) {
            rand = bag.nextInt(NUM_FACES);
            dieRolls.add(Faces.values()[rand]);
        }

        // prints whats rolled.
        System.out.printf("\t\t\t\t%s rolled: %s\n", name, dieRolls.toString());


    }

    /**
     * Sets aside die for the player based ona default strategy or random  combo.
     * The strategy only rolls 2 die keeping 6 in storage until all of them run out.
     * @param player The player who's setting the die aside.
     * @param strat1 The strategy of setting the die aside.
     */
    public void keepDice(Player player, String strat1) {

        int keepLimit = 1; // How much die to keep at each turn by default.

        // There is no strategy just use default othewise apply strategy
        if (strat1.equalsIgnoreCase("combo")){
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
        // Combo driven strategy to maxmize triples
        else if (strat1.equalsIgnoreCase("random")){
            if(player.getDieStorage().size() == 0) {
                player.getDieStorage().addAll(dieRolls.subList(0, 6));
                player.setNumDie(2);
            }
            else if (player.getNumDie() < 3) {
                dieRolls.add(player.getDieStorage().get(0));
                player.getDieStorage().remove(0);
                player.incrementNumDie(1);

            }

        }
        logger.printf(Level.INFO,"\t\t\t\t\t%s die storage: %s\n", player.getName(), player.getDieStorage().toString());

    }

    /**
     * The threeSkull function determines how many skulls the player has each roll if the player
     * has three skulls the player is disqualified thus the game ends.
     * @param player The player whos skulls are being checked and counted.
     */
    public boolean isThreeSkull(Player player) {

        int removalCounter = 0;

        // Counts how many skulls were rolled.
        for (Faces face : dieRolls) {
            if (face == Faces.SKULL) {
                player.incrementSkullCounter(1);
                removalCounter++;
            }
        }

        // Disqualifies player and ends game if there are three skulls.
        if (player.getSkulls() >= 3) {
            player.setSkulls(0);
            return true;
        }
        logger.printf(Level.INFO,"\t\t\t\tNumber of skulls: %d\n", player.getSkulls());

        // Removes die which rolled skull from die pile.
        for (int i = removalCounter; i > 0; i--) {
            dieRolls.remove(Faces.SKULL);
            if (player.getNumDie() > 1)
                player.decrementNumDie(1);
        }


        return false; // if there isn't three skulls return false.
    }

    
}
