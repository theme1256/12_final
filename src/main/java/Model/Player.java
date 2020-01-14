package Model;

import Controller.MatadorUI;
import Controller.PlayerController;
import Model.Fields.BaseField;
import Model.Fields.Property;
import Model.Fields.StreetField;
import gui_fields.GUI_Car;
import gui_fields.GUI_Player;
import gui_main.GUI;

import java.awt.*;
import java.util.Arrays;

public class Player {
    public Account account;
    public String playerName;
    private GUI_Player car;
    private GUI gui;
    private static GUI_Car brik;

    public boolean passedStart = false;
    private boolean jailPass = false;
    public int turnsInJail = 0;
    public boolean isInJail = false;
    private boolean hasGivenUp = false;

    public int currentFelt = 0;
    public int previousFelt = 0;

    private String[] carColors = new String[]{"Sort", "Rød", "Grøn", "Blå", "Gul", "Hvid"};



    public Player(GUI gui, int startBalance, PlayerController playerController) {
        this.gui = gui;
        username();
        account = new Account(startBalance);

        brikselect(gui, playerController);

        GUI_Player playercar = new GUI_Player(playerName, account.balance, brik);
        gui.addPlayer(playercar);
        car = playercar;
        gui.getFields()[this.currentFelt].setCar(this.car, true);
    }

    private void brikselect(GUI gui, PlayerController pc){
        while(true){
            String valg = gui.getUserSelection("Hvilken farve bil vil du have?", pc.getCarColors());
            pc.removeCarColor(valg);
            if (valg.equals("Sort")) {
                brik = new GUI_Car(Color.BLACK, Color.WHITE,GUI_Car.Type.CAR,GUI_Car.Pattern.FILL);
                break;
            } else if(valg.equals("Rød")) {
                brik = new GUI_Car(Color.RED, Color.WHITE,GUI_Car.Type.CAR,GUI_Car.Pattern.FILL);
                break;
            } else if (valg.equals("Grøn")) {
                brik = new GUI_Car(Color.GREEN, Color.WHITE,GUI_Car.Type.CAR,GUI_Car.Pattern.FILL);
                break;
            } else if (valg.equals("Blå")) {
                brik = new GUI_Car(Color.BLUE, Color.WHITE,GUI_Car.Type.CAR,GUI_Car.Pattern.FILL);
                break;
            } else if (valg.equals("Gul")) {
                brik = new GUI_Car(Color.YELLOW, Color.WHITE,GUI_Car.Type.CAR,GUI_Car.Pattern.FILL);
                break;
            } else if (valg.equals("Hvid")) {
                brik = new GUI_Car(Color.WHITE, Color.BLACK,GUI_Car.Type.CAR,GUI_Car.Pattern.FILL);
                break;
            } else {
                gui.getUserButtonPressed("Den farve er allerede valgt", "Prøv igen");
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
        this.isInJail = true;
    }
    public void resetTurnsInJail() {
        this.turnsInJail = 0;
        this.isInJail = false;
    }

    public int getNetWorth(BaseField[] felter) {
        int out = getBalance();
        for (BaseField felt : felter) {
            if (felt instanceof Property) {
                Player owner = ((Property) felt).getOwner();
                if (owner != null) {
                    if (owner.getPlayerName().equals(this.playerName)) {
                        out += felt.getPrice();
                    }
                }
            }
        }
        return out;

    }

    public int getHouses(BaseField[] felter) {
        int out = 0;
        for (BaseField felt : felter) {
            if (felt instanceof Property) {
                Player owner = ((Property) felt).getOwner();
                if (owner != null) {
                    if (owner.getPlayerName().equals(this.playerName) && ((StreetField) felt).getBuildLevel() < 5) {
                        out +=((StreetField) felt).getBuildLevel();
                    } else
                        out += 0;
                }
            }
        }
        return out;

    }
    public int getHotels(BaseField[] felter) {
        int out = 0;
        for (BaseField felt : felter) {
            if (felt instanceof Property) {
                Player owner = ((Property) felt).getOwner();
                if (owner != null) {
                    if (owner.getPlayerName().equals(this.playerName) && ((StreetField) felt).getBuildLevel() == 5) {
                        out +=((StreetField) felt).getBuildLevel();
                    } else
                        out += 0;
                }
            }
        }
        return out;
    }

    public String[] getStreets(BaseField[] felter) {
        String[] out = new String[0];
        for (BaseField felt : felter) {
            if (felt instanceof StreetField) {
                Player owner = ((StreetField) felt).getOwner();
                if (owner != null) {
                    if (owner.getPlayerName().equals(this.playerName)) {
                        out = MatadorUI.addElement(out, felt.getName());
                    }
                }
            }
        }
        return out;
    }
    public String[] getProperties(BaseField[] felter) {
        String[] out = new String[0];
        for (BaseField felt : felter) {
            if (felt instanceof Property) {
                Player owner = ((Property) felt).getOwner();
                if (owner != null) {
                    if (owner.getPlayerName().equals(this.playerName)) {
                        out = MatadorUI.addElement(out, felt.getName());
                    }
                }
            }
        }
        return out;
    }

    public void giveUp(BaseField[] felter) {
        for (BaseField felt : felter) {
            if (felt instanceof Property && ((Property) felt).isOwned()) {
                if (((Property) felt).getOwner().getPlayerName().equals(this.playerName))
                    ((Property) felt).sell(gui);
            }
        }
        if (gui != null) {
            gui.getFields()[this.currentFelt].setCar(this.car, false);
        }
        this.updateBalance(-1 * this.getBalance());
        this.hasGivenUp = true;
    }
    public boolean gaveUp() {
        return this.hasGivenUp;
    }

    public boolean getJailPass() {
        return this.jailPass;
    }
    public void setJailPass(boolean inp) {
        this.jailPass = inp;
    }
    public boolean getIsInJail(){
        return this.isInJail;
    }

    public void setIsInJail(boolean inp){
        this.isInJail = inp;
    }
    @Override
    public String toString() {
        return playerName;
    }
}
