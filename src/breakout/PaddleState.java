package breakout;

import java.awt.Color;

/**
 * Represents the state of a paddle in the breakout game.
 *
 * @immutable
 * @invar | getCenter() != null
 */
public abstract class PaddleState {
	
	public static final int HEIGHT = 500;
	public static final int WIDTH = 3000;
	
	/**
	 * Return the center point of this paddle.
	 */
	public abstract Point getCenter();

	/**
	 * Return the rectangle occupied by this paddle in the field.
	 * 
	 * @post | result != null
	 * @post | result.getTopLeft().equals(getCenter().plus(new Vector(-WIDTH/2,-HEIGHT/2)))
	 * @post | result.getBottomRight().equals(getCenter().plus(new Vector(WIDTH/2,HEIGHT/2)))
	 */
	public abstract Rect getLocation();

	public abstract Color getColor();

	public abstract int getReplicateCount();
}
