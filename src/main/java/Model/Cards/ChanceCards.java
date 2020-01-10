package Model.Cards;

import Model.Player;
import gui_main.GUI;

public abstract class ChanceCards {
    protected int cardNumber;
    protected String cardDescription;
    protected String action;
    protected int value;

    public ChanceCards(int cardNumber){
        this.cardNumber = cardNumber;
    }


    public int getCardNumber() {
        return this.cardNumber;
    }

    public void action(Player player, GUI gui) {
    }

}