package Controller;

import Model.*;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_fields.GUI_Street;
import gui_main.GUI;

public class BoardController {
    private static int numberOfPlayers = 0;
    private static GUI gui;
    private static int startBalance = 0;
    private static Player[] players;

    public BoardController() {
    }
g
private static void getNumberOfPlayers() {
        System.out.println("Indtast ønskede antal spillere");

        numberOfPlayers = gui.getUserInteger("Indtast ønskede antal spillere", 3, 6);
}

private static void getNumberOfPlayers() {
        getNumberOfPlayers();
        players =  Player[numberOfPlayers];

        if ((numberOfPlayers >=3) && (numberOfPlayers <= 6))
            startBalance = 1500;
        else        {
            System.out.println("Dette antal spillere er ikke understøttet");
            gui.showMessage("Dette antal spillere er ikke understøttet");
        }
}



}
