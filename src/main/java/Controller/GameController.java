package Controller;

import Model.ChanceDeck;
import Model.Player;
import Model.Shaker;

public class GameController {
    static int numberOfPlayers = 0;
    static int startBalance = 0;
    static Shaker shaker;
    static ChanceDeck chanceDeck;
    static Player[] players;

    public GameController(){
        boolean playing = true;
        while (playing) {
            for (Player player : GameController.players) {
                playing = DiceController.handleRound(player);
                if(!playing)
                    break;
            }
        }
    }

    static void initVars(){
        shaker = new Shaker(2);
        chanceDeck = new ChanceDeck();
        chanceDeck.blandkort();
        while (GameController.startBalance == 0) {
            setStartBalance();
        }

        for (int i = 0; i < GameController.numberOfPlayers; i++) {
            players[i] = new Player(BoardControllerGUI.gui, GameController.startBalance, i);

            BoardControllerGUI.gui.showMessage("Navn: " + players[i].playerName + "\nStart-balance: " + players[i].account.balance);
        }
    }

    private static void getNumberOfPlayers() {
        System.out.println("Indtast ønskede antal spillere");
        numberOfPlayers = BoardControllerGUI.gui.getUserInteger("Indtast ønskede antal spillere", 3, 6);
    }

    static void setStartBalance() {
        getNumberOfPlayers();
        GameController.players = new Player[numberOfPlayers];

        if ((numberOfPlayers >= 3) && (numberOfPlayers <= 6))
            startBalance = 1500;
        else {
            System.out.println("Dette antal spillere er ikke understøttet");
            BoardControllerGUI.gui.showMessage("Dette antal spillere er ikke understøttet");
        }
    }




}
