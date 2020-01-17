package Stub.Controller;

import Stub.Model.ShakerStub;

public class DiceControllerStub {


    private ShakerStub shaker;

    private int[] lastShake = new int[2];

    public DiceControllerStub(){
        shaker = new ShakerStub(2);
    }

    /**
     * Ruller terningerne, viser dem i GUI og returnerer summen af slaget
     *
     * @return Summen af de to terninger
     */
    public int rollAndSumDice() {
        lastShake = shaker.shake();
        return lastShake[0] + lastShake[1];
    }

    /**
     * Ruller terningerne, viser dem i GUI og returnerer et array af slaget
     *
     * @return De enkelte værdier af terningerner
     */
    public int[] rollDice() {
        lastShake = shaker.shake();
        return lastShake;
    }

    /**
     * Tjekker om det slag der var sidst, gav en ekstra tur
     *
     * @return om der skal gives en ekstra tur
     */
    public boolean gaveExtraTurn() {
        return (lastShake[0] == lastShake[1]);
    }
}



