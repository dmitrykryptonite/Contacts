package com.example.names.data.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseNames extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "names.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAMES = "names";
    public static final String NAME_ID = "_id";
    public static final String NAME = "name";
    public static final String TIME_ADDED = "time_added";
    private static final String NAME_ADDED = "nameAdded";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAMES + " (" +
                    NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NAME +  " TEXT, " +
                    TIME_ADDED + " INTEGER, " +
                    NAME_ADDED + " TEXT default CURRENT_TIMESTAMP" + ")";
    public DatabaseNames(@Nullable Context context) {
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
