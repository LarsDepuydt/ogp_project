package breakout;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class BreakoutStateTest {

    BallState[] oneBall;
    BlockState[] oneBlock;
    Point bottomRight;
    PaddleState paddle;
    BreakoutState state1;
    BreakoutState stateWon;
    BreakoutState stateDead;
    BreakoutState stateBeforeBounceBlock;
    BreakoutState stateBeforeBounceBlockRight;
    Vector origBallVelocity;
    BlockState bounceBlock;
    BallState ball;

    public static final String initMap1 = """
#		       
		       
		       
		       
		       
		     o

		     =

		""";
    public static final String initMapWon = """
		       
		       
		       
		       
		       
		     o

		     =

		""";
    public static final String initMapDead = """
#		       
		       
		       
		       
		       
		     

		     =

		""";

    public static final String initMapBeforeBounce = """
		       
		       
	o	       
	###	       
		       
		     

		     =

		""";
    public static final String initMapBeforeBounceRight = """
		       
		       
	         	       
	  #####	       
		       
		     
         o
		 =

		""";


    @BeforeEach
    void setUp() throws Exception {
        state1 = GameMap.createStateFromDescription(initMap1);
        oneBall = state1.getBalls();
        ball = oneBall[0];
        origBallVelocity = ball.getVelocity();
        oneBlock = state1.getBlocks();
        bottomRight = state1.getBottomRight();
        paddle = state1.getPaddle();
        stateWon = GameMap.createStateFromDescription(initMapWon);
        stateDead = GameMap.createStateFromDescription(initMapDead);
        stateBeforeBounceBlock = GameMap.createStateFromDescription(initMapBeforeBounce);
        stateBeforeBounceBlockRight = GameMap.createStateFromDescription(initMapBeforeBounceRight);
    }

    @Test
    void testBreakoutStateNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new BreakoutState(null,oneBlock,bottomRight,paddle) );
        assertThrows(IllegalArgumentException.class,
                () -> new BreakoutState(oneBall,null,bottomRight,paddle) );
        assertThrows(IllegalArgumentException.class,
                () -> new BreakoutState(oneBall,oneBlock,null,paddle) );
        assertThrows(IllegalArgumentException.class,
                () -> new BreakoutState(oneBall,oneBlock,bottomRight,null) );
    }

    @Test
    void testBreakoutStateNormal() {
        BreakoutState state = new BreakoutState(oneBall,oneBlock,bottomRight,paddle);
        assertTrue(Arrays.equals(oneBall, state.getBalls()));
        assertTrue(Arrays.equals(oneBlock, state.getBlocks()));
        assertEquals(bottomRight,state.getBottomRight());
        assertEquals(paddle, state.getPaddle());
    }

    @Test
    void testTickNormal() {
        state1.tick(0);
        assertEquals(1,state1.getBalls().length);
        BallState b = state1.getBalls()[0];
        assertEquals(origBallVelocity,b.getVelocity());
    }

    @Test
    void testTickBounceBlock() {
        for(int i = 0; i < 300; ++i) {
            stateBeforeBounceBlock.tick(1);
        }
        assertEquals(1,stateBeforeBounceBlock.getBalls().length);
        assertEquals(2,stateBeforeBounceBlock.getBlocks().length);
        assertEquals(new Vector(5,-7),stateBeforeBounceBlock.getBalls()[0].getVelocity());
    }

    @Test
    void testTickBounceBlockRight() {
        for(int i = 0; i < 300; ++i) {
            stateBeforeBounceBlockRight.tick(1);
        }
        assertEquals(1,stateBeforeBounceBlockRight.getBalls().length);
        System.out.println(stateBeforeBounceBlockRight.getBalls()[0].getVelocity());
        assertEquals(5,stateBeforeBounceBlockRight.getBlocks().length);

        assertEquals(new Vector(5,-7),stateBeforeBounceBlockRight.getBalls()[0].getVelocity());
    }

    @Test
    void testIsWon() {
        assertFalse(state1.isWon());
        assertTrue(stateWon.isWon());
        assertFalse(stateDead.isWon());
    }

    @Test
    void testIsDead() {
        assertFalse(state1.isDead());
        assertFalse(stateWon.isDead());
        assertTrue(stateDead.isDead());
    }

}
