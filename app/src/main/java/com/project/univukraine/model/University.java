package com.project.univukraine.model;

import java.io.Serializable;

public class University implements Serializable {
    private int id;
    private String name;
    private String address;
    private int studentAmount;
    private int webometrix;
    private int excellence;
    private String latitude;
    private String longitude;

    public University() {
    }

    public University(int id, String name, String address, int studentAmount, int webometrix, int excellence, String latitude, String longitude) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.studentAmount = studentAmount;
        this.webometrix = webometrix;
        this.excellence = excellence;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStudentAmount() {
        return studentAmount;
    }

    public void setStudentAmount(int studentAmount) {
        this.studentAmount = studentAmount;
    }

    public int getWebometrix() {
        return webometrix;
    }

    public void setWebometrix(int webometrix) {
        this.webometrix = webometrix;
    }

    public int getExcellence() {
        return excellence;
    }

    public void setExcellence(int excellence) {
        this.excellence = excellence;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "University{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", studentAmount=" + studentAmount +
                ", webometrix=" + webometrix +
                ", excellence=" + excellence +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
