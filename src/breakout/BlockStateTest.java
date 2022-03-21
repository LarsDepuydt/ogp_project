package breakout;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BlockStateTest {

    BlockState bl1;
    BlockState bl2;
    BlockState bl3;

    @BeforeEach
    void setUp() throws Exception {
        bl1 = new BlockState(new Point(1,2), new Point(5,7));
        bl2 = new BlockState(new Point(20,40), new Point(500,300));
        bl3 = new BlockState(new Point(100,90), new Point(300,60));
    }

    @Test
    void testBlockTL() {
        assertEquals(new Point(1, 2), bl1.getBlockTL());
        assertEquals(new Point(20, 40), bl2.getBlockTL());
        assertEquals(new Point(100, 90), bl3.getBlockTL());
    }

    @Test
    void testBlockBR() {
        assertEquals(new Point(5, 7), bl1.getBlockBR());
        assertEquals(new Point(500, 300), bl2.getBlockBR());
        assertEquals(new Point(300, 60), bl3.getBlockBR());
    }

    @Test
    void testEqualsObject() {
        assertEquals(bl1,bl1);
        assertNotEquals(bl1,bl2);
        assertNotEquals(null,bl1);
    }

}
