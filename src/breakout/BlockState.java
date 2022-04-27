package breakout;

import java.awt.Color;

/**
 * Represents the state of a block in the breakout game.
 *
 * @immutable
 * @invar | getLocation() != null
 * @invar | getHealth() != 0
 */
public abstract class BlockState {

	public abstract Rect getLocation();

	public abstract Color getColor();

	public abstract int getHealth();

	public abstract boolean getMakeSupercharged();

	public abstract boolean getMakeReplicatorPaddle();
}