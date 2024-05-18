package com.example.orthoflexhip;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orthoflexhip.adapters.PatientListAdapter;
import com.example.orthoflexhip.adapters.RecentAddedPatientsAdapter;
import com.example.orthoflexhip.api.ApiService;
import com.example.orthoflexhip.api.RetrofitClient;
import com.example.orthoflexhip.dataClass.PatientListData;
import com.example.orthoflexhip.dataClass.PatientListRecyclerData;
import com.example.orthoflexhip.dataClass.RecentPatientData;
import com.example.orthoflexhip.dataClass.RecentPatientRecyclerData;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BlankFragment extends Fragment {

    RecyclerView patientListRV;
    SearchView search;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.patient_fragment, container, false);

        // Find the button in the fragment layout
        ImageButton button = view.findViewById(R.id.imageButton9);
        patientListRV = view.findViewById(R.id.patientListRV);
        search=view.findViewById(R.id.SearchPatients);
        // Set a click listener for the button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to start the new activity
                Intent intent = new Intent(getActivity(), AddPatientsActivity.class);
                startActivity(intent);
            }
        });


        patientListRV.setLayoutManager(new LinearLayoutManager(requireContext()));

        ApiService apiService = RetrofitClient.getInstance();
        apiService.patientList().enqueue(new Callback<PatientListData>() {
            ArrayList<PatientListRecyclerData> dataList;
            PatientListAdapter adapter = null;

            @Override
            public void onResponse(Call<PatientListData> call, Response<PatientListData> response) {
                PatientListRecyclerData[] dataArray = response.body().getData();
                 dataList = new ArrayList<>(Arrays.asList(dataArray));
                adapter = new PatientListAdapter(dataList,getContext());
                patientListRV.setAdapter(adapter);
                searchOption();
            }

            @Override
            public void onFailure(Call<PatientListData> call, Throwable t) {
                Toast.makeText(requireContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }
            private void searchOption()
            {
                search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        // inside on query text change method we are
                        // calling a method to filter our recycler view.
                        filter(newText);
                        return false;
                    }
                });
            }

            private void filter(String text) {
                // creating a new array list to filter our data.
                ArrayList<PatientListRecyclerData> filteredlist = new ArrayList<PatientListRecyclerData>();

                // running a for loop to compare elements.
                for (PatientListRecyclerData item : dataList) {
                    // checking if the entered string matched with any item of our recycler view.
                    if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                        // if the item is matched we are
                        // adding it to our filtered list.
                        filteredlist.add(item);
                    }
                }
                if (filteredlist.isEmpty()) {
                    // if no item is added in filtered list we are
                    // displaying a toast message as no data found.
                    Toast.makeText(getContext(), "No Data Found..", Toast.LENGTH_SHORT).show();
                } else {
                    // at last we are passing that filtered
                    // list to our adapter class.
                    adapter.filterList(filteredlist);
                }
            }
        });

        return view;
    }

}
