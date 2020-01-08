package Model.Fields;

import gui_fields.GUI_Field;
import gui_fields.GUI_Tax;

public class Tax extends Field {
    private GUI_Tax GUIv = new GUI_Tax();

    public Tax(int id, String text) {
        super(id, text);
        GUIv.setTitle(text);
        GUIv.setSubText("");
        GUIv.setDescription(text);
    }

    @Override
    public GUI_Field getGUIVersion() {
        return GUIv;
    }
}
