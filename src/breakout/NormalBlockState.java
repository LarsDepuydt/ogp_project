package breakout;

import java.awt.Color;

public class NormalBlockState extends BlockState {
    /**
     * @invar | location != null
     */
    private final Rect location;
    private static final Color color = Color.blue;
    private final int health = 1;

    public NormalBlockState(Rect location) {
        this.location = location;
    }

    public Rect getLocation() { return location; }

    public Color getColor() { return color; }

    public int getHealth() {
        return health;
    }
}
