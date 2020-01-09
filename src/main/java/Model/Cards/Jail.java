package Model.Cards;

import Model.Player;
import gui_main.GUI;

public class Jail extends Kort {
    public Jail(int cardNumber) {
        super(cardNumber);
    }

    @Override
    public void action(Player player, GUI gui) {
        player.setJailPass(true);
        gui.displayChanceCard(toString());
    }

    public String toString(){
        cardDescription = "";
        action = "";
        value = 0;
        switch (cardNumber){
            case 0:
                cardDescription += "I anledning af Kongens fødselsdag benådes De herved for fængsel.\n" +
                        "Dette kort opbevares, indtil De får brug for det, eller De kan sælge det.";
                break;
            case 1:
                cardDescription += "I anledning af Kongens fødselsdag benådes De herved for fængsel.\nDette kort opbevares, indtil De får brug for det, eller De kan sælge det.";
                break;
            default:
                cardDescription += cardNumber+0;
                break;
        }
        return cardDescription;
    }
}


