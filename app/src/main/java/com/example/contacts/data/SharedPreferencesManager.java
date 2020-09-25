package com.example.contacts.data;

import android.content.SharedPreferences;
import androidx.preference.PreferenceManager;

import com.example.contacts.app.App;
import com.example.contacts.domain.entities.Contact;

import io.reactivex.Single;

public class SharedPreferencesManager {
    private static SharedPreferencesManager instance;

    public static SharedPreferencesManager getInstance() {
        if (instance == null)
            instance = new SharedPreferencesManager();
        return instance;
    }

    private SharedPreferences preferences;
    private final String SAVED_ID = "saved_id";
    private final String SAVED_NAME = "saved_text";
    private final String SAVED_CALL_NUMBER = "saved_call_number";

    public void saveInfoContact(Contact contact) {
        preferences = PreferenceManager.getDefaultSharedPreferences(App.getApp());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(SAVED_ID, contact.getId());
        editor.putString(SAVED_NAME, contact.getName());
        editor.putString(SAVED_CALL_NUMBER, contact.getCallNumber());
        editor.apply();
    }

    public Single<Contact> getInfoContact() {
        return Single.create(emitter -> {
            preferences = PreferenceManager.getDefaultSharedPreferences(App.getApp());
            int savedId = preferences.getInt(SAVED_ID, 0);
            String savedName = preferences.getString(SAVED_NAME, "");
            String callNumber = preferences.getString(SAVED_CALL_NUMBER, "");
            Contact contact = new Contact(savedId, savedName, callNumber);
            emitter.onSuccess(contact);
        });
    }
}
