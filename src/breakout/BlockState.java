package breakout;

import java.awt.Color;

/**
 * Represents the state of a block in the breakout game.
 *
 * @immutable
 * @invar | getLocation() != null
 */
public abstract class BlockState {

	/**
	 * Return the rectangle occupied by this block in the field.
	 */
	public abstract Rect getLocation();

	public abstract Color getColor();

	public abstract int getHealth();
}
