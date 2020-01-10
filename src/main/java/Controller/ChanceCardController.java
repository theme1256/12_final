package Controller;

import Model.Cards.Kort;
import Model.Player;

public class ChanceCardController {
    //private static ChanceDeck chanceDeck;

    public static void handleChancekort(Player player) {
        if(BoardControllerGUI.felter.fields[player.currentFelt].getName().equals("Prøv lykken")) {
            BoardControllerGUI.gui.getUserButtonPressed(player + ", lander på chancekort felt!", "TRÆK KORT");
            Kort chanceKort = GameController.chanceDeck.traekkort();

            System.out.println(chanceKort);
            chanceKort.action(player, BoardControllerGUI.gui);

        }
    }
}
