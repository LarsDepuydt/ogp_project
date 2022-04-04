package breakout;

import java.awt.Color;

public class ReplicatorBlockState extends BlockState {
    // TODO: just a copy of normalBlockState

    private final Rect location;
    private static final Color color = Color.magenta;

    public ReplicatorBlockState(Rect location) {
        this.location = location;
    }

    public Rect getLocation() { return location; }

    public Color getColor() { return color; }
}
