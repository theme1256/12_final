package Model.Cards;

public class Money_special extends Kort {
    public Money_special(int cardNumber) {
        super(cardNumber);
    }
    public String toString(){
        switch (cardNumber){
            case 17:
                cardDiscription +="De har lagt penge ud til sammenskudsgilde. Mærkværdigvis betaler alle straks.\n" +
                        " Modtag fra hver medspiller kr. 25,00.";
                break;
            case 18:
                cardDiscription +="De modtager >Matador-legatet for værdig trængende<, stort 2000,00.\n" +
                        "Ved værdig trængende forstås at Deres formue, dvs. Deres kontante penge + skøder + bygninger ikke overstiger kr. 750,00.";
                break;
            case 19:
                cardDiscription += "Kul- og kokspriserne er steget, og De skal betale:\n" +
                        "kr. 25,00 pr. hus og\nkr. 125,00 pr. hotel.";
                break;
            case 20:
                cardDiscription += "Ejendomsskatterne er steget, ekstraudgifterne er:\n" +
                        "kr. 50,00 pr. hus\nkr. 125,00 pr. hotel.";
                break;
            default:
                cardDiscription += cardDiscription+0;
                break;
        }
        return cardDiscription;
    }
}
