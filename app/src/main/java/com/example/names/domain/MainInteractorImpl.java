package com.example.names.domain;

import com.example.names.data.repositories.MainRepositoryImpl;

import io.reactivex.Completable;

public class MainInteractorImpl implements MainInteractor {
    private MainRepositoryImpl mainRepositoryImpl = MainRepositoryImpl.getInstance();

    @Override
    public Completable deleteAllContacts() {
        return mainRepositoryImpl.deleteAllContacts();
    }
}
