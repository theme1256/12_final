package Controller;
import Model.Shaker;
import gui_main.GUI;


public class DiceController {
    private GUI gui;
    private Shaker shaker;

    private int[] lastShake = new int[2];

    public DiceController(GUI gui){
        this.gui = gui;
        shaker = new Shaker(2);
    }

    /**
     * Ruller terningerne, viser dem i GUI og returnerer summen af slaget
     *
     * @return Summen af de to terninger
     */
    public int rollAndSumDice() {
        lastShake = shaker.shake();
        this.gui.setDice(lastShake[0], lastShake[1]);
        return lastShake[0] + lastShake[1];
    }

    /**
     * Ruller terningerne, viser dem i GUI og returnerer et array af slaget
     *
     * @return De enkelte værdier af terningerner
     */
    public int[] rollDice() {
        lastShake = shaker.shake();
        this.gui.setDice(lastShake[0], lastShake[1]);
        return lastShake;
    }

    /**
     * Returnerer summen af hvad der sidst blev slået
     * Bruges til når en spiller er kommet ud af fængsel og skal rykke det slåede
     *
     * @return Summen af det slåede
     */
    public int sumLastShake() {
        return lastShake[0] + lastShake[1];
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
