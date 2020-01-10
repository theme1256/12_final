package Controller;

import Model.ChanceDeck;
import Model.Player;
import Model.Shaker;
import View.MatadorUI;
import gui_main.GUI;

public class GameController {
    private GUI gui;
    private ChanceCardController chanceCardController;
    private DiceController diceController;
    private PlayerController playerController;


    //static Shaker shaker;
    static ChanceDeck chanceDeck;
    static int[] val;
    static int value;


    public GameController(GUI gui, ChanceCardController cc, DiceController dc, PlayerController pc) {
        this.gui = gui;
        this.chanceCardController = cc;
        this.diceController = dc;
        this.playerController = pc;
        chanceDeck = new ChanceDeck();
        chanceDeck.blandkort();
        playerController.createPlayers();
        boolean playing = true;
        while (playing) {
            for (Player player : playerController.players) {
                playing = handleRound(player);
                if (!playing)
                    break;
            }
        }
    }


    boolean handleRound(Player player) {

        // Slå med terningen når spilleren trykker
        this.gui.getUserButtonPressed(player + ", tryk enter/knappen for at slå", "SLÅ");

        // Vis resultatet og opdater felt
        val = diceController.rollDice();
        this.gui.setDice(val[0], val[1]);
        value = val[0] + val[1];

        player.move(value);

        playerController.handleGetInJail(player);

        //Håndterer chancekort
        this.chanceCardController.handleChancekort(player);

        //Tjekker hvorvidt en spiller har slået 2 ens og hvor mange gange.
        diceController.extraTurn(player, playerController);

        //Hvis en spiller lander på et felt over felt 39; så starterde forfra på brættet og chekcer om spilleren skal have 200kr.
        playerController.handlePassStart(player);

        return player.account.balance > 0;

    }
}