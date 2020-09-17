package com.example.contacts.presentation.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contacts.R;
import com.example.contacts.domain.entities.Contact;
import com.example.contacts.presentation.view.ListContactsFragment;

import java.util.ArrayList;
import java.util.List;

public class ListContactsRecyclerViewAdapter extends RecyclerView.Adapter<ListContactsRecyclerViewAdapter.ViewHolder> {
    private List<Contact> namesList = new ArrayList<>();
    private ListContactsFragment fragment;

    public ListContactsRecyclerViewAdapter(ListContactsFragment fragment) {
        this.fragment = fragment;
    }

    public void updateContactsList(List<Contact> namesList) {
        this.namesList = namesList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListContactsRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rv_contacts, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListContactsRecyclerViewAdapter.ViewHolder holder, int position) {
        final Contact contact = namesList.get(position);
        holder.tvNameContact.setText(contact.getName());
        holder.btnCallContact.setOnClickListener(v -> fragment.openCallScreen(contact));
        holder.btnInfoContact.setOnClickListener(v -> fragment.openInfoScreen(contact));
        holder.btnDeleteContact.setOnClickListener(v -> fragment.deleteContact(contact));
    }

    @Override
    public int getItemCount() {
        return namesList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNameContact;
        ImageButton btnCallContact, btnInfoContact, btnDeleteContact;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameContact = itemView.findViewById(R.id.tvNameContact);
            btnCallContact = itemView.findViewById(R.id.btnCallContact);
            btnInfoContact = itemView.findViewById(R.id.btnInfoContact);
            btnDeleteContact = itemView.findViewById(R.id.btnDeleteContact);
        }
    }
}
