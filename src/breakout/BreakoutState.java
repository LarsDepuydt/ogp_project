package breakout;

import java.util.ArrayList;

/**
 * Each instance of this class stores the state of the game, these are all the balls, the blocks, the bottomRight point
 * of the playing field and the paddle.
 *
 *
 */
public class BreakoutState {

	/**
	 * @invar | balls != null
	 * @invar | blocks != null
	 * @invar | bottomRight.getX() > Point.ORIGIN.getX() && bottomRight.getY() > Point.ORIGIN.getY()
	 * @invar | paddle.getCenter().getX() > Point.ORIGIN.getX() || paddle.getCenter().getX() < bottomRight.getX()
	 */
	private BallState[] balls;
	private BlockState[] blocks;
	private Point bottomRight;
	private PaddleState paddle;

	/**
	 * Initializes this object with the given balls, blocks, bottomRight point and paddle.
	 *
	 * @throws IllegalArgumentException if balls are equal to null
	 * 	| balls != null
	 * @throws IllegalArgumentException if blocks are equal to null
	 * 	| blocks != null
	 * @throws IllegalArgumentException if bottomRight is equal to null
	 * 	| bottomRight != null
	 * @throws IllegalArgumentException if paddle is equal to null
	 * 	| paddle != null
	 * @throws IllegalArgumentException if bottomRight coordinates are smaller than the ORIGIN coordinates
	 *  | !(bottomRight.getX() >  Point.ORIGIN.getX() && bottomRight.getY() > Point.ORIGIN.getY())
	 * @throws IllegalArgumentException if the paddle center is smaller than the ORIGIN X coordinate or larger than the bottomRight X coordinate
	 * 	| !(paddle.getCenter().getX() > Point.ORIGIN.getX() || paddle.getCenter().getX() < bottomRight.getX())
	 *
	 * @post This object's balls equal the given balls
	 * 	| getBalls() == balls
	 * @post This object's blocks equal the given blocks
	 * 	| getBlocks() == blocks
	 * @post This object's bottomRight point equal to the given bottomRight point
	 * 	| getBottomRight() == bottomRight
	 * @post This object's paddle equal the given paddle
	 * 	| getPaddle() == paddle
	 */
	public BreakoutState(BallState[] balls, BlockState[] blocks, Point bottomRight, PaddleState paddle) {
		if (balls == null) {
			throw new IllegalArgumentException("balls_not_null");
		}
		if (blocks == null) {
			throw new IllegalArgumentException("blocks_not_null");
		}
		if (bottomRight == null) {
			throw new IllegalArgumentException("bottomRight_not_null");
		}
		if (paddle == null) {
			throw new IllegalArgumentException("paddle_not_null");
		}
		if (!(bottomRight.getX() >  Point.ORIGIN.getX() && bottomRight.getY() > Point.ORIGIN.getY())) {
			throw new IllegalArgumentException("bottomRight_out_of_range");
		}
		if (!(paddle.getCenter().getX() > Point.ORIGIN.getX() || paddle.getCenter().getX() < bottomRight.getX())) {
			throw new IllegalArgumentException("paddle_center_out_of_range");
		}

		this.balls = balls;
		this.blocks = blocks;
		this.bottomRight = bottomRight;
		this.paddle = paddle;
	}

	/**
	 * Returns this instance's balls
	 *
	 * @creates | ballsResult
	 * @post | ballsResult != null
	 */
	public BallState[] getBalls() { return balls.clone(); }

	/**
	 * Returns this instance's blocks
	 *
	 * @creates | blocksResult
	 * @post | blocksResult != null
	 */
	public BlockState[] getBlocks() { return blocks.clone(); }

	/**
	 * Returns this instance's paddle
	 *
	 * @post | paddle.getCenter().getX() > Point.ORIGIN.getX() || paddle.getCenter().getX() < bottomRight.getX()
	 */
	public PaddleState getPaddle() { return paddle; }

	/**
	 * Returns this instance's bottomRight point
	 *
	 * @post | bottomRight.getX() > Point.ORIGIN.getX() && bottomRight.getY() > Point.ORIGIN.getY()
	 */
	public Point getBottomRight() { return bottomRight; }

	public Vector checkForCollision(Point pointTL, Point pointBR, BallState ball) {
		var ballCenterX = ball.getCenter().getX();
		var ballCenterY = ball.getCenter().getY();
		var diameter = ball.getDiameter();

		var ballVelocityY = ball.getVelocity().getY();
		var ballVelocityX = ball.getVelocity().getX();

		var blockTopY = pointTL.getY();
		var blockBottomY = pointBR.getY();
		var blockLeftX = pointTL.getX();
		var blockRightX = pointBR.getX();


		var newVelocity = new Vector(0,0);

		// top or bottom side of object
		if (ballCenterY + diameter >= blockTopY && ballCenterY - diameter <= blockBottomY	// conditions on the Y-axis
				&& ballCenterX >= blockLeftX && ballCenterX <= blockRightX ) {				// conditions on the X-axis
			// top side of object
			if (ballVelocityY > 0) {
				newVelocity = ball.getVelocity().mirrorOver(Vector.UP);
			// bottom side of element
			} else {
				newVelocity = ball.getVelocity().mirrorOver(Vector.DOWN);
			}

		// left or right side of object
		} else if (ballCenterY >= blockTopY && ballCenterY <= blockBottomY									// conditions on the Y-axis
				&& ballCenterX + diameter >= blockLeftX && ballCenterX - diameter <= blockRightX ) {		// conditions on the X-axis
			// left side of object
			if (ballVelocityX > 0) {
				newVelocity = ball.getVelocity().mirrorOver(Vector.LEFT);
			// right side of element
			} else {
				newVelocity = ball.getVelocity().mirrorOver(Vector.RIGHT);
			}
		}

		return newVelocity;
	}

	/* Updates the BreakoutState according to one tick has passed. */
	public void tick(int paddleDir) {
		var balls = getBalls();
		var newBalls = new ArrayList<BallState>();
		var paddle = getPaddle();
		var blocks = getBlocks();
		var newBlocks = new ArrayList<BlockState>();

		for (var ball : balls) {
			var newVelocity =  ball.getVelocity();
			var addBall = true;

			/* Ball hit left side */
			var left = checkForCollision(new Point(-100, Point.ORIGIN.getY()), new Point(Point.ORIGIN.getX(), getBottomRight().getY()), ball);
			if (left.getSquareLength() != 0) {
				newVelocity = left;
			}

			/* Ball hit right side */
			var right = checkForCollision(new Point(getBottomRight().getX(), Point.ORIGIN.getY()), new Point(getBottomRight().getX() + 100, getBottomRight().getY()), ball);
			if (right.getSquareLength() != 0) {
				newVelocity = right;
			}

			/* Ball hit top side */
			var top = checkForCollision(new Point(Point.ORIGIN.getX(), -100), new Point(getBottomRight().getX(), Point.ORIGIN.getY()), ball);
			if (top.getSquareLength() != 0) {
				newVelocity = top;
			}

			/*Ball hit bottom of the field*/
			if (ball.getCenter().getY() + ball.getDiameter() > getBottomRight().getY()) {
				addBall = false;
			}

			/* Ball hit block */
			for (var block : blocks) {
				var blockHitVelocity = checkForCollision(block.getBlockTL(), block.getBlockBR(), ball);

				if ((blockHitVelocity.getSquareLength() != 0)
				) {
					newVelocity = blockHitVelocity;
				} else {
					newBlocks.add(block);
				}
			}
			this.blocks = newBlocks.toArray(new BlockState[]{});

			/* Ball hit paddle */
			var center = paddle.getCenter();
			var size = paddle.getSize();

			var paddleHit = checkForCollision(center.minus(size), center.plus(size), new BallState(ball.getCenter(), ball.getDiameter(), ball.getVelocity().plus(ball.getVelocity().scaledDiv(5).scaled(paddleDir))));
			if (paddleHit.getSquareLength() != 0) {
				System.out.println("hit paddle");
				System.out.println(paddleDir);
				System.out.println(ball.getVelocity());
				newVelocity = paddleHit;
				System.out.println(newVelocity);
			}


			var newCenter = new Point(ball.getCenter().plus(newVelocity).getX(), ball.getCenter().plus(newVelocity).getY());
			var newBall = new BallState(newCenter, ball.getDiameter(), newVelocity);

			if (addBall) { newBalls.add(newBall); }
		}
		this.balls = newBalls.toArray(new BallState[]{});
	}

	/* Moves the paddle according to the given size. */
	private PaddleState movePaddle(int size) {
		var paddle = getPaddle();
		var newX = paddle.getCenter().getX();

		var condition = paddle.getCenter().getX() - paddle.getSize().getX() > 0;
		if (size > 0) {
			condition = paddle.getCenter().getX() + paddle.getSize().getX() < getBottomRight().getX();
		}

		if ( condition ) {
			newX =paddle.getCenter().getX() + size;
		}

		var newPoint = new Point(newX, paddle.getCenter().getY());
		return new PaddleState(newPoint, paddle.getSize());
	}

	/* Moves the paddle right. */
	public void movePaddleRight() {
		this.paddle = movePaddle(10);
	}

	/* Moves the paddle left. */
	public void movePaddleLeft() {
		this.paddle = movePaddle(-10);
	}

	/**
	 * Returns if the player has won the game
	 *
	 * @post
	 * 		The result is {@code true} if there is at least one ball left and
	 * 		there are no blocks left.
	 * 	  | result == (
	 * 	  | 	getBalls().length > 0 &&
	 * 	  |		getBlocks().length <= 0
	 * 	  | )
	 */
	public boolean isWon() {
		var balls = getBalls();
		var blocks = getBlocks();

		return balls.length > 0 && blocks.length <= 0;
	}

	/**
	 * Returns if the player is dead
	 *
	 * @post
	 * 		The result is {@code true} if there are no balls left in the game.
	 *    | result == (
	 *    | 	getBalls() <= 0
	 *    | )
	 */
	public boolean isDead() {
		var balls = getBalls();
		return balls.length <= 0;
	}
}
