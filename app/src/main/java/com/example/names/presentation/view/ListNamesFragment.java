package com.example.names.presentation.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.names.R;
import com.example.names.domain.entities.Name;
import com.example.names.navigation.Router;
import com.example.names.presentation.presenters.ListNamesPresenter;
import com.example.names.presentation.ui.adapters.ListNamesRecyclerViewAdapter;

import java.util.List;

import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;

public class ListNamesFragment extends MvpAppCompatFragment implements ListNamesView {
    @InjectPresenter
    ListNamesPresenter presenter;
    private ListNamesRecyclerViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_names, container, false);
        RecyclerView rvListNames = view.findViewById(R.id.rvListNames);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rvListNames.setLayoutManager(llm);
        adapter = new ListNamesRecyclerViewAdapter(this);
        rvListNames.setAdapter(adapter);
        presenter.onCreateView();
        Router router = new Router(getActivity());
        presenter.setRouter(router);
        return view;
    }

    @Override
    public void updateNamesList(List<Name> listNames) {
        adapter.updateNamesList(listNames);
    }

    @Override
    public void deleteName(Name name) {
        presenter.onBtnDeleteClicked(name);
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
    public void openEditItemScreen(Name name) {
        presenter.onBtnEditClicked(name);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResumeView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.releasePresenter();
        presenter = null;
    }
}
