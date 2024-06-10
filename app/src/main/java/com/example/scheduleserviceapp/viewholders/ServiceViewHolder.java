package com.example.scheduleserviceapp.viewholders;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scheduleserviceapp.R;

public class ServiceViewHolder extends RecyclerView.ViewHolder {
    public TextView doctorName;
    public TextView servicePrice;
    public Button scheduleButton;

    public ServiceViewHolder(@NonNull View itemView) {
        super(itemView);

        doctorName = itemView.findViewById(R.id.doctor_name_rv);
        servicePrice = itemView.findViewById(R.id.service_price_rv);
        scheduleButton = itemView.findViewById(R.id.schedule_button);
    }
}
