package com.example.orthoflexhip;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.orthoflexhip.api.ApiService;
import com.example.orthoflexhip.api.RetrofitClient;
import com.example.orthoflexhip.dataClass.UploadVideoData;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadExerciseVideosActivity extends AppCompatActivity {

    Uri selectedVideoUri;
    EditText videoUrlET;
    private static final int STORAGE_PERMISSION_CODE = 101;

    private static final int PICK_VIDEO_REQUEST_CODE = 1003;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.upload_exercise_videos);

        // Button to open gallery and select a video
        Button openGalleryButton = findViewById(R.id.button11);
        openGalleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                checkPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, STORAGE_PERMISSION_CODE);
                openGallery();
            }
        });

        EditText videoTitleET = findViewById(R.id.editTextText2);
        videoUrlET = findViewById(R.id.editTextText3);
        videoUrlET.setEnabled(false);

        // Back button
        ImageButton backButton = findViewById(R.id.imageButton8);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close the current activity
            }
        });

        Button uploadButton = findViewById(R.id.button12);
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String videoTitle = videoTitleET.getText().toString();
                RequestBody name=RequestBody.create(MediaType.parse("text/plain"),videoTitle);
                if (selectedVideoUri != null) {
                    String selectedImagePath = getPath(selectedVideoUri);
                    uploadVideo(name, uploadVideo(selectedImagePath));
                } else {
                    Toast.makeText(UploadExerciseVideosActivity.this, "Please select a video", Toast.LENGTH_SHORT).show();
                }
            }
        });

        if (selectedVideoUri != null) {
            videoUrlET.setText(selectedVideoUri.toString());
        }
    }
    public void checkPermission(String permission, int requestCode)
    {
        // Checking if permission is not granted
        if (ContextCompat.checkSelfPermission(UploadExerciseVideosActivity.this, permission) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(UploadExerciseVideosActivity.this, new String[] { permission }, requestCode);
        }
        else {
        }
    }
    private  MultipartBody.Part uploadVideo(String str)
    {
        File videoFile = new File(str);
        RequestBody videoBody = RequestBody.create(MediaType.parse("video/*"), videoFile);
        MultipartBody.Part vFile = MultipartBody.Part.createFormData("video_file", videoFile.getName(), videoBody);
        return vFile;
    }
    private void uploadVideo(RequestBody videoTitle, MultipartBody.Part selectedUrlUri) {
        ApiService apiService = RetrofitClient.getInstance();
        apiService.uploadVideo(videoTitle,selectedUrlUri).enqueue(new Callback<UploadVideoData>() {
            @Override
            public void onResponse(Call<UploadVideoData> call, Response<UploadVideoData> response) {
                if(response.isSuccessful()){
                    if (response.body().getStatus()) {
                        Toast.makeText(UploadExerciseVideosActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(UploadExerciseVideosActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(UploadExerciseVideosActivity.this,"error", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<UploadVideoData> call, Throwable t) {
                Log.e("TAG","error "+t.getMessage());
                Toast.makeText(UploadExerciseVideosActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
        intent.setType("video/*"); // Set MIME type to filter videos
        startActivityForResult(Intent.createChooser(intent, "Select Video"), PICK_VIDEO_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_VIDEO_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            selectedVideoUri = data.getData();
            File videoFile = new File(getPath(selectedVideoUri));
            videoUrlET.setText(videoFile.getName());
            // MEDIA GALLERY
            // Use the selected video URI
        }

    }
    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Video.Media.DATA };
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            // HERE YOU WILL GET A NULLPOINTER IF CURSOR IS NULL
            // THIS CAN BE, IF YOU USED OI FILE MANAGER FOR PICKING THE MEDIA
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else
            return null;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

         if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(UploadExerciseVideosActivity.this, "Storage Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(UploadExerciseVideosActivity.this, "Storage Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

   // private void uploadVideo()
