package HW11;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UtilTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    //TODO
    public void testRndInt() {
        int[] counts = new int[5];
        for(int i = 0; i < 10000; i++) {
            int current = Util.rndInt(5, 10);
            switch(current) {
            case 5:
                counts[0]++;
                break;
            case 6:
                counts[1]++;
                break;
            case 7:
                counts[2]++;
                break;
            case 8:
                counts[3]++;
                break;
            case 9:
                counts[4]++;
                break;
            }
        }
        assertTrue(counts[0]/10000.0 < 0.21 && counts[0]/10000.0 > 0.19);
        assertTrue(counts[1]/10000.0 < 0.21 && counts[1]/10000.0 > 0.19);
        assertTrue(counts[2]/10000.0 < 0.21 && counts[2]/10000.0 > 0.19);
        assertTrue(counts[3]/10000.0 < 0.21 && counts[3]/10000.0 > 0.19);
        assertTrue(counts[4]/10000.0 < 0.21 && counts[4]/10000.0 > 0.19);
        
    }

    @Test
    //TODO
    public void testCount() {
        int[] a = new int[] {1, 2, 3, 4, 5, 5, 5, 5};
        assertEquals(0, Util.count(a, 0, 0, 4));
        assertEquals(0, Util.count(a, 5, 1, 3));
        assertEquals(1, Util.count(a, 3, 1, 3));
        
        assertEquals(4, Util.count(a, 5, 0, 7));
        assertEquals(3, Util.count(a, 5, 0, 6));
    }

}
