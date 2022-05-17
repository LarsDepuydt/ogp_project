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
	Ball b1;
	

	@BeforeEach
	void setUp() throws Exception {
		p11 = new Point(1, 1);
		p83 = new Point(8, 3);
		r1183 = new Rect(p11, p83);
		
		p57 = new Point(5, 7);
		c572 = new Circle(p57, 2);
		vm1m8 = new Vector(-1, -8);
		a1 = new Alpha(c572, vm1m8);
		b1 = new NormalBall(c572, new Vector(10, 10).plus(new Vector(-2, -2)));
	}
	
	@Test
	void testAlpha() {
		assertEquals(a1.getColor(), Color.blue);
		assertEquals(a1.getLocation(), c572);
		assertEquals(a1.getVelocity(),vm1m8);
		Set<Alpha> emptySet = new HashSet<Alpha> ();
		assertEquals(a1.getLinkedBalls(),emptySet);
	}
	
	@Test
	void testNoInteractionBlocks() {
		a1.move(new Vector(-2, -16), 0);
		assertEquals(a1.getVelocity(), vm1m8);
		assertEquals(a1.getLocation().getCenter(), new Point(3, -9));
	}
	
	@Test
	void testMove() {
		a1.move(new Vector(-1, -8), 0);
		assertEquals(a1.getVelocity(), vm1m8);
		assertEquals(a1.getLocation().getCenter(), new Point(4,-1));
	}
	
	@Test
	void testHitWall() {
		a1.hitWall(new Rect(new Point(3, 4),new Point(8, 6)));
		assertEquals(a1.getVelocity(), new Vector(-1, 8));
		assertEquals(a1.getLocation(), c572);
	}
	
	@Test
	void testHitPaddle() {
		a1.setLocation(c572);
		a1.setVelocity(new Vector(10, 10));
		a1.hitPaddle(new Rect(new Point(3, 7), new Point(8, 8)), new Vector(10, 0));
		assertEquals(a1.getLocation(), c572);
		assertEquals(a1.getVelocity(), new Vector(10, -10).plus(new Vector(10, 0).scaledDiv(5))); //testen of er een ball wordt gemaakt gaat niet, dat is een methode in breakoutstate.
	}
	
	@Test
	void testLinks() {
		b1.addLink(a1);
		Set<Ball> sb1 = new HashSet<>();
		sb1.add(b1);
		assertEquals(a1.getLinkedBalls(), sb1);
		b1.removeLink(a1);
		Set<Ball> emptySet = new HashSet<>();
		assertEquals(a1.getLinkedBalls(), emptySet);
	}
}
