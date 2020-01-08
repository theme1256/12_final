package Model.Fields;

import gui_fields.GUI_Field;
import gui_fields.GUI_Shipping;

import java.awt.*;

public class Shipping extends Ownable {
    private GUI_Shipping GUIv = new GUI_Shipping();

    public Shipping(int id, String text, int pris) {
        super(id, text, pris);
        this.pris = pris;
        GUIv.setTitle(text);
        GUIv.setDescription(text);
        GUIv.setSubText("Kr. " + pris);
    }
    public Shipping(int id, String text, Color C_bg, Color C_text, int pris) {
        super(id, text, pris);
        this.pris = pris;
        GUIv.setTitle(text);
        GUIv.setDescription(text);
        GUIv.setSubText("Kr. " + pris);
        GUIv.setBackGroundColor(C_bg);
        GUIv.setForeGroundColor(C_text);
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
