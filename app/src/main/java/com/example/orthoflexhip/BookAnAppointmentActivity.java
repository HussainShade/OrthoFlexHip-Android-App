package com.example.orthoflexhip;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.orthoflexhip.api.ApiService;
import com.example.orthoflexhip.api.RetrofitClient;
import com.example.orthoflexhip.dataClass.BookAppointmentData;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookAnAppointmentActivity extends AppCompatActivity {

    int patientId;
    private TextView dateTextView;
    private Spinner spinner;
    private String startTime;
    private String date;
    private final int doctorId = 1;
    String reason;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_an_appoinment);

        ImageButton backButton = findViewById(R.id.imageButton8);
        Button bookBtn = findViewById(R.id.button8);
        EditText reasonET = findViewById(R.id.reasonET);
         reason = reasonET.getText().toString();
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        patientId = sharedPreferences.getInt("patientId", -1);

        dateTextView = findViewById(R.id.textView95);
        dateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        View editTextDate = findViewById(R.id.viewDate);
//        editTextDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showDatePickerDialog();
//            }
//        });

        bookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date = dateTextView.getText().toString();
                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                int patientId = sharedPreferences.getInt("patientId", 0);
                reason = reasonET.getText().toString();
                if (date.isEmpty() || startTime.isEmpty() || reason.isEmpty()) {
                    Toast.makeText(BookAnAppointmentActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    bookAppointment(doctorId,patientId,date,startTime,reason);
                }
            }
        });

        spinner = findViewById(R.id.spinner8);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.dropdown_items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Extract the start time from the selected item
                String selectedTiming = parent.getItemAtPosition(position).toString();
                String[] timingParts = selectedTiming.split(" - ");
                startTime = timingParts[0];
                System.out.println(startTime);
                // Extract only the time part without AM/PM
                String[] timeParts = startTime.split(":");
                String time = timeParts[0] + ":" + timeParts[1]; // Concatenate hours and minutes

// If AM/PM is present, remove it
                if (timeParts.length > 1) {
                    String[] hoursAndAmPm = timeParts[1].split("[AaPp][Mm]");
                    time += hoursAndAmPm[0];
                }
                System.out.println(time);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar selectedDate = Calendar.getInstance();
                        selectedDate.set(year, monthOfYear, dayOfMonth);

                        if (selectedDate.before(Calendar.getInstance())) {
                            // Selected date is before current date
                            // Show a message or handle the case as needed
                            // For example, show a Toast message
                            Toast.makeText(BookAnAppointmentActivity.this, "Please select a future date", Toast.LENGTH_SHORT).show();
                        } else {
                            // Update the dateTextView with the selected date
                            dateTextView.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                            date = dateTextView.getText().toString();
                        }
                    }
                }, year, month, day);

        // Set the minimum date to the current date to disable past dates
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }

    private void bookAppointment(int doctorId, int patientId, String date, String startTime, String reason) {
        ApiService apiService = RetrofitClient.getInstance();
        apiService.bookAppointment(patientId,doctorId,date,startTime,reason).enqueue(new Callback<BookAppointmentData>() {
            @Override
            public void onResponse(Call<BookAppointmentData> call, Response<BookAppointmentData> response) {
                if (response.body().getSuccess()) {
                    Toast.makeText(BookAnAppointmentActivity.this, response.body().getData(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(BookAnAppointmentActivity.this, response.body().getData(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BookAppointmentData> call, Throwable t) {
                Toast.makeText(BookAnAppointmentActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
