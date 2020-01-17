package Model.Cards;

import Controller.FieldController;
import Model.Player;
import gui_main.GUI;

public class Move_special extends ChanceCards {
    public Move_special(int cardNumber) {
        super(cardNumber);
    }

    public boolean action(Player player, GUI gui) {
        if (player.currentFelt <= 5 || player.currentFelt > 35) {
            player.moveTo(5, true);
        } else if (player.currentFelt <= 15) {
            player.moveTo(15);
        } else if (player.currentFelt <= 25) {
            player.moveTo(25);
        } else {
            player.moveTo(35);
        }
        player.nextRentModifier = 2;
        gui.displayChanceCard(toString());
        return true;
    }

    @Override
    public String toString(){
        cardDescription = "Ryk brikken frem til nærmeste dampskibsselskab og betal ejeren to gange den leje, han ellers er berettiget til.\n " +
                        "Hvis selskabet ikke ejes af nogen, kan De købe det af banken";
        return cardDescription;
    }
}
