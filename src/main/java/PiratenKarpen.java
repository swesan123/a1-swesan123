import pk.Dice;
import pk.Faces;

import java.util.ArrayList;
import java.util.Arrays;

public class PiratenKarpen {


    public static void main(String[] args) {

        System.out.println("Welcome to Piraten Karpen Simulator!");
        int numGames = Integer.parseInt(args[0]);
        System.out.println(numGames);
        int numDice = 8;

        Dice myDice1 = new Dice(numDice-1);
        Dice myDice2 = new Dice(numDice-1);
        ArrayList<Faces> player1;
        ArrayList<Faces> player2;

        while (numGames > 0) {

            player1 = myDice1.rollEight();
            player2 = myDice2.rollEight();
            System.out.println(player1);
            System.out.println(player2);
            int craneCounter1 = 0;
            int craneCounter2 = 0;
            for (Faces face: player1) {
                if (face == Faces.SKULL)
                    craneCounter1++;
            }
            for (Faces face: player2) {
                if (face == Faces.SKULL)
                    craneCounter2++;
            }

            if (craneCounter1 == 3 || craneCounter2 == 3)
                break;

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