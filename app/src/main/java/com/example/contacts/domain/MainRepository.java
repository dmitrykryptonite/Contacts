package com.example.contacts.domain;

import com.example.contacts.domain.entities.Contact;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface MainRepository {
    Completable saveContact(String name, String callNumber);

    Completable deleteAllContacts();

    Completable deleteContact(Contact contact);

    void getListContacts();

    void saveInfoContact(Contact contact);

    Single<Contact> getInfoContact();

    Completable editContact(int id, String name, String callNumber);
}
