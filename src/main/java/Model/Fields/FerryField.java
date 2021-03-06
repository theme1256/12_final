package Model.Fields;

import Model.Player;
import gui_fields.GUI_Field;
import gui_fields.GUI_Shipping;
import gui_main.GUI;

import java.awt.*;

public class FerryField extends Property {
    private int[] group = new int[]{6, 16, 26, 36};
    private int[] rent = new int[]{25, 50, 100, 200};

    public FerryField(String name, String description, int nr, int price, Color color) {
        super(name, description, nr, price, color);
    }

    /**
     * Henter den version af feltet, som GUI bruger
     *
     * @param gui Pointer til det aktive GUI
     * @return Et felt, som der kan bruges til at modificere GUI
     */
    private GUI_Shipping getGuiVersion(GUI gui) {
        GUI_Field[] fields = gui.getFields();
        return ((GUI_Shipping) fields[nr - 1]);
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
            if (this.nr != value && felter[value-1] instanceof FerryField) {
                if (((FerryField) felter[value-1]).getOwner() == this.owner) {
                    owns++;
                }
            }
        }
        return owns;
    }

    /**
     * Beregner hvor meget en person skal betale i leje for at lande på feltet
     *
     * @param felter Array med alle felter
     * @return Hvor mange kr der skal betales i leje
     */
    @Override
    protected int calculateRent(BaseField[] felter) {
        return rent[ownersInGroup(felter)-1];
    }

    @Override
    public void action(GUI gui, Player player, BaseField[] felter) {
        if (this.owned) {
            if (this.owner == player) {
                gui.getUserButtonPressed("Du ejer allerede denne grund.", "OK");
            } else {
                // Beregn leje, husk multiplier, hvis spilleren har trukket Move_special
                int rent = this.calculateRent(felter) * player.nextRentModifier;
                gui.getUserButtonPressed(player.playerName +" du er landet på " + this.name + ", som er ejet af " + this.owner.getPlayerName() + " og skal betale husleje på " + rent, "Øv");
                player.updateBalance(-1 * rent);
                this.owner.updateBalance(rent);
            }
        } else {
            // Tilbyd at køb, hvis spiller har nok penge
            if (player.getBalance() >= this.price) {
                String valg = gui.getUserButtonPressed(player.playerName + " du har råd til at købe " + this.name + ". Vil du købe rederiet?", "Ja", "Nej");
                if (valg.equals("Ja")) {
                    this.setOwner(player);
                    player.updateBalance(-1 * this.price);
                    GUI_Shipping GUIv = getGuiVersion(gui);
                    GUIv.setOwnerName(player.getPlayerName());
                    GUIv.setRent(this.calculateRent(felter) + " kr.");
                }
            } else {
                gui.getUserButtonPressed(player.playerName + " du har ikke nok penge til at købe dette rederi", "Øv");
            }
        }
        // Sæt altid nextRentModifier til 1, så den ikke bliver videreført til næste gang spilleren lander på et rederi
        player.nextRentModifier = 1;
    }
    @Override
    public void action(GUI gui, Player player){}
}
