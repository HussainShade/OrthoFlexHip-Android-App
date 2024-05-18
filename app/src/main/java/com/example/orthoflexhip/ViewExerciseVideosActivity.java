package com.example.orthoflexhip;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.orthoflexhip.api.RetrofitClient;

public class ViewExerciseVideosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_exercise_videos);

        VideoView video = findViewById(R.id.video);
        TextView videoNameTV = findViewById(R.id.videoName);
        ImageButton backButton = findViewById(R.id.imageButton8);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String videoName = getIntent().getStringExtra("VIDEO_NAME");
        String videoFile = getIntent().getStringExtra("VIDEO_FILE");

        videoNameTV.setText(videoName);
//        video.setVideoURI(Uri.parse(RetrofitClient.NETWORK_URL+videoFile));
        MediaController mediaController = new MediaController(this);
        video.setVideoURI(Uri.parse(RetrofitClient.BASE_URL+videoFile));
        mediaController.setAnchorView(video);
        mediaController.setMediaPlayer(video);

        // sets the media controller to the videoView
        video.setMediaController(mediaController);
    }
}