package View;


import Controller.GameController;
import Model.BoardsFields;
import gui_fields.*;
import gui_main.GUI;

import java.awt.*;

public class MatadorUI {

    public static GUI gui;
    public static BoardsFields felter;

    public static void main(String[] args) {
        initGame();
    }

    private static void initGame() {
        felter = new BoardsFields();
        createGUIFromFields(felter.fields);
        GameController.initVars();
        new GameController();
    }

    private static void createGUIFromFields(Model.Fields.Field[] felter) {
        GUI_Field[] gui_fields = new GUI_Field[felter.length];
        for (int i = 0; i < felter.length; i++) {


            if (felter[i].getNr() == 2 || felter[i].getNr() == 8 || felter[i].getNr() == 18 || felter[i].getNr() == 23 || felter[i].getNr() == 34 || felter[i].getNr() == 37) {
                GUI_Field field = new GUI_Chance("?", "CHANCE", " ", Color.BLACK, Color.WHITE);

                gui_fields[i] = field;
            } else if (felter[i].getNr() == 5 || felter[i].getNr() == 39) {
                GUI_Field field = new GUI_Tax();
                field.setTitle(felter[i].getName());
                field.setSubText(" ");
                field.setDescription(felter[i].getDescription());
                gui_fields[i] = field;

            } else if (felter[i].getNr() == 6 || felter[i].getNr() == 16 || felter[i].getNr() == 26 || felter[i].getNr() == 36) {
                GUI_Field field = new GUI_Shipping();
                field.setTitle(felter[i].getName());
                field.setSubText(felter[i].getPrice() + "kr");
                gui_fields[i] = field;

            } else if (felter[i].getNr() == 11 || felter[i].getNr() == 31) {
                GUI_Field field = new GUI_Jail();
                field.setTitle(felter[i].getName());
                field.setSubText("Fængsel");
                gui_fields[i] = field;
            } else if (felter[i].getNr() == 13 || felter[i].getNr() == 29) {
                GUI_Field field = new GUI_Brewery();
                field.setTitle(felter[i].getName());
                field.setSubText(felter[i].getPrice() + "kr");
                gui_fields[i] = field;

            } else if (felter[i].getNr() == 21) {
                GUI_Field field = new GUI_Refuge();
                field.setTitle(felter[i].getName());
                field.setSubText("Helle");
                gui_fields[i] = field;

            } else if (felter[i].getNr() == 1) {

                GUI_Field field = new GUI_Start();
                field.setTitle(felter[i].getName());
                field.setSubText("");
                gui_fields[i] = field;
            } else {

                GUI_Field field = new GUI_Street();
                field.setTitle(felter[i].getName());
                field.setBackGroundColor(felter[i].getColor());
                field.setSubText(String.valueOf(felter[i].getPrice() + "kr"));
                gui_fields[i] = field;
            }
        }
        gui = new GUI(gui_fields);
    }


}

