package com.iAmLuisJ;

import com.iAmLuisJ.utilityClasses.Coordinate;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day13 {

    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("C:\\Users\\a1016060\\GitHub\\AoC 2021\\aoc\\src\\Day13test.txt");
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

            // print grid to see fold
            printGrid(coords);
            // execute folds

            System.out.println(" ");
        }
        System.out.println("Final answer");
        printGrid(coords);

    }

    static void printGrid(ArrayList<Coordinate> grid) {
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

        for (int i = 0; i < maxX; i++) {
            for (int j = 0; j < maxY; j++) {
                // print either a . or a #
                Coordinate newCord = new Coordinate(i, j);
                if (grid.contains(newCord)) {
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }

            }
            System.out.println("|");
        }
    }
}
