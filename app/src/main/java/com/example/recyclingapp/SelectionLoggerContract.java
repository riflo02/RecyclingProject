package com.example.recyclingapp;

import android.provider.BaseColumns;

public final class SelectionLoggerContract {
    private SelectionLoggerContract(){};

    public static class LoggerEntry implements BaseColumns {
        public static final String TABLE_NAME = "Users";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_PASSWORD = "password";
//        public static final String COLUMN_NAME_TOTAL_POINTS = "points";
        public static final String COLUMN_NAME_TIMESTAMP = "timestamp";
    }
}
