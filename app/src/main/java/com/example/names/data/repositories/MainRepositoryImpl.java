package com.example.names.data.repositories;

import com.example.names.data.databases.DatabaseNamesManager;
import com.example.names.domain.MainRepository;
import com.example.names.domain.entities.Name;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;


public class MainRepositoryImpl implements MainRepository {
    private static MainRepositoryImpl instance;
    public static MainRepositoryImpl getInstance() {
        if (instance == null)
            instance = new MainRepositoryImpl();
        return instance;
    }
    private DatabaseNamesManager databaseNamesManager = DatabaseNamesManager.getInstance();
    public Observable<List<Name>> namesUpdateListener = databaseNamesManager.namesUpdateListener;

    @Override
    public Completable saveName(String name) {
        return databaseNamesManager.addName(name);
    }

    @Override
    public Completable deleteAllNames() {
        return databaseNamesManager.deleteAllNames();
    }

    @Override
    public Completable deleteItem(Name name) {
        return databaseNamesManager.deleteItem(name);
    }

    @Override
    public void getListNames() {
        databaseNamesManager.getListNames();
    }
}
