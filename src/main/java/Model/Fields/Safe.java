package Model.Fields;

import gui_fields.GUI_Field;
import gui_fields.GUI_Refuge;

import java.awt.*;

public class Safe extends Field {
    private GUI_Refuge GUIv = new GUI_Refuge();

    public Safe(int id, String text) {
        super(id, text);
        GUIv.setSubText(text);
        GUIv.setDescription(text);
    }
    public Safe(int id, String text, Color C_bg, Color C_text) {
        super(id, text);
        GUIv.setSubText(text);
        GUIv.setDescription(text);
        GUIv.setBackGroundColor(C_bg);
        GUIv.setForeGroundColor(C_text);
    }

    @Override
    public GUI_Field getGUIVersion() {
        return GUIv;
    }
}
