import pk.Dice;
import pk.Faces;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;

import java.util.ArrayList;
import java.util.Arrays;


public class PiratenKarpen {

    public static Logger logger = LogManager.getLogger(PiratenKarpen.class);


    public static void main(String[] args) {

        int numGames;
        numGames = Integer.parseInt(args[0]);

//        try {

//        }
//        catch (Exception e) {
//            System.out.println("No input of number of games...");
//
//        } finally {
//            System.out.println("Switching to default number of games...");
//            numGames = 3;
//        }

        System.out.println("Welcome to Piraten Karpen Simulator!");

        System.out.println("Number of games: " + numGames);
        System.out.println();
        int numDice = 8;
        Dice myDice1 = new Dice(numDice-1);
        Dice myDice2 = new Dice(numDice-1);
        ArrayList<Faces> player1,player2;



        int skullCounter1 = 0,skullCounter2 = 0;

        int gameCounter = 1;

        float playerOneWins = 0;
        float playerTwoWins = 0;

        boolean gameFinish;
        int numRounds;
        float finalScore1;
        float finalScore2;

        while (numGames >= gameCounter) {

            gameFinish = false;
            numRounds = 1;
            System.out.println("GAME " + gameCounter);
            while (!gameFinish) {

                System.out.println("ROUND " + numRounds);
                System.out.println();
                System.out.println("Player 1 Turn: ");
                System.out.println("Num die P1: " + myDice1.getNumDie());
                System.out.println();
                player1 = myDice1.roll(numDice);
                System.out.println("die roll: " + player1.toString());
                System.out.println("Score P1: " + myDice1.getScore());
                System.out.println();
                myDice1.KeepDice();
                System.out.println("Player 1's dice: " + myDice1.getDieStorage());
                System.out.println();
                System.out.println("Player 2 Turn: ");
                System.out.println("num die P2: " + myDice2.getNumDie());
                System.out.println();
                player2 = myDice2.roll(numDice);
                System.out.println("die roll: " + player2.toString());
                System.out.println("Score P2: " + myDice2.getScore());


                System.out.println();
                myDice2.KeepDice();
                System.out.println("Player 2's dice: " + myDice2.getDieStorage());

                System.out.println();
                if (myDice1.threeSkull() || myDice2.threeSkull()) {
                    finalScore1 = myDice1.score;
                    finalScore2 = myDice2.score;
                    System.out.println("Final Score P1: " + finalScore1 );
                    System.out.println("Final Score P2: " + finalScore2);

                    if (finalScore1  > finalScore2 ) {
                        System.out.println("Player 1 Wins!!");
                        playerOneWins++;
                    }else {
                        System.out.println("Player 2 Wins!!");
                        playerTwoWins++;
                    }
                    gameFinish = true;

                }
                numRounds++;
            }
            System.out.println();
            gameCounter++;
        }

        float winPercentageP1 = (playerOneWins / numGames) * 100;
        float winPercentageP2 = (playerTwoWins / numGames) * 100;

        System.out.printf("Player 1 Win Percentage %.2f \n", winPercentageP1);
        System.out.printf("Player 2 Win Percentage %.2f \n", winPercentageP2);

        logger.trace(winPercentageP1);
        logger.trace(winPercentageP2);
        logger.error(winPercentageP1);
        logger.error(winPercentageP2);

//        System.out.println("I'm rolling a dice");

//        System.out.println("  (DEBUG) there are " + myDice.NUMBER_FACES + " faces");
//        System.out.println("  (DEBUG) " + Arrays.toString(Faces.values()));
//        System.out.println(myDice.roll());
//        System.out.println("I'm rolling 8 die!");
//        System.out.println("  (DEBUG) there are " + myDice.NUMBER_FACES + " faces");
//        System.out.println("  (DEBUG) " + Arrays.toString(Faces.values()));
//        System.out.println(myDice.rollEight());
//        System.out.println("That's all folks!");

    }

}