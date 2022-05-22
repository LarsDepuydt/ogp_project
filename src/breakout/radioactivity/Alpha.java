package breakout.radioactivity;

import java.util.HashSet;
import java.util.Set;
import java.awt.Color;
import breakout.utils.Circle;
import breakout.utils.Rect;
import breakout.utils.Vector;

/**
 * Represents the state of an alpha particle in the breakout game.
 * 
 * @invar | getLocation() != null
 * @invar | getVelocity() != null
 * @invar | getLinkedBalls() != null
 * @invar | Arrays.stream(getLinkedBalls()).allMatch(Objects::nonNull)
 */
public class Alpha {
	
	private static final Color ALPHA_COLOR = Color.blue;
	
	/**
     * @peerObjects
     */
    Set<Ball> linkedBalls = new HashSet<Ball>();
	
	protected Circle location;
	protected Vector velocity;
	
	/**
	 * Construct a new alpha particle at a given `location`, with a given `velocity`.
	 * 
	 * @pre | location != null
	 * @pre | velocity != null
	 * @post | getLocation().equals(location)
	 * @post | getVelocity().equals(velocity)
	 */
	public Alpha(Circle location, Vector velocity) {
		this.location = location;
		this.velocity = velocity;
	}
	
	/**
	 * Return this alpha's location
	 */
	public Circle getLocation() {
		return location;
	}
	
	/**
	 * Return this alpha's velocity
	 */
	public Vector getVelocity() {
		return velocity;
	}
	
	/**
	 * Return this alpha's color
	 */
	public Color getColor() {
		return ALPHA_COLOR;
	}

	/**
	 * @peerObjects
	 *
	 * @inspects this
	 * @creates result
	 */
	public Set<Ball> getLinkedBalls() {
		Set<Ball> dummySetBalls = new HashSet<>();
		dummySetBalls.addAll(linkedBalls);
		return dummySetBalls;
	}

	/**
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
	
	/**
	 * Check whether this alpha particle collides with a given `rect` and if so, return the
	 * new velocity this alpha particle will have after bouncing on the given rect. (alpha behaves like ball)
	 * 
	 * @pre | rect != null
	 * @post | (rect.collideWith(getLocation()) == null && result == null) ||
	 *       | (rect.collideWith(getLocation()) != null && getVelocity().product(rect.collideWith(getLocation())) <= 0 && result == null) ||
	 *       | (rect.collideWith(getLocation()) != null && result.equals(getVelocity().mirrorOver(rect.collideWith(getLocation()))))
	 * @inspects this
	 */
	public Vector bounceOn(Rect rect) {
		Vector coldir = rect.collideWith(location);
		if (coldir != null && velocity.product(coldir) > 0) {
			return velocity.mirrorOver(coldir);
		}
		return null;
	}
	
	/**
	 * Update the state of the alpha particle after hitting a paddle at a given location.
	 * 
	 * @pre | rect != null
	 * @pre | collidesWith(rect)
	 * @pre | paddleVel != null
	 * @post | getLocation().equals(old(getLocation()))
	 * @mutates this
	 */
	public void hitPaddle(Rect rect, Vector paddleVel) {
		Vector nspeed = bounceOn(rect);
		velocity = nspeed.plus(paddleVel.scaledDiv(5));
	}
	
	/**
	 * Update the state of the alpha particle after hitting a wall at a given location.
	 * 
	 * @pre | rect != null
	 * @pre | collidesWith(rect)
	 * @post | getLocation().equals(old(getLocation()))
	 * @mutates this
	 */
	public void hitWall(Rect rect) {
		velocity = bounceOn(rect);
	}
	
	public void move(Vector v, int elapsedTime) {
		location = new Circle(getLocation().getCenter().plus(v), getLocation().getDiameter());
	}

	public boolean equalContent(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alpha other = (Alpha) obj;
		if (!getVelocity().equals(other.getVelocity()))
			return false;
		if (!getLocation().getCenter().equals(other.getLocation().getCenter()))
			return false;
		if (getLocation().getDiameter() != other.getLocation().getDiameter())
			return false;
		return true;
	}
}