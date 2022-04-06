package breakout;

import java.awt.*;

public class PowerupBallBlockState extends BlockState {
    // TODO: just a copy of normalBlockState

    private final Rect location;
    private static final Color color = Color.magenta;
    private final int health = 1;
    private final boolean superchargedBall = true;
    private final boolean replicatorPaddle = false;

    public PowerupBallBlockState(Rect location) {
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

    public boolean getMakeReplicatorPaddle() {
        return replicatorPaddle;
    }
}
