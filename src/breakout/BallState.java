package breakout;

public class BallState {

	private final Point center;
	private final int diameter;
	private final Vector velocity;

	public BallState(Point center, int diameter, Vector velocity) {
		this.center = center;
		this.diameter = diameter;
		this.velocity = velocity;
	}
	
	public Point getCenter() {
		return center;
	}

	public int getDiameter() {
		return diameter;
	}
	
	public Vector getVelocity() {
		return velocity;
	}
}
