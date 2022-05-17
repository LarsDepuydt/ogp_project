package radioactivity;

import java.util.HashSet;
import java.util.Set;
import java.awt.Color;
import utils.Circle;
import utils.Rect;
import utils.Vector;

/**
 * Represents the state of an alphaa particle in the breakout game.
 * 
 * @invar | getLocation() != null
 * @invar | getVelocity() != null
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
	 */
	public Set<Ball> getLinkedBalls() {
//		return Set.copyOf(linkedBalls);
		return linkedBalls;
	}
	
	// Generic method to merge a set and a Ball object.
	@SuppressWarnings("unchecked")
	public static<T> Set<T> mergeSetBall(Set<T> a, Ball b)
	{
	    Set<T> set = new HashSet<>();
	 
	    set.addAll(a);
	    set.add((T) b);
	 
	    return set;
	}
	
	//Generic method to merge a set and an Alpha object.
	@SuppressWarnings("unchecked")
	public static<T> Set<T> mergeSetAlpha(Set<T> a, Alpha b)
	{
	    Set<T> set = new HashSet<>();
	 
	    set.addAll(a);
	    set.add((T) b);
	 
	    return set;
	}
	
	//Generic method to remova a Ball object from a set.
	public static<T> Set<T> removeBallfromSet(Set<T> a, Ball b) 
	{
	    a.remove(b);
	    return a;
	}
	
	//Generic method to remova an Alpha object from a set.
	public static<T> Set<T> removeAlphafromSet(Set<T> a, Alpha b) 
	{
		a.remove(b);
		return a;
	}
		
	
	/**
	 * Add a link between a ball and an alpha particle
	 *
	 * @throws IllegalArgumentException if {@code ball} is null | ball == null
	 *
	 * @mutates_properties | this.getLinkedBalls(), ball.getLinkedAlphas()
	 * 
	 * @pre | ball != null
	 *
	 * @post The given linked ball particles equal the old linked ball particles plus this ball particle
	 * 	| getLinkedBalls().equals(mergeSetBall(old(getLinkedBalls()),ball))
	 * 
	 * @post The linked alphas of the ball equals its old linked alphas plus this
	 *  | ball.getLinkedAlphas().equals(mergeSetAlpha(old(ball.getLinkedAlphas()),this))
	 */
	public void addLink(Ball ball) {
		if (ball == null) {
			throw new IllegalArgumentException("Ball_is_null");
		}
		
		if (linkedBalls == null) {
			linkedBalls = new HashSet<Ball>();
		}
		
		if (ball.linkedAlphas == null) {
			ball.linkedAlphas = new HashSet<Alpha>();
		}

		linkedBalls.add(ball);
		ball.linkedAlphas.add(this);
	}
	
	/**
	 * Removes a given ball particle from the linked ball particles
	 *
	 * @throws IllegalArgumentException if {@code ball} is null | ball == null
	 *
	 * @mutates_properties | ball.getLinkedAlphas(), this.getLinkedBalls()
	 *
	 * @post This ball is no longer linked to the alpha particle
	 * 	| getLinkedBalls().equals(removeBallfromSet(old(getLinkedBalls()),ball))
	 * @post The linked alphas of the ball equals its old linked alphas minus this
	 * 	| ball.getLinkedAlphas().equals(removeAlphafromSet(old(ball.getLinkedAlphas()),this))
	 */
	public void removeLink(Ball ball) {
		if (ball == null) {
			throw new IllegalArgumentException("Alpha_is_null");
		}

		ball.linkedAlphas.remove(this);
		linkedBalls.remove(ball);
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

}