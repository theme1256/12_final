package Model.ChanceCards;

import Model.Player;
import gui_main.GUI;

public class Amount extends Card {
    private int amount;

    public Amount(int cardNumber, String text, int amount) {
        super(cardNumber, text);
        this.amount = amount;
    }

    @Override
    public void action(Player player, GUI gui) {
        gui.displayChanceCard(toString());
        player.updateBalance(this.amount);
    }
}
