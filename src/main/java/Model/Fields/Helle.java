package Model.Fields;

import Model.Player;
import gui_main.GUI;

public class Helle extends Field {

    public Helle(String name, String description, int nr) {
        super(name, description, nr);
    }

    @Override
    public void action(GUI gui, Player player) {
        gui.getUserButtonPressed("Der sker ikke noget på dette felt", "OK");
    }
}
