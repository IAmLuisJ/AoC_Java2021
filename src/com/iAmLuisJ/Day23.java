package com.iAmLuisJ;

public class Day23 {
    public static void main(String[] args) {

        // #############
        // #...........#
        // ###A#C#B#A###
        // ###D#D#B#C###
        // #############
        // podType * (movementToHall + movementToBurrow)
        int A = 1;
        int B = 10;
        int C = 100;
        int D = 1000;

        int SumOfMoves = 0;
        SumOfMoves += A * 3;
        SumOfMoves += A * 8;
        SumOfMoves += C * 3;
        SumOfMoves += D * 10;
        SumOfMoves += A * 3;
        SumOfMoves += A * 3;
        SumOfMoves += B * 6;
        SumOfMoves += B * 5;
        SumOfMoves += C * 5;
        SumOfMoves += C * 4;
        SumOfMoves += D * 7;
        SumOfMoves += B * 3;
        SumOfMoves += B * 4;

        System.out.println(SumOfMoves);
        // HashMap<String, String> burrows = new HashMap<>();
        // burrows.put("H0", "");
        // burrows.put("H1", "");
        // burrows.put("H2", "");
        // burrows.put("H3", "");
        // burrows.put("H4", "");
        // burrows.put("H5", "");
        // burrows.put("H6", "");
        // burrows.put("H7", "");
        // burrows.put("A0", "");
        // burrows.put("A1", "");
        // burrows.put("B0", "");
        // burrows.put("B1", "");
        // burrows.put("C0", "");
        // burrows.put("C1", "");
        // burrows.put("D0", "");
        // burrows.put("D1", "");

        // Start
        // #############
        // #...........#
        // ###A#C#B#A###
        // ###D#D#B#C###
        // #############

        // A3
        // #############
        // #A..........#
        // ###.#C#B#A###
        // ###D#D#B#C###
        // #############

        // A8
        // #############
        // #AA.........#
        // ###.#C#B#.###
        // ###D#D#B#C###
        // #############

        // C3
        // #############
        // #AA.......C.#
        // ###.#C#B#.###
        // ###D#D#B#.###
        // #############

        // D10
        // #############
        // #AA.......C.#
        // ###.#C#B#.###
        // ###.#D#B#D###
        // #############

        // A3
        // #############
        // #A........C.#
        // ###.#C#B#.###
        // ###A#D#B#D###
        // #############

        // A3
        // #############
        // #.........C.#
        // ###A#C#B#.###
        // ###A#D#B#D###
        // #############

        // C2
        // #############
        // #...C.....C.#
        // ###A#.#B#.###
        // ###A#D#B#D###
        // #############

        // D7
        // #############
        // #...C.....C.#
        // ###A#.#B#D###
        // ###A#.#B#D###
        // #############

        // B5
        // #############
        // #...C.....C.#
        // ###A#.#.#D###
        // ###A#B#B#D###
        // #############

        // 5B
        // #############
        // #...C.....C.#
        // ###A#B#.#D###
        // ###A#B#.#D###
        // #############

        // C5
        // #############
        // #.........C.#
        // ###A#B#.#D###
        // ###A#B#C#D###
        // #############

        // C4
        // #############
        // #...........#
        // ###A#B#C#D###
        // ###A#B#C#D###
        // #############

    }
}
