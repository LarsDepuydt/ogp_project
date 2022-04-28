package breakout;

import java.awt.Color;

/**
 * Represents the state of a replicator block in the breakout game.
 *
 * @immutable
 * @invar | getLocation() != null
 */
public class ReplicatorBlockState extends BlockState {
    /**
     * @invar | location != null
     */
    private final Rect location;
    private static final Color color = Color.gray;
    private final int health = 1;
    private final boolean superchargedBall = false;
    private final boolean replicatorPaddle = true;
    
    /**
     * Construct a replicator block at a given `location`.
     *
     * @pre | location != null
     * 
     * @post | getLocation().equals(location)
     */
    public ReplicatorBlockState(Rect location) {
        this.location = location;
    }

    /**
     * Return this blocks location.
     * 
     * @creates result
     */
    public Rect getLocation() { 
    	return new Rect(location.getTopLeft(), location.getBottomRight());
    }
    /**
     * Return the color of the object.
     */
    public Color getColor() { 
    	return color; 
    }
    
    /**
     * Return the state of the sturdy blocks.
     */
    public int getHealth() {
        return health;
    }
    
    /**
     * Return 'True' or 'False', whether the block is a powerup block or not.
     */
    public boolean getMakeSupercharged() {
        return superchargedBall;
    }
    
    /**
     * Return 'True' or 'False', whether the block is a replicator block or not.
     */
    public boolean getMakeReplicatorPaddle() {
        return replicatorPaddle;
    }
}