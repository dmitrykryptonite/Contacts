package com.example.names.presentation.view;

import com.example.names.domain.entities.Name;

import java.util.List;

import moxy.MvpView;
import moxy.viewstate.strategy.SkipStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface ListNamesView extends MvpView {
    @StateStrategyType(SkipStrategy.class)
    void updateNamesList(List<Name> listNames);

    @StateStrategyType(SkipStrategy.class)
    void deleteItem(Name name);

    @StateStrategyType(SkipStrategy.class)
    void showSuccessMassage(String massage);

    @StateStrategyType(SkipStrategy.class)
    void showErrorMassage(String massage);

    @StateStrategyType(SkipStrategy.class)
    void openEditItemScreen();
}
