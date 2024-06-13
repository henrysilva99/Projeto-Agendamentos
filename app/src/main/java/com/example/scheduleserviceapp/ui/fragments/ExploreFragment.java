package com.example.scheduleserviceapp.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.scheduleserviceapp.data.network.client.ApiService;
import com.example.scheduleserviceapp.data.network.client.RetrofitClient;
import com.example.scheduleserviceapp.data.network.client.model.AddressModel;
import com.example.scheduleserviceapp.ui.activities.ClinicServicesDetailActivity;
import com.example.scheduleserviceapp.R;
import com.example.scheduleserviceapp.adapters.ExploreAdapter;
import com.example.scheduleserviceapp.data.model.MedicalClinic;
import com.example.scheduleserviceapp.interfaces.OnPlaceClick;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExploreFragment extends Fragment {
    private RecyclerView recyclerExplore;
    private ExploreAdapter adapter;
    private OnPlaceClick listener;

    final List<String> clinicNames = Arrays.asList("Clínica Vitta", "Clínica Médica Nova Itaim", "Clínica Carvalho de Oliveira", "Clínica Sua Consulta Goiânia", "Clínica Doutor Pop");
    final List<String> clinicUrlImages = Arrays.asList("https://drpauloribeiroortopedista.com.br/wp-content/uploads/2021/04/Vitta-01.jpeg", "https://clinicanovaitaim.com.br/images/unidades-itaim.jpg", "https://znarquitetos.com.br/projetos/16008_Interior-em-Clinica-Medica-03/img/ZN-16008-02-clinica-fachada-02.jpg", "https://www.panoramago.com.br/images/noticias/165/8af942fe909bfced5991db81a7672beb.jpeg", "https://static.wixstatic.com/media/26df46_72459b9266e2482aa56504d0daf1422d~mv2.jpg/v1/fit/w_2500,h_1330,al_c/26df46_72459b9266e2482aa56504d0daf1422d~mv2.jpg");

    int iterator = 0;

    public ExploreFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        listener = new OnPlaceClick() {
            @Override
            public void onClick(final MedicalClinic medicalClinic, int position) {
                final Intent intent = new Intent(inflater.getContext(), ClinicServicesDetailActivity.class);
                intent.putExtra("MEDICAL_CLINIC", medicalClinic);
                startActivity(intent);
            }
        };

        final View v = inflater.inflate(R.layout.fragment_explore, container, false);

        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getAddressesData(inflater.getContext(), v);
            }
        }, 500);

        return v;
    }

    void getAddressesData(final Context context, final View v) {
        ArrayList<MedicalClinic> clinics = new ArrayList<>();
        iterator = 0;

        ApiService methods = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<AddressModel> call = methods.getAllAddress();
        call.enqueue(new Callback<AddressModel>() {
            @Override
            public void onResponse(Call<AddressModel> call, Response<AddressModel> response) {

                for (AddressModel.ResultsModel obj : response.body().getResults()) {
                    clinics.add(new MedicalClinic(iterator + 1, clinicNames.get(iterator), obj.getLocation().getStreet().getName() + ", " + obj.getLocation().getStreet().getNumber() + " • " + obj.getLocation().getCity() + " - " + obj.getLocation().getState(), obj.getPhone(), clinicUrlImages.get(iterator)));
                    iterator++;
                }

                configureRecyclerView(context, v, clinics);

            }

            @Override
            public void onFailure(Call<AddressModel> call, Throwable t) {
                Toast.makeText(context, "An error has occured", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void configureRecyclerView(final Context context, final View v, final ArrayList<MedicalClinic> clinics) {
        recyclerExplore = v.findViewById(R.id.rvExplore);
        recyclerExplore.setHasFixedSize(true);

        adapter = new ExploreAdapter(context, clinics, listener);

        final LinearLayoutManager llm = new LinearLayoutManager(context);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerExplore.setLayoutManager(llm);
        recyclerExplore.addItemDecoration(new DividerItemDecoration(context, LinearLayout.VERTICAL));
        recyclerExplore.setAdapter(adapter);
    }


}