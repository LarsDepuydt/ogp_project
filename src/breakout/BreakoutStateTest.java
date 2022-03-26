package breakout;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class BreakoutStateTest {

    BallState ball1;
    BallState ball2;
    BallState ball3;
    BallState[] balls1;
    BallState[] balls2;
    BallState[] collision_ballsTop;
    BallState[] collision_ballsBottom;
    BallState[] collision_ballsLeft;
    BallState[] collision_ballsRight;
    BallState[] field_ballsRight;
    BallState[] field_ballsLeft;
    BallState[] field_ballsTop;
    BallState[] field_ballsBottom;


    BlockState block1;
    BlockState block2;
    BlockState block3;
    BlockState[] blocks1;
    BlockState[] blocks2;
    BlockState[] collision_blocksTop;
    BlockState[] collision_blocksBottom;
    BlockState[] collision_blocksLeft;
    BlockState[] collision_blocksRight;

    Point bottomRight1;
    Point bottomRight2;
    Point illigalBottomRight;

    PaddleState paddle1;
    PaddleState paddle2;
    PaddleState illigalPaddleLeft;
    PaddleState illigalPaddleRight;

    BreakoutState b1;
    BreakoutState b2;
    BreakoutState collision_breakoutTop;
    BreakoutState collision_breakoutBottom;
    BreakoutState collision_breakoutLeft;
    BreakoutState collision_breakoutRight;
    BreakoutState field_breakoutRight;
    BreakoutState field_breakoutLeft;
    BreakoutState field_breakoutTop;
    BreakoutState field_breakoutBottom;
    BreakoutState breakout_isWon;
    BreakoutState breakout_isDead;

    @BeforeEach
    void setUp() {
        // test getters and setters
        ball1 = new BallState(new Point(10, 20), 20, new Vector(5, -7) );
        ball2 = new BallState(new Point(0, 0), 0, new Vector(0, 0) );
        ball3 = new BallState(new Point(20, 5), 35, new Vector(-3, 9) );

        var ballsArray = new ArrayList<BallState>();
        ballsArray.add(ball1);
        balls1 = ballsArray.toArray(new BallState[]{});


        block1 = new BlockState(new Point(1,2), new Point(5,7));
        block2 = new BlockState(new Point(20,40), new Point(500,300));
        block3 = new BlockState(new Point(100,90), new Point(300,60));

        var blocksArray = new ArrayList<BlockState>();
        blocksArray.add(block1);
        blocks1 = blocksArray.toArray(new BlockState[]{});

        bottomRight1 = new Point (100, 200);
        paddle1 = new PaddleState(new Point(20,30), new Vector(5,4));


        b1 = new BreakoutState(balls1, blocks1, bottomRight1, paddle1);


        ballsArray.add(ball2);
        ballsArray.add(ball3);
        balls2 = ballsArray.toArray(new BallState[]{});

        blocksArray.add(block2);
        blocksArray.add(block3);
        blocks2 = blocksArray.toArray(new BlockState[]{});

        bottomRight2 = new Point (120, 50);
        paddle2 = new PaddleState(new Point(100,50), new Vector(50,30));


        b2 = new BreakoutState(balls2, blocks2, bottomRight2, paddle2);

        // test collision
        var collisionBallsArrayTop = new ArrayList<BallState>();
        collisionBallsArrayTop.add(new BallState(new Point(2, 2), 1, new Vector(1, 5)));
        collision_ballsTop = collisionBallsArrayTop.toArray(new BallState[]{});

        var collisionBallsArrayBottom = new ArrayList<BallState>();
        collisionBallsArrayBottom.add(new BallState(new Point(2, 20), 1, new Vector(1, -5)));
        collision_ballsBottom = collisionBallsArrayBottom.toArray(new BallState[]{});

        var collisionBallsArrayLeft = new ArrayList<BallState>();
        collisionBallsArrayLeft.add(new BallState(new Point(2, 2), 1, new Vector(5, 1)));
        collision_ballsLeft = collisionBallsArrayLeft.toArray(new BallState[]{});

        var collisionBallsArrayRight = new ArrayList<BallState>();
        collisionBallsArrayRight.add(new BallState(new Point(20, 2), 1, new Vector(-5, 1)));
        collision_ballsRight = collisionBallsArrayRight.toArray(new BallState[]{});


        var collisionBlocksArrayTop = new ArrayList<BlockState>();
        collisionBlocksArrayTop.add(new BlockState(new Point(0,4), new Point(50, 50)));
        collision_blocksTop = collisionBlocksArrayTop.toArray(new BlockState[]{});

        var collisionBlocksArrayBottom = new ArrayList<BlockState>();
        collisionBlocksArrayBottom.add(new BlockState(new Point(0,0), new Point(10, 10)));
        collision_blocksBottom = collisionBlocksArrayBottom.toArray(new BlockState[]{});

        var collisionBlocksArrayLeft = new ArrayList<BlockState>();
        collisionBlocksArrayLeft.add(new BlockState(new Point(10,0), new Point(50, 50)));
        collision_blocksLeft = collisionBlocksArrayLeft.toArray(new BlockState[]{});

        var collisionBlocksArrayRight = new ArrayList<BlockState>();
        collisionBlocksArrayRight.add(new BlockState(new Point(0,0), new Point(10, 20)));
        collision_blocksRight = collisionBlocksArrayRight.toArray(new BlockState[]{});


        collision_breakoutTop = new BreakoutState(collision_ballsTop, collision_blocksTop, bottomRight1, paddle2);
        collision_breakoutBottom = new BreakoutState(collision_ballsBottom, collision_blocksBottom, bottomRight1, paddle2);
        collision_breakoutLeft = new BreakoutState(collision_ballsLeft, collision_blocksLeft, bottomRight1, paddle2);
        collision_breakoutRight = new BreakoutState(collision_ballsRight, collision_blocksRight, bottomRight1, paddle2);

        // left, right, top and bottom side of playing field
        var FieldBallsArrayRight = new ArrayList<BallState>();
        FieldBallsArrayRight.add(new BallState(new Point(90, 20), 1, new Vector(5, 5)));
        field_ballsRight = FieldBallsArrayRight.toArray(new BallState[]{});

        var FieldBallsArrayLeft = new ArrayList<BallState>();
        FieldBallsArrayLeft.add(new BallState(new Point(10, 20), 1, new Vector(-5, 5)));
        field_ballsLeft = FieldBallsArrayLeft.toArray(new BallState[]{});

        var FieldBallsArrayTop = new ArrayList<BallState>();
        FieldBallsArrayTop.add(new BallState(new Point(20, 10), 1, new Vector(1, -5)));
        field_ballsTop = FieldBallsArrayTop.toArray(new BallState[]{});

        var FieldBallsArrayBottom = new ArrayList<BallState>();
        FieldBallsArrayBottom.add(new BallState(new Point(10, 190), 1, new Vector(1, 5)));
        field_ballsBottom = FieldBallsArrayBottom.toArray(new BallState[]{});


        field_breakoutRight = new BreakoutState(field_ballsRight, collision_blocksBottom, bottomRight1, paddle2);
        field_breakoutLeft = new BreakoutState(field_ballsLeft, collision_blocksBottom, bottomRight1, paddle2);
        field_breakoutTop = new BreakoutState(field_ballsTop, collision_blocksBottom, bottomRight1, paddle2);
        field_breakoutBottom = new BreakoutState(field_ballsBottom, collision_blocksBottom, bottomRight1, paddle2);

        var emptyArray = new ArrayList<BallState>();

        breakout_isWon = new BreakoutState(field_ballsBottom, emptyArray.toArray(new BlockState[]{}), bottomRight1, paddle1);
        breakout_isDead = new BreakoutState(emptyArray.toArray(new BallState[]{}), collision_blocksBottom, bottomRight1, paddle1);

        illigalBottomRight = new Point(-100, -100);
    }

    @Test
    void testBallsState() {
        assertTrue(Arrays.equals(balls1, b1.getBalls()));
        assertTrue(Arrays.equals(balls2, b2.getBalls()));
        assertNotEquals(b1.getBalls(), b2.getBalls());
    }

    @Test
    void testBlocksState() {
        assertTrue(Arrays.equals(blocks1, b1.getBlocks()));
        assertTrue(Arrays.equals(blocks2, b2.getBlocks()));
        assertNotEquals(b1.getBlocks(), b2.getBlocks());
    }

    @Test
    void testBottomRight() {
        assertEquals(bottomRight1, b1.getBottomRight());
        assertEquals(bottomRight2, b2.getBottomRight());
        assertNotEquals(b1.getBottomRight(), b2.getBottomRight());
    }

    @Test
    void testPaddle() {
        assertEquals(paddle1, b1.getPaddle());
        assertEquals(paddle2, b2.getPaddle());
        assertNotEquals(b1.getPaddle(), b2.getPaddle());
    }

    @Test
    void testEqualsObject() {
        assertEquals(b1,b1);
        assertNotEquals(b1,b2);
        assertNotEquals(null,b1);
    }

    @Test
    void testTopCollision() {
        for(int i = 0; i < 2; ++i) {
            collision_breakoutTop.tick(0);
        }
        assertEquals(0, collision_breakoutTop.getBlocks().length);
        assertEquals(new Vector(1, -5), collision_breakoutTop.getBalls()[0].getVelocity());
    }

    @Test
    void testBottomCollision() {
        for(int i = 0; i < 3; ++i) {
            collision_breakoutBottom.tick(0);
        }
        assertEquals(0, collision_breakoutBottom.getBlocks().length);
        assertEquals(new Vector(1, 5), collision_breakoutBottom.getBalls()[0].getVelocity());
    }

    @Test
    void testLeftCollision() {
        for(int i = 0; i < 3; ++i) {
            collision_breakoutLeft.tick(0);
        }
        assertEquals(0, collision_breakoutLeft.getBlocks().length);
        assertEquals(new Vector(5, -1), collision_breakoutLeft.getBalls()[0].getVelocity());
    }

    @Test
    void testRightCollision() {
        for(int i = 0; i < 3; ++i) {
            collision_breakoutRight.tick(0);
        }
        assertEquals(0, collision_breakoutRight.getBlocks().length);
        assertEquals(new Vector(5, 1), collision_breakoutRight.getBalls()[0].getVelocity());
    }

    @Test
    void testRightFieldCollision() {
        for(int i = 0; i < 3; ++i) {
            field_breakoutRight.tick(0);
        }
        assertEquals(new Vector(-5, -5), field_breakoutRight.getBalls()[0].getVelocity());
    }

    @Test
    void testLeftFieldCollision() {
        for(int i = 0; i < 3; ++i) {
            field_breakoutLeft.tick(0);
        }
        assertEquals(new Vector(5, 5), field_breakoutLeft.getBalls()[0].getVelocity());
    }

    @Test
    void testTopFieldCollision() {
        for(int i = 0; i < 3; ++i) {
            field_breakoutTop.tick(0);
        }
        assertEquals(new Vector(1, 5), field_breakoutTop.getBalls()[0].getVelocity());
    }

    @Test
    void testBottomFieldCollision() {
        for(int i = 0; i < 3; ++i) {
            field_breakoutBottom.tick(0);
        }
        assertEquals(0, field_breakoutBottom.getBalls().length);
    }

    @Test
    void movePaddleRight() {
        b1.movePaddleRight();
        b2.movePaddleRight();
        assertEquals(new Point(30, 30), b1.getPaddle().getCenter());
        assertEquals(new Point(100, 50), b2.getPaddle().getCenter());
    }

    @Test
    void movePaddleLeft() {
        b1.movePaddleLeft();
        b2.movePaddleLeft();
        assertEquals(new Point(10, 30), b1.getPaddle().getCenter());
        assertEquals(new Point(90, 50), b2.getPaddle().getCenter());
        b1.movePaddleLeft();
        b1.movePaddleLeft();
        assertEquals(new Point(0, 30), b1.getPaddle().getCenter());
    }

    @Test
    void isWon() {
        assertEquals(true, breakout_isWon.isWon());
    }

    @Test
    void isDeath() {
        assertEquals(true, breakout_isDead.isDead());
    }

    @Test
    void breakoutStateNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new BreakoutState(null,collision_blocksBottom,bottomRight1,paddle1) );
        assertThrows(IllegalArgumentException.class,
                () -> new BreakoutState(balls1,null,bottomRight1,paddle1) );
        assertThrows(IllegalArgumentException.class,
                () -> new BreakoutState(balls1,collision_blocksBottom,null,paddle1) );
        assertThrows(IllegalArgumentException.class,
                () -> new BreakoutState(balls1,collision_blocksBottom,bottomRight1,null) );
    }

    @Test
    void illigalPositions() {
        assertThrows(IllegalArgumentException.class,
                () -> new BreakoutState(balls1,collision_blocksBottom,illigalBottomRight,paddle1) );
    }
}
