package com.example.names.domain;

import io.reactivex.Completable;

public interface AddNameInteractor {
    Completable saveName(String name);
}
