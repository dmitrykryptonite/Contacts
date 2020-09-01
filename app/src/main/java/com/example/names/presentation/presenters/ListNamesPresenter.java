package com.example.names.presentation.presenters;

import com.example.names.domain.entities.Name;

public interface ListNamesPresenter {
    void onCreateView();

    void onBtnDeleteClicked(Name name);

    void releasePresenter();
}
