package com.example.backrow;

import android.provider.BaseColumns;

public class UsersMaster {

    private UsersMaster() { }

    protected static class Notice implements BaseColumns {
        public static final String COLUMN_ID = "notice_ID";
        public static final String TABLE_NAME = "notice";
        public static final String COLUMN_NAME_TITLE = "username";
        public static final String COLUMN_NAME_DESCRIPTION = "password";
        public static final String COLUMN_NAME_DATE = "date_";
        public static final String COLUMN_NAME_IMAGE = "image_";

    }
}
