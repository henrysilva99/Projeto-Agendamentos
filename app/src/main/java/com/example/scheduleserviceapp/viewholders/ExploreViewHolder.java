package com.example.scheduleserviceapp.viewholders;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scheduleserviceapp.R;

public class ExploreViewHolder extends RecyclerView.ViewHolder {
    public TextView clinicalName;
    public TextView clinicalAddress;
    public TextView clinicalPhoneNumber;
    public LinearLayout exploreListItem;

    public ExploreViewHolder(@NonNull View itemView) {
        super(itemView);

        clinicalName = itemView.findViewById(R.id.clinical_name_rv);
        clinicalAddress = itemView.findViewById(R.id.clinical_address_rv);
        clinicalPhoneNumber = itemView.findViewById(R.id.clinical_phone_number_rv);
        exploreListItem = itemView.findViewById(R.id.explore_list_item);
    }
}
