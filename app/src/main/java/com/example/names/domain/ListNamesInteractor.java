package com.example.names.domain;

import com.example.names.domain.entities.Name;

import io.reactivex.Completable;

public interface ListNamesInteractor {
    Completable deleteName(Name name);

    void getListNames();

    void saveEditName(Name name);
}
