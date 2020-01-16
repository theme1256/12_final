package Model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ShakerTest {

    @Test
    public void shake() {
        Shaker shaker = new Shaker();
        int[] sum = shaker.shake();
        assertTrue((1 <= sum[0] && sum[0] <= 6));
        assertTrue((1 <= sum[1] && sum[1] <= 6));
    }

    @Test
    public void shake_and_sum() {
        Shaker shaker = new Shaker();
        int sum = shaker.shake_and_sum();
        assertTrue((2 <= sum && sum <= 12));
    }

    @Test
    public void override() {
        Shaker shaker = new Shaker();
        shaker.override(new int[][]{{3, 3}});
        int[] sum = shaker.shake();
        assertArrayEquals(new int[]{3, 3}, sum);
    }

    @Test
    public void override_and_sum() {
        Shaker shaker = new Shaker();
        shaker.override(new int[][]{{3, 3}});
        int sum = shaker.shake_and_sum();
        assertEquals(6, sum);
    }
}