package com.example.names.presentation.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.names.R;
import com.example.names.domain.entities.Name;
import com.example.names.presentation.presenters.EditorPresenter;

import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;

public class EditorActivity extends MvpAppCompatActivity implements EditorView {
    @InjectPresenter
    EditorPresenter presenter;
    private EditText etName;
    private RelativeLayout rootView;
    private Name name;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        etName = findViewById(R.id.etName);
        presenter.onCreateView();
        etName.setOnFocusChangeListener((v, hasFocus) -> presenter.onEditTextFocusChanged(hasFocus));
        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() >= 41) {
                    etName.getBackground().mutate().setColorFilter(getResources()
                            .getColor(android.R.color.holo_red_light), PorterDuff.Mode.SRC_ATOP);
                    presenter.wrongLengthEditText();
                }
                else
                    etName.getBackground().mutate().setColorFilter(getResources()
                            .getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
            }
        });
        ImageView imgSubmit = findViewById(R.id.imgSubmit);
        imgSubmit.setOnClickListener(v -> {
            String name = etName.getText().toString();
            presenter.onImgSubmitClicked(this.name.getId(), name);
        });
        ImageView imgCancel = findViewById(R.id.imgCancel);
        imgCancel.setOnClickListener(v -> presenter.onImgCancelClicked());
        rootView = findViewById(R.id.rootView);
        rootView.setOnTouchListener((v, event) -> {
            presenter.onRootViewClicked();
            return false;
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        presenter.onBackPressed();
    }

    @Override
    public void showWarningMassage(String massage) {
        Toast.makeText(EditorActivity.this, massage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFinishActivityMassage(String massage) {
        Toast.makeText(getApplicationContext(), massage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void rootViewIsFocused() {
        rootView.requestFocus();
    }

    @Override
    public void showKeyboard() {
        final Activity activity = EditorActivity.this;
        final InputMethodManager imm = (InputMethodManager) activity
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        assert (imm != null);
        imm.showSoftInput(etName, InputMethodManager.SHOW_FORCED);
    }

    @Override
    public void hideKeyboard() {
        final Activity activity = EditorActivity.this;
        final View view = activity.getWindow().getDecorView();
        final InputMethodManager imm = (InputMethodManager) activity
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        assert (imm != null);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void setName(Name name) {
        this.name = name;
        etName.setText(name.getName());
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPauseView();
    }

    @Override
    public void finishActivity() {
        finish();
    }
}
