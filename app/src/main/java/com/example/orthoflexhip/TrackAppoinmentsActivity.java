package com.example.orthoflexhip;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orthoflexhip.adapters.TrackAppointmentsAdapter;
import com.example.orthoflexhip.api.ApiService;
import com.example.orthoflexhip.api.RetrofitClient;
import com.example.orthoflexhip.dataClass.TrackAppointmentData;
import com.example.orthoflexhip.dataClass.TrackAppointmentRecyclerData;
import android.content.SharedPreferences;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrackAppoinmentsActivity extends AppCompatActivity {

    RecyclerView trackAppointmentRV;
    int patientId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.track_appoinments);
        trackAppointmentRV = findViewById(R.id.RecyclerView);
        ImageButton imageButton8 = findViewById(R.id.imageButton8);

        // Retrieve patientId from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        patientId = sharedPreferences.getInt("patientId", -1);

        imageButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define your back button behavior here
                finish(); // Close the current activity
            }
        });

        trackAppointmentRV.setLayoutManager(new LinearLayoutManager(this));

        ApiService apiService = RetrofitClient.getInstance();
        apiService.trackAppointment(patientId).enqueue(new Callback<TrackAppointmentData>() {
            @Override
            public void onResponse(Call<TrackAppointmentData> call, Response<TrackAppointmentData> response) {
                TrackAppointmentsAdapter adapter = null;
                List<TrackAppointmentRecyclerData> dataArray = response.body().getData();
                ArrayList<TrackAppointmentRecyclerData> dataList = new ArrayList<>(dataArray);
                adapter = new TrackAppointmentsAdapter(dataList);
                trackAppointmentRV.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<TrackAppointmentData> call, Throwable t) {
                Toast.makeText(TrackAppoinmentsActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

//    private void trackAppointment() {
//        trackAppointmentRV.setLayoutManager(new LinearLayoutManager(requireContext()));
//
//        ApiService apiService = RetrofitClient.getInstance();
//        apiService.trackAppointment(1).enqueue(new Callback<TrackAppointmentData>() {
//            @Override
//            public void onResponse(Call<TrackAppointmentData> call, Response<TrackAppointmentData> response) {
//                TrackAppointmentsAdapter adapter = null;
//                List<TrackAppointmentRecyclerData> dataArray = response.body().getData();
//                ArrayList<TrackAppointmentRecyclerData> dataList = new ArrayList<>(dataArray);
//                adapter = new TrackAppointmentsAdapter(dataList);
//                trackAppointmentRV.setAdapter(adapter);
//            }
//
//            @Override
//            public void onFailure(Call<TrackAppointmentData> call, Throwable t) {
//                Toast.makeText(TrackAppoinmentsActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}