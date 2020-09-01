package com.example.names.presentation.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.names.R;
import com.example.names.domain.entities.Name;
import com.example.names.presentation.presenters.ListNamesPresenter;
import com.example.names.presentation.presenters.ListNamesPresenterImpl;
import com.example.names.presentation.ui.adapters.ListNamesRecyclerViewAdapter;

import java.util.List;

public class ListNamesFragment extends Fragment implements ListNamesView {
    private ListNamesPresenter presenter;
    private ListNamesRecyclerViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_names, container, false);
        presenter = new ListNamesPresenterImpl(this);
        RecyclerView rvListNames = view.findViewById(R.id.rvListNames);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rvListNames.setLayoutManager(llm);
        adapter = new ListNamesRecyclerViewAdapter(this);
        rvListNames.setAdapter(adapter);
        presenter.onCreateView();
        return view;
    }

    @Override
    public void updateNamesList(List<Name> listNames) {
        adapter.updateNamesList(listNames);
    }

    @Override
    public void deleteItem(Name name) {
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
    public void onDestroy() {
        super.onDestroy();
        presenter.releasePresenter();
        presenter = null;
    }
}
