package Model.Fields;

import Model.Player;

import java.awt.*;

// alt vi vil gerne at eje

public abstract class Property extends Field{


    public Property(String name, String description, int nr, int price) {
        super(name, description, nr, price);
        this.name = name;
        this.description = description;
        this.nr = nr;
        this.price = price;
    }

    public Property(String name, String description, int nr, int price, Color color) {
        super(name, description, nr, price, color);
        this.name = name;
        this.description = description;
        this.nr = nr;
        this.price = price;
        this.color = color;
    }




}
