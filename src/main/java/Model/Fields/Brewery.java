package Model.Fields;

import gui_fields.GUI_Field;
import gui_fields.GUI_Brewery;

public class Brewery extends Ownable {
    private GUI_Brewery GUIv = new GUI_Brewery();

    public Brewery(int id, String text, int pris) {
        super(id, text, pris);
        this.pris = pris;
        GUIv.setTitle(text);
        GUIv.setSubText("Kr. " + pris);
        GUIv.setDescription(text);
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
