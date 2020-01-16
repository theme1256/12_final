package Controller;

import Model.Fields.BeerField;
import Model.Fields.BaseField;
import Model.Fields.Property;
import Model.Fields.StreetField;
import Model.Player;
import gui_main.GUI;

public class GameController {
    private GUI gui;
    private ChanceCardController chanceCardController;
    private DiceController diceController;
    public static PlayerController playerController;
    private FieldController fieldController;

    public boolean extraTurn = false;
    public int turnsInARow = 0;


    public GameController(GUI gui, ChanceCardController cc, DiceController dc, PlayerController pc, FieldController fc) {
        // Gemmer de variabler der er givet i parametre, så de kan bruges senere
        this.gui = gui;
        this.chanceCardController = cc;
        this.diceController = dc;
        playerController = pc;
        this.fieldController = fc;

        // Bed playerController om at oprette spillere
        playerController.createPlayers();

        // Start selve spillet
        this.playGame();
    }

    /**
     * Håndterer at give spillerne deres ture, og ekstra ture.
     * Holder øje med om en spiller er ude.
     */
    private void playGame() {
        boolean playing = true;
        while (playing) {
            // Løber spillerne igennem en af gangen
            // Grunden til dette loop er valgt er at, så kan man nemt give ekstra ture, ved at tælle i ned
            for (int i = 0; i < playerController.players.length; i++) {
                // Tæller antallet af ture spilleren har haft op, så det kan ses om personen har haft 3 i streg
                turnsInARow++;
                // Bare en formalitet, så man kan skrive "player" i stedet for "playerController.players[i]"
                Player player = playerController.players[i];
                // Tjekker om spilleren stadig er med
                if (!player.getIsOut()) {
                    // Kalder, så håndtering af en tur bliver gjort
                    handleRound(player, true);

                    // Tjekker om der kun er en spiller tilbage, fordi så har den sidst vundet
                    if (playerController.numberOfPlayers <= 1) {
                        playing = false;
                        break;
                    }

                    // Tjekker om spilleren skal have en ekstra tur og ikke har givet op, eller gået fallit
                    if (extraTurn && !player.getIsOut()) {
                        gui.getUserButtonPressed(player.playerName + " tillykke du får ekstra tur", "Fedt!");
                        i--;
                        extraTurn = false;
                    } else {
                        // Nulstiller hvor mange ture der har været i streg
                        turnsInARow = 0;
                    }
                }
            }
        }
        // Skriv hvem der vandt, det er den eneste der ikke har givet op eller er gået fallit
        for (Player player : playerController.players) {
            if (!player.getIsOut()) {
                gui.getUserButtonPressed("Tillykke til " + player.getPlayerName() + ", som vandt");
            }
        }
        // Luk programmet
        System.exit(0);
    }

    /**
     * Håndterer det primære flow i en tur, terningkast, land på felter, dobbelt-slag, etc.
     *
     * @param player Pointer til den aktive player
     * @param move Om det er en tur, hvor spilleren skal slå, eller det er fordi spilleren er blevet flyttet
     */
    private void handleRound(Player player, boolean move, boolean moveLastRoll, boolean previousWasChance) {
        if (player.getIsInJail()) {
            // Hvis spilleren kommer ud skal der behandles at de er rykket
            int rsp = playerController.handeGetOutOfJail(player);
            if (rsp == 1) { // Spilleren kom ud ved frikort og skal have en almindelig runde
                handleRound(player, true);
            } else if (rsp == 2) { // Spilleren kom ud ved terningslag og skal rykke det der blev slået
                handleRound(player, false, true);
            }
        } else {
            if (move) {
                // Det er en almindelig runde, så spilleren skal bare slå og rykke
                this.gui.getUserButtonPressed(player + ", tryk enter/knappen for at slå", "SLÅ");

                // Slå med terningerne, opdater terningerne i GUI og flyt spilleren
                player.move(diceController.rollAndSumDice());
            } else if (moveLastRoll) {
                // Spilleren er lige kommet ud af fængsel og skal rykke det der sidst er blevet slået
                player.move(diceController.sumLastShake());
            }

            // Tjek om spilleren landede på "Gå i fængsel"
            if (!playerController.handleGetInJail(player)) {
                // Hvis en spiller lander på et felt over felt 39; så starterde forfra på brættet og chekcer om spilleren skal have 200kr.
                playerController.handlePassStart(player);

                // Lad feltet håndtere at der er landet en person på det, forskellige felter kræver forskellige parametre
                BaseField felt = fieldController.getField(player.currentFelt);
                if (felt instanceof BeerField)
                    ((BeerField) felt).action(gui, player, fieldController.getFields(), diceController);
                else if (felt instanceof Property)
                    felt.action(gui, player, fieldController.getFields());
                else
                    felt.action(gui, player);

                // Håndterer chancekort, hvis der returneres true, så skal der håndteres at spilleren er blevet flyttet
                if (this.chanceCardController.handleChancekort(player)) {
                    handleRound(player, false, false, true);
                }

                // Håndter handel/byg/nedriv/salg/etc
                if (!previousWasChance)
                    handleOptions(player);

                //Tjekker hvorvidt en spiller har slået 2 ens
                extraTurn = diceController.gaveExtraTurn();
                // Tjek om spilleren har fået 3 ture i streg
                if (turnsInARow == 3 && extraTurn) {
                    gui.getUserButtonPressed("Du har slået 2 ens, 3 gange i streg og bliver smidt i spjældet", "øv..");
                    player.moveTo(10, false);
                    player.isInJail = true;
                    extraTurn = false;
                }

                // Hvis spilleren ikke har flere penge, smid spilleren ud af spillet
                if (player.getBalance() < 0) {
                    gui.getUserButtonPressed("Du er gået falit og kan ikke spille med mere", "Ok");
                    player.giveUp(fieldController.getFields());
                }
            }
        }
    }
    /**
     * En måde at kalde handleRound, uden at have alle parametre med
     *
     * @param player Pointer til den aktive player
     * @param move Om det er en tur, hvor spilleren skal slå, eller det er fordi spilleren er blevet flyttet
     */
    private void handleRound(Player player, boolean move) {
        handleRound(player, move, false);
    }

    private void handleRound(Player player, boolean move, boolean moveLastRoll) { handleRound(player, move, moveLastRoll, false); }

    /**
     * Håndterer at give Spilleren muligheder i slutningen af en tur og udføre den valgte handling
     *
     * @param player Den player, hvis tur det er, som skal vælge
     */
    private void handleOptions(Player player) {
        // Et uendeligt loop, så det først er når man der "breakes" et spillet går videre
        while (true) {
            // Menuen med muligheder opbygges
            // Den har som standard kun en mulighed: "Give turen videre"
            String[] options = new String[]{(diceController.gaveExtraTurn() && turnsInARow != 3 ? "Tage min ekstra tur" : "Give turen videre")};
            // Liste med grunde, som spilleren ejer, hentes
            String[] streets = player.getStreets(fieldController.getFields());
            // Hvis spilleren ejer nogen grunde, skal "Bygge" og "Nedrive" tilføjes til menuen
            if (streets.length > 0) {
                options = MatadorUIController.addElement(options, "Bygge");
                options = MatadorUIController.addElement(options, "Nedrive");
            }
            // Liste med alle skøder, som spilleren ejer, hentes
            String[] properties = player.getProperties(fieldController.getFields());
            // Hvis spilleren ejer noget, skal "Sælge", "Pantsætte" og "Tilbagebetale lån" tilføjes til menuen
            if (properties.length > 0) {
                options = MatadorUIController.addElement(options, "Sælge");
                //options = MatadorUI.addElement(options, "Pantsætte");
                //options = MatadorUI.addElement(options, "Tilbagebetale lån");
            }
            // Tilføjer punktet "Give op" til menuen
            options = MatadorUIController.addElement(options, "Give op");

            // Spørger Spilleren hvad de vil
            String valg = gui.getUserSelection(player.getPlayerName() + ", hvad vil du gøre?", options);
            if (valg.equals("Bygge")) {
                // Spilleren vil bygge, tjek om de ejer nogen grunde, bare for en sikerheds skyld
                if (streets.length > 0) {
                    // Tilføj punktet "Annuller" til mulighederne
                    streets = MatadorUIController.addElement(streets, "Annuller");

                    // Spørger Spilleren hvad der skal ske
                    valg = gui.getUserSelection("Hvilken grund vil du bygge på?", streets);
                    // Så længe der ikke blev valgt "Annuller", forsøg at udføre bygningen
                    if (!valg.equals("Annuller")) {
                        // Finder feltet
                        BaseField felt = fieldController.getFieldFromName(valg);
                        // Hvis feltet er en grund, forsøg at bygge
                        if (felt instanceof StreetField) {
                            ((StreetField) felt).build(gui, player, fieldController.getFields());
                            player.getNumberOfHouses(fieldController.getFields());
                        }
                    }
                } else {
                    gui.getUserButtonPressed("Du har ikke nogen grunde der kan bygges på", "Ok");
                }
            } else if (valg.equals("Nedrive")) {
                // Spilleren vil nedrive, tjek om de ejer nogen grunde, bare for en sikerheds skyld
                if (streets.length > 0) {
                    // Tilføj punktet "Annuller" til mulighederne
                    streets = MatadorUIController.addElement(streets, "Annuller");

                    // Spørger Spilleren hvad der skal ske
                    valg = gui.getUserSelection("Hvilken grund vil du rive ned på?", streets);
                    // Så længe der ikke blev valgt "Annuller", forsøg at udføre nedrivningen
                    if (!valg.equals("Annuller")) {
                        // Finder feltet
                        BaseField felt = fieldController.getFieldFromName(valg);
                        // Hvis feltet er en grund, forsøg at nedrive
                        if (felt instanceof StreetField) {
                            ((StreetField) felt).destroy(gui, player, fieldController.getFields());
                        }
                    }
                } else {
                    gui.getUserButtonPressed("Du har ikke nogen grunde der kan nedrives", "Ok");
                }
            } else if (valg.equals("Sælge")) {
                // Spilleren vil sælge, tjek om de ejer noget, bare for en sikkerheds skyld
                if (properties.length > 0) {
                    // Tilføj punktet "Annuller" til mulighederne
                    properties = MatadorUIController.addElement(properties, "Annuller");

                    // Spørger Spilleren hvad der skal ske
                    valg = gui.getUserSelection("Hvilket skøde vil du sælge?", properties);
                    // Så længe der ikke blev valgt "Annuller", forsøg at udføre salget
                    if (!valg.equals("Annuller")) {
                        // Finder feltet
                        BaseField felt = fieldController.getFieldFromName(valg);
                        // Hvis feltet nedarver fra "Property", forsøg at sælge
                        if (felt instanceof Property) {
                            ((Property) felt).sell(gui);
                        }
                    }
                } else {
                    gui.getUserButtonPressed("Du har ikke nogen skøder der kan sælges", "Ok");
                }
            } else if (valg.equals("Give op")) {
                // Spilleren vil give op, tjek at de ikke har valgt en forkert mulighed
                valg = gui.getUserButtonPressed("Er du sikker?", "Ja", "Nej");
                // Hvis de er sikre, så lad dem give op
                if (valg.equals("Ja")) {
                    player.giveUp(fieldController.getFields());
                    playerController.numberOfPlayers--;
                    break;
                }
            } else if (valg.equals("Pantsætte")) {
                // Ikke implementeret
            } else if (valg.equals("Tilbagebetale lån")) {
                // Ikke implementeret
            } else {
                // Spilleren har valgt at give turen videre, bryd det uendelige loop
                break;
            }
        }
    }
}