package com.iAmLuisJ;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day18 {
    public static void main(String[] args) throws FileNotFoundException {
        // SnailFish Math
        // get input
        File myFile = new File("/Users/luisjuarez/GitHub/AOC_Java/data/Day18/testdata.txt");
        Scanner myScan = new Scanner(myFile);
        ArrayList<String> homework = new ArrayList<>();

        while (myScan.hasNext()) {
            String line = myScan.nextLine();
            homework.add(line);
        }
        myScan.close();
        System.out.println("parsed");
        // [[[0,[5,8]],[[1,7],[9,6]]],[[4,[1,2]],[[1,4],2]]]
        System.out.println(homework.get(0));
        //calling parseFish Method
        parseFish(homework.get(0));
        // further parse the snailfish numbers?
        var commaSplit = homework.get(0).split("\\[");
        System.out.println(commaSplit);
        // Do math

        // If any pair is nested inside four pairs, the leftmost such pair explodes.

        // If any regular number is 10 or greater, the leftmost such regular number
        // splits.

        // reduce

        // calculate magnitude
        // The magnitude of a pair is 3 times the magnitude of its left element plus 2
        // times the magnitude of its right element. The magnitude of a regular number
        // is just that number.
    }

    public static void parseFish(String line) {
        //method parse
        //takes in a line of snailfish, creates a snailfish, then recursively checks if the child is a pair or a number,
        //if its a number, end loop
        //if its a pair, parse again



        //returns object

    }
}
