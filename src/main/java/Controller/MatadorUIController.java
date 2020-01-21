package Controller;

import gui_fields.*;
import gui_main.GUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatadorUIController {
    public static void main(String[] args) {
        // Opretter fieldcontroller og henter GUI-versioner af felterne ud
        FieldController fieldController = new FieldController();
        GUI_Field[] gui_fields = fieldController.createGUIFromFields();

        // Opret GUI fra felter
        GUI gui = new GUI(gui_fields);

        // Opret en chancecardcontroller
        ChanceCardController chanceCardController = null;

        // Opret en dicecontroller
        DiceController diceController = new DiceController(gui);

        // Opretter playercontroller
        PlayerController playerController = null;

        // En variabel til at fortælle GameController, om den skal sørge for at brugere oprettes
        boolean createPlayers = true;

        // Hvis der er givet et eller flere parametere, da programmet startede
        if (args.length > 0) {
            // Reager på det første parameter
            switch (args[0]) {
                case "Eksamen":
                    // Giver dicecontroller en liste med slag, som shaker skal slå
                    diceController.overrideShakes(new int[][]{
                        {1, 1}, // Theis, Start -> Prøv lykken (30) -> Øresund
                        {5, 5}, // Theis, Øresund -> D.F.D.S.
                        {5, 6}, // Theis, D.F.D.S. -> Bredgade
                        {3, 4}, // Amer, Start -> Prøv lykken (24) -> Rådhuspladsen
                        {3, 3}, // Zahra, Start -> Skat
                        {1, 1}, // Zahra, Skat -> Valby Langgade
                        {1, 2}, // Zahra, Valby Langgade -> Frederikberg Alle
                        {6, 6}, // Christian, Start -> Tuborg
                        {5, 4}, // Christian, Tuborg -> Trianglen
                        {1, 1}, // Christine, Start -> Prøv lykken (26) -> Øresund
                        {1, 1}, // Christine, Øresund -> Prøv lykken (31) -> D.F.D.S
                        {2, 3}, // Christine, D.F.D.S -> Helle -> Giv op
                        {1, 1}, // Ismail, Start -> Prøv lykken (0)
                        {3, 2}, // Ismail, Prøv lykken -> Prøv lykken (21) -> Fængsel
                        // Runde
                        {2, 2}, // Theis, Bredgade -> Gå i fængsel -> Fængsel (ingen ekstra-tur)
                        {1, 3}, // Amer, Rådhuspladsen -> Hvidovre -> giv op
                        {1, 1}, // Zahra, Frederikberg Alle -> Bülowsvej
                        {3, 3}, // Zahra, Bülowsvej -> Strandvej
                        {1, 1}, // Zahra, Strandvej -> Trianglen -> Fængsel
                        {1, 1}, // Christian, Trianglen -> Østerbrogade
                        {4, 6}, // Christian, Østerbrogade -> Prøv lykken (28) -> Grønningen
                        {4, 6}, // Ismail, Fængsel -> Helle -> giv op
                        // Runde
                        {2, 1}, // Theis, i fængsel
                        {5, 5}, // Zahra, Fængsel -> Helle -> giv op
                        {1, 3}, // Christian, Grønningen -> Carlsberg
                        // Runde
                        {2, 1}, // Theis, i fængsel
                        {1, 2}, // Christian, Carlsberg -> Amagertorv
                        // Runde
                        {2, 3}, // Theis, i fængsel (betal) -> D.F.D.S.
                    });
                    // Overskriver chancecardcontroller med en fast rækkefølge
                    chanceCardController = new ChanceCardController(fieldController, gui, true, new int[]{30, 24, 26, 31, 0, 21, 28});
                    // Opretter spillere
                    playerController = new PlayerController(gui, diceController, new String[]{"Theis", "Amer", "Zahra", "Christian", "Christine", "Ismail"});
                    // Overskriver at GameController skal oprette spillere
                    createPlayers = false;
                    break;
                case "TC2":
                    // Giver dicecontroller en liste med slag, som shaker skal slå
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
                    // Overskriver chancecardcontroller med en fast rækkefølge
                    chanceCardController = new ChanceCardController(fieldController, gui, true, new int[]{2, 3, 4});
                    break;
                case "TC3":
                    // Giver dicecontroller en liste med slag, som shaker skal slå
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
                    // Giver dicecontroller en liste med slag, som shaker skal slå
                    diceController.overrideShakes(new int[][]{
                        {3, 3}, // P1 Start -> Roskildevej
                        {1, 1}, // P1 Roskildevej -> Valby Langgade
                        {5, 4}, // P1 Valby Langgade -> Chance (move(29)) -> Start
                        {2, 3}, // P2 Start -> Øresund
                        {6, 6}, // P3 Start -> Tuborg
                        {3, 1}, // P3 Tuborg -> Bernstorffsvej
                        {5, 4}, // P1 Start -> Allégade
                    });
                    // Overskriver chancecardcontroller med en fast rækkefølge
                    chanceCardController = new ChanceCardController(fieldController, gui, true, new int[]{29});
                    break;
                case "TC5":
                    // Giver dicecontroller en liste med slag, som shaker skal slå
                    diceController.overrideShakes(new int[][]{
                        {3, 4},  //p1 Start -> Prøv lykken -> Rådhuspladsen
                        {6, 6},  //p2 Start -> Bryggeri + ekstra tur
                        {1, 2},  //p2 Bryggeri -> D.F.D.S
                        {1, 6},  //p3 Start -> Chancekort -> Ryk til nærmeste skib og betal dobbelt husleje
                        {1, 2},  //p1 Rådhuspladsen -> Chancekort -> Gå i fængsel (Modtager ikke 200kr)
                    });
                    // Overskriver chancecardcontroller med en fast rækkefølge
                    chanceCardController = new ChanceCardController(fieldController, gui, true, new int[]{24,30,27});
                    break;
                case "TC6":
                    // Giver dicecontroller en liste med slag, som shaker skal slå
                    diceController.overrideShakes(new int[][]{
                        {3, 3}, // P1 Start -> Roskildevej
                        {1, 1}, // P1 Roskildevej -> Valby Langgade
                        {5, 4}, // P1 Valby Langgade -> Chance (move(29)) -> Start
                        {2, 3}, // P2 Start -> Øresund
                        {6, 6}, // P3 Start -> Tuborg
                        {3, 1}, // P3 Tuborg -> Bernstorffsvej
                        {5, 4}, // P1 Start -> Allégade
                        {2, 2}, // P2 Øresund -> Allégade
                        {4, 4}, // P2 Allégade -> Chance (move(26)) -> Øresund
                        {1, 3}, // P2 Øresund -> Allégade
                        {1, 3}, // P3 Bernstorffsvej -> Helle
                        {5, 6}, // P1 Allégade -> Helle
                        {4, 4}, // P2 Allégade -> Chance (move(24)) -> Rådhuspladsen
                        {4, 6}, // P2 Rådhuspladsen -> Allégade
                    });
                    // Overskriver chancecardcontroller med en fast rækkefølge
                    chanceCardController = new ChanceCardController(fieldController, gui, true, new int[]{29, 26, 24});
                    break;
                case "TC7":
                    // Giver dicecontroller en liste med slag, som shaker skal slå
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
                    // Overskriver chancecardcontroller, så kort ikke er blandet
                    chanceCardController = new ChanceCardController(fieldController, gui, true);
                    break;
                default:
                    System.out.println("Den testcase blev ikke genkendt, fik: " + args[0]);
                    break;
            }
        }
        if (playerController == null) {
            playerController= new PlayerController(gui, diceController);
        }
        if (chanceCardController == null) {
            chanceCardController = new ChanceCardController(fieldController, gui);
        }

        // Opretter gamecontroller, som starter spillet
        GameController gameController = new GameController(gui, chanceCardController, diceController, playerController, fieldController, createPlayers);
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
     *
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

