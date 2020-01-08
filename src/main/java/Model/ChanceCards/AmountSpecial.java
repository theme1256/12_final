package Model.ChanceCards;

import Controller.BoardController;
import Model.Player;
import gui_main.GUI;

public class AmountSpecial extends Card {
    private int amount;
    private int hus = 0;
    private int hotel = 0;
    private int fortune = 0;

    public AmountSpecial(int cardNumber, String text, int amount) {
        super(cardNumber, text);
        this.amount = amount;
    }
    public AmountSpecial(int cardNumber, String text, int amount, int fortune) {
        super(cardNumber, text);
        this.amount = amount;
        this.fortune = fortune;
    }
    public AmountSpecial(int cardNumber, String text, int[] amount) {
        super(cardNumber, text);
        this.hus = amount[0];
        this.hotel = amount[1];
    }

    @Override
    public void action(Player player, GUI gui) {
        gui.displayChanceCard(toString());
        if (this.fortune > 0) {
            //
        } else if (this.hus > 0) {
            //
        } else {
            player.updateBalance(amount * BoardController.playerCount);
//            for (Player other : BoardController.players) {
//                // other.updateBalance(-1 * amount);
//            }
        }
    }
}
