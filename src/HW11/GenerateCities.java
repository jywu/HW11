package HW11;

public class GenerateCities {

    static City[] square(int N) {
        // N is the # of cities per side of the square

        City[] cities = new City[4 * N];

        // top-left corner of the plotted square of cities
        int x0 = 50;
        int y0 = 60;

        // width of the square of cities
        int width = Map.WIDTH;


        int[] horz = new SplitInterval(x0, x0 + width, N).getIntervals();
        int[] vert = new SplitInterval(y0, y0 + width, N).getIntervals();

        int i = 0;

        // top row
        for (int j = 0; j < horz.length; j++) {
            cities[i] = new City(horz[j], y0);
            i++;
        }
        // right column
        // note that j starts from 1 and not 0 so that we don't
        // recomputer the top-right corner
        for (int j = 1; j < vert.length; j++) {
            cities[i] = new City(x0 + width, vert[j]);
            i++;
        }
        // bottom row
        for (int j = horz.length - 1 - 1; j >= 0; j--) {
            cities[i] = new City(horz[j], y0 + width);
            i++;
        }
        // left columns
        // note that j>0 so that we don't redo (x0,y0)
        for (int j = vert.length - 1 - 1; j > 0; j--) {
            cities[i] = new City(x0, vert[j]);
            i++;
        }
        return cities;
    }

    public static void main(String[] args) {
        City[] cities = GenerateCities.square(5);

        for (int i = 0; i < cities.length; i++) {
            System.out.printf("%3d -> %s\n", i, cities[i]);
        }
    }

}
