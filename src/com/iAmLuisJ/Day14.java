package com.iAmLuisJ;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day14 {
    public static void main(String[] args) throws FileNotFoundException {
       // File input = new File("C:\\Users\\a1016060\\GitHub\\AoC_Java\\data\\day14\\input.txt");
        File input = new File("/Users/luisjuarez/GitHub/AOC_Java/data/day14/input.txt");
        Scanner scanner = new Scanner(input);
        Map<String, String[]> instructions = new HashMap<>();
        HashMap<String, Long> polymer = new HashMap<>();

        // pass the first line to a string
        String startTemplate = scanner.nextLine();
        char firstLetter = startTemplate.charAt(0);
        // skip the blank space before instructions
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            // parse data
            String line = scanner.nextLine();
            var part = line.split(" -> ");
            var pattern = part[0];
            var letterToAdd = part[1];
            String[] newPairs = { pattern.charAt(0) + letterToAdd, letterToAdd + pattern.charAt(1) };
            instructions.put(pattern, newPairs);
        }
        scanner.close();

        // parse starting string to hashmap
        for (int x = 0; x < startTemplate.length() - 1; x++) {
            var firstPair = startTemplate.substring(x, x + 2);
            var count = polymer.getOrDefault(firstPair, 0L);
            polymer.put(firstPair, count + 1);
        }

        // loop through instructions
        for (int i = 0; i < 40; i++) {
            polymer = doInsert(polymer, instructions);
        }
        // count frequency of characters
        HashMap<Character, Long> charMap = new HashMap<>();
        long firstCharCount = charMap.getOrDefault(firstLetter, 0L);
        charMap.put(firstLetter, firstCharCount + 1L);
        for (String key : polymer.keySet()) {
            char c = key.charAt(1);
            long n = polymer.get(key);

            long charCount = charMap.getOrDefault(c, 0L);
            charMap.put(c, charCount + n);
        }
        // most common element - least common element
        char mostChar = 0;
        char leastChar = 0;
        long mostCharCount = 0;
        long leastCharCount = Long.MAX_VALUE;
        for (char currentCharacter : charMap.keySet()) {
            if (charMap.get(currentCharacter) > mostCharCount) {
                mostCharCount = charMap.get(currentCharacter);
                mostChar = currentCharacter;
            }
            if (charMap.get(currentCharacter) < leastCharCount) {
                leastCharCount = charMap.get(currentCharacter);
                leastChar = currentCharacter;
            }
        }
        var answer = mostCharCount - leastCharCount;
        System.out.println("Most common char is " + mostChar + " which shows up " + mostCharCount + " times");
        System.out.println("Lead common char is " + leastChar + " which shows up " + leastCharCount + " times");
        System.out.println("The answer is " + answer);
    }

    static HashMap<String, Long> doInsert(Map<String, Long> polymer, Map<String, String[]> instructions) {
        HashMap<String, Long> updatedPolymer = new HashMap<>();

        for (String key : polymer.keySet()) {
            Long countKey = polymer.get(key);

            if (instructions.containsKey(key)) {
                var insertions = instructions.get(key);
                long firstPairFrequency = updatedPolymer.getOrDefault(insertions[0], 0L);
                long secondPairFrequency = updatedPolymer.getOrDefault(insertions[1], 0L);
                updatedPolymer.put(insertions[0], firstPairFrequency + countKey);
                updatedPolymer.put(insertions[1], secondPairFrequency + countKey);
            } else {
                updatedPolymer.put(key, countKey);
            }
        }
        return updatedPolymer;
    }
}
