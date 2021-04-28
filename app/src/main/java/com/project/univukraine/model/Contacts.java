package com.project.univukraine.model;

public class Contacts {
    private int id;
    private String name;
    private String surname;
    private String phoneNum;
    private String email;

    public Contacts() {
    }

    public Contacts(int id, String name, String surname, String phoneNum, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phoneNum = phoneNum;
        this.email = email;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString() {
        return "Contacts{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
