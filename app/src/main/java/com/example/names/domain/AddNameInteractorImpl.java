package com.example.names.domain;

import com.example.names.data.repositories.MainRepositoryImpl;

import io.reactivex.Completable;

public class AddNameInteractorImpl implements AddNameInteractor {
    private MainRepositoryImpl mainRepositoryImpl = MainRepositoryImpl.getInstance();
    @Override
    public Completable saveName(String name) {
        return mainRepositoryImpl.saveName(name);
    }
}
