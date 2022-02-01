package com.iAmLuisJ.utilityClasses;

public class ALU {
    private int w;
    private int x;
    private int y;
    private int z;

    public ALU() {
        this.w = 0;
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public int getVal(String invar) {
        if (invar.equals("w")) {
            return this.w;
        }
        if (invar.equals("x")) {
            return this.x;
        }
        if (invar.equals("y")) {
            return this.y;
        }
        if (invar.equals("z")) {
            return this.z;
        }
        return 0;
    }

    public void setVal(String inVar, int newVal) {
        if (inVar.equals("w")) {
            this.w = newVal;
        }
        if (inVar.equals("x")) {
            this.x = newVal;
        }
        if (inVar.equals("y")) {
            this.y = newVal;
        }
        if (inVar.equals("z")) {
            this.z = newVal;
        }

    }

    // instruction methods
    public int inp(int a) {
        return a;
    }

    public int add(int a, int b) {
        a += b;
        return a;
    }

    public int mul(int a, int b) {
        a *= b;
        return a;
    }

    public int div(int a, int b) {
        if (b == 0) {
            return a;
        }
        a /= b;
        return a;
    }

    public int mod(int a, int b) {
        if (a <= 0 || b <= 0) {
            return a;
        }
        a %= b;
        return a;
    }

    public int eql(int a, int b) {
        if (a == b) {
            return 1;
        }
        return 0;
    }
}
