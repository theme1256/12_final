package Model.ChanceCards;

import Model.Player;
import gui_main.GUI;

public class Move extends Card {
    private int amount;

    public Move(int cardNumber, String text, int amount) {
        super(cardNumber, text);
        this.amount = amount;
    }

    @Override
    public void action(Player player, GUI gui) {
        gui.displayChanceCard(toString());
        player.move(this.amount);
    }
}
