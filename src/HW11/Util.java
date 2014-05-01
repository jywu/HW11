package HW11;

import java.util.Random;

public class Util {
    static Random random = new Random();

    //TODO
    // return an random integer from m upto but NOT including n
    static int rndInt(int m, int n) {
        if (m>n) {
            throw new IllegalArgumentException();
        }
        // [m..n)
        return m + random.nextInt(n-m);
    }

    // TODO
    // count the number of occurrence of key from start to end
    static int count(int[] a, int key, int start, int end) {
        int count = 0;
        for(int i = start; i <= end; i++) {
            if(a[i] == key) count++;
        }
        return count;
    }

    static void pause(int n) {
        try {
            Thread.sleep(n);
        } catch (Exception e) {

        }
    }

}
