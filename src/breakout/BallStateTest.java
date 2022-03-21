package breakout;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BallStateTest {

    BallState bs1;
    BallState bs2;
    BallState bs3;

    @BeforeEach
    void setUp() throws Exception {
        bs1 = new BallState(new Point(10, 20), 20, new Vector(5, -7) );
        bs2 = new BallState(new Point(0, 0), 0, new Vector(0, 0) );
        bs3 = new BallState(new Point(20, 5), 35, new Vector(-3, 9) );
    }

    @Test
    void testCenter() {
        assertEquals(new Point(10, 20), bs1.getCenter());
        assertEquals(new Point(0, 0), bs2.getCenter());
        assertEquals(new Point(20, 5), bs3.getCenter());
    }

    @Test
    void testDiameter() {
        assertEquals(20, bs1.getDiameter());
        assertEquals(0, bs2.getDiameter());
        assertEquals(35, bs3.getDiameter());
    }

    @Test
    void testVelocity() {
        assertEquals(new Vector(5,-7), bs1.getVelocity());
        assertEquals(new Vector(0,0), bs2.getVelocity());
        assertEquals(new Vector(-3,9), bs3.getVelocity());
    }

    @Test
    void testEqualsObject() {
        assertEquals(bs1,bs1);
        assertNotEquals(bs1,bs2);
        assertNotEquals(null,bs1);
    }
}
