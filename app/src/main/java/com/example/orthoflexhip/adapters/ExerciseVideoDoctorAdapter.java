package com.example.orthoflexhip.adapters;

import static com.example.orthoflexhip.api.RetrofitClient.BASE_URL;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orthoflexhip.R;
import com.example.orthoflexhip.api.RetrofitClient;
import com.example.orthoflexhip.dataClass.VideoListRecyclerData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ExerciseVideoDoctorAdapter extends RecyclerView.Adapter<ExerciseVideoDoctorAdapter.ExerciseVideoDoctorViewholder> {

    public interface OnItemClickListener {
        void onItemClick(int id,String name,String fileName);
    }
    private ArrayList<VideoListRecyclerData> itemList;
    private Context context;
    private OnItemClickListener listener;
    public ExerciseVideoDoctorAdapter(Context context, ArrayList<VideoListRecyclerData> itemList,OnItemClickListener listener) {
        this.itemList = itemList;
        this.context=context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ExerciseVideoDoctorViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate your item layout and create a new ViewHolder instance
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item5, parent, false);
        return new ExerciseVideoDoctorAdapter.ExerciseVideoDoctorViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseVideoDoctorViewholder holder, int position) {
        VideoListRecyclerData item = itemList.get(position);
        holder.videoTextTv.setText(item.getVideoName());
        String video=item.getVideoFile();
//        holder.imageView.setVisibility(View.GONE);
        holder.video.setVisibility(View.VISIBLE);
        Uri uri = Uri.parse(BASE_URL+video);
        holder.video.setVideoURI(uri);
        MediaController mediaController = new MediaController(context);
        holder.video.setVideoURI(uri);
        holder.video.setVideoURI(uri);
        mediaController.setAnchorView(holder.video);

        // sets the media player to the videoView
        mediaController.setMediaPlayer(holder.video);

        // sets the media controller to the videoView
        holder.video.setMediaController(mediaController);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.imageView.setVisibility(View.GONE);
                holder.video.start();
            }
        });
        holder.imageView.setOnClickListener(v -> listener.onItemClick(item.getVideoID(),item.getVideoName(),item.getVideoFile()));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ExerciseVideoDoctorViewholder extends RecyclerView.ViewHolder {

        VideoView video;

        TextView videoTextTv;
        ImageView imageView;
        public ExerciseVideoDoctorViewholder(@NonNull View itemView) {
            super(itemView);
            video = itemView.findViewById(R.id.video);
            imageView=itemView.findViewById(R.id.imageView11);
            videoTextTv = itemView.findViewById(R.id.videoTextTv);

        }
    }
}
