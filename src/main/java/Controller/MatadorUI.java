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

    /**
     * Kan tilføje et element til et String-array, ved at udvidde array'et
     *
     * @param srcArray Det array der skal tilføjes til
     * @param elementToAdd Den string der skal tilføjes
     * @return Det udviddede array
     */
    public static String[] addElement(String[] srcArray, String elementToAdd) {
        String[] destArray = Arrays.copyOf(srcArray, srcArray.length + 1);
        destArray[destArray.length - 1] = elementToAdd;
        return destArray;
    }

    /**
     * Kan tilføje et element til et int-array, ved at udvidde array'et
     *
     * @param srcArray Det array der skal tilføjes til
     * @param elementToAdd Det tal der skal tilføjes
     * @return Det udviddede array
     */
    public static int[] addElement(int[] srcArray, int elementToAdd) {
        int[] destArray = Arrays.copyOf(srcArray, srcArray.length + 1);
        destArray[destArray.length - 1] = elementToAdd;
        return destArray;
    }

    /**
     * Kan slette et element fra et String-array, ud fra værdien, og fjerne punktet i array'et, så størrelsen stadig passer
     * @param input Det array der skal fjernes et element fra
     * @param deleteMe Det element der skal fjernes
     * @return Det opdaterede array
     */
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

