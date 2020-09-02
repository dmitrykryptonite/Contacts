package com.example.names.presentation.view;

import com.example.names.domain.entities.Name;

import java.util.List;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface ListNamesView extends MvpView {
    @StateStrategyType(AddToEndSingleStrategy.class)
    void updateNamesList(List<Name> listNames);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void deleteItem(Name name);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showSuccessMassage(String massage);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showErrorMassage(String massage);
}
