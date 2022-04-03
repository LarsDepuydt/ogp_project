package breakout;

import java.awt.Color;

/**
 * Represents the state of a block in the breakout game.
 *
 * @immutable
 * @invar | getLocation() != null
 */
public class BlockState {
	
	/**
	 * @invar | location != null
	 */
	private final Rect location;
	private char type;	// TODO: moet dit nu immutable zijn of niet, indien wel idk hoe ge type en color fixt
	private Color color;

	/**
	 * Construct a block occupying a given rectangle in the field.
	 * @pre | location != null
	 * @post | getLocation().equals(location)
	 */
	public BlockState (Rect location) {
		this.location = location;
		this.type = 'N';
	}

	/**
	 * Return the rectangle occupied by this block in the field.
	 */
	public Rect getLocation() {
		return location;
	}

	public Color getColor() {
		return color;
	}
}
