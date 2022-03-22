package breakout;

/**
 * Each instance of this class represents a paddle with a center point and a size vector
 * 
 * @immutable
 * @invar This object's center X and Y coordinates are greater than 0
 * 	| getCenter().getX() > 0 && getCenter().getY() > 0
 * @invar This object's size vector X and Y coordinates are greater than 0
 * 	| getSize().getX() > 0 && getSize().getY() > 0
 */
public class PaddleState {

    /**
     * @invar | center.getX() > 0 && center.getY() > 0
     * @invar | size.getX() > 0 && size.getY() > 0
     */
    private final Point center;
    private final Vector size;
    
    /**
     * @pre Argument {@code center.getX()} and argument {@code center.getY()} are greater than {@code 0}
     * 	| center.getX() > 0 && center.getY() > 0
     * @pre Argument {@code size.getX()} and argument {@code size.getY()} are greater than {@code 0}
     * 	| size.getX() > 0 && size.getY() > 0
     * 
     * @post | getCenter() == center
     * @post | getSize() == size
	 */
    PaddleState(Point center, Vector size) {
        this.center = center;
        this.size = size;
    }

    /* Returns this instance's center point */
    public Point getCenter() {
        return center;
    }

    /* Returns this instance's size vector */
    public Vector getSize() {
        return size;
    }
}
