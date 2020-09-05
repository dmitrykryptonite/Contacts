package com.example.names.data;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.names.app.App;
import com.example.names.domain.entities.Name;

import io.reactivex.Single;

public class SharedPreferencesManager {
    private static SharedPreferencesManager instance;

    public static SharedPreferencesManager getInstance() {
        if (instance == null)
            instance = new SharedPreferencesManager();
        return instance;
    }

    private SharedPreferences preferences;
    private final String SAVED_NAME = "saved_text";
    private final String SAVED_ID = "saved_id";

    public void saveEditName(Name name) {
        preferences = PreferenceManager.getDefaultSharedPreferences(App.getApp());
        SharedPreferences.Editor ed = preferences.edit();
        ed.putString(SAVED_NAME, name.getName());
        ed.putInt(SAVED_ID, name.getId());
        ed.apply();
    }

    public Single<Name> getEditName() {
        return Single.create(emitter -> {
            preferences = PreferenceManager.getDefaultSharedPreferences(App.getApp());
            String savedName = preferences.getString(SAVED_NAME, "");
            int idName = preferences.getInt(SAVED_ID, 0);
            Name name = new Name(idName, savedName);
            emitter.onSuccess(name);
        });
    }
}
