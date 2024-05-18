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
import com.example.orthoflexhip.dataClass.RecentPatientRecyclerData;
import com.example.orthoflexhip.dataClass.TrackAppointmentRecyclerData;
import com.squareup.picasso.Picasso;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TrackAppointmentsAdapter extends RecyclerView.Adapter<TrackAppointmentsAdapter.TrackAppointmentsViewHolder> {

    private ArrayList<TrackAppointmentRecyclerData> itemList;

    public TrackAppointmentsAdapter(ArrayList<TrackAppointmentRecyclerData> itemList) {
        this.itemList = itemList;
    }
    @NonNull
    @Override
    public TrackAppointmentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate your item layout and create a new ViewHolder instance
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent, false);
        return new TrackAppointmentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrackAppointmentsAdapter.TrackAppointmentsViewHolder holder, int position) {
        TrackAppointmentRecyclerData item = itemList.get(position);
        holder.doctorName.setText("Dr. Sajith");
        holder.reason.setText(item.getReason());
        holder.raisedOn.setText(item.getRequestDate());
        holder.schedule.setText(item.getScheduleDate());
        holder.time.setText(item.getScheduleTime());
        holder.status.setText(item.getStatus());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class TrackAppointmentsViewHolder extends RecyclerView.ViewHolder {
        TextView doctorName,reason,raisedOn,schedule,time,status;
        public TrackAppointmentsViewHolder(@NonNull View itemView) {
            super(itemView);
            doctorName = itemView.findViewById(R.id.textView21E);
            reason = itemView.findViewById(R.id.textView21F);
            raisedOn = itemView.findViewById(R.id.textView21G);
            schedule = itemView.findViewById(R.id.textView21H);
            time = itemView.findViewById(R.id.textView21I);
            status = itemView.findViewById(R.id.textView21J);
        }
    }
}
