package com.iAmLuisJ;

import java.util.*;

public class junk {

    public static void main(String[] args) {
        // primitive data types
        int myNumber = 5; // creating an initailizing an int

        System.out.println("Shortcut for println is sout" + myNumber); // shortcut

        // basic data types
        // int - 32 bits
        // byte - 8 bits
        // short - 16 bits
        // long - 64 bits 2^63 - max value 9223372036854775807
        long myLongVal = Long.MAX_VALUE;
        System.out.println(myLongVal);

        // casting - to caste a data type, put the type in parenthesis before the
        // variable
        int myValue = 1;
        byte newByte = (byte) (myValue);
        System.out.println(newByte);
        // string
        String myName = "Luis"; // creating an init a String
        System.out.println("Hello " + myName); // concatenating a string

        // boolean
        boolean myBool = false;
        System.out.println(myBool);
        // eight prmitive data types in Java
        // byte
        // short
        // long
        // float
        // double
        // char
        // boolean
        // int

        // arrays are built in, but are immutable
        String[] plainArray = { "first item", "second" };
        System.out.println(plainArray);

        // arraylists are mutable, imported from java.util
        // specify the data type in <> brackets
        // its a class so you have to call its constructor
        ArrayList<String> names = new ArrayList<String>();
        names.add("FirstName");
        names.add("zName");
        // Collections util class can sort arrays or arraylists
        Collections.sort(names);
        System.out.println(names);
        // LinkedLists are the same as arrays but in some situations are more performant

        // HashMap
        // basically a dictionary or key value pair array
        // Hashmap<data type, data type> = new Hashmap<data type, data type>
        HashMap<String, String> myHashMap = new HashMap<String, String>();
        myHashMap.put("Firstname", "Luis");
        myHashMap.get("Firstname");
        myHashMap.put("badAttribute", "null");
        myHashMap.remove("badAttribute");
        // myHasMap.clear(); removes all items
        System.out.println(myHashMap);

        // HashSet - an array where each element has to be unique, so you can add
        // something twice and it wil only show up once
        HashSet<String> myHashSet = new HashSet<String>();
        myHashSet.add("Low");
        myHashSet.add("Medium");
        myHashSet.add("Low");
        myHashSet.add("High");
        System.out.println(myHashSet);

        myHashSet.contains("Low"); // check if element exists in hashmap

        // Iterator, creates a way to track loops and allows you to modify specific
        // index of the array while looping
        Iterator<String> myIterator = myHashSet.iterator();
        // this passes the indexes of the array to the iterator, which has a next() and
        // hasNext() method
        while (myIterator.hasNext()) {
            // loops through collection, with myIterator being replaced by each index
            myIterator.remove();
        }

        // exceptions, use a try/catch or throw an exception type
        // throw new Exception("Error");

        // Brute force / manual way
        ArrayList<String> row = new ArrayList<>();
        row.add("1");
        row.add("2");
        row.add("3");
        row.add("4");
        row.add("5");
        ArrayList<String> row2 = new ArrayList<>();
        row2.add("6");
        row2.add("7");
        row2.add("8");
        row2.add("9");
        row2.add("1");
        ArrayList<ArrayList<String>> board = new ArrayList<>();
        board.add(row);
        board.add(row2);

        // looks like this

        // 1 2 3 4 5
        // 6 7 8 9 1

        // programatic way
        ArrayList<ArrayList<String>> board2 = new ArrayList<>();
        for (int y = 0; y < 2; y++) {
            ArrayList<String> xRow = new ArrayList<>();
            for (int x = 0; x < 5; x++) {
                xRow.add("[" + x + "," + +y + "]");
            }
            board2.add(xRow);
        }

        // board2
        // [0,0] [1,0] [2,0] [3, 0] [4,0]
        // [0,1] [1,1] [2,1] [3,1] [4,1]
    }
}
