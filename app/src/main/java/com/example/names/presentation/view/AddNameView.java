package com.example.names.presentation.view;

import moxy.MvpView;
import moxy.viewstate.strategy.SkipStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface AddNameView extends MvpView {
    @StateStrategyType(SkipStrategy.class)
    void showSuccessMassage(String massage);

    @StateStrategyType(SkipStrategy.class)
    void showErrorMassage(String massage);

    @StateStrategyType(SkipStrategy.class)
    void showMassageEditTextIsEmpty(String massage);

    @StateStrategyType(SkipStrategy.class)
    void rootViewIsFocused();

    @StateStrategyType(SkipStrategy.class)
    void setTextEditText(String text);

    @StateStrategyType(SkipStrategy.class)
    void showKeyboard();

    @StateStrategyType(SkipStrategy.class)
    void hideKeyboard();
}
