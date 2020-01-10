package Controller;

import Model.Player;
import View.MatadorUI;


public class PlayerController {
    static Player[] players;
    static int numberOfPlayers = 0;

    public static  void createPlayers(){
        while (GameController.startBalance == 0) {
            GameController.setStartBalance();
        }
        players = new Player[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            players[i] = new Player(MatadorUI.gui, GameController.startBalance, i);

            MatadorUI.gui.showMessage("Navn: " + players[i].playerName + "\nStart-balance: " + players[i].account.balance);
        }
    }

    public static void handlePassStart(Player player) {
        if (player.passedStart) {
            MatadorUI.gui.getUserButtonPressed(player + " passerer start og modtager 200 kr", "OK");
            player.updateBalance(200);
            player.passedStart = false;

        }
    }

    public static void handleGetInJail(Player player) {
        if(player.currentFelt == 31) {
            MatadorUI.gui.getUserButtonPressed("Du er røget i fængsel!", "ØV");
            player.extraTurn = false;
            player.moveTo(10,false);

        }
    }




}
