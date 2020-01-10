package Controller;

import Model.Cards.ChanceCards;
import Model.Player;
import View.MatadorUI;

public class ChanceCardController {
    //private static ChanceDeck chanceDeck;

    public static void handleChancekort(Player player) {
        if(MatadorUI.felter.fields[player.currentFelt].getName().equals("Prøv lykken")) {
            MatadorUI.gui.getUserButtonPressed(player + ", lander på chancekort felt!", "TRÆK KORT");
            ChanceCards chanceChanceCards = GameController.chanceDeck.traekkort();

            System.out.println(chanceChanceCards);
            chanceChanceCards.action(player, MatadorUI.gui);

        }
    }
}
