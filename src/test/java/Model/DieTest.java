package Model;

import org.junit.Test;

import static org.junit.Assert.*;

public class DieTest {

    @Test
    public void roll_limit() {
        Die die = new Die();
        int face = 0;
        for (int i = 0; i < 10000; i++) {
            assertFalse("Der blev slået en værdi over 6", die.roll() > 6);
        }
    }

    @Test
    public void roll() {
        int normalmargin = 400;
        int loops = 6000;
        Die die = new Die();
        int[] faces = new int[6];
        for (int i = 0; i < loops; i++) {
            faces[die.roll()-1]++;
        }
        for (int face : faces) {
            assertNotEquals("Der var en side der slet ikke blev slået", 0, face);
            assertTrue("Et tal blev slået meget mere", (face <= loops/6+normalmargin));
            assertTrue("Et tal blev slået meget mindre", (loops/6-normalmargin <= face));
        }
    }
}