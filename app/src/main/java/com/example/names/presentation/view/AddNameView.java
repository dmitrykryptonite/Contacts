package com.example.names.presentation.view;

public interface AddNameView {
    void showSuccessMassage(String massage);
    void showErrorMassage(String massage);
    void editTextClearFocus();
    void setTextEditText(String text);
    void hideKeyboard();
}
