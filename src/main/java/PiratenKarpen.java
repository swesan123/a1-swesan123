import pk.Dice;
import pk.Faces;

import java.util.ArrayList;
import java.util.Arrays;

public class PiratenKarpen {


    public static void main(String[] args) {


        int numGames = Integer.parseInt(args[0]);
        int numDice = 8;

        Dice myDice = new Dice(numDice-1);


        while (numGames > 0) {

            ArrayList<Faces> player1 = myDice.rollEight();
            ArrayList<Faces> player2 = myDice.rollEight();

            myDice.rollEight();
            for (Faces face: player1) {

            }

            numGames--;
        }
        System.out.println("Welcome to Piraten Karpen Simulator!");
        System.out.println("I'm rolling a dice");

        System.out.println("  (DEBUG) there are " + myDice.NUMBER_FACES + " faces");
        System.out.println("  (DEBUG) " + Arrays.toString(Faces.values()));
        System.out.println(myDice.roll());
        System.out.println("I'm rolling 8 die!");
        System.out.println("  (DEBUG) there are " + myDice.NUMBER_FACES + " faces");
        System.out.println("  (DEBUG) " + Arrays.toString(Faces.values()));
        System.out.println(myDice.rollEight());
        System.out.println("That's all folks!");

    }
    
}
