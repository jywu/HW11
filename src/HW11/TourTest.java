package HW11;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TourTest {
	
	Tour tr;
	static TSP tsp;
	City[] cities;
	double dis = 0;

	@Before
	public void setUp() throws Exception {
		tsp = new TSP();
		cities = tsp.cities;
		tr = Tour.rndTour();
	}

	@Test
	public void testDistance() {
		for (int i = 0; i < cities.length-1; i++) {
			dis += Math.sqrt(Math.pow(cities[i].x-cities[i+1].x, 2)+Math.pow(cities[i].y-cities[i+1].y, 2));
		}
		assertTrue(new Double(dis).equals(tr.distance()));
	}

	@Test
	public void testCrossOver() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testIsIn() {
		int key = 1;
		int[] arr = {1,2,3,4,5};
		assertTrue(tr.isIn(key, arr, 0, 4));
		assertTrue(tr.isIn(key, arr, 0, 0));
		assertFalse(tr.isIn(key, arr, 1, 4));
	}

}
