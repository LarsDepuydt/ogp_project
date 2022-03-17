package breakout;

public class PaddleState {
	private final Point center;
    private final Vector size;

    PaddleState(Point center, Vector size) {
        this.center = center;
        this.size = size;
    }

    public Point getCenter() {
        return center;
    }

    public Vector getSize() {
        return size;
    }
}
