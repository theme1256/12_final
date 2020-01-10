package Controller;
import Model.ChanceDeck;
import Model.Player;


public class DiceController {

    public static void extraTurn(Player player){

        if(GameController.val[0]==GameController.val[1]){

            BoardControllerGUI.gui.getUserButtonPressed(player + ", får en ekstra tur!", "FEDT!");
            BoardControllerGUI.gui.getUserButtonPressed(player + ", tryk enter/knappen for at slå", "SLÅ");
            int[] val1 = GameController.shaker.shake();
            BoardControllerGUI.gui.setDice(val1[0],val1[1]);
            int value1 = val1[0] + val1[1];
            player.move(value1);
            PlayerController.handleGetInJail(player);

            if(val1[0]== val1[1] ){
                BoardControllerGUI.gui.getUserButtonPressed(player + ", får en ekstra tur!", "FEDT!");
                BoardControllerGUI.gui.getUserButtonPressed(player + ", tryk enter/knappen for at slå", "SLÅ");

                System.out.println("Tillykke du får en ekstra tur");
                int[] val2 = GameController.shaker.shake();
                BoardControllerGUI.gui.setDice(val2[0],val2[1]);
                int value2 = val2[0] + val2[1];
                player.move(value2);
                PlayerController.handleGetInJail(player);

                if(val2[0]== val2[1]){
                    BoardControllerGUI.gui.getUserButtonPressed(player + ", er for heldig til det kan være rigtigt! Du ryger i fængsel", "ØV!");
                    player.moveTo(10,false);

                }
            }
        }


    }


}
