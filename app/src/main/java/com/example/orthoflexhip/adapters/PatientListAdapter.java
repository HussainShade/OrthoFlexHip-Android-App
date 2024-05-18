package com.example.orthoflexhip.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orthoflexhip.R;
import com.example.orthoflexhip.ViewMedicalDetailsDoctorActivity;
import com.example.orthoflexhip.api.RetrofitClient;
import com.example.orthoflexhip.apiresponse.Constant;
import com.example.orthoflexhip.dataClass.PatientListRecyclerData;
import com.example.orthoflexhip.dataClass.RecentPatientRecyclerData;
import com.squareup.picasso.Picasso;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PatientListAdapter extends RecyclerView.Adapter<PatientListAdapter.PatientListViewholder> {
    private ArrayList<PatientListRecyclerData> itemList;
    private Context context;
    public PatientListAdapter(ArrayList<PatientListRecyclerData> itemList,Context context) {
        this.itemList = itemList;
        this.context=context;
    }
    public void filterList(ArrayList<PatientListRecyclerData> filterlist) {
        // below line is to add our filtered
        // list in our course array list.
        itemList = filterlist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PatientListViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate your item layout and create a new ViewHolder instance
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item3, parent, false);
        return new PatientListViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientListViewholder holder, int position) {
        PatientListRecyclerData item = itemList.get(position);
        holder.patientName.setText(item.getName());
        holder.problem.setText(item.getProblem());
        String image=item.getProfilePhoto();
        holder.admittedDate.setText(item.getAdmittedDate());
        Picasso.get()
                .load(RetrofitClient.BASE_URL+image)
                .placeholder(R.drawable.myprofile) // Optional placeholder image while loading
                .error(R.drawable.myprofile) // Optional error image if the load fails
                .into(holder.patientImage);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ViewMedicalDetailsDoctorActivity.class);
                SharedPreferences sf=context.getSharedPreferences("MyPrefs",Context.MODE_PRIVATE);
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

    public static class PatientListViewholder extends RecyclerView.ViewHolder {
        TextView patientName,problem,admittedDate;
        ImageView patientImage;
        Button button;
        public PatientListViewholder(@NonNull View itemView) {
            super(itemView);
            patientName = itemView.findViewById(R.id.textView89);
            problem = itemView.findViewById(R.id.textView90);
            admittedDate = itemView.findViewById(R.id.textView91);
            patientImage = itemView.findViewById(R.id.imageView10);
            button=itemView.findViewById(R.id.button19);
        }
    }
}
