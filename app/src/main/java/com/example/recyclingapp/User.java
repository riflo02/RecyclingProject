package com.example.recyclingapp;

public class User {
    private String id;
    private String name;
    private String code;
    private String email;

    private int points;


    public User(String id, String name, String code, String email) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.email = email;
    }

    public String getUserId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void getName() {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setPassword(String code) {
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
