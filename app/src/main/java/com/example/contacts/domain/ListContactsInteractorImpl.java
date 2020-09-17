package com.example.contacts.domain;

import com.example.contacts.data.repositories.MainRepositoryImpl;
import com.example.contacts.domain.entities.Contact;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public class ListContactsInteractorImpl implements ListContactsInteractor {
    private MainRepositoryImpl mainRepositoryImpl = MainRepositoryImpl.getInstance();
    public Observable<List<Contact>> contactsUpdateListener = mainRepositoryImpl.contactsUpdateListener;

    @Override
    public Completable deleteContact(Contact contact) {
        return mainRepositoryImpl.deleteContact(contact);
    }

    @Override
    public void getListContacts() {
        mainRepositoryImpl.getListContacts();
    }

    @Override
    public void saveInfoContact(Contact contact) {
        mainRepositoryImpl.saveInfoContact(contact);
    }
}
