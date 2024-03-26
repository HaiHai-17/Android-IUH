package com.example.bai23;
public class Congviec {
    private String tenCongViec;
    private String noiDung;
    private String ngay;
    private String gio;

    public String getTenCongViec() {
        return tenCongViec;
    }

    public void setTenCongViec(String tenCongViec) {
        this.tenCongViec = tenCongViec;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getGio() {
        return gio;
    }

    public void setGio(String gio) {
        this.gio = gio;
    }

    public Congviec(String tenCongViec, String noiDung, String ngay, String gio) {
        this.tenCongViec = tenCongViec;
        this.noiDung = noiDung;
        this.ngay = ngay;
        this.gio = gio;
    }
}
