package com.example.recyclingapp;

import java.util.UUID;

public class User {
    private String name;
    private String id;
    private String username;
    private String password;
    private String email;
    private Integer ID;
    private float materialKgs;
    private float glassKg;
    private float plasticKg;
    private float aluminiumKg;
    private float paperKg;
    private int points;

    public User(Integer ID,String name,String email, String username, String password,int points,float aluminiumKg,float glassKg,float paperKg,float plasticKg) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.points = points;
        this.ID = ID;
        this.aluminiumKg= aluminiumKg;
        this.glassKg= glassKg;
        this.paperKg= paperKg;
        this.plasticKg= plasticKg;
    }

    public String getName() {
        return name;
    }
    public String getUsername() {
        return username;
    }
    public double getMaterialKgs() {
        return materialKgs;
    }
    public float getGlassKg() {
        return glassKg;
    }
    public float getPlasticKg() {
        return plasticKg;
    }

    public float getAluminiumKg() {
        return aluminiumKg;
    }
    public float getPaperKg() {
        return paperKg;
    }

    public int getPoints() {
        return points;
    }
    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public boolean isUsername(String username){
        return this.username.equals(username);
    }

    public boolean isPassword(String password){
        return this.password.equals(password);
    }

}
