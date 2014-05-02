package HW11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadCities {
    final static String filePath = "src/wg22_xy.txt";
    final static int numCities = 20;

    static City[] read() {
        
        
        City[] cities = new City[numCities];
        int i = 0;
        
        BufferedReader reader;
        try {
            FileReader fr = new FileReader(filePath);            
            reader = new BufferedReader(fr);
            String line = null;
            while ((line = reader.readLine()) != null && i < numCities) {
                try {
                    //parseLine(line);
                    String[] xy = line.trim().split("\\s+");
                    Integer x = ((int) Double.parseDouble(xy[0])+120)*2;
                    Integer y = ((int) Double.parseDouble(xy[1])+150)*2;
                    cities[i++] = new City(x, y);                    
                }catch(IllegalArgumentException e) {
                    System.out.println(e.getMessage() + '\n' + line);
                }
            }
            reader.close();
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
        
        return cities;
        
    }
    
    public static void main(String[] args) {
        City[] cities = ReadCities.read();
        for (int i = 0; i < cities.length; i++) {
            System.out.printf("%3d -> %s\n", i, cities[i]);
        }
    }
}
