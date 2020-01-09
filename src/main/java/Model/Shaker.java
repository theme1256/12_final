package Model;

public class Shaker {
    private Die dice;
    private int numberOfDices;

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

    public int[] shake() {
        int[] rtn = new int[this.numberOfDices];
        for(int i = 0; i < this.numberOfDices; i++) {
            rtn[i] = this.dice.roll();
        }
        return rtn;
    }

    public int shake_and_sum() {
        int rtn = 0;
        for(int i = 0; i < this.numberOfDices; i++) {
            rtn += this.dice.roll();
        }
        return rtn;
    }
}
