package com.example.names.domain;

import com.example.names.domain.entities.Contact;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface InfoInteractor {
    Single<Contact> getInfoContact();

    Completable editName(int id, String name);

    Completable editCallNumber(int id, String callNumber);
}
