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
import com.example.orthoflexhip.apiresponse.Constant;
import com.example.orthoflexhip.dataClass.PatientMedicationData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MedicationsActivity extends AppCompatActivity {

    TextView antibiotics,analgesics,antacids,antiEdemaDrugs,tromboprophylaxis,supportiveDrugs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.medications);
        valueAssign();
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String patientId = sharedPreferences.getString(Constant.VIEW_PATIENT_ID_FOR_MEDICAL_DETAILS, null);
        apiCall(Integer.parseInt(patientId));
        ImageButton imageButton8 = findViewById(R.id.imageButton8);
        imageButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define your back button behavior here
                finish(); // Close the current activity
            }
        });
    }
    private void apiCall(int id){
        ApiService apiService = RetrofitClient.getInstance();
        apiService.patientViewMedication(id).enqueue(new Callback<PatientMedicationData>() {
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
                        Toast.makeText(MedicationsActivity.this,"No Medications Added Yet",Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<PatientMedicationData> call, Throwable t) {
                Toast.makeText(MedicationsActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void valueAssign(){
         antibiotics = findViewById(R.id.textView20);
         analgesics = findViewById(R.id.textView202);
         antacids = findViewById(R.id.textView203);
         antiEdemaDrugs = findViewById(R.id.textView2003);
         tromboprophylaxis = findViewById(R.id.textView2004);
         supportiveDrugs = findViewById(R.id.textView204);
    }
}