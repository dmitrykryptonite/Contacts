package com.example.names.presentation.presenters;

import com.example.names.domain.ListContactsInteractorImpl;
import com.example.names.domain.entities.Contact;
import com.example.names.navigation.Router;
import com.example.names.presentation.view.ListContactsView;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class ListContactsPresenter extends MvpPresenter<ListContactsView> {
    private ListContactsInteractorImpl listNamesInteractorImpl = new ListContactsInteractorImpl();
    private Disposable disposableUpdateListContacts, disposableDeleteContact;
    private Router router;

    public ListContactsPresenter() {
        Observable<List<Contact>> namesUpdateListener = listNamesInteractorImpl.namesUpdateListener;
        disposableUpdateListContacts = namesUpdateListener.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(names -> getViewState().updateContactsList(names));
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        listNamesInteractorImpl.getListContacts();
    }

    public void onBtnDeleteClicked(Contact contact) {
        Completable deleteContact = listNamesInteractorImpl.deleteContact(contact);
        disposableDeleteContact = deleteContact.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> getViewState().showSuccessMassage("Contact deleted"),
                        throwable -> getViewState().showErrorMassage(throwable.getMessage()));
    }

    public void setRouter(Router router) {
        this.router = router;
    }

    public void onBtnInfoClicked(Contact contact) {
        listNamesInteractorImpl.saveInfoContact(contact);
        router.openInfoScreen();
    }

    public void onBtnCallClicked(Contact contact) {
        router.openCallScreen(contact);
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
