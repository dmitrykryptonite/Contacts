package com.example.names.presentation.presenters;

import com.example.names.domain.InfoInteractorImpl;
import com.example.names.domain.entities.Contact;
import com.example.names.presentation.view.InfoView;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class InfoPresenter extends MvpPresenter<InfoView> {
    private InfoInteractorImpl infoInteractorImpl = new InfoInteractorImpl();
    private Disposable disposableSetInfoContact, disposableInfoContact;

    public void onCreateView() {
        setEditContact();
    }

    public void wrongLengthEditText() {
        getViewState().showWarningMassage("Sorry, length name must be 1-40 symbols");
    }

    public void wrongLengthEditTextCallNumber() {
        getViewState().showWarningMassage("Sorry, length call number must be 1-13 symbols");
    }

    public void onRootViewClicked() {
        getViewState().rootViewIsFocused();
        getViewState().hideKeyboard();
    }

    public void onEditTextNameFocusChanged(boolean hasFocus) {
        if (hasFocus)
            getViewState().showKeyboardForEtName();
        else {
            getViewState().rootViewIsFocused();
            getViewState().hideKeyboard();
        }
    }

    public void onEditTextCallNumberFocusChanged(boolean hasFocus) {
        if (hasFocus)
            getViewState().showKeyboardForEtCallNumber();
        else {
            getViewState().rootViewIsFocused();
            getViewState().hideKeyboard();
        }
    }

    public void onImgCancelClicked() {
        getViewState().rootViewIsFocused();
        getViewState().hideKeyboard();
        getViewState().finishActivity();
        getViewState().showFinishActivityMassage("Contact has not changed");
    }

    public void onImgSubmitClicked(int id, String name, String callNumber) {
        name = name.replaceAll("'", "''");
        if (name.isEmpty() || callNumber.isEmpty())
            getViewState().showWarningMassage("The line must not be empty");
        else if (name.length() >= 41)
            getViewState().showWarningMassage("Sorry, length name must be 1-40 symbols");
        else if (callNumber.length() >= 14)
            getViewState().showWarningMassage("Sorry, length call number must be 1-13 symbols");
        else {
            Completable editContact = infoInteractorImpl.editContact(id, name, callNumber);
            disposableInfoContact = editContact.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> {
                        getViewState().rootViewIsFocused();
                        getViewState().hideKeyboard();
                        getViewState().finishActivity();
                        getViewState().showFinishActivityMassage("Contact changed");
                    });
        }
    }

    public void onBackPressed() {
        getViewState().rootViewIsFocused();
        getViewState().hideKeyboard();
        getViewState().showFinishActivityMassage("Contact has not changed");
    }

    public void onPauseView() {
        getViewState().rootViewIsFocused();
        getViewState().hideKeyboard();
    }

    private void setEditContact() {
        Single<Contact> getInfoContact = infoInteractorImpl.getInfoContact();
        disposableSetInfoContact = getInfoContact.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(name -> getViewState().setContact(name));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposableSetInfoContact != null && disposableSetInfoContact.isDisposed())
            disposableSetInfoContact.dispose();
        if (disposableInfoContact != null && disposableInfoContact.isDisposed())
            disposableInfoContact.dispose();
    }
}
