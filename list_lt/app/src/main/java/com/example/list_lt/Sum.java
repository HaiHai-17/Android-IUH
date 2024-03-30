package com.example.list_lt;

public class Sum {
    private int a;
    private int b;
    private int kq;

    public Sum(int a, int b, int kq) {
        this.a = a;
        this.b = b;
        this.kq = kq;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getKq() {
        return kq;
    }

    public void setKq(int kq) {
        this.kq = kq;
    }

    @Override
    public String toString() {
        return a + " + " + b + " = " + kq;
    }
}
