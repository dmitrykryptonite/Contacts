package com.example.names.domain;

import io.reactivex.Completable;

public interface AddContactInteractor {
    Completable saveContact(String name, String callNumber);
}
