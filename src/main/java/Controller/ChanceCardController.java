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

        // Opret et nyt dæk af chancekort og sørg for at det er blandet
        chanceDeck = new ChanceDeck();
        chanceDeck.blandkort();
    }
    public ChanceCardController(FieldController fc, GUI gui, boolean override) {
        this.fieldController = fc;
        this.gui = gui;

        // Opret et nyt dæk af chancekort og sørg for at det er blandet
        chanceDeck = new ChanceDeck();
        if (!override) {
            chanceDeck.blandkort();
        }
    }
    public ChanceCardController(FieldController fc, GUI gui, boolean override, int[] newOrder) {
        this.fieldController = fc;
        this.gui = gui;

        // Opret et nyt dæk af chancekort og sørg for at det er blandet
        chanceDeck = new ChanceDeck();
        if (!override) {
            chanceDeck.blandkort();
        } else {
            chanceDeck.setOrder(newOrder);
        }
    }

    /**
     * Håndterer at trække et chancekort, hvis der er landet på et chance-felt
     *
     * @param player Pointer til den spiller, hvis tur det er
     * @return Om der skal håndteres at spilleren er blevet flyttet
     */
    public boolean handleChancekort(Player player) {
        // Hvis feltet er af type "ChanceCardField", så må det være fordi der skal trækkes og udføres et chancekort
        if(fieldController.getField(player.currentFelt) instanceof ChanceCardsField) {
            // Træk et chancekort
            ChanceCards card = chanceDeck.traekkort();

            // Bed kortet om at udføre den handling det skal udføre
            return card.action(player, this.gui);
        }
        return false;
    }
}
