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

	public abstract Vector bounceOn(Rect rect);

	public abstract Point getCenter();

	public abstract Color getColor();
}

