package Controller;
import Model.ChanceDeck;
import Model.Player;
import Model.Shaker;
import View.MatadorUI;
import gui_main.GUI;


public class DiceController {
    private GUI gui;
    private Shaker shaker;

    private int[] lastShake = new int[2];

    public DiceController(GUI gui){
        this.gui = gui;
        shaker = new Shaker(2);
    }

    public int[] rollDice() {
        lastShake = shaker.shake();
        return lastShake;
    }

    void extraTurn(Player player, PlayerController playerController){
        if(lastShake[0] == lastShake[1] && player.extraTurn){

            this.gui.getUserButtonPressed(player + ", får en ekstra tur!", "FEDT!");
            this.gui.getUserButtonPressed(player + ", tryk enter/knappen for at slå", "SLÅ");
            int[] val1 = shaker.shake();
            this.gui.setDice(val1[0],val1[1]);
            int value1 = val1[0] + val1[1];
            player.move(value1);
            playerController.handleGetInJail(player);

            if(val1[0]== val1[1] && player.extraTurn){
                this.gui.getUserButtonPressed(player + ", får en ekstra tur!", "FEDT!");
                this.gui.getUserButtonPressed(player + ", tryk enter/knappen for at slå", "SLÅ");

                System.out.println("Tillykke du får en ekstra tur");
                int[] val2 = shaker.shake();
                this.gui.setDice(val2[0],val2[1]);
                int value2 = val2[0] + val2[1];
                player.move(value2);
                playerController.handleGetInJail(player);

                if(val2[0]== val2[1] && player.extraTurn){
                    this.gui.getUserButtonPressed(player + ", er for heldig til det kan være rigtigt! Du ryger i fængsel", "ØV!");
                    player.moveTo(10,false);

                }
            }
        }


    }


}
