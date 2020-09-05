package com.example.names.domain;

import com.example.names.data.repositories.MainRepositoryImpl;
import com.example.names.domain.entities.Name;

import io.reactivex.Single;

public class EditorInteractorImpl implements EditorInteractor {
    private MainRepositoryImpl mainRepositoryImpl = MainRepositoryImpl.getInstance();

    @Override
    public Single<Name> getEditName() {
        return mainRepositoryImpl.getEditName();
    }
}
