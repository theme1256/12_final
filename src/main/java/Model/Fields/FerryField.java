package Model.Fields;

import Model.Player;
import gui_main.GUI;

import java.awt.*;

public class FerryField extends Property {
    public FerryField(String name, String description, int nr, int price, Color color) {
        super(name, description, nr, price, color);
    }

    @Override
    protected int calculateRent() {
        return 0;
    }

    @Override
    public void action(GUI gui, Player player) {

    }
}
