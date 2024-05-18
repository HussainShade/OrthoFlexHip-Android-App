package com.example.orthoflexhip;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.Toast;
import com.example.orthoflexhip.api.ApiService;
import com.example.orthoflexhip.api.RetrofitClient;
import com.example.orthoflexhip.dataClass.PatientViewDischargeData;
import com.github.barteksc.pdfviewer.PDFView;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PatientDischargeSummaryActivity extends AppCompatActivity {

    int patientId;
    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_discharge_summary);
        ImageButton backButton = findViewById(R.id.imageButton8);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

         pdfView = findViewById(R.id.pdfView);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        patientId = sharedPreferences.getInt("patientId", -1);

        ApiService apiService = RetrofitClient.getInstance();
        apiService.patientViewDischargeSummary(patientId).enqueue(new Callback<PatientViewDischargeData>() {
            @Override
            public void onResponse(Call<PatientViewDischargeData> call, Response<PatientViewDischargeData> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String pdfUrl = RetrofitClient.BASE_URL + response.body().getData();
                    new RetrievePDFfromUrl().execute(pdfUrl);
                }
            }

            @Override
            public void onFailure(Call<PatientViewDischargeData> call, Throwable t) {
                Toast.makeText(PatientDischargeSummaryActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
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