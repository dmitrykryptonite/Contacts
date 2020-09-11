package com.example.names.domain;

import com.example.names.domain.entities.Contact;

import io.reactivex.Completable;

public interface ListContactsInteractor {
    Completable deleteContact(Contact contact);

    void getListContacts();

    void saveInfoContact(Contact contact);
}
