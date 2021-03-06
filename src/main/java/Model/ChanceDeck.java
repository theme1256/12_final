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

    /**
     * Trækker det første kort i arrayet, kopierer det til enden af arrayet og returnerer det til den der skal bruge det
     *
     * @return Det kort der blev trukket
     */
    public ChanceCards draw() {
        // Tager en kopi af det første kort i array'et
        ChanceCards trukket = this.cards[0];
        // Løber kortene igennem og flytter dem med en
        for (int i = 0; i < this.cards.length - 1; i++) {
            this.cards[i] = this.cards[i + 1];
        }
        // Tilføjer det først kort til enden
        this.cards[this.cards.length - 1] = trukket;
        // Returnerer det første kort
        return trukket;
    }

    /**
     * Blander rækkefølgen, som kortene trækkes i
     */
    public void shuffle() {
        int index;
        // Laver et sted at opbevare et kort, midlertidigt
        ChanceCards temp;
        Random random = new Random();
        // Løber alle kortene igennem baglæns
        for (int i = cards.length - 1; i > 0; i--) {
            // Find et tilfældigt tal
            index = random.nextInt(i + 1);
            // Lav en midlertidig kopi af et tilfældigt kort
            temp = cards[index];
            // Erstart det tilfældige kort med det nuværrende
            cards[index] = cards[i];
            // Erstart det nuværrende med det tilfældige kort
            cards[i] = temp;
        }
    }

    /**
     * Overskiver rækkefølgen, som chancekortene er i.
     * Hvis der ikke gives en rækkefølge, som er kortere end antallet af kort, så flyttes de resterende kort til efter de definerede
     *
     * @param newOrder Et array af numre, i den rækkefølge de skal trækkes
     */
    public void setOrder(int[] newOrder) {
        // Opretter et midlertidigt array til at holde den nye rækkefølge på kortene
        ChanceCards[] tmp = new ChanceCards[this.cards.length];

        // For alle de tal der er givet i "newOrder", kopier fra den usorterede liste
        for (int i = 0; i < newOrder.length; i++) {
            tmp[i] = this.cards[newOrder[i]];
        }

        // Hvis der ikke er givet en ny rækkefølge, som er lige så lang som det originale dæk
        if (newOrder.length < this.cards.length) {
            // Den placering, som der skal sættes ind ved, da der allerede er kopieret nogen elementer
            int i = newOrder.length;
            // Løb alle kortene igennem
            for (ChanceCards card : this.cards) {
                // Hvis den nye liste ikke indeholder det nuværrende kort, tilføj det og til index 1 op
                if (!this.contains(tmp, card)) {
                    tmp[i] = card;
                    i++;
                }
            }
        }

        // Overskiv listen med kort, men den midlertidige der lige er oprettet
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
        // Opretter en variabel der kan returneres, som som standard er falsk
        boolean result = false;
        // Hvis der er givet to variabeler
        if (array != null && v != null) {
            // For alle de chancekort, som "array" gerne skulle bestå af
            for (ChanceCards i : array) {
                // Hvis det er et chancekort
                if (i != null) {
                    // Hvis de to chancekort's numre er det samme, overskriv "restult" og stop loop
                    if (i.getCardNumber() == v.getCardNumber()) {
                        result = true;
                        break;
                    }
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return Arrays.asList(this.cards).toString();
    }
}