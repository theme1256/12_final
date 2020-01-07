package Model.ChanceCards;

import Model.Player;
import gui_main.GUI;

public class Card {
    protected int cardNumber;
    protected String text;

    public Card(int cardNumber, String text){
        this.cardNumber= cardNumber;
        this.text = text;
    }

    public int getCardNumber() {
        return this.cardNumber;
    }

    public void action(Player player, GUI gui) {
    }

    @Override
    public String toString() {
        return this.text;
    }
}
