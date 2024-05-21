package com.example.recyclingapp;

import java.util.UUID;

public class User {
    private String name;
    private String id;
    private String username;
    private String password;
    private String email;
    private double materialKgs=0;
    private double glassKg=0;
    private double plasticKg=0;
    private double aluminiumKg=0;
    private double papperKg=0;
    private int points=0;

    public User(String name,String email, String username, String password,int points) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.points = 0;
        this.id = UUID.randomUUID().toString();;
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
        return papperKg;
    }

    public int getPoints() {
        return points;
    }
    public String getId() {
        return id;
    }

    public void createForm(){

    }

    public boolean isUser(String userID){
        return this.id.equals(userID);
    }

    public void reward(String material, double kg, int rewardPoints){
        points+=rewardPoints;
        switch (material){
            case "Glass" : glassKg+=kg;
            case "Plastic" : plasticKg+=kg;
            case "Paper" : papperKg+=kg;
            case "Aluminium": aluminiumKg+=kg;
        }
    }

}
