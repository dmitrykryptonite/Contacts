package com.example.names.data.repositories;

import com.example.names.data.SharedPreferencesManager;
import com.example.names.data.databases.DatabaseContactsManager;
import com.example.names.domain.MainRepository;
import com.example.names.domain.entities.Contact;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;


public class MainRepositoryImpl implements MainRepository {
    private static MainRepositoryImpl instance;

    public static MainRepositoryImpl getInstance() {
        if (instance == null)
            instance = new MainRepositoryImpl();
        return instance;
    }

    private DatabaseContactsManager databaseContactsManager = DatabaseContactsManager.getInstance();
    private SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
    public Observable<List<Contact>> contactsUpdateListener = databaseContactsManager.contactsUpdateListener;

    @Override
    public Completable saveContact(String name, String callNumber) {
        return databaseContactsManager.addContact(name, callNumber);
    }

    @Override
    public Completable deleteAllContacts() {
        return databaseContactsManager.deleteAllContacts();
    }

    @Override
    public Completable deleteContact(Contact contact) {
        return databaseContactsManager.deleteContact(contact);
    }

    @Override
    public void getListContacts() {
        databaseContactsManager.getListContacts();
    }

    @Override
    public void saveInfoContact(Contact contact) {
        sharedPreferencesManager.saveInfoContact(contact);
    }

    @Override
    public Single<Contact> getInfoContact() {
        return sharedPreferencesManager.getInfoContact();
    }

    @Override
    public Completable editContact(int id, String name, String callNumber) {
        return databaseContactsManager.editContact(id, name, callNumber);
    }
}
