package Controller;

import Model.*;
import Model.Fields.*;
import gui_fields.*;

import java.awt.*;

public class FieldController {
    private BoardsFields board;
    private Model.Fields.Field[] fields;

    public FieldController() {
        board = new BoardsFields();
        fields = board.getFields();
    }

    public Model.Fields.Field[] getFields() {
        return fields;
    }
    public Model.Fields.Field getField(int i) {
        return fields[i];
    }

    public GUI_Field[] createGUIFromFields() {
        GUI_Field[] gui_fields = new GUI_Field[fields.length];
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getNr() == 3 || fields[i].getNr() == 8 || fields[i].getNr() == 18 || fields[i].getNr() == 23 || fields[i].getNr() == 34 || fields[i].getNr() == 37) {
                GUI_Field field = new GUI_Chance("?", "CHANCE", " ", Color.BLACK, Color.WHITE);
                gui_fields[i] = field;
            } else if (fields[i].getNr() == 5 || fields[i].getNr() == 39) {
                GUI_Field field = new GUI_Tax();
                field.setTitle(fields[i].getName());
                field.setSubText(" ");
                field.setDescription(fields[i].getDescription());
                gui_fields[i] = field;
            } else if (fields[i].getNr() == 6 || fields[i].getNr() == 16 || fields[i].getNr() == 26 || fields[i].getNr() == 36) {
                GUI_Field field = new GUI_Shipping();
                field.setTitle(fields[i].getName());
                field.setSubText(fields[i].getPrice() + "kr");
                gui_fields[i] = field;
            } else if (fields[i].getNr() == 11 || fields[i].getNr() == 31) {
                GUI_Field field = new GUI_Jail();
                field.setTitle(fields[i].getName());
                field.setSubText("Fængsel");
                gui_fields[i] = field;
            } else if (fields[i].getNr() == 13 || fields[i].getNr() == 29) {
                GUI_Field field = new GUI_Brewery();
                field.setTitle(fields[i].getName());
                field.setSubText(fields[i].getPrice() + "kr");
                gui_fields[i] = field;
            } else if (fields[i].getNr() == 21) {
                GUI_Field field = new GUI_Refuge();
                field.setTitle(fields[i].getName());
                field.setSubText("Helle");
                gui_fields[i] = field;
            } else if (fields[i].getNr() == 1) {
                GUI_Field field = new GUI_Start();
                field.setTitle(fields[i].getName());
                field.setSubText("");
                gui_fields[i] = field;
            } else {
                GUI_Field field = new GUI_Street();
                field.setTitle(fields[i].getName());
                field.setBackGroundColor(fields[i].getColor());
                field.setSubText(String.valueOf(fields[i].getPrice() + "kr"));
                gui_fields[i] = field;
            }
        }
        return gui_fields;
    }
}
