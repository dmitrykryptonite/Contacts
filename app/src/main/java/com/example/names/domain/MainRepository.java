package com.example.names.domain;

import com.example.names.domain.entities.Name;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface MainRepository {
    Completable saveName(String name);

    Completable deleteAllNames();

    Completable deleteItem(Name name);

    void getListNames();

    void saveEditName(Name name);

    Single<Name> getEditName();
}
