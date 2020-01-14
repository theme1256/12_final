package Controller;

import gui_fields.*;
import gui_main.GUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatadorUI {
    public static void main(String[] args) {
        FieldController fieldController = new FieldController();
        GUI_Field[] gui_fields = fieldController.createGUIFromFields();

        GUI gui = new GUI(gui_fields);

        ChanceCardController chanceCardController = new ChanceCardController(fieldController, gui);

        DiceController diceController = new DiceController(gui);

        PlayerController playerController = new PlayerController(gui, diceController);

        GameController gameController = new GameController(gui, chanceCardController, diceController, playerController, fieldController);
    }

    public static String[] addElement(String[] srcArray, String elementToAdd) {
        String[] destArray = Arrays.copyOf(srcArray, srcArray.length + 1);
        destArray[destArray.length - 1] = elementToAdd;
        return destArray;
    }

    public static String[] removeElement(String[] input, String deleteMe) {
        if (input != null) {
            List<String> list = new ArrayList<String>(Arrays.asList(input));
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equals(deleteMe)) {
                    list.remove(i);
                    break;
                }
            }
            return list.toArray(new String[0]);
        } else {
            return new String[0];
        }
    }
}

