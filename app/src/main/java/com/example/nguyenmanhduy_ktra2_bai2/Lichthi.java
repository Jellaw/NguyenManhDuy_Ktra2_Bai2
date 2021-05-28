package com.example.nguyenmanhduy_ktra2_bai2;

public class Lichthi {
    int id;
    String tenmonhoc;
    String ngaythi;
    String giothi;
    String kieuthi;

    public Lichthi(int id, String tenmonhoc, String ngaythi, String giothi, String kieuthi) {
        this.id = id;
        this.tenmonhoc = tenmonhoc;
        this.ngaythi = ngaythi;
        this.giothi = giothi;
        this.kieuthi = kieuthi;
    }

    public Lichthi(String tenmonhoc, String ngaythi, String giothi, String kieuthi) {
        this.tenmonhoc = tenmonhoc;
        this.ngaythi = ngaythi;
        this.giothi = giothi;
        this.kieuthi = kieuthi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNgaythi() {
        return ngaythi;
    }

    public void setNgaythi(String ngaythi) {
        this.ngaythi = ngaythi;
    }

    public String getGiothi() {
        return giothi;
    }

    public void setGiothi(String giothi) {
        this.giothi = giothi;
    }

    public String getTenmonhoc() {
        return tenmonhoc;
    }

    public void setTenmonhoc(String tenmonhoc) {
        this.tenmonhoc = tenmonhoc;
    }

    public String getKieuthi() {
        return kieuthi;
    }

    public void setKieuthi(String kieuthi) {
        this.kieuthi = kieuthi;
    }
}
