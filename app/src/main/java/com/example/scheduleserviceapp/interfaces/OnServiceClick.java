package com.example.scheduleserviceapp.interfaces;

import com.example.scheduleserviceapp.entities.ServiceModel;

public interface OnServiceClick {
    void onClick(final ServiceModel serviceModel, final int position);
}