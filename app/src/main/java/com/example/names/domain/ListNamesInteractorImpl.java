package com.example.names.domain;

import com.example.names.data.repositories.MainRepositoryImpl;
import com.example.names.domain.entities.Name;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public class ListNamesInteractorImpl implements ListNamesInteractor {
    private MainRepositoryImpl mainRepositoryImpl = MainRepositoryImpl.getInstance();
    public Observable<List<Name>> namesUpdateListener = mainRepositoryImpl.namesUpdateListener;

    @Override
    public Completable deleteItem(Name name) {
        return mainRepositoryImpl.deleteItem(name);
    }

    @Override
    public void getListNames() {
        mainRepositoryImpl.getListNames();
    }

    @Override
    public void saveEditName(Name name) {
        mainRepositoryImpl.saveEditName(name);
    }
}
