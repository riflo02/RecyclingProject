package com.example.recyclingapp;

import java.util.Date;

public class RecyclingRecord {
    private long id;
    private long userId;
    private String materialType;
    private double quantity;
    private Date timestamp;
    private boolean approved;
    private int rewardPoints;

    // Κατασκευαστής
    public RecyclingRecord(long id, long userId, String materialType, double quantity, Date timestamp) {
        this.id = id;
        this.userId = userId;
        this.materialType = materialType;
        this.quantity = quantity;
        this.timestamp = timestamp;
        this.approved = false; // Αρχικά η καταγραφή δεν είναι εγκεκριμένη
        this.rewardPoints = 0; // Αρχικά δεν έχουν αποδοθεί πόντοι ανταμοιβής
    }

    // Getters και Setters
    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public String getMaterialType() {
        return materialType;
    }

    public double getQuantity() {
        return quantity;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public int getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(int rewardPoints) {
        this.rewardPoints = rewardPoints;
    }
}
