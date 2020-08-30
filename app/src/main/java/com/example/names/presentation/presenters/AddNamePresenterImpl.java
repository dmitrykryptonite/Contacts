package com.example.names.presentation.presenters;

import com.example.names.domain.AddNameInteractorImpl;
import com.example.names.presentation.view.AddNameView;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class AddNamePresenterImpl implements AddNamePresenter {
    private AddNameView addNameView;
    private AddNameInteractorImpl addNameInteractorImpl = new AddNameInteractorImpl();
    private Disposable disposableSaveName;

    public AddNamePresenterImpl(AddNameView addNameView) {
        this.addNameView = addNameView;
    }

    @Override
    public void valueEditTextIsEmpty() {
        addNameView.showMassageEditTextIsEmpty("The line must not be empty");
    }

    @Override
    public void onRootViewClicked() {
        addNameView.hideKeyboard();
        addNameView.editTextClearFocus();
    }

    @Override
    public void onBtnSubmitClicked(String name) {
        Completable saveName = addNameInteractorImpl.saveName(name);
        disposableSaveName = saveName.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action() {
                    @Override
                    public void run() {
                        addNameView.editTextClearFocus();
                        addNameView.setTextEditText("");
                        addNameView.hideKeyboard();
                        addNameView.showSuccessMassage("Name saved");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        addNameView.showErrorMassage(throwable.getMessage());
                    }
                });
    }

    @Override
    public void releasePresenter() {
        if (disposableSaveName != null && disposableSaveName.isDisposed())
            disposableSaveName.dispose();
        addNameView = null;
    }

}
