package Model.Fields;

import Model.Player;
import gui_main.GUI;

import java.awt.*;

public class BeerField extends Property{

    public BeerField(String name, String description, int nr, int price) {
        super(name, description, nr, price);
    }

    @Override
    protected int calculateRent() {
        return 0;
    }

    @Override
    public void action(GUI gui, Player player) {

    }
}
