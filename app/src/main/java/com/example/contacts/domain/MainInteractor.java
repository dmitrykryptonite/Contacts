package com.example.contacts.domain;

import io.reactivex.Completable;

public interface MainInteractor {
    Completable deleteAllContacts();
}
