// package com.iAmLuisJ;

// import java.io.File;
// import java.io.FileNotFoundException;
// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.PriorityQueue;
// import java.util.Scanner;

// public class Day23v2 {

// public static final int[] MOVE_COSTS = { 1, 10, 100, 1000 };
// public static int NUM_EACH_TYPE = 0;

// public static void main(String[] args) throws FileNotFoundException {
// solvePosition("C:\\Users\\a1016060\\GitHub\\AoC_Java\\data\\Day23\\input.txt");
// }

// private static void solvePosition(String filename) throws
// FileNotFoundException {
// File myFile = new File(filename);
// Scanner myScanner = new Scanner(myFile);
// List<String> input = new ArrayList<>();
// while (myScanner.hasNext()) {
// input.add(myScanner.nextLine());
// }
// myScanner.close();
// NUM_EACH_TYPE = input.size() - 3;
// int[] startingPositions = new int[totalUnits()];
// for (int i = 0; i < NUM_EACH_TYPE; i++) {
// String line = input.get(i + 2);
// for (int j = 0; j < 4; j++) {
// char c = line.charAt(2 * j + 3);
// int unit = (c - 'A') * NUM_EACH_TYPE;
// while (startingPositions[unit] != 0) {
// unit++;
// }
// startingPositions[unit] = 4 * i + j + 7;
// }
// }

// PriorityQueue<GameState> Q = new
// PriorityQueue<>(Comparable.Long(GameState::cost));
// var newGame = new GameState(startingPositions, 0);
// Q.add(newGame);

// long best = Long.MAX_VALUE;
// Map<String, Long> alreadyProcessed = new HashMap<>();
// while (!Q.isEmpty()) {
// GameState toProcess = Q.poll();
// if (toProcess.cost >= best) {
// break;
// }

// for (int unit = 0; unit < totalUnits(); unit++) {
// boolean[] validPos = findValidPositions(toProcess.positions, unit);
// for (int i = 0; i < validPos.length; i++) {
// if (!validPos[i]) {
// continue;
// }

// int price = calcPrice(unit, toProcess.positions[unit], i);
// GameState next = toProcess.moveUnit(unit, i, price);
// if (next.isComplete()) {
// best = Math.min(best, next.cost);
// } else {
// String repr = next.getRepr();
// if (next.cost < alreadyProcessed.getOrDefault(repr, Long.MAX_VALUE)) {
// alreadyProcessed.put(repr, next.cost);
// Q.add(next);
// }
// }
// }
// }
// }
// System.out.println(best);
// }

// private static int getType(int unit) {
// if (unit == -1) {
// return -1;
// }

// return unit / NUM_EACH_TYPE;
// }

// private static int totalUnits() {
// return 4 * NUM_EACH_TYPE;
// }

// private static boolean[] findValidPositions(int[] positions, int unit) {
// if (positions[unit] < 7) {
// return findValidRoomPositions(positions, unit);
// } else {
// return findValidHallPositions(positions, unit);
// }
// }

// private static boolean[] findValidHallPositions(int[] positions, int unit) {
// int[] occupied = new int[totalUnits() + 7];
// for (int i = 0; i < totalUnits() + 7; i++) {
// occupied[i] = -1;
// }
// for (int i = 0; i < totalUnits(); i++) {
// occupied[positions[i]] = i;
// }

// boolean[] rv = new boolean[7];

// int cPos = positions[unit];
// int type = getType(unit);
// for (int i = cPos - 4; i > 6; i -= 4) {
// if (occupied[i] > -1) {
// return rv;
// }
// }

// if ((cPos + 1) % 4 == type) {
// boolean gottaMove = false;
// for (int i = cPos + 4; i < totalUnits() + 7; i += 4) {
// if (getType(occupied[i]) != type) {
// gottaMove = true;
// break;
// }
// }
// if (!gottaMove) {
// return rv;
// }
// }

// int effPos = cPos;
// while (effPos > 10) {
// effPos -= 4;
// }
// for (int i = 0; i < 7; i++) {
// if (occupied[i] == -1 && checkHallwayClear(i, effPos, occupied)) {
// rv[i] = true;
// }
// }
// return rv;
// }

// private static boolean[] findValidRoomPositions(int[] positions, int unit) {
// int[] occupied = new int[totalUnits() + 7];
// for (int i = 0; i < totalUnits() + 7; i++) {
// occupied[i] = -1;
// }
// for (int i = 0; i < totalUnits(); i++) {
// occupied[positions[i]] = i;
// }
// boolean[] rv = new boolean[totalUnits() + 7];

// int cPos = positions[unit];
// int type = getType(unit);
// int room1 = type + 7;

// if (!checkHallwayClear(cPos, room1, occupied)) {
// return rv;
// }
// int tgt = room1;
// for (int i = 0; i < NUM_EACH_TYPE; i++) {
// if (occupied[room1 + 4 * i] == -1) {
// tgt = room1 + 4 * i;
// } else if (getType(occupied[room1 + 4 * i]) != type) {
// return rv;
// }
// }
// rv[tgt] = true;
// return rv;
// }

// private static boolean checkHallwayClear(int hallPos, int roomPos, int[]
// occupied) {
// int min = Math.min(hallPos + 1, roomPos - 5);
// int max = Math.max(hallPos - 1, roomPos - 6);

// for (int i = min; i <= max; i++) {
// if (occupied[i] != -1) {
// return false;
// }
// }
// return true;
// }

// private static int calcPrice(int unit, int from, int to) {
// if (from > to) {
// int tmp = from;
// from = to;
// to = tmp;
// }

// int depth = (to - 3) / 4;
// int tgtHall = ((to + 1) % 4) * 2 + 3;
// int discount = (from == 0 || from == 6) ? 1 : 0;
// int dist = Math.abs(2 * from - tgtHall) + depth - discount;
// int type = getType(unit);
// return MOVE_COSTS[type] * dist;
// }

// public static class GameState {
// int[] positions;
// long cost;

// public GameState(int[] newPositions, long l) {
// positions = newPositions;
// cost = l;
// }

// public GameState moveUnit(int unit, int position, int price) {
// int[] newPositions = Arrays.copyOf(positions, positions.length);
// newPositions[unit] = position;
// GameState rv = new GameState(newPositions, cost + price);
// return rv;
// }

// public boolean isComplete() {
// for (int i = 0; i < positions.length; i++) {
// int type = getType(i);
// if (positions[i] < 7 || (positions[i] + 1) % 4 != type) {
// return false;
// }
// }

// return true;
// }

// public String getRepr() {
// int[] occupied = new int[totalUnits() + 7];
// for (int i = 0; i < totalUnits() + 7; i++) {
// occupied[i] = -1;
// }
// for (int i = 0; i < totalUnits(); i++) {
// occupied[positions[i]] = i;
// }

// String rv = "";
// for (int i = 0; i < totalUnits() + 7; i++) {
// int type = getType(occupied[i]);
// if (type == -1) {
// rv += "x";
// } else {
// rv += type;
// }
// }
// return rv;
// }
// }
// }
