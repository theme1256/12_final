package Model.Fields;

import Model.Player;

import java.awt.*;


//Til brug af anden felt metode HUSK AT TILFØJ FELT KLASSER
public abstract class Field {
    protected String description;
    protected String name;
    protected int nr;
    protected Player owner;
    protected Color color;
    protected int price;

    //Constructor
    public Field(String name, String description, int nr) {
        this.description = description;
        this.name = name;
        this.nr = nr;
    }
    public Field(String name, String description, int nr, int price, Player owner) {
        this.description = description;
        this.name = name;
        this.nr = nr;
        this.price = price;
        this.owner = owner;
    }
    public Field(String name, String description, int nr, int price, Color color, Player owner) {
        this.description = description;
        this.name = name;
        this.nr = nr;
        this.color = color;
        this.price = price;
        this.owner = owner;
    }

    public String getName() {return name;}
    public int getNr() {return nr;}
    public String getDescription() {return description;}
    public Color getColor() {return color;}
    public int getPrice(){return price;};
}





/*public class Field {

    public Color farve;
    public int pris;
    public String navn;
    public String type;
    public String gruppe;


    public Field(Color farve, int pris, String navn, String type){

        this.navn = navn;
        this.pris = pris;
        this.farve = farve;
        this.type = type;
        this.gruppe = gruppe;

    } */







