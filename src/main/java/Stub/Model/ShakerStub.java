package Stub.Model;

import Model.Die;

public class ShakerStub {
    private Die die;
    private int numberOfDices;

    public ShakerStub() {
        this.die = new Die();
        this.numberOfDices = 2;
    }
    public ShakerStub(int numberOfDices) {
        this.numberOfDices = numberOfDices;
        this.die = new Die();
    }
    ShakerStub(int numberOfDices, int numberOfSides) {
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
