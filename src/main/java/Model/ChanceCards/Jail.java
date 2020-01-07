package Model.ChanceCards;

import Model.Player;
import gui_main.GUI;

public class Jail extends Card {
    public Jail(int cardNumber, String text) {
        super(cardNumber, text);
    }

    @Override
    public void action(Player player, GUI gui) {
        gui.displayChanceCard(toString());
        player.setJailPass(true);
    }
}
