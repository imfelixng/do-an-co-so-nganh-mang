package com.ngquangan.bean;

import java.io.Serializable;
import java.util.Date;

public class CanBo implements Serializable {

    private String maNV;
    private String teNV;
    private Date ngaySinh;
    private boolean gioiTinh;
    private String soDT;
    private String email;
    private String phongBan;
    private String chucVu;
    private String username;
    private boolean online;

    public CanBo() {
    }

    public CanBo(String maNV, String teNV, Date ngaySinh, boolean gioiTinh, String soDT, String email, String phongBan, String chucVu, String username, boolean online) {
        this.maNV = maNV;
        this.teNV = teNV;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.soDT = soDT;
        this.email = email;
        this.phongBan = phongBan;
        this.chucVu = chucVu;
        this.username = username;
        this.online = online;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTeNV() {
        return teNV;
    }

    public void setTeNV(String teNV) {
        this.teNV = teNV;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhongBan() {
        return phongBan;
    }

    public void setPhongBan(String phongBan) {
        this.phongBan = phongBan;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
}
