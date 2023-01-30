import org.apache.logging.log4j.Level;
import pk.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class PiratenKarpen {


    public static Logger logger = LogManager.getLogger(PiratenKarpen.class);


    public static boolean gameTurn (Player player1, Player player2, CardDeck deck, Dice dice, String strat) {
        System.out.printf("\t\t%s Turn:\n",player1.getName());
        logger.printf(Level.INFO,"\t\tNumber of P1 die: %d\n", player1.getNumDie());
        deck.cardPull(player1);
        System.out.printf("\t\t\t\tFortune Card: %s\n", player1.getCurrentCard());
        dice.roll(player1);
        if(dice.threeSkull(player1)) {
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
            dice.keepDice(player1, strat);
        }
        return false;
    }

    public static void main(String[] args) {
        int numGames;
        String strategy = "";


        Dice dice = new Dice();
        CardDeck deck = new CardDeck();
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");

        System.out.println("Welcome to Piraten Karpen Simulator!");
        if (args.length > 1) {
            strategy = args[0];
            numGames = Integer.parseInt(args[1]);
            logger.printf(Level.INFO,"Command Line Arg specified: [%s, %s]\n", strategy, numGames);
        }
        else if (args.length > 0){
            strategy = args[0];
            numGames = 42;
            logger.printf(Level.INFO,"Command Line Arg specified: [%s]\n", strategy);
        } else {
            logger.printf(Level.INFO,"No command lines specified switching to default.....");

            numGames = 42;

        }

        logger.printf(Level.INFO,"Number of games: %d\n", numGames);
        System.out.println();

        int gameCounter = 1;
        boolean gameFinish;
        int numRounds;
        boolean isPlayer1Done;
        boolean isPlayer2Done;

        while (numGames >= gameCounter) {


            player1.gameReset();
            player2.gameReset();
            gameFinish = false;
            numRounds = 1;
            deck.shuffle();
            System.out.printf("GAME: %d\n", gameCounter);
            while (!gameFinish) {
                System.out.printf("\tROUND: %d\n", numRounds);
                isPlayer1Done = gameTurn(player1, player2, deck, dice, strategy);
                isPlayer2Done = gameTurn(player2, player1, deck, dice, "");

                if (isPlayer1Done || isPlayer2Done)
                    gameFinish = true;

                numRounds++;
            }
            gameCounter++;
        }

        logger.printf(Level.INFO,"%s Wins: %f", player1.getName(), player1.getWins());
        logger.printf(Level.INFO,"%s Wins: %f", player2.getName(), player2.getWins());
        logger.printf(Level.INFO,"%s Win Percentage: %f", player1.getName(), player1.winPercent());
        logger.printf(Level.INFO,"%s Win Percentage: %f", player2.getName(), player2.winPercent());


        System.out.println("That's all folks!");

    }

}