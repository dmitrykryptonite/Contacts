package com.example.names.presentation.view;

import com.example.names.domain.entities.Name;

import java.util.List;

public interface ListNamesView {
    void updateNamesList(List<Name> listNames);
    void deleteItem(Name name);
    void showSuccessMassage(String massage);
    void showErrorMassage(String massage);
}
