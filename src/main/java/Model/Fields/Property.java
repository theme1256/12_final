package Model.Fields;

import Model.Player;
import gui_main.GUI;

import java.awt.*;

// alt vi vil gerne at eje

public abstract class Property extends Field{
    protected boolean owned = false;
    protected Player owner = null;

    public Property(String name, String description, int nr, int price) {
        super(name, description, nr, price);
    }
    public Property(String name, String description, int nr, int price, Color color) {
        super(name, description, nr, price, color);
    }


    protected abstract int calculateRent();
    public abstract void action(GUI gui, Player player);
    public Player getOwner() {
        return this.owner;
    }
    public void setOwner(Player player) {
        this.owner = player;
        this.owned = true;
    }
    public void sell() {
        this.owner = null;
        this.owned = false;
    }
}
