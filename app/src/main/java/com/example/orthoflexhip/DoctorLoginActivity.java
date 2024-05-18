package com.example.orthoflexhip;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.orthoflexhip.api.ApiService;
import com.example.orthoflexhip.api.RetrofitClient;
import com.example.orthoflexhip.dataClass.LoginData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class DoctorLoginActivity extends AppCompatActivity {
    EditText passwordET;
    ImageButton ivPasswordToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_login);

        TextView textView4 = findViewById(R.id.textView4);
        ivPasswordToggle = findViewById(R.id.ivPasswordToggle);
        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorLoginActivity.this, HelpActivity.class);
                startActivity(intent);
            }
        });

        ImageButton imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorLoginActivity.this, SelectLoginActivity.class);
                startActivity(intent);
            }
        });

        Button button = findViewById(R.id.button3);
        EditText userNameET = findViewById(R.id.edtDocId);
        passwordET = findViewById(R.id.edtLoginPassword);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = userNameET.getText().toString();
                String password = passwordET.getText().toString();
                doctorLogin(username,password);
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

    private void doctorLogin(String username, String password) {
        ApiService apiService = RetrofitClient.getInstance();
        apiService.doctorLogin(username, password).enqueue(new Callback<LoginData>() {
            @Override
            public void onResponse(Call<LoginData> call, retrofit2.Response<LoginData> response) {
                if (response.isSuccessful()) {
                    if (response.body().getSuccess()) {
                        Intent intent = new Intent(DoctorLoginActivity.this, DoctorHomepageActivity.class);
                        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        int doctorId = Math.toIntExact(response.body().getID());
                        editor.putInt("doctorId", doctorId);
                        editor.apply();
                        startActivity(intent);
                    } else {
                        Toast.makeText(DoctorLoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginData> call, Throwable t) {
                Log.e("error", t.toString());
                Toast.makeText(DoctorLoginActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
