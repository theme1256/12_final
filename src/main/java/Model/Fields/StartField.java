package Model.Fields;

import Model.Player;
import gui_main.GUI;

public class StartField extends Field {
    public StartField(String name, String description, int nr) {
        super(name, description, nr);
    }

    @Override
    public void action(GUI gui, Player player) {
        gui.getUserButtonPressed("Du er landet på start", "OK");
    }
    public void action(GUI gui, Player player, Field[] felter){}
}
