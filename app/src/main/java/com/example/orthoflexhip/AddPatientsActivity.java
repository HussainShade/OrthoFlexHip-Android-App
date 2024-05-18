package com.example.orthoflexhip;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
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
import com.example.orthoflexhip.dataClass.AddPatientData;
import com.example.orthoflexhip.dataClass.LoginData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPatientsActivity extends AppCompatActivity {

    static final int PICK_FILE_REQUEST_CODE = 1001;
    EditText hospitalIdET,loginIdET, passwordET, nameET, mobileET, ageET, genderET, heightET, weightET, problemET, admittedDateET, dischargeDateET;
    Button addPatient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_patients);
        ImageButton imageButton8 = findViewById(R.id.imageButton8);
        hospitalIdET = findViewById(R.id.EdittextView2);
        loginIdET = findViewById(R.id.EdittextView3);
        passwordET = findViewById(R.id.EdittextView4);
        nameET = findViewById(R.id.EdittextView5);
        mobileET = findViewById(R.id.EdittextView6);
        ageET = findViewById(R.id.EdittextView7);
        genderET = findViewById(R.id.EdittextView8);
        heightET = findViewById(R.id.EdittextView9);
        weightET = findViewById(R.id.EdittextView10);
        problemET = findViewById(R.id.EdittextView11);
        admittedDateET = findViewById(R.id.EdittextView12);
        dischargeDateET = findViewById(R.id.EdittextView13);
        addPatient = findViewById(R.id.button4);
        imageButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddPatientsActivity.this, BlankFragment.class);
                startActivity(intent);
            }
        });

        Button button9 = findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddPatientsActivity.this, BlankFragment.class);
                startActivity(intent);
            }
        });

        addPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = loginIdET.getText().toString();
                String password = passwordET.getText().toString();
                String name = nameET.getText().toString();
                String age = ageET.getText().toString();
                String gender = genderET.getText().toString();
                String height = heightET.getText().toString();
                String weight = weightET.getText().toString();
                String problem = problemET.getText().toString();
                String admittedDate = admittedDateET.getText().toString();
                String dischargeDate = dischargeDateET.getText().toString();
                String hospitalId = hospitalIdET.getText().toString();
                String mobile = mobileET.getText().toString();
                if (userName.isEmpty() ||  password.isEmpty() ||  name.isEmpty() ||  age.isEmpty() ||  gender.isEmpty() ||  height.isEmpty() ||  weight.isEmpty() ||  problem.isEmpty() ||  admittedDate.isEmpty() ||  dischargeDate.isEmpty() ||  hospitalId.isEmpty() ||  mobile.isEmpty()) {
                    Toast.makeText(AddPatientsActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    addPatientDetails(userName,password,name,age,gender,height,weight,problem,admittedDate,dischargeDate,hospitalId,mobile);
                }
            }
        });
    }

    private void openStorage() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.parse("file:///");
        intent.setDataAndType(uri, "*/*");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(Intent.createChooser(intent, "Open folder"));
    }

    private void openFileManager() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*"); // Set MIME type to filter files
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(Intent.createChooser(intent, "Select a File"), PICK_FILE_REQUEST_CODE);
        } catch (ActivityNotFoundException ex) {
            Toast.makeText(this, "Please install a File Manager.", Toast.LENGTH_SHORT).show();
        }
    }

    private void addPatientDetails(String userName, String password, String name, String age, String gender, String height, String weight, String problem, String admittedDate, String dischargeDate, String hospitalId, String mobile) {
        ApiService apiService = RetrofitClient.getInstance();
        apiService.addPatientDetails(userName,password,name,age,gender,height,weight,problem,admittedDate,dischargeDate,hospitalId,mobile).enqueue(new Callback<AddPatientData>() {
            @Override
            public void onResponse(Call<AddPatientData> call, Response<AddPatientData> response) {
                if (response.body().getStatus()) {
                        Toast.makeText(AddPatientsActivity.this, response.body().getData(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddPatientsActivity.this, response.body().getData(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddPatientData> call, Throwable t) {
                Toast.makeText(AddPatientsActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
