package com.iAmLuisJ;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class Day10 {

    public static void main(String[] args) throws FileNotFoundException {
        int totalScore = 0;
        //pairs
        HashMap<String, String> bracketPairs = new HashMap<String, String>();
        bracketPairs.put("(", ")");
        bracketPairs.put("[", "]");
        bracketPairs.put("{", "}");
        bracketPairs.put("<", ">");

        //point system
        HashMap<String, Integer> points = new HashMap<>();
        points.put(")", 3);
        points.put("]", 57);
        points.put("}", 1197);
        points.put(">", 25137);

        //read in the input
//      File input = new File("/Users/luisjuarez/GitHub/AOC_Java/data/day10/input.txt");
        File input = new File("/Users/luisjuarez/GitHub/AOC_Java/data/day10/input.txt");
        Scanner scanner = new Scanner(input);


        while(scanner.hasNextLine()) {
            Stack<String> stack = new Stack<>();
            //loop through each line
            //pass current line to string
            String line = scanner.nextLine();
            //breakup line into charArray
            for(char i: line.toCharArray()) {
                //cast char to string
                String character = String.valueOf(i);
                //check if character has a pair
                if(bracketPairs.containsKey(character)) {
                    stack.push(character);
                } else {
                    String closing = stack.pop();
                    if(!bracketPairs.get(closing).equals(character)) {
                        int score = points.get(character);
                        totalScore += score;
                    };
                }
            }



        }
        System.out.println(totalScore);
        scanner.close();
    }

}
