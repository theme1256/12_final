package Controller;

import Model.*;
import Model.Fields.Field;
import gui_fields.*;
import gui_main.GUI;

public class BoardController {

private  static GUI gui;
private  static Board felter;

    public BoardController() {

        felter = new Board();
        createGUIFromFields(felter.fields);
    }

    private void createGUIFromFields(Field[] felter) {
        GUI_Field[] gui_fields = new GUI_Field[felter.length];

        for (int i = 0; i < felter.length; i++) {

            Field felt = felter[i];
            if (felt.type.equals("ownable")) {
                GUI_Field field = new GUI_Street();
                field.setTitle(felt.navn);
                field.setBackGroundColor(felt.farve);
                field.setSubText(String.valueOf(felt.pris));
                gui_fields[i] = field;
            }else if (felt.type.equals("skib")) {

                GUI_Field field = new GUI_Shipping();
                field.setTitle(felt.navn);
                field.setBackGroundColor(felt.farve);
                field.setSubText(String.valueOf(felt.pris));
                gui_fields[i] = field;

            } else if (felt.type.equals("safe")) {
                GUI_Field field = new GUI_Refuge();
                field.setTitle(felt.navn);
                field.setBackGroundColor(felt.farve);
                field.setSubText(String.valueOf(felt.pris));
                gui_fields[i] = field;

            }else if (felt.type.equals("prison")) {
                GUI_Field field = new GUI_Jail();
                field.setTitle(felt.navn);
                field.setBackGroundColor(felt.farve);
                field.setSubText(String.valueOf(felt.pris));
                gui_fields[i] = field;

            } else if (felt.type.equals("chance")) {
                GUI_Field field = new GUI_Chance();

                field.setTitle(felt.navn);
                field.setBackGroundColor(felt.farve);
                field.setSubText(String.valueOf(felt.pris));
                gui_fields[i] = field;

            }  else if (felt.type.equals("skat")) {
                GUI_Field field = new GUI_Tax();
                field.setTitle(felt.navn);
                field.setBackGroundColor(felt.farve);
                field.setSubText(String.valueOf(felt.pris));
                gui_fields[i] = field;

            }else if (felt.type.equals("start")) {
                GUI_Field field = new GUI_Start();
                field.setTitle(felt.navn);
                field.setBackGroundColor(felt.farve);
                field.setSubText(String.valueOf(felt.pris));
                gui_fields[i] = field;

            }else if (felt.type.equals("bryggeri")) {
            GUI_Field field = new GUI_Brewery();
            field.setTitle(felt.navn);
            field.setBackGroundColor(felt.farve);
            field.setSubText(String.valueOf(felt.pris));
            gui_fields[i] = field;

        }
        }
        gui = new GUI(gui_fields);
    }


}
