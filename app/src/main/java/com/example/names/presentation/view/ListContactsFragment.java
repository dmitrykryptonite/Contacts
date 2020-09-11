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
import com.example.names.domain.entities.Contact;
import com.example.names.navigation.Router;
import com.example.names.presentation.presenters.ListContactsPresenter;
import com.example.names.presentation.ui.adapters.ListContactsRecyclerViewAdapter;

import java.util.List;

import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;

public class ListContactsFragment extends MvpAppCompatFragment implements ListContactsView {
    @InjectPresenter
    ListContactsPresenter presenter;
    private ListContactsRecyclerViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_conatcs, container, false);
        RecyclerView rvListNames = view.findViewById(R.id.rvListNames);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rvListNames.setLayoutManager(llm);
        adapter = new ListContactsRecyclerViewAdapter(this);
        rvListNames.setAdapter(adapter);
        Router router = new Router(getActivity());
        presenter.setRouter(router);
        return view;
    }

    @Override
    public void updateContactsList(List<Contact> listContacts) {
        adapter.updateContactsList(listContacts);
    }

    @Override
    public void deleteContact(Contact contact) {
        presenter.onBtnDeleteClicked(contact);
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
    public void openInfoScreen(Contact contact) {
        presenter.onBtnInfoClicked(contact);
    }

    @Override
    public void openCallScreen(Contact contact) {
        presenter.onBtnCallClicked(contact);
    }
}
