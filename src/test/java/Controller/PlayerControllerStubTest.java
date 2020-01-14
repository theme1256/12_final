package Controller;

import Model.AccountStub;
import Model.Player;
import Model.PlayerStub;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerControllerStubTest {

    PlayerStub[] players;
    AccountStub accountStub;

    DiceControllerStub dc = new DiceControllerStub();
    PlayerControllerStub pc = new PlayerControllerStub(dc);

    int startBalance = 3;
    int numberOfPlayers = 3;

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
    }

    @Test
    public void handleGetInJail() {
    }

    @Test
    public void handeGetOutOfJail() {
    }
}
