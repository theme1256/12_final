package Model.Fields;

import Model.Player;
import gui_fields.GUI_Field;
import gui_fields.GUI_Ownable;
import gui_fields.GUI_Street;

import java.awt.*;

public abstract class Ownable extends Field {
    protected int pris;
    protected GUI_Ownable GUIv;

    public Ownable(int id, String text, int pris) {
        super(id, text);
        this.pris = pris;
    }
    public Ownable(int id, String text, Color C_bg, Color C_text, int pris) {
        super(id, text, C_bg, C_text);
        this.pris = pris;
    }

    public String getOwner() {
        return GUIv.getOwnerName();
    }
    public void setOwner(Player player) {
        GUIv.setOwnerName(player.playerName);
    }
    public void resetOwner() {
        GUIv.setOwnerName("");
    }

    public int getPris() {
        return pris;
    }

    public abstract int getRent();
}
