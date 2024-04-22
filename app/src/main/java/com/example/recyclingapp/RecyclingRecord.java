package com.example.recyclingapp;

public class RecyclingRecord {

    private String materialType;

    private String userId;
    private double quantity;
    private boolean approved;
    private int rewardPoints;

    public RecyclingRecord(String materialType, double quantity, boolean approved, int rewardPoints) {
        this.materialType = materialType;
        this.quantity = quantity;
        this.approved = false;
        this.rewardPoints = 0;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
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
