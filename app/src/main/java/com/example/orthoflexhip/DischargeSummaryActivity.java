package com.example.orthoflexhip;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import com.github.barteksc.pdfviewer.PDFView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.example.orthoflexhip.api.ApiService;
import com.example.orthoflexhip.api.RetrofitClient;
import com.example.orthoflexhip.apiresponse.Constant;
import com.example.orthoflexhip.dataClass.PatientViewDischargeData;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;




public class DischargeSummaryActivity extends AppCompatActivity {

    String patientId;
    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.discharge_summary);
        ImageButton imageButton8 = findViewById(R.id.imageButton8);
        imageButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define your back button behavior here
                finish(); // Close the current activity
            }
        });

        pdfView = findViewById(R.id.pdfView);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        patientId = sharedPreferences.getString(Constant.VIEW_PATIENT_ID_FOR_MEDICAL_DETAILS, null);

        ApiService apiService = RetrofitClient.getInstance();
        apiService.patientViewDischargeSummary(Integer.parseInt(patientId)).enqueue(new Callback<PatientViewDischargeData>() {
            @Override
            public void onResponse(Call<PatientViewDischargeData> call, Response<PatientViewDischargeData> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String pdfUrl = RetrofitClient.BASE_URL + response.body().getData();
                    new RetrievePDFfromUrl().execute(pdfUrl);
                }
            }

            @Override
            public void onFailure(Call<PatientViewDischargeData> call, Throwable t) {
                Toast.makeText(DischargeSummaryActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    class RetrievePDFfromUrl extends AsyncTask<String, Void, InputStream> {
        @Override
        protected InputStream doInBackground(String... strings) {
            // we are using inputstream
            // for getting out PDF.
            InputStream inputStream = null;
            try {
                URL url = new URL(strings[0]);
                // below is the step where we are
                // creating our connection.
                HttpURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                if (urlConnection.getResponseCode() == 200) {
                    // response is success.
                    // we are getting input stream from url
                    // and storing it in our variable.
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }

            } catch (IOException e) {
                // this is the method
                // to handle errors.
                e.printStackTrace();
                return null;
            }
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            // after the execution of our async
            // task we are loading our pdf in our pdf view.
            pdfView.fromStream(inputStream).load();
        }
    }

}