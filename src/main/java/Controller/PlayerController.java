package Controller;

import Model.Player;
import gui_main.GUI;

import java.awt.*;

public class PlayerController {
    private GUI gui;
    private DiceController diceController;

    public Player[] players;
    public int numberOfPlayers = 0;
    private int startBalance = 0;
    private String[] carColors = new String[]{"Sort", "Rød", "Grøn", "Blå", "Gul", "Hvid"};

    public PlayerController(GUI gui, DiceController dc) {
        this.gui = gui;
        this.diceController = dc;
    }
    public PlayerController(GUI gui, DiceController dc, String[] users) {
        this.gui = gui;
        this.diceController = dc;

        Color[] carColorColors = new Color[]{Color.BLACK, Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.WHITE};
        this.startBalance = 1500;
        players = new Player[users.length];
        for (int i = 0; i < users.length; i++) {
            players[i] = new Player(this.gui, this.startBalance, users[i], carColorColors[i]);
        }
    }

    /**
     * Henter listen med mulige farver, som spillerne kan vælge
     *
     * @return Array med farver
     */
    public String[] getCarColors() {
        return this.carColors;
    }

    /**
     * Fjerner en farve fra listen med mulige farver, som spillerne kan vælge mellem
     *
     * @param color Den farve der skal fjernes
     */
    public void removeCarColor(String color) {
        this.carColors = MatadorUIController.removeElement(this.carColors, color);
    }

    /**
     * Beder om input om antal spillere og opretter det antal spillere
     */
    public void createPlayers(){
        // Så længe der ikke er sat en start-balance, så forsøg at få et antal spillere
        while (this.startBalance == 0) {
            // Bed om et antal spillere
            this.numberOfPlayers = this.gui.getUserInteger("Indtast ønskede antal spillere", 3, 6);

            // Hvis det er mellem 3 og 6, sæt start balancen, så loop brydes
            if ((this.numberOfPlayers >= 3) && (this.numberOfPlayers <= 6)) {
                this.startBalance = 1500;
            } else {
                this.gui.showMessage("Dette antal spillere er ikke understøttet");
            }
        }

        // Opret det rigtige antal spillere
        this.players = new Player[this.numberOfPlayers];
        for (int i = 0; i < this.numberOfPlayers; i++) {
            // Opret en spiller, den håndterer selv at spørge om username og valg af farve
            this.players[i] = new Player(this.gui, this.startBalance, this);

            // Skriv resultatet ud
            this.gui.showMessage("Navn: " + this.players[i].playerName + ", start-balance: " + this.players[i].account.balance + " kr.");
        }
    }

    /**
     * Henter et array med navnene på alle spillere
     * Bruges til at sikre at der ikke er nogen der har samme navn
     *
     * @return Array med navnene på de spillere der er oprette ind til nu
     */
    public String[] getPlayerNames() {
        String[] out = new String[0];
        // Tjekker om der er nogen spillere endnu
        if (players.length > 0) {
            // Looper gennem spillerne
            for (Player p : players) {
                // Sikrer sig at spilleren rent faktisk findes og tilføjer navnet til listen
                if (p != null)
                    out = MatadorUIController.addElement(out, p.getPlayerName());
            }
        }
        return out;
    }

    /**
     * Håndterer at give spilleren penge, hvis de passerer start
     *
     * @param player Pointer til den aktive spiller
     */
    public void handlePassStart(Player player) {
        // Tjek om spilleren passerede start
        if (player.passedStart) {
            // Hvis det skete, fortæl spilleren at de modtager 200 kr.
            this.gui.getUserButtonPressed(player + " passerer start og modtager 200 kr", "OK");
            // Giv de 200 kr
            player.updateBalance(200);
            // Nulstil at spilleren ikke længere har passeret start
            player.passedStart = false;
        }
    }

    /**
     * Tjekker om en spiller er landet på et felt, som betyder at de skal i fængslet
     *
     * @param player Pointer til den aktive spiller
     * @return Om spilleren er røjet i fængsel
     */
    public boolean handleGetInJail(Player player) {
        // Tjek om spilleren står på det rigtige felt
        if (player.currentFelt == 30) {
            // Spilleren skal i fængsel, ryk dem
            this.gui.getUserButtonPressed("Du er røget i fængsel!", "ØV");
            player.moveTo(10,false);
            player.resetTurnsInJail();
            player.setIsInJail(true);
            return true;
        }

        // Spilleren står ikke det rigtige sted
        return false;
    }

    /**
     * Håndterer at give spilleren muligheder for hvordan de vil komme ud af fængslet
     *
     * @param player Pointer til den spiller der er aktiv
     * @return 0 = Spilleren kom ikke ud, 1 = Kom ud og skal tage en tur, 2 = Kom ud og skal tage en tur, hvor der rykkes det forrige slag
     */
    public int handeGetOutOfJail(Player player) {
        // Uendeligt loop, så man er tvunget til at vælge noget der virker
        while (true) {
            // Spørg spilleren hvordan de vil forsøge at komme ud
            String valg = gui.getUserButtonPressed(player.playerName + " hvordan vil du løslades?", "Brug frikort", "Betal 50kr og ryk det slåede", "Prøv at slå to ens");
            if (valg.equals("Brug frikort")) {
                // Spilleren vil forsøge at bruge et frokort
                if (player.getJailPass()) {
                    // Spilleren har et frikort, fjern det
                    player.setJailPass(false);
                    player.resetTurnsInJail();
                    gui.showMessage("Du løslades med dit frikort");
                    return 1;
                } else {
                    gui.showMessage("Du har ikke noget frikort!");
                }
            } else if (valg.equals("Betal 50kr og ryk det slåede")) {
                // Spilleren vil betale, træk pengene
                player.updateBalance(-50);
                player.resetTurnsInJail();
                return 1;
            } else if (valg.equals("Prøv at slå to ens")) {
                // Spilleren vil forsøge at slå to ens
                int[] val = diceController.rollDice();
                if (val[0] == val[1]) {
                    // Spilleren slog to ens
                    gui.showMessage("Tillykke du slog to ens");
                    player.resetTurnsInJail();
                    return 2;
                } else {
                    // Spilleren slog ikke to ens
                    gui.showMessage(player.playerName + " slog ikke to ens");
                    player.addTurnInJail();

                    // Tjek om spilleren har været i fængsel i 3 ture nu
                    if (player.getTurnsInJail() >= 3) {
                        // Spilleren har nu været her i 3 ture og skal betale for at komme ud
                        gui.showMessage("Du løslades ved 3. forsøg");
                        player.updateBalance(-50);
                        player.resetTurnsInJail();
                        return 2;
                    }

                    // Spilleren har ikke været her i 3 ture endnu
                    return 0;
                }
            }
        }
    }
}


