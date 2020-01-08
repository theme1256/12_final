package Controller;

import Model.*;
import Model.Fields.Field;
import gui_fields.*;
import gui_main.GUI;

import java.awt.*;

public class BoardController {
    private static int numberOfPlayers = 0;
    private static GUI gui;
    private static int startBalance = 0;
    private static Player[] players;
    private  static Board felter;

    public BoardController() {

        felter = new Board();
        createGUIFromFields(felter.fields);
    }
    private static void getNumberOfPlayers() {
        System.out.println("Indtast ønskede antal spillere");

        numberOfPlayers = gui.getUserInteger("Indtast ønskede antal spillere", 3, 6);
    }
    private void createGUIFromFields(Field[] felter) {
        GUI_Field[] gui_fields = new GUI_Field[felter.length];

        for (int i = 0; i < felter.length; i++) {

            Field felt = felter[i];
            if (felt.type.equals("ownable")) {
                GUI_Field field = new GUI_Street();
                field.setTitle(felt.navn);
                field.setBackGroundColor(felt.farve);
                field.setSubText(String.valueOf(felt.pris) + "kr");
                gui_fields[i] = field;
            }else if (felt.type.equals("skib")) {

                GUI_Field field = new GUI_Shipping();
                field.setTitle(felt.navn);
                field.setBackGroundColor(felt.farve);
                field.setSubText(String.valueOf(felt.pris) + "kr");
                gui_fields[i] = field;

            } else if (felt.type.equals("safe")) {
                GUI_Field field = new GUI_Refuge();
                field.setTitle(felt.navn);

                field.setSubText("Helle");
                gui_fields[i] = field;

            }else if (felt.type.equals("prison")) {
                GUI_Field field = new GUI_Jail();
                field.setTitle(felt.navn);
                field.setSubText("Fængsel");
                gui_fields[i] = field;

            }else if (felt.type.equals("toJail")) {
                GUI_Field field = new GUI_Jail();
                field.setTitle(felt.navn);
                field.setSubText("Gå i fængsel");
                gui_fields[i] = field;

            } else if (felt.type.equals("chance")) {
                GUI_Field field = new GUI_Chance("?", "CHANCE", " ", Color.BLACK, Color.WHITE);

                gui_fields[i] = field;

            }  else if (felt.type.equals("skat")) {
                GUI_Field field = new GUI_Tax();
                field.setTitle(felt.navn);
                field.setSubText(" ");
                gui_fields[i] = field;

            }else if (felt.type.equals("start")) {
                GUI_Field field = new GUI_Start();
                field.setTitle(felt.navn);
                field.setBackGroundColor(felt.farve);
                field.setSubText("");
                gui_fields[i] = field;

            }else if (felt.type.equals("bryggeri")) {
            GUI_Field field = new GUI_Brewery();
            field.setTitle(felt.navn);
            field.setBackGroundColor(felt.farve);
            field.setSubText(String.valueOf(felt.pris) + "kr");
            gui_fields[i] = field;

        }
        }
        gui = new GUI(gui_fields);
    }


private static void setStartBalance() {
        getNumberOfPlayers();
        players = new Player[numberOfPlayers];

        if ((numberOfPlayers >=3) && (numberOfPlayers <= 6))
            startBalance = 1500;
        else {
            System.out.println("Dette antal spillere er ikke understøttet");
            gui.showMessage("Dette antal spillere er ikke understøttet");
        }
}





}
