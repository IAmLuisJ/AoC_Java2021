package com.iAmLuisJ;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class day14v2 {
    private static Map<String, String[]> insertions = new HashMap<>();
    private static Character first;

    public static void main(String[] args) {
        String path = "/Users/luisjuarez/GitHub/AOC_Java/data/day14/testdata.txt";
        Map<String, Long> templateMap = loadInput(path);
        for (int i = 0; i < 40; i++) {
            templateMap = transform(templateMap);
        }

        print(countChars(templateMap));
    }

    private static void print(Map<Character, Long> countChars) {
        char maxChar = 0;
        char minChar = 0;
        Long max = Long.MIN_VALUE;
        Long min = Long.MAX_VALUE;

        for (Character c : countChars.keySet()) {
            if (countChars.get(c) > max) {
                max = countChars.get(c);
                maxChar = c;
            } else if (countChars.get(c) < min) {
                min = countChars.get(c);
                minChar = c;
            }
        }

        System.out.printf("The most common element is %c with %d occurance\n " +
                "and the leat common element is %c with %d occurance\n " +
                "the subtraction is %d", maxChar, max, minChar, min, max - min);
    }

    private static Map<String, Long> loadInput(String path) {
        String startTemplate = null;
        try (Scanner scanner = new Scanner(new File(path))) {
            startTemplate = scanner.nextLine();
            first = startTemplate.charAt(0);
            scanner.nextLine();
            while (scanner.hasNext()) {
                createInsertions(scanner.nextLine().split(" -> "));
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + path);
        }
        return createStartingMap(startTemplate);
    }

    private static void createInsertions(String[] line) {
        String pattern = line[0];
        String plus = line[1];
        String[] replace = {pattern.charAt(0) + plus, plus  + pattern.charAt(1)};
        insertions.put(pattern, replace);
    }

    private static Map<String, Long> createStartingMap(String line) {
        Map<String, Long> map = new HashMap<>();
        for (int i = 0; i < line.length() - 1; i++) {
            String s = line.substring(i, i + 2);
            Long n = map.getOrDefault(s, 0L);
            map.put(s, n + 1);
        }
        return map;
    }

    private static Map<String, Long> transform(Map<String, Long> record) {
        Map<String, Long> newRecord = new HashMap<>();

        for (String key : record.keySet()) {
            Long countKey = record.get(key);

            if (insertions.containsKey(key)) {
                String insert1 = insertions.get(key)[0];
                String insert2 = insertions.get(key)[1];
                Long count1 = newRecord.getOrDefault(insert1, 0L);
                Long count2 = newRecord.getOrDefault(insert2, 0L);
                newRecord.put(insert1, count1 + countKey);
                newRecord.put(insert2, count2 + countKey);
            } else {
                newRecord.put(key, countKey);
            }
        }
        return newRecord;
    }

    private static Map<Character, Long> countChars(Map<String, Long> templateMap) {
        Map<Character, Long> charMap = new HashMap<>();
        Long firstN = charMap.getOrDefault(first, 0L);
        charMap.put(first, firstN + 1);
        for (String key : templateMap.keySet()) {
            char c = key.charAt(1);
            Long n = templateMap.get(key);

            Long charN = charMap.getOrDefault(c, 0L);
            charMap.put(c, charN + n);
        }
        return charMap;
    }
}
