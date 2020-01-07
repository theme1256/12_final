package Model.ChanceCards;

import Controller.BoardController;
import Model.Player;
import gui_main.GUI;

public class AmountSpecial extends Card {
    private int amount;

    public AmountSpecial(int cardNumber, String text, int amount) {
        super(cardNumber, text);
        this.amount = amount * (BoardController.playerCount - 1);
    }

    @Override
    public void action(Player player, GUI gui) {
        gui.displayChanceCard(toString());
        player.updateBalance(this.amount);
    }
}
