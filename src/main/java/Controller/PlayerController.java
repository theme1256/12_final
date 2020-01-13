package Controller;

import Model.Player;
import gui_main.GUI;

public class PlayerController {
    private GUI gui;
    private DiceController diceController;

    public Player[] players;
    public int numberOfPlayers = 0;
    private int startBalance = 0;

    public PlayerController() {
    }

    public PlayerController(GUI gui, DiceController dc) {
        this.gui = gui;
        this.diceController = dc;
    }

    private void getNumberOfPlayers() {
        System.out.println("Indtast ønskede antal spillere");
        numberOfPlayers = this.gui.getUserInteger("Indtast ønskede antal spillere", 3, 6);
    }


    private void setStartBalance() {
        getNumberOfPlayers();

        if ((numberOfPlayers >= 3) && (numberOfPlayers <= 6)) {
            startBalance = 1500;
        } else {
            System.out.println("Dette antal spillere er ikke understøttet");
            this.gui.showMessage("Dette antal spillere er ikke understøttet");
        }
    }

    public void createPlayers(){
        while (startBalance == 0) {
            setStartBalance();
        }
        players = new Player[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            players[i] = new Player(this.gui, startBalance, i);

            this.gui.showMessage("Navn: " + players[i].playerName + ", start-balance: " + players[i].account.balance + " kr.");
        }
    }

    public void handlePassStart(Player player) {
        if (player.passedStart) {
            this.gui.getUserButtonPressed(player + " passerer start og modtager 200 kr", "OK");
            player.updateBalance(200);
            player.passedStart = false;
        }
    }

    public boolean handleGetInJail(Player player) {
        if (player.currentFelt > 25) {
            this.gui.getUserButtonPressed("Du er røget i fængsel!", "ØV");
            player.moveTo(10,false);
            player.setIsInJail(true);
            player.resetTurnsInJail();
            return true;
        }
        return false;
    }

    public void handeGetOutOfJail(Player player, DiceController diceController) {

        if( player.getTurnsInJail() >= 3){
            gui.showMessage("Du løslades ved 3. forsøg");
            player.updateBalance(-50);
            player.resetTurnsInJail();
            player.setIsInJail(false);

        }  String valg = gui.getUserButtonPressed(player.playerName + " hvordan vil du løslades?", "Brug frikort", "Betal 50kr og ryk det slåede", "Prøv at slå to ens");

        if(valg.equals("Brug frikort")){
        if (player.getJailPass() ) {
            player.setJailPass(false);
            gui.showMessage("Du løslades med dit frikort");

            } else gui.showMessage("Du har ikke noget frikort!");
        } else if (!player.getJailPass()) {

            if (valg.equals("Betal 50kr og ryk det slåede")) {
                player.updateBalance(-50);
                int[] val = diceController.rollDice();
                gui.setDice(val[0], val[1]);
                player.move(val[0]+val[1]);
                player.setIsInJail(false);

            } else if (valg.equals("Prøv at slå to ens")) {
                int[] val = diceController.rollDice();
                gui.setDice(val[0], val[1]);
                if(val[0] == val[1]) {
                    gui.showMessage("Tillykke du slog to ens");
                    player.move(val[0]+val[1]);
                    player.setIsInJail(false);
                    player.resetTurnsInJail();

                }else
                    gui.showMessage(player.playerName + " slog ikke to ens");
                    player.addTurnInJail();
                    player.setIsInJail(true);
            }
        }
    }
}


