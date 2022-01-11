package com.iAmLuisJ;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day12 {
    public static boolean isSmallCave(String name) {
        char[] chars = name.toCharArray();
        for(int i = 0; i < chars.length; i++) {
            if(!Character.isLowerCase(chars[i])) {
                return false;
            }
        }
        return true;
    }

    static public void main(String[] args) throws FileNotFoundException {
        File input = new File("/Users/luisjuarez/GitHub/AOC_Java/data/Day12/input.txt");
        Scanner scanner = new Scanner(input);
        HashMap<String, String> caves = new HashMap();

        //parse list into caves
        while(scanner.hasNextLine()) {
            String[] line  = (scanner.nextLine().split("-"));
            //split list into nodes
            caves.put(line[0], line[1]);
        }

        //loop through list
       //maybe we don't want a hashmap of the caves
        //maybe we want a List<String[]> where start is cave[0] and end is cave[1]
        //then we loop through and say

    }
}
