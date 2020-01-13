package Controller;

import gui_fields.*;
import gui_main.GUI;

public class MatadorUI {

    public static void main(String[] args) {
        FieldController fieldController = new FieldController();
        GUI_Field[] gui_fields = fieldController.createGUIFromFields();

        GUI gui = new GUI(gui_fields);

        ChanceCardController chanceCardController = new ChanceCardController(fieldController, gui);

        DiceController diceController = new DiceController(gui);

        PlayerController playerController = new PlayerController(gui);

        GameController gameController = new GameController(gui, chanceCardController, diceController, playerController, fieldController);
    }
}

