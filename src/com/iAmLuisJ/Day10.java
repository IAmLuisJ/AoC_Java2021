package com.iAmLuisJ;
import java.io.*;
import java.util.*;

public class Day10 {

    public static void main(String[] args) throws FileNotFoundException {
        int totalScore = 0;
        long totalScorePart2 = 0;
        ArrayList<Long> scoreBoard = new ArrayList<>();
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

        HashMap<String, Integer> points2 = new HashMap<>();
        points2.put(")", 1);
        points2.put("]", 2);
        points2.put("}", 3);
        points2.put(">", 4);

        //read in the input
        File input = new File("/Users/luisjuarez/GitHub/AOC_Java/data/day10/input.txt");
        Scanner scanner = new Scanner(input);
        ArrayList<String> incompleteLines = new ArrayList<>();

        while(scanner.hasNextLine()) {
            boolean corruptLine = false;
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
                    String opening = stack.pop();
                    if(!bracketPairs.get(opening).equals(character)) {
                        int score = points.get(character);
                        totalScore += score;
                        //line is corrupt
                        corruptLine = true;
                    };
                }
            }
            if (!corruptLine) {
                incompleteLines.add(line);
            }
        }
        System.out.println("Part 1 is "+totalScore);
        scanner.close();

        //part 2
       //loop through incomplete lines
        for (String line: incompleteLines ) {
            Stack<String> stack2 = new Stack<>();
            for(char d: line.toCharArray()) {
                String currentCharacter = String.valueOf(d);
                if(bracketPairs.containsKey(currentCharacter)) {
                    //if unit is opening, add it to stack
                    stack2.push(currentCharacter);
                }
                if(bracketPairs.get(stack2.peek()).equals(currentCharacter)) {
                    stack2.pop();
                }
            }
            //anything in stack2 at this point needs to be fixed
            while(!stack2.empty()) {
                String open = stack2.pop();
                String close = bracketPairs.get(open);
                totalScorePart2 *= 5;
                int p = points2.get(close);
                totalScorePart2 += p;
            }
            scoreBoard.add(totalScorePart2);
            totalScorePart2 = 0;

        }
        Collections.sort(scoreBoard);
        long win = scoreBoard.get(scoreBoard.size()/2);
        System.out.println("Part 2 is " + win);

    }

}
