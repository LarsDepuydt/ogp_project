package breakout;

import java.awt.Color;

public class NormalBlockState extends BlockState {
    /**
     * @invar | location != null
     */
    private final Rect location;
    private static final Color color = Color.blue;
    private final int health = 1;
    private final boolean superchargedBall = false;
    private final boolean replicatorPaddle = false;
    
    /**
     * Construct a normal block at a given `location`.
     *
     * @pre | location != null
     * 
     * @post | getLocation() == location
     */
    public NormalBlockState(Rect location) {
        this.location = location;
    }
    
    
    /**
     * Return this blocks location.
     */
    public Rect getLocation() { 
    	return location; 
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
