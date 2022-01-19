package com.iAmLuisJ;

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

        // loop through instructions
        for (String fold : instructions) {
            String axis = fold.substring(11, 12);
            String axisVal = fold.substring(13);
            // execute folds
        }

    }
}
