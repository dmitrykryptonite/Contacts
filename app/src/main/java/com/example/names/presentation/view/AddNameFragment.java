package com.example.names.presentation.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.names.R;
import com.example.names.presentation.presenters.AddNamePresenter;

import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;

public class AddNameFragment extends MvpAppCompatFragment implements AddNameView {
    private EditText etName;
    @InjectPresenter
    AddNamePresenter presenter;
    private RelativeLayout rootView;

    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmet_add_name, container, false);
        etName = view.findViewById(R.id.etName);
        etName.setOnFocusChangeListener((v, hasFocus) -> presenter.onEditTextFocusChanged(hasFocus));
        Button btnSubmit = view.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(v -> {
            String name = etName.getText().toString();
            if (name.isEmpty())
                presenter.valueEditTextIsEmpty();
            else
                presenter.onBtnSubmitClicked(etName.getText().toString());
        });
        rootView = view.findViewById(R.id.rootView);
        rootView.setOnTouchListener((v, event) -> {
            presenter.onRootViewClicked();
            return false;
        });
        return view;
    }

    @Override
    public void showSuccessMassage(String massage) {
        Toast.makeText(getContext(), massage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorMassage(String massage) {
        Toast.makeText(getContext(), massage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMassageEditTextIsEmpty(String massage) {
        Toast.makeText(getContext(), massage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void rootViewIsFocused() {
        rootView.requestFocus();
    }

    @Override
    public void setTextEditText(String text) {
        etName.setText(text);
    }

    @Override
    public void showKeyboard() {
        final Activity activity = getActivity();
        assert activity != null;
        final InputMethodManager imm = (InputMethodManager) activity
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        assert (imm != null);
        imm.showSoftInput(etName, InputMethodManager.SHOW_FORCED);
    }

    @Override
    public void hideKeyboard() {
        final Activity activity = getActivity();
        assert activity != null;
        final View view = activity.getWindow().getDecorView();
        final InputMethodManager imm = (InputMethodManager) activity
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        assert (imm != null);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void onDestroy() {
        presenter.releasePresenter();
        presenter = null;
        super.onDestroy();
    }
}
