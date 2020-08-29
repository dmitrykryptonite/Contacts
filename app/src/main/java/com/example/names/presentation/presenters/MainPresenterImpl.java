package com.example.names.presentation.presenters;

import com.example.names.domain.MainInteractorImpl;
import com.example.names.presentation.view.MainView;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenterImpl implements MainPresenter {
    private MainView mainView;
    private MainInteractorImpl mainInteractorImpl = new MainInteractorImpl();
    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
    }
    @Override
    public void onBtnDeleteClicked() {
        Completable deleteAllNames = mainInteractorImpl.deleteAllNames();
        deleteAllNames.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        mainView.showSuccessMassage("All names deleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        mainView.showErrorMassage(e.getMessage());
                    }
                });
    }
}
