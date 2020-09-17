package com.example.contacts.domain;

import com.example.contacts.domain.entities.Contact;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface InfoInteractor {
    Single<Contact> getInfoContact();

    Completable editContact(int id, String name, String callNumber);

    Completable deleteContact(Contact contact);
}
