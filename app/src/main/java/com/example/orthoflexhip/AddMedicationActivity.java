package com.example.orthoflexhip;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.orthoflexhip.api.RetrofitClient;
import com.example.orthoflexhip.apiresponse.AddMedicationResponse;
import com.example.orthoflexhip.apiresponse.ApprovedStatusResponse;
import com.example.orthoflexhip.apiresponse.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMedicationActivity extends AppCompatActivity {

    Spinner spinner,spinner1,spinner2,spinner3,spinner4,spinner5;
    String sString,s1String,s2String,s3String,s4String,s5String;
    ArrayAdapter<CharSequence> adapter,adapter1,adapter2,adapter3,adapter4,adapter5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.add_medication);
        ImageButton imageButton8 = findViewById(R.id.imageButton8);
        imageButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddMedicationActivity.this, BlankFragment.class);
                startActivity(intent);
            }
        });

        Button button16 = findViewById(R.id.button16);
        button16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if(sString.isEmpty()&&s1String.isEmpty()&&s2String.isEmpty()&&s3String.isEmpty()
                &&s4String.isEmpty()&&s5String.isEmpty())  {
                  Toast.makeText(AddMedicationActivity.this, "select all fields", Toast.LENGTH_SHORT).show();
              }else{
                  SharedPreferences sf=getSharedPreferences(Constant.SF_NAME,MODE_PRIVATE);
                  String id=sf.getString(Constant.VIEW_PATIENT_ID_FOR_MEDICAL_DETAILS,null);
                  apiCall(id,sString,s1String,s2String,s3String,s4String,s5String);
              }
              
            }
        });
         spinner = findViewById(R.id.spinner);
         adapter = ArrayAdapter.createFromResource(this, R.array.Antibiotics, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

         spinner1 = findViewById(R.id.spinner1);
         adapter1 = ArrayAdapter.createFromResource(this, R.array.Analgesics, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

         spinner2 = findViewById(R.id.spinner2);
         adapter2 = ArrayAdapter.createFromResource(this, R.array.Antacids, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

         spinner3 = findViewById(R.id.spinner3);
         adapter3 = ArrayAdapter.createFromResource(this, R.array.Anti_edema_drugs, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);

         spinner4 = findViewById(R.id.spinner4);
         adapter4 = ArrayAdapter.createFromResource(this, R.array.Supportive_drugs, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter4);

         spinner5 = findViewById(R.id.spinner5);
         adapter5 = ArrayAdapter.createFromResource(this, R.array.physiotheraphy, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner5.setAdapter(adapter5);
        spinnerSelectedItem();

    }
    private void apiCall(String id,String s,String s1,String s2,String s3,String s4,String s5)
    {
        Call<AddMedicationResponse> responseCall= RetrofitClient.getInstance().addPatientMedication(
                id,s,s1,s2,s3,s4,s5
        );
        responseCall.enqueue(new Callback<AddMedicationResponse>() {
            @Override
            public void onResponse(Call<AddMedicationResponse> call, Response<AddMedicationResponse> response) {
                if (response.isSuccessful()){
                    if(response.body().getStatus()){
                        Toast.makeText(AddMedicationActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(AddMedicationActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        
                    }
                }
            }

            @Override
            public void onFailure(Call<AddMedicationResponse> call, Throwable t) {
                Toast.makeText(AddMedicationActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void spinnerSelectedItem()
    {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sString=adapter.getItem(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s1String=adapter1.getItem(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s2String=adapter2.getItem(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s3String=adapter3.getItem(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s4String=adapter4.getItem(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s5String=adapter5.getItem(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}