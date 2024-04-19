package com.example.recyclingapp;

import java.util.ArrayList;
import java.util.List;

public class RecyclingRecordService {

    private List<RecyclingRecord> records;

    public RecyclingRecordService() {

        this.records = new ArrayList<>();
    }

    // Προσθήκη νέας καταγραφής
    public void addRecord(RecyclingRecord record) {

        records.add(record);
    }

    // Ανάκτηση όλων των καταγραφών
    public List<RecyclingRecord> getAllRecords() {

        return records;
    }

    public RecyclingRecord getRecordById(int recordId) {
        for (RecyclingRecord record : records) {
            if (record.getId() == recordId) {
                return record;
            }
        }
        return null; // Επιστρέφουμε null αν δεν βρεθεί καταγραφή με το συγκεκριμένο ID
    }

    // Έγκριση καταγραφής
    public void approveRecyclingRecord(RecyclingRecord record) {
        record.setApproved(true);
    }


    public void rejectRecyclingRecord(RecyclingRecord record) {
        record.setApproved(false);
    }
}

