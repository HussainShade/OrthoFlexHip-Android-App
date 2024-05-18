package com.example.orthoflexhip;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.orthoflexhip.api.ApiService;
import com.example.orthoflexhip.api.RetrofitClient;
import com.example.orthoflexhip.dataClass.PatientProfileData;
import com.example.orthoflexhip.dataClass.PatientXrayData;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PatientViewXrayActivity extends AppCompatActivity {

    int patientId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.patient_view_xray);
        ImageButton imageButton8 = findViewById(R.id.imageButton8);
        ImageView preXrayImage = findViewById(R.id.imageView6);
        ImageView postXrayImage = findViewById(R.id.imageView7);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        patientId = sharedPreferences.getInt("patientId", -1);

        ApiService apiService = RetrofitClient.getInstance();
        apiService.patientXrayImage(patientId).enqueue(new Callback<PatientXrayData>() {
            @Override
            public void onResponse(Call<PatientXrayData> call, Response<PatientXrayData> response) {
                if (response.isSuccessful()) {
                    Picasso.get().load(RetrofitClient.BASE_URL+response.body().getData().getPreXrayImage()).into(preXrayImage);
                    Picasso.get().load(RetrofitClient.BASE_URL+response.body().getData().getPostXrayImage()).into(postXrayImage);
                }
            }

            @Override
            public void onFailure(Call<PatientXrayData> call, Throwable t) {
                Toast.makeText(PatientViewXrayActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        imageButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define your back button behavior here
                finish(); // Close the current activity
            }
        });
    }
}