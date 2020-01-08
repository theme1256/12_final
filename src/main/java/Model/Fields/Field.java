package Model.Fields;

import java.awt.*;


//Til brug af anden felt metode HUSK AT TILFØJ FELT KLASSER
public abstract class Field {
    protected String description;
    protected String name;
    //String color;
    //int price;
    protected int nr;
    protected Color color;

    //Constructor
    public Field(String name, String description, int nr) {
        this.description = description;
        this.name = name;
        this.nr = nr;
    }
    public Field(String name, String description, int nr, Color color) {
        this.description = description;
        this.name = name;
        this.nr = nr;
        this.color = color;
    }

    public String getName() {return name;}
    public int getNr() {return nr;}
    public String getDescription() {return description;}
    public Color getColor() {return color;}
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







