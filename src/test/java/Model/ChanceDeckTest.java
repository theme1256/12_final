package Model;

import Model.Cards.ChanceCards;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChanceDeckTest {

    @Test
    public void traekkort() {
        ChanceDeck deck = new ChanceDeck();
        ChanceCards card = deck.draw();
        assertEquals(0, card.getCardNumber());
        card = deck.draw();
        assertEquals(1, card.getCardNumber());
        card = deck.draw();
        assertEquals(2, card.getCardNumber());
    }

    @Test
    public void blandkort() {
        ChanceDeck deck = new ChanceDeck();
        deck.shuffle();
        ChanceCards card = deck.draw();
        ChanceCards firstCard = card;
        for (int i = 0; i < 32; i++) {
            assertTrue((0 <= card.getCardNumber() && card.getCardNumber() <= 31));
            card = deck.draw();
        }
        assertSame(firstCard, card);
    }

    @Test
    public void setOrder() {
        ChanceDeck deck = new ChanceDeck();
        deck.setOrder(new int[]{10, 20, 30});
        ChanceCards card = deck.draw();
        assertEquals(10, card.getCardNumber());
        card = deck.draw();
        assertEquals(20, card.getCardNumber());
        card = deck.draw();
        assertEquals(30, card.getCardNumber());
        card = deck.draw();
        assertEquals(0, card.getCardNumber());
    }
}