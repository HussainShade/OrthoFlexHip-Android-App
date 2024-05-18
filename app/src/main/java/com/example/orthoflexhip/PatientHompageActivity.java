package com.example.orthoflexhip;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.orthoflexhip.api.ApiService;
import com.example.orthoflexhip.api.RetrofitClient;
import com.example.orthoflexhip.dataClass.PatientProfileData;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PatientHompageActivity extends AppCompatActivity {

    int patientId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.patient_hompage);
        DrawerLayout drawer = findViewById(R.id.main);
        NavigationView navigationView = findViewById(R.id.nav_view2);

        TextView patientName = findViewById(R.id.textView5);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        patientId = sharedPreferences.getInt("patientId", -1);

        ApiService apiService = RetrofitClient.getInstance();
        apiService.patientProfile(patientId).enqueue(new Callback<PatientProfileData>() {
            @Override
            public void onResponse(Call<PatientProfileData> call, Response<PatientProfileData> response) {
                if (response.isSuccessful()) {
                    patientName.setText("Hello! " + response.body().getData().getName());
                }
            }

            @Override
            public void onFailure(Call<PatientProfileData> call, Throwable t) {
                Toast.makeText(PatientHompageActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        View view4 = findViewById(R.id.view4);
        view4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientHompageActivity.this, ViewMedicalDetailsActivity.class);
                startActivity(intent);
            }
        });

        ImageButton imageButton11 = findViewById(R.id.imageButton11);
        imageButton11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientHompageActivity.this, FeedbackActivity.class);
                startActivity(intent);
            }
        });

        View view5 = findViewById(R.id.view5);
        view5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientHompageActivity.this, BookAnAppointmentActivity.class);
                startActivity(intent);
            }
        });

        View view6 = findViewById(R.id.view6);
        view6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientHompageActivity.this, TrackAppoinmentsActivity.class);
                startActivity(intent);
            }
        });

        View view7 = findViewById(R.id.view7);
        view7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientHompageActivity.this, ExerciseVideosPatinetsActivity.class);
                startActivity(intent);
            }
        });

        ImageButton imageButton3 = findViewById(R.id.imageButton3);
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientHompageActivity.this, ViewMedicalDetailsActivity.class);
                startActivity(intent);
            }
        });

        ImageButton imageButton4 = findViewById(R.id.imageButton4);
        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientHompageActivity.this, BookAnAppointmentActivity.class);
                startActivity(intent);
            }
        });

        ImageButton imageButton5 = findViewById(R.id.imageButton5);
        imageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientHompageActivity.this, TrackAppoinmentsActivity.class);
                startActivity(intent);
            }
        });

        ImageButton imageButton6 = findViewById(R.id.imageButton6);
        imageButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientHompageActivity.this, ExerciseVideosPatinetsActivity.class);
                startActivity(intent);
            }
        });


        ImageButton imageButton2 = findViewById(R.id.imageButton2);
        if (imageButton2 != null) {
            imageButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (drawer != null) {
                        drawer.openDrawer(GravityCompat.START);
                    } else {
                        Log.e("PatientHomepageActivity", "DrawerLayout is null");
                    }
                }
            });
        } else {
            Log.e("PatientHomepageActivity", "ImageButton12 is null");
        }
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    // Handle navigation view item clicks here
                    int id = item.getItemId();

                    if (id == R.id.nav_profile) {
                        // Handle button 1 click
                        Intent intent = new Intent(PatientHompageActivity.this, PatientProfileActivity.class);
                        startActivity(intent);
                    } else if (id == R.id.nav_logout) {
                        // Handle button 2 click
                        Intent intent = new Intent(PatientHompageActivity.this, SelectLoginActivity.class);
                        startActivity(intent);
                    }

                    drawer.closeDrawer(GravityCompat.START);
                    return true;
                }
            });
        }
    }
}
