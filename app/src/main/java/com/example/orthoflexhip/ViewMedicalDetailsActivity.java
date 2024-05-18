package com.example.orthoflexhip;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.orthoflexhip.api.ApiService;
import com.example.orthoflexhip.api.RetrofitClient;
import com.example.orthoflexhip.dataClass.PatientDetailData;
import com.example.orthoflexhip.dataClass.PatientProfileData;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewMedicalDetailsActivity extends AppCompatActivity {

    int patientId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.view_medical_details);
        ImageButton imageButton8 = findViewById(R.id.imageButton7);
        TextView hospitalId = findViewById(R.id.textView2);
        TextView username = findViewById(R.id.textView4);
        TextView password = findViewById(R.id.textView6);
        TextView name = findViewById(R.id.textView04);
        TextView mobile = findViewById(R.id.textView25);
        TextView age = findViewById(R.id.textView26);
        TextView gender = findViewById(R.id.textView27);
        TextView height = findViewById(R.id.textView28);
        TextView weight = findViewById(R.id.textView29);
        TextView problem = findViewById(R.id.textView210);
        TextView admittedDate = findViewById(R.id.textView211);
        TextView dischargeDate = findViewById(R.id.textView212);
        TextView score = findViewById(R.id.textView213);
        TextView condition = findViewById(R.id.textView214);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        patientId = sharedPreferences.getInt("patientId", -1);

        ApiService apiService = RetrofitClient.getInstance();
        apiService.patientMedicalDetail(patientId).enqueue(new Callback<PatientDetailData>() {
            @Override
            public void onResponse(Call<PatientDetailData> call, Response<PatientDetailData> response) {
                if (response.isSuccessful()) {
                    name.setText(response.body().getData().getName());
                    username.setText(response.body().getData().getUsername());
                    hospitalId.setText(response.body().getData().getHospitalID());
                    gender.setText(response.body().getData().getGender());
                    age.setText(response.body().getData().getAge());
                    mobile.setText(response.body().getData().getMobile());
                    password.setText(response.body().getData().getPassword());
                    height.setText(response.body().getData().getHeight());
                    weight.setText(response.body().getData().getWeight());
                    problem.setText(response.body().getData().getProblem());
                    admittedDate.setText(response.body().getData().getAdmittedDate());
                    dischargeDate.setText(response.body().getData().getDischargeDate());
                    score.setText(response.body().getData().getScore());
                    condition.setText(response.body().getData().getScoreResult());
                }
            }

            @Override
            public void onFailure(Call<PatientDetailData> call, Throwable t) {
                Toast.makeText(ViewMedicalDetailsActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        imageButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define your back button behavior here
                finish(); // Close the current activity
            }
        });
        Button button = findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewMedicalDetailsActivity.this, MedicationsPatientsActivity.class);
                startActivity(intent);
            }
        });
        Button button5 = findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewMedicalDetailsActivity.this, PatientViewXrayActivity.class);
                startActivity(intent);
            }
        });
        Button button6 = findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewMedicalDetailsActivity.this, PatientDischargeSummaryActivity.class);
                startActivity(intent);
            }
        });
    }
}