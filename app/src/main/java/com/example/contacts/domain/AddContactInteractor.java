package com.example.contacts.domain;

import io.reactivex.Completable;

public interface AddContactInteractor {
    Completable saveContact(String name, String callNumber);
}
