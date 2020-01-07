package Controller;

import Model.*;
import Model.Fields.Field;
import Model.Fields.StartField;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_fields.GUI_Street;
import gui_main.GUI;

public class BoardController {
    private Field[] boardList = new Field[40];

    public BoardController() {
        boardList[0] = new StartField();


    }
}
