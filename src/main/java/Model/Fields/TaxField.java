package Model.Fields;

import Model.Player;
import gui_main.GUI;

public class TaxField extends BaseField {

    public TaxField(String name, String description, int nr) {
        super(name, description, nr);
    }

    @Override
    public void action(GUI gui, Player player) {
        if (this.nr == 5) {
            String valg = gui.getUserButtonPressed(player.playerName +" skal betale 10% eller kr. 200 i skat", "10%", "kr. 200");
            if (valg.equals("10%")) {
                int total = player.getBalance();
                player.updateBalance(-1 * total/10);
            } else {
                player.updateBalance(-200);
            }
        } else if (this.nr == 39) {
            gui.getUserButtonPressed(player.playerName + " skal betale kr. 100 i skat", "OK");
            player.updateBalance(-100);
        }
    }
    public void action(GUI gui, Player player, BaseField[] felter){}
}
