package Model.Cards;

public class Jail extends Kort {
    public Jail(int cardNumber) {
        super(cardNumber);
    }
    public String toString(){
        switch (cardNumber){
            case 0:
                cardDiscription += "I anledning af Kongens fødselsdag benådes De herved for fængsel.\n" +
                        "Dette kort opbevares, indtil De får brug for det, eller De kan sælge det.";
                break;
            case 1:
                cardDiscription += "I anledning af Kongens fødselsdag benådes De herved for fængsel.\nDette kort opbevares, indtil De får brug for det, eller De kan sælge det.";
                break;
            default:
                cardDiscription += cardNumber+0;
                break;
        }
        return cardDiscription;
    }
}


