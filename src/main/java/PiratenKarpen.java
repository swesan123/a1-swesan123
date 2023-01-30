import org.apache.logging.log4j.Level;
import pk.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


public class PiratenKarpen {


    public static Logger logger = LogManager.getLogger(PiratenKarpen.class);


    public static boolean gameTurn (Player player, CardDeck deck, Dice dice) {
        System.out.printf("\t\t%s Turn:\n", player.getName());
        System.out.printf("\t\tNumber of P1 die: %d\n", player.getNumDie());
        deck.cardPull(player);
        System.out.printf("\t\t\t\tFortune Card: %s\n", player.getCurrentCard());
        dice.roll(player);
        if(dice.threeSkull(player)) {
            player.isWinner(player);
            return true;
        } else {
            if (player.getCurrentCard() == Cards.SEA_BATTLE)
                deck.seaBattle(player, dice);
            else if (player.getCurrentCard() == Cards.MONKEY_BUSINESS)
                deck.monkeyBusiness(player, dice);
            else
                player.calcScore(dice);
            System.out.printf("\t\t\t\t\t%s score: %d\n", player.getName(), player.getScore());
            dice.keepDice(player);
        }
        return true;

    }

    public static void main(String[] args) {
        int numGames;
        String strategy;


        Dice dice = new Dice();
        CardDeck deck = new CardDeck();
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");

        System.out.println("Welcome to Piraten Karpen Simulator!");
        if (args.length > 1) {
            strategy = args[0];
            numGames = Integer.parseInt(args[1]);
            System.out.printf("Command Line Arg specified: [%s, %s]\n", strategy, numGames);
        }
        else if (args.length > 0){
            strategy = args[0];
            numGames = 42;
            System.out.printf("Command Line Arg specified: [%s]\n", strategy);
        } else {
            System.out.println("No command lines specified swtiching to default.....");
            numGames = 42;
        }

        System.out.printf("Number of games: %d\n", numGames);
        System.out.println();

        int gameCounter = 1;
        boolean gameFinish;
        int numRounds;

        while (numGames >= gameCounter) {


            player1.gameReset();
            player2.gameReset();
            gameFinish = false;
            numRounds = 1;
            deck.shuffle();
            System.out.printf("GAME: %d\n", gameCounter);
            while (!gameFinish) {

                //&& gameTurn(player2, deck, dice)
                if (gameTurn(player1, deck, dice) )
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