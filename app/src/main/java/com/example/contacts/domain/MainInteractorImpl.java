package com.example.contacts.domain;

import com.example.contacts.data.repositories.MainRepositoryImpl;

import io.reactivex.Completable;

public class MainInteractorImpl implements MainInteractor {
    private MainRepositoryImpl mainRepositoryImpl = MainRepositoryImpl.getInstance();

    @Override
    public Completable deleteAllContacts() {
        return mainRepositoryImpl.deleteAllContacts();
    }
}
