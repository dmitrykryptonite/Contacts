package com.example.names.presentation.view;

import android.os.Bundle;

import com.example.names.R;
import com.example.names.presentation.presenters.EditorPresenter;

import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;

public class EditorActivity extends MvpAppCompatActivity implements EditorView {
    @InjectPresenter
    EditorPresenter editorPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
    }
}
