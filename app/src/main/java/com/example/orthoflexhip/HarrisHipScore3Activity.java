package com.example.orthoflexhip;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.orthoflexhip.api.RetrofitClient;
import com.example.orthoflexhip.apiresponse.Constant;
import com.example.orthoflexhip.apiresponse.ScoreCalculationResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HarrisHipScore3Activity extends AppCompatActivity {

    Spinner spinner9,spinner10,spinner11,spinner12;
    ArrayAdapter<CharSequence> adapter9,adapter10,adapter11,adapter12;
    String sSpinner9,sSpinner10,sSpinner11,sSpinner12;
    float intSpinner9, intSpinner10, intSpinner11, intSpinner12;
    TextView score,result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.harris_hip_score3);

        ImageButton imageButton8 = findViewById(R.id.imageButton8);
        Button calculateButton = findViewById(R.id.button15);
        spinners();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        SharedPreferences sf=getSharedPreferences(Constant.SF_NAME,MODE_PRIVATE);
        String id=sf.getString(Constant.VIEW_PATIENT_ID_FOR_MEDICAL_DETAILS,null);
        int pain=sf.getInt(Constant.SPINNER,0);
        int distance_walked=sf.getInt(Constant.SPINNER1,0);
        int activities=sf.getInt(Constant.SPINNER2,0);
        int public_transportation=sf.getInt(Constant.SPINNER3,0);
        int support=sf.getInt(Constant.SPINNER4,0);
        int limp=sf.getInt(Constant.SPINNER5,0);
        int sitting=sf.getInt(Constant.SPINNER6,0);
        int stairs=sf.getInt(Constant.SPINNER7,0);
        Intent intent=getIntent();
        int section2=intent.getIntExtra(Constant.SPINNER,0);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSpinnerValue();
                intSpinner9=assignValueForSpinner9(spinner9.getSelectedItemPosition());
                intSpinner10=assignValueForSpinner10(spinner10.getSelectedItemPosition());
                intSpinner11=assignValueForSpinner11(spinner11.getSelectedItemPosition());
                intSpinner12=assignValueForSpinner12(spinner12.getSelectedItemPosition());
                apiCall(id,pain,distance_walked,activities,public_transportation,support,limp,stairs,sitting,section2,intSpinner9,
                        intSpinner10,intSpinner11,intSpinner12);
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
    private void apiCall(String id,int pain,int distance_walked,int activities,int public_transportation
            ,int support,int limp,int stairs,int sitting,int section_2,float total_degree_of_flexion
            ,float total_degree_of_abduction,float total_degree_of_ext_rotation,
                         float total_degree_of_adduction){


        Call<ScoreCalculationResponse> responseCall= RetrofitClient.getInstance()
                .scoreCalculation(id, pain, distance_walked, activities, public_transportation,
                        support, limp, stairs, sitting, section_2, total_degree_of_flexion,
                        total_degree_of_abduction, total_degree_of_ext_rotation, total_degree_of_adduction);
        responseCall.enqueue(new Callback<ScoreCalculationResponse>() {
            @Override
            public void onResponse(Call<ScoreCalculationResponse> call, Response<ScoreCalculationResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().isSuccess()){
                        TextView score=findViewById(R.id.textView82123);
                        TextView result=findViewById(R.id.textView83123);
                        score.setText(""+response.body().getScore());
                        result.setText(response.body().getScore_result());

                        Toast.makeText(HarrisHipScore3Activity.this,response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(HarrisHipScore3Activity.this,response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ScoreCalculationResponse> call, Throwable t) {
                Log.e("TAG",t.getMessage());
                Toast.makeText(HarrisHipScore3Activity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private float assignValueForSpinner9(int i){
        if(0==i){
            return 0;
        } else if (1==i) {
            return 0.4f;
        } else if (2==i) {
            return 0.8f;
        }else if (3==i) {
            return 1.2f;
        }else if (4==i) {
            return  1.6f;
        }else if (5==i) {
            return 2;
        }else if(6==i){
            return 2.25f;
        }else if (7==i) {
            return 2.55f;
        }else if (8==i) {
            return 2.85f;
        }else if (9==i) {
            return 3;
        }else if (10==i) {
            return  3.15f;
        }else if (11==i) {
            return 3.3f;
        }else if (12==i) {
            return 3.6f;
        }else if (13==i) {
            return 3.75f;
        }else if (14==i) {
            return  3.9f;
        }
        return 100;
    }

    private float assignValueForSpinner10(int i){
        if(0==i){
            return 0;
        } else if (1==i) {
            return 0.2f;
        } else if (2==i) {
            return 0.4f;
        }else if (3==i) {
            return 0.6f;
        }else if (4==i) {
            return  0.65f;
        }
        return 100;
    }
    private float assignValueForSpinner11(int i){
        if(0==i){
            return 0;
        } else if (1==i) {
            return 0.1f;
        } else if (2==i) {
            return 0.2f;
        }else if (3==i) {
            return 0.3f;
        }
        return 100;
    }

    private float assignValueForSpinner12(int i){
        if(0==i){
            return 0;
        } else if (1==i) {
            return 0.05f;
        } else if (2==i) {
            return 0.1f;
        }else if (3==i) {
            return 0.15f;
        }
        return 100;
    }

    private void spinners() {
        spinner9 = findViewById(R.id.spinner);
        adapter9 = ArrayAdapter.createFromResource(this, R.array.Total_degrees_of_Flexion, android.R.layout.simple_spinner_item);
        adapter9.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner9.setAdapter(adapter9);

        spinner10 = findViewById(R.id.spinner1);
        adapter10 = ArrayAdapter.createFromResource(this, R.array.Total_degrees_of_Abduction, android.R.layout.simple_spinner_item);
        adapter10.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner10.setAdapter(adapter10);

        spinner11 = findViewById(R.id.spinner2);
        adapter11 = ArrayAdapter.createFromResource(this, R.array.Total_degrees_of_Ext_Rotation, android.R.layout.simple_spinner_item);
        adapter11.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner11.setAdapter(adapter11);

        spinner12 = findViewById(R.id.spinner3);
        adapter12 = ArrayAdapter.createFromResource(this, R.array.Total_degrees_of_Adduction, android.R.layout.simple_spinner_item);
        adapter12.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner12.setAdapter(adapter12);
    }
    private void getSpinnerValue(){
        sSpinner9=spinner9.getSelectedItem().toString();
        sSpinner10=spinner10.getSelectedItem().toString();
        sSpinner11=spinner11.getSelectedItem().toString();
        sSpinner12=spinner12.getSelectedItem().toString();
    }
}