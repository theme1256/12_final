package Controller;

import Model.AccountStub;
import Model.Player;
import Model.PlayerStub;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerControllerStubTest {

    private PlayerStub[] players;
    private PlayerStub player = new PlayerStub(0,"Henrik");
    AccountStub accountStub;

    private DiceControllerStub dc = new DiceControllerStub();
    private PlayerControllerStub pc = new PlayerControllerStub(dc);

    private int startBalance = 3;
    private int numberOfPlayers = 3;

    @Test
    public void createPlayers() {

        players = new PlayerStub[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            players[i] = new PlayerStub(startBalance, pc);
        }
        assertEquals(3, players.length);

    }



    @Test
    public void handlePassStart() {
        player.passedStart = false;
        player.moveTo(2,true);
        assertEquals(0,player.getBalance());
        if(player.passedStart){
            player.updateBalance(200);
            assertEquals(200,player.getBalance());
            player.passedStart = false;
            assertTrue(false);

        }

    }

    @Test
    public void handleGetInJail() {
        System.out.println("Balance = "+ player.getBalance());
        player.moveTo(39);
        player.moveTo(35, true);
        if(player.currentFelt == 30){
            player.moveTo(10,false);
        }else {System.out.println("Du ryger ikke i fængsel og modtager 200kr");
            player.updateBalance(200);}

        System.out.println("Balance = "+ player.getBalance());
        assertFalse(player.getIsInJail());
        assertTrue(player.passedStart);


        player.moveTo(30,true);
        if(player.currentFelt == 30){
            System.out.println("Du ryger i fængsel og modtager ikke 200kr");
            player.moveTo(10,false);
            player.setIsInJail(true);

        }else {System.out.println("Du ryger ikke i fængsel");
        player.updateBalance(200);}

        System.out.println("Balance = "+ player.getBalance());
        assertTrue(player.getIsInJail());
        assertEquals(200,player.getBalance());

    }

    @Test
    public void handeGetOutOfJail() {
    }
}
