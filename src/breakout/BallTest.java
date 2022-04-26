package breakout;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BallTest {
	
	Point p53;
	Point p910;
	Rect r53910;
	
	Point p23;
	Circle c231;
	Vector v1010;
	Point p143;
	Circle c1432;
	Vector vm1010;
	Point p611;
	Circle c6112;
	Vector v10m10;
	Ball b1;
	Ball b2;
	Ball b3;
	
	Vector v2020;
	Point p1213;
	Point p2223;
	int mf1;
	int mf5;
	int t;
	
	@BeforeEach
	void setUp() throws Exception {
		p53 = new Point(5,3);
		p910 = new Point(9,10);
		r53910 = new Rect(p53,p910);
		
		p23 = new Point(2,3);
		c231 = new Circle(p23,1);
		v1010 = new Vector(10,10);
		p143 = new Point(14,3);
		c1432 = new Circle(p143,2);
		vm1010 = new Vector(-10,10);
		p611 = new Point(6,11);
		c6112 = new Circle(p611,2);
		v10m10 = new Vector(10,-10);
		b1 = new NormalBall(c231,v1010);
		b2 = new SuperchargedBall(c1432,vm1010);
		b3 = new NormalBall(c6112,v10m10);
		
		v2020 = new Vector(20,20);
		p1213 = new Point(12,13);
		p2223 = new Point(22,23);
		mf1 = 1;
		mf5 = 5*1000;
		t = 10*1000;
	}

	@Test
	void testBall() {
		assertEquals(p23, b1.getLocation().getCenter());
		assertEquals(1, b1.getLocation().getDiameter());
		assertEquals(c231,b1.getLocation());
		assertEquals(v1010, b1.getVelocity());
		
		assertEquals(p143, b2.getLocation().getCenter());
		assertEquals(2, b2.getLocation().getDiameter());
		assertEquals(c1432,b2.getLocation());
		assertEquals(vm1010, b2.getVelocity());
		
		assertEquals(p611, b3.getLocation().getCenter());
		assertEquals(2, b3.getLocation().getDiameter());
		assertEquals(c6112,b3.getLocation());
		assertEquals(v10m10, b3.getVelocity());
		
	}

	@Test
	void testhitBlock() {
		assertEquals(null,b1.hitBlock(r53910, false));
		assertEquals(null,b1.hitBlock(r53910, true));
		
		assertEquals(null,b2.hitBlock(r53910, false));
		assertEquals(null,b2.hitBlock(r53910, true));
		
		assertEquals(v1010,b3.hitBlock(r53910, false));
		assertEquals(v1010,b3.hitBlock(r53910, true));
	}
	
	@Test 
	void testmoveForward() {
		b1.moveForward(mf1);
		assertEquals(p1213, b1.getLocation().getCenter());
		
		b1.setCenter(c231);
		b1.setVelocity(v2020);
		b1.moveForward(mf1);
		assertEquals(p2223, b1.getLocation().getCenter());
	}
	
	@Test
	void testgetTimeLeft() {
		assertEquals(t,b2.getTimeLeft());
		b2.moveForward(mf5);
		assertEquals(mf5, b2.getTimeLeft());
	}

}
