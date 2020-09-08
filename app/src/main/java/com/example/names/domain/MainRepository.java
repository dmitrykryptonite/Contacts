package com.example.names.domain;

import com.example.names.domain.entities.Name;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface MainRepository {
    Completable saveName(String name);

    Completable deleteAllNames();

    Completable deleteName(Name name);

    void getListNames();

    void saveEditName(Name name);

    Single<Name> getEditName();

    Completable editName(int id, String name);
}
