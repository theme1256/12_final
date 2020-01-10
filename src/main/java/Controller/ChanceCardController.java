package Controller;

import Model.Cards.ChanceCards;
import Model.Player;
import View.MatadorUI;
import gui_main.GUI;

public class ChanceCardController {
    private FieldController fieldController;
    private GUI gui;
    //private static ChanceDeck chanceDeck;

    public ChanceCardController(FieldController fc, GUI gui) {
        this.fieldController = fc;
        this.gui = gui;
    }

    public void handleChancekort(Player player) {
        if(fieldController.getField(player.currentFelt).getName().equals("Prøv lykken")) {
            this.gui.getUserButtonPressed(player + ", lander på chancekort felt!", "TRÆK KORT");
            ChanceCards chanceChanceCards = GameController.chanceDeck.traekkort();

            System.out.println(chanceChanceCards);
            chanceChanceCards.action(player, this.gui);

        }
    }
}
