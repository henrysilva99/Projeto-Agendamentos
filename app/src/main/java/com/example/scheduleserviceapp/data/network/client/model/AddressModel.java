package com.example.scheduleserviceapp.data.network.client.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddressModel {
    @SerializedName("results")
    public List<ResultsModel> results = null;

    public AddressModel(List<ResultsModel> results) {
        this.results = results;
    }

    public List<ResultsModel> getResults() {
        return results;
    }

    @Override
    public String toString() {
        return "AddressModel{" + "results=" + results + '}';
    }

    public class ResultsModel {
        @SerializedName("phone")
        private final String phone;
        @SerializedName("location")
        private final LocationModel location;

        public ResultsModel(String phone, LocationModel location) {
            this.phone = phone;
            this.location = location;
        }

        public String getPhone() {
            return phone;
        }

        public LocationModel getLocation() {
            return location;
        }

        @Override
        public String toString() {
            return "ResultsModel{" + "phone='" + phone + '\'' + ", location=" + location + '}';
        }
    }

    public class LocationModel {
        @SerializedName("city")
        private final String city;
        @SerializedName("state")
        private final String state;
        @SerializedName("street")
        private final StreetModel street;

        public LocationModel(String city, String state, StreetModel street) {
            this.city = city;
            this.state = state;
            this.street = street;
        }

        public String getCity() {
            return city;
        }

        public String getState() {
            return state;
        }

        public StreetModel getStreet() {
            return street;
        }

        @Override
        public String toString() {
            return "LocationModel{" + "city='" + city + '\'' + ", state='" + state + '\'' + ", street=" + street + '}';
        }
    }

    public class StreetModel {
        @SerializedName("number")
        private final String number;
        @SerializedName("name")
        private final String name;

        public StreetModel(String number, String name) {
            this.number = number;
            this.name = name;
        }

        public String getNumber() {
            return number;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "StreetModel{" + "number='" + number + '\'' + ", name='" + name + '\'' + '}';
        }
    }
}





