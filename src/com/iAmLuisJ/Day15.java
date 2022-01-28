package com.iAmLuisJ;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import javax.sound.sampled.SourceDataLine;

public class Day15 {
    public static void main(String[] args) throws FileNotFoundException {

        File input2 = new File("C:\\Users\\a1016060\\GitHub\\AoC_Java\\data\\Day15\\testdata.txt");
        // File input2 = new
        // File("/Users/luisjuarez/GitHub/AOC_Java/data/Day15/secondtestinput.txt");
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
        // int x = 0;
        // int y = 0;
        // loop through path
        // while (bestPath == false) {
        // // current chiton
        // // var currentNum = cavern.get(x).get(y);
        // // pull neighbors
        // try {
        // var rightNeighbor = cavern.get(x + 1).get(y);
        // var belowNeighbor = cavern.get(x).get(y + 1);
        // // check which path is lower risk
        // if (rightNeighbor > belowNeighbor) {
        // x = x + 1;
        // } else if (belowNeighbor >= rightNeighbor) {
        // y = y + 1;
        // }
        // System.out.println("Moving to " + x + " " + y);
        // System.out.println("adding " + riskLevel);
        // riskLevel += cavern.get(x).get(y);
        // } catch (Exception e) {
        // bestPath = true;
        // break;
        // }
        // }
        System.out.println("Original map");
        printMap(cavern);
        System.out.println(" ");
        int optimal = ScanMap(cavern);
        var bigCavern = expandMap(cavern);
        var expandedOptimal = evaluateRisk(bigCavern);

        System.out.println("Optimal path using scanMap is " + optimal);
        System.out.println("Optimal path of bigger map using scanMap is " + expandedOptimal);
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
            for (int c = 0; c < cavern.get(0).size(); c++) { // each column
                r.add(1000000); // init inifinity (using high number to avoid overflow)
            }
            riskSums.add(i, r);
        }
        // set risk level of initial point to 0
        riskSums.get(0).set(0, 0);

        boolean changeMade = true;
        while (changeMade) {
            for (int y = 0; y < cavern.size(); y++) {
                for (int x = 0; x < cavern.get(0).size(); x++) {
                    changeMade = false;
                    int minRisk = Integer.MAX_VALUE;
                    int currentRisk = riskSums.get(y).get(x);
                    // right neighbor
                    if (x + 1 < cavern.get(0).size()) { // check to make sure it isnt out of bounds
                        int newRisk = cavern.get(y).get(x + 1); // risk adding moving to that spot
                        // riskSums.get(y).set(x + 1, currentRisk + newRisk); // set the risksum of the
                        // space
                        minRisk = Math.min(minRisk, riskSums.get(y).get(x + 1) + newRisk);
                    }
                    // top neighbor
                    if (y - 1 >= 0) {
                        int newRisk = cavern.get(y - 1).get(x);
                        // riskSums.get(y-1).set(x, currentRisk + newRisk);
                        minRisk = Math.min(minRisk, riskSums.get(y - 1).get(x) + newRisk);
                    }
                    // left neighbor
                    if (x - 1 >= 0) {
                        int newRisk = cavern.get(y).get(x - 1);
                        // riskSums.get(y).set(x-1, newRisk + currentRisk);
                        minRisk = Math.min(minRisk, riskSums.get(y).get(x - 1) + newRisk);
                    }
                    // below neighbor

                    if (y + 1 < riskSums.size()) {
                        int newRisk = cavern.get(y + 1).get(x);
                        // riskSums.get(y+1).set(x, currentRisk + cavern.get(y + 1).get(x));
                        minRisk = Math.min(minRisk, riskSums.get(y + 1).get(x) + newRisk);
                    }

                    // the risk to this node is the minimum path
                    if (currentRisk > minRisk) {
                        riskSums.get(y).set(x, minRisk);
                        changeMade = true;
                    }
                }
            }
        }
        // loop through riskSums array, checking distance from neighbor to start
        // location
        printMap(riskSums);
        // return bottom right riskSum
        return riskSums.get(cavern.size() - 1).get(cavern.get(0).size() - 1);
    }

    public static int evaluateRisk(int[][] riskMap) {
        // A 2d array to track the total risk involved to travel from each and every
        // node to the bottom-right corner of the map.
        int[][] riskSums = new int[riskMap.length][riskMap[0].length];
        // Initialize the riskSum at every node to a large number. 1,000,000 should do
        // fine. We can't use Integer.MAX_VALUE since we will loop through and possibly
        // add to these values. We don't want to overflow.
        for (int r = 0; r < riskSums.length; r++) {
            for (int c = 0; c < riskSums[0].length; c++) {
                riskSums[r][c] = 1_000_000;
            }
        }
        // The riskSum for the bottom-right node is 0.
        riskSums[riskSums.length - 1][riskSums[0].length - 1] = 0;

        // The idea is to loop through the riskSums array from the bottom-right to the
        // top-left and update the riskSum at each node based on the riskSums of it's
        // neighbors. We will change the riskSum at each node to reflect the minimum
        // risk+riskSum of each of it's neighbors. If a change is made to the map, we
        // will loop back through in case that change triggered another potential
        // improvement to the graph. We will continue to loop through until no changes
        // are made.
        boolean changeMade = true;
        while (changeMade) {
            changeMade = false;
            for (int r = riskSums.length - 1; r >= 0; r--) {
                for (int c = riskSums[0].length - 1; c >= 0; c--) {
                    // Four neighbors:
                    // riskMap[r][c] : the risk to enter this 1 node.
                    // riskSum[r][c] : the total risk involved in traveling to the bottom-right
                    // from this node.
                    int min = Integer.MAX_VALUE;
                    if (r - 1 >= 0)
                        min = Math.min(min, riskMap[r - 1][c] + riskSums[r - 1][c]);
                    if (r + 1 < riskSums.length)
                        min = Math.min(min, riskMap[r + 1][c] + riskSums[r + 1][c]);
                    if (c - 1 >= 0)
                        min = Math.min(min, riskMap[r][c - 1] + riskSums[r][c - 1]);
                    if (c + 1 < riskSums[0].length)
                        min = Math.min(min, riskMap[r][c + 1] + riskSums[r][c + 1]);

                    // If a change is being made to a node, we will have to loop back through again.
                    int oldRisk = riskSums[r][c];
                    riskSums[r][c] = Math.min(riskSums[r][c], min);
                    if (riskSums[r][c] != oldRisk)
                        changeMade = true;
                }
            }
        }
        // We now know the riskSum at every single node, but all we wanted was the
        // riskSum at 0,0.
        return (riskSums[0][0]);
    }

    public static int[][] expandMap(ArrayList<ArrayList<Integer>> map) {
        int[][] newMap = new int[map.size() * 5][map.get(0).size() * 5];
        // initialize risksum at every node to infinity, until we check its actual risk
        // sum
        for (int y = 0; y < map.size(); y++) { // each row
            for (int x = 0; x < map.get(0).size(); x++) { // each column
                int val = map.get(y).get(x); // currentValue

                for (int i = 0; i < 5; i++) { //
                    for (int j = 0; j < 5; j++) {
                        int newVal = val + i + j;
                        if (newVal > 9) { // wrap 9's back around
                            newVal -= 9;
                        }
                        int currY = y + i * map.size();
                        int currX = x + j * map.get(0).size();
                        newMap[currX][currY] = newVal;
                    }
                }
            }
        }
        System.out.println("expanded Map");
        print2DInt(newMap);
        return newMap;
    }

    public static void printMap(ArrayList<ArrayList<Integer>> map) {
        for (ArrayList<Integer> x : map) {
            for (Integer y : x) {
                System.out.print(" " + y + " ");
            }
            System.out.println(" ");
        }
    }

    public static void print2DInt(int[][] map) {
        for (int[] row : map) {
            for (int is : row) {
                System.out.print(" " + is + " ");
            }
            System.out.println(" ");
        }
    }
}
