package Model.Fields;

import Model.Player;
import gui_fields.GUI_Field;
import gui_fields.GUI_Start;

import java.awt.*;

public class Start extends Field {
    private GUI_Start GUIv = new GUI_Start();
    private int payout = 0;

    public Start(int id, String text, int payout){
        super(id, text);
        this.payout = payout;
        GUIv.setTitle(text);
        GUIv.setSubText("Modtag: " + payout);
    }

    public void passStart(Player player) {
        player.updateBalance(payout);
    }

    @Override
    public GUI_Field getGUIVersion() {
        return GUIv;
    }
}
