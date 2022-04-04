package breakout;

import java.awt.Color;

public class NormalBlockState extends BlockState {
    /**
     * @invar | location != null
     */
    private final Rect location;
    private static final Color color = Color.blue;

    public NormalBlockState(Rect location) {
        this.location = location;
    }

    public Rect getLocation() { return location; }

    public Color getColor() { return color; }
}
