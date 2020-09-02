package com.example.names.presentation.view;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface MainView extends MvpView {
    @StateStrategyType(AddToEndSingleStrategy.class)
    void showSuccessMassage(String massage);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showErrorMassage(String massage);
}
