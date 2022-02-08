package com.iAmLuisJ;

import java.io.*;
import java.util.*;

import com.iAmLuisJ.utilityClasses.ALU;

public class Day24 {
    public static void main(String[] args) throws FileNotFoundException {

        long testNum = 11111111111111L;
        String testNumber = String.valueOf(testNum);
        long highestNum = 0L;

        while (testNum < 99999999999999L) {
            if (testNumber.contains("0")) {
                testNum++;
                testNumber = String.valueOf(testNum);
                continue;
            }
            var myFile = new File("C:\\Users\\a1016060\\GitHub\\AoC_Java\\data\\Day24\\input.txt");
            var myScanner = new Scanner(myFile);
            ALU myALU = new ALU();

            String[] inputNumbers = testNumber.split("");
            int numberCount = 0;

            while (myScanner.hasNext()) {
                // loop through input
                // parse the string
                String instruction = myScanner.nextLine();
                String[] pieces = instruction.split(" ");
                var method = pieces[0];
                var intVar = pieces[1];
                String numVar = "";
                if (pieces.length > 2) {
                    numVar = pieces[2];
                }
                int output = myALU.getVal(intVar);
                // execute commands
                // System.out.println("ALU values are w: " + myALU.getVal("w") + " x: " +
                // myALU.getVal("x") + " y: " + myALU.getVal("y") + " z: " + myALU.getVal("z"));
                try {
                    switch (method) {
                        case "inp":
                            myALU.setVal(intVar, Integer.parseInt(inputNumbers[numberCount]));
                            numberCount++;
                            break;
                        case "mul":
                            myALU.setVal(intVar, myALU.mul(output, Integer.parseInt(numVar)));
                            break;
                        case "add":
                            myALU.setVal(intVar, myALU.add(output, Integer.parseInt(numVar)));
                            break;
                        case "mod":
                            myALU.setVal(intVar, myALU.mod(output, Integer.parseInt(numVar)));
                            break;
                        case "div":
                            myALU.setVal(intVar, myALU.div(output, Integer.parseInt(numVar)));
                            break;
                        case "eql":
                            myALU.setVal(intVar, myALU.eql(output, Integer.parseInt(numVar)));
                            break;
                        default:
                            break;
                    }
                } catch (Exception e) {
                    switch (method) {
                        case "mul":
                            myALU.setVal(intVar, myALU.mul(output, myALU.getVal(numVar)));
                            break;
                        case "add":
                            myALU.setVal(intVar, myALU.add(output, myALU.getVal(numVar)));
                            break;
                        case "mod":
                            myALU.setVal(intVar, myALU.mod(output, myALU.getVal(numVar)));
                            break;
                        case "div":
                            myALU.setVal(intVar, myALU.div(output, myALU.getVal(numVar)));
                            break;
                        case "eql":
                            myALU.setVal(intVar, myALU.eql(output, myALU.getVal(numVar)));
                            break;
                        default:
                            break;
                    }
                }

            }
            myScanner.close();
            if (myALU.getVal("z") == 0) {
                // valid model number
                if (testNum > highestNum) {
                    highestNum = testNum;
                    System.out.println("This is a valid model number " + testNum);
                }
            }
        }

        System.out.println("end");
    }
}
