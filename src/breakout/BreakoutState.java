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

			/*Ball hit left or right side*/
			if (ball.getCenter().getX() - ball.getDiameter() < 0 || ball.getCenter().getX() + ball.getDiameter() > getBottomRight().getX()) {
				System.out.println("hit wall");
				newVelocity = ball.getVelocity().mirrorOver(Vector.LEFT);
			}

			/*Ball hit paddle*/
			if ((ball.getCenter().getY() + ball.getDiameter() >= paddle.getCenter().getY() - paddle.getSize().getY() && ball.getCenter().getY() + ball.getDiameter() <= paddle.getCenter().getY() + paddle.getSize().getY() )
				&& (ball.getCenter().getX() >= paddle.getCenter().getX() - paddle.getSize().getX() && ball.getCenter().getX() <=  paddle.getCenter().getX() + paddle.getSize().getX())) {
				System.out.println("hit paddle");
				newVelocity = ball.getVelocity().mirrorOver(Vector.UP);
			}

			/*Ball hit block*/
			for (var block : blocks) {
				if ((ball.getCenter().getY() - ball.getDiameter() <= block.getBlockBR().getY() && ball.getCenter().getY() - ball.getDiameter() >= block.getBlockTL().getY())
						&& (ball.getCenter().getX() >= block.getBlockTL().getX() && ball.getCenter().getX() <= block.getBlockBR().getX())
				) {
					System.out.println("hit block");
					newVelocity = ball.getVelocity().mirrorOver(Vector.DOWN);
				} else {
					newBlocks.add(block);
				}
			}
			this.blocks = newBlocks.toArray(new BlockState[]{});


			var newCenter = new Point(ball.getCenter().plus(newVelocity).getX(), ball.getCenter().plus(newVelocity).getY());
			var newBall = new BallState(newCenter, ball.getDiameter(), newVelocity);
			newBalls.add(newBall);
		}
		this.balls = newBalls.toArray(new BallState[]{});
	}

	public void movePaddleRight() {
		var paddle = getPaddle();
		var newPoint = new Point(paddle.getCenter().getX() + 10, paddle.getCenter().getY());

		this.paddle = new PaddleState(newPoint, paddle.getSize());
	}

	public void movePaddleLeft() {
		var paddle = getPaddle();
		var newPoint = new Point(paddle.getCenter().getX() - 10, paddle.getCenter().getY());

		this.paddle = new PaddleState(newPoint, paddle.getSize());
	}
	
	public boolean isWon() {
		return false;
	}

	public boolean isDead() {
		return false;
	}
}
