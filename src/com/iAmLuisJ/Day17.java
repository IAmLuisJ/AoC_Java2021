package com.iAmLuisJ;

import java.io.*;
import java.util.*;
import com.iAmLuisJ.utilityClasses.Probe;

public class Day17 {
    public static void main(String[] args) throws FileNotFoundException {
        int countSteps = 0;
        File input = new File("C:\\Users\\a1016060\\GitHub\\AoC_Java\\data\\Day17\\input.txt");
        Scanner myScan = new Scanner(input);
        var targetArea = myScan.nextLine();
        myScan.close();

        // grab x range
        var xRange = targetArea.substring(targetArea.indexOf("x"), targetArea.indexOf(","));
        var yRange = targetArea.substring(targetArea.indexOf("y"));
        int xMin = Integer.parseInt(xRange.substring(2, xRange.indexOf("..")));
        int xMax = Integer.parseInt(xRange.substring(xRange.indexOf("..") + 2));

        int yMin = Integer.parseInt(yRange.substring(2, yRange.indexOf("..")));
        int yMax = Integer.parseInt(yRange.substring(yRange.indexOf("..") + 2));
        System.out.println("Check");

        Probe probe = new Probe(0, 0);
        // start with a Y velocity, then increase until highest
        int initX = xMax;
        int initY = yMax;
        probe.setXVelocity(initX);
        probe.setYVelocity(initY);

        Boolean launched = true;
        int maxYPossible = 0;
        int winCount = 0;

        // for loop to keep changing velocity
        for (int i = -100; i < 450; i++) {
            for (int j = -100; j < 450; j++) {
                int highY = Integer.MIN_VALUE;
                probe.setX(0);
                probe.setY(0);
                probe.setXVelocity(i);
                probe.setYVelocity(j);

                launched = true;
                countSteps = 0;

                while (launched) {
                    // step
                    countSteps++;
                    step(probe);
                    // check if its in the zone
                    var x = probe.getX();
                    var y = probe.getY();

                    // check its highest y
                    if (y > highY) {
                        highY = y;
                    }

                    if (x >= xMin && x <= xMax && y <= yMax && y >= yMin) {
                        // x is in the zone
                        winCount++;
                        System.out.println(" ");
                        System.out.println("Launched from " + i + " " + j);
                        System.out.println("In the zone! at x:" + x + " and y:" + y);
                        System.out.println("Steps " + countSteps);
                        System.out.println("Highest Y was " + highY);
                        System.out.println(" ");
                        if (highY > maxYPossible) {
                            maxYPossible = highY;
                        }

                        launched = false;
                    }
                    // if x and y are past the zone, fail
                    if ((x > xMax) || (y < yMin)) {
                        // System.out.println("Fail");
                        launched = false;

                    }

                }

            }
        }
        System.out.println("highest y was " + maxYPossible);
        System.out.println("Wincount is " + winCount);
    }

    public static void step(Probe probe) {
        // The probe's x position increases by its x velocity.
        probe.setX(probe.getX() + probe.getXVelocity());
        // The probe's y position increases by its y velocity.
        probe.setY(probe.getY() + probe.getYVelocity());
        // Due to drag, the probe's x velocity changes by 1 toward the value 0; that is,
        // it decreases by 1 if it is greater than 0, increases by 1 if it is less than
        // 0, or does not change if it is already 0.
        if (probe.getXVelocity() > 0) {
            probe.setXVelocity(probe.getXVelocity() - 1);
        }
        if (probe.getXVelocity() < 0) {
            probe.setXVelocity(probe.getXVelocity() + 1);
        }
        // Due to gravity, the probe's y velocity decreases by 1.
        probe.setYVelocity(probe.getYVelocity() - 1);
    }

}
