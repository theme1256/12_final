package Model.Fields;

public class StreetField extends Field {
    int price;
    int nr;
    String streetName;
    String color;

    // Constructor
    public StreetField ( int price, int nr, String streetName, String color){
        this.price = price;
        this.nr = nr;
        this.streetName = streetName;
        this.color = color;
    }

}
