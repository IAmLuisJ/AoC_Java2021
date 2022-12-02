import java.io.File;

import java.util.Scanner;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        File input = new File("C:\\Users\\luisi\\OneDrive\\Desktop\\Day2\\src\\input.txt");
        Scanner scanner = new Scanner(input);
        ArrayList<String> move = new ArrayList<String>();

        final int win = 6;
        final int tie = 3;
        final int loss = 0;

        int rock = 1;
        int paper = 2;
        int scissors = 3;

        // ArrayList<Integer> tournament = new ArrayList<>();
        int totalScore = 0;
        // A for Rock, B for Paper, and C for Scissors.
        // X for Rock, Y for Paper, and Z for Scissors.
        // 1 for Rock, 2 for Paper, and 3 for Scissors) plus the score for the outcome
        // of the round (0 if you lost, 3 if the round was a draw, and 6 if you won).

        // X means you need to lose, Y means you need to end the round in a draw, and Z
        // means you need to win.
        while (scanner.hasNext()) {
            int round = 0;
            String thisMove = scanner.nextLine();
            String[] step = thisMove.split(" ");
            String opponentMove = step[0];
            String myMove = step[1];

            if (opponentMove.equals("A")) {
                if (myMove.equals("X")) {
                    round = loss + scissors;
                }
                if (myMove.equals("Y")) {
                    round = tie + rock;
                }
                if (myMove.equals("Z")) {
                    round = win + paper;
                }
            }
            if (opponentMove.equals("B")) {
                if (myMove.equals("X")) {
                    round = loss + rock;
                }
                if (myMove.equals("Y")) {
                    round = tie + paper;
                }
                if (myMove.equals("Z")) {
                    round = win + scissors;
                }
            }
            if (opponentMove.equals("C")) {
                if (myMove.equals("X")) {
                    round = loss + paper;
                }
                if (myMove.equals("Y")) {
                    round = tie + scissors;
                }
                if (myMove.equals("Z")) {

                    round = win + rock;
                }
            }
            totalScore = totalScore + round;
        }
        System.out.println(totalScore);
    }
}
