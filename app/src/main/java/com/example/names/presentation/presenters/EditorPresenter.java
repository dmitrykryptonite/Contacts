package com.example.names.presentation.presenters;

import com.example.names.domain.EditorInteractorImpl;
import com.example.names.presentation.view.EditorView;

import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class EditorPresenter extends MvpPresenter<EditorView> {
    private EditorInteractorImpl editorInteractorImpl = new EditorInteractorImpl();
}
