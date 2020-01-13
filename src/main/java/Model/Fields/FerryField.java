package Model.Fields;

import Model.Player;
import gui_main.GUI;

import java.awt.*;

public class FerryField extends Property {
    private int[] group = new int[]{6, 16, 26, 36};
    private int[] rent = new int[]{25, 50, 100, 200};

    public FerryField(String name, String description, int nr, int price, Color color) {
        super(name, description, nr, price, color);
    }

    @Override
    protected int calculateRent(Field[] felter) {
        return 0;
    }

    @Override
    public void action(GUI gui, Player player){}
    public void action(GUI gui, Player player, Field[] felter) {

    }
}
