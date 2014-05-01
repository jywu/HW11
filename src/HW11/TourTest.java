package HW11;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TourTest {
	
	Tour tr;
	static TSP tsp;
	City[] cities;
	int[] indices;
	double dis = 0;

	@Before
	public void setUp() throws Exception {
		tsp = new TSP();
		cities = tsp.cities;
		tr = Tour.rndTour();
		indices = tr.index;
	}

	@Test
	public void testDistance() {
		for (int i = 0; i < cities.length-1; i++) {
			dis += Math.sqrt(Math.pow(cities[indices[i]].x-cities[indices[i+1]].x, 2)+Math.pow(cities[indices[i]].y-cities[indices[i+1]].y, 2));
		}
		assertTrue(new Double(dis).equals(tr.distance()));
	}

	@Test
	public void testCrossOver() {
		int[] indices1 = {0,1,16,11,25,23,6,30,13,10,8,14,12,31,3,20,19,17,22,15,9,7,18,21,27,4,29,28,26,5,24,2};
		int[] indices2 = {0,6,25,24,8,20,10,2,4,28,27,22,12,29,14,19,30,17,7,18,5,16,13,15,3,26,21,1,23,9,31,11};
		int[] indices3 = {0,1,16,11,25,23,6,8,13,10,27,22,12,29,14,19,30,17,7,18,5,31,3,21,20,4,15,28,26,9,24,2};
		tr.index = indices1;
		Tour p2 = Tour.sequence();
		p2.index = indices2;
		int x1 = 10, x2 = 20;
		Tour child = tr.crossOver(p2, x1, x2);
		assertArrayEquals(child.index, indices3);
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
