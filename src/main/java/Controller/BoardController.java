package Controller;

import Model.*;
import gui_fields.GUI_Field;
import gui_fields.GUI_Jail;
import gui_fields.GUI_Player;
import gui_fields.GUI_Street;
import gui_main.GUI;

public class BoardController {
    public static String lang = "da";
    public static int playerCount;

    public BoardController() {
        Deck deck = new Deck();

        Board board = new Board();
        GUI gui = new GUI(board.getGUIFields());
    }
}
