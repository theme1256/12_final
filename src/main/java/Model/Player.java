package Model;

import gui_fields.GUI_Car;
import gui_fields.GUI_Player;
import gui_main.GUI;

import java.awt.*;

public class Player {
    public Account account;
    private String input;
    public String playerName;
    private GUI_Player car;
    private GUI gui;

    public boolean passedStart = false;
    private boolean jailPass = false;
    private boolean freebee = false;

    public int currentFelt = 0;
    public int previousFelt = 0;

    public Player(GUI gui, int startBalance, int i) {
        this.gui = gui;
        username();
        account = new Account(startBalance);

        GUI_Car brik = new GUI_Car(
                Color.BLACK,
                Color.WHITE,
                (
                    i == 0 ? GUI_Car.Type.CAR :
                    i == 1 ? GUI_Car.Type.RACECAR :
                    i == 2 ? GUI_Car.Type.TRACTOR :
                    GUI_Car.Type.UFO
                ),
                GUI_Car.Pattern.FILL);

        GUI_Player playercar = new GUI_Player(playerName, account.balance, brik);
        gui.addPlayer(playercar);
        car = playercar;
        gui.getFields()[this.currentFelt].setCar(this.car, true);
    }

    public Player(int startBalance, String username) {
        account = new Account(startBalance);
        playerName = username;
    }

    private void username() {
        playerName = gui.getUserString("Indtast dit navn");
    }

    public String getPlayerName() {
        return playerName;
    }

    public void updateBalance(int diff){
        account.updateBalance(diff);
        if(car != null)
            car.setBalance(account.balance);
    }

    private void updateCar(){
        if(gui != null) {
            gui.getFields()[this.previousFelt].setCar(this.car, false);
            gui.getFields()[this.currentFelt].setCar(this.car, true);
        }
    }

    public void move(int amount) {
        this.previousFelt = this.currentFelt;
        this.currentFelt += amount;
        if(this.currentFelt >= 24) {
            this.currentFelt -= 24;
            this.passedStart = true;
        }
        this.updateCar();
    }

    public void moveTo(int to) {
        this.previousFelt = this.currentFelt;
        this.currentFelt = to;
        this.updateCar();
    }

    public void moveTo(int to, boolean passStart) {
        this.previousFelt = this.currentFelt;
        this.currentFelt = to;
        if(this.currentFelt < this.previousFelt && passStart)
            this.passedStart = true;
        this.updateCar();
    }

    public boolean getJailPass() {
        return this.jailPass;
    }
    public void setJailPass(boolean inp) {
        this.jailPass = inp;
    }

    @Override
    public String toString() {
        return playerName;
    }
}
