package Model.Fields;

import Model.Player;

import java.awt.*;

public class StreetField extends Property {
    private Color color;
    public StreetField(String name, String description, int nr, int price, Player owner, Color color) {
        super(name, description, nr, price, owner);
        this.color = color;
    }


}
