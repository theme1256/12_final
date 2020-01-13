package Controller;

import Model.Cards.ChanceCards;
import Model.ChanceDeck;
import Model.Player;
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
            ChanceCards card = chanceDeck.traekkort();

            System.out.println(card);
            card.action(player, this.gui);
        }
    }
}
