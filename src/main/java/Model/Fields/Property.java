package Model.Fields;

import Model.Player;

import java.awt.*;

// alt vi vil gerne at eje

public abstract class Property extends Field{


    public Property(String name, String description, int nr, int price, Player owner) {
        super(name, description, nr, price, owner);
        this.name = name;
        this.description = description;
        this.nr = nr;
        this.price = price;
        this.owner = owner;
    }

    public Property(String name, String description, int nr, int price, Color color, Player owner) {
        super(name, description, nr, price, color, owner);
        this.name = name;
        this.description = description;
        this.nr = nr;
        this.price = price;
        this.color = color;
        this.owner = owner;
    }




}
