package com.example.contacts.presentation.view;

import com.example.contacts.domain.entities.Contact;

import java.util.List;

import moxy.MvpView;
import moxy.viewstate.strategy.SkipStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface ListContactsView extends MvpView {
    @StateStrategyType(SkipStrategy.class)
    void showSuccessMassage(String massage);

    @StateStrategyType(SkipStrategy.class)
    void showErrorMassage(String massage);

    @StateStrategyType(SkipStrategy.class)
    void updateContactsList(List<Contact> listContacts);

    @StateStrategyType(SkipStrategy.class)
    void openCallScreen(Contact contact);

    @StateStrategyType(SkipStrategy.class)
    void openInfoScreen(Contact contact);

    @StateStrategyType(SkipStrategy.class)
    void deleteContact(Contact contact);
}
