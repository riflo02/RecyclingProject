package com.example.recyclingapp;

public class AdminController {

    private RecyclingRecordService recyclingRecordService; // for access to recycling records
    private UserService userService; //for users

    public AdminController() {
        this.recyclingRecordService = new RecyclingRecordService();
        this.userService = new UserService();
    }

    //Method for approving a record and awarding points
    public void approveRecord(int recordId){
        recyclingRecordService.approveRecord(recordId);

    }
}
