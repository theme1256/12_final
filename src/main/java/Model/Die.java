package Model;

import java.util.Random;

public class Die {
    private int numberOfSides = 6;
    private int faceValue = 0;

    public Die() {}
    public Die(int s) {
        if(s > 1)
            this.numberOfSides = s;
    }

    public int roll() {
        Random rand = new Random();
        this.faceValue = 1 + rand.nextInt((this.numberOfSides));
        return this.faceValue;
    }

    public int getFaceValue() {
        return this.faceValue;
    }

    public String toString() {
        return Integer.toString(this.faceValue);
    }
}