package com.iAmLuisJ.utilityClasses;

public class SnailFish {
    int x;
    int y;
    SnailFish parent;
    SnailFish left;
    SnailFish right;

    public SnailFish(String snail) {

        parent = null;
        left = null;
        right = null;
    }

    public void setX(int left) {
        this.x = left;
    }

    public void setY(int right) {
        this.y = right;
    }

    public void setParent(SnailFish parent) {
        this.parent = parent;
    }

    public void setLeft(SnailFish lnode) {
        left = lnode;
    }

    public void setRight(SnailFish rnode) {
        right = rnode;
    }

    public int getX(int left) {
        return x;
    }

    public int getY(int right) {
        return y;
    }
}
