package com.example.names.presentation.presenters;

public interface AddNamePresenter {
    void valueEditTextIsEmpty();

    void onRootViewClicked();

    void onBtnSubmitClicked(String name);

    void onEditTextFocusChanged(boolean hasFocused);

    void releasePresenter();
}
