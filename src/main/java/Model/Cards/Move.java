package Model.Cards;

public class Move extends Kort {
    public Move(int cardNumber) {
        super(cardNumber);
    }
    public String toString(){
        switch (cardNumber){
            case 21:
                cardDescription += "Gå i fængsel. Ryk direkte til fængslet. Selv om De passerer >Start<, indkasserer De ikke kr. 200,00.";
                break;
            case 22:
                cardDescription += "Ryk frem til Frederiksbergallé. Hvis De passerer >Start<, indkassér kr. 200,00.";
                break;
            case 23:
                cardDescription += "Ryk tre felter tilbage.";
                break;
            case 24:
                cardDescription += "Tag ind på Rådhuspladsen.";
                break;
            case 25:
                cardDescription += "Ryk tre felter tilbage.";
                break;
            case 26:
                cardDescription += "Tag med Øresundsbåden - Flyt brukken frem, og hvis De passerer >Start<, indkassér kr. 200,00.";
                break;
            case 27:
                cardDescription += "Gå i fængsel. Ryk direkte til fængslet. Selv om De passerer >Start<, indkasserer De ikke kr. 200,00.";
                break;
            case 28:
                cardDescription += "Ryk frem til Grønningen. Hvis de passerer >Start<, indkassér da kr. 200,00.";
                break;
            case 29:
                cardDescription += "Ryk frem til >Start<.";
                break;
            default:
                cardDescription += cardNumber+0;
                break;
        }
        return cardDescription;
    }
}
