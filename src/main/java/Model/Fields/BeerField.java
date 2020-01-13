package Model.Fields;

import Model.Player;
import gui_main.GUI;

import java.awt.*;

public class BeerField extends Property{
    private int[] group = new int[]{13, 29};
    private int[] rent = new int[]{4, 10};

    public BeerField(String name, String description, int nr, int price) {
        super(name, description, nr, price);
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
