package Model.Fields;

import Model.Player;
import gui_main.GUI;

public class JailField extends BaseField {

    public JailField(String name, String description, int nr) {
        super(name, description, nr);
    }

    @Override
    public void action(GUI gui, Player player) {
        player.addTurnInJail();
    }
    public void action(GUI gui, Player player, BaseField[] felter){}
}


