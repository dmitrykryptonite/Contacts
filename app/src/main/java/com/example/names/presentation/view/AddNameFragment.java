package com.example.names.presentation.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.names.R;
import com.example.names.presentation.presenters.AddNamePresenter;
import com.example.names.presentation.presenters.AddNamePresenterImpl;

public class AddNameFragment extends Fragment implements AddNameView {
    private EditText etName;
    private AddNamePresenter presenter;
    private RelativeLayout rootView;

    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmet_add_name, container, false);
        presenter = new AddNamePresenterImpl(this);
        etName = view.findViewById(R.id.etName);
        etName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                presenter.onEditTextFocusChanged(hasFocus);
            }
        });
        Button btnSubmit = view.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                if (name.isEmpty())
                    presenter.valueEditTextIsEmpty();
                else
                    presenter.onBtnSubmitClicked(etName.getText().toString());
            }
        });
        rootView = view.findViewById(R.id.rootView);
        rootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                presenter.onRootViewClicked();
                return false;
            }
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
    public void editTextClearFocus() {
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
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
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
