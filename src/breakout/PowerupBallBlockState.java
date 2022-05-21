package breakout;

import java.awt.Color;

import radioactivity.Alpha;
import utils.Rect;
import radioactivity.Ball;
import radioactivity.SuperChargedBall;


public class PowerupBallBlockState extends NormalBlockState {

	private static final int SUPERCHARGED_BALL_LIFETIME = 10000;
	private static final Color COLOR = new Color(0xff, 0x5e, 0x81);

	public PowerupBallBlockState(Rect location) {
		super(location);
	}

	@Override
	public Color getColor() {
		return COLOR;
	}

	@Override
	public Ball ballStateAfterHit(Ball ballState) {
		Ball b = new SuperChargedBall(ballState.getLocation(), ballState.getVelocity(), SUPERCHARGED_BALL_LIFETIME);
		if (ballState.getLinkedAlphas() != null) {
			for (Alpha a : ballState.getLinkedAlphas()) {
				b.addLink(a);
			}
		}
		return b;
	}

}
