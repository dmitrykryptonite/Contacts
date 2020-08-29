package com.example.names.domain;

import com.example.names.domain.entities.Name;

import io.reactivex.Completable;

public interface MainRepository {
    Completable saveName(String name);
    Completable deleteAllNames();
    Completable deleteItem(Name name);
    void getListNames();
}
