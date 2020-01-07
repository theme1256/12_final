package Model.Fields;

public abstract class Field {
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


}


