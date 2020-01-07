package Model;

public class Account {
    int startBalance;
    int updateBalance;

    //Constructor
    public Account (int startBalance){
        this.startBalance = startBalance;
    }


    // get and set methods
    public int getUpdateBalance() {
        return updateBalance;
    }

    public void setUpdateBalance(int updateBalance) {
        this.updateBalance = updateBalance;
    }
}
