package breakout;

import java.awt.Color;

/**
 * Represents the state of a ball in the breakout game.
 * 
 * @mutable
 * @invar | getLocation() != null
 * @invar | getVelocity() != null
 */
public abstract class Ball {
	
	public abstract Circle getLocation();

	public abstract Vector getVelocity();

	public abstract Vector hitBlock(Rect rect, boolean destroyed);

	public abstract Point getCenter();

	public abstract Color getColor();

	public abstract void moveForward(int elapsedTime);

	public abstract void setCenter(Circle location);

	public abstract void setVelocity(Vector velocity);

	public abstract int getTimeLeft();
}