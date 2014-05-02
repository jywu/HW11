package HW11;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

class City {

    int x, y;

    City(int x, int y) {
        this.x = x;
        this.y = y;
    }
    

    double distance(City c) {
        // TODO return distance to city c. Euclidean distance
        return Math.sqrt(Math.pow(x-c.x, 2) + Math.pow(y-c.y, 2));
    }

    public String toString() {
        return String.format("<City: %3d, %3d>", this.x, this.y);
    }
    
    // TODO
    // move the method displayCity from Map.java to here
    // and call it display
    // you will need to import some packages too
    void displayCity(Graphics g, Map map, int n) {
        g.setColor(map.CITY_COLOR);
        g.fillOval(x - Map.CITY_SIZE / 2, y - Map.CITY_SIZE / 2, Map.CITY_SIZE, Map.CITY_SIZE);
        g.setColor(Color.YELLOW);
        g.setFont(new Font("SansSerif", Font.BOLD, 16));
        g.drawString(Integer.toString(n), x + Map.CITY_SIZE / 2, y - Map.CITY_SIZE / 2);

    }

}
