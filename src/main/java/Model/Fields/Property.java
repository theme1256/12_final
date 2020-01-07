package Model.Fields;

import Model.Player;

import java.awt.*;

// alt vi vil gerne at eje

public abstract class Property extends Field{
    public Property(String name, String description, int nr, int price, Player owner, Color color) {
        super(name, description, nr);
        this.price = price;
        this.owner = owner;
    }

    int price;
    Player owner;


}
