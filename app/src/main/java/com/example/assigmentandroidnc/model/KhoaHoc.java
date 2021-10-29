package com.example.assigmentandroidnc.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "khoahoc")
public class KhoaHoc implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String kihoc;
    private String lichhoc;
    private String ngaythi;
    private String lopHoc;
    private String monhoc;

    public KhoaHoc() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public KhoaHoc(String kihoc, String lichhoc, String ngaythi, String lopHoc, String monhoc) {
        this.kihoc = kihoc;
        this.lichhoc = lichhoc;
        this.ngaythi = ngaythi;
        this.lopHoc = lopHoc;
        this.monhoc = monhoc;
    }

    public String getKihoc() {
        return kihoc;
    }

    public void setKihoc(String kihoc) {
        this.kihoc = kihoc;
    }

    public String getLichhoc() {
        return lichhoc;
    }

    public void setLichhoc(String lichhoc) {
        this.lichhoc = lichhoc;
    }

    public String getNgaythi() {
        return ngaythi;
    }

    public void setNgaythi(String ngaythi) {
        this.ngaythi = ngaythi;
    }

    public String getLopHoc() {
        return lopHoc;
    }

    public void setLopHoc(String lopHoc) {
        this.lopHoc = lopHoc;
    }

    public String getMonhoc() {
        return monhoc;
    }

    public void setMonhoc(String monhoc) {
        this.monhoc = monhoc;
    }
}
