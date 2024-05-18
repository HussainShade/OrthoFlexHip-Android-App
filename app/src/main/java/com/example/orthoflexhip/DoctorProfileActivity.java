package com.example.orthoflexhip;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.orthoflexhip.api.ApiService;
import com.example.orthoflexhip.api.RetrofitClient;
import com.example.orthoflexhip.dataClass.DoctorProfileData;
import com.example.orthoflexhip.dataClass.DoctorProfileDetailsData;
import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorProfileActivity extends AppCompatActivity {

    int doctorId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.doctor_profile);
        ImageButton imageButton10 = findViewById(R.id.imageButton10);
        TextView name = findViewById(R.id.textView47);
        TextView username = findViewById(R.id.textView50);
        TextView gender = findViewById(R.id.textView53);
        TextView dob = findViewById(R.id.textView56);
        TextView age = findViewById(R.id.textView59);
        TextView mobile = findViewById(R.id.textView62);
        ShapeableImageView doctorImage = findViewById(R.id.imageView8);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        doctorId = sharedPreferences.getInt("doctorId", -1);

        imageButton10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorProfileActivity.this, DoctorHomepageActivity.class);
                startActivity(intent);
            }
        });

        ApiService apiService = RetrofitClient.getInstance();
        apiService.doctorProfile(doctorId).enqueue(new Callback<DoctorProfileData>() {
            @Override
            public void onResponse(Call<DoctorProfileData> call, Response<DoctorProfileData> response) {
                if (response.isSuccessful()) {
                    name.setText(response.body().getData().getName());
                    username.setText(response.body().getData().getUsername());
                    gender.setText(response.body().getData().getGender());
                    dob.setText(response.body().getData().getDob());
                    age.setText(response.body().getData().getAge());
                    mobile.setText(response.body().getData().getMobile());
                    Picasso.get().load(RetrofitClient.BASE_URL+response.body().getData().getProfilePhoto()).into(doctorImage);
                }
            }

            @Override
            public void onFailure(Call<DoctorProfileData> call, Throwable t) {
                Toast.makeText(DoctorProfileActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}