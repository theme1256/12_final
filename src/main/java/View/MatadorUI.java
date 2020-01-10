package View;

import Controller.*;
import gui_fields.*;
import gui_main.GUI;

public class MatadorUI {
    private static GUI gui;
    private static FieldController fieldController;
    private static ChanceCardController chanceCardController;
    private static GameController gameController;
    private static DiceController diceController;
    private static PlayerController playerController;


    public static void main(String[] args) {
        initGame();
    }

    private static void initGame() {
        fieldController = new FieldController();
        GUI_Field[] gui_fields = fieldController.createGUIFromFields();

        gui = new GUI(gui_fields);

        chanceCardController = new ChanceCardController(fieldController, gui);

        diceController = new DiceController(gui);

        playerController = new PlayerController(gui);

        gameController = new GameController(gui, chanceCardController, diceController, playerController);
    }
}

