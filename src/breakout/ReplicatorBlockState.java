package breakout;

import java.awt.Color;

public class ReplicatorBlockState extends BlockState {
    // TODO: just a copy of normalBlockState

    private final Rect location;
    private static final Color color = Color.magenta;
    private final int health = 1;
    private final boolean superchargedBall = false;

    public ReplicatorBlockState(Rect location) {
        this.location = location;
    }

    public Rect getLocation() { return location; }

    public Color getColor() { return color; }

    public int getHealth() {
        return health;
    }

    public boolean getMakeSupercharged() {
        return superchargedBall;
    }
}
