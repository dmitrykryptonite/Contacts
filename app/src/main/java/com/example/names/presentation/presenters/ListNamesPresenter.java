package com.example.names.presentation.presenters;

import com.example.names.domain.ListNamesInteractorImpl;
import com.example.names.domain.entities.Name;
import com.example.names.navigation.Router;
import com.example.names.presentation.view.ListNamesView;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class ListNamesPresenter extends MvpPresenter<ListNamesView> {
    private ListNamesInteractorImpl listNamesInteractorImpl = new ListNamesInteractorImpl();
    private Disposable disposableUpdateListNames, disposableDeleteItem;
    private Router router;

    public ListNamesPresenter() {
        Observable<List<Name>> namesUpdateListener = listNamesInteractorImpl.namesUpdateListener;
        disposableUpdateListNames = namesUpdateListener.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(names -> getViewState().updateNamesList(names));
    }

    public void onCreateView() {
        listNamesInteractorImpl.getListNames();
    }

    public void onBtnDeleteClicked(Name name) {
        Completable deleteItem = listNamesInteractorImpl.deleteItem(name);
        disposableDeleteItem = deleteItem.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> getViewState().showSuccessMassage("Name deleted"),
                        throwable -> getViewState().showErrorMassage(throwable.getMessage()));
    }

    public void releasePresenter() {
        if (disposableUpdateListNames != null && disposableUpdateListNames.isDisposed())
            disposableUpdateListNames.dispose();
        if (disposableDeleteItem != null && disposableDeleteItem.isDisposed())
            disposableDeleteItem.dispose();
    }

    public void setRouter(Router router) {
        this.router = router;
    }

    public void onBtnEditClicked(Name name) {
        listNamesInteractorImpl.saveEditName(name);
        router.openEditItemScreen();
    }
}
