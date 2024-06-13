package com.example.scheduleserviceapp.data.model;

public class ReservationOfDateAvailable {
    private final int id;
    private final int serviceId;
    private final String hour;

    public ReservationOfDateAvailable(int id, int serviceId, String hour) {
        this.id = id;
        this.serviceId = serviceId;
        this.hour = hour;
    }

    public int getId() {
        return id;
    }

    public int getServiceId() {
        return serviceId;
    }

    public String getHour() {
        return hour;
    }

    @Override
    public String toString() {
        return "ReservationOfDateAvailable{" +
                "id=" + id +
                ", serviceId=" + serviceId +
                ", hour='" + hour + '\'' +
                '}';
    }
}
