package Stub.Model;

import Stub.Controller.PlayerControllerStub;

public class PlayerStub {
    public  AccountStub account;
    public String playerName;

    public boolean passedStart = false;
    private boolean jailPass = false;
    public int turnsInJail = 0;
    public boolean isInJail = false;
    private boolean hasGivenUp = false;

    public int currentFelt = 0;
    public int previousFelt = 0;


    public PlayerStub(int startBalance, PlayerControllerStub pc) {
        username();
        account = new AccountStub(startBalance);
    }

    public PlayerStub(int startBalance, String username) {
        account = new AccountStub(startBalance);
        playerName = username;
    }
    private void username() {
        playerName = "Henrik";
    }

    public String getPlayerName() {
        return playerName;
    }

    public void updateBalance(int diff) {
        account.updateBalance(diff);
    }

    public int getBalance() {
        return account.balance;
    }
    public void move(int amount) {
        this.previousFelt = this.currentFelt;
        this.currentFelt += amount;
        if(this.currentFelt >= 40) {
            this.currentFelt -= 40;
            this.passedStart = true;
        }
    }

    public void moveTo(int to) {
        this.previousFelt = this.currentFelt;
        this.currentFelt = to;

    }

    public void moveTo(int to, boolean passStart) {
        this.previousFelt = this.currentFelt;
        this.currentFelt = to;
        if(this.currentFelt < this.previousFelt && passStart)
            this.passedStart = true;
    }


    public int getTurnsInJail() {
        return this.turnsInJail;
    }
    public void addTurnInJail() {
        this.turnsInJail++;
        this.isInJail = true;
    }
    public void resetTurnsInJail() {
        this.turnsInJail = 0;
        this.isInJail = false;
    }

    public boolean getJailPass() {
        return this.jailPass;
    }
    public void setJailPass(boolean inp) {
        this.jailPass = inp;
    }
    public boolean getIsInJail(){
        return this.isInJail;
    }

    public void setIsInJail(boolean inp){
        this.isInJail = inp;
    }


    @Override
    public String toString() {
        return playerName;
    }
}




