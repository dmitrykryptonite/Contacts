package com.example.names.data.repositories;

import com.example.names.data.SharedPreferencesManager;
import com.example.names.data.databases.DatabaseNamesManager;
import com.example.names.domain.MainRepository;
import com.example.names.domain.entities.Name;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;


public class MainRepositoryImpl implements MainRepository {
    private static MainRepositoryImpl instance;

    public static MainRepositoryImpl getInstance() {
        if (instance == null)
            instance = new MainRepositoryImpl();
        return instance;
    }

    private DatabaseNamesManager databaseNamesManager = DatabaseNamesManager.getInstance();
    public Observable<List<Name>> namesUpdateListener = databaseNamesManager.namesUpdateListener;
    private SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();

    @Override
    public Completable saveName(String name) {
        return databaseNamesManager.addName(name);
    }

    @Override
    public Completable deleteAllNames() {
        return databaseNamesManager.deleteAllNames();
    }

    @Override
    public Completable deleteName(Name name) {
        return databaseNamesManager.deleteName(name);
    }

    @Override
    public void getListNames() {
        databaseNamesManager.getListNames();
    }

    @Override
    public void saveEditName(Name name) {
        sharedPreferencesManager.saveEditName(name);
    }

    @Override
    public Single<Name> getEditName() {
        return sharedPreferencesManager.getEditName();
    }

    @Override
    public Completable editName(int id, String name) {
        return databaseNamesManager.editName(id, name);
    }
}
