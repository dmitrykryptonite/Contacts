package com.example.names.presentation.presenters;

import com.example.names.domain.InfoInteractorImpl;
import com.example.names.domain.entities.Contact;
import com.example.names.navigation.Router;
import com.example.names.presentation.view.InfoView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class InfoPresenter extends MvpPresenter<InfoView> {
    private InfoInteractorImpl infoInteractorImpl = new InfoInteractorImpl();
    private Disposable disposableSetInfoContact, disposableUpdateListContacts,
            disposableInfoContact, disposableDeleteContact;
    private Router router;
    private Contact mContact;
    private boolean isEditingMode = false;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        disposableSetInfoContact = infoInteractorImpl.getInfoContact()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(contact -> {
                    getViewState().setContact(contact);
                    mContact = contact;
                });
        disposableUpdateListContacts = infoInteractorImpl.contactsUpdateListener
                .flatMap(Observable::fromIterable)
                .filter(contact -> contact.getId() == mContact.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(contact -> {
                    getViewState().setContact(contact);
                    mContact = contact;
                });
    }

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

    public void setRouter(Router router) {
        this.router = router;
    }

    public void onBtnCallCLicked() {
        router.openCallScreen(mContact);
    }

    public void onBtnEditClicked() {
        isEditingMode = true;
        getViewState().showPanel(isEditingMode);
    }

    public void onBtnCancelClicked() {
        isEditingMode = false;
        getViewState().showPanel(isEditingMode);
        getViewState().showFinishActivityMassage("Contact has not changed");
    }

    public void onBtnOkClicked(String name, String callNumber) {
        name = name.replaceAll("'", "''");
        if (name.isEmpty() || callNumber.isEmpty())
            getViewState().showWarningMassage("The line must not be empty");
        else if (name.length() >= 41)
            getViewState().showWarningMassage("Sorry, length name must be 1-40 symbols");
        else if (callNumber.length() >= 16)
            getViewState().showWarningMassage("Sorry, length call number must be 1-15 symbols");
        else {
            disposableInfoContact = infoInteractorImpl.editContact(mContact.getId(), name, callNumber)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> {
                        isEditingMode = false;
                        getViewState().showPanel(isEditingMode);
                        getViewState().showFinishActivityMassage("Contact changed");
                    });
        }
    }

    public void onBtnDeleteClicked() {
        disposableDeleteContact = infoInteractorImpl.deleteContact(mContact)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    getViewState().hideKeyboard();
                    getViewState().finishActivity();
                    getViewState().showFinishActivityMassage("Contact deleted");
                });
    }

    public void onPauseView() {
        getViewState().editTextNameClearFocus();
        getViewState().editTextCallNumberClearFocus();
        getViewState().hideKeyboard();
    }

    public void onBackPressed() {
        getViewState().hideKeyboard();
        getViewState().finishActivity();
    }

    public void onResumeView() {
        getViewState().showPanel(isEditingMode);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposableSetInfoContact != null && disposableSetInfoContact.isDisposed())
            disposableSetInfoContact.dispose();
        if (disposableInfoContact != null && disposableInfoContact.isDisposed())
            disposableInfoContact.dispose();
        if (disposableDeleteContact != null && disposableDeleteContact.isDisposed())
            disposableDeleteContact.dispose();
        if (disposableUpdateListContacts != null && disposableUpdateListContacts.isDisposed())
            disposableUpdateListContacts.dispose();
    }
}
