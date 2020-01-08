package Model.Fields;

import java.awt.*;

public class Field {

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

    }


//Til brug af anden felt metode HUSK AT TILFØJ FELT KLASSER
/* public abstract class Field {
    String description;
    String name;
    //String color;
    //int price;
    int nr;

    //Constructor
    public Field(String name, String description, int nr) {
        this.description = description;
        this.name = name;
        this.nr = nr;
    }
    } */


}


