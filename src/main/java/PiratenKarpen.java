import pk.Dice;
import pk.Faces;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pk.Player;

import java.util.ArrayList;


public class PiratenKarpen {

    public static Logger logger = LogManager.getLogger(PiratenKarpen.class);


    public static void main(String[] args) {

        int numGames;
        numGames = Integer.parseInt(args[0]);

        Dice dice = new Dice();

        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");

        System.out.println("Welcome to Piraten Karpen Simulator!");
        System.out.printf("Number of games: %d\n", numGames);
        System.out.println();

        int gameCounter = 1;
        boolean gameFinish;
        int numRounds;

        while (numGames >= gameCounter) {

            player1.setScore(0);
            player2.setScore(0);
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
                }

                //Player2's turn
                System.out.printf("\t\t%s Turn:\n", player2.getName());
                System.out.printf("\t\tNumber of P1 die: %d\n", player2.getNumDie());
                dice.roll(player2);
                if(dice.threeSkull(player2)) {
                    player2.isWinner(player1);
                    gameFinish = true;
                }


//                player1.keepDice();
//                System.out.println("Player 1's dice: " + player1.getDieStorage());
//                System.out.println();

                // Player2's  turn
//                System.out.println("Player 2 Turn: ");
//                System.out.println("num die P2: " + player2.getNumDie());
//                System.out.println();
//                player2 = player2.roll(numDice);
//                System.out.println("die roll: " + player2.toString());
//                System.out.println("Score P2: " + player2.getScore());
//
//
//                System.out.println();
//                player2.keepDice();
//                System.out.println("Player 2's dice: " + player2.getDieStorage());
//
//                System.out.println();
//                if (player1.threeSkull() || player2.threeSkull()) {
//                    finalScore1 = player1.score;
//                    finalScore2 = player2.score;
//                    System.out.println("Final Score P1: " + finalScore1 );
//                    System.out.println("Final Score P2: " + finalScore2);
//
//                    if (finalScore1  > finalScore2 ) {
//                        System.out.println("Player 1 Wins!!");
//                        playerOneWins++;
//                    }else {
//                        System.out.println("Player 2 Wins!!");
//                        playerTwoWins++;
//                    }
//                    roundFinish = true;
//
//                }
                numRounds++;
            }
            System.out.println();
            gameCounter++;
        }

//        float winPercentageP1 = (playerOneWins / numGames) * 100;
//        float winPercentageP2 = (playerTwoWins / numGames) * 100;
//
//        System.out.printf("Player 1 Win Percentage %.2f \n", winPercentageP1);
//        System.out.printf("Player 2 Win Percentage %.2f \n", winPercentageP2);
//
//        logger.trace(winPercentageP1);
//        logger.trace(winPercentageP2);
//        logger.error(winPercentageP1);
//        logger.error(winPercentageP2);

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