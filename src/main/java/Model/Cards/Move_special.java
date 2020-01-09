package Model.Cards;

public class Move_special extends Kort {
    public Move_special(int cardNumber) {
        super(cardNumber);
    }
    public String toString(){
        switch (cardNumber){
            case 30:
                cardDescription = "Ryk brikken frem til nærmeste dampskibsselskab og betal ejeren to gange den leje, han ellers er berettiget til.\n " +
                        "Hvis selskabet ikke ejes af nogen, kan De købe det af banken";
                break;
            case 31:
                cardDescription = "Ryk brikken frem til nærmeste dampskibsselskab og betal ejeren to gange den leje, han ellers er berettiget til. Hvis selskabet ikke ejes af nogen, kan De købe det af banken.";
                break;
            default:
            cardDescription +=cardNumber+0;
            break;
        }
        return cardDescription;
    }
}
