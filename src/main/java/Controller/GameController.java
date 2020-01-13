package Controller;

import Model.Fields.Field;
import Model.Fields.Property;
import Model.Player;
import gui_main.GUI;

public class GameController {
    private GUI gui;
    private ChanceCardController chanceCardController;
    private DiceController diceController;
    public static PlayerController playerController;
    private FieldController fieldController;

    public static boolean extraTurn = false;
    public int turnsInARow = 0;

    public GameController(GUI gui, ChanceCardController cc, DiceController dc, PlayerController pc, FieldController fc) {
        this.gui = gui;
        this.chanceCardController = cc;
        this.diceController = dc;
        playerController = pc;
        this.fieldController = fc;

        playerController.createPlayers();


        boolean playing = true;
        while (playing) {
            for (int i = 0; i < playerController.players.length; i++) {
                turnsInARow++;
                Player player = playerController.players[i];
                playing = handleRound(player);

                if (!playing)
                    break;

                if (extraTurn) {
                    i--;
                    extraTurn = false;
                } else {
                    turnsInARow = 0;
                }
            }
        }
    }

    boolean handleRound(Player player){
        return handleRound(player, true);
    }
    boolean handleRound(Player player, boolean move) {
        // Slå med terningen når spilleren trykker
        if (move) {
            this.gui.getUserButtonPressed(player + ", tryk enter/knappen for at slå", "SLÅ");

            // Vis resultatet og opdater felt
            int[] val = diceController.rollDice();
            this.gui.setDice(val[0], val[1]);
            int value = val[0] + val[1];
            player.move(value);
        }

        // Tjek om spilleren landede på "Gå i fængsel"
        if(!playerController.handleGetInJail(player)) {
            // Hvis en spiller lander på et felt over felt 39; så starterde forfra på brættet og chekcer om spilleren skal have 200kr.
            playerController.handlePassStart(player);

            // Lad feltet håndtere at der er landet en person på det
            Field felt = fieldController.getField(player.currentFelt);
            if (felt instanceof Property)
                felt.action(gui, player, fieldController.getFields());
            else
                felt.action(gui, player);

            // Håndterer chancekort
            if (this.chanceCardController.handleChancekort(player)) {
                handleRound(player, false);
            } else {
                //Tjekker hvorvidt en spiller har slået 2 ens
                extraTurn = diceController.giveExtraTurn();
                // Tjek om spilleren har fået 3 ture i streg
                if (turnsInARow == 3 && extraTurn) {
                    gui.getUserButtonPressed("Du har slået 2 ens, 3 gange i streg og bliver smidt i spjældet", "øv..");
                    player.moveTo(10, false);
                    extraTurn = false;
                }
            }
        } else {
            playerController.handeGetOutOfJail(player);
        }

        return player.account.balance > 0;
    }
}