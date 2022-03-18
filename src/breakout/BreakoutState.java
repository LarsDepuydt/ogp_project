package breakout;

import java.util.ArrayList;

public class BreakoutState {

	private BallState[] balls;
	private BlockState[] blocks;
	private Point bottomRight;
	private PaddleState paddle;
	
	public BreakoutState(BallState[] balls, BlockState[] blocks, Point bottomRight, PaddleState paddle) {
		this.balls = balls;
		this.blocks = blocks;
		this.bottomRight = bottomRight;
		this.paddle = paddle;
	}
	
	public BallState[] getBalls() {
		return balls;
	}

	public BlockState[] getBlocks() {
		return blocks;
	}

	public PaddleState getPaddle() {
		return paddle;
	}

	public Point getBottomRight() {
		return bottomRight;
	}

	public void tick(int paddleDir) {
		var balls = getBalls();
		var newBalls = new ArrayList<BallState>();
		var paddle = getPaddle();
		var blocks = getBlocks();
		var newBlocks = new ArrayList<BlockState>();

		for (var ball : balls) {
			var newVelocity =  ball.getVelocity();
			var addBall = true;

			/*Ball hit left or right side*/
			if (ball.getCenter().getX() - ball.getDiameter() < 0 || ball.getCenter().getX() + ball.getDiameter() > getBottomRight().getX()) {
				System.out.println("hit wall");
				newVelocity = ball.getVelocity().mirrorOver(Vector.LEFT);
			}

			/*Ball hit top border*/
			if(ball.getCenter().getY() - ball.getDiameter() < 0) {
				System.out.println("hit top wall");
				newVelocity = ball.getVelocity().mirrorOver(Vector.DOWN);
			}

			/*Ball hit bottom of the field*/
			if (ball.getCenter().getY() + ball.getDiameter() > getBottomRight().getY()) {
				addBall = false;
			}

			/*Ball hit block*/
			for (var block : blocks) {
				if ((ball.getCenter().getY() - ball.getDiameter() <= block.getBlockBR().getY() && ball.getCenter().getY() - ball.getDiameter() >= block.getBlockTL().getY())
						&& (ball.getCenter().getX() + ball.getDiameter() >= block.getBlockTL().getX() && ball.getCenter().getX() - ball.getDiameter() <= block.getBlockBR().getX())
				) {
					System.out.println("hit block");
					if (ball.getVelocity().product(Vector.DOWN) < 0) {
						newVelocity = ball.getVelocity().mirrorOver(Vector.DOWN);
					} else {
						newVelocity = ball.getVelocity().mirrorOver(Vector.UP);
					}
				} else {
					newBlocks.add(block);
				}
			}
			this.blocks = newBlocks.toArray(new BlockState[]{});

			/*Ball hit paddle*/
			if ((ball.getCenter().getY() + ball.getDiameter() >= paddle.getCenter().getY() - paddle.getSize().getY() && ball.getCenter().getY() + ball.getDiameter() <= paddle.getCenter().getY() + paddle.getSize().getY() )
					&& (ball.getCenter().getX() + ball.getDiameter() >= paddle.getCenter().getX() - paddle.getSize().getX() && ball.getCenter().getX() - ball.getDiameter() <=  paddle.getCenter().getX() + paddle.getSize().getX())) {
				System.out.println("hit paddle");
				System.out.println(paddleDir);
				System.out.println(ball.getVelocity().mirrorOver(Vector.UP));
				System.out.println(ball.getVelocity().mirrorOver(Vector.UP).plus(ball.getVelocity().scaled(paddleDir/5)));

				newVelocity = ball.getVelocity().mirrorOver(Vector.UP).plus(ball.getVelocity().scaled(paddleDir/5));
			}


			var newCenter = new Point(ball.getCenter().plus(newVelocity).getX(), ball.getCenter().plus(newVelocity).getY());
			var newBall = new BallState(newCenter, ball.getDiameter(), newVelocity);

			if (addBall) { newBalls.add(newBall); }
		}
		this.balls = newBalls.toArray(new BallState[]{});
	}

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

	public void movePaddleRight() {
		this.paddle = movePaddle(10);
	}

	public void movePaddleLeft() {
		this.paddle = movePaddle(-10);
	}

	public boolean isWon() {
		var balls = getBalls();
		var blocks = getBlocks();

		return balls.length > 0 && blocks.length <= 0;
	}

	public boolean isDead() {
		var balls = getBalls();
		return balls.length <= 0;
	}
}
