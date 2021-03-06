package Model.Fields;

import Controller.DiceController;
import Model.Player;
import gui_fields.GUI_Brewery;
import gui_fields.GUI_Field;
import gui_main.GUI;

public class BeerField extends Property{
    private int[] group = new int[]{13, 29};
    private int[] rent = new int[]{4, 10};

    public BeerField(String name, String description, int nr, int price) {
        super(name, description, nr, price);
    }

    /**
     * Henter den version af feltet, som GUI bruger
     *
     * @param gui Pointer til det aktive GUI
     * @return Et felt, som der kan bruges til at modificere GUI
     */
    private GUI_Brewery getGuiVersion(GUI gui) {
        GUI_Field[] fields = gui.getFields();
        return ((GUI_Brewery) fields[nr - 1]);
    }

    /**
     * Finder ud af hvor mange rederier der er ejet af den person, som ejer det aktive felt
     *
     * @param felter Array med alle felterne
     * @return Et mennesketal, som viser hvor mange grunde der ejes, husk minus 1, hvis der skal slås op i this.rent
     */
    private int ownersInGroup(BaseField[] felter) {
        int owns = 1;
        for (int value : group) {
            if (this.nr != value && felter[value-1] instanceof BeerField) {
                if (((BeerField) felter[value-1]).getOwner() == this.owner) {
                    owns++;
                }
            }
        }
        return owns;
    }

    /**
     * Beregner hvor meget der skal betales i leje for at lande på feltet
     *
     * @param felter Array med alle felter
     * @param dc Pointer til DiceController
     * @return Hvor mange kr. der skal betales i leje
     */
    private int calculateRent(BaseField[] felter, DiceController dc) {
        return dc.rollAndSumDice() * this.rent[ownersInGroup(felter)-1];
    }

    /**
     *  Bruges ikke
     * @param felter Array med alle felter
     * @return 0, ikke i brug
     */
    @Override
    protected int calculateRent(BaseField[] felter){ return 0; }

    /**
     * Finder ud af om feltet kan købes og tilbyder det, eller finder ud af hvad der skal betales i leje og trækker det
     *
     * @param gui Pointer til aktivt GUI
     * @param player Pointer til aktiv player
     * @param felter Array med alle felterne
     * @param dc Pointer til DiceController
     */
    public void action(GUI gui, Player player, BaseField[] felter, DiceController dc) {
        if (this.owned) {
            if (this.owner == player) {
                gui.getUserButtonPressed("Du ejer allerede denne grund.", "OK");
            } else {
                // Beregn leje
                int rent = this.calculateRent(felter, dc);
                gui.getUserButtonPressed("Du er landet på " + this.name + ", som er ejet af " + this.owner.getPlayerName() + " og skal betale husleje på " + rent, "Øv");
                player.updateBalance(-1 * rent);
                this.owner.updateBalance(rent);
            }
        } else {
            // Tilbyd at køb, hvis spiller har nok penge
            if (player.getBalance() >= this.price) {
                String valg = gui.getUserButtonPressed("Du har råd til at købe " + this.name + ". Vil du købe bryggeriet?", "Ja", "Nej");
                if (valg.equals("Ja")) {
                    this.setOwner(player);
                    player.updateBalance(-1 * this.price);
                    GUI_Brewery GUIv = getGuiVersion(gui);
                    GUIv.setOwnerName(player.getPlayerName());
                    GUIv.setRent(this.calculateRent(felter) + " kr.");
                }
            } else {
                gui.getUserButtonPressed("Du har ikke nok penge til at købe dette bryggeri", "Øv");
            }
        }
    }
    @Override
    public void action(GUI gui, Player player){}
    @Override
    public void action(GUI gui, Player player, BaseField[] felter) {}
}
