package com.example.names.presentation.view;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndStrategy;
import moxy.viewstate.strategy.SkipStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface AddContactView extends MvpView {
    @StateStrategyType(SkipStrategy.class)
    void showSuccessMassage(String massage);

    @StateStrategyType(SkipStrategy.class)
    void showErrorMassage(String massage);

    @StateStrategyType(SkipStrategy.class)
    void showWarningMassage(String massage);

    @StateStrategyType(SkipStrategy.class)
    void editTextNameClearFocus();

    @StateStrategyType(SkipStrategy.class)
    void editTextCallNumberClearFocus();

    @StateStrategyType(SkipStrategy.class)
    void setTextEditTextName(String text);

    @StateStrategyType(SkipStrategy.class)
    void setTextEditTextCallNumber(String text);

    @StateStrategyType(SkipStrategy.class)
    void hideKeyboard();
}
