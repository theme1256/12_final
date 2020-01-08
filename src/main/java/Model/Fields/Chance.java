package Model.Fields;

import gui_fields.GUI_Field;
import gui_fields.GUI_Chance;

public class Chance extends Field {
    private GUI_Chance GUIv = new GUI_Chance();

    public Chance(int id, String text) {
        super(id, text);
        GUIv.setDescription(text);
        GUIv.setSubText(text);
    }

    @Override
    public GUI_Field getGUIVersion() {
        return GUIv;
    }
}
