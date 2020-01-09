package Model;

public class Shaker {
    private Die dice;
    private int numberOfDices = 2;

    Shaker() {
        this.dice = new Die();
    }
    public Shaker(int numberOfDices) {
        this.numberOfDices = numberOfDices;
        this.dice = new Die();
    }
    Shaker(int numberOfDices, int numberOfSides) {
        this.numberOfDices = numberOfDices;
        this.dice = new Die(numberOfSides);
    }

    int[] shake() {
        int[] rtn = new int[this.numberOfDices];
        for(int i = 0; i < this.numberOfDices; i++) {
            rtn[i] = this.dice.roll();
        }
        return rtn;
    }

    int shake_and_sum() {
        int rtn = 0;
        for(int i = 0; i < this.numberOfDices; i++) {
            rtn += this.dice.roll();
        }
        return rtn;
    }
}
