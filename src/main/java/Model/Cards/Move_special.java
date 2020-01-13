package Model.Cards;

import Model.Player;
import gui_main.GUI;

public class Move_special extends ChanceCards {
    public Move_special(int cardNumber) {
        super(cardNumber);
    }

    //MANGLER AT MAN BETALER DOBBELT!

    public void action(Player player, GUI gui) {
    switch (this.cardNumber) {
        case 30:
        case 31:

            if (player.currentFelt == 3) {
                player.moveTo(6);
                break;

            } else if (player.currentFelt == 8) {
                player.moveTo(16);
                break;

            } else if (player.currentFelt == 18 || player.currentFelt == 23) {
                player.moveTo(24);
                break;

            } else if (player.currentFelt == 34) {
                player.moveTo(36);
                break;

            } else if (player.currentFelt == 37) {
                player.moveTo(6, true);
                break;

            }

    }
        gui.displayChanceCard(toString());
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
