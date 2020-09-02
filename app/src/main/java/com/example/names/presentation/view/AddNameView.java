package com.example.names.presentation.view;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface AddNameView extends MvpView {
    @StateStrategyType(AddToEndSingleStrategy.class)
    void showSuccessMassage(String massage);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showErrorMassage(String massage);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showMassageEditTextIsEmpty(String massage);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void editTextClearFocus();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void setTextEditText(String text);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showKeyboard();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void hideKeyboard();
}
