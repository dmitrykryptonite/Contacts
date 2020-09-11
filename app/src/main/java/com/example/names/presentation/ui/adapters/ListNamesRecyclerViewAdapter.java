package com.example.names.presentation.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.names.R;
import com.example.names.domain.entities.Name;
import com.example.names.presentation.view.ListNamesFragment;

import java.util.ArrayList;
import java.util.List;

public class ListNamesRecyclerViewAdapter extends RecyclerView.Adapter<ListNamesRecyclerViewAdapter.ViewHolder> {
    private List<Name> namesList = new ArrayList<>();
    private ListNamesFragment fragment;

    public ListNamesRecyclerViewAdapter(ListNamesFragment fragment) {
        this.fragment = fragment;
    }

    public void updateNamesList(List<Name> namesList) {
        this.namesList = namesList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListNamesRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rv_names, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListNamesRecyclerViewAdapter.ViewHolder holder, int position) {
        final Name name = namesList.get(position);
        holder.tvNameContact.setText(name.getName());
        holder.tvNameContact.setTextIsSelectable(false);
        holder.tvNameContact.measure(-1, -1);
        holder.tvNameContact.setTextIsSelectable(true);
        holder.imgDeleteContact.setOnClickListener(v -> fragment.deleteName(name));
        holder.imgInfoContact.setOnClickListener(v -> fragment.openEditItemScreen(name));
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
