package com.example.names.presentation.presenters;

import com.example.names.domain.MainInteractorImpl;
import com.example.names.presentation.view.MainView;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainPresenterImpl implements MainPresenter {
    private MainView mainView;
    private MainInteractorImpl mainInteractorImpl = new MainInteractorImpl();
    private Disposable disposableDeleteAllNames;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void onBtnDeleteClicked() {
        Completable deleteAllNames = mainInteractorImpl.deleteAllNames();
        disposableDeleteAllNames = deleteAllNames.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action() {
                    @Override
                    public void run() {
                        mainView.showSuccessMassage("All names deleted");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        mainView.showErrorMassage(throwable.getMessage());
                    }
                });
    }

    @Override
    public void releasePresenter() {
        if (disposableDeleteAllNames != null && disposableDeleteAllNames.isDisposed())
            disposableDeleteAllNames.dispose();
        mainView = null;
    }
}
