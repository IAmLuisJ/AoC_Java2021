package com.iAmLuisJ;

import com.iAmLuisJ.utilityClasses.Coordinate;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day13 {

    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("/Users/luisjuarez/GitHub/AOC_Java/data/day13/input.txt");
        Scanner scanner = new Scanner(input);
        ArrayList<Coordinate> coords = new ArrayList<>();
        ArrayList<String> instructions = new ArrayList<>();
        boolean isCoord = true;

        // parse the input
        while (scanner.hasNextLine()) {

            String line = scanner.nextLine();
            // check if its coordinates
            if (line.isEmpty()) { // if its a blank line,
                isCoord = false;
                System.out.println("All coords scanned");
                line = scanner.nextLine();
            }
            if (isCoord) {
                // if it is, put it in an array
                String[] xyVals = line.split(",");
                Coordinate d = new Coordinate(xyVals[0], xyVals[1]);
                coords.add(d);
            } else {
                instructions.add(line);
            }
        }
        scanner.close();
        // write methods to transform the grid

        // print grid before instructions
        printGrid(coords);
        // loop through instructions
        for (String fold : instructions) {
            String axis = fold.substring(11, 12);
            String axisVal = fold.substring(13);
            int foldVal = Integer.parseInt(axisVal);
            // execute folds
            //for an x fold, the formula should be new xval = xval - (foldX *2)
            if(axis.equals("x")) {
                for(Coordinate dot : coords) {
                    var x = dot.getX();
                    if(x > foldVal) {
                        var distanceX = (x - foldVal) * 2;
                        var newX = x - distanceX;
                        if(newX > -1) {
                            dot.setX(newX);
                        }
                    }
                }
            }
            if(axis.equals("y")) {
                for(Coordinate dot : coords) {
                    var y = dot.getY();
                    if(y > foldVal) {
                        var distanceY = (y - foldVal) * 2;
                        var newY = y - distanceY;
                        if(newY > -1) {
                            dot.setY(newY);
                        }
                    }
                }
            }

            System.out.println(fold);
            // print grid to see fold
            printGrid(coords);
            System.out.println(" ");
        }
        System.out.println("Final answer");
        printGrid(coords);

    }

    static void printGrid(ArrayList<Coordinate> grid) {
        int dotCount = 0;

        System.out.println("Start of Grid");
        // get the highest x and y values
        var maxX = 0;
        var maxY = 0;
        for (Coordinate coordinate : grid) {
            if (coordinate.getX() > maxX) {
                maxX = coordinate.getX();
            }
            if (coordinate.getY() > maxY) {
                maxY = coordinate.getY();
            }
        }

        for (int i = 0; i < maxY +1; i++) {
            for (int j = 0; j < maxX+1; j++) {
                // print either a . or a #
                Coordinate newCord = new Coordinate(j, i);
                if (grid.contains(newCord)) {
                    System.out.print("#");
                    dotCount++;
                } else {
                    System.out.print(".");
                }
            }
            System.out.println("|");
        }
        System.out.println("End of Grid");
        System.out.println("Dot count is " + dotCount);
    }
}
