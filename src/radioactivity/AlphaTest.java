package radioactivity;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import utils.Circle;
import utils.Point;
import utils.Rect;
import utils.Vector;

class AlphaTest {
	Point p11;
	Point p83;
	Rect r1183;
	
	Point p57;
	Circle c572;
	Vector vm1m8;
	Alpha a1;
	

	@BeforeEach
	void setUp() throws Exception {
		p11 = new Point(1, 1);
		p83 = new Point(8, 3);
		r1183 = new Rect(p11, p83);
		
		p57 = new Point(5, 7);
		c572 = new Circle(p57, 2);
		vm1m8 = new Vector(-1, -8);
		a1 = new Alpha(c572, vm1m8);
	}
	
	@Test
	void testAlpha() {
		assertEquals(a1.getColor(), Color.blue);
		assertEquals(a1.getLocation(), c572);
		assertEquals(a1.getVelocity(),vm1m8);
		Set<Alpha> emptySet = new HashSet<Alpha> ();
		assertEquals(a1.getLinkedBalls(),emptySet);
	}
}
