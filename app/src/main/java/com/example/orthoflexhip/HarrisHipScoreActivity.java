package com.example.orthoflexhip;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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

import com.example.orthoflexhip.apiresponse.Constant;

public class HarrisHipScoreActivity extends AppCompatActivity {
    Spinner spinner,spinner1,spinner2,spinner3,spinner4,spinner5,spinner6,spinner7;
    ArrayAdapter<CharSequence> adapter,adapter1,adapter2,adapter3,adapter4,adapter5,adapter6,adapter7;
    String sSpinner,sSpinner1,sSpinner2,sSpinner3,sSpinner4,sSpinner5,sSpinner6,sSpinner7;
    int intSpinner, intSpinner1, intSpinner2, intSpinner3, intSpinner4, intSpinner5, intSpinner6, intSpinner7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.harris_hip_score);
        Button button = findViewById(R.id.button12);

        spinners();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intSpinner=assignValueForSpinner(spinner.getSelectedItemPosition());
                intSpinner1=assignValueForSpinner1(spinner1.getSelectedItemPosition());
                intSpinner2=assignValueForSpinner2(spinner2.getSelectedItemPosition());
                intSpinner3=assignValueForSpinner3(spinner3.getSelectedItemPosition());
                intSpinner4=assignValueForSpinner4(spinner4.getSelectedItemPosition());
                intSpinner5=assignValueForSpinner5(spinner5.getSelectedItemPosition());
                intSpinner6=assignValueForSpinner6(spinner6.getSelectedItemPosition());
                intSpinner7=assignValueForSpinner7(spinner7.getSelectedItemPosition());
                Log.e("HarrisHipScoreActivity",""+intSpinner+" "+intSpinner1);
                getSpinnerValue();
                SharedPreferences sf=getSharedPreferences(Constant.SF_NAME,MODE_PRIVATE);
                SharedPreferences.Editor editor=sf.edit();
                editor.putInt(Constant.SPINNER,intSpinner);
                editor.putInt(Constant.SPINNER1,intSpinner1);
                editor.putInt(Constant.SPINNER2,intSpinner2);
                editor.putInt(Constant.SPINNER3,intSpinner3);
                editor.putInt(Constant.SPINNER4,intSpinner4);
                editor.putInt(Constant.SPINNER5,intSpinner5);
                editor.putInt(Constant.SPINNER6,intSpinner6);
                editor.putInt(Constant.SPINNER7,intSpinner7);
                editor.apply();
                Intent intent = new Intent(HarrisHipScoreActivity.this, HarrisHipScore2Activity.class);
                startActivity(intent);

            }
        });
        ImageButton imageButton8 = findViewById(R.id.imageButton8);
        imageButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define your back button behavior here
                finish(); // Close the current activity
            }
        });

    }
    private int assignValueForSpinner(int i){
        if(0==i){
            return 44;
        } else if (1==i) {
            return 40;
        } else if (2==i) {
            return 30;
        }else if (3==i) {
            return 20;
        }else if (4==i) {
            return  10;
        }else if (5==i) {
            return 0;
        }
        return 100;
    }

    private int assignValueForSpinner1(int i){
        if(0==i){
            return 11;
        } else if (1==i) {
            return 8;
        } else if (2==i) {
            return 5;
        }else if (3==i) {
            return 2;
        }else if (4==i) {
            return  0;
        }
        return 100;
    }

    private int assignValueForSpinner2(int i){
        if(0==i){
            return 4;
        } else if (1==i) {
            return 2;
        } else if (2==i) {
            return 0;
        }
        return 100;
    }

    private int assignValueForSpinner3(int i){
        if(0==i){
            return 1;
        } else if (1==i) {
            return 0;
        }
        return 100;
    }

    private int assignValueForSpinner4(int i){
        if(0==i){
            return 11;
        } else if (1==i) {
            return 7;
        } else if (2==i) {
            return 5;
        }else if (3==i) {
            return 3;
        }else if (4==i) {
            return  2;
        }else if (5==i) {
            return  0;
        }
        return 100;
    }

    private int assignValueForSpinner5(int i){
        if(0==i){
            return 11;
        } else if (1==i) {
            return 8;
        } else if (2==i) {
            return 5;
        }else if (3==i) {
            return  0;
        }
        return 100;
    }

    private int assignValueForSpinner6(int i){
        if(0==i){
            return 5;
        } else if (1==i) {
            return 3;
        } else if (2==i) {
            return 0;
        }
        return 100;
    }

    private int assignValueForSpinner7(int i){
        if(0==i){
            return 4;
        } else if (1==i) {
            return 2;
        } else if (2==i) {
            return 1;
        }else if (3==i) {
            return 0;
        }
        return 100;
    }
    private void spinners(){
        spinner = findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.Pain , android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner1 = findViewById(R.id.spinner1);
        adapter1 = ArrayAdapter.createFromResource(this, R.array.Distance_walked, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        spinner2 = findViewById(R.id.spinner2);
        adapter2 = ArrayAdapter.createFromResource(this, R.array.Activities_shoes_socks, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        spinner3 = findViewById(R.id.spinner3);
        adapter3 = ArrayAdapter.createFromResource(this, R.array.Public_transportation, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);

        spinner4 = findViewById(R.id.spinner4);
        adapter4 = ArrayAdapter.createFromResource(this, R.array.Support, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter4);

        spinner5 = findViewById(R.id.spinner5);
        adapter5 = ArrayAdapter.createFromResource(this, R.array.Limp, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner5.setAdapter(adapter5);

        spinner6 = findViewById(R.id.spinner6);
        adapter6 = ArrayAdapter.createFromResource(this, R.array.Sitting, android.R.layout.simple_spinner_item);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner6.setAdapter(adapter6);

        spinner7 = findViewById(R.id.spinner7);
        adapter7 = ArrayAdapter.createFromResource(this, R.array.stairs, android.R.layout.simple_spinner_item);
        adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner7.setAdapter(adapter7);
    }
    private void getSpinnerValue(){
        sSpinner=spinner.getSelectedItem().toString();
        sSpinner1=spinner1.getSelectedItem().toString();
        sSpinner2=spinner2.getSelectedItem().toString();
        sSpinner3=spinner3.getSelectedItem().toString();
        sSpinner4=spinner4.getSelectedItem().toString();
        sSpinner5=spinner5.getSelectedItem().toString();
        sSpinner6=spinner6.getSelectedItem().toString();
        sSpinner7=spinner7.getSelectedItem().toString();
    }
}