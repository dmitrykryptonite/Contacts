package com.example.contacts.domain;

import com.example.contacts.data.repositories.MainRepositoryImpl;
import com.example.contacts.domain.entities.Contact;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

public class InfoInteractorImpl implements InfoInteractor {
    private MainRepositoryImpl mainRepositoryImpl = MainRepositoryImpl.getInstance();
    public Observable<List<Contact>> contactsUpdateListener = mainRepositoryImpl.contactsUpdateListener;

    @Override
    public Single<Contact> getInfoContact() {
        return mainRepositoryImpl.getInfoContact();
    }

    @Override
    public Completable editContact(int id, String name, String callNumber) {
        return mainRepositoryImpl.editContact(id, name, callNumber);
    }

    @Override
    public Completable deleteContact(Contact contact) {
        return mainRepositoryImpl.deleteContact(contact);
    }
}
