package Model.Cards;

import Controller.FieldController;
import Model.Player;
import gui_main.GUI;

public abstract class ChanceCards {
    protected int cardNumber;
    protected String cardDescription;
    protected String action;
    protected int value;

    ChanceCards(int cardNumber){
        this.cardNumber = cardNumber;
    }


    public int getCardNumber() {
        return this.cardNumber;
    }

    public abstract boolean action(Player player, GUI gui);
}