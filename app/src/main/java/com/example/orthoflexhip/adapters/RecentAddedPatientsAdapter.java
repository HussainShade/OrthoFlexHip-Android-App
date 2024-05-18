package com.example.orthoflexhip.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orthoflexhip.R;
import com.example.orthoflexhip.ViewMedicalDetailsDoctorActivity;
import com.example.orthoflexhip.api.RetrofitClient;
import com.example.orthoflexhip.apiresponse.Constant;
import com.example.orthoflexhip.dataClass.RecentPatientRecyclerData;
import com.squareup.picasso.Picasso;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class RecentAddedPatientsAdapter extends RecyclerView.Adapter<RecentAddedPatientsAdapter.RecentPatientAddedViewholder> {
    private ArrayList<RecentPatientRecyclerData> itemList;
    private Context context;

    public RecentAddedPatientsAdapter(ArrayList<RecentPatientRecyclerData> itemList,Context context) {
        this.itemList = itemList;
        this.context=context;
    }

    @NonNull
    @Override
    public RecentPatientAddedViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate your item layout and create a new ViewHolder instance
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items2, parent, false);
        return new RecentPatientAddedViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentPatientAddedViewholder holder, int position) {
        RecentPatientRecyclerData item = itemList.get(position);
        holder.patientName.setText(item.getName());
        holder.status.setText(item.getStatus());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // You can change the format as needed
        String admittedDateStr = item.getAdmitted_date();
        String image=item.getProfile_photo();
        holder.admittedDate.setText(admittedDateStr);
        Picasso.get()
                .load(RetrofitClient.BASE_URL+image)
                .placeholder(R.drawable.myprofile) // Optional placeholder image while loading
                .error(R.drawable.myprofile) // Optional error image if the load fails
                .into(holder.patientImage);
        holder.patientImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ViewMedicalDetailsDoctorActivity.class);
                SharedPreferences sf=context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sf.edit();
                editor.putString(Constant.VIEW_PATIENT_ID_FOR_MEDICAL_DETAILS,item.getID());
                editor.apply();
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class RecentPatientAddedViewholder extends RecyclerView.ViewHolder {

        TextView patientName,status,admittedDate;
        ImageView patientImage;
        public RecentPatientAddedViewholder(@NonNull View itemView) {
            super(itemView);
            patientName = itemView.findViewById(R.id.textView84);
            status = itemView.findViewById(R.id.textView86);
            admittedDate = itemView.findViewById(R.id.textView88);
            patientImage = itemView.findViewById(R.id.imageView9);
        }
    }
}
