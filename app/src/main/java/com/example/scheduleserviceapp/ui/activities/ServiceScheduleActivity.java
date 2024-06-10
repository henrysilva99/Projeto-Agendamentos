package com.example.scheduleserviceapp.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.scheduleserviceapp.R;
import com.example.scheduleserviceapp.entities.ReservationOfDateAvailable;
import com.example.scheduleserviceapp.entities.ServiceModel;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.Calendar;

public class ServiceScheduleActivity extends AppCompatActivity {

    ArrayList<ReservationOfDateAvailable> dateAvailable = new ArrayList<>();
    Button actionButton;
    TextView resultSelections;
    String selectedDate = "";
    ReservationOfDateAvailable selectedDateAvailable = new ReservationOfDateAvailable(0, 0, "99:99");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_schedule);

        populateReservationOfDateAvailable();

        final ServiceModel serviceModel = (ServiceModel) getIntent().getSerializableExtra("SERVICE_MODEL");

        final ImageView backImageView = findViewById(R.id.back_arrow_ass);
        final TextView doctorName = findViewById(R.id.doctor_name_reservation_tv);
        final TextView price = findViewById(R.id.price_reservation_tv);
        resultSelections = findViewById(R.id.result_selections_tv);
        final Button datePickerButton = findViewById(R.id.date_picker_button);
        actionButton = findViewById(R.id.confirm_reservation_button);

        actionButton.setEnabled(false);
        doctorName.setText(serviceModel.getDoctorName());
        price.setText(serviceModel.getPriceParsed());

        datePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        ServiceScheduleActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                                datePickerButton.setText(selectedDate);
                                checkActionButton();
                            }
                        },
                        year, month, day);
                datePickerDialog.show();
            }
        });

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Agendamento realizado!",
                        Toast.LENGTH_LONG).show();
                ServiceScheduleActivity.this.finish();
            }
        });

        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServiceScheduleActivity.this.finish();
            }
        });

        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                addChipToGroup();
            }
        }, 500);
    }

    private void checkActionButton() {
        if (!selectedDate.isEmpty() && !selectedDateAvailable.getHour().equals("99:99")) {
            actionButton.setEnabled(true);
            resultSelections.setVisibility(View.VISIBLE);
            resultSelections.setText("Agendado para " + selectedDate + " as " + selectedDateAvailable.getHour());
        }
    }

    private void addChipToGroup() {
        final ChipGroup chipGroup = findViewById(R.id.chip_group);

        for (int i = 0; i < dateAvailable.size(); i++) {
            Chip chip = new Chip(this);
            chip.setTag("chip" + i);
            chip.setText(dateAvailable.get(i).getHour());
            chip.setCloseIconVisible(false);
            chip.setClickable(true);
            chip.setCheckable(true);

            chip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    final String textTag = view.getTag().toString();
                    final String selectedIdString = textTag.replace("chip", "");
                    selectedDateAvailable = dateAvailable.get(Integer.parseInt(selectedIdString));
                    checkActionButton();
                }
            });

            chipGroup.addView(chip);
        }
    }

    void populateReservationOfDateAvailable() {
        dateAvailable.add(new ReservationOfDateAvailable(1, 1, "09:00"));
        dateAvailable.add(new ReservationOfDateAvailable(2, 1, "10:00"));
        dateAvailable.add(new ReservationOfDateAvailable(3, 1, "11:30"));
        dateAvailable.add(new ReservationOfDateAvailable(4, 1, "08:10"));
        dateAvailable.add(new ReservationOfDateAvailable(5, 1, "07:30"));
        dateAvailable.add(new ReservationOfDateAvailable(6, 1, "12:00"));
    }

}
