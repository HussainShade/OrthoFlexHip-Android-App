package com.example.orthoflexhip;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.orthoflexhip.api.ApiService;
import com.example.orthoflexhip.api.RetrofitClient;
import com.example.orthoflexhip.apiresponse.AddMedicationResponse;
import com.example.orthoflexhip.dataClass.AddPatientData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedbackActivity extends AppCompatActivity {

    EditText feedbackET;

    int id;

    Button addFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.feedback);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ImageButton imageButton8 = findViewById(R.id.imageButton8);
        feedbackET = findViewById(R.id.editTextText);
        addFeedback = findViewById(R.id.button21);
        imageButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define your back button behavior here
                finish(); // Close the current activity
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        id = sharedPreferences.getInt("patientId", -1);

        addFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String feedback = feedbackET.getText().toString();
                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                int id = sharedPreferences.getInt("patientId", 0);
                if (feedback.isEmpty()) {
                    Toast.makeText(FeedbackActivity.this, "Please fill the empty fields", Toast.LENGTH_SHORT).show();
                } else {
                    addFeedback(String.valueOf(id),feedback);
                }
            }
        });

    }
    private void addFeedback(String id, String feedback) {
        ApiService apiService = RetrofitClient.getInstance();
        apiService.addFeedback(id, feedback).enqueue(new Callback<AddMedicationResponse>() {
            @Override
            public void onResponse(Call<AddMedicationResponse> call, Response<AddMedicationResponse> response) {
                if (response.body().getStatus()) {
                    Toast.makeText(FeedbackActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(FeedbackActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddMedicationResponse> call, Throwable t) {
                Toast.makeText(FeedbackActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}