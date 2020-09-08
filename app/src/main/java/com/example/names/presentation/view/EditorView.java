package com.example.names.presentation.view;

import com.example.names.domain.entities.Name;

import moxy.MvpView;
import moxy.viewstate.strategy.SkipStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface EditorView extends MvpView {

    @StateStrategyType(SkipStrategy.class)
    void showWarningMassage(String massage);

    @StateStrategyType(SkipStrategy.class)
    void showFinishActivityMassage(String massage);

    @StateStrategyType(SkipStrategy.class)
    void rootViewIsFocused();

    @StateStrategyType(SkipStrategy.class)
    void showKeyboard();

    @StateStrategyType(SkipStrategy.class)
    void hideKeyboard();

    @StateStrategyType(SkipStrategy.class)
    void finishActivity();

    @StateStrategyType(SkipStrategy.class)
    void setName(Name name);
}
