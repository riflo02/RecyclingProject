package com.example.recyclingapp;

import java.util.ArrayList;
import java.util.List;

public class Administrator {

    private List<User> userList;
    private RecyclingRecordService recyclingRecordSr;

    public Administrator(){

        userList = new ArrayList<>();

    }

    public void approval(String userId, RecyclingRecord rec){

        for(User user: userList){
            if(user.isUser(userId))
                recyclingRecordSr.approveRec(user, rec);
        }


    }

}
