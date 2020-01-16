package Model;

import Controller.MatadorUIController;
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
    private boolean isOut = false;
    public int nextRentModifier = 1;

    public int currentFelt = 0;
    public int previousFelt = 0;


    public Player(GUI gui, int startBalance, PlayerController playerController) {
        this.gui = gui;
        getUserInputPlayerName(playerController);
        account = new Account(startBalance);

        brikselect(gui, playerController);

        GUI_Player playercar = new GUI_Player(playerName, account.balance, brik);
        gui.addPlayer(playercar);
        car = playercar;
        gui.getFields()[this.currentFelt].setCar(this.car, true);
    }

    public Player(int startBalance, String username) {
        account = new Account(startBalance);
        playerName = username;
    }

    /**
     * Giver spilleren mulighed for selv at vælge farve på sin brik, og fjerner muligheden, så en anden ikke kan vælge det samme
     *
     * @param gui Pointer til aktivt GUI
     * @param pc Pointer til PlayerController
     */
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

    /**
     * Beder brugeren om at indtaste et navn og gemmer det
     */
    private void getUserInputPlayerName(PlayerController pc) {
        while (true) {
            playerName = gui.getUserString("Indtast dit navn");
            if (Arrays.stream(pc.getPlayerNames()).anyMatch(playerName::equals)) {
                gui.getUserButtonPressed("Det er der allerede en player der hedder", "Prøv igen");
            } else {
                break;
            }
        }
    }

    /**
     * Henter navnet på spilleren
     *
     * @return Navnet
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Opdaterer spillerens balance, også i GUI, hvis det er defineret
     *
     * @param diff Hvor meget der skal tilføjes eller fjernes
     */
    public void updateBalance(int diff){
        account.updateBalance(diff);
        if (car != null)
            car.setBalance(account.balance);
    }

    /**
     * Henter hvor mange penge spilleren har
     *
     * @return Antal kr. spilleren har
     */
    public int getBalance() {
        return account.balance;
    }

    /**
     * Opdaterer spillerens position i GUI, hvis der er defineret et GUI
     */
    private void updateCar(){
        if (gui != null) {
            gui.getFields()[this.previousFelt].setCar(this.car, false);
            gui.getFields()[this.currentFelt].setCar(this.car, true);
        }
    }

    /**
     * Flytter spilleren det givne antal felter
     *
     * @param amount Antal felter der skal flyttes
     */
    public void move(int amount) {
        this.previousFelt = this.currentFelt;
        this.currentFelt += amount;
        if (this.currentFelt >= 40) {
            this.currentFelt -= 40;
            this.passedStart = true;
        } else if (this.currentFelt < 0) {
            this.currentFelt += 40;
        }
        this.updateCar();
    }

    /**
     * Flytter spilleren til det givne felt
     *
     * @param to Felt der skal flyttes til
     */
    public void moveTo(int to) {
        this.previousFelt = this.currentFelt;
        this.currentFelt = to;

        this.updateCar();
    }

    /**
     * Flytter spilleren til det givne felt og markerer at spilleren passerede start, hvis passStart er true
     *
     * @param to Felt der skal flyttes til
     * @param passStart Om spilleren skal have penge for at passere start, hvis start passeres
     */
    public void moveTo(int to, boolean passStart) {
        this.previousFelt = this.currentFelt;
        this.currentFelt = to;
        if (this.currentFelt < this.previousFelt && passStart)
            this.passedStart = true;
        this.updateCar();
    }

    /**
     * Fortæler om spilleren er i fængsel
     *
     * @return Om spilleren er i fængsel
     */
    public int getTurnsInJail() {
        return this.turnsInJail;
    }

    /**
     * Tæller antal af ture, som spilleren har været i fængsel, en op og markerer at spilleren er i fængsel
     */
    public void addTurnInJail() {
        this.turnsInJail++;
        this.isInJail = true;
    }

    /**
     * Nulstiller hvor længe spilleren har været i fængsel og markerer at spilleren ikke er der mere
     */
    public void resetTurnsInJail() {
        this.turnsInJail = 0;
        this.isInJail = false;
    }

    /**
     * Finder ud af hvor meget spilleren er værd (balance + værdi af skøder + bygninger)
     *
     * @param felter Array af alle felter
     * @return Antal kr. spilleren er værd
     */
    public int getNetWorth(BaseField[] felter) {
        int out = getBalance();
        for (BaseField felt : felter) {
            if (felt instanceof Property) {
                Player owner = ((Property) felt).getOwner();
                if (owner != null) {
                    if (owner.getPlayerName().equals(this.playerName)) {
                        out += felt.getPrice();
                        if(felt instanceof StreetField)
                            out += ((StreetField) felt).getBuildLevel() * ((StreetField) felt).getBuildPrice();
                    }
                }
            }
        }
        return out;
    }

    /**
     * Henter antal huse spilleren har bygget
     *
     * @param felter Array med alle felter på brættet
     * @return Antal huse
     */
    public int getNumberOfHouses(BaseField[] felter) {
        int out = 0;
        for (BaseField felt : felter) {
            if (felt instanceof Property) {
                Player owner = ((Property) felt).getOwner();
                if (owner != null) {
                    if (owner.getPlayerName().equals(this.playerName) && ((StreetField) felt).getBuildLevel() < 5) {
                        out += ((StreetField) felt).getBuildLevel();
                    }
                }
            }
        }
        return out;
    }

    /**
     * Henter antal hoteller spilleren har bygget
     *
     * @param felter Array med alle felter på brættet
     * @return Antal hoteller
     */
    public int getNumberOfHotels(BaseField[] felter) {
        int out = 0;
        for (BaseField felt : felter) {
            if (felt instanceof Property) {
                Player owner = ((Property) felt).getOwner();
                if (owner != null) {
                    if (owner.getPlayerName().equals(this.playerName) && ((StreetField) felt).getBuildLevel() == 5) {
                        out++;
                    }
                }
            }
        }
        return out;
    }

    /**
     * Finder ud af hvilke skøder på grunde, som spilleren ejer
     *
     * @param felter Array med alle felter
     * @return Array med navne på grundene som spilleren ejer
     */
    public String[] getStreets(BaseField[] felter) {
        String[] out = new String[0];
        for (BaseField felt : felter) {
            if (felt instanceof StreetField) {
                Player owner = ((StreetField) felt).getOwner();
                if (owner != null) {
                    if (owner.getPlayerName().equals(this.playerName)) {
                        out = MatadorUIController.addElement(out, felt.getName());
                    }
                }
            }
        }
        return out;
    }

    /**
     * Finder ud af hvilke skøder spilleren ejer
     *
     * @param felter Array med alle felter
     * @return Array med navne på felterne som spilleren ejer
     */
    public String[] getProperties(BaseField[] felter) {
        String[] out = new String[0];
        for (BaseField felt : felter) {
            if (felt instanceof Property) {
                Player owner = ((Property) felt).getOwner();
                if (owner != null) {
                    if (owner.getPlayerName().equals(this.playerName)) {
                        out = MatadorUIController.addElement(out, felt.getName());
                    }
                }
            }
        }
        return out;
    }

    /**
     * Sælger alle spillerens skøder, sletter spillerens balance og fjerner bilen fra brættet
     *
     * @param felter Array med alle felter på brættet
     */
    public void giveUp(BaseField[] felter) {
        // Løber alle felterne på brættet igennem
        for (BaseField felt : felter) {
            // Hvis feltet er en ejendom og der er en der ejer det
            if (felt instanceof Property && ((Property) felt).isOwned()) {
                // Hvis det er den nuværrende player der ejer den, sælg den
                if (((Property) felt).getOwner().getPlayerName().equals(this.playerName))
                    ((Property) felt).sell(gui);
            }
        }
        // Hvis der er et GUI, slet spillerens brik
        if (gui != null) {
            gui.getFields()[this.currentFelt].setCar(this.car, false);
        }
        // Fjern alle pengene på kontoen
        this.updateBalance(-1 * this.getBalance());
        // Marker at spilleren er ude
        this.isOut = true;
    }

    /**
     * Fortæller om spilleren har givet op eller tabt
     *
     * @return Om spilleren stadig er med
     */
    public boolean getIsOut() {
        return this.isOut;
    }

    /**
     * Fortæller om spilleren har et frikokrt til fængslet
     *
     * @return Om spilleren har et frikort
     */
    public boolean getJailPass() {
        return this.jailPass;
    }

    /**
     * Angiver om spilleren skal have et frikort til fængslet
     *
     * @param inp Om spilleren skal have et frikort
     */
    public void setJailPass(boolean inp) {
        this.jailPass = inp;
    }

    /**
     * Fortæller om spilleren er i fængsel
     *
     * @return Om spilleren er i fængsel
     */
    public boolean getIsInJail(){
        return this.isInJail;
    }

    /**
     * Opdaterer om spilleren er i fængsel eller ej
     * @param inp Om spilleren er i fængsel
     */
    public void setIsInJail(boolean inp){
        this.isInJail = inp;
    }

    @Override
    public String toString() {
        return playerName;
    }
}
