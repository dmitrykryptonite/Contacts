package com.example.contacts.presentation.view;

import com.example.contacts.domain.entities.Contact;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndStrategy;
import moxy.viewstate.strategy.SkipStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface InfoView extends MvpView {

    @StateStrategyType(SkipStrategy.class)
    void editTextNameClearFocus();

    @StateStrategyType(SkipStrategy.class)
    void editTextCallNumberClearFocus();

    @StateStrategyType(SkipStrategy.class)
    void showWarningMassage(String massage);

    @StateStrategyType(SkipStrategy.class)
    void showFinishActivityMassage(String massage);

    @StateStrategyType(SkipStrategy.class)
    void hideKeyboard();

    @StateStrategyType(SkipStrategy.class)
    void finishActivity();

    @StateStrategyType(AddToEndStrategy.class)
    void setContact(Contact contact);

    @StateStrategyType(SkipStrategy.class)
    void showPanel(boolean isEditingMode);
}
