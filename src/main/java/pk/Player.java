package pk;

import java.util.ArrayList;

public class Player {

    private int skullCounter;
    private ArrayList<Faces> dieStorage = new ArrayList<>();
    private int score;
    public Player() {
        this.skullCounter = 0;
        this.dieStorage.ensureCapacity(2);
        this.score = 0;

    }

    public int getSkullCounter() {
        return this.skullCounter;
    }

    public ArrayList<Faces> getDieStorage() {
        return this.dieStorage;

    }
    public int getScore() {

        return this.score;
    }
    public void setSkullCounter(int value) {
        this.skullCounter = value;
    }
    public void incrementSkullCounter(int increVal) {
        this.skullCounter+=increVal;
    }

    public boolean threeSkull (Dice dice) {

        boolean isEndRound = false;
        for (Faces face : dice.getDieRolls()) {
            if (face == Faces.SKULL) {
                skullCounter++;
                dice.removeDice(Faces.SKULL);
            }
        }
        if (skullCounter >= 3) {
            isEndRound = true;
            dice.setNumDie(dice.getDieRolls().size());
            skullCounter = 0;

        }

        return isEndRound;
    }
    public void  setDieStorage(Faces face) {
        this.dieStorage.add(face);

    }
    public int setScore(Dice dice) {

        int numSaber = 0;
        int numMonkey = 0;
        int numParrot = 0;

        for (Faces face: dice.getDieRolls()) {
            switch(face) {
                case GOLD, DIAMOND -> this.score += 100;
                case SABER -> ++numSaber;
                case MONKEY -> ++numMonkey;
                case PARROT -> ++numParrot;
            }

        }
        if (numSaber == 3)
            this.score += 100;
        else if (numMonkey == 3)
            this.score += 100;
        else if (numParrot == 3)
            this.score += 100;

        return this.score;
    }


}
