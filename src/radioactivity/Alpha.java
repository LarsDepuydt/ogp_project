package radioactivity;

import java.util.Set;
import java.awt.Color;
import utils.Circle;
import utils.Rect;
import utils.Vector;

public class Alpha {
	
	private static final Color ALPHA_COLOR = Color.blue;
	
	/**
     * @peerObjects
     */
    Set<Ball> linkedBalls;
	
	protected Circle location;
	protected Vector velocity;
	
	public Alpha(Circle location, Vector velocity) {
		this.location = location;
		this.velocity = velocity;
	}
	
	public Circle getLocation() {
		return location;
	}
	
	public Vector getVelocity() {
		return velocity;
	}
	
	public Color getColor() {
		return ALPHA_COLOR;
	}

	/**
	 * @peerObjects
	 */
	public Set<Ball> getLinkedBalls() {
		return Set.copyOf(linkedBalls);
	}

	/**
	 * TODO: just a copy of ball, must not bounce on blocks
	 *
	 * Check whether this ball collides with a given `rect`.
	 *
	 * @pre | rect != null
	 * @post | result == ((rect.collideWith(getLocation()) != null) &&
	 *       |            (getVelocity().product(rect.collideWith(getLocation())) > 0))
	 * @inspects this
	 */
	public boolean collidesWith(Rect rect) {
		Vector coldir = rect.collideWith(getLocation());
		return coldir != null && (getVelocity().product(coldir) > 0);
	}

	/**
	 * Set the center to a new location on the grid.
	 *
	 * @mutates this
	 *
	 * @pre | location != null
	 *
	 * @post | location == getLocation()
	 */
	public void setLocation(Circle location) {
		this.location = location;
	}

	/**
	 * Change the ball's velocity.
	 *
	 * @mutates this
	 *
	 * @pre | velocity != null
	 *
	 * @post | velocity == getVelocity()
	 */
	public void setVelocity(Vector velocity) {
		this.velocity = velocity;
	}

	/**
	 * Return a clone of this Alpha with the given velocity.
	 * 
	 * @inspects this
	 * @creates result
	 * @post | result.getLocation().equals(getLocation())
	 * @post | result.getVelocity().equals(v)
	 */
	public Alpha cloneWithVelocity(Vector v) {
		return new Alpha(getLocation(),v);
	}
	
	/**
	 * Return a clone of this Alpha.
	 * 
	 * @inspects this
	 * @creates result
	 * @post | result.getLocation().equals(getLocation())
	 * @post | result.getVelocity().equals(getVelocity())
	 */
	public Alpha clone() {
		return cloneWithVelocity(getVelocity());
	}

}
