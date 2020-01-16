package Model.Fields;

import Model.Player;
import gui_fields.GUI_Field;
import gui_fields.GUI_Ownable;
import gui_main.GUI;

import java.awt.*;

// alt vi vil gerne at eje

public abstract class Property extends BaseField {
    protected boolean owned = false;
    protected Player owner = null;

    public Property(String name, String description, int nr, int price) {
        super(name, description, nr, price);
    }
    public Property(String name, String description, int nr, int price, Color color) {
        super(name, description, nr, price, color);
    }


    protected abstract int calculateRent(BaseField[] felter);
    public abstract void action(GUI gui, Player player);
    public abstract void action(GUI gui, Player player, BaseField[] felter);


    /**
     * Henter den version af feltet, som GUI bruger
     *
     * @param gui Pointer til det aktive GUI
     * @return Et felt, som der kan bruges til at modificere GUI
     */
    private GUI_Ownable getGuiVersion(GUI gui) {
        GUI_Field[] fields = gui.getFields();
        return ((GUI_Ownable) fields[nr - 1]);
    }

    public Player getOwner() {
        return this.owner;
    }

    public void setOwner(Player player) {
        this.owner = player;
        this.owned = true;
    }

    public boolean isOwned() {
        return owned;
    }

    /**
     * Håndterer at sælge et skøde
     * @param gui Pointer til det aktive GUI
     */
    public void sell(GUI gui) {
        boolean stop = false;
        // Hvis det er en grund, så skal alle bygninderne være solgt inden skødet kan sælges
        if (this instanceof StreetField) {
            if (((StreetField) this).getBuildLevel() > 0) {
                gui.getUserButtonPressed("Du kan ikke sælge denne grund, da der er bygget på den", "OK");
                stop = true;
            }
        }
        if (!stop) {
            // Giv penge tilbage
            this.owner.updateBalance(this.price);
            // Hvis der er et GUI, opdater ejer og leje-pris
            if (gui != null) {
                GUI_Ownable GUIv = getGuiVersion(gui);
                GUIv.setRent("");
                GUIv.setOwnerName("");
            }
            // Nulstil ejeren
            this.owner = null;
            this.owned = false;
        }
    }
}
