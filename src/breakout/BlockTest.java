package breakout;

import java.awt.Color;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BlockTest {
	Point p43;
	Point p78;
	Point p93;
	Point p128;
	Point p410;
	Point p715;
	Point p910;
	Point p1215;
	
	Rect r4378;
	Rect r93128;
	Rect r410715;
	Rect r9101215;
	
	BlockState b1;
	BlockState b2;
	BlockState b3;
	BlockState b4h3;
	BlockState b4h2;
	BlockState b4h1;

	@BeforeEach
	void setUp() throws Exception {
		p43 = new Point(4,3);
		p78 = new Point(7,8);
		p93 = new Point(9,3);
		p128 = new Point(12,8);
		p410 = new Point(4,10);
		p715 = new Point(7,15);
		p910 = new Point(9,10);
		p1215 = new Point(12,15);
		
		r4378 = new Rect(p43,p78);
		r93128 = new Rect(p93,p128);
		r410715 = new Rect(p410,p715);
		r9101215 = new Rect(p910,p1215);
		
		b1 = new NormalBlockState(r4378);
		b2 = new PowerupBallBlockState(r93128);
		b3 = new ReplicatorBlockState(r410715);
		b4h3 = new SturdyBlockState(r9101215,3);
		b4h2 = new SturdyBlockState(r9101215,2);
		b4h1 = new SturdyBlockState(r9101215,1);
		
	}

	@Test
	void testBlock() {
		assertEquals(r4378,b1.getLocation());
		assertEquals(r93128,b2.getLocation());
		assertEquals(r410715,b3.getLocation());
		assertEquals(r9101215,b4h3.getLocation());
		assertEquals(r9101215,b4h2.getLocation());
		assertEquals(r9101215,b4h1.getLocation());
		
		assertEquals(Color.blue, b1.getColor());
		assertEquals(Color.magenta, b2.getColor());
		assertEquals(Color.gray, b3.getColor());
		assertEquals(Color.red, b4h3.getColor());
		assertEquals(Color.orange, b4h2.getColor());
		assertEquals(Color.yellow, b4h1.getColor());
		
		assertEquals(3, b4h3.getHealth());
		assertEquals(2, b4h2.getHealth());
		assertEquals(1, b4h1.getHealth());
		
		assertEquals(false, b1.getMakeSupercharged());
		assertEquals(true, b2.getMakeSupercharged());
		assertEquals(false, b3.getMakeSupercharged());
		assertEquals(false, b4h3.getMakeSupercharged());
		assertEquals(false, b4h2.getMakeSupercharged());
		assertEquals(false, b4h1.getMakeSupercharged());
		
		assertEquals(false, b1.getMakeReplicatorPaddle());
		assertEquals(false, b2.getMakeReplicatorPaddle());
		assertEquals(true, b3.getMakeReplicatorPaddle());
		assertEquals(false, b4h3.getMakeReplicatorPaddle());
		assertEquals(false, b4h2.getMakeReplicatorPaddle());
		assertEquals(false, b4h1.getMakeReplicatorPaddle());
			
		assertEquals(1, b1.getHealth());
		assertEquals(1, b2.getHealth());
		assertEquals(1, b3.getHealth());
		assertEquals(3, b4h3.getHealth());
		assertEquals(2, b4h2.getHealth());
		assertEquals(1, b4h1.getHealth());
	}

}
