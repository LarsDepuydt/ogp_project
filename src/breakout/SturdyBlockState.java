package breakout;

import java.awt.Color;

/**
 * Represents the state of a sturdy block in the breakout game.
 *
 * @immutable
 * @invar | getLocation() != null
 * @invar | getHealth() != 0
 */
public class SturdyBlockState extends BlockState {
    /**
     * @invar | getLocation() != null
     * @invar | getHealth() != 0
     */
    private final Rect location;
    private final Color color;
    private final int health;
    private final boolean superchargedBall = false;
    private final boolean replicatorPaddle = false;

    /**
     * Construct a sturdy block at a given `location`, with a certain 'health' level.
     *
     * @pre | location != null
     * @pre | health != 0
     * 
     * @post | getLocation() == location
     * @post | getHealth() == health
     */
    public SturdyBlockState(Rect location, int health) {
        this.location = location;
        this.health = health;

        switch (health) {
            case 2: this.color = Color.orange; break;
            case 1: this.color = Color.YELLOW; break;
            default: this.color = Color.red;
        }
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