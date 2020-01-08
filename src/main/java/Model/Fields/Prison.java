package Model.Fields;

import gui_fields.GUI_Field;
import gui_fields.GUI_Jail;

public class Prison extends Field {
    private GUI_Jail GUIv = new GUI_Jail();

    public Prison(int id, String text) {
        super(id, text);
        GUIv.setSubText(text);
        GUIv.setDescription(text);
    }

    @Override
    public GUI_Field getGUIVersion() {
        return GUIv;
    }
}
