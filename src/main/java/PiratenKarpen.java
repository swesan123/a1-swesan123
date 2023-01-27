import pk.Dice;
import pk.Faces;

import java.util.ArrayList;
import java.util.Arrays;

public class PiratenKarpen {


    public static void main(String[] args) {

        System.out.println("Welcome to Piraten Karpen Simulator!");
        int numGames = Integer.parseInt(args[0]);
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


        while (numGames >= gameCounter) {

            boolean gameFinish = false;
            while (!gameFinish) {
                System.out.println("GAME " + gameCounter);

                System.out.println("Player 1 Turn: ");
                System.out.println("Num die P1: " + myDice1.numDie);
                System.out.println();
                player1 = myDice1.rollEight();
                System.out.println("die roll: " + player1.toString());
                System.out.println("Score P1: " + myDice1.getScore());

                for (Faces face : player1) {
                    if (face == Faces.SKULL)
                        skullCounter1++;
                }

                System.out.println();
                System.out.println("skull counter player 1: " + skullCounter1);
                myDice1.KeepDice();
                System.out.println("Player 1's dice: " + myDice1.getDieStorage());
                System.out.println();

                System.out.println("Player 2 Turn: ");
                System.out.println("num die P2: " + myDice2.numDie);
                System.out.println();
                player2 = myDice2.rollEight();
                System.out.println("die roll: " + player2.toString());
                System.out.println("Score P2: " + myDice2.getScore());


                for (Faces face : player2) {
                    if (face == Faces.SKULL)
                        skullCounter2++;
                }

                System.out.println();
                System.out.println("skull counter player 2: " + skullCounter2);
                myDice2.KeepDice();
                System.out.println("Player 2's dice: " + myDice2.getDieStorage());

                System.out.println();
                if (skullCounter1 >= 3 || skullCounter2 >= 3) {
                    float finalScore1 = myDice1.score;
                    float finalScore2 = myDice2.score;
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

            }
            System.out.println();
            gameCounter++;
        }

        float winPercentageP1 = (playerOneWins / numGames) * 100;
        float winPercentageP2 = (playerTwoWins / numGames) * 100;

        System.out.printf("Player 1 Win Percentage %.2f \n", winPercentageP1);
        System.out.printf("Player 2 Win Percentage %.2f \n", winPercentageP2);

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