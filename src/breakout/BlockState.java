package breakout;

/**
 * Each instance of this class represents a block in the coordinate grid. 
 *
 * @immutable
 * @invar This object's blockTL X and Y coordinates are greater than or equal to ORIGIN
 * 	| Point.ORIGIN.isUpAndLeftFrom(getBlockTL())
 */
public class BlockState {

    /**
     * @invar | Point.ORIGIN.isUpAndLeftFrom(blockTL)
     * @invar | Point.ORIGIN.isUpAndLeftFrom(blockBR)
     */
    private final Point blockTL;
    private final Point blockBR;

    /**
     * Initializes this instance so that it represents a block
     * @pre Argument {@code blockTL} X and Y coordinates are greater than or equal to {@code Point.ORIGIN}
     * 	| Point.ORIGIN.isUpAndLeftFrom(blockTL)
     * @pre Argument {@code blockBR}  and Y coordinates are greater than or equal to {@code Point.ORIGIN}
     * 	| Point.ORIGIN.isUpAndLeftFrom(blockBR)
     *
     * @post | getBlockTL() == blockTL
     * @post | getBlockBR() == blockBR
     */
    public BlockState(Point blockTL, Point blockBR) {
        this.blockTL = blockTL;
        this.blockBR = blockBR;
    }

    /* Returns this instance blockTl point */
    public Point getBlockTL() {
        return blockTL;
    }

    /* Returns this instance blockBR point */
    public Point getBlockBR() {
        return blockBR;
    }
}
