package Model;

public class Shaker {
    private Die die;
    private int numberOfDices;
    private int[][] overrideSlag;
    private int overrideCounter = 0;
    private boolean override = false;

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

    public void override(int[][] slag) {
        this.overrideSlag = slag;
        this.override = true;
    }

    public int[] shake() {
        int[] rtn = new int[this.numberOfDices];
        if (this.override && this.overrideSlag.length >= this.overrideCounter) {
            rtn = this.overrideSlag[this.overrideCounter];
            this.overrideCounter++;
        } else {
            for (int i = 0; i < this.numberOfDices; i++) {
                rtn[i] = this.die.roll();
            }
        }
        return rtn;
    }

    public int shake_and_sum() {
        int rtn = 0;
        if (this.override && this.overrideSlag.length >= this.overrideCounter) {
            for (int tal : this.overrideSlag[this.overrideCounter]) {
                rtn += tal;
            }
            this.overrideCounter++;
        } else {
            for (int i = 0; i < this.numberOfDices; i++) {
                rtn += this.die.roll();
            }
        }
        return rtn;
    }
}
