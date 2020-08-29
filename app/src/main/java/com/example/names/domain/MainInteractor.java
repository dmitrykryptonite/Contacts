package com.example.names.domain;

import io.reactivex.Completable;

public interface MainInteractor {
    Completable deleteAllNames();
}
