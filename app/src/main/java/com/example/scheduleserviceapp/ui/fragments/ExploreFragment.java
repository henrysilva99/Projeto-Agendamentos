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

import com.example.scheduleserviceapp.ui.activities.ClinicServicesDetailActivity;
import com.example.scheduleserviceapp.R;
import com.example.scheduleserviceapp.adapters.ExploreAdapter;
import com.example.scheduleserviceapp.entities.MedicalClinic;
import com.example.scheduleserviceapp.interfaces.OnPlaceClick;

import java.util.ArrayList;

public class ExploreFragment extends Fragment {
    private RecyclerView recyclerExplore;
    private ExploreAdapter adapter;
    private OnPlaceClick listener;

    public ExploreFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
                configureRecyclerView(inflater.getContext(), v);
            }
        }, 500);

        return v;
    }

    private void configureRecyclerView(final Context context, final View v) {
        ArrayList<MedicalClinic> clinics = new ArrayList<>();
        clinics.add(new MedicalClinic(1, "Clínica Vitta", "R. dos Pinheiros, 498 - Conj.81", "(11) 2309-4590", "https://drpauloribeiroortopedista.com.br/wp-content/uploads/2021/04/Vitta-01.jpeg"));
        clinics.add(new MedicalClinic(2, "Clínica Médica Nova Itaim", "R. Valente de Novais, 51 - Itaim Paulista, São Paulo", "(11) 3678-4949", "https://clinicanovaitaim.com.br/images/unidades-itaim.jpg"));
        clinics.add(new MedicalClinic(3, "Clínica Carvalho de Oliveira", "Av. Brasil, 1500 • Jardins - São Paulo", "(11) 0000-0000", "https://znarquitetos.com.br/projetos/16008_Interior-em-Clinica-Medica-03/img/ZN-16008-02-clinica-fachada-02.jpg"));
        clinics.add(new MedicalClinic(4, "Clínica Sua Consulta Goiânia", "R. C-244, 114 - Jardim América, Goiânia - GO", "(62) 99158-0217", "https://www.panoramago.com.br/images/noticias/165/8af942fe909bfced5991db81a7672beb.jpeg"));
        clinics.add(new MedicalClinic(5, "Clínica Doutor Pop", "Av. Imirim, 1864 - São Paulo, SP", "(011) 2208-2244", "https://static.wixstatic.com/media/26df46_72459b9266e2482aa56504d0daf1422d~mv2.jpg/v1/fit/w_2500,h_1330,al_c/26df46_72459b9266e2482aa56504d0daf1422d~mv2.jpg"));

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