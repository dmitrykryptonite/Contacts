package com.example.names.data.databases;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.names.app.App;
import com.example.names.domain.entities.Contact;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

import static com.example.names.data.databases.DatabaseContacts.CALL_NUMBER;
import static com.example.names.data.databases.DatabaseContacts.CONTACT_ID;
import static com.example.names.data.databases.DatabaseContacts.NAME;
import static com.example.names.data.databases.DatabaseContacts.TABLE_CONTACTS;

public class DatabaseContactsManager {
    private static DatabaseContactsManager instance;

    public static DatabaseContactsManager getInstance() {
        if (instance == null)
            instance = new DatabaseContactsManager();
        return instance;
    }

    public Observable<List<Contact>> contactsUpdateListener;
    private ObservableEmitter<List<Contact>> emitter;
    private DatabaseContacts databaseContacts;

    private DatabaseContactsManager() {
        contactsUpdateListener = Observable.create(emitter ->
                DatabaseContactsManager.this.emitter = emitter);
        databaseContacts = new DatabaseContacts(App.getApp());
    }

    private int connectionsCount = 0;

    private void closeDB() {
        connectionsCount--;
        if (connectionsCount == 0) {
            databaseContacts.close();
        }
    }

    public void getListContacts() {
        emitter.onNext(getAllContacts());
    }

    public Completable addContact(String name, String callNumber) {
        return Completable.create(emitter -> {
            ContentValues values = new ContentValues();
            SQLiteDatabase sqLiteDatabase = databaseContacts.getWritableDatabase();
            connectionsCount++;
            values.put(NAME, name);
            values.put(CALL_NUMBER, callNumber);
            sqLiteDatabase.insert(TABLE_CONTACTS, null, values);
            closeDB();
            emitter.onComplete();
            DatabaseContactsManager.this.emitter.onNext(getAllContacts());
        });
    }

    private List<Contact> getAllContacts() {
        List<Contact> contactsList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = databaseContacts.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_CONTACTS +
                " ORDER BY " + CONTACT_ID + " DESC", null);
        if (cursor.moveToFirst()) {
            int idColIndex = cursor.getColumnIndex(CONTACT_ID);
            int nameColIndex = cursor.getColumnIndex(NAME);
            int callNumberColIndex = cursor.getColumnIndex(CALL_NUMBER);
            do {
                int id = cursor.getInt(idColIndex);
                String name = cursor.getString(nameColIndex);
                String callNumber = cursor.getString( callNumberColIndex);
                Contact contact = new Contact(id, name, callNumber);
                contactsList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        databaseContacts.close();
        return contactsList;
    }

    public Completable deleteAllContacts() {
        return Completable.create(emitter -> {
            SQLiteDatabase sqLiteDatabase = databaseContacts.getWritableDatabase();
            connectionsCount++;
            sqLiteDatabase.delete(TABLE_CONTACTS, null, null);
            closeDB();
            emitter.onComplete();
            DatabaseContactsManager.this.emitter.onNext(getAllContacts());
        });
    }

    public Completable deleteContact(Contact contact) {
        return Completable.create(emitter -> {
            SQLiteDatabase sqLiteDatabase = databaseContacts.getWritableDatabase();
            connectionsCount++;
            sqLiteDatabase.execSQL("DELETE FROM " + TABLE_CONTACTS + " WHERE " + CONTACT_ID + " = " +
                    contact.getId());
            closeDB();
            emitter.onComplete();
            DatabaseContactsManager.this.emitter.onNext(getAllContacts());
        });
    }

    public Completable editName(int id, String name) {
        return Completable.create(emitter -> {
            SQLiteDatabase sqLiteDatabase = databaseContacts.getWritableDatabase();
            connectionsCount++;
            sqLiteDatabase.execSQL("UPDATE " + TABLE_CONTACTS + " SET " + NAME + " = '" + name +
                    "' WHERE " + CONTACT_ID + " = " + id);
            closeDB();
            emitter.onComplete();
            DatabaseContactsManager.this.emitter.onNext(getAllContacts());
        });
    }

    public Completable editCallNumber(int id, String callNumber) {
        return Completable.create(emitter -> {
            SQLiteDatabase sqLiteDatabase = databaseContacts.getWritableDatabase();
            connectionsCount++;
            sqLiteDatabase.execSQL("UPDATE " + TABLE_CONTACTS +
                    " SET " + CALL_NUMBER + " = '" + callNumber +
                    "' WHERE " + CONTACT_ID + " = " + id);
            closeDB();
            emitter.onComplete();
            DatabaseContactsManager.this.emitter.onNext(getAllContacts());
        });
    }
}
