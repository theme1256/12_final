package Controller;

import Model.Player;
import gui_main.GUI;


public class PlayerController {
    private GUI gui;

    public Player[] players;
    public int numberOfPlayers = 0;
    private int startBalance = 0;

    public PlayerController() {
    }

    public PlayerController(GUI gui) {
        this.gui = gui;
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

            this.gui.showMessage("Navn: " + players[i].playerName + ", start-balance: " + players[i].account.balance);
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
        if(player.currentFelt == 31) {
            this.gui.getUserButtonPressed("Du er røget i fængsel!", "ØV");
            player.moveTo(10,false);
            return true;
        }
        return false;
    }
}
