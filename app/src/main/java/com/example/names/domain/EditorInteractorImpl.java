package com.example.names.domain;

import com.example.names.data.repositories.MainRepositoryImpl;

public class EditorInteractorImpl implements EditorInteractor {
    private MainRepositoryImpl mainRepositoryImpl = MainRepositoryImpl.getInstance();
}
