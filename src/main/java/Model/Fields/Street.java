package Model.Fields;

import Model.Player;
import gui_fields.GUI_Field;
import gui_fields.GUI_Street;

import java.awt.*;

public class Street extends Ownable {
    private GUI_Street GUIv = new GUI_Street();
    private int buildLevel = 0;
    private String[] rent;

    public Street(int id, String text, Color C_bg, Color C_text, int pris, String[] rent) {
        super(id, text, C_bg, C_text, pris);
        GUIv.setTitle(text);
        GUIv.setDescription(text);
        GUIv.setBackGroundColor(C_bg);
        GUIv.setForeGroundColor(C_text);
        GUIv.setRent(rent[0]);
        GUIv.setSubText("kr. " + pris);
        this.rent = rent;
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

    public void build() {
        if (buildLevel < 5) {
            buildLevel++;
            GUIv.setRent(rent[buildLevel]);
            if (buildLevel < 5) {
                GUIv.setHouses(buildLevel);
            } else {
                GUIv.setHouses(0);
                GUIv.setHotel(true);
            }
        }
    }
    public void destroy() {
        if (buildLevel > 0) {
            GUIv.setHotel(false);
            buildLevel--;
            GUIv.setHouses(buildLevel);
        }
    }

    @Override
    public int getPris() {
        return pris;
    }

    @Override
    public int getRent() {
        return 0;
    }

    @Override
    public GUI_Field getGUIVersion() {
        return GUIv;
    }
}
