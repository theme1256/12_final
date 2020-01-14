package Controller;


import Model.Player;
import gui_main.GUI;
import org.junit.Test;

import java.util.zip.DeflaterInputStream;

import static org.junit.Assert.*;
public class PlayerControllerTest {

    GUI gui = new GUI();
    DiceController dc = new DiceController(gui);
    PlayerController pc = new PlayerController(gui, dc);
    Player[] players;
    Player player = new Player(gui, 0,pc);

    @Test
    public void createPlayers() {

        PlayerController pc = new PlayerController(gui, dc);
        int numberOfPlayers = 3;
        int startBalance = 0;

        players = new Player[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            players[i] = new Player(this.gui, startBalance, pc);
        }

        assertEquals(3,players.length);

    }

    @Test
    public void handlePassStart() {
        int balance = player.getBalance();
       assertEquals(0, balance);
       //assertTrue(player.passedStart);
      /*  if(player.passedStart){
            player.updateBalance(200);
            player.passedStart = false;

            assertEquals(200,balance);
        }
        assertFalse(player.passedStart);
*/
    }

    @Test
    public void handleGetInJail() {
    }

    @Test
    public void handeGetOutOfJail() {
    }
}
