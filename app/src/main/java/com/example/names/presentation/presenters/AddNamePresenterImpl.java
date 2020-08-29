package com.example.names.presentation.presenters;

import com.example.names.domain.AddNameInteractorImpl;
import com.example.names.presentation.view.AddNameView;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddNamePresenterImpl implements AddNamePresenter {
    private AddNameView addNameView;
    private AddNameInteractorImpl addNameInteractorImpl = new AddNameInteractorImpl();
    public AddNamePresenterImpl(AddNameView addNameView) {
        this.addNameView = addNameView;
    }
    @Override
    public void onBtnSubmitClicked(String name) {
        Completable saveName = addNameInteractorImpl.saveName(name);
        saveName.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        addNameView.editTextClearFocus();
                        addNameView.setTextEditText("");
                        addNameView.hideKeyboard();
                        addNameView.showSuccessMassage("Name saved");
                    }

                    @Override
                    public void onError(Throwable e) {
                        addNameView.showErrorMassage(e.getMessage());
                    }
                });
    }

    @Override
    public void onRootViewClicked() {
        addNameView.hideKeyboard();
        addNameView.editTextClearFocus();
    }
}
