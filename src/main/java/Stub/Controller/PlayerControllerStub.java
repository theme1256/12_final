package Stub.Controller;

import Stub.Model.PlayerStub;

import java.util.Scanner;

public class PlayerControllerStub {

    private DiceControllerStub diceController = new DiceControllerStub();

    public PlayerStub[] players;
    public int numberOfPlayers = 0;
    private int startBalance = 0;



    public PlayerControllerStub(DiceControllerStub dc) {

        this.diceController = dc;

    }

    private void getUserInputNumberOfPlayers() {
        System.out.println("Indtast ønskede antal spillere");
        numberOfPlayers = 3;
    }

    private void setStartBalance() {
        getUserInputNumberOfPlayers();

        if ((numberOfPlayers >= 3) && (numberOfPlayers <= 6)) {
            startBalance = 1500;
        } else {
            System.out.println("Dette antal spillere er ikke understøttet");

        }
    }

    public void createPlayers(){
        while (startBalance == 0) {
            setStartBalance();
        }
        players = new PlayerStub[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            players[i] = new PlayerStub(startBalance, this);
        }
    }

    public void handlePassStart(PlayerStub player) {
        if (player.passedStart) {
            System.out.println(player + "passerer start og modtager 200kr");
            player.updateBalance(200);
            player.passedStart = false;
        }
    }

    public boolean handleGetInJail(PlayerStub player) {
        if (player.currentFelt > 25) {
            System.out.println("Du er røget i fængsel");
            player.moveTo(10,false);
            player.resetTurnsInJail();
            player.setIsInJail(true);
            return true;
        }
        return false;
    }

    public boolean handeGetOutOfJail(PlayerStub player) {
        if (player.getTurnsInJail() >= 3) {
            System.out.println("Du løslades ved 3. forsøg");
            player.updateBalance(-50);
            player.resetTurnsInJail();
            return true;
        }

        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println("Brug frikort" + "\n" + "Betal 50kr" + "\n" + "Slå to ens");
            String valg = scan.nextLine();
            if (valg.equals("Brug frikort")) {
                if (player.getJailPass()) {
                    player.setJailPass(false);
                    System.out.println("Du løslades med dit frikort");
                    player.move(diceController.rollAndSumDice());
                    return true;
                } else {
                    System.out.println("Du har ikke noget frikort");
                    continue;
                }
            } else if (valg.equals("Betal 50kr")) {
                player.updateBalance(-50);
                player.move(diceController.rollAndSumDice());
                player.resetTurnsInJail();
                return true;
            } else if (valg.equals("Slå to ens")) {
                int[] val = diceController.rollDice();
                if (val[0] == val[1]) {
                    System.out.println("Tillykke du slog to ens");
                    player.move(val[0] + val[1]);
                    player.resetTurnsInJail();
                    return true;
                } else {
                    System.out.println("Du slog ikke to ens");
                    player.addTurnInJail();
                    return false;
                }
            }
        }
    }
}




