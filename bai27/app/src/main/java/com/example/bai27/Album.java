package com.example.bai27;

public class Album {
    int stt;
    String ma, ten;

    public Album(int stt, String ma, String ten) {
        this.stt = stt;
        this.ma = ma;
        this.ten = ten;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
}
