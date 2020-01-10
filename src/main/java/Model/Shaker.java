package Model;

public class Shaker {
    private Die die;
    private int numberOfDices;

    public Shaker() {
        this.die = new Die();
        this.numberOfDices = 2;
    }
    public Shaker(int numberOfDices) {
        this.numberOfDices = numberOfDices;
        this.die = new Die();
    }
    Shaker(int numberOfDices, int numberOfSides) {
        this.numberOfDices = numberOfDices;
        this.die = new Die(numberOfSides);
    }

    public int[] shake() {
        int[] rtn = new int[this.numberOfDices];
        for(int i = 0; i < this.numberOfDices; i++) {
            rtn[i] = this.die.roll();
        }
        return rtn;
    }

    public int shake_and_sum() {
        int rtn = 0;
        for(int i = 0; i < this.numberOfDices; i++) {
            rtn += this.die.roll();
        }
        return rtn;
    }
}
