package breakout;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PaddleTest {
	Point p00;
	Point pm1500m250;
	Point p1500250;
	Rect r1500250;
	
	PaddleState npa00;
	PaddleState rpa00c0;
	PaddleState rpa00c1;
	PaddleState rpa00c2;
	PaddleState rpa00c3;

	@BeforeEach
	void setUp() throws Exception {
		p00 = new Point(0,0);
		pm1500m250 = new Point(-1500,-250);
		p1500250 = new Point(1500,250);
		r1500250 = new Rect(pm1500m250,p1500250);
		
		npa00 = new NormalPaddle(p00);
		rpa00c0 = new ReplicatorPaddle(p00,0);
		rpa00c1 = new ReplicatorPaddle(p00,1);
		rpa00c2 = new ReplicatorPaddle(p00,2);
	}

	@Test
	void testPaddle() {
		assertEquals(p00, npa00.getCenter());
		assertEquals(r1500250,npa00.getLocation());
		assertEquals(pm1500m250,npa00.getLocation().getTopLeft());
		assertEquals(p1500250,npa00.getLocation().getBottomRight());
		assertEquals(Color.green, npa00.getColor());
		
		assertEquals(p00, rpa00c0.getCenter());
		assertEquals(r1500250,rpa00c0.getLocation());
		assertEquals(pm1500m250,rpa00c0.getLocation().getTopLeft());
		assertEquals(p1500250,rpa00c0.getLocation().getBottomRight());
		assertEquals(0,rpa00c0.getReplicateCount());
		assertEquals(Color.darkGray, rpa00c0.getColor());
		
		assertEquals(p00, rpa00c1.getCenter());
		assertEquals(r1500250,rpa00c1.getLocation());
		assertEquals(pm1500m250,rpa00c1.getLocation().getTopLeft());
		assertEquals(p1500250,rpa00c1.getLocation().getBottomRight());
		assertEquals(1,rpa00c1.getReplicateCount());
		assertEquals(Color.lightGray, rpa00c1.getColor());
		
		assertEquals(p00, rpa00c2.getCenter());
		assertEquals(r1500250,rpa00c2.getLocation());
		assertEquals(pm1500m250,rpa00c2.getLocation().getTopLeft());
		assertEquals(p1500250,rpa00c2.getLocation().getBottomRight());
		assertEquals(2,rpa00c2.getReplicateCount());
		assertEquals(Color.gray, rpa00c2.getColor());
		
	}

}
