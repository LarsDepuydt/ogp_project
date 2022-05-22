package breakout.radioactivity;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

import breakout.utils.Point;
import breakout.utils.Circle;
import breakout.utils.Rect;
import breakout.utils.Vector;

/**
 * Represents the state of a ball in the breakout game.
 * 
 * @invar | getLocation() != null
 * @invar | getVelocity() != null
 * @invar | getLinkedALphas() != null
 * @invar | Arrays.stream(getLinkedALphas()).allMatch(Objects::nonNull)
 * @invar | getEcharge() != null
 * @invar | getEcharge() != 0
 */
public abstract class Ball {
	
	private int eCharge;
    /**
     * @peerObjects
     */
    Set<Alpha> linkedAlphas = new HashSet<Alpha>();

	protected Circle location;
	protected Vector velocity;

	/**
	 * Construct a new ball at a given `location`, with a given `velocity`.
	 * 
	 * @pre | location != null
	 * @pre | velocity != null
	 * @post | getLocation().equals(location)
	 * @post | getVelocity().equals(velocity)
	 */
	public Ball(Circle location, Vector velocity) {
		this.location = location;
		this.velocity = velocity;
	}

	/**
	 * Return this ball's location.
	 */
	public Circle getLocation() {
		return location;
	}

	/**
	 * Return this ball's velocity.
	 */
	public Vector getVelocity() {
		return velocity;
	}
	
	/**
	 * Return this ball's electric charge.
	 */
	public int getEcharge() {
		return eCharge;
	}
	
	/**
	 * Calculate the charge of the ball that is dependant of linkedAlphas().
	 */
	private void calculateCharge() {
		var linkedSize = this.getLinkedAlphas().size();
		
		int sign = 1;
		if (linkedSize % 2 == 1) {
			sign = -1;
		}
		
		if (linkedSize == 0) {
			eCharge = sign;
		} else {
			eCharge = sign*linkedSize;
		}
	}

	/**
	 * @peerObjects
	 *
	 * @inspects this
	 * @creates result
	 */
	public Set<Alpha> getLinkedAlphas() {
		Set<Alpha> dummySetAlphas = new HashSet<>();
		dummySetAlphas.addAll(linkedAlphas);
		return dummySetAlphas;
	}
	
	//add ball to ball set, and return the set. (purely for documentation purposes)
	public static Set<Ball> addBalltoSet(Set<Ball> a, Ball b)
	{
		a.add(b);
		return a;
	}
		
	//add alpha to alpha set, and return the set. (purely for documentation purposes)
	public static Set<Alpha> addAlphatoSet(Set<Alpha> a, Alpha b)
	{
		a.add(b);
		return a;
	}
		
	//remove ball from ball set, and return the set. (purely for documentation purposes)
	public static Set<Ball> removeBallfromSet(Set<Ball> a, Ball b) 
	{
		a.remove(b);
		return a;
	}
		
	//remove alpha from alpha set, and return the set. (purely for documentation purposes)
	public static Set<Alpha> removeAlphafromSet(Set<Alpha> a, Alpha b) 
    {
        a.remove(b);
		return a;
	}
		 


	/**
	 * Add a link between a ball and an alpha particle
	 *
	 * @throws IllegalArgumentException if {@code alpha} is null | alpha == null
	 *
	 * @inspects | alpha, this
	 * @mutates_properties | this.getLinkedAlphas(), alpha.getLinkedBalls()
	 * @mutates | this
	 *
	 * @post The given linked alpha particles equal the old linked alpha particles plus this alpha particle.
	 * 	| getLinkedAlphas().equals(addAlphatoSet(old(getLinkedAlphas()),alpha))
	 * @post the linked balls of the alpha are its old linked balls plus this
	 *  | alpha.getLinkedBalls().equals(addBalltoSet(old(alpha.getLinkedBalls()), this))
	 */
	public void addLink(Alpha alpha) {
		if (alpha == null) {
			throw new IllegalArgumentException("Alpha_is_null");
		}

		linkedAlphas.add(alpha);
		alpha.linkedBalls.add(this);

		calculateCharge();
	}

	/**
	 * Removes a given alpha particle from the linked alpha particles
	 *
	 * @throws IllegalArgumentException if {@code alpha} is null | alpha == null
	 *
	 * @inspects | alpha, this
	 * @mutates_properties | this.getLinkedAlphas(), alpha.getLinkedBalls()
	 * @mutates | this
	 *
	 * @post This alpha is no longer linked to the ball particle
	 * 	| getLinkedAlphas().equals(removeAlphafromSet(old(getLinkedAlphas()),alpha))
	 * @post The linked balls of the alpha equals its old linked balls minus this
	 * 	| alpha.getLinkedBalls().equals(removeBallfromSet(old(alpha.getLinkedBalls()),this))
	 */
	public void removeLink(Alpha alpha) {
		if (alpha == null) {
			throw new IllegalArgumentException("Alpha_is_null");
		}

		alpha.linkedBalls.remove(this);
		linkedAlphas.remove(alpha);

		calculateCharge();
	}

	/**
	 * Check whether this ball collides with a given `rect` and if so, return the
	 * new velocity this ball will have after bouncing on the given rect.
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
	 * Move this BallState by the given vector.
	 * 
	 * @pre | v != null
	 * @pre | elapsedTime >= 0
	 * @pre | elapsedTime <= BreakoutState.MAX_ELAPSED_TIME
	 * @post | getLocation().getCenter().equals(old(getLocation()).getCenter().plus(v))
	 * @post | getLocation().getDiameter() == old(getLocation()).getDiameter()
	 * @mutates this
	 */
	public abstract void move(Vector v, int elapsedTime);

	/**
	 * Update the BallState after hitting a block at a given location, taking into account whether the block was destroyed by the hit or not.
	 * 
	 * @pre | rect != null
	 * @pre | collidesWith(rect)
	 * @post | getLocation().equals(old(getLocation()))
	 * @mutates this
	 */
	public abstract void hitBlock(Rect rect, boolean destroyed);

	/**
	 * Update the BallState after hitting a paddle at a given location.
	 * 
	 * @pre | rect != null
	 * @pre | collidesWith(rect)
	 * @pre | paddleVel != null
	 * @post | getLocation().equals(old(getLocation()))
	 * @mutates this
	 */
	public abstract void hitPaddle(Rect rect, Vector paddleVel);

	/**
	 * Update the BallState after hitting a wall at a given location.
	 * 
	 * @pre | rect != null
	 * @pre | collidesWith(rect)
	 * @post | getLocation().equals(old(getLocation()))
	 * @mutates this
	 */
	public abstract void hitWall(Rect rect);

	/**
	 * Return the color this ball should be painted in.
	 * 
	 * @post | result != null
	 * @inspects this
	 */
	public abstract Color getColor();
	
	/**
	 * Return this point's center.
	 * 
	 * @post | getLocation().getCenter().equals(result)
	 * @inspects this
	 */
	public Point getCenter() {
		return getLocation().getCenter();
	}
	
	/**
	 * Return a clone of this BallState with the given velocity.
	 * 
	 * @inspects this
	 * @creates result
	 * @post | result.getLocation().equals(getLocation())
	 * @post | result.getVelocity().equals(v)
	 */
	public abstract Ball cloneWithVelocity(Vector v);

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
	 * Return a clone of this BallState.
	 * 
	 * @inspects this
	 * @creates result
	 * @post | result.getLocation().equals(getLocation())
	 * @post | result.getVelocity().equals(getVelocity())
	 */
	public Ball clone() {
		return cloneWithVelocity(getVelocity());
	}

	public boolean equalContent(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ball other = (Ball) obj;
		if (!getVelocity().equals(other.getVelocity()))
			return false;
		if (!getLocation().getCenter().equals(other.getLocation().getCenter()))
			return false;
		if (getLocation().getDiameter() != other.getLocation().getDiameter())
			return false;
		return true;
	}
}