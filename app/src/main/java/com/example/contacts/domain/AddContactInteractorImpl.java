package com.example.contacts.domain;

import com.example.contacts.data.repository.MainRepositoryImpl;

import io.reactivex.Completable;

public class AddContactInteractorImpl implements AddContactInteractor {
    private MainRepositoryImpl mainRepositoryImpl = MainRepositoryImpl.getInstance();

    @Override
    public Completable saveContact(String name, String callNumber) {
        return mainRepositoryImpl.saveContact(name, callNumber);
    }
}
