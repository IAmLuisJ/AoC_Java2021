package com.iAmLuisJ.utilityClasses;

public class SearchNode {
    private int x;
    private int y;
    private int riskSum;

    public SearchNode(int x, int y, int riskSum) {
        this.x = x;
        this.y = y;
        this.riskSum = riskSum;
    }

    public SearchNode(int x, int y) {
        this.x = x;
        this.y = y;
        this.riskSum = Integer.MAX_VALUE;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRiskSum() {
        return riskSum;
    }
}
