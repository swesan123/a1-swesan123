/**
 * The card deck that contains fortune cards that have special functionality.
 */
package pk;
import org.apache.logging.log4j.Level;
import java.util.ArrayList;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CardDeck {

    // Initializes logger
    public static Logger logger = LogManager.getLogger(CardDeck.class);
    private int sea_battle;
    private int monkey_business;
    private int nop; // Cards that haven't been implemented yet.

    private ArrayList<Cards> cards = new ArrayList<>(); // A deck of cards.

    private final int NUM_CARDS = 35; // The number of total cards in the deck.

    /**
     * default constructor that contains how many of each fortune card should each deck contain.
     */
    public CardDeck() {
        sea_battle = 6;
        monkey_business = 4;
        nop = 25;

    }

    /**
     * Shuffles the deck so it's randomized.
     */
    public void shuffle() {

        cardReset();
        int rand = 0;
        Random bag = new Random();
        for (Cards card: Cards.values()) {
            if (card == Cards.NOP)
                for (int i = nop; i > 0; i--)
                    cards.add(card);

            else if (card == Cards.SEA_BATTLE) {
                for (int i = sea_battle; i > 0; i--) {
                    rand = bag.nextInt(cards.size());
                    cards.add(rand, card);
                }
            }
            else if (card == Cards.MONKEY_BUSINESS) {
                for (int i = monkey_business; i > 0; i--) {
                    rand = bag.nextInt(cards.size());
                    cards.add(rand, card);
                }
            }


        }


    }

    /**
     * pulls a single card out of deck and gives it to the player.
     * @param player The player pulling the card out of deck.
     * @return The card which was pulled.
     */
    public Cards cardPull(Player player) {
        Random bag = new Random();
        player.setCurrentCard(cards.get(bag.nextInt(cards.size()))); // Picks a card from the deck from index 0-34.
        return player.getCurrentCard();

    }

    /**
     * The sea battle fortune card puts the player in battle where they have to roll
     * and get a randomized number of saber faces. If they get the right number they win a bonus if they lose
     * the amount worth the bonus, and it skips their roll combos.
     * @param player The player in the sea battle.
     * @param dice The die rolled by the player.
     */
    public void seaBattle(Player player, Dice dice) {
        // Removes sea battle card from deck
        sea_battle--;
        cards.remove(Cards.SEA_BATTLE);

        logger.printf(Level.INFO,"\t\t\t\tcards left: %d\n", sea_battle);
        logger.printf(Level.INFO,"\t\t\t\tcards: %s\n", cards.toString());


        if (sea_battle > 0) {
            int bonus = 200;
            int rand;
            Random bag = new Random();
            int numSabers = bag.nextInt(player.getNumDie());
            int counter = 0;
            for (Faces face : dice.getDieRolls()) {
                if (face == Faces.SABER)
                    counter++;

            }

            logger.printf(Level.INFO, "\t\t\t\tSabers Needed: %d\n", numSabers);
            logger.printf(Level.INFO, "\t\t\t\tSabers Rolled: %d\n", counter);
            // if they got the right number of sabers they win a bonus score.
            if (numSabers == counter) {
                logger.printf(Level.INFO,"\t\t\t\tYou won the sea battle!");
                logger.printf(Level.INFO,"\t\t\t\tBonus of %d is added!",bonus);
                player.calcScore(dice);
                player.incrementScore(bonus);
            // If they didn't get right amount.
            } else {
                logger.printf(Level.INFO,"\t\t\t\t\tYou lost the sea battle");
                logger.printf(Level.INFO,"\t\t\t\t\tYou lost bonus!",bonus);
                if ((player.getScore() - bonus) < 0)
                    player.setScore(0);
                else
                    player.decrementScore(bonus);


            }

        }


    }

    /**
     * Monkey business fortune card makes parrots and monkey faces equivalent to allow for greater chance of combos.
     * @param player The player who pulled the monkey card.
     * @param dice The die that was rolled by the player.
     */
    public void monkeyBusiness(Player player, Dice dice) {

        //removes monkey cards from deck.
        monkey_business--;
        cards.remove(Cards.MONKEY_BUSINESS);

        logger.printf(Level.INFO,"\t\t\t\tcards left: %d\n", monkey_business);
        logger.printf(Level.INFO,"\t\t\t\tcards: %s\n", cards.toString());
        if (monkey_business > 0) {
            ArrayList<Faces> rolls = dice.getDieRolls(); // the rolls for easier access

            int counter = 0;
            for (Faces face : dice.getDieRolls()) {
                if (face == Faces.PARROT) {
                    counter++;
                }
            }
            //replaces all parrots with monkey faces.
            for (int i = counter; i > 0; i--) {
                rolls.remove(Faces.PARROT);
                rolls.add(Faces.MONKEY);
            }

            //calculates score with new faces.
            player.calcScore(dice);

        }

    }

    /**
     * Resets the card deck to default values.
     */
    public void cardReset() {
        cards.clear();
        sea_battle = 6;
        monkey_business = 4;


    }




}
