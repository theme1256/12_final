package Model.Cards;

import Model.Player;
import gui_main.GUI;

public class Kort {
    protected int cardNumber;
    protected String cardDescription;
    protected String action;
    protected int value;

    public Kort(int cardNumber){
        this.cardNumber = cardNumber;
    }


    public int getCardNumber() {
        return this.cardNumber;
    }

    public void action(Player player, GUI gui) {
    }

}