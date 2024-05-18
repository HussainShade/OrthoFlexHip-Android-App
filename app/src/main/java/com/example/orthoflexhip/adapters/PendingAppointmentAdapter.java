package com.example.orthoflexhip.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orthoflexhip.R;
import com.example.orthoflexhip.api.ApiService;
import com.example.orthoflexhip.api.RetrofitClient;
import com.example.orthoflexhip.apiresponse.ApprovedStatusResponse;
import com.example.orthoflexhip.dataClass.PendingAppoinmentRecyclerData;
import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PendingAppointmentAdapter extends RecyclerView.Adapter<PendingAppointmentAdapter.PendingAppointmentViewholder> {
    private ArrayList<PendingAppoinmentRecyclerData> itemList;
    private Context context;
    public PendingAppointmentAdapter(ArrayList<PendingAppoinmentRecyclerData> itemList,Context context) {
        this.itemList = itemList;
        this.context=context;
    }

    @NonNull
    @Override
    public PendingAppointmentViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate your item layout and create a new ViewHolder instance
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items4, parent, false);
        return new PendingAppointmentViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PendingAppointmentViewholder holder, int position) {
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
        holder.approveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiCall(item.getID(),"Approved");
            }
        });
        holder.rejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiCall(item.getID(),"Rejected");
            }
        });
    }
    private void apiCall(String id,String status){
        ApiService api=RetrofitClient.getInstance();
        Call<ApprovedStatusResponse> call =api.statusUpdate(id,status);
        call.enqueue(new Callback<ApprovedStatusResponse>() {
            @Override
            public void onResponse(Call<ApprovedStatusResponse> call, Response<ApprovedStatusResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().getSuccess()){
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                else{

                }
            }

            @Override
            public void onFailure(Call<ApprovedStatusResponse> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class PendingAppointmentViewholder extends RecyclerView.ViewHolder {
        TextView patientName,reason,scheduleDate, scheduleTime;
        ShapeableImageView patientImage;
        Button rejectButton,approveButton;
        public PendingAppointmentViewholder(@NonNull View itemView) {
            super(itemView);
            patientName = itemView.findViewById(R.id.textView89);
            reason = itemView.findViewById(R.id.textView90);
            scheduleDate = itemView.findViewById(R.id.textView91);
            scheduleTime = itemView.findViewById(R.id.textView92);
            rejectButton = itemView.findViewById(R.id.button20);
            approveButton = itemView.findViewById(R.id.button19);
            patientImage = itemView.findViewById(R.id.imageView10);
        }
    }
}
