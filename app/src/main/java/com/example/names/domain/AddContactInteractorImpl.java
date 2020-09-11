package com.example.names.domain;

import com.example.names.data.repositories.MainRepositoryImpl;

import io.reactivex.Completable;

public class AddContactInteractorImpl implements AddContactInteractor {
    private MainRepositoryImpl mainRepositoryImpl = MainRepositoryImpl.getInstance();

    @Override
    public Completable saveContact(String name, String callNumber) {
        return mainRepositoryImpl.saveContact(name, callNumber);
    }
}
