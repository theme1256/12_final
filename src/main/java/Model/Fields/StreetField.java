package Model.Fields;

import Model.Player;
import gui_fields.GUI_Field;
import gui_fields.GUI_Street;
import gui_main.GUI;

import java.awt.*;

/**
 * Extender Property, som extender BaseField.
 *
 * Håndterer, hvis en spiller lander på en grund.
 * Beregner hvad leje skal være, hvis grunden er ejet, der er bygget på den og om de andre grunde i gruppen er ejet af den samme
 * Holder styr på at opdatere GUI, når der bygges/nedrives/købes/sælges
 */
public class StreetField extends Property {
    private int buildLevel = 0;
    private int[] rent;
    private int[] group;
    private int buildPrice;

    public StreetField(String name,String description, int nr, int price, int[] rent, int[] group, int buildPrice, Color color) {
        super(name, description, nr, price, color);
        this.rent = rent;
        this.group = group;
        this.buildPrice = buildPrice;
    }

    /**
     * Giver information om hvor meget der er bygget på denne grund. Bruges til at beregne en spillers værdi og hvad de skal betale i skat
     *
     * @return Antal huse der er bygget, med mindre det er 5, så er det et hotel
     */
    public int getBuildLevel() {
        return this.buildLevel;
    }

    /**
     * Returnerer hvad det koster at bygge et hus på denne grund
     *
     * @return Antal kr et hus koster
     */
    public int getBuildPrice() {
        return this.buildPrice;
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
    private boolean ownsEntireGroup(BaseField[] felter, Player player) {
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
    private boolean buildingFlat(String direction, BaseField[] felter) {
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
     * @param player Pointer til den aktive player
     * @param felter Array med felter, som GUI er bygget af
     */
    public void build(GUI gui, Player player, BaseField[] felter) {
        GUI_Street GUIv = getGuiVersion(gui);
        // Tjekker at der kan bygges på grunden, som der fortsat vil være bygget jævn og om den samme spiller ejer alle felterne i gruppen
        if (buildLevel < 5 && buildingFlat("up", felter) && ownsEntireGroup(felter, player)) {
            // Tjekker om spilleren har nok penge til at bygge
            if (player.getBalance() >= this.buildPrice && this.buildLevel < 4 || player.getBalance() >= this.buildPrice*5 && this.buildLevel == 4) {
                // Tæller antallet af huse op
                buildLevel++;
                // Opdaterer GUI til at vise den nye leje
                GUIv.setRent(rent[buildLevel] + " kr");
                // Hvis der skal bygges mindre end 5, så er det et antal huse, ellers er det et hotel
                if (buildLevel < 5) {
                    GUIv.setHouses(buildLevel);
                    // Træk pengene fra spilleren
                    player.updateBalance(-1 * this.buildPrice);
                } else {
                    // Fjern huse og erstat med et hotel
                    GUIv.setHouses(0);
                    GUIv.setHotel(true);
                    // Træk pengene fra spilleren
                    player.updateBalance(-1 * this.buildPrice*5);
                }
            } else {
                gui.getUserButtonPressed("Du har ikke nok penge til at bygge", "Ok");
            }
        } else {
            gui.getUserButtonPressed(player.playerName + " kan ikke bygge på denne grund lige nu", "OK");
        }
    }

    /**
     * Forsøger at rive nedrive hotel/et hus, hvis det er muligt
     *
     * @param gui Pointer til det aktive GUI
     * @param player Pointer til den aktive player
     * @param felter Array med felter, som GUI er bygget af
     */
    public void destroy(GUI gui, Player player, BaseField[] felter) {
        GUI_Street GUIv = getGuiVersion(gui);
        // Tjekker om der er bygget noget på grunden, at man fortsat vil have bygget jævnt hvis der fjernes et hus og om den samme spiller ejer alle felterne i gruppen
        if (buildLevel > 0 && buildingFlat("down", felter) && ownsEntireGroup(felter, player)) {
            // Uanset hvad, så skal der ikke længere være et hotel på denne grund
            GUIv.setHotel(false);
            // Giv spilleren penge tilbage, det halve af byggeprisen
            if (buildLevel == 5) {
                player.updateBalance(this.buildPrice / 2 * 5);
            } else {
                player.updateBalance(this.buildPrice / 2);
            }
            // Tæl antallet af huse ned
            buildLevel--;
            // Opdater GUI til at vise det nye antal huse
            GUIv.setHouses(buildLevel);
            // Opdater prisen på leje, som står i GUI
            GUIv.setRent(rent[buildLevel] + " kr");
        } else {
            gui.getUserButtonPressed(player.playerName + " kan ikke nedrive på denne grund lige nu", "OK");
        }
    }

    /**
     * Finder ud af hvad der skal betales i leje, hvis en spiller er landet på dette felt og det er ejet
     * @param felter Array med felter, som GUI er lavet af
     * @return Hvor mange kr der skal betales i leje
     */
    @Override
    protected int calculateRent(BaseField[] felter) {
        int multiplier = 1;
        // Hvis det er den samme, som ejer hele gruppen og der ikke er bygget endnu, så skal leje være det dobbelte
        if (ownsEntireGroup(felter, ((Property) felter[nr-1]).getOwner()) && this.buildLevel == 0) {
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
    public void action(GUI gui, Player player, BaseField[] felter) {
        if (this.owned) {
            // Beregn leje
            int rent = this.calculateRent(felter);
            gui.getUserButtonPressed("Du er landet på " + this.name + ", som er ejet af " + this.owner.getPlayerName() + " og skal betale husleje på " + rent + " kr. ", "Øv");
            player.updateBalance(-1 * rent);
            this.owner.updateBalance(rent);
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
