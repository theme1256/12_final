package Model.ChanceCards;

import Model.Player;
import gui_main.GUI;

public class MoveTo extends Card {
    private int to;
    private boolean start;

    public MoveTo(int cardNumber, String text, int to, boolean start) {
        super(cardNumber, text);
        this.to = to;
        this.start = start;
    }

    @Override
    public void action(Player player, GUI gui) {
        gui.displayChanceCard(toString());
        player.moveTo(this.to, this.start);
    }
}
