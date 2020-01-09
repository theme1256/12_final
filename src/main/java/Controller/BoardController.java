package Controller;

import Model.ChanceDeck;

import Model.Cards.*;
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
    private static Board felter;
    private static Shaker shaker;
    private static ChanceDeck chanceDeck;

    public BoardController() {

        initVars();
        boolean playing = true;
        while (playing) {
            for (Player player : players) {
                playing = handleRound(player);
                if(!playing)
                    break;
            }
        }

    }

    private static void initVars() {

        felter = new Board();
        createGUIFromFields(felter.fields);
        shaker = new Shaker(2);
        chanceDeck = new ChanceDeck();
        chanceDeck.blandkort();
        while (startBalance == 0) {
            setStartBalance();
        }

        for (int i = 0; i < numberOfPlayers; i++) {
            players[i] = new Player(gui, startBalance, i);

            gui.showMessage("Navn: " + players[i].playerName + "\nStart-balance: " + players[i].account.balance);
        }
    }

    private static void createGUIFromFields(Field[] felter) {
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

    private static void getNumberOfPlayers() {
        System.out.println("Indtast ønskede antal spillere");

        numberOfPlayers = gui.getUserInteger("Indtast ønskede antal spillere", 3, 6);
    }

    private static void setStartBalance() {
        getNumberOfPlayers();
        players = new Player[numberOfPlayers];

        if ((numberOfPlayers >= 3) && (numberOfPlayers <= 6))
            startBalance = 1500;
        else {
            System.out.println("Dette antal spillere er ikke understøttet");
            gui.showMessage("Dette antal spillere er ikke understøttet");
        }
    }

    private static void handleChancekort(Player player) {
        if(felter.fields[player.currentFelt].getName().equals("Prøv lykken")) {

            Kort chanceKort = chanceDeck.traekkort();

            System.out.println(chanceKort);
            chanceKort.action(player, gui);

        }
    }

    private static boolean handleRound(Player player) {

        // Slå med terningen når spilleren trykker
        gui.getUserButtonPressed(player + ", tryk enter/knappen for at slå", "SLÅ");

        // Vis resultatet og opdater felt
        int[] val = shaker.shake();
        gui.setDice(val[0],val[1]);
        int value = val[0] + val[1];

        player.move(value);

        handleGetInJail(player);
        //Håndterer chancekort
       handleChancekort(player);

       if((val[0]==1 && val[1] ==1)||(val[0]==2 && val[1] ==2) ||(val[0]==3 && val[1] ==3) || (val[0]==4 && val[1] ==4) || (val[0]==5 && val[1] ==5) ||(val[0]==6 && val[1] ==6)){

           gui.getUserButtonPressed(player + ", får en ekstra tur!", "FEDT!");
           gui.getUserButtonPressed(player + ", tryk enter/knappen for at slå", "SLÅ");
           int[] val1 = shaker.shake();
           gui.setDice(val1[0],val1[1]);
           int value1 = val1[0] + val1[1];
           player.move(value1);
           handleGetInJail(player);

           if((val1[0]==1 && val1[1] ==1)||(val1[0]==2 && val1[1] ==2) ||(val1[0]==3 && val1[1] ==3) || (val1[0]==4 && val1[1] ==4) || (val1[0]==5 && val1[1] ==5) ||(val1[0]==6 && val1[1] ==6)){
               gui.getUserButtonPressed(player + ", får en ekstra tur!", "FEDT!");
               gui.getUserButtonPressed(player + ", tryk enter/knappen for at slå", "SLÅ");

               System.out.println("Tillykke du får en ekstra tur");
               int[] val2 = shaker.shake();
               gui.setDice(val2[0],val2[1]);
               int value2 = val2[0] + val2[1];
               player.move(value2);
               handleGetInJail(player);

               if((val2[0]==1 && val2[1] ==1)||(val2[0]==2 && val2[1] ==2) ||(val2[0]==3 && val2[1] ==3) || (val2[0]==4 && val2[1] ==4) || (val2[0]==5 && val2[1] ==5) ||(val2[0]==6 && val2[1] ==6)){
                    gui.getUserButtonPressed(player + ", er for heldig til det kan være rigtigt! Du ryger i fængsel", "ØV!");
                    player.moveTo(10,false);

               }

           }
       }

        //Hvis en spiller lander på et felt over felt 39; så starterde forfra på brætte
        handlePassStart(player);

        return  player.account.balance > 0;
    }

    private static void handlePassStart(Player player) {
        if (player.passedStart == true) {
            gui.getUserButtonPressed(player + " passerer start og modtager 200 kr", "OK");
            player.updateBalance(200);
            player.passedStart = false;

        }
    }

    private static void handleGetInJail(Player player) {
        if(player.currentFelt == 31) {
            gui.getUserButtonPressed("Du er røget i fængsel!", "ØV");
            player.moveTo(10,false);

        }
    }





}
