package com.example.recyclingapp;

import java.util.UUID;

public class User {
    private String name;
    private String id;
    private String username;
    private String password;
    private String email;
    private Integer ID;
    private double materialKgs=0;
    private double glassKg=0;
    private double plasticKg=0;
    private double aluminiumKg=0;
    private double paperKg=0;
    private int points=0;

    public User(Integer ID,String name,String email, String username, String password,int points,double aluminiumKg,double glassKg,double paperKg,double plasticKg) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.points = 0;
        this.ID = ID;
        this.aluminiumKg= aluminiumKg;
        this.glassKg= aluminiumKg;
        this.paperKg= aluminiumKg;
        this.plasticKg= aluminiumKg;
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
    public double getGlassKg() {
        return glassKg;
    }
    public double getPlasticKg() {
        return plasticKg;
    }

    public double getAluminiumKg() {
        return aluminiumKg;
    }
    public double getPapperKg() {
        return paperKg;
    }

    public int getPoints() {
        return points;
    }
    public String getId() {
        return id;
    }

    public void createForm(){

    }

    public boolean isUsername(String username){
        return this.username.equals(username);
    }

    public boolean isPassword(String password){
        return this.password.equals(password);
    }

    public void reward(String material, double kg, int rewardPoints){
        points+=rewardPoints;
        switch (material){
            case "Glass" : glassKg+=kg;
            case "Plastic" : plasticKg+=kg;
            case "Paper" : paperKg+=kg;
            case "Aluminium": aluminiumKg+=kg;
        }
    }

}
