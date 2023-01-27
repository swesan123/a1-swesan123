import pk.Dice;
import pk.Faces;

import java.util.ArrayList;
import java.util.Arrays;

public class PiratenKarpen {


    public static void main(String[] args) {

        System.out.println("Welcome to Piraten Karpen Simulator!");
        int numGames = Integer.parseInt(args[0]);
        System.out.println("Number of games: " + numGames);
        int numDice = 8;

        Dice myDice1 = new Dice(numDice-1);
        Dice myDice2 = new Dice(numDice-1);
        ArrayList<Faces> player1,player2;


        int skullCounter1 = 0,skullCounter2 = 0;

        while (numGames > 0) {

            System.out.println("Player 1 Turn: ");
            System.out.println("Score P1: " + myDice1.getScore());
            System.out.println("num die P1: " + myDice1.numDie);
            player1 = myDice1.rollEight();
            System.out.println("die roll: " + player1.toString());

            for (Faces face: player1) {
                if (face == Faces.SKULL)
                    skullCounter1++;
            }

            System.out.println("skull counter player 1: " + skullCounter1);

            myDice1.KeepDice();
            System.out.println("Player 1's dice: " + myDice1.getDieStorage());


            System.out.println("Player 2 Turn: ");
            System.out.println("Score P2: " + myDice2.getScore());
            System.out.println("num die P2: " + myDice2.numDie);
            player2 = myDice2.rollEight();
            System.out.println("die roll: " + player2.toString());

            for (Faces face: player2) {
                if (face == Faces.SKULL)
                    skullCounter2++;
            }


            System.out.println("skull counter player 2: " + skullCounter2);

            myDice2.KeepDice();
            System.out.println("Player 2's dice: " + myDice2.getDieStorage());


            if (skullCounter1 >= 3 || skullCounter2 >= 3) {
                System.out.println("Final Score P1: " + myDice2.getScore());
                System.out.println("Final Score P2: " + myDice2.getScore());
                break;
            }
            numGames--;
        }


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