package com.example.nguyenhoanghai_20115801;

public class Employee {
    String manv;
    String tennv;
    String luong;
    String chucvu;

    public Employee(String manv, String tennv, String luong, String chucvu) {
        this.manv = manv;
        this.tennv = tennv;
        this.luong = luong;
        this.chucvu = chucvu;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public String getTennv() {
        return tennv;
    }

    public void setTennv(String tennv) {
        this.tennv = tennv;
    }

    public String getLuong() {
        return luong;
    }

    public void setLuong(String luong) {
        this.luong = luong;
    }

    public String getChucvu() {
        return chucvu;
    }

    public void setChucvu(String chucvu) {
        this.chucvu = chucvu;
    }
}
