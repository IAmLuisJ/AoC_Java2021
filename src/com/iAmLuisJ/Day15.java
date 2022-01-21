package com.iAmLuisJ;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day15 {
    public static void main(String[] args) throws FileNotFoundException {
        boolean bestPath = false;
        int riskLevel = 0;
        File input2 = new File("C:\\Users\\a1016060\\GitHub\\AoC_Java\\data\\Day15\\testdata.txt");
        Scanner myScan = new Scanner(input2);
        ArrayList<ArrayList<Integer>> cavern = new ArrayList<>();

        while (myScan.hasNextLine()) { // parse data
            var currentLine = myScan.nextLine();
            ArrayList<Integer> row = new ArrayList<>();
            var items = currentLine.split("");
            for (String num : items) {
                int chiton = Integer.parseInt(num);
                row.add(chiton);
            }
            cavern.add(row);
        }
        // start at top right
        int x = 0;
        int y = 0;
        // loop through path
        while (bestPath == false) {
            // current chiton
            var currentNum = cavern.get(x).get(y);
            // pull neighbors
            try {
                var rightNeighbor = cavern.get(x + 1).get(y);
                var belowNeighbor = cavern.get(x).get(y + 1);
                // check which path is lower risk
                if (rightNeighbor > belowNeighbor) {
                    x = x + 1;
                } else if (belowNeighbor >= rightNeighbor) {
                    y = y + 1;
                }
                System.out.println("Moving to " + x + " " + y);
                System.out.println("adding " + riskLevel);
                riskLevel += cavern.get(x).get(y);
            } catch (Exception e) {
                // TODO: handle exception
                bestPath = true;
                break;
            }

        }
        System.out.println("risk level is" + riskLevel);
        System.out.println("Done");
    }
}
