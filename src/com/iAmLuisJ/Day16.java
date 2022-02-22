package com.iAmLuisJ;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Day16 {
    public static void main(String[] args) throws FileNotFoundException {
        // Day 16
        // parse input
        File myFile = new File("C:\\Users\\a1016060\\GitHub\\AoC_Java\\data\\Day16\\input.txt");
        Scanner myScan = new Scanner(myFile);
        String input = myScan.nextLine();
        myScan.close();
        // test data
        input = "8A004A801A8002F478";
        // input = "620080001611562C8802118E34";
        // input = "C0015000016115A2E0802F182340";
        // input = "A0016C880162017C3686B18A3D4780";
        // convert hexadecimal representation to binary
        // each character of hexadecimal corresponds to four bits of binary
        HashMap<String, String> binMap = new HashMap<>();
        binMap.put("0", "0000");
        binMap.put("1", "0001");
        binMap.put("2", "0010");
        binMap.put("3", "0011");
        binMap.put("4", "0100");
        binMap.put("5", "0101");
        binMap.put("6", "0110");
        binMap.put("7", "0111");
        binMap.put("8", "1000");
        binMap.put("9", "1001");
        binMap.put("A", "1010");
        binMap.put("B", "1011");
        binMap.put("C", "1100");
        binMap.put("D", "1101");
        binMap.put("E", "1110");
        binMap.put("F", "1111");

        String binaryString = "";
        for (String s : input.split("")) {
            binaryString += binMap.get(s);
        }

        System.out.println(input);
    }
}
