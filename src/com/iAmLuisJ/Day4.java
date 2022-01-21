package com.iAmLuisJ;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day4 {
    public static void main(String[] args) throws FileNotFoundException {
        // PROGRAM START
        int firstWin = 0;
        int secondWin = 0;
        int lineCount = 1;
        int answer = 0;

        File input2 = new File("C:\\Users\\a1016060\\GitHub\\AoC 2021\\aoc\\src\\Day4input.txt");
        Scanner myScan = new Scanner(input2);
        ArrayList<ArrayList<List<String>>> gameBoards = new ArrayList<>();
        List<String> deck = new ArrayList<>();

        while (myScan.hasNextLine()) {
            // if line count is 0, its the first line, scan string into numbers array
            if (lineCount == 1) {
                String firstLine = myScan.nextLine();
                deck = Arrays.asList(firstLine.split(","));
                System.out.println(deck.size());
            }
            // if line count is not 1
            // parse game boards
            ArrayList<List<String>> bingoBoard = new ArrayList<>();

            // first line will be zero so just scan the nextline
            myScan.nextLine();
            // first line of the gameboard
            for (int i = 0; i < 5; i++) {
                String currentLine = myScan.nextLine();
                List<String> bingoLine = (List<String>) Arrays.asList(currentLine.trim().split("\\s+"));
                bingoBoard.add(bingoLine);
            }
            gameBoards.add(bingoBoard);
            lineCount++;
        }
        boolean isRowWin;
        boolean isColumnWin;
        // at this point we have gameBoards which holds all the values of the numbers

        for (String called : deck) { // called is the current number in deck
            Iterator<ArrayList<List<String>>> currentBoard = gameBoards.iterator();
            while (currentBoard.hasNext()) { // current board
                ArrayList<List<String>> board = currentBoard.next();
                for (int x = 0; x < 5; x++) {
                    isRowWin = true;
                    isColumnWin = true;
                    for (int y = 0; y < 5; y++) {
                        var boardLine = board.get(x);
                        var boardSpot = boardLine.get(y);
                        // check if drawn number is equal to the current boardSpot
                        if (called.equals(boardSpot)) {
                            System.out.println(
                                    "Found a match on this board at [" + x + "] [" + y + "] for " + called);
                            boardLine.set(y, "X"); // set the value of a match to 0, maybe should be x?
                            boardSpot = boardLine.get(y);
                        }
                        if (!boardSpot.equals("X")) {
                            isRowWin = false;
                        }

                        var verticleLine = board.get(y);
                        var vSpot = verticleLine.get(x);
                        if (!vSpot.equals("X")) {
                            isColumnWin = false;
                        }
                    }
                    if (isRowWin || isColumnWin) {
                        if (isRowWin) {
                            System.out.println("row win!");
                        }
                        if (isColumnWin) {
                            System.out.println("column win!");
                        }
                        // calculate "score" of this board
                        // loop through and find the sum of the board
                        // then multiply the sum by the number that was called
                        var winningBoard = board;
                        int sum = 0;
                        for (List<String> string : winningBoard) {
                            for (String num : string) {
                                if (!num.equals("X")) {
                                    sum = sum + Integer.parseInt(num);
                                }
                            }
                        }

                        answer = sum * Integer.parseInt(called);
                        if (firstWin == 0) {
                            firstWin = answer;
                        }

                        // if this board has won, remove it from the list of boards
                        if (gameBoards.size() > 1) {
                            currentBoard.remove();
                            System.out.println("There are this many boards left " + gameBoards.size());
                            break;
                        } else {
                            if (secondWin == 0) {
                                secondWin = answer;
                            }
                        }

                    }
                }
            }
        }
        // loop through numbers, check if any boards have the number, mark as X if true
        System.out.println("Answer to Part 1 is: " + firstWin);
        System.out.println("Answer to part 2 is: " + secondWin);
        // PROGRAM END
        myScan.close();
    }
}
