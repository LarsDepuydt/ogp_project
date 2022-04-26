package breakout;

import java.awt.*;

public class NormalBall extends Ball {
    private Circle location;
    private Vector velocity;
    private static final Color color = Color.white;
    private static int timeLeft = 0;

    /**
     * Construct a new ball at a given `location`, with a given `velocity`.
     *
     * @pre | location != null
     * @pre | velocity != null
     * 
     * @post | getLocation() == location
     * @post | getVelocity().equals(velocity)
     */
    public NormalBall(Circle location, Vector velocity) {
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
     * Check whether this ball collides with a given `rect` and if so, return the
     * new velocity this ball will have after bouncing on the given rect.
     *
     * @pre | rect != null
     * @post | (rect.collideWith(getLocation()) == null && result == null) ||
     *       | (getVelocity().product(rect.collideWith(getLocation())) <= 0 && result == null) ||
     *       | (result.equals(getVelocity().mirrorOver(rect.collideWith(getLocation()))))
     */
    public Vector hitBlock(Rect rect, boolean destroyed) {
        Vector coldir = rect.collideWith(location);
        if(coldir != null && velocity.product(coldir) > 0) {
            return velocity.mirrorOver(coldir);

        }
        return null;
    }

    /**
     * Return this point's center.
     *
     * @post | getLocation().getCenter().equals(result)
     */
    public Point getCenter() {
        return getLocation().getCenter();
    }
    
    /**
     * Return the color of the object.
     */
    public Color getColor() {
        return color;
    }
    
    /**
	 * Move the ball forward for a certain time.
	 * 
	 * @mutates | getLocation()
	 * 
	 * @pre | elapsedTime != 0
	 * 
	 * @post | getLocation().getDiameter() == old(location.getDiameter()) 
	 * @post | getLocation().getCenter().equals(old(location.getCenter()).plus(getVelocity().scaled(elapsedTime)))
	 */
    public void moveForward(int elapsedTime) {
        location = new Circle(location.getCenter().plus(velocity.scaled(elapsedTime)), location.getDiameter());
    }
    
    /**
     * Set the center to a new location on the grid.
     * 
     * @mutates | getLocation()
     * 
     * @pre | location != null
     * 
     * @post | location == getLocation()
     */
    public void setCenter(Circle location) {
        this.location = location;
    }
    
    /**
     * Change the ball's velocity.
     * 
     * @mutates | getVelocity()
     * 
     * @pre | velocity != null
     * 
     * @post | velocity == getVelocity()
     */
    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }
    /**
     * return the time that the supercharged ball has left being supercharged.
     */
    public int getTimeLeft() {
        return timeLeft;
    }
    
    
}
