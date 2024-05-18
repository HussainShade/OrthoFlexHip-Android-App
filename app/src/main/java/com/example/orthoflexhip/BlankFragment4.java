package com.example.orthoflexhip;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.orthoflexhip.adapters.PatientListAdapter;
import com.example.orthoflexhip.adapters.RecentAddedPatientsAdapter;
import com.example.orthoflexhip.api.ApiService;
import com.example.orthoflexhip.api.RetrofitClient;
import com.example.orthoflexhip.dataClass.RecentPatientData;
import com.example.orthoflexhip.dataClass.RecentPatientRecyclerData;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BlankFragment4 extends Fragment {

    RecyclerView recentPatientsRV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.doctorhome_fragment, container, false);
        recentPatientsRV = view.findViewById(R.id.RecentPatients);

        recentPatientsRV.setLayoutManager(new LinearLayoutManager(requireContext()));

        ApiService apiService = RetrofitClient.getInstance();
        apiService.recentpatients().enqueue(new Callback<RecentPatientData>() {
            @Override
            public void onResponse(Call<RecentPatientData> call, Response<RecentPatientData> response) {
                RecentAddedPatientsAdapter adapter = null;
                RecentPatientRecyclerData[] dataArray = response.body().getData();
                ArrayList<RecentPatientRecyclerData> dataList = new ArrayList<>(Arrays.asList(dataArray));
                adapter = new RecentAddedPatientsAdapter(dataList,getContext());
                recentPatientsRV.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<RecentPatientData> call, Throwable t) {
                Toast.makeText(requireContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}