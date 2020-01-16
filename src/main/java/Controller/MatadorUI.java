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
        if (args.length > 0) {
            switch (args[0]) {
                case "TC2":
                    diceController.overrideShakes(new int[][]{
                            {1, 2},
                            {1, 2},
                            {1, 2},
                    });
                    break;
                case "TC3":
                    diceController.overrideShakes(new int[][]{
                            {1, 3},
                            {3, 3},
                            {1, 2},
                            {3, 3},
                            {2, 2},
                            {1, 2},
                            {3, 3},
                            {2, 2},
                            {5, 5},
                    });
                    break;
                case "TC4":
                    diceController.overrideShakes(new int[][]{
                            {1, 4},
                            {1, 4},
                            {1, 4},
                    });
                    chanceCardController = new ChanceCardController(fieldController, gui, true);
                    break;
                case "TC5":
                    diceController.overrideShakes(new int[][]{
                            {1, 5},
                            {1, 5},
                            {1, 5},
                    });
                    chanceCardController = new ChanceCardController(fieldController, gui, true);
                    break;
                case "TC6":
                    diceController.overrideShakes(new int[][]{
                            {1, 6},
                            {1, 6},
                            {1, 6},
                    });
                    chanceCardController = new ChanceCardController(fieldController, gui, true);
                    break;
                case "TC7":
                    diceController.overrideShakes(new int[][]{
                            {2, 6},
                            {2, 6},
                            {2, 6},
                    });
                    chanceCardController = new ChanceCardController(fieldController, gui, true);
                    break;
                default:
                    System.out.println("Den testcase blev ikke genkendt, fik: " + args[0]);
                    break;
            }
        }

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

