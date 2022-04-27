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
	
	public abstract Point getCenter();

	public abstract Rect getLocation();

	public abstract Color getColor();

	public abstract int getReplicateCount();
}
