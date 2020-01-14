package Controller;

import Model.Fields.BeerField;
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
                if (!player.gaveUp()) {
                    handleRound(player, true);

                    if (playerController.numberOfPlayers <= 1) {
                        playing = false;
                        break;
                    }

                    if (extraTurn && !player.gaveUp()) {
                        gui.getUserButtonPressed(player.playerName + " tillykke du får ekstra tur", "Fedt!");
                        i--;
                        extraTurn = false;
                    } else {
                        turnsInARow = 0;
                    }
                }
            }
        }
        // Skriv hvem der vandt
        for (Player player : playerController.players) {
            if (!player.gaveUp()) {
                gui.showMessage("Tillykke til " + player.getPlayerName() + ", som vandt");
            }
        }
        System.exit(0);
    }

    /**
     * Håndterer det primære flow i en tur, terningkast, land på felter, dobbelt-slag, etc.
     *
     * @param player Pointer til den aktive player
     * @param move Om det er en tur, hvor spilleren skal slå, eller det er fordi spilleren er blevet flyttet
     * @return Om spilleren er ude
     */
    private void handleRound(Player player, boolean move) {
        if (player.getIsInJail()) {
            // Hvis spilleren kommer ud skalder behandles at de er rykket
            if (playerController.handeGetOutOfJail(player)) {
                handleRound(player, false);
            }
        } else {
            // Slå med terningen når spilleren trykker
            if (move) {
                this.gui.getUserButtonPressed(player + ", tryk enter/knappen for at slå", "SLÅ");

                // Slå med terningerne, opdater terningerne i GUI og flyt spilleren
                player.move(diceController.rollAndSumDice());
            }

            // Tjek om spilleren landede på "Gå i fængsel"
            if (!playerController.handleGetInJail(player)) {
                // Hvis en spiller lander på et felt over felt 39; så starterde forfra på brættet og chekcer om spilleren skal have 200kr.
                playerController.handlePassStart(player);

                // Lad feltet håndtere at der er landet en person på det
                Field felt = fieldController.getField(player.currentFelt);
                if (felt instanceof BeerField)
                    ((BeerField) felt).action(gui, player, fieldController.getFields(), diceController);
                else if (felt instanceof Property)
                    felt.action(gui, player, fieldController.getFields());
                else
                    felt.action(gui, player);

                // Håndterer chancekort
                if (this.chanceCardController.handleChancekort(player)) {
                    handleRound(player, false);
                } else if (move) {
                    //Tjekker hvorvidt en spiller har slået 2 ens
                    extraTurn = diceController.gaveExtraTurn();
                    // Tjek om spilleren har fået 3 ture i streg
                    if (turnsInARow == 3 && extraTurn) {
                        gui.getUserButtonPressed("Du har slået 2 ens, 3 gange i streg og bliver smidt i spjældet", "øv..");
                        player.moveTo(10, false);
                        extraTurn = false;
                    }
                }

                // Håndter handel/byg/nedriv
                handleTrade(player);
            }
        }
    }

    private void handleTrade(Player player) {
        while (true) {
            String valg = gui.getUserSelection("Hvad vil du gøre?", "Bygge", "Nedrive", "Sælge", "Give op", "Give turen videre");
            if (valg.equals("Bygge")) {
                String[] properties = player.getProperties(fieldController.getFields());
                valg = gui.getUserSelection("Hvilken grund vil du bygge på?", properties);
            } else if (valg.equals("Nedrive")) {
                //
            } else if (valg.equals("Sælge")) {
                //
            } else if (valg.equals("Give op")) {
                valg = gui.getUserButtonPressed("Er du sikker?", "Ja", "Nej");
                if (valg.equals("Ja")) {
                    player.giveUp(fieldController.getFields());
                    playerController.numberOfPlayers--;
                    break;
                }
            } else if (valg.equals("Give turen videre")) {
                break;
            }
        }
    }
}