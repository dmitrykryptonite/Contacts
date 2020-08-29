package com.example.names.presentation.presenters;

import com.example.names.domain.ListNamesInteractorImpl;
import com.example.names.domain.entities.Name;
import com.example.names.presentation.view.ListNamesView;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ListNamesPresenterImpl implements ListNamesPresenter {
    private ListNamesView listNamesView;
    private ListNamesInteractorImpl listNamesInteractorImpl = new ListNamesInteractorImpl();
    private Disposable disposableUpdateListNames, disposableDeleteItem;

    public ListNamesPresenterImpl(final ListNamesView listNamesView) {
        this.listNamesView = listNamesView;
        Observable<List<Name>> namesUpdateListener = listNamesInteractorImpl.namesUpdateListener;
        disposableUpdateListNames = namesUpdateListener.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Name>>() {
                    @Override
                    public void accept(List<Name> names) {
                        listNamesView.updateNamesList(names);
                    }
                });
    }

    @Override
    public void onViewCreated() {
        listNamesInteractorImpl.getListNames();
    }


    @Override
    public void onBtnDeleteClicked(Name name) {
        Completable deleteItem = listNamesInteractorImpl.deleteItem(name);
        disposableDeleteItem = deleteItem.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action() {
                    @Override
                    public void run() {
                        listNamesView.showSuccessMassage("Name deleted");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        listNamesView.showErrorMassage(throwable.getMessage());
                    }
                });
    }

    @Override
    public void releasePresenter() {
        if (disposableUpdateListNames != null && disposableUpdateListNames.isDisposed())
            disposableUpdateListNames.dispose();
        if (disposableDeleteItem != null && disposableDeleteItem.isDisposed())
            disposableDeleteItem.dispose();
        listNamesView = null;
    }
}
