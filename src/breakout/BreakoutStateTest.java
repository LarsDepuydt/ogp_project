package breakout;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class BreakoutStateTest {

    Ball ball1;
    Ball ball2;
    Ball ball3;
    Ball[] balls1;
    Ball[] balls2;
    Ball[] collision_ballsSupercharged;
    Ball[] collision_balls;
    Ball[] field_ballsRight;
    Ball[] field_ballsLeft;
    Ball[] field_ballsTop;
    Ball[] field_ballsBottom;


    BlockState block1;
    BlockState block2;
    BlockState block3;
    BlockState[] blocks1;
    BlockState[] blocks2;
    BlockState[] collision_blocksSturdy;
    BlockState[] collision_blocks;
    BlockState[] collision_blocksReplicator;
    BlockState[] collision_blocksSupercharged;

    Point bottomRight1;
    Point bottomRight2;
    Point illigalBottomRight;

    PaddleState paddle1;
    PaddleState paddle2;
    PaddleState illigalPaddleLeft;
    PaddleState illigalPaddleRight;

    BreakoutState b1;
    BreakoutState b2;
    BreakoutState collision_breakout;
    BreakoutState collision_breakoutSuperchargedBall;
    BreakoutState collision_breakoutSturdy;
    BreakoutState collision_breakoutReplicator;
    BreakoutState collision_breakoutSupercharged;
    BreakoutState field_breakoutRight;
    BreakoutState field_breakoutLeft;
    BreakoutState field_breakoutTop;
    BreakoutState field_breakoutBottom;
    BreakoutState breakout_isWon;
    BreakoutState breakout_isDead;

    BreakoutFacade facade;

    @BeforeEach
    void setUp() {
        facade = new BreakoutFacade();

        // test getters and setters
        ball1 = facade.createNormalBall(new Point(10, 20), 20, new Vector(5, -7) );
        ball2 = facade.createSuperchargedBall(new Point(100, 100), 1, new Vector(10, 10), 10 );
        ball3 = facade.createNormalBall(new Point(200, 50), 35, new Vector(-3, 9) );

        var ballsArray = new ArrayList<Ball>();
        ballsArray.add(ball1);
        balls1 = ballsArray.toArray(new Ball[]{});


        block2 = facade.createNormalBlockState(new Point(20,40), new Point(500,300));
        block1 = facade.createPowerupBallBlockState(new Point(1,2), new Point(5,7));
        block3 = facade.createReplicatorBlockState(new Point(100,60), new Point(300,90));

        var blocksArray = new ArrayList<BlockState>();
        blocksArray.add(block1);
        blocks1 = blocksArray.toArray(new BlockState[]{});

        bottomRight1 = new Point (100000, 10000);
        paddle1 = facade.createNormalPaddleState(new Point(2000,3000));


        b1 = new BreakoutState(balls1, blocks1, bottomRight1, paddle1);


        ballsArray.add(ball2);
        ballsArray.add(ball3);
        balls2 = ballsArray.toArray(new Ball[]{});

        blocksArray.add(block2);
        blocksArray.add(block3);
        blocks2 = blocksArray.toArray(new BlockState[]{});

        bottomRight2 = new Point (10000, 10000);
        paddle2 = facade.createReplicatorPaddleState(new Point(4000,5000), 3);


        b2 = new BreakoutState(balls2, blocks2, bottomRight2, paddle2);

        // test collision
        var collisionBallsArrayBottom = new ArrayList<Ball>();
        collisionBallsArrayBottom.add(facade.createNormalBall(new Point(2, 20), 5, new Vector(1, -5)));
        collision_balls = collisionBallsArrayBottom.toArray(new Ball[]{});

        var collisionSuperchargedBallsArrayBottom = new ArrayList<Ball>();
        collisionSuperchargedBallsArrayBottom.add(facade.createSuperchargedBall(new Point(2, 20), 5, new Vector(1, -5), 10));
        collision_ballsSupercharged = collisionBallsArrayBottom.toArray(new Ball[]{});


        var collisionBlocksArrayBottom = new ArrayList<BlockState>();
        collisionBlocksArrayBottom.add(facade.createNormalBlockState(new Point(0,0), new Point(10, 10)));
        collision_blocks = collisionBlocksArrayBottom.toArray(new BlockState[]{});

        var collisionSturdyBlocksArrayBottom = new ArrayList<BlockState>();
        collisionSturdyBlocksArrayBottom.add(facade.createSturdyBlockState(new Point(0,0), new Point(10, 10), 3));
        collision_blocksSturdy = collisionBlocksArrayBottom.toArray(new BlockState[]{});

        var collisionReplicatorBlocksArrayBottom = new ArrayList<BlockState>();
        collisionReplicatorBlocksArrayBottom.add(facade.createReplicatorBlockState(new Point(0,0), new Point(10, 10)));
        collision_blocksReplicator = collisionBlocksArrayBottom.toArray(new BlockState[]{});

        var collisionSuperchargedBlocksArrayBottom = new ArrayList<BlockState>();
        collisionSuperchargedBlocksArrayBottom.add(facade.createPowerupBallBlockState(new Point(0,0), new Point(10, 10)));
        collision_blocksSupercharged = collisionBlocksArrayBottom.toArray(new BlockState[]{});


        collision_breakout = new BreakoutState(collision_balls, collision_blocks, bottomRight1, paddle2);
        collision_breakoutSuperchargedBall = new BreakoutState(collision_ballsSupercharged, collision_blocks, bottomRight1, paddle2);
        collision_breakoutSturdy = new BreakoutState(collision_balls, collision_blocksSturdy, bottomRight1, paddle2);
        collision_breakoutReplicator = new BreakoutState(collision_balls, collision_blocksReplicator, bottomRight1, paddle2);
        collision_breakoutSupercharged = new BreakoutState(collision_balls, collision_blocksSupercharged, bottomRight1, paddle2);

        // left, right, top and bottom side of playing field
        var FieldBallsArrayRight = new ArrayList<Ball>();
        FieldBallsArrayRight.add(facade.createNormalBall(new Point(99980, 20), 5, new Vector(6, 5)));
        field_ballsRight = FieldBallsArrayRight.toArray(new Ball[]{});

        var FieldBallsArrayLeft = new ArrayList<Ball>();
        FieldBallsArrayLeft.add(facade.createNormalBall(new Point(10, 20), 5, new Vector(-5, 5)));
        field_ballsLeft = FieldBallsArrayLeft.toArray(new Ball[]{});

        var FieldBallsArrayTop = new ArrayList<Ball>();
        FieldBallsArrayTop.add(facade.createNormalBall(new Point(20, 10), 5, new Vector(1, -5)));
        field_ballsTop = FieldBallsArrayTop.toArray(new Ball[]{});

        var FieldBallsArrayBottom = new ArrayList<Ball>();
        FieldBallsArrayBottom.add(facade.createNormalBall(new Point(1000, 9998), 5, new Vector(1, 5)));
        field_ballsBottom = FieldBallsArrayBottom.toArray(new Ball[]{});


        field_breakoutRight = new BreakoutState(field_ballsRight, collision_blocks, bottomRight1, paddle2);
        field_breakoutLeft = new BreakoutState(field_ballsLeft, collision_blocks, bottomRight1, paddle2);
        field_breakoutTop = new BreakoutState(field_ballsTop, collision_blocks, bottomRight1, paddle2);
        field_breakoutBottom = new BreakoutState(field_ballsBottom, collision_blocks, bottomRight1, paddle2);

        var emptyArray = new ArrayList<Ball>();

        breakout_isWon = new BreakoutState(field_ballsBottom, emptyArray.toArray(new BlockState[]{}), bottomRight1, paddle1);
        breakout_isDead = new BreakoutState(emptyArray.toArray(new Ball[]{}), collision_blocks, bottomRight1, paddle1);

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
    void testCollision() {
        for(int i = 0; i < 3; ++i) {
            collision_breakout.tick(0, 1);
        }
        assertEquals(0, collision_breakout.getBlocks().length);
        assertEquals(new Vector(1, 5), collision_breakout.getBalls()[0].getVelocity());
    }

    @Test
    void testCollisionSuperchargedBall() {
        for(int i = 0; i < 3; ++i) {
            collision_breakoutSuperchargedBall.tick(0, 1);
        }
        assertEquals(0, collision_breakoutSuperchargedBall.getBlocks().length);
        assertEquals(new Vector(1, 5), collision_breakoutSuperchargedBall.getBalls()[0].getVelocity());
    }

    @Test
    void testSturdyCollision() {
        for(int i = 0; i < 1; ++i) {
            collision_breakoutSturdy.tick(0, 1);
        }
        assertEquals(1, collision_breakoutSturdy.getBlocks().length);
        assertEquals(1, collision_breakoutSturdy.getBlocks()[0].getHealth());
        assertEquals(new Vector(1, -5), collision_breakoutSturdy.getBalls()[0].getVelocity());
    }

    @Test
    void testReplicatorCollision() {
        for(int i = 0; i < 3; ++i) {
            collision_breakoutReplicator.tick(0, 1);
        }
        assertEquals(0, collision_breakoutReplicator.getBlocks().length);
        assertEquals(new Vector(1, 5), collision_breakoutReplicator.getBalls()[0].getVelocity());
    }

    @Test
    void testSuperchargedCollision() {
        for(int i = 0; i < 3; ++i) {
            collision_breakoutSupercharged.tick(0, 1);
        }
        assertEquals(0, collision_breakoutSupercharged.getBlocks().length);
        assertEquals(new Vector(1, 5), collision_breakoutSupercharged.getBalls()[0].getVelocity());
    }

    @Test
    void testRightFieldCollision() {
        for(int i = 0; i < 5; ++i) {
            field_breakoutRight.tick(0, 1);
        }
        assertEquals(new Vector(-6, 5), field_breakoutRight.getBalls()[0].getVelocity());
    }

    @Test
    void testLeftFieldCollision() {
        for(int i = 0; i < 3; ++i) {
            field_breakoutLeft.tick(0, 1);
        }
        assertEquals(new Vector(-5, 5), field_breakoutLeft.getBalls()[0].getVelocity());
    }

    @Test
    void testTopFieldCollision() {
        for(int i = 0; i < 3; ++i) {
            field_breakoutTop.tick(0, 1);
        }
        assertEquals(new Vector(1, 5), field_breakoutTop.getBalls()[0].getVelocity());
    }

    @Test
    void testBottomFieldCollision() {
        for(int i = 0; i < 3; ++i) {
            field_breakoutBottom.tick(0, 1);
        }
        assertEquals(0, field_breakoutBottom.getBalls().length);
    }

    @Test
    void movePaddleRight() {
        b1.movePaddleRight(1);
        b2.movePaddleRight(1);
        assertEquals(new Point(2010, 3000), b1.getPaddle().getCenter());
        assertEquals(new Point(4010, 5000), b2.getPaddle().getCenter());
    }

    @Test
    void movePaddleLeft() {
        b1.movePaddleLeft(1);
        b2.movePaddleLeft(1);
        assertEquals(new Point(1990, 3000), b1.getPaddle().getCenter());
        assertEquals(new Point(3990, 5000), b2.getPaddle().getCenter());
        b1.movePaddleLeft(1);
        b1.movePaddleLeft(1);
        assertEquals(new Point(1970, 3000), b1.getPaddle().getCenter());
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
                () -> new BreakoutState(null,collision_blocks,bottomRight1,paddle1) );
        assertThrows(IllegalArgumentException.class,
                () -> new BreakoutState(balls1,null,bottomRight1,paddle1) );
        assertThrows(IllegalArgumentException.class,
                () -> new BreakoutState(balls1,collision_blocks,null,paddle1) );
        assertThrows(IllegalArgumentException.class,
                () -> new BreakoutState(balls1,collision_blocks,bottomRight1,null) );
    }

    @Test
    void illigalPositions() {
        assertThrows(IllegalArgumentException.class,
                () -> new BreakoutState(balls1,collision_blocks,illigalBottomRight,paddle1) );
    }
}
