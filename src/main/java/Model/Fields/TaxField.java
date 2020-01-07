package Model.Fields;

public class TaxField extends Field {
    String name;
    int price;
    int nr;

    //Constructor
    public TaxField(String name, int price, int nr){
        this.name = name;
        this.price = price;
        this.nr = nr;
    }
}
