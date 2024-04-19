package com.example.recyclingapp;

public class AdminController {

    private RecyclingRecordService recyclingRecordService; // for access to recycling records
    private UserService userService; //for users

    public AdminController() {
        this.recyclingRecordService = new RecyclingRecordService();
        this.userService = new UserService();
    }

    //Method for approving a record and awarding points
    public void approveAndReward(RecyclingRecord record, int points) {
        recyclingRecordService.approveRecyclingRecord(record); // Εγκρίνει την καταχώρηση
        User user = userService.getUserById(record.getUserId());
        userService.assignPointsForReward(user.getUserId(), points); // Αποδίδει πόντους στον χρήστη
    }

    public void rejectRecyclingRecord(RecyclingRecord record) {
        recyclingRecordService.rejectRecyclingRecord(record);
    }
}
