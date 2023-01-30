package pk;

import java.util.ArrayList;
import java.util.Random;

public class CardDeck {

    private int sea_battle;
    private int monkey_business;
    private int nop;

    private ArrayList<Cards> cards = new ArrayList<>();

    private final int NUM_CARDS = 35;
    public CardDeck() {
        sea_battle = 6;
        monkey_business = 4;
        nop = 25;

    }

    public ArrayList<Cards> getCards() {
        return cards;
    }
    public int getSize() {
        return cards.size();
    }

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

    public Cards cardPull(Player player) {
        Random bag = new Random();
        player.setCurrentCard(cards.get(bag.nextInt(cards.size())));
        return player.getCurrentCard();

    }

    public void seaBattle(Player player, Dice dice) {
        sea_battle--;
        cards.remove(Cards.SEA_BATTLE);
        System.out.printf("\t\t\t\tcards left: %d\n", sea_battle);
        System.out.printf("\t\t\t\tcards: %s\n", cards.toString());
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
            if (numSabers == counter) {
                System.out.println("\t\t\t\tYou won the sea battle!");
                System.out.println("\t\t\t\tBonus of 500 is added!");
                player.calcScore(dice);
                player.incrementScore(500);

            } else {
                System.out.println("\t\t\t\t\tYou lost the sea battle");
                System.out.println("\t\t\t\t\tYou lost 500!");
                if ((player.getScore() - 500) < 0)
                    player.setScore(0);
                else
                    player.decrementScore(500);


            }

        }


    }

    public void monkeyBusiness(Player player, Dice dice) {
        monkey_business--;
        cards.remove(Cards.MONKEY_BUSINESS);
        System.out.printf("\t\t\t\tcards left: %d\n", monkey_business);
        System.out.printf("\t\t\t\tcards: %s\n", cards.toString());
        if (monkey_business > 0) {

            int rand;
            int index;
            ArrayList<Faces> rolls = dice.getDieRolls();
            Random bag = new Random();
            int counter = 0;
            for (Faces face : dice.getDieRolls()) {
                if (face == Faces.PARROT) {
                    counter++;
                }
            }
            for (int i = counter; i > 0; i--) {
                rolls.remove(Faces.PARROT);
                rolls.add(Faces.MONKEY);
            }

            player.calcScore(dice);

        }

    }

    public void cardReset() {
        cards.clear();
        sea_battle = 6;
        monkey_business = 4;


    }




}
