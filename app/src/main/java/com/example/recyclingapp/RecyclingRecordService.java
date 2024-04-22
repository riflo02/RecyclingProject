package com.example.recyclingapp;

import java.util.List;

public class RecyclingRecordService {

    private List<RecyclingRecord> records;

    public RecyclingRecordService(List<RecyclingRecord> record) {
        this.records = record;
    }

    public void approveRec(User applicant, RecyclingRecord rec){
        for(RecyclingRecord record: records){
            if(record.equals(rec)){
                if(rec.getMaterialType().equals("Glass") || rec.getMaterialType().equals("Plastic")
                        ||rec.getMaterialType().equals("Aluminium") ||rec.getMaterialType().equals("Papper"))
                {
                    rec.setApproved(true);
                    applicant.reward(rec.getMaterialType(), rec.getQuantity(), rec.getRewardPoints());
                    //Mikrh moy Evaaa
                }
            }
        }
    }
}
