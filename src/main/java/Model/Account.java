package Model;

public class Account {
    public int balance;

    Account(int startBalance){
        this.balance = startBalance;
    }

    /**
     * Retter balancen med det givne tal, om det er positiv eller negativt
     *
     * @param diff Det der skal rettes med
     * @return Den opdaterede balance
     */
    public int updateBalance(int diff){
        this.balance += diff;
        return this.balance;
    }
}
