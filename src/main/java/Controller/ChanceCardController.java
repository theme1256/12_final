package Controller;

import Model.Cards.ChanceCards;
import Model.ChanceDeck;
import Model.Player;
import View.MatadorUI;
import gui_main.GUI;

public class ChanceCardController {
    private FieldController fieldController;
    private GUI gui;
    private ChanceDeck chanceDeck;

    public ChanceCardController(FieldController fc, GUI gui) {
        this.fieldController = fc;
        this.gui = gui;
        chanceDeck = new ChanceDeck();
        chanceDeck.blandkort();
    }

    public void handleChancekort(Player player) {
        if(fieldController.getField(player.currentFelt).getName().equals("Prøv lykken")) {
            this.gui.getUserButtonPressed(player + ", lander på chancekort felt!", "TRÆK KORT");
            ChanceCards card = chanceDeck.traekkort();

            System.out.println(card);
            card.action(player, this.gui);
        }
    }
}
