package com.example.bai15;

public class KhachHang {
    private String ten;
    private int soLuongSach;
    private boolean laKhachVIP;

    public KhachHang(String ten, int soLuongSach, boolean laKhachVIP) {
        this.ten = ten;
        this.soLuongSach = soLuongSach;
        this.laKhachVIP = laKhachVIP;
    }

    public String getTen() {
        return ten;
    }

    public int getSoLuongSach() {
        return soLuongSach;
    }

    public boolean isLaKhachVIP() {
        return laKhachVIP;
    }
}

