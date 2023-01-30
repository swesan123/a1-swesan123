/**
 * The Player class stores the player's data and holds the functionality to calculate score.
 */
package pk;

import java.util.ArrayList;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class Player {

    // Initialize logger
    public static Logger logger = LogManager.getLogger(Player.class);
    private String name = ""; // Name of the player
    private int skulls; // The number of skulls a player has accumulated.
    private int wins;  // The number of wins for a player.
    private int numDie; // Stores the number of die the player has.
    private ArrayList<Faces> dieStorage = new ArrayList<>(); // The die the player puts aside.
    private int score; // The score of the player.
    private Cards currentCard; // The fortune card the player has pulled from deck.

    /**
     * Default constructor that sets the player stats to default values.
     * @param name The name the player identifies as.
     */
    public Player(String name) {
        this.name = name;
        this.skulls = 0;
        this.wins = 0;
        this.numDie = 8;
        this.score = 0;

    }
    /**
     * Constructor class to creat a player with your own stats.
     * @param name Name of player.
     * @param skulls # of skulls of player.
     * @param wins # of wins of player.
     * @param numDie # of die a player has
     * @param score # the score of a player.
     */
    public Player(String name, int skulls, int wins, int numDie, int score) {
        this.name = name;
        this.skulls = skulls;
        this.wins = wins;
        this.numDie = numDie;
        this.score = score;

    }
    /**
     * Getter method for skulls.
     * @return number of skulls a player has as a integer.
     */
    public int getSkulls() {
        return this.skulls;
    }
    /**
     * Getter method for dies a player has set aside.
     * @return the ArrayList of the dies the player has set aside.
     */
    public ArrayList<Faces> getDieStorage() {
        return this.dieStorage;

    }
    /**
     * Getter method for the name of the player.
     * @return The name of player as a string.
     */
    public String getName() {
        return name;
    }
    /**
     * Getter method for the score of the player.
     * @return The score of the player.
     */
    public int getScore() {

        return score;
    }
    /**
     * Getter method for the wins of the player.
     * @return the wins of the player as an int.
     */
    public int getWins() {
        return wins;
    }
    /**
     * Getter method for the number of die a player has.
     * @return the number of die a player has as an int.
     */
    public int getNumDie() {
        return numDie;
    }
    /**
     * Getter method to get the current fortune card the player has in his turn.
     * @return The current fortune card as a type Cards.
     */
    public Cards getCurrentCard() {
        return currentCard;
    }
    /**
     * Setter method for setting the number of skulls for a player.
     * @param skulls integer value of skull to change to.
     */
    public void setSkulls(int skulls) {
        this.skulls = skulls;
    }
    /**
     * Setter method to change the number of die a player has.
     * @param numDie The interger value of die to change to.
     */
    public void setNumDie(int numDie) {
        this.numDie = numDie;
    }
    /**
     * Setter method to change the score of the player
     * @param score The score integer to change to.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Setter method to add new dice to player's dies.
     * @param face the die of type Faces to put aside.
     */
    public void setDieStorage(Faces face) {
        this.dieStorage.add(face);

    }

    /**
     * Setter method to change the current card the player holds to a new card.
     * @param currentCard The new card as a type Cards.
     */
    public void setCurrentCard(Cards currentCard) {
        this.currentCard = currentCard;
    }

    /**
     * Increments score by a given value.
     * @param score The value to increment the score by as an int.
     */
    public void incrementScore(int score) {
        this.score += score;
    }

    /**
     * Increments the skull counter by a given value.
     * @param increVal the value to increase the skulls by.
     */
    public void incrementSkullCounter(int increVal) {
        this.skulls +=increVal;
    }

    /**
     * Increments the number of die a player has by a given value.
     * @param increVal The given value to increase the die by.
     */
    public void incrementNumDie(int increVal){
        this.numDie += increVal;
    }
    /**
     * Decrements the score by a given value.
     * @param score The value to decrement the score by as an int.
     */
    public void decrementScore(int score) {
        this.score -= score;
    }

    public void decrementNumDie(int decreVal) {
        this.numDie -= decreVal;
    }
    /**
     * Calculates the score of a given roll by counting the number of each Face and then adding score based on that number.
     * Combos include 3,4,5,6,7, and 8 in a row with each combo increasing in points awarded. Diamond and gold add 100 each on top of the combo score.
     * @param dice dice of type Dice to check the given rolls of die to determine combos.
     */
    public void calcScore(Dice dice) {
        int dimaond = 0;
        int gold = 0;
        int monkey = 0;
        int saber = 0;
        int parrot = 0;


        for (Faces face: dice.getDieRolls()) {
            /*
            Diamond and gold increment and also add 100 score. While monkey, saber and parrot only provides points
            on combos of 3-8.
             */
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
        // logs the number of each face in a given roll.
        logger.printf(Level.INFO,"\t\t\t\tDiamonds: %d\n", dimaond);
        logger.printf(Level.INFO,"\t\t\t\tGolds: %d\n", gold);
        logger.printf(Level.INFO,"\t\t\t\tMonkeys: %d\n", monkey);
        logger.printf(Level.INFO,"\t\t\t\tSabers: %d\n", saber);
        logger.printf(Level.INFO,"\t\t\t\tParrots: %d\n", parrot);

        // Uses die combo function to determine the point allocation.
        dieCombo(dimaond);
        dieCombo(gold);
        dieCombo(monkey);
        dieCombo(saber);
        dieCombo(parrot);


    }

    /**
     * The dieCombo function calculates the score given the number of face within a die roll. The combos range from 3-8 and the scores range from
     * 100-4000. Increasing accordingly with the combo number.
     * @param val The number of a face within a die roll.
     */
    public void dieCombo (int val) {
        switch (val) {
            case 3 -> {
                score += 100;
                logger.printf(Level.INFO,"\t\t\t\tThree in a row! ");
            }
            case 4 -> {
                score += 200;
                logger.printf(Level.INFO,"\t\t\t\tFour in a row! ");
            }
            case 5 -> {
                score += 500;
                logger.printf(Level.INFO,"\t\t\t\tFive in a row! ");
            }
            case 6 -> {
                score += 1000;
                logger.printf(Level.INFO,"\t\t\t\tSix in a row! ");
            }
            case 7 -> {
                score += 2000;
                logger.printf(Level.INFO,"\t\t\t\tSeven in a row! ");
            }
            case 8 -> {
                score += 4000;
                logger.printf(Level.INFO,"\t\t\t\tEight in a row! ");
            }

        }

    }

    /**
     * isWinner function checks if a player has won not against another player based on score.
     * @param player the player the score is being compared against.
     */
    public void isWinner(Player player) {

        boolean winner;
        System.out.printf("\t\t\t\t\t\tscore: %d\n", this.score);
        System.out.printf("\t\t\t\t\t\tscore: %d\n", player.score);
        if (this.score > player.score || this.score >= 6000) {
            this.wins++;
            System.out.printf("\t\t\t\t\t\tThe winner is %s!\n", this.name);
        } else if (this.score == player.score){
            System.out.println("\t\t\t\t\t\tThe players tied!");
        } else {
            player.wins++;
            System.out.printf("\t\t\t\t\t\tThe winner is %s!\n", player.name);
        }

    }

    /**
     * Calculates the win percentage the player has.
     * @return The win percentage based on 42 games as a float.
     */
    public double winPercent() {
        return (wins/42.0) * 100;
    }

    /**
     * gameReset resets all the players stats back to default.
     */
    public void gameReset() {
        this.score = 0;
        this.numDie = 8;
        this.dieStorage.clear();
        this.skulls = 0;
        this.currentCard = null;
    }



}
