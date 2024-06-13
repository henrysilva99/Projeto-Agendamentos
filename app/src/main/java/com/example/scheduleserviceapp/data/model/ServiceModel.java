package com.example.scheduleserviceapp.data.model;

import java.io.Serializable;

@SuppressWarnings("serial") //With this annotation we are going to hide compiler warnings
public class ServiceModel implements Serializable {
    private final int id;
    private final int clinicId;
    private final String doctorName;
    private final double price;
    private String date;
    private String time;
    private String status;

    public ServiceModel(final int id, final int clinicId, final String doctorName, final double price) {
        this.id = id;
        this.clinicId = clinicId;
        this.doctorName = doctorName;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public int getClinicId() {
        return clinicId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public double getPrice() {
        return price;
    }

    public String getPriceParsed() {
        return "R$ " + price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ServiceModel{" +
                "id='" + id + '\'' +
                ", clinicId=" + clinicId +
                ", doctorName=" + doctorName +
                ", price=" + price +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
