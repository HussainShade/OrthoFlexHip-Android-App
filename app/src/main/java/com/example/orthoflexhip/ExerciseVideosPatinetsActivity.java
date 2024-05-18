package com.example.orthoflexhip;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orthoflexhip.adapters.ExerciseVideoDoctorAdapter;
import com.example.orthoflexhip.api.ApiService;
import com.example.orthoflexhip.api.RetrofitClient;
import com.example.orthoflexhip.dataClass.VideoListData;
import com.example.orthoflexhip.dataClass.VideoListRecyclerData;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExerciseVideosPatinetsActivity extends AppCompatActivity implements ExerciseVideoDoctorAdapter.OnItemClickListener {

    RecyclerView patientExerciseVideosRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.exercise_videos_patinets);
        patientExerciseVideosRV = findViewById(R.id.patientExerciseVideosRV);
        ImageButton imageButton8 = findViewById(R.id.imageButton8);
        imageButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define your back button behavior here
                finish(); // Close the current activity
            }
        });

        patientExerciseVideosRV.setLayoutManager(new LinearLayoutManager(this));

        ApiService apiService = RetrofitClient.getInstance();
        apiService.videoList().enqueue(new Callback<VideoListData>() {
            @Override
            public void onResponse(Call<VideoListData> call, Response<VideoListData> response) {
                if(response.isSuccessful()){
                    ExerciseVideoDoctorAdapter adapter = null;
                    VideoListRecyclerData[] dataArray =response.body().getData();
                    ArrayList<VideoListRecyclerData> dataList = new ArrayList<>(Arrays.asList(dataArray));
                    adapter = new ExerciseVideoDoctorAdapter(ExerciseVideosPatinetsActivity.this,dataList,ExerciseVideosPatinetsActivity.this);
                    patientExerciseVideosRV.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<VideoListData> call, Throwable t) {
                Toast.makeText(ExerciseVideosPatinetsActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClick(int id, String name, String fileName) {
        Intent intent = new Intent(this, ViewExerciseVideosActivity.class);
        intent.putExtra("VIDEO_NAME", name);
        intent.putExtra("VIDEO_FILE", fileName);
        startActivity(intent);
    }
}