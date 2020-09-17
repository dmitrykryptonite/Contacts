package com.example.contacts.data.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseContacts extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "names.db";
    private static final int DATABASE_VERSION = 1;

    static final String TABLE_CONTACTS = "names";
    static final String CONTACT_ID = "_id";
    static final String NAME = "name";
    static final String CALL_NUMBER = "call_number";
    private static final String TIME_ADDED = "time_added";
    private static final String CONTACT_ADDED = "nameAdded";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_CONTACTS + " (" +
                    CONTACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NAME + " TEXT, " +
                    CALL_NUMBER + " TEXT, " +
                    TIME_ADDED + " INTEGER, " +
                    CONTACT_ADDED + " TEXT default CURRENT_TIMESTAMP" + ")";

    DatabaseContacts(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
