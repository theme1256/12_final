package Model.ChanceCards;

import Model.Player;
import gui_main.GUI;

public class MoveSpecial extends Card {
    private int[] to;

    public MoveSpecial(int cardNumber, String text, int[] to) {
        super(cardNumber, text);
        this.to = to;
    }

    @Override
    public void action(Player player, GUI gui) {
        gui.displayChanceCard(toString());
        int to = 0;
        String choice = gui.getUserButtonPressed("Hvilket dampskibsselskab vil du flytte til?", "Øresund A/S", "D. F. D. S.", "Ø. K.", "D/S Bornholm 1866");
        switch (choice) {
            case "Øresund A/S":
                to = 6;
                break;
            case "D. F. D. S.":
                to = 16;
                break;
            case "Ø. K.":
                to = 26;
                break;
            case "D/S Bornholm 1866":
                to = 36;
                break;
        }
        player.moveTo(to);
    }
}
