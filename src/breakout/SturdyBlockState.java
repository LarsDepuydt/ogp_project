package breakout;

import java.awt.Color;

public class SturdyBlockState extends BlockState {

    private final Rect location;
    private final Color color;
    private final int health;

    public SturdyBlockState(Rect location, int health) {
        this.location = location;
        this.health = health;

        switch (health) {
            case 2: this.color = Color.orange; break;
            case 1: this.color = Color.YELLOW; break;
            default: this.color = Color.red;
        }
    }

    public Rect getLocation() { return location; }

    public Color getColor() { return color; }

    public int getHealth() {
        return health;
    }
}
