package Model.Cards;



import java.util.Arrays;
import java.util.Random;

public class ChanceKortDeck {
    private int cardNumber;
    private Kort kort = new Kort(0);
    private Kort[] korts;

    public ChanceKortDeck() {
        this.korts = new Kort[32];
        cards();
    }

    public void cards() {
        korts[0] = new Jail(0);
        korts[1] = new Jail(1);
        korts[2] = new Money(2);
        korts[3] = new Money(3);
        korts[4] = new Money(4);
        korts[5] = new Money(5);
        korts[6] = new Money(6);
        korts[7] = new Money(7);
        korts[8] = new Money(8);
        korts[9] = new Money(9);
        korts[10] = new Money(10);
        korts[11] = new Money(11);
        korts[12] = new Money(12);
        korts[13] = new Money(13);
        korts[14] = new Money(14);
        korts[15] = new Money(15);
        korts[16] = new Money(16);
        korts[17] = new Money_special(17);
        korts[18] = new Money_special(18);
        korts[19] = new Money_special(19);
        korts[20] = new Money_special(20);
        korts[21] = new Move(21);
        korts[22] = new Move(22);
        korts[23] = new Move(23);
        korts[24] = new Move(24);
        korts[25] = new Move(25);
        korts[26] = new Move(26);
        korts[27] = new Move(27);
        korts[28] = new Move(28);
        korts[29] = new Move(29);
        korts[30] = new Move_special(30);
        korts[31] = new Move_special(31);
    }

    public Kort traekkort() {
        Kort trukket = korts[0];
        for (int i = 0; i < korts.length - 1; i++) {
            korts[i] = korts[i + 1];
        }
        korts[korts.length - 1] = trukket;
        return trukket;
    }

    public void blandkort() {
        int index;
        Kort temp;
        Random random = new Random();
        for (int i = korts.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            temp = korts[index];
            korts[index] = korts[i];
            korts[i] = temp;
        }
        //Collections.shuffle(Arrays.asList(kort));
    }

    public String toString() {
        return Arrays.asList(korts).toString();
    }
}