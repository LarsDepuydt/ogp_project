package breakout;

import java.awt.*;

public class SturdyBlockState extends BlockState {
    // TODO: just a copy of normalBlockState

    private final Rect location;
    private Color color = Color.red;

    public SturdyBlockState(Rect location) {
        this.location = location;
    }

    public Rect getLocation() { return location; }

    public Color getColor() { return color; }
}
