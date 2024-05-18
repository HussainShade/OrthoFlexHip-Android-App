package com.example.orthoflexhip;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.orthoflexhip.api.ApiService;
import com.example.orthoflexhip.api.RetrofitClient;
import com.example.orthoflexhip.dataClass.LoginData;

import retrofit2.Call;
import retrofit2.Callback;

public class PatientLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.patient_login);



        ImageButton imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientLoginActivity.this, SelectLoginActivity.class);
                startActivity(intent);
            }
        });
        Button button = findViewById(R.id.button3);
        EditText userNameET = findViewById(R.id.edtPatientId);
        ImageButton ivPasswordToggle = findViewById(R.id.ivPasswordToggle);
        EditText passwordET = findViewById(R.id.edtPatientPassword);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = userNameET.getText().toString();
                String password = passwordET.getText().toString();
                patientLogin(username,password);
            }
        });

        TextView textView4 = findViewById(R.id.textView4);
        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientLoginActivity.this, HelpActivity.class);
                startActivity(intent);
            }
        });

        ivPasswordToggle.setOnClickListener(new View.OnClickListener() {
            boolean isBlack = true;

            @Override
            public void onClick(View v) {
                if (isBlack) {
                    ivPasswordToggle.setImageResource(R.drawable.icons8_hide_30);
                    passwordET.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    ivPasswordToggle.setImageResource(R.drawable.icons8_eye_30);
                    passwordET.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                isBlack = !isBlack;
//                passwordET.setSelection(passwordET.getText().length());
            }
        });
    }

    private void patientLogin(String username, String password) {
        ApiService apiService = RetrofitClient.getInstance();
        apiService.patientLogin(username, password).enqueue(new Callback<LoginData>() {
            @Override
            public void onResponse(Call<LoginData> call, retrofit2.Response<LoginData> response) {
                if (response.isSuccessful()) {
                    if (response.body().getSuccess()) {
                        Intent intent = new Intent(PatientLoginActivity.this, PatientHompageActivity.class);
                        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        int patientId = Math.toIntExact(response.body().getID());
                        editor.putInt("patientId", patientId);
                        editor.apply();
                        startActivity(intent);
                    } else {
                        Toast.makeText(PatientLoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginData> call, Throwable t) {
                Log.e("error", t.toString());
                Toast.makeText(PatientLoginActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}