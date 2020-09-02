package com.example.names.presentation.presenters;

import com.example.names.domain.AddNameInteractorImpl;
import com.example.names.presentation.view.AddNameView;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class AddNamePresenter extends MvpPresenter<AddNameView> {
    private AddNameInteractorImpl addNameInteractorImpl = new AddNameInteractorImpl();
    private Disposable disposableSaveName;

    public void valueEditTextIsEmpty() {
        getViewState().showMassageEditTextIsEmpty("The line must not be empty");
    }

    public void onRootViewClicked() {
        getViewState().hideKeyboard();
        getViewState().editTextClearFocus();
    }

    public void onBtnSubmitClicked(String name) {
        Completable saveName = addNameInteractorImpl.saveName(name);
        disposableSaveName = saveName.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action() {
                    @Override
                    public void run() {
                        getViewState().editTextClearFocus();
                        getViewState().setTextEditText("");
                        getViewState().hideKeyboard();
                        getViewState().showSuccessMassage("Name saved");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        getViewState().showErrorMassage(throwable.getMessage());
                    }
                });
    }

    public void onEditTextFocusChanged(boolean hasFocused) {
        if (hasFocused)
            getViewState().showKeyboard();
        else
            getViewState().hideKeyboard();
    }

    public void releasePresenter() {
        if (disposableSaveName != null && disposableSaveName.isDisposed())
            disposableSaveName.dispose();
    }

}
