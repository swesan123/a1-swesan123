import pk.Dice;
import pk.Faces;

import java.util.Arrays;

public class PiratenKarpen {


    public static void main(String[] args) {
        System.out.println("Welcome to Piraten Karpen Simulator!");
        System.out.println("I'm rolling a dice");
        Dice myDice = new Dice(7);
        System.out.println("  (DEBUG) there are " + myDice.NUMBER_FACES + " faces");
        System.out.println("  (DEBUG) " + Arrays.toString(Faces.values()));
        System.out.println(myDice.roll());
        System.out.println("I'm rolling 8 die!");
        System.out.println("  (DEBUG) there are " + myDice.NUMBER_FACES + " faces");
        System.out.println("  (DEBUG) " + Arrays.toString(Faces.values()));
        System.out.println(myDice.rollEight());
        System.out.println("That's all folks!");
        System.out.println("test");
        System.out.println("test");

    }
    
}
