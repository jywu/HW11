package HW11;

import java.util.Arrays;

public class TSP {

	final City[] cities;

	Map map;

	final int POPL_SIZE = 1000;

	Tour[] new_pool, old_pool;

	static final double p_mutate = 0.1;

	TSP() {
		this.cities = GenerateCities.square(8);
		Tour.tsp = this;
		this.map = new Map(this);

		old_pool = new Tour[POPL_SIZE];
		new_pool = new Tour[POPL_SIZE];
	}

	int numCities() {
		return cities.length;
	}

	void run() {

		// create an initial random population of tours
		for (int i = 0; i < POPL_SIZE; i++) {
			old_pool[i] = Tour.rndTour();
		}

		Tour[] tmp_pool;

		// display initial random tours in map
		Arrays.sort(old_pool);
		old_pool[0].display(map);
		Util.pause(2000);

		// run one sweep of the GA 100 times
		for (int k = 0; k < 100; k++) {
			if (k % 1 == 0) { // change the 1 to what ever interval
				showPool(old_pool, String.format("Gen: %3d", k), 5);
				map.gen = k;
				// display to lowest distance tour
				old_pool[0].display(map);
				Util.pause(250);
				if (old_pool[0].distance == 4 * Map.WIDTH) {
					break;
				}
			}

			// reset the index; -- see below
			new_i = 0;

			for (int i = 0; i < POPL_SIZE / 2; i++) {
				do_ga(); // new children are added to new_pool
			}
			// interchange the new and old pools
			tmp_pool = old_pool;
			old_pool = new_pool;
			new_pool = tmp_pool;
			// sort the old_pool in ascending order
			Arrays.sort(old_pool);
		}
	}

	int new_i = 0; // index into the new_pool

	void do_ga() {

		// * SELECT two individuals to mate
		Tour[] pair = selectTwo();

		// * CROSS-OVER
		// determine two cross-over points
		int x1, x2;
		x1 = Util.rndInt(1, numCities() - 2);
		x2 = Util.rndInt(x1, numCities());

		// determine the children by first crossing pair[0] with pair[1]
		// and then crossing pair[1] with pair[0]
		Tour c0, c1;
		c0 = pair[0].crossOver(pair[1], x1, x2);
		c1 = pair[1].crossOver(pair[0], x1, x2);

		// * MUTATE both c0 and c1
		c0.mutate();
		c1.mutate();

		// finally add c0 and c1 to the new pool
		new_pool[new_i++] = c0;
		new_pool[new_i++] = c1;
	}

	// randomly select two parents from the more fit population to mate
	Tour[] selectTwo() {
		Tour[] pair = new Tour[2];

		int k = POPL_SIZE / 2;

		pair[0] = old_pool[Util.rndInt(0, k)];
		pair[1] = old_pool[Util.rndInt(0, k)];

		return pair;
	}

	public static void main(String[] args) {
		TSP tsp = new TSP();
		tsp.run();
	}

	void showPool(Tour[] pool, String msg, int n) {
		System.out.println(msg);
		for (int i = 0; i < n; i++) {
			System.out.printf("  %3d %s\n", i, pool[i]);
		}
	}

}
