package Controller;

import Model.ChanceDeck;
import Model.Player;
import Model.Shaker;

public class GameController {

    static int startBalance = 0;
    static Shaker shaker;
    static ChanceDeck chanceDeck;
    static int[] val;
    static int value;


    public GameController() {
        boolean playing = true;
        while (playing) {
            for (Player player : PlayerController.players) {
                playing = handleRound(player);
                if (!playing)
                    break;
            }
        }
    }

    static void initVars() {
        shaker = new Shaker(2);
        chanceDeck = new ChanceDeck();
        chanceDeck.blandkort();
        PlayerController.createPlayers();
    }

    private static void getNumberOfPlayers() {
        System.out.println("Indtast ønskede antal spillere");
        PlayerController.numberOfPlayers = BoardControllerGUI.gui.getUserInteger("Indtast ønskede antal spillere", 3, 6);
    }

    static void setStartBalance() {
        getNumberOfPlayers();

        if ((PlayerController.numberOfPlayers >= 3) && (PlayerController.numberOfPlayers <= 6))
            startBalance = 1500;
        else {
            System.out.println("Dette antal spillere er ikke understøttet");
            BoardControllerGUI.gui.showMessage("Dette antal spillere er ikke understøttet");
        }
    }


    static boolean handleRound(Player player) {

        // Slå med terningen når spilleren trykker
        BoardControllerGUI.gui.getUserButtonPressed(player + ", tryk enter/knappen for at slå", "SLÅ");

        // Vis resultatet og opdater felt
        val = GameController.shaker.shake();
        BoardControllerGUI.gui.setDice(val[0], val[1]);
        value = val[0] + val[1];

        player.move(value);

        PlayerController.handleGetInJail(player);

        //Håndterer chancekort
        ChanceCardController.handleChancekort(player);

        DiceController.extraTurn(player);

        //Hvis en spiller lander på et felt over felt 39; så starterde forfra på brættet og chekcer om spilleren skal have 200kr.
        PlayerController.handlePassStart(player);

        return player.account.balance > 0;

    }
}