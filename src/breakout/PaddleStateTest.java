package breakout;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PaddleStateTest {

    PaddleState p1;
    PaddleState p2;
    PaddleState p3;

    @BeforeEach
    void setUp() throws Exception {
        p1 = new PaddleState(new Point(3,2), new Vector(5,6));
        p2 = new PaddleState(new Point(100,60), new Vector(-6,9));
        p3 = new PaddleState(new Point(25,98), new Vector(10,-2));
    }

    @Test
    void testCenter() {
        assertEquals(new Point(3,2), p1.getCenter());
        assertEquals(new Point(100,60), p2.getCenter());
        assertEquals(new Point(25,98), p3.getCenter());
    }

    @Test
    void testSize() {
        assertEquals(new Vector(5,6), p1.getSize());
        assertEquals(new Vector(-6,9), p2.getSize());
        assertEquals(new Vector(10,-2), p3.getSize());
    }

    @Test
    void testEqualsObject() {
        assertEquals(p1,p1);
        assertNotEquals(p1,p2);
        assertNotEquals(null,p1);
    }
}
