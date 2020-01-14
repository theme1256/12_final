package Model.Fields;

import Model.Player;
import gui_fields.GUI_Field;
import gui_fields.GUI_Street;
import gui_main.GUI;

import java.awt.*;

public class StreetField extends Property {
    private int buildLevel = 0;
    private int[] rent;
    private int[] group;
    private int buildPrice;

    public StreetField(String name,String description, int nr, int price, int[] rent, int[] group, Color color) {
        super(name, description, nr, price, color);
        this.rent = rent;
        this.group = group;
    }
    public int getBuildLevel() {
        return buildLevel;
    }

    /**
     * Henter den version af feltet, som GUI bruger
     *
     * @param gui Pointer til det aktive GUI
     * @return Et felt, som der kan bruges til at modificere GUI
     */
    private GUI_Street getGuiVersion(GUI gui) {
        GUI_Field[] fields = gui.getFields();
        return ((GUI_Street) fields[nr - 1]);
    }

    /**
     * Tjekker om den givne player ejer alle felterne i gruppen
     * @param felter Array med alle felter
     * @param player Pointer til den aktive player
     * @return Om den player ejer alle felterne i gruppen
     */
    private boolean ownsEntireGroup(Field[] felter, Player player) {
        boolean ownsAll = true;
        for (int value : group) {
            if (((Property) felter[value - 1]).getOwner() != player) {
                ownsAll = false;
                break;
            }
        }
        return ownsAll;
    }

    /**
     * Tjekker om der er bygget jævnt i en gruppe
     *
     * @param direction "up" eller "down". Om der bygges eller nedrives
     * @return Om der må bygges/nedrives eller ej
     */
    private boolean buildingFlat(String direction, Field[] felter) {
        boolean flat = true;
        if (group.length == 1) {
            // Hvis der kun er en anden grund i gruppen, er det lidt nemmere at tjekke
            StreetField other = (StreetField) felter[group[0]-1];
            if (this.buildLevel < other.getBuildLevel() && direction.equals("down")) {
                // Der forsøges at rive ned, men der er allerede bygget lavere end den anden
                flat = false;
            } else if (this.buildLevel > other.getBuildLevel() && direction.equals("up")) {
                // Der forsøges at bygge, men der er allerede bygget højere end den anden
                flat = false;
            }
        } else {
            // Hvis der er to andre i gruppen kan tjekket deles lidt op
            StreetField[] others = new StreetField[]{(StreetField) felter[group[0]-1], (StreetField) felter[group[1]-1]};
            if (others[0].getBuildLevel() == others[1].getBuildLevel()) {
                // De to andre grunde er bygget lige højt, så det er samme tjek, som hvis der var en anden grund i gruppen
                if (this.buildLevel < others[0].getBuildLevel() && direction.equals("down")) {
                    // Der forsøges at rive ned, men der er allerede bygget lavere end de andre
                    flat = false;
                } else if (this.buildLevel > others[0].getBuildLevel() && direction.equals("up")) {
                    // Der forsøges at bygge, men der er allerede bygget højere end de andre
                    flat = false;
                }
            } else if(others[0].getBuildLevel() < others[1].getBuildLevel()) {
                // Den ene af de to andre grunde er bygget lavere end den anden
                if (this.buildLevel == others[1].getBuildLevel() && direction.equals("up")) {
                    // Der forsøges at bygge, men der er allerede bygget lige så højt som den højeste af de andre
                    flat = false;
                } else if (this.buildLevel == others[0].getBuildLevel() && direction.equals("down")) {
                    // Der forsøges at rive ned, men den er allerede samme højde, som den laveste af de andre
                    flat = false;
                }
            } else if(others[0].getBuildLevel() > others[1].getBuildLevel()) {
                // Den ene af de to andre grunde er bygget højere end den anden
                if (this.buildLevel == others[0].getBuildLevel() && direction.equals("up")) {
                    // Der forsøges at bygge, men der er allerede bygget lige så højt som den højeste af de andre
                    flat = false;
                } else if (this.buildLevel == others[1].getBuildLevel() && direction.equals("down")) {
                    // Der forsøges at rive ned, men den er allerede samme højde, som den laveste af de andre
                    flat = false;
                }
            }
        }
        return flat;
    }

    /**
     * Forsøger at bygge på den aktive grund, hvis det er muligt
     *
     * @param gui Pointer til det aktive GUI
     * @param felter Array med felter, som GUI er bygget af
     * @param player Pointer til den aktive player
     */
    public void build(GUI gui, Field[] felter, Player player) {
        GUI_Street GUIv = getGuiVersion(gui);
        if (buildLevel < 5 && buildingFlat("up", felter) && ownsEntireGroup(felter, player)) {
            buildLevel++;
            GUIv.setRent(rent[buildLevel] + " kr");
            if (buildLevel < 5) {
                GUIv.setHouses(buildLevel);
            } else {
                GUIv.setHouses(0);
                GUIv.setHotel(true);
            }
            player.updateBalance(-1 * this.buildPrice);
        } else {
            gui.getUserButtonPressed(player.playerName + " kan ikke bygge på denne grund lige nu", "OK");
        }
    }

    /**
     * Forsøger at rive nedrive hotel/et hus, hvis det er muligt
     *
     * @param gui Pointer til det aktive GUI
     * @param felter Array med felter, som GUI er bygget af
     * @param player Pointer til den aktive player
     */
    public void destroy(GUI gui, Field[] felter, Player player) {
        GUI_Street GUIv = getGuiVersion(gui);
        if (buildLevel > 0 && buildingFlat("down", felter)) {
            GUIv.setHotel(false);
            buildLevel--;
            GUIv.setHouses(buildLevel);
            GUIv.setRent(rent[buildLevel] + " kr");
            player.updateBalance(this.buildPrice/2);
        }
    }

    /**
     * Finder ud af hvad der skal betales i leje, hvis en spiller er landet på dette felt og det er ejet
     * @param felter Array med felter, som GUI er lavet af
     * @return Hvor mange kr der skal betales i leje
     */
    @Override
    protected int calculateRent(Field[] felter) {
        int multiplier = 1;
        if (ownsEntireGroup(felter, ((Property) felter[nr-1]).getOwner())) {
            multiplier = 2;
        }
        return this.rent[this.buildLevel] * multiplier;
    }

    /**
     * Udfører den rigtige handling, når der landes på et felt, af denne type
     *
     * @param gui Pointer til det aktive GUI
     * @param player Pointer til den player, hvis tur det er
     * @param felter Array af pointers til de felter, som GUI er bygget op af
     */
    @Override
    public void action(GUI gui, Player player, Field[] felter) {
        if (this.owned) {
            // Beregn leje
            int rent = this.calculateRent(felter);
            gui.getUserButtonPressed(player.playerName + " er landet på " + this.name + ", som er ejet af " + this.owner.getPlayerName() + " og skal betale husleje på " + rent, "Øv");
            player.updateBalance(-1 * rent);
        } else {
            // Tilbyd at køb, hvis spiller har nok penge
            if (player.getBalance() >= this.price) {
                String valg = gui.getUserButtonPressed(player.playerName + " har råd til at købe " + this.name + ". Vil du købe grunden?", "Ja", "Nej");
                if (valg.equals("Ja")) {
                    this.setOwner(player);
                    player.updateBalance(-1 * this.price);
                    GUI_Street GUIv = getGuiVersion(gui);
                    GUIv.setOwnerName(player.getPlayerName());
                    GUIv.setRent(this.calculateRent(felter) + " kr.");
                }
            }
        }
    }
    @Override
    public void action(GUI gui, Player player){}
}
