package com.example.scheduleserviceapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scheduleserviceapp.R;
import com.example.scheduleserviceapp.entities.MedicalClinic;
import com.example.scheduleserviceapp.interfaces.OnPlaceClick;
import com.example.scheduleserviceapp.viewholders.ExploreViewHolder;

import java.util.ArrayList;

public class ExploreAdapter extends RecyclerView.Adapter<ExploreViewHolder> {

    private Context context;
    private ArrayList<MedicalClinic> items;
    private OnPlaceClick listener;

    public ExploreAdapter(final Context context, final ArrayList<MedicalClinic> clinics, final OnPlaceClick listener) {
        this.context = context;
        this.items = clinics;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ExploreViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int typeView) {
        final View view = LayoutInflater.from(context).inflate(R.layout.explore_row, viewGroup, false);
        final ExploreViewHolder viewHolder = new ExploreViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExploreViewHolder exploreViewHolder, int position) {
        final MedicalClinic clinic = items.get(position);

        exploreViewHolder.exploreListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(clinic, exploreViewHolder.getAdapterPosition());
            }
        });

        exploreViewHolder.clinicalName.setText(clinic.getName());
        exploreViewHolder.clinicalAddress.setText(clinic.getAddress());
        exploreViewHolder.clinicalPhoneNumber.setText(clinic.getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}