package breakout;

import java.awt.*;

public class ReplicatorPaddle extends PaddleState {
    /**
     * @invar | center != null
     */
    private final Point center;
    private Color color = Color.cyan;

    /**
     * Construct a paddle located around a given center in the field.
     * @pre | center != null
     * @post | getCenter().equals(center)
     */
    public ReplicatorPaddle(Point center) { // TODO: color fixen
        this.center = center;
    }

    /**
     * Return the center point of this paddle.
     */
    public Point getCenter() {
        return center;
    }

    /**
     * Return the rectangle occupied by this paddle in the field.
     *
     * @post | result != null
     * @post | result.getTopLeft().equals(getCenter().plus(new Vector(-WIDTH/2,-HEIGHT/2)))
     * @post | result.getBottomRight().equals(getCenter().plus(new Vector(WIDTH/2,HEIGHT/2)))
     */
    public Rect getLocation() {
        Vector halfDiag = new Vector(-WIDTH/2,-HEIGHT/2);
        return new Rect(center.plus(halfDiag), center.plus(halfDiag.scaled(-1)));
    }

    public Color getColor() {
        return color;
    }
}
