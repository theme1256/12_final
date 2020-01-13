package Model.Fields;

import Model.Player;
import gui_main.GUI;

import java.awt.*;

public class StreetField extends Property {

    public StreetField(String name,String description, int nr, int price, Color color) {
        super(name, description, nr, price, color);
    }

    @Override
    protected int calculateRent() {
        return 0;
    }

    @Override
    public void action(GUI gui, Player player) {
        if (this.owned) {
            // Beregn leje
            int rent = this.calculateRent();
            gui.getUserButtonPressed("Du er landet på " + this.name + ", som er ejet af " + this.owner.getPlayerName() + " og skal betale husleje på " + rent, "Øv");
            player.updateBalance(-1 * rent);
        } else {
            // Tilbyd at køb, hvis spiller har nok penge
            if (player.getBalance() >= this.price) {
                String valg = gui.getUserButtonPressed("Du har råd til at købe " + this.name + ". Vil du købe grunden?", "Ja", "Nej");
                if (valg.equals("Ja")) {
                    this.setOwner(player);
                    player.updateBalance(-1 * this.price);
                }
            }
        }
    }
}
