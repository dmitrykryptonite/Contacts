package com.example.contacts.data.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.contacts.app.App;
import com.example.contacts.domain.entities.Contact;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

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
        Observable<List<Contact>> observable = Observable.create(emitter ->
                DatabaseContactsManager.this.emitter = emitter);
        contactsUpdateListener = observable.share();
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
            values.put(DatabaseContacts.NAME, name);
            values.put(DatabaseContacts.CALL_NUMBER, callNumber);
            sqLiteDatabase.insert(DatabaseContacts.TABLE_CONTACTS, null, values);
            closeDB();
            emitter.onComplete();
            DatabaseContactsManager.this.emitter.onNext(getAllContacts());
        });
    }

    private List<Contact> getAllContacts() {
        List<Contact> contactsList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = databaseContacts.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DatabaseContacts.TABLE_CONTACTS +
                " ORDER BY " + DatabaseContacts.CONTACT_ID + " DESC", null);
        if (cursor.moveToFirst()) {
            int idColIndex = cursor.getColumnIndex(DatabaseContacts.CONTACT_ID);
            int nameColIndex = cursor.getColumnIndex(DatabaseContacts.NAME);
            int callNumberColIndex = cursor.getColumnIndex(DatabaseContacts.CALL_NUMBER);
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
            sqLiteDatabase.delete(DatabaseContacts.TABLE_CONTACTS, null, null);
            closeDB();
            emitter.onComplete();
            DatabaseContactsManager.this.emitter.onNext(getAllContacts());
        });
    }

    public Completable deleteContact(Contact contact) {
        return Completable.create(emitter -> {
            SQLiteDatabase sqLiteDatabase = databaseContacts.getWritableDatabase();
            connectionsCount++;
            sqLiteDatabase.execSQL("DELETE FROM " + DatabaseContacts.TABLE_CONTACTS + " WHERE " + DatabaseContacts.CONTACT_ID + " = " +
                    contact.getId());
            closeDB();
            emitter.onComplete();
            DatabaseContactsManager.this.emitter.onNext(getAllContacts());
        });
    }

    public Completable editContact(int id, String name, String callNumber) {
        return Completable.create(emitter -> {
            SQLiteDatabase sqLiteDatabase = databaseContacts.getWritableDatabase();
            connectionsCount++;
            sqLiteDatabase.execSQL("UPDATE " + DatabaseContacts.TABLE_CONTACTS + " SET " + DatabaseContacts.NAME + " = '" + name +
                    "' WHERE " + DatabaseContacts.CONTACT_ID + " = " + id);
            sqLiteDatabase.execSQL("UPDATE " + DatabaseContacts.TABLE_CONTACTS +
                    " SET " + DatabaseContacts.CALL_NUMBER + " = '" + callNumber +
                    "' WHERE " + DatabaseContacts.CONTACT_ID + " = " + id);
            closeDB();
            emitter.onComplete();
            DatabaseContactsManager.this.emitter.onNext(getAllContacts());
        });
    }
}
