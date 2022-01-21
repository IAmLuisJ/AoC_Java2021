package com.iAmLuisJ.utilityClasses;

public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate(String x, String y) {
        this.x = Integer.parseInt(x);
        this.y = Integer.parseInt(y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean equals(Coordinate compareCord) {
        if (this == compareCord)
            return true;
        if (compareCord.getX() == this.x && compareCord.getY() == this.y) {
            return true;
        }
        return false;
    }

    public boolean equals(Object compareCord) {
        if (this == compareCord)
            return true;
        Coordinate thisCord = (Coordinate) compareCord;
        if (thisCord.getX() == this.x && thisCord.getY() == this.y) {
            return true;
        }
        return false;
    }
}