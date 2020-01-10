package Model.Cards;

import Model.Player;
import gui_main.GUI;

public class Move extends ChanceCards {
    public Move(int cardNumber) {
        super(cardNumber);
    }

    @Override
    public void action(Player player, GUI gui) {
        switch (this.cardNumber) {
            case 21:
                player.moveTo(10, false);
                break;
            case 22:
                player.moveTo(11, true);
                break;
            case 23:
                player.moveTo(player.currentFelt -3);
                break;
            case 24:
                player.moveTo(39);
                break;
            case 25:
                player.moveTo(player.currentFelt +3);
                break;
            case 26:
                if (player.currentFelt >= 7){
                    player.moveTo(5, true);
                }
                else
                player.moveTo(5, false);
                break;
            case 27:
                player.moveTo(10, false);
                break;
            case 28:
                if (player.currentFelt >= 34){
                    player.moveTo(24, true);
                }
                else
                player.moveTo(24, false);
                break;
            case 29:
                player.moveTo(0, true);
                break;
            default:
                break;
        }
        gui.displayChanceCard(toString());
    }

    public String toString(){
        switch (cardNumber){
            case 21:
                cardDescription = "Gå i fængsel. Ryk direkte til fængslet. Selv om De passerer >Start<, indkasserer De ikke kr. 200,00.";
                break;
            case 22:
                cardDescription = "Ryk frem til Frederiksbergallé. Hvis De passerer >Start<, indkassér kr. 200,00.";
                break;
            case 23:
                cardDescription = "Ryk tre felter tilbage.";
                break;
            case 24:
                cardDescription = "Tag ind på Rådhuspladsen.";
                break;
            case 25:
                cardDescription = "Ryk tre felter tilbage.";
                break;
            case 26:
                cardDescription = "Tag med Øresundsbåden - Flyt brikken frem, og hvis De passerer >Start<, indkassér kr. 200,00.";
                break;
            case 27:
                cardDescription = "Gå i fængsel. Ryk direkte til fængslet. Selv om De passerer >Start<, indkasserer De ikke kr. 200,00.";
                break;
            case 28:
                cardDescription = "Ryk frem til Grønningen. Hvis de passerer >Start<, indkassér da kr. 200,00.";
                break;
            case 29:
                cardDescription = "Ryk frem til >Start<.";
                break;
            default:
                cardDescription += cardNumber+0;
                break;
        }
        return cardDescription;
    }
}
