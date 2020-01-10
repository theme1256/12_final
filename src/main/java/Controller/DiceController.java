package Controller;
import Model.ChanceDeck;
import Model.Player;
import Model.Shaker;
import View.MatadorUI;
import gui_main.GUI;


public class DiceController {
    private GUI gui;
    private Shaker shaker;

    private int[] lastShake = new int[2];

    public DiceController(GUI gui){
        this.gui = gui;
        shaker = new Shaker(2);
    }

    public int[] rollDice() {
        lastShake = shaker.shake();
        return lastShake;
    }

    public boolean giveExtraTurn() {
        return (lastShake[0] == lastShake[1]);
    }
}
