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
    private static   GUI_Car brik;

    public boolean passedStart = false;
    private boolean jailPass = false;
    private int turnsInJail = 0;

    public int currentFelt = 0;
    public int previousFelt = 0;

    private static boolean bilLock = false;
    private static boolean bilLock2 = false;
    private static boolean bilLock3 = false;
    private static boolean bilLock4 = false;
    private static boolean bilLock5 = false;
    private static boolean bilLock6 = false;


    public Player(GUI gui, int startBalance, int i) {
        this.gui = gui;
        username();
        account = new Account(startBalance);

        brikselect();

        GUI_Player playercar = new GUI_Player(playerName, account.balance, brik);
        gui.addPlayer(playercar);
        car = playercar;
        gui.getFields()[this.currentFelt].setCar(this.car, true);
    }

    private static void brikselect(){
        while(true){
            if(!bilLock){
                brik = new GUI_Car(Color.BLACK, Color.WHITE,GUI_Car.Type.CAR,GUI_Car.Pattern.FILL);
                bilLock = true;
                break;
            }if(!bilLock2 && bilLock){
                brik = new GUI_Car(Color.RED, Color.WHITE,GUI_Car.Type.CAR,GUI_Car.Pattern.FILL);
                bilLock2 = true;
                break;
            } if(!bilLock3 && bilLock && bilLock2){
                brik = new GUI_Car(Color.GREEN, Color.WHITE,GUI_Car.Type.CAR,GUI_Car.Pattern.FILL);
                bilLock3 = true;
                break;
            }if(!bilLock4 && bilLock && bilLock2&&bilLock3){
                brik = new GUI_Car(Color.BLUE, Color.WHITE,GUI_Car.Type.CAR,GUI_Car.Pattern.FILL);
                bilLock4 = true;
                break;
            }if(!bilLock5 && bilLock && bilLock2&&bilLock3&&bilLock4){
                brik = new GUI_Car(Color.YELLOW, Color.WHITE,GUI_Car.Type.CAR,GUI_Car.Pattern.FILL);
                bilLock5 = true;
                break;
            }if(!bilLock6 && bilLock && bilLock2&&bilLock3&&bilLock4&&bilLock5){
                brik = new GUI_Car(Color.WHITE, Color.BLACK,GUI_Car.Type.CAR,GUI_Car.Pattern.FILL);
                bilLock6 = true;
                break;
            }
        }

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
    public int getBalance() {
        return account.balance;
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
        if(this.currentFelt >= 40) {
            this.currentFelt -= 40;
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

    public int getTurnsInJail() {
        return this.turnsInJail;
    }
    public void addTurnInJail() {
        this.turnsInJail++;
    }

    public int getNetWorth() {
        return 0;
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
