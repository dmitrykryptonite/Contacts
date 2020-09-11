package com.example.names.domain;

import com.example.names.domain.entities.Contact;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface InfoInteractor {
    Single<Contact> getInfoContact();

    Completable editContact(int id, String name, String callNumber);
}
