package com.example.scheduleserviceapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scheduleserviceapp.R;
import com.example.scheduleserviceapp.entities.ServiceModel;
import com.example.scheduleserviceapp.interfaces.OnServiceClick;
import com.example.scheduleserviceapp.viewholders.ServiceViewHolder;

import java.util.ArrayList;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceViewHolder> {

    private Context context;
    private ArrayList<ServiceModel> items;
    private OnServiceClick listener;

    public ServiceAdapter(final Context context, final ArrayList<ServiceModel> services, final OnServiceClick listener) {
        this.context = context;
        this.items = services;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int typeView) {
        final View view = LayoutInflater.from(context).inflate(R.layout.service_row, viewGroup, false);
        final ServiceViewHolder viewHolder = new ServiceViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder exploreViewHolder, int position) {
        final ServiceModel service = items.get(position);

        exploreViewHolder.scheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(service, exploreViewHolder.getAdapterPosition());
            }
        });

        exploreViewHolder.doctorName.setText(service.getDoctorName());
        exploreViewHolder.servicePrice.setText(service.getPriceParsed());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}