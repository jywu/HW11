package HW11;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.lang.Comparable;
import java.util.Arrays;

public class Tour implements Comparable {

    static TSP tsp;

    int[] index; // indices into the array of cities in TSP.java

    double distance;

    private Tour() {
        this.index = new int[tsp.cities.length];
    }

    int length() {
        return index.length;
    }

    public int compareTo(Object obj) {
        Tour tr = (Tour) obj;
        if (this.distance < tr.distance) {
            return -1;
        } else if (this.distance == tr.distance) {
            return 0;
        } else {
            return +1;
        }
    }

    // the below constructor is used to make a clone
    Tour(Tour tr) {
        this();
        for (int i = 0; i < tr.length(); i++) {
            this.index[i] = tr.index[i];
        }
        this.distance = distance();
    }

    static Tour sequence() {
        Tour tr = new Tour();
        for (int i = 0; i < tr.length(); i++) {
            tr.index[i] = i;
        }
        tr.distance = tr.distance();
        return tr;
    }

    static Tour rndTour() {
        Tour tr = Tour.sequence();
        tr.randomize();
        tr.distance = tr.distance();
        return tr;
    }

    void randomize() {
        randomize(1, this.length());
    }

    void randomize(int i, int j) {
        for (int k = 0; k < (j - i + 1); k++) {
            rndSwapTwo(i, j);
        }
        this.distance = distance();
    }

    void rndSwapTwo() {
        // index 0 has the start city and doesn't change
        // hence the random#s are from 1 onwards
        rndSwapTwo(1, this.length());
    }

    // randomly swap two cities between [i,j)
    void rndSwapTwo(int i, int j) {
        int a, b;
        int tmp;

        a = Util.rndInt(i, j);
        b = Util.rndInt(i, j);
        tmp = index[a];
        index[a] = index[b];
        index[b] = tmp;
    }

    City city(int i) {
        return tsp.cities[this.index[i]];
    }

    //TODO
    // calculate the distance of the whole tour
    double distance() {
        double d = 0;
        City[] cities = tsp.cities;
        for(int i=1; i < index.length; i++) {
            d += cities[i-1].distance(cities[i]);
        }
        return d;
    }

    public String toString() {
        String s = String.format("%6.1f :", this.distance);
        for (int i = 0; i < this.length(); i++) {
            s += String.format("%3d", index[i]);
        }
        return s;
    }

    void mutate() {
        if (Math.random() < TSP.p_mutate) {
            // randomly flip two cities
            rndSwapTwo();
            this.distance = distance();
        }
    }

    Tour crossOver(Tour p2, int x1, int x2) {
        // make an empty tour and then fill it in
        Tour child = new Tour();

        int i = 0;
        // copy the 1st segment of (this) to the child
        // *** TODO ***
        for(int j = 0; j < x1; j++, i++) {
            child.index[i] = index[j];
        }
        // copy the cross-over portion of p2 to the child
        for(int j = x1; j <= x2; j++, i++) {
            child.index[i] = p2.index[j];
        }
        // copy the last segment of (this) to the child
        for(int j = x2+1; j < index.length; j++, i++) {
            child.index[i] = index[j];
        }

        // Now we need to correct the child for any duplicate cities

        // First find out the unique elements of the cross-over segment
        // i.e., those elements of the cross-over segment of
        // p1 that are not in p2
        // *** TODO ***
        int[] uniq = new int[x2 - x1 + 1];
        for(int j = 0; j < uniq.length; j++) {
            uniq[j] = -1;
        }
        int k = 0;
        int[] middle1 = Arrays.copyOfRange(index, x1, x2+1);
        int[] middle2 = Arrays.copyOfRange(p2.index, x1, x2+1);
        Arrays.sort(middle2);
        for(int j = 0; j < uniq.length; j++) {
            if(!isIn(middle1[j], middle2, 0, middle2.length-1)) {
                uniq[k] = middle1[j];
                k++;
            }
        }		

        // scan the two portions of p1 that have been crossed into the
        // the child for any duplicates in the crossed-over 
        // segment and if so replace with an element from the uniq list
        // *** TODO ***
        k = 0;
        for(int j = 0; j < x1; j++) {
            int current = child.index[j];
            if(isIn(current, middle2, 0, middle2.length-1)) {
                child.index[j] = uniq[k];
                while(uniq[k] == -1) {
                    k = (k+1) % uniq.length; 
                }                             
            }
        }
        for(int j = x2+1; j < child.index.length; j++) {
            int current = child.index[j];
            if(isIn(current, middle2, 0, middle2.length-1)) {
                child.index[j] = uniq[k];
                while(uniq[k] == -1) {
                    k = (k+1) % uniq.length; 
                }               
            }            
        }

        child.distance = child.distance();
        return child;
    }

    void display(Map m) {
        m.tour = this;
        m.update(m.getGraphics());
    }

    void display(Graphics g) {
        final int SIZE = 2 * Map.CITY_SIZE;
        // plot the tour
        int len = this.length();
        int x1, y1, x2, y2;
        int i;
        for (i = 0; i < len - 1; i++) {
            x1 = city(i).x;
            y1 = city(i).y;
            x2 = city(i + 1).x;
            y2 = city(i + 1).y;
            g.setColor(Color.CYAN);
            g.drawLine(x1, y1, x2, y2);
            g.setColor(Color.RED);
            g.setFont(new Font("SansSerif", Font.BOLD, 16));
            g.drawString(Integer.toString(i), x1 - SIZE, y1 + SIZE);
        }
        // close the loop
        g.setColor(Color.CYAN);
        x1 = city(len - 1).x;
        y1 = city(len - 1).y;
        x2 = city(0).x;
        y2 = city(0).y;
        g.drawLine(x1, y1, x2, y2);
        g.setColor(Color.RED);
        g.drawString(Integer.toString(i), x1 - SIZE, y1 + SIZE);

    }

    boolean isIn(int key, int[] sortedArr, int start, int end) {
        if(start > end) {
            return false;
        }
        int mid = (start + end)/2;
        if(sortedArr[mid] == key) return true;
        else if(sortedArr[mid] < key) return isIn(key, sortedArr, mid+1, end);
        else return isIn(key, sortedArr, start, mid-1);
    }
}
