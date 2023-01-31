/**
* @author Swesan Pathmanathan
* @since Jan 01, 2023
* @Version v3.0
* Course: SFWRENG 2AA4
* Date last modified: Jan 29, 2023
* Project Name: Pirate Karpen
* Description: The program simulates the game, Pirate Karpen, in a 42 simulation game where two players are against each other.
* To win you must either acquire 6000 points or have more points than the opponent. Points are awarded based on the custom faces of the dice.
* There are six faces: MONKEY, PARROT, SABER, SKULL, DIAMOND and GOLD. 8 dice are rolled and points are awarded based on combinations of 3,4,5,6,7, amd 8.
* Diamond and gold award 100 points default combined with combo points. If a player acquire three skulls they are disqualified.
* The player also has to pull a fortune card each turn. Each of these cards have some effect on the game.
*/

import org.apache.logging.log4j.Level;
import pk.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PiratenKarpen {

    // Initialize logger
    public static Logger logger = LogManager.getLogger(PiratenKarpen.class);

    /**
     * Simulates the play of a single turn in Piraten Karpen.
     * @param player1 The first player.
     * @param player2 The second player.
     * @param deck The deck used in the game.
     * @param dice The set of die that are going to be used.
     * @param strat Customized strategies for each player.
     * @return a boo lean value indicting if the player has completed their turn.
     */
    public static boolean gameTurn (Player player1, Player player2, CardDeck deck, Dice dice, String strat1) {
        System.out.printf("\t\t%s Turn:\n",player1.getName());
        logger.printf(Level.INFO,"\t\tNumber of P1 die: %d\n", player1.getNumDie());
        deck.cardPull(player1);
        System.out.printf("\t\t\t\tFortune Card: %s\n", player1.getCurrentCard());
        dice.roll(player1);
        if(dice.isThreeSkull(player1)) {
            player1.isWinner(player2);
            return true;
        } else {
            if (player1.getCurrentCard() == Cards.SEA_BATTLE)
                deck.seaBattle(player1, dice);
            else if (player1.getCurrentCard() == Cards.MONKEY_BUSINESS)
                deck.monkeyBusiness(player1, dice);
            else
                player1.calcScore(dice);
            logger.printf(Level.INFO,"\t\t\t\t\t%s score: %d\n", player1.getName(), player1.getScore());
            dice.keepDice(player1, strat1);
        }
        return false;
    }

    /**
     * The main function of the game, it runs all the functions necessary to play a simulation of the game.
     * @param args The args contain the strategies, and number of games played.
     */
    public static void main(String[] args) {

        int numGames; // total number of games
        String stratP1 = ""; // strategy variable for player 1.
        String stratP2 = ""; // strategy variable for player 2.

        Dice dice = new Dice(); // Initializes a new pair of die.
        CardDeck deck = new CardDeck(); // Initializes new card deck.
        Player player1 = new Player("Player 1"); // Initializes player 1
        Player player2 = new Player("Player 2"); // Initializes player 2

        System.out.println("Welcome to Piraten Karpen Simulator!");
        /**
         * These conditional statements are in place of a try and catch to mitigate any index errors that would arise from
         * improper command line usage.
         */
        if (args.length == 3) {
            stratP1 = args[0]; // strategy for P1 would be the first argument in the command line.
            stratP2 = args[1]; // strategy for P2 would be the second argument in the command line.
            numGames = Integer.parseInt(args[2]); // Number of games would be the third argument in the command line.
            logger.printf(Level.INFO,"Command Line Arg specified: [%s,%s, %s]\n", stratP1, stratP2, numGames); // Log statement prints the command line arguments entered.

        }
        else if (args.length == 2) {
            stratP1 = args[0];
            stratP2 = args[1];
            numGames = 42; // The default amount of games for simulation.
            logger.printf(Level.INFO,"Command Line Arg specified: [%s, %s]\n", stratP1, stratP2); // Log statement prints the command line arguments entered.
        }
        else if (args.length > 0){
            stratP1 = args[0];
            stratP2 = "random";
            numGames = 42;
            logger.printf(Level.INFO,"Command Line Arg specified: [%s]\n", stratP1); // Log statement prints the command line arguments entered.
        } else {
            stratP1 = "random";
            stratP2 = "random";
            numGames = 42;
            logger.printf(Level.INFO,"No command lines specified switching to default.....");

        }

        logger.printf(Level.INFO,"Number of games: %d\n", numGames);

        int gameCounter = 1; // Keep tracks of how much games occurs
        boolean gameFinish; // boolean value that stores whether a game is finished or not.
        int numRounds;
        boolean isPlayer1Done; // boolean value that stores if a player is done their turn.
        boolean isPlayer2Done;

        // Loops until # of games is met.
        while (numGames >= gameCounter) {

            // Resets the game components to default values.
            player1.gameReset();
            player2.gameReset();

            gameFinish = false;
            numRounds = 1;

            // Shuffles card deck.
            deck.shuffle();

            System.out.printf("GAME: %d\n", gameCounter);

            //Runs turns until a game is finished when a condition is met.
            while (!gameFinish) {
                System.out.printf("\tROUND: %d\n", numRounds);
                isPlayer1Done = gameTurn(player1, player2, deck, dice, stratP1);
                isPlayer2Done = gameTurn(player2, player1, deck, dice, stratP2);

                //if either player has lost or won.
                if (isPlayer1Done || isPlayer2Done)
                    gameFinish = true;

                numRounds++;
            }
            gameCounter++;
        }

        // logs in percentages and win percents of both players.
        logger.printf(Level.INFO,"%s Wins: %d", player1.getName(), player1.getWins());
        logger.printf(Level.INFO,"%s Wins: %d", player2.getName(), player2.getWins());
        logger.printf(Level.INFO,"%s Win Percentage: %f", player1.getName(), player1.winPercent());
        logger.printf(Level.INFO,"%s Win Percentage: %f", player2.getName(), player2.winPercent());
        System.out.println("That's all folks!");

    }

}