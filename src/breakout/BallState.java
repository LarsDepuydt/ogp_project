package breakout;

/**
 * Each instance of this class represents a ball with a center point, a diameter of type integer and a velocity vector.
 * 
 * @immutable
 * @invar This object's X and Y coordinates of center must be greater than 0
 * 	| getCenter().getX() > 0 && getCenter().getY() > 0
 * @invar This object's diameter must be greater than 0
 * 	| getDiameter() > 0
 */
public class BallState {
	
	/**
	 * @invar | center.getX() > 0 && center.getY() > 0
	 * @invar | diameter > 0
	 *
	 * @representationObject
	 */
	private final Point center;
	private final int diameter;
	private final Vector velocity;

	/**
	 * Initializes this instance so that it represents a ball with a given center point, diameter and velocity.
	 * 
	 * @pre Argument {@code center.getX()} and {@code center.getY()} are greater than {@code 0} 
	 * 	| center.getX() > 0 && center.getY() > 0
	 * @pre Argument {@code diameter} is greater than {@code 0}
	 * 	| diameter > 0
	 * @pre Argument {@code velocity} is not {@code null}
	 * 	| velocity != null
	 * 
	 * @post | getCenter() == center
	 * @post | getDiameter() == diameter
	 * @post | getVelocity() == velocity
	 */
	public BallState(Point center, int diameter, Vector velocity) {
		this.center = center;
		this.diameter = diameter;
		this.velocity = velocity;
	}
	
	/** Returns this instance's center point. */
	public Point getCenter() { return center; }

	/** Returns this instance's diameter. */
	public int getDiameter() { return diameter; }
	
	/** Returns this instance's velocity vector. */
	public Vector getVelocity() { return velocity; }
}
