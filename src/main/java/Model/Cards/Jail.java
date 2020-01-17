package Model.Cards;

import Controller.FieldController;
import Model.Player;
import gui_main.GUI;

public class Jail extends ChanceCards {
    public Jail(int cardNumber) {
        super(cardNumber);
    }

    @Override
    public boolean action(Player player, GUI gui) {
        player.setJailPass(true);
        gui.displayChanceCard(toString());
        return false;
    }

    public String toString(){
        cardDescription = "I anledning af Kongens fødselsdag benådes De herved for fængsel.\n" +
                        "Dette kort opbevares, indtil De får brug for det, eller De kan sælge det.";
        return cardDescription;
    }


    //IGNORER ikke slet
    public boolean action(Player player, GUI gui, FieldController fc) {
        return true;
    }
}


