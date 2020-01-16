package Model;



import Model.Cards.*;

import java.util.Arrays;
import java.util.Random;

public class ChanceDeck {
    private ChanceCards[] cards;

    public ChanceDeck() {
        this.cards = new ChanceCards[32];
        cards();
    }

    public void cards() {
        cards[0] = new Jail(0);
        cards[1] = new Jail(1);
        cards[2] = new Money(2);
        cards[3] = new Money(3);
        cards[4] = new Money(4);
        cards[5] = new Money(5);
        cards[6] = new Money(6);
        cards[7] = new Money(7);
        cards[8] = new Money(8);
        cards[9] = new Money(9);
        cards[10] = new Money(10);
        cards[11] = new Money(11);
        cards[12] = new Money(12);
        cards[13] = new Money(13);
        cards[14] = new Money(14);
        cards[15] = new Money(15);
        cards[16] = new Money(16);
        cards[17] = new Money_special(17);
        cards[18] = new Money_special(18);
        cards[19] = new Money_special(19);
        cards[20] = new Money_special(20);
        cards[21] = new Move(21);
        cards[22] = new Move(22);
        cards[23] = new Move(23);
        cards[24] = new Move(24);
        cards[25] = new Move(25);
        cards[26] = new Move(26);
        cards[27] = new Move(27);
        cards[28] = new Move(28);
        cards[29] = new Move(29);
        cards[30] = new Move_special(30);
        cards[31] = new Move_special(31);
    }

    public ChanceCards draw() {
        ChanceCards trukket = cards[0];
        for (int i = 0; i < cards.length - 1; i++) {
            cards[i] = cards[i + 1];

        }
        cards[cards.length - 1] = trukket;
        return trukket;
    }


    public void shuffle() {
        int index;
        ChanceCards temp;
        Random random = new Random();
        for (int i = cards.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            temp = cards[index];
            cards[index] = cards[i];
            cards[i] = temp;
        }
    }

    public void setOrder(int[] newOrder) {
        ChanceCards[] tmp = new ChanceCards[32];
        for (int i = 0; i < newOrder.length; i++) {
            tmp[i] = this.cards[newOrder[i]];
        }
        if (newOrder.length < cards.length) {
            int i = newOrder.length;
            for (ChanceCards card : this.cards) {
                if (!this.contains(tmp, card)) {
                    tmp[i] = card;
                    i++;
                }
            }
        }
        this.cards = tmp;
    }

    /**
     * Finder ud af om et chancekort er i et array af chancekort
     *
     * @param array Det array der skal ledes i
     * @param v Det kort der skal findes
     * @return Om kortet blev fundet
     */
    public boolean contains(ChanceCards[] array, ChanceCards v) {
        boolean result = false;
        for(ChanceCards i : array){
            if(i.getCardNumber() == v.getCardNumber()){
                result = true;
                break;
            }
        }
        return result;
    }

    public String toString() {
        return Arrays.asList(cards).toString();
    }
}