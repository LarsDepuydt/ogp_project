package breakout;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents the current state of a breakout game.
 *  
 * @invar | getBalls() != null
 * @invar | getBlocks() != null
 * @invar | getPaddle() != null
 * @invar | getBottomRight() != null
 * @invar | Point.ORIGIN.isUpAndLeftFrom(getBottomRight())
 * @invar | Arrays.stream(getBalls()).allMatch(b -> getField().contains(b.getLocation()))
 * @invar | Arrays.stream(getBlocks()).allMatch(b -> getField().contains(b.getLocation()))
 * @invar | getField().contains(getPaddle().getLocation())
 */
public class BreakoutState {
	public static int MAX_ELAPSED_TIME = 50;
	private static final Vector PADDLE_VEL = new Vector(10,0);
	/**
	 * @invar | bottomRight != null
	 * @invar | Point.ORIGIN.isUpAndLeftFrom(bottomRight)
	 */
	private final Point bottomRight;
	/**
	 * @invar | balls != null
	 * @invar | Arrays.stream(balls).allMatch(b -> getFieldInternal().contains(b.getLocation()))
	 * @representationObject
	 */
	private Ball[] balls;
	/**
	 * @invar | blocks != null
	 * @invar | Arrays.stream(blocks).allMatch(b -> getFieldInternal().contains(b.getLocation()))
	 * @representationObject
	 */
	private BlockState[] blocks;
	/**
	 * @invar | paddle != null
	 * @invar | getFieldInternal().contains(paddle.getLocation())
	 */
	private PaddleState paddle;

	private final Rect topWall;
	private final Rect rightWall;
	private final Rect leftWall;
	private final Rect[] walls;

	/**
	 * Construct a new BreakoutState with the given balls, blocks, paddle.
	 * 
	 * @throws IllegalArgumentException | balls == null
	 * @throws IllegalArgumentException | blocks == null
	 * @throws IllegalArgumentException | bottomRight == null
	 * @throws IllegalArgumentException | paddle == null
	 * @throws IllegalArgumentException | !Point.ORIGIN.isUpAndLeftFrom(bottomRight)
	 * @throws IllegalArgumentException | !(new Rect(Point.ORIGIN,bottomRight)).contains(paddle.getLocation())
	 * @throws IllegalArgumentException | !Arrays.stream(blocks).allMatch(b -> (new Rect(Point.ORIGIN,bottomRight)).contains(b.getLocation()))
	 * @throws IllegalArgumentException | !Arrays.stream(balls).allMatch(b -> (new Rect(Point.ORIGIN,bottomRight)).contains(b.getLocation()))
	 * @post | Arrays.equals(getBalls(),balls)
	 * @post | Arrays.equals(getBlocks(),blocks)
	 * @post | getBottomRight().equals(bottomRight)
	 * @post | getPaddle().equals(paddle)
	 */
	public BreakoutState(Ball[] balls, BlockState[] blocks, Point bottomRight, PaddleState paddle) {
		if( balls == null) throw new IllegalArgumentException();
		if( blocks == null) throw new IllegalArgumentException();
		if( bottomRight == null) throw new IllegalArgumentException();
		if( paddle == null) throw new IllegalArgumentException();

		if(!Point.ORIGIN.isUpAndLeftFrom(bottomRight)) throw new IllegalArgumentException();
		this.bottomRight = bottomRight;
		if(!getFieldInternal().contains(paddle.getLocation())) throw new IllegalArgumentException();
		if(!Arrays.stream(blocks).allMatch(b -> getFieldInternal().contains(b.getLocation()))) throw new IllegalArgumentException();
		if(!Arrays.stream(balls).allMatch(b -> getFieldInternal().contains(b.getLocation()))) throw new IllegalArgumentException();
	
		this.balls = balls.clone();
		this.blocks = blocks.clone();
		this.paddle = paddle;

		this.topWall = new Rect( new Point(0,-1000), new Point(bottomRight.getX(),0));
		this.rightWall = new Rect( new Point(bottomRight.getX(),0), new Point(bottomRight.getX()+1000,bottomRight.getY()));
		this.leftWall = new Rect( new Point(-1000,0), new Point(0,bottomRight.getY()));
		this.walls = new Rect[] {topWall,rightWall, leftWall };
	}

	/**
	 * Return the balls of this BreakoutState.
	 * 
	 * @creates result
	 */
	public Ball[] getBalls() {
		return balls.clone();
	}

	/**
	 * Return the blocks of this BreakoutState. 
	 *
	 * @creates result
	 */
	public BlockState[] getBlocks() {
		return blocks.clone();
	}

	/**
	 * Return the paddle of this BreakoutState. 
	 */
	public PaddleState getPaddle() {
		return paddle;
	}

	/**Arrays
	 * Return the point representing the bottom right corner of this BreakoutState.
	 * The top-left corner is always at Coordinate(0,0). 
	 */
	public Point getBottomRight() {
		return bottomRight;
	}

	// internal version of getField which can be invoked in partially inconsistent states
	private Rect getFieldInternal() {
		return new Rect(Point.ORIGIN, bottomRight);
	}
	
	/**
	 * Return a rectangle representing the game field.
	 * @post | result != null
	 * @post | result.getTopLeft().equals(Point.ORIGIN)
	 * @post | result.getBottomRight().equals(getBottomRight())
	 */
	public Rect getField() {
		return getFieldInternal();
	}

	private void bounceWalls(Ball ball) {
		Circle loc = ball.getLocation();
		for( Rect wall : walls) {
			Vector nspeed = ball.hitBlock(wall, false);
			if( nspeed != null ) {
				ball.setVelocity(nspeed);
			}
		}
	}

	private Ball removeDead(Ball ball) {
		if( ball.getLocation().getBottommostPoint().getY() > bottomRight.getY()) { return null; }
		else { return ball; }
	}

	private void clampBall(Ball ball) {
		Circle loc = getFieldInternal().constrain(ball.getLocation());
		ball.setCenter(loc);
	}
	
	private Ball collideBallBlocks(Ball ball) {
		for(BlockState block : blocks) {
			Vector nspeed = ball.hitBlock(block.getLocation(), ball.getTimeLeft() <= 0 || block.getHealth() > 1);
			if(nspeed != null) {
				removeBlock(block);
				ball.setVelocity(nspeed);

				if (block.getMakeSupercharged()) {
					ball = new SuperchargedBall(ball.getLocation(), ball.getVelocity());
				}

				if (block.getMakeReplicatorPaddle()) {
					paddle = new ReplicatorPaddle(paddle.getCenter(), 3);
				}
			}
		}

		return ball;
	}

	private void addReplicationBalls(Ball ball) {
		var newBalls = new ArrayList<Ball>();
		for (var ballLoop : balls) {
			newBalls.add(ballLoop);
		}

		var replicateCount = paddle.getReplicateCount();
		if (replicateCount >= 3) {
			newBalls.add(new NormalBall(ball.getLocation(), ball.getVelocity().plus(new Vector(2, -2))));
		}
		if (replicateCount >= 2) {
			newBalls.add(new NormalBall(ball.getLocation(), ball.getVelocity().plus(new Vector(-2, 2))));
		}
		if (replicateCount >= 1) {
			newBalls.add(new NormalBall(ball.getLocation(), ball.getVelocity().plus(new Vector(2, 2))));
		}
		this.balls = newBalls.toArray(new Ball[]{});
	}

	private void changeReplicationCount() {
		int newReplicateCount = paddle.getReplicateCount() - 1;
		if (newReplicateCount > 0) {
			paddle = new ReplicatorPaddle(paddle.getCenter(), newReplicateCount);
		} else {
			paddle = new NormalPaddle(paddle.getCenter());
		}
	}

	private void collideBallPaddle(Ball ball, Vector paddleVel) {
		Vector nspeed = ball.hitBlock(paddle.getLocation(), false);
		if(nspeed != null) {
			Point ncenter = ball.getLocation().getCenter().plus(nspeed);
			nspeed = nspeed.plus(paddleVel.scaledDiv(5));

			ball.setCenter(ball.getLocation().withCenter(ncenter));
			ball.setVelocity(nspeed);

			if (paddle.getReplicateCount() > 0) {
				addReplicationBalls(ball);
				changeReplicationCount();
			}
		}
	}

	private void removeBlock(BlockState block) {
		ArrayList<BlockState> nblocks = new ArrayList<BlockState>();
		for (BlockState b : blocks) {
			if (b != block) {
				nblocks.add(b);
			}
		}
		if (block.getHealth() > 1) {
			nblocks.add(new SturdyBlockState(block.getLocation(), block.getHealth() - 1));
		}
		blocks = nblocks.toArray(new BlockState[] {});
	}

	/**
	 * Move all moving objects one step forward.
	 * 
	 * @mutates this
	 */
	public void tick(int paddleDir, int elapsedTime) {
		stepBalls(elapsedTime);
		bounceBallsOnWalls();
		removeDeadBalls();
		bounceBallsOnBlocks();
		bounceBallsOnPaddle(paddleDir);
		clampBalls();
		balls = Arrays.stream(balls).filter(x -> x != null).toArray(Ball[]::new);
	}

	private void clampBalls() {
		for(int i = 0; i < balls.length; ++i) {
			if(balls[i] != null) {
				clampBall(balls[i]);
			}		
		}
	}

	private void bounceBallsOnPaddle(int paddleDir) {
		Vector paddleVel = PADDLE_VEL.scaled(paddleDir);
		for(int i = 0; i < balls.length; ++i) {
			if(balls[i] != null) {
				collideBallPaddle(balls[i], paddleVel);
			}
		}
	}

	private void bounceBallsOnBlocks() {
		for(int i = 0; i < balls.length; ++i) {
			if(balls[i] != null) {
				balls[i] = collideBallBlocks(balls[i]);
			}
		}
	}

	private void removeDeadBalls() {
		for(int i = 0; i < balls.length; ++i) {
			balls[i] = removeDead(balls[i]);
		}
	}

	private void bounceBallsOnWalls() {
		for(int i = 0; i < balls.length; ++i) {
			bounceWalls(balls[i]);
		}
	}

	private void stepBalls(int elapsedTime) {
		for(int i = 0; i < balls.length; ++i) {
			balls[i].moveForward(elapsedTime);
			if (balls[i].getTimeLeft() < 0) {
				balls[i] = new NormalBall(balls[i].getLocation(), balls[i].getVelocity());
			}
		}
	}

	/**
	 * Move the paddle right.
	 * 
	 * @mutates this
	 */
	public void movePaddleRight(int elapsedTime) {
		Point ncenter = paddle.getCenter().plus(PADDLE_VEL.scaled(elapsedTime));

		if (paddle.getReplicateCount() > 0) {
			paddle = new ReplicatorPaddle(getField().minusMargin(PaddleState.WIDTH / 2, 0).constrain(ncenter), paddle.getReplicateCount());
		} else {
			paddle = new NormalPaddle(getField().minusMargin(PaddleState.WIDTH / 2, 0).constrain(ncenter));
		}
	}

	/**
	 * Move the paddle left.
	 * 
	 * @mutates this
	 */
	public void movePaddleLeft(int elapsedTime) {
		Point ncenter = paddle.getCenter().plus(PADDLE_VEL.scaled(-1).scaled(elapsedTime));

		if (paddle.getReplicateCount() > 0) {
			paddle = new ReplicatorPaddle(getField().minusMargin(PaddleState.WIDTH / 2, 0).constrain(ncenter), paddle.getReplicateCount());
		} else {
			paddle = new NormalPaddle(getField().minusMargin(PaddleState.WIDTH / 2, 0).constrain(ncenter));
		}
	}

	/**
	 * Return whether this BreakoutState represents a game where the player has won.
	 * 
	 * @post | result == (getBlocks().length == 0 && !isDead())
	 * @inspects this
	 */
	public boolean isWon() {
		return getBlocks().length == 0 && !isDead();
	}

	/**
	 * Return whether this BreakoutState represents a game where the player is dead.
	 * 
	 * @post | result == (getBalls().length == 0)
	 * @inspects this
	 */
	public boolean isDead() {
		return getBalls().length == 0;
	}
}
