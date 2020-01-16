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
                        {3, 3}, // P1 Start -> Roskildevej (køb)
                        {1, 2}, // P1 Roskildevej -> Allégade (køb)
                        {3, 2}, // P2 Start -> Øresund (køb)
                        {2, 2}, // P3 Start -> Skat
                        {3, 3}, // P3 Skat -> Fængsel
                        {1, 6}, // P3 Fængsel -> Prøv Lykken (Money(2))
                        {1, 1}, // P4 Start -> Prøv lykken (Money(3))
                        {1, 2}, // P4 Prøv-lykken -> Øresund (betal leje)
                        {6, 6}, // P5 Start -> Tuborg (køb)
                        {6, 6}, // P5 Tuborg -> Grønningen
                        {1, 3}, // P5 Grønningen -> Carlsberg (køb)
                        {1, 2}, // P6 Start -> Hvidovre
                        {6, 6}, // P1 Allégade -> Trianglen (køb)
                        {1, 1}, // P1 Trianglen -> Østerbrogade (køb)
                        {3, 4}, // P1 Østerbrogade -> Gå i fængsel
                        {5, 5}, // P2 Øresund -> D.F.D.S (køb)
                        {5, 5}, // P2 D.F.D.S -> Ø.K. (køb)
                        {1, 2}, // P2 Ø.K. -> Carlsberg (betal leje)
                        {1, 2}, // P3 Prøv Lykken -> Helle
                        {5, 5}, // P4 Øresund -> D.F.D.S (betal leje)
                        {2, 4}, // P4 D.F.D.S -> Trianglen (betal leje)
                        {5, 5}, // P5 Carlsberg -> Skat
                        {1, 3}, // P5 Skat -> Prøv lykken (Money(4))
                        {1, 1}, // P6 Hvidovre -> Øresund (betal leje)
                        {1, 3}, // P6 Øresund -> Allégade (betal leje)
                    });
                    chanceCardController = new ChanceCardController(fieldController, gui, true, new int[]{2, 3, 4});
                    break;
                case "TC3":
                    diceController.overrideShakes(new int[][]{
                        {1, 3}, // P1 Start -> Skat
                        {3, 3}, // P2 Start -> Roskildevej
                        {1, 2}, // P2 Roskildevej -> Allégade
                        {3, 3}, // P3 Start -> Roskildevej
                        {2, 2}, // P3 Roskildevej -> Fængsel
                        {1, 2}, // P3 Fængsel -> Bülowsvej
                        {3, 3}, // P4 Start -> Roskildevej
                        {2, 2}, // P4 Roskildevej -> Fængsel
                        {5, 5}, // P4 Fængsel -> Helle -> Fængsel
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
                        {1, 1}, // P1 Start -> Prøv lykken (free-card)
                        {1, 1}, // P1 Prøv lykken -> Skat
                        {1, 1}, // P1 Skat -> Roskildevej -> Fængsel
                        {1, 1}, // P2 Start -> Prøv lykken (free-card)
                        {1, 1}, // P2 Prøv lykken -> Skat
                        {1, 1}, // P2 Skat -> Roskildevej -> Fængsel
                        {6, 6}, // P3 Start -> Tuborg
                        {6, 6}, // P3 Tuborg -> Grønningen
                        {4, 2}, // P3 Grønningen -> Fængsel
                        {6, 6}, // P4 Start -> Tuborg
                        {6, 6}, // P4 Tuborg -> Grønningen
                        {4, 2}, // P4 Grønningen -> Fængsel
                        {4, 6}, // P1 Fængsel -> Helle
                        {4, 6}, // P2 Fængsel -> Helle
                        {3, 3}, // P3 Fængsel -> Bernstorffsvej
                        {1, 3}, // P3 Bernstorffsvej -> Helle
                        {1, 2}, // P4 Fængsel
                        {1, 2}, // P1 Helle -> Grønningen
                        {1, 2}, // P2 Helle -> Grønningen
                        {1, 2}, // P3 Helle -> Grønningen
                        {1, 2}, // P4 Fængsel
                        {1, 2}, // P1 Grønningen -> Kgs.  Nytorv
                        {1, 2}, // P2 Grønningen -> Kgs.  Nytorv
                        {1, 2}, // P3 Grønningen -> Kgs.  Nytorv
                        {4, 5}, // P4 Fængsel -> Strandvej
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

