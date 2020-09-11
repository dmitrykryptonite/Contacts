package com.example.names.presentation.view;

import com.example.names.domain.entities.Contact;

import moxy.MvpView;
import moxy.viewstate.strategy.SkipStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface InfoView extends MvpView {

    @StateStrategyType(SkipStrategy.class)
    void showWarningMassage(String massage);

    @StateStrategyType(SkipStrategy.class)
    void showFinishActivityMassage(String massage);

    @StateStrategyType(SkipStrategy.class)
    void rootViewIsFocused();

    @StateStrategyType(SkipStrategy.class)
    void showKeyboardForEtName();

    @StateStrategyType(SkipStrategy.class)
    void showKeyboardForEtCallNumber();

    @StateStrategyType(SkipStrategy.class)
    void hideKeyboard();

    @StateStrategyType(SkipStrategy.class)
    void finishActivity();

    @StateStrategyType(SkipStrategy.class)
    void setContact(Contact contact);
}
