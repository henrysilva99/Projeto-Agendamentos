package com.example.scheduleserviceapp.interfaces;

import com.example.scheduleserviceapp.data.model.MedicalClinic;

public interface OnPlaceClick {
    void onClick (final MedicalClinic medicalClinic, final int position);
}
