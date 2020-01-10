package Controller;

import Model.Player;


public class PlayerController {

    //private static GUI gui = new GUI();

    public static void handlePassStart(Player player) {
        if (player.passedStart) {
            BoardControllerGUI.gui.getUserButtonPressed(player + " passerer start og modtager 200 kr", "OK");
            player.updateBalance(200);
            player.passedStart = false;

        }
    }

    public static void handleGetInJail(Player player) {
        if(player.currentFelt == 31) {
            BoardControllerGUI.gui.getUserButtonPressed("Du er røget i fængsel!", "ØV");
            player.moveTo(10,false);

        }
    }




}
