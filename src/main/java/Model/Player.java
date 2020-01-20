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
        this.account = new Account(startBalance);

        GUI_Car brik = brikselect(gui, playerController);

        this.car = new GUI_Player(this.playerName, this.account.balance, brik);
        gui.addPlayer(this.car);
        gui.getFields()[this.currentFelt].setCar(this.car, true);
    }

    public Player(int startBalance, String username) {
        this.account = new Account(startBalance);
        this.playerName = username;
    }

    /**
     * Giver spilleren mulighed for selv at vælge farve på sin brik, og fjerner muligheden, så en anden ikke kan vælge det samme
     *
     * @param gui Pointer til aktivt GUI
     * @param pc Pointer til PlayerController
     */
    private GUI_Car brikselect(GUI gui, PlayerController pc){
        while(true){
            String valg = gui.getUserSelection("Hvilken farve bil vil du have?", pc.getCarColors());
            pc.removeCarColor(valg);
            if (valg.equals("Sort")) {
                return new GUI_Car(Color.BLACK, Color.WHITE,GUI_Car.Type.CAR,GUI_Car.Pattern.FILL);
            } else if(valg.equals("Rød")) {
                return new GUI_Car(Color.RED, Color.WHITE,GUI_Car.Type.CAR,GUI_Car.Pattern.FILL);
            } else if (valg.equals("Grøn")) {
                return new GUI_Car(Color.GREEN, Color.WHITE,GUI_Car.Type.CAR,GUI_Car.Pattern.FILL);
            } else if (valg.equals("Blå")) {
                return new GUI_Car(Color.BLUE, Color.WHITE,GUI_Car.Type.CAR,GUI_Car.Pattern.FILL);
            } else if (valg.equals("Gul")) {
                return new GUI_Car(Color.YELLOW, Color.WHITE,GUI_Car.Type.CAR,GUI_Car.Pattern.FILL);
            } else if (valg.equals("Hvid")) {
                return new GUI_Car(Color.WHITE, Color.BLACK,GUI_Car.Type.CAR,GUI_Car.Pattern.FILL);
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
            this.playerName = this.gui.getUserString("Indtast dit navn");
            if (Arrays.stream(pc.getPlayerNames()).anyMatch(this.playerName::equals)) {
                this.gui.getUserButtonPressed("Det er der allerede en player der hedder", "Prøv igen");
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
        return this.playerName;
    }

    /**
     * Opdaterer spillerens balance, også i GUI, hvis det er defineret
     *
     * @param diff Hvor meget der skal tilføjes eller fjernes
     */
    public void updateBalance(int diff){
        // Opdater account
        this.account.updateBalance(diff);
        // Hvis der er et GUI, opdater GUI
        if (this.car != null)
            this.car.setBalance(this.account.balance);
    }

    /**
     * Henter hvor mange penge spilleren har
     *
     * @return Antal kr. spilleren har
     */
    public int getBalance() {
        return this.account.balance;
    }

    /**
     * Opdaterer spillerens position i GUI, hvis der er defineret et GUI
     */
    private void updateCar(){
        // Hvis der er et GUI
        if (this.gui != null) {
            // Fjern bilen fra det forrige felt
            this.gui.getFields()[this.previousFelt].setCar(this.car, false);
            // Tilføj bilen til det nuværrende felt
            this.gui.getFields()[this.currentFelt].setCar(this.car, true);
        }
    }

    /**
     * Flytter spilleren det givne antal felter
     *
     * @param amount Antal felter der skal flyttes
     */
    public void move(int amount) {
        // Huske hvor spilleren stod før
        this.previousFelt = this.currentFelt;
        // Flytter spilleren
        this.currentFelt += amount;
        // Hvis spilleren står på et for højt tal, ryk tilbage med 40, da der er 40 felter på brættet
        if (this.currentFelt >= 40) {
            this.currentFelt -= 40;
            this.passedStart = true;
        } else if (this.currentFelt < 0) {
            // Spilleren er blevet rykket bagud og har passeret start, tilføj 40 felter, så spilleren stadig er på brættet
            this.currentFelt += 40;
        }
        // Opdater bilen i GUI
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
        // Opret en variabel der kan returneres, med startværdi af det som spilleren har af balance
        int out = getBalance();
        // For alle felter
        for (BaseField felt : felter) {
            // Hvis det er en ejendom
            if (felt instanceof Property) {
                // Find ejeren af feltet
                Player owner = ((Property) felt).getOwner();
                // Hvis der er en ejer
                if (owner != null) {
                    // Hvis navnet på ejeren er det samme som den nuværrende spiller
                    if (owner.getPlayerName().equals(this.playerName)) {
                        // Tilføj prisen på feltet til retur-værdien
                        out += felt.getPrice();
                        // Hvis feltet er en grund/vej, tilføj værdien af bygningerne
                        if (felt instanceof StreetField) {
                            int level = ((StreetField) felt).getBuildLevel();
                            if (level == 5)
                                out += 9 * ((StreetField) felt).getBuildPrice();
                            else
                                out += level * ((StreetField) felt).getBuildPrice();
                        }
                    }
                }
            }
        }
        // Returner spillerens værdi
        return out;
    }

    /**
     * Henter antal huse spilleren har bygget
     *
     * @param felter Array med alle felter på brættet
     * @return Antal huse
     */
    public int getNumberOfHouses(BaseField[] felter) {
        // Opret en variabel der kan returneres
        int out = 0;
        // For alle felter
        for (BaseField felt : felter) {
            // Hvis feltet er en grund
            if (felt instanceof StreetField) {
                Player owner = ((StreetField) felt).getOwner();
                // Hvis der er en ejer
                if (owner != null) {
                    // Hvis ejeren er den nuværrende spiller og der er bygget til mindre en niveau 5
                    if (owner.getPlayerName().equals(this.playerName) && ((StreetField) felt).getBuildLevel() < 5) {
                        // Tilføj antallet af huse til totalen
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
        // Opret en variabel der kan returneres
        int out = 0;
        // For alle felter
        for (BaseField felt : felter) {
            // Hvis feltet er en grund
            if (felt instanceof StreetField) {
                Player owner = ((StreetField) felt).getOwner();
                // Hvis der er en ejer
                if (owner != null) {
                    // Hvis ejeren er den nuværrende og der er bygget til niveau 5
                    if (owner.getPlayerName().equals(this.playerName) && ((StreetField) felt).getBuildLevel() == 5) {
                        // Tæl antallet af hoteller en op
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
        // Opret et array, som kan returneres
        String[] out = new String[0];
        // For alle felter
        for (BaseField felt : felter) {
            // Hvis det er en grund
            if (felt instanceof StreetField) {
                Player owner = ((StreetField) felt).getOwner();
                // Hvis der er en ejer
                if (owner != null) {
                    // Hvis ejeren er den nuværrende spiller
                    if (owner.getPlayerName().equals(this.playerName)) {
                        // Tilføj navnet på vejen til array med grunde spilleren ejer
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
        // Opret et array, som kan returneres
        String[] out = new String[0];
        // For alle felter
        for (BaseField felt : felter) {
            // Hvis feltet er en ejendom
            if (felt instanceof Property) {
                Player owner = ((Property) felt).getOwner();
                // Hvis der er en ejer
                if (owner != null) {
                    // Hvis ejeren er den nuværrende spiller
                    if (owner.getPlayerName().equals(this.playerName)) {
                        // Tilføj navnet på ejendommen til array med felter spilleren ejer
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
        if (this.gui != null) {
            this.gui.getFields()[this.currentFelt].setCar(this.car, false);
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
        return this.playerName;
    }
}
