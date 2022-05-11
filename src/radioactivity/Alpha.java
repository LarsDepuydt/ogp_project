package radioactivity;

import java.util.Set;
import java.awt.Color;
import utils.Circle;
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
