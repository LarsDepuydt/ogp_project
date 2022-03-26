package breakout;

/**
 * Each instance of this class represents a paddle with a center point and a size vector
 * 
 * @immutable
 * @invar This object's center X and Y coordinates are greater than or equal to ORIGIN
 * 	| Point.ORIGIN.isUpAndLeftFrom(getCenter())
 */
public class PaddleState {

    /**
     * @invar | Point.ORIGIN.isUpAndLeftFrom(center)
     */
    private final Point center;
    private final Vector size;
    
    /**
     * @pre Argument {@code center} X and Y coordinates are greater than or equal to {@code Point.ORIGIN}
     * 	| Point.ORIGIN.isUpAndLeftFrom(center)
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
