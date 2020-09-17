package com.example.contacts.presentation.presenters;

import com.example.contacts.domain.AddContactInteractorImpl;
import com.example.contacts.presentation.view.AddContactView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class AddContactPresenter extends MvpPresenter<AddContactView> {
    private AddContactInteractorImpl addContactInteractor = new AddContactInteractorImpl();
    private Disposable disposableSaveContact;

    public void onRootViewClicked() {
        getViewState().editTextCallNumberClearFocus();
        getViewState().editTextNameClearFocus();
        getViewState().hideKeyboard();
    }

    public void wrongLengthEditTextName() {
        getViewState().showWarningMassage("Sorry, length name must be 1-40 symbols");
    }

    public void wrongLengthEditTextCallNumber() {
        getViewState().showWarningMassage("Sorry, length call number must be 1-15 symbols");
    }

    public void onBtnSubmitClicked(String name, String callNumber) {
        if (name.isEmpty() || callNumber.isEmpty())
            getViewState().showWarningMassage("The lines must not be empty");
        else if (name.length() >= 41)
            getViewState().showWarningMassage("Sorry, length name must be 1-40 symbols");
        else if (callNumber.length() >= 16)
            getViewState().showWarningMassage("Sorry, length call number must be 1-15 symbols");
        else {
            disposableSaveContact = addContactInteractor.saveContact(name, callNumber)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> {
                        getViewState().setTextEditTextName("");
                        getViewState().setTextEditTextCallNumber("");
                        getViewState().showSuccessMassage("Contact saved");
                        getViewState().editTextCallNumberClearFocus();
                        getViewState().editTextNameClearFocus();
                        getViewState().hideKeyboard();
                    }, throwable -> getViewState().showErrorMassage(throwable.getMessage()));
        }
    }

    public void onPauseView() {
        getViewState().editTextCallNumberClearFocus();
        getViewState().editTextNameClearFocus();
        getViewState().hideKeyboard();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposableSaveContact != null && disposableSaveContact.isDisposed())
            disposableSaveContact.dispose();
    }
}
