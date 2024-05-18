package com.example.orthoflexhip.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orthoflexhip.R;
import com.example.orthoflexhip.api.RetrofitClient;
import com.example.orthoflexhip.dataClass.PendingAppoinmentRecyclerData;
import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class ApprovedAppointmentAdapter extends RecyclerView.Adapter<ApprovedAppointmentAdapter.ApprovedAppointmentViewholder> {
    private ArrayList<PendingAppoinmentRecyclerData> itemList;

    public ApprovedAppointmentAdapter(ArrayList<PendingAppoinmentRecyclerData> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ApprovedAppointmentAdapter.ApprovedAppointmentViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate your item layout and create a new ViewHolder instance
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item6, parent, false);
        return new ApprovedAppointmentAdapter.ApprovedAppointmentViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ApprovedAppointmentAdapter.ApprovedAppointmentViewholder holder, int position) {
        PendingAppoinmentRecyclerData item = itemList.get(position);
        holder.patientName.setText(item.getName());
        holder.reason.setText(item.getReason());
        String image=item.getPatientPhoto();
        holder.scheduleDate.setText(item.getScheduleDate());
        holder.scheduleTime.setText(item.getScheduleTime());
        Picasso.get()
                .load(RetrofitClient.BASE_URL+image)
                .placeholder(R.drawable.myprofile) // Optional placeholder image while loading
                .error(R.drawable.myprofile) // Optional error image if the load fails
                .into(holder.patientImage);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ApprovedAppointmentViewholder extends RecyclerView.ViewHolder {
        TextView patientName,reason,scheduleDate, scheduleTime;
        ShapeableImageView patientImage;
        public ApprovedAppointmentViewholder(@NonNull View itemView) {
            super(itemView);
            patientName = itemView.findViewById(R.id.textView89);
            reason = itemView.findViewById(R.id.textView90);
            scheduleDate = itemView.findViewById(R.id.textView91);
            scheduleTime = itemView.findViewById(R.id.textView94);
            patientImage = itemView.findViewById(R.id.imageView10);
        }
    }
}
