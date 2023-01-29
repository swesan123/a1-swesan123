import org.apache.logging.log4j.Level;
import pk.Dice;
import pk.Faces;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pk.Player;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


public class PiratenKarpen {


    public static Logger logger = LogManager.getLogger(PiratenKarpen.class);


    public static void main(String[] args) {
        int numGames;
        String strategy;


        Dice dice = new Dice();

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
            System.out.printf("GAME: %d\n", gameCounter);
            while (!gameFinish) {

                // Player1's turn
                System.out.printf("\tROUND: %d\n", numRounds);
                System.out.printf("\t\t%s Turn:\n", player1.getName());
                System.out.printf("\t\tNumber of P1 die: %d\n", player1.getNumDie());
                dice.roll(player1);
                if(dice.threeSkull(player1)) {
                    player1.isWinner(player2);
                    gameFinish = true;
                    continue;
                } else {
                    player1.calcScore(dice);
                    System.out.printf("\t\t\t\t\t%s score: %d\n", player1.getName(), player1.getScore());
                    dice.keepDice(player1);
                }

                //Player2's turn
                System.out.printf("\t\t%s Turn:\n", player2.getName());
                System.out.printf("\t\tNumber of P1 die: %d\n", player2.getNumDie());
                dice.roll(player2);
                if(dice.threeSkull(player2)) {
                    player2.isWinner(player1);
                    gameFinish = true;
                    continue;
                } else {
                    player2.calcScore(dice);
                    System.out.printf("\t\t\t\t\t%s score: %d\n", player2.getName(), player2.getScore());
                    dice.keepDice(player2);
                }

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