package Model;

public class AccountStub {
    public int balance;

    AccountStub(int startBalance){
        this.balance = startBalance;
    }

    public int updateBalance(int diff){
        this.balance += diff;
        return this.balance;
    }

}
