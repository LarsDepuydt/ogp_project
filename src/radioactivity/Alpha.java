package radioactivity;

import java.util.HashSet;
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
    Set<Ball> linkedBalls = new HashSet<Ball>();
	
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
//		return Set.copyOf(linkedBalls);
		return linkedBalls;
	}
	
	/**
	 * Add a link between a ball and an alpha particle
	 *
	 * throws IllegalArgumentException if {@code ball} is null
	 * 	| ball == null
	 *
	 * mutates_properties | this.getLinkedBalls(), ball.getLinkedAlphas()
	 *
	 * post The given linked ball particles equal the old linked ball particles plus this ball particle
	 * 	| alpha.getLinkedBalls().equals(Set.plus(old(linkedAlphas.getLinkedBalls()), this))
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
	 * throws IllegalArgumentException if {@code ball} is null
	 * 	 | ball == null
	 *
	 * mutates_properties | this.getLinkedAlphas(), alpha.getLinkedBalls()
	 *
	 * post This ball is no longer linked to the alpha particle
	 * 	| getLinkedBalls().equals(Set.minus(old(getLinkedBalls()), ball))
	 * post This alpha particles old linked balls are its old linked balls minus this ball
	 * 	| getLinkedAlphas().getLinkedBalls().equals(Set.minus(old(getLinkedAlphas().getLinkedBalls()), ball))
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
