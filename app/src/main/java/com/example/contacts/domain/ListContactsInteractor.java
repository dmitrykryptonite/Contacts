package com.example.contacts.domain;

import com.example.contacts.domain.entities.Contact;

import io.reactivex.Completable;

public interface ListContactsInteractor {
    Completable deleteContact(Contact contact);

    void getListContacts();

    void saveInfoContact(Contact contact);
}
