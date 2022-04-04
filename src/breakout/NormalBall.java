package breakout;

import java.awt.*;

public class NormalBall extends Ball {
    private final Circle location;
    private final Vector velocity;
    private static final Color color = Color.white;

    /**
     * Construct a new ball at a given `location`, with a given `velocity`.
     *
     * @pre | location != null
     * @pre | velocity != null
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
    public Vector bounceOn(Rect rect) {
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

    public Color getColor() {
        return color;
    }
}
