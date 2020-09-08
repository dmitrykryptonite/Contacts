package com.example.names.presentation.presenters;

import com.example.names.domain.EditorInteractorImpl;
import com.example.names.domain.entities.Name;
import com.example.names.presentation.view.EditorView;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class EditorPresenter extends MvpPresenter<EditorView> {
    private EditorInteractorImpl editorInteractorImpl = new EditorInteractorImpl();
    private Disposable disposableSetEditName, disposableEditName;

    public void onCreateActivity() {
        setEditName();
    }

    public void wrongLengthEditText() {
        getViewState().showWarningMassage("Sorry, length name must be 1-40 symbols");
    }

    public void onRootViewClicked() {
        getViewState().rootViewIsFocused();
        getViewState().hideKeyboard();
    }

    public void onEditTextFocusChanged(boolean hasFocus) {
        if (hasFocus)
            getViewState().showKeyboard();
        else {
            getViewState().rootViewIsFocused();
            getViewState().hideKeyboard();
        }
    }

    public void onImgCancelClicked() {
        getViewState().rootViewIsFocused();
        getViewState().hideKeyboard();
        getViewState().finishActivity();
        getViewState().showFinishActivityMassage("Name has not changed");
    }

    public void onImgSubmitClicked(int id, String name) {
        name = name.replaceAll("'", "''");
        if (name.isEmpty())
            getViewState().showWarningMassage("The line must not be empty");
        else if (name.length() >= 41)
            getViewState().showWarningMassage("Sorry, length name must be 1-40 symbols");
        else {
            Completable editName = editorInteractorImpl.editName(id, name);
            disposableEditName = editName.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> {
                        getViewState().rootViewIsFocused();
                        getViewState().hideKeyboard();
                        getViewState().finishActivity();
                        getViewState().showFinishActivityMassage("Name changed");
                    });
        }
    }

    public void onBackPressed() {
        getViewState().rootViewIsFocused();
        getViewState().hideKeyboard();
        getViewState().showFinishActivityMassage("Name has not changed");
    }

    public void onPauseActivity() {
        getViewState().rootViewIsFocused();
        getViewState().hideKeyboard();
    }

    private void setEditName() {
        Single<Name> getEditName = editorInteractorImpl.getEditName();
        disposableSetEditName = getEditName.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(name -> getViewState().setName(name));
    }

    public void releasePresenter() {
        if (disposableSetEditName != null && disposableSetEditName.isDisposed())
            disposableSetEditName.dispose();
        if (disposableEditName != null && disposableEditName.isDisposed())
            disposableEditName.dispose();
    }
}
