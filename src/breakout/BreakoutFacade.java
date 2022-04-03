package breakout;

import java.awt.Color;

//No documentation required for this class
public class BreakoutFacade {
	public PaddleState createNormalPaddleState(Point center) {
		// TODO
		return new PaddleState(center);
	}

	public Ball createNormalBall(Point center, int diameter, Vector initBallVelocity) {
		// TODO
		var circle = new Circle(center, diameter);
		return new Ball(circle, initBallVelocity);
	}

	public Ball createSuperchargedBall(Point center, int diameter, Vector initBallVelocity, int lifetime) {
		// TODO
		var circle = new Circle(center, diameter);
		return new Ball(circle, initBallVelocity);	// TODO: nog niks speciaal aan nu
	}

	public BreakoutState createBreakoutState(Ball[] balls, BlockState[] blocks, Point topRight,
			PaddleState paddle) {
		// TODO
		return new BreakoutState(balls, blocks, topRight, paddle);
	}

	public BlockState createNormalBlockState(Point topLeft, Point bottomRight) {
		// TODO
		var rect = new Rect(topLeft, bottomRight);
		return new BlockState(rect);
	}

	public BlockState createSturdyBlockState(Point topLeft, Point bottomRight, int i) {
		// TODO
		var rect = new Rect(topLeft, bottomRight);
		return new BlockState(rect);	// TODO: nog niks speciaal aan nu
	}

	public BlockState createReplicatorBlockState(Point topLeft, Point bottomRight) {
		// TODO
		var rect = new Rect(topLeft, bottomRight);
		return new BlockState(rect);	// TODO: nog niks speciaal aan nu
	}

	public BlockState createPowerupBallBlockState(Point topLeft, Point bottomRight) {
		// TODO
		var rect = new Rect(topLeft, bottomRight);
		return new BlockState(rect); 	// TODO: nog niks speciaal aan nu
	}

	public Color getColor(PaddleState paddle) {
		// TODO
		return paddle.getColor();
	}

	public Color getColor(Ball ball) {
		// TODO
		return ball.getColor();
	}

	public Rect getLocation(PaddleState paddle) {
		// TODO
		return paddle.getLocation();
	}

	public Point getCenter(Ball ball) {
		// TODO
		return ball.getCenter();
	}

	public int getDiameter(Ball ball) {
		// TODO
		return ball.getLocation().getDiameter();
	}

	public Ball[] getBalls(BreakoutState breakoutState) {
		// TODO
		return breakoutState.getBalls();
	}

	public Color getColor(BlockState block) {
		// TODO
		return block.getColor();
	}

	public Rect getLocation(BlockState block) {
		// TODO
		return block.getLocation();
	}
}