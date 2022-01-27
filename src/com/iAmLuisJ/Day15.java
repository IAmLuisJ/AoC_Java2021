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
        myScan.close();
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
                bestPath = true;
                break;
            }
        }
        riskLevel = ScanMap(cavern);

        System.out.println("risk level is" + riskLevel);
        System.out.println("Done");
    }

    public static int ScanMap(ArrayList<ArrayList<Integer>> cavern) {
        // Cavern is a 2D arraylist with all the points of the cavern
        // Create another 2D arraylist to hold the riskSum from one point to 0,0
        ArrayList<ArrayList<Integer>> riskSums = new ArrayList<>();
        // initialize risksum at every node to infinity, until we check its actual risk
        // sum
        for (int i = 0; i < cavern.size(); i++) { // each row
            ArrayList<Integer> r = new ArrayList<>();
            for (int c = 0; c > cavern.get(0).size(); c++) { // each column
                r.add(100000); // init inifinity (using high number to avoid overflow)
            }
            riskSums.add(i, r);

        }
        // set risk level of initial point to 0
        riskSums.get(0).set(0, 0);

        // loop through riskSums array, checking distance from neighbor to start
        // location
        for (int y = 0; y < cavern.size() - 1; y++) {
            for (int x = 0; x < cavern.get(0).size(); x++) {
                int minRisk = Integer.MAX_VALUE;
                int currentRisk = riskSums.get(y).get(x);
                // right neighbor
                if (x + 1 < cavern.get(y).size()) { // check to make sure it isnt out of bounds
                    int newRisk = cavern.get(y).get(x + 1); // risk adding moving to that spot
                    riskSums.get(y).set(x + 1, currentRisk + newRisk); // set the risksum of the space
                    minRisk = Math.min(minRisk, 2);
                }
                // top neighbor
                if (y - 1 > 0) {

                }

                // left neighbor
                if (x - 1 < 0) {

                }

                // below neighbor
                if (y + 1 < cavern.size()) {
                    minRisk = Math.min(minRisk, currentRisk + cavern.get(y + 1).get(x));
                }
                // if this path is lower risk than old path, switch to this path?

                // check each neighbor, add neighbors risk to the map?
                //
                // riskSums
                // 037900
                // 000000

                // riskMap
                // 123456
                // 432131
            }
        }

        return 0;
    }
}
