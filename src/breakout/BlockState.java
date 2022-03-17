package breakout;

public class BlockState {
    private final Point blockTL;
    private final Point blockBR;

    public BlockState(Point blockTL, Point blockBR) {
        this.blockTL = blockTL;
        this.blockBR = blockBR;
    }

    public Point getBlockTL() {
        return blockTL;
    }

    public Point getBlockBR() {
        return blockBR;
    }
}
