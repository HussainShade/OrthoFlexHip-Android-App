package com.example.orthoflexhip;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orthoflexhip.adapters.ExerciseVideoDoctorAdapter;
import com.example.orthoflexhip.adapters.TrackAppointmentsAdapter;
import com.example.orthoflexhip.api.ApiService;
import com.example.orthoflexhip.api.RetrofitClient;
import com.example.orthoflexhip.dataClass.TrackAppointmentData;
import com.example.orthoflexhip.dataClass.TrackAppointmentRecyclerData;
import com.example.orthoflexhip.dataClass.VideoListData;
import com.example.orthoflexhip.dataClass.VideoListRecyclerData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BlankFragment3 extends Fragment implements ExerciseVideoDoctorAdapter.OnItemClickListener {

    RecyclerView doctorExerciseVideosRV;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.videos_fragment, container, false);

        // Correctly cast the view to AppCompatImageButton
        ImageButton button = view.findViewById(R.id.imageButton9);
        doctorExerciseVideosRV = view.findViewById(R.id.doctorExerciseVideosRV);
//        showDoctorVideos();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Use getActivity() to get the hosting activity and start the activity from there
                Intent intent = new Intent(getActivity(), UploadExerciseVideosActivity.class);
                startActivity(intent);
            }
        });

        doctorExerciseVideosRV.setLayoutManager(new LinearLayoutManager(requireContext()));

        ApiService apiService = RetrofitClient.getInstance();
        apiService.videoList().enqueue(new Callback<VideoListData>() {
            @Override
            public void onResponse(Call<VideoListData> call, Response<VideoListData> response) {
                if(response.isSuccessful()){
                    ExerciseVideoDoctorAdapter adapter = null;
                    VideoListRecyclerData[] dataArray =response.body().getData();
                    ArrayList<VideoListRecyclerData> dataList = new ArrayList<>(Arrays.asList(dataArray));
                    adapter = new ExerciseVideoDoctorAdapter(requireContext(),dataList,BlankFragment3.this);
                    doctorExerciseVideosRV.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<VideoListData> call, Throwable t) {
                Toast.makeText(requireContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void onItemClick(int id, String name, String fileName) {
        Intent intent = new Intent(requireContext(), ViewExerciseVideosActivity.class);
        intent.putExtra("VIDEO_NAME", name);
        intent.putExtra("VIDEO_FILE", fileName);
        startActivity(intent);
    }

//    private void showDoctorVideos() {
//        ApiService apiService = RetrofitClient.getInstance();
//        apiService.videoList().enqueue(new Callback<VideoListData>() {
//            @Override
//            public void onResponse(Call<VideoListData> call, Response<VideoListData> response) {
//                ExerciseVideoDoctorAdapter adapter = null;
//               VideoListRecyclerData[] dataArray =response.body().getData();
//               System.out.println(Arrays.toString(dataArray));
//                ArrayList<VideoListRecyclerData> dataList = new ArrayList<>(Arrays.asList(dataArray));
//                adapter = new ExerciseVideoDoctorAdapter(dataList);
//                doctorExerciseVideosRV.setAdapter(adapter);
//            }
//
//            @Override
//            public void onFailure(Call<VideoListData> call, Throwable t) {
//                Toast.makeText(requireContext(), t.toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}
