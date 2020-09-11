package com.example.names.presentation.presenters;

import com.example.names.domain.MainInteractorImpl;
import com.example.names.presentation.view.MainView;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {
    private MainInteractorImpl mainInteractorImpl = new MainInteractorImpl();
    private Disposable disposableDeleteAllContacts;

    public void onBtnDeleteClicked() {
        Completable deleteAllContacts = mainInteractorImpl.deleteAllContacts();
        disposableDeleteAllContacts = deleteAllContacts.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> getViewState().showSuccessMassage("All contacts deleted"),
                        throwable -> getViewState().showErrorMassage(throwable.getMessage()));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposableDeleteAllContacts != null && disposableDeleteAllContacts.isDisposed())
            disposableDeleteAllContacts.dispose();
    }
}
