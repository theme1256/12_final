package Model.Fields;

public class BeerFeild extends Field{
    String name;
    int price;
    int nr;

    //Constructor
    public BeerFeild(String name, int price, int nr){
        this.name = name;
        this.price = price;
        this.nr = nr;
    }
}
