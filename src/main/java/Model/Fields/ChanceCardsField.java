package Model.Fields;

import Model.Player;
import gui_main.GUI;

public class ChanceCardsField extends Field {

    public ChanceCardsField(String name, String description, int nr) {

        super(name, description, nr);
    }

    @Override
    public void action(GUI gui, Player player) {
        gui.getUserButtonPressed(player.playerName +" træk et chancekort", "OK");
    }
    public void action(GUI gui, Player player, Field[] felter){}
}
