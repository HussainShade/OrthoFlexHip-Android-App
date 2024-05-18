package com.example.orthoflexhip;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
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
import com.example.orthoflexhip.dataClass.PatientMedicationData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MedicationsPatientsActivity extends AppCompatActivity {

    int patientId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.medications_patients);
        ImageButton imageButton8 = findViewById(R.id.imageButton8);
        TextView antibiotics = findViewById(R.id.textView20);
        TextView analgesics = findViewById(R.id.textView202);
        TextView antacids = findViewById(R.id.textView203);
        TextView antiEdemaDrugs = findViewById(R.id.textView2003);
        TextView tromboprophylaxis = findViewById(R.id.textView2004);
        TextView supportiveDrugs = findViewById(R.id.textView204);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        patientId = sharedPreferences.getInt("patientId", -1);

        ApiService apiService = RetrofitClient.getInstance();
        apiService.patientViewMedication(patientId).enqueue(new Callback<PatientMedicationData>() {
            @Override
            public void onResponse(Call<PatientMedicationData> call, Response<PatientMedicationData> response) {
                if (response.isSuccessful()) {
                    if (response.body().getSuccess()) {
                        antibiotics.setText(response.body().getData().getAntibiotics());
                        analgesics.setText(response.body().getData().getAnalgesics());
                        antacids.setText(response.body().getData().getAntacids());
                        antiEdemaDrugs.setText(response.body().getData().getAntiEdemaDrugs());
                        tromboprophylaxis.setText(response.body().getData().getTromboprophylaxis());
                        supportiveDrugs.setText(response.body().getData().getSupportiveDrugs());
                    } else {
                        Toast.makeText(MedicationsPatientsActivity.this,"No Medications Added Yet",Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<PatientMedicationData> call, Throwable t) {
                Toast.makeText(MedicationsPatientsActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
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