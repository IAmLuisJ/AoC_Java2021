package com.iAmLuisJ;

import java.util.HashMap;
import java.util.Map;

public class Junk2 {
    public static void main(String[] args) {
        // Java.Util.Map
        Map<String, Integer> myFrequency = new HashMap<>();
        myFrequency.put("firstKey", 4);
        myFrequency.put("secondKey", 3);
        myFrequency.put("third", 2);
        myFrequency.put("four", 1);

        // loop through with compute

        // for (String thisString : myFrequency.keySet()) {
        // Integer currentInt = myFrequency.get(thisString);
        // System.out.println(currentInt);
        // }

        myFrequency.forEach((key1, val1) -> System.out.println(val1));

        myFrequency.forEach((mykey, myval) -> myFrequency.compute(mykey, ((key, val) -> val + 1)));
        System.out.println(myFrequency);

    }
}
