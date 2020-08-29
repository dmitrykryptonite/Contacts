package com.example.names.presentation.view;

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
import androidx.fragment.app.Fragment;

import com.example.names.R;
import com.example.names.presentation.presenters.AddNamePresenter;
import com.example.names.presentation.presenters.AddNamePresenterImpl;

public class AddNameFragment extends Fragment implements AddNameView {
    private EditText etName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmet_add_name, container, false);
        final AddNamePresenter presenter = new AddNamePresenterImpl(this);
        etName = view.findViewById(R.id.etName);
        Button btnSubmit = view.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onBtnSubmitClicked(etName.getText().toString());
            }
        });
        RelativeLayout rootView = view.findViewById(R.id.rootView);
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onRootViewClicked();
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
    public void editTextClearFocus() {
        etName.clearFocus();
    }

    @Override
    public void setTextEditText(String text) {
        etName.setText(text);
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
}
