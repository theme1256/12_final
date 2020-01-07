package Model;

public class Account {
    public int balance;

    Account(int startBalance){
        this.balance = startBalance;
    }

    public int updateBalance(int diff){
        this.balance += diff;
        return this.balance;
    }
}
