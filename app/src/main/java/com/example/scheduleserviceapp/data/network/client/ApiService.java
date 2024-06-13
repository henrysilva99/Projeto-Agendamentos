package com.example.scheduleserviceapp.data.network.client;

import com.example.scheduleserviceapp.data.network.client.model.AddressModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("api?results=5&nat=br&exc=picture,dob,login,timezone")
    Call<AddressModel> getAllAddress();
}
