package com.example.names.domain;

import com.example.names.domain.entities.Name;

import io.reactivex.Completable;

public interface ListNamesInteractor {
    Completable deleteItem(Name name);

    void getListNames();
}
