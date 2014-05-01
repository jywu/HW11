package HW11;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CityTest {
	
	City city1;
	City city2;
	int x1;
	int y1;
	int x2;
	int y2;
	double dis;
	
	@Before
	public void setUp() throws Exception {
		x1 = Util.random.nextInt(100);
		y1 = Util.random.nextInt(100);
		x2 = Util.random.nextInt(100);
		y2 = Util.random.nextInt(100);
		dis = Math.sqrt(Math.pow(x1-x2,2) + Math.pow(y1-y2,2));
		city1 = new City(x1, y1);
		city2 = new City(x2, y2);
	}

	@Test
	public void testDistance() {
		assertTrue(new Double(city1.distance(city2)).equals(city2.distance(city1)));
		assertTrue(new Double(dis).equals(city1.distance(city2)));
		assertTrue(new Double(dis).equals(city2.distance(city1)));
	}

}
