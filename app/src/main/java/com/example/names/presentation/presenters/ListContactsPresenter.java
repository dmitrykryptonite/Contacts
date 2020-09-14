package com.example.names.presentation.presenters;

import com.example.names.domain.ListContactsInteractorImpl;
import com.example.names.domain.entities.Contact;
import com.example.names.navigation.Router;
import com.example.names.presentation.view.ListContactsView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class ListContactsPresenter extends MvpPresenter<ListContactsView> {
    private ListContactsInteractorImpl listContactsInteractorImpl = new ListContactsInteractorImpl();
    private Disposable disposableUpdateListContacts, disposableDeleteContact;
    private Router router;

    public ListContactsPresenter() {
        disposableUpdateListContacts = listContactsInteractorImpl.contactsUpdateListener
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(contacts -> getViewState().updateContactsList(contacts));
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        listContactsInteractorImpl.getListContacts();
    }

    @Override
    public void attachView(ListContactsView view) {
        super.attachView(view);
        listContactsInteractorImpl.getListContacts();
    }

    public void setRouter(Router router) {
        this.router = router;
    }

    public void onBtnInfoClicked(Contact contact) {
        listContactsInteractorImpl.saveInfoContact(contact);
        router.openInfoScreen();
    }

    public void onBtnCallClicked(Contact contact) {
        router.openCallScreen(contact);
    }

    public void onBtnDeleteClicked(Contact contact) {
        disposableDeleteContact = listContactsInteractorImpl.deleteContact(contact)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> getViewState().showSuccessMassage("Contact deleted"),
                        throwable -> getViewState().showErrorMassage(throwable.getMessage()));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposableUpdateListContacts != null && disposableUpdateListContacts.isDisposed())
            disposableUpdateListContacts.dispose();
        if (disposableDeleteContact != null && disposableDeleteContact.isDisposed())
            disposableDeleteContact.dispose();
    }
}
