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

        this.playGame();
    }

    /**
     * Håndterer at give spillerne deres ture, og ekstra ture.
     * Holder øje med om en spiller er ude.
     */
    private void playGame() {
        boolean playing = true;
        while (playing) {
            for (int i = 0; i < playerController.players.length; i++) {
                turnsInARow++;
                Player player = playerController.players[i];
                playing = handleRound(player);

                if (!playing)
                    break;

                if (extraTurn) {
                    gui.getUserButtonPressed(player.playerName +" tillykke du får ekstra tur", "Fedt!");
                    i--;
                    extraTurn = false;
                } else {
                    turnsInARow = 0;
                }
            }
        }
    }

    /**
     * En wrapper til den anden handleRound, så man ikke skal give den to parametre
     *
     * @param player Pointer til den aktive player
     * @return Om spilleren er ude
     */
    boolean handleRound(Player player){
        return handleRound(player, true);
    }

    /**
     * Håndterer det primære flow i en tur, terningkast, land på felter, dobbelt-slag, etc.
     *
     * @param player Pointer til den aktive player
     * @param move Om det er en tur, hvor spilleren skal slå, eller det er fordi spilleren er blevet flyttet
     * @return Om spilleren er ude
     */
    boolean handleRound(Player player, boolean move) {

        // Slå med terningen når spilleren trykker
        if (move && !player.getIsInJail()) {

            this.gui.getUserButtonPressed(player + ", tryk enter/knappen for at slå", "SLÅ");

            // Vis resultatet og opdater felt
            int[] val = diceController.rollDice();
            this.gui.setDice(val[0], val[1]);
            int value = val[0] + val[1];
            player.move(value);
        }


        // Tjek om spilleren landede på "Gå i fængsel"
        if(!playerController.handleGetInJail(player) && !player.getIsInJail()) {
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
            } else if (move) {
                //Tjekker hvorvidt en spiller har slået 2 ens
                extraTurn = diceController.giveExtraTurn();
                // Tjek om spilleren har fået 3 ture i streg
                if (turnsInARow == 3 && extraTurn) {
                    gui.getUserButtonPressed("Du har slået 2 ens, 3 gange i streg og bliver smidt i spjældet", "øv..");
                    player.moveTo(10, false);
                    extraTurn = false;
                }
            }
        } else if (player.getIsInJail()){
            playerController.handeGetOutOfJail(player, diceController);
        }

        return player.account.balance > 0;
    }
}