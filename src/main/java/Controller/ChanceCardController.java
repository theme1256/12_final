package Controller;

import Model.Cards.ChanceCards;
import Model.ChanceDeck;
import Model.Fields.ChanceCardsField;
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

    public boolean handleChancekort(Player player) {
        if(fieldController.getField(player.currentFelt) instanceof ChanceCardsField) {
            ChanceCards card = chanceDeck.traekkort();

            System.out.println(card);
            return card.action(player, this.gui);
        }
        return false;
    }
}
