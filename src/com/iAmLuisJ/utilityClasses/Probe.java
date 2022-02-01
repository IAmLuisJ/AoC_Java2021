package com.iAmLuisJ.utilityClasses;

public class Probe {
    private int x;
    private int y;
    private int xVelocity;
    private int yVelocity;

    public Probe(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Probe(String x, String y) {
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

    public void setXVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public void setYVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    public int getXVelocity() {
        return this.xVelocity;
    }

    public int getYVelocity() {
        return this.yVelocity;
    }

    public boolean equals(Probe compareCord) {
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
        Probe thisCord = (Probe) compareCord;
        if (thisCord.getX() == this.x && thisCord.getY() == this.y) {
            return true;
        }
        return false;
    }
}
