package com.example.recyclingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;

public class SQLiteConnection {
        private SelectionLoggerDbHelper dbHelper;
        private SQLiteDatabase db;

        SQLiteConnection(Context context){
            dbHelper = new SelectionLoggerDbHelper(context);
            db = dbHelper.getWritableDatabase();
        }

        public long insert(String name, String email,String username, String password){

            ContentValues values = new ContentValues();
            values.put(SelectionLoggerContract.LoggerEntry.COLUMN_NAME_NAME, name);
            values.put(SelectionLoggerContract.LoggerEntry.COLUMN_NAME_EMAIL, email);
            values.put(SelectionLoggerContract.LoggerEntry.COLUMN_NAME_USERNAME, username);
            values.put(SelectionLoggerContract.LoggerEntry.COLUMN_NAME_PASSWORD, password);
//            values.put(SelectionLoggerContract.LoggerEntry.COLUMN_NAME_TOTAL_POINTS, points);
            values.put(SelectionLoggerContract.LoggerEntry.COLUMN_NAME_TIMESTAMP, new Date(System.currentTimeMillis()).toString());

            long newRowId = db.insert(SelectionLoggerContract.LoggerEntry.TABLE_NAME, null, values);
            return newRowId;

        }

        public void close(){
            db.close();
        }
    }


