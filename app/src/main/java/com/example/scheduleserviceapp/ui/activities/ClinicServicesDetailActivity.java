package com.example.scheduleserviceapp.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.scheduleserviceapp.R;
import com.example.scheduleserviceapp.adapters.ServiceAdapter;
import com.example.scheduleserviceapp.data.model.MedicalClinic;
import com.example.scheduleserviceapp.data.model.ServiceModel;
import com.example.scheduleserviceapp.interfaces.OnServiceClick;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ClinicServicesDetailActivity extends AppCompatActivity {

    private RecyclerView recyclerExplore;
    private ServiceAdapter adapter;
    private OnServiceClick listener;
    ArrayList<ServiceModel> services = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_services_detail);

        populateServiceList();

        final MedicalClinic medicalClinic = (MedicalClinic) getIntent().getSerializableExtra("MEDICAL_CLINIC");

        final ImageView imageView = findViewById(R.id.clinic_image_view);
        final ImageView backImageView = findViewById(R.id.back_arrow_acsd);
        final TextView clinicalName = findViewById(R.id.clinical_name_ac);
        final TextView clinicalAddress = findViewById(R.id.clinical_address_ac);
        final TextView clinicalPhoneNumber = findViewById(R.id.clinical_phone_number_ac);

        clinicalName.setText(medicalClinic.getName());
        clinicalAddress.setText(medicalClinic.getAddress());
        clinicalPhoneNumber.setText(medicalClinic.getPhoneNumber());
        Picasso.get().load(medicalClinic.getImageUrl()).into(imageView);

        listener = new OnServiceClick() {
            @Override
            public void onClick(final ServiceModel serviceModel, int position) {
                Log.d("ClinicServicesDetailActivity", "onClick()");
                final Intent intent = new Intent(getApplicationContext(), ServiceScheduleActivity.class);
                intent.putExtra("SERVICE_MODEL", serviceModel);
                startActivity(intent);
            }
        };

        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClinicServicesDetailActivity.this.finish();
            }
        });

        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                configureRecyclerView(getApplicationContext());
            }
        }, 500);
    }

    private void configureRecyclerView(final Context context) {
        recyclerExplore = findViewById(R.id.rvServices);
        recyclerExplore.setHasFixedSize(true);

        adapter = new ServiceAdapter(context, services, listener);

        LinearLayoutManager llm = new LinearLayoutManager(context);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerExplore.setLayoutManager(llm);
        recyclerExplore.addItemDecoration(new DividerItemDecoration(context, LinearLayout.VERTICAL));
        recyclerExplore.setAdapter(adapter);
    }

    void populateServiceList() {
        services.add(new ServiceModel(1, 1, "João", 300.0));
        services.add(new ServiceModel(2, 1, "Natália", 400.0));
        services.add(new ServiceModel(3, 1, "Pedro", 350.0));
    }

}