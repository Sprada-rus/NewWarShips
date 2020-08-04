package bin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CreateShips {
    private static final String alphabet = "абвгд";
    private int gridLen = 5;
    private int gridSize = 25;
    private int [] grid = new int[gridSize];
    private int shipCount = 0;
    private int row;
    private int column;

    public String getUserInput(String number){
        String inputLine = null;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            inputLine = bf.readLine();
            if (inputLine.length() == 0) return null;
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return inputLine.toLowerCase();
    }

    public ArrayList<String> placeShips(int shipSize){
        ArrayList<String> alphaCells = new ArrayList<String>();
        String [] alphaCodes = new String[shipSize];
        String temp = null;
        int [] coords = new int[shipSize];
        int attempts = 0;
        boolean success = false;
        int location = 0;

        shipCount++;
        int incr = 1;
        if ((shipCount % 2) == 1){
            incr = gridLen;
        }

        while (!success & attempts++ < 200){
            location = (int) (Math.random() * gridSize);
            System.out.println("Try location " + location);
            int x = 0;
            success = true;
            while (success && x < shipSize){
                if (grid[location] == 0){
                    coords[x++] = location;
                    location += incr;
                    if (location >= gridSize){
                        success = false;
                    }
                    if (x > 0 && (location % gridLen == 0)){
                        success = false;
                    }
                } else {
                    System.out.println("Used location " + location);
                    success = false;
                }
            }
        }

        int x = 0;
        int column = 0;
        int row = 0;

        while (x < shipSize){
            grid[coords[x]] = 1;
            row = (int) (coords[x] / gridLen);
            column = coords[x] % gridLen;

            temp = String.valueOf(alphabet.charAt(column));

            alphaCells.add(temp.concat(Integer.toString(row+1)));
            x++;
            System.out.println("Coords ships " + " = " + alphaCells.get(x-1));
        }
        return alphaCells;
    }
}
