package com.example.contacts.presentation.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
        holder.imgDeleteContact.setOnClickListener(v -> fragment.deleteContact(contact));
        holder.imgInfoContact.setOnClickListener(v -> fragment.openInfoScreen(contact));
        holder.imgCallContact.setOnClickListener(v -> fragment.openCallScreen(contact));
    }

    @Override
    public int getItemCount() {
        return namesList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNameContact;
        ImageView imgCallContact, imgInfoContact, imgDeleteContact;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameContact = itemView.findViewById(R.id.tvNameContact);
            imgCallContact = itemView.findViewById(R.id.imgCallContact);
            imgInfoContact = itemView.findViewById(R.id.imgInfoContact);
            imgDeleteContact = itemView.findViewById(R.id.imgDeleteContact);
        }
    }
}
