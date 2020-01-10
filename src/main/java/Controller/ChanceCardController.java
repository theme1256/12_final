package Controller;

import Model.Cards.ChanceCards;
import Model.ChanceDeck;
import Model.Player;
import View.MatadorUI;

public class ChanceCardController {
    static ChanceDeck chanceDeck;

    ChanceCardController(){
        chanceDeck = new ChanceDeck();
        chanceDeck.blandkort();

    }

    public static void handleChancekort(Player player) {
        if(MatadorUI.felter.fields[player.currentFelt].getName().equals("Prøv lykken")) {
            MatadorUI.gui.getUserButtonPressed(player + ", lander på chancekort felt!", "TRÆK KORT");
            ChanceCards chanceChanceCards = chanceDeck.traekkort();

            System.out.println(chanceChanceCards);
            chanceChanceCards.action(player, MatadorUI.gui);

        }
    }
}
