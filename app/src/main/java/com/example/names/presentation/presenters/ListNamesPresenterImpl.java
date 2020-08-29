package com.example.names.presentation.presenters;

import com.example.names.domain.ListNamesInteractorImpl;
import com.example.names.domain.entities.Name;
import com.example.names.presentation.view.ListNamesView;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ListNamesPresenterImpl implements ListNamesPresenter {
    private ListNamesView listNamesView;
    private ListNamesInteractorImpl listNamesInteractorImpl = new ListNamesInteractorImpl();

    public ListNamesPresenterImpl(final ListNamesView listNamesView) {
        this.listNamesView = listNamesView;
        Observable<List<Name>> namesUpdateListener = listNamesInteractorImpl.namesUpdateListener;
        namesUpdateListener.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Name>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Name> names) {
                        listNamesView.updateNamesList(names);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

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
        deleteItem.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        listNamesView.showSuccessMassage("Name deleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        listNamesView.showErrorMassage(e.getMessage());
                    }
                });
    }
}
