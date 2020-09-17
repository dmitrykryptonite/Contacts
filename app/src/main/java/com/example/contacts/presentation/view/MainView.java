package com.example.contacts.presentation.view;

import moxy.MvpView;
import moxy.viewstate.strategy.SkipStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface MainView extends MvpView {
    @StateStrategyType(SkipStrategy.class)
    void showSuccessMassage(String massage);

    @StateStrategyType(SkipStrategy.class)
    void showErrorMassage(String massage);

    @StateStrategyType(SkipStrategy.class)
    void hideKeyboard();
}
