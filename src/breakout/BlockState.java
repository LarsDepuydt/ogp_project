package breakout;

/**
 * Each instance of this class represents a block in the coordinate grid. 
 * 
 * @immutable
 * @invar This object's blockTL X and Y coordinates are greater than zero
 * 	| getBlockTL().getX() > 0 && getBlockTL().getY() > 0
 * @invar This object's blockBR X and Y coordinates are greater than zero and great than the blockTL coordinates
 * 	| getBlockBR().getX() > 0 && getBlockBR().getY() >  0 && getBlockBR().getX() > getBlockTL().getX() && getBlockBR().getY() > getBlockTL().getY()
 */
public class BlockState {

    /**
     * @invar | blockTL.getX() >= 0 && blockTL.getY() >= 0
     * @invar | blockBR.getX() >= 0 && blockBR.getY() >= 0
     * @invar | blockBR.getX() > blockTL.getX() && blockBR.getY() > blockTL.getY()
     *
     * @representationObject
     */
    private final Point blockTL;
    private final Point blockBR;
    
    /**
     * Initializes this instance so that it represents a block
     * @pre Argument {@code blockTL.getX()} and Argument {@code blockTL.getY()} are greater than {@code 0}
     * 	| blockTL.getX() >= 0 && blockTL.getY() >= 0
     * @pre Argument {@code blockBR.getX()} and Argument {@code blockBR.getY()} are greater than {@code 0}
     * 	| blockBR.getX() >= 0 && blockBR.getY() >= 0
     * @pre Argument {@code blockBR.getX()} and Argument {@code blockBR.getY()} are grater than Argument {@code blockTL.getX()} and Argument {@code blockTL.getY()}
     * 	| blockBR.getX() > blockTL.getX() && blockBR.getY() > blockTL.getY()
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
