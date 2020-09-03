package com.example.names.data.databases;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.names.app.App;
import com.example.names.domain.entities.Name;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

import static com.example.names.data.databases.DatabaseNames.NAME_ID;
import static com.example.names.data.databases.DatabaseNames.TABLE_NAMES;


public class DatabaseNamesManager {
    private static DatabaseNamesManager instance;

    public static DatabaseNamesManager getInstance() {
        if (instance == null)
            instance = new DatabaseNamesManager();
        return instance;
    }

    public Observable<List<Name>> namesUpdateListener;
    private ObservableEmitter<List<Name>> emitter;
    private DatabaseNames databaseNames;

    private DatabaseNamesManager() {
        namesUpdateListener = Observable.create(emitter -> DatabaseNamesManager.this.emitter = emitter);
        databaseNames = new DatabaseNames(App.getApp());
    }

    private int connectionsCount = 0;

    private void closeDB() {
        connectionsCount--;
        if (connectionsCount == 0) {
            databaseNames.close();
        }
    }

    public void getListNames() {
        emitter.onNext(getAllNames());
    }

    public Completable addName(final String name) {
        return Completable.create(emitter -> {
            ContentValues values = new ContentValues();
            SQLiteDatabase sqLiteDatabase = databaseNames.getWritableDatabase();
            connectionsCount++;
            values.put("name", name);
            sqLiteDatabase.insert("names", null, values);
            closeDB();
            emitter.onComplete();
            DatabaseNamesManager.this.emitter.onNext(getAllNames());
        });
    }

    private List<Name> getAllNames() {
        List<Name> namesList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = databaseNames.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAMES +
                " ORDER BY " + NAME_ID + " DESC", null);
        if (cursor.moveToFirst()) {
            int idColIndex = cursor.getColumnIndex("_id");
            int nameColIndex = cursor.getColumnIndex("name");
            do {
                int id = cursor.getInt(idColIndex);
                String strName = cursor.getString(nameColIndex);
                Name name = new Name(id, strName);
                namesList.add(name);
            } while (cursor.moveToNext());
        }
        cursor.close();
        databaseNames.close();
        return namesList;
    }

    public Completable deleteAllNames() {
        return Completable.create(emitter -> {
            SQLiteDatabase sqLiteDatabase = databaseNames.getWritableDatabase();
            connectionsCount++;
            sqLiteDatabase.delete("names", null, null);
            closeDB();
            emitter.onComplete();
            DatabaseNamesManager.this.emitter.onNext(getAllNames());
        });
    }

    public Completable deleteItem(final Name name) {
        return Completable.create(emitter -> {
            SQLiteDatabase sqLiteDatabase = databaseNames.getWritableDatabase();
            connectionsCount++;
            sqLiteDatabase.execSQL("DELETE FROM " + TABLE_NAMES + " WHERE _id = " +
                    name.getId());
            closeDB();
            emitter.onComplete();
            DatabaseNamesManager.this.emitter.onNext(getAllNames());
        });
    }
}
