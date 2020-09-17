package com.example.names.presentation.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import com.example.names.R;
import com.example.names.domain.entities.Contact;
import com.example.names.navigation.Router;
import com.example.names.presentation.presenters.InfoPresenter;

import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;

public class InfoActivity extends MvpAppCompatActivity implements InfoView {
    @InjectPresenter
    InfoPresenter presenter;
    private EditText etName, etCallNumber;
    private TextView tvNamePref, tvName, tvCallNumberPref, tvCallNumber;
    private Button btnOk, btnCancel;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setTitle("Info");
        RelativeLayout rootView = findViewById(R.id.rootView);
        rootView.setOnTouchListener((v, event) -> {
            presenter.onRootViewClicked();
            return false;
        });
        Router router = new Router(this);
        presenter.setRouter(router);
        tvNamePref = findViewById(R.id.tvNamePref);
        tvName = findViewById(R.id.tvName);
        tvCallNumberPref = findViewById(R.id.tvCallNumberPref);
        tvCallNumber = findViewById(R.id.tvCallNumber);
        etName = findViewById(R.id.etName);
        etCallNumber = findViewById(R.id.etCallNumber);
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
                    presenter.wrongLengthEditTextName();
                } else
                    etName.getBackground().mutate().setColorFilter(getResources()
                            .getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
            }
        });

        etCallNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() >= 16) {
                    etCallNumber.getBackground().mutate().setColorFilter(getResources()
                            .getColor(android.R.color.holo_red_light), PorterDuff.Mode.SRC_ATOP);
                    presenter.wrongLengthEditTextCallNumber();
                } else
                    etCallNumber.getBackground().mutate().setColorFilter(getResources()
                            .getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
            }
        });
        btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(v -> presenter.onBtnCancelClicked());
        btnOk = findViewById(R.id.btnOk);
        btnOk.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String callNumber = etCallNumber.getText().toString();
            presenter.onBtnOkClicked(name, callNumber);
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        presenter.onBackPressed();
    }

    @Override
    public void editTextNameClearFocus() {
        etName.clearFocus();
    }

    @Override
    public void editTextCallNumberClearFocus() {
        etCallNumber.clearFocus();
    }

    @Override
    public void showWarningMassage(String massage) {
        Toast.makeText(InfoActivity.this, massage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFinishActivityMassage(String massage) {
        Toast.makeText(getApplicationContext(), massage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideKeyboard() {
        final Activity activity = InfoActivity.this;
        final View view = activity.getWindow().getDecorView();
        final InputMethodManager imm = (InputMethodManager) activity
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        assert (imm != null);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void setContact(Contact contact) {
        etName.setText(contact.getName());
        etCallNumber.setText(contact.getCallNumber());
        tvName.setText(contact.getName());
        tvCallNumber.setText(contact.getCallNumber());
    }

    @Override
    public void showPanel(boolean isEditingMode) {
        if (isEditingMode) {
            tvNamePref.setVisibility(View.GONE);
            tvName.setVisibility(View.GONE);
            tvCallNumberPref.setVisibility(View.GONE);
            tvCallNumber.setVisibility(View.GONE);
            etName.setVisibility(View.VISIBLE);
            etCallNumber.setVisibility(View.VISIBLE);
            btnCancel.setVisibility(View.VISIBLE);
            btnOk.setVisibility(View.VISIBLE);
        } else {
            etName.setVisibility(View.GONE);
            etCallNumber.setVisibility(View.GONE);
            btnCancel.setVisibility(View.GONE);
            btnOk.setVisibility(View.GONE);
            tvNamePref.setVisibility(View.VISIBLE);
            tvName.setVisibility(View.VISIBLE);
            tvCallNumberPref.setVisibility(View.VISIBLE);
            tvCallNumber.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.info_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_call) {
            presenter.onBtnCallCLicked();
        } else if (id == R.id.action_edit) {
            presenter.onBtnEditClicked();
        } else if (id == R.id.action_delete) {
            presenter.onBtnDeleteClicked();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResumeView();
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
