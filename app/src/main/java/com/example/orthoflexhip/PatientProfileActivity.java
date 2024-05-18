package com.example.orthoflexhip;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.orthoflexhip.api.ApiService;
import com.example.orthoflexhip.api.RetrofitClient;
import com.example.orthoflexhip.apiresponse.ApprovedStatusResponse;
import com.example.orthoflexhip.dataClass.DoctorProfileData;
import com.example.orthoflexhip.dataClass.PatientProfileData;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PatientProfileActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST_CODE = 1;

    int patientId;
    ImageView patientImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.patient_profile);
        ImageButton imageButton10 = findViewById(R.id.imageButton10);
        TextView name = findViewById(R.id.textView47);
        TextView username = findViewById(R.id.textView50);
        TextView hospitalId = findViewById(R.id.textView53);
        TextView gender = findViewById(R.id.textView56);
        TextView age = findViewById(R.id.textView59);
        TextView mobile = findViewById(R.id.textView62);
         patientImage = findViewById(R.id.imageView8);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        patientId = sharedPreferences.getInt("patientId", -1);

        ApiService apiService = RetrofitClient.getInstance();
        apiService.patientProfile(patientId).enqueue(new Callback<PatientProfileData>() {
            @Override
            public void onResponse(Call<PatientProfileData> call, Response<PatientProfileData> response) {
                if (response.isSuccessful()) {
                    name.setText(response.body().getData().getName());
                    username.setText(response.body().getData().getUsername());
                    hospitalId.setText(response.body().getData().getHospitalID());
                    gender.setText(response.body().getData().getGender());
                    age.setText(response.body().getData().getAge());
                    mobile.setText(response.body().getData().getMobile());
                    Picasso.get().load(RetrofitClient.BASE_URL+response.body().getData().getProfilePhoto()).into(patientImage);
                }
            }

            @Override
            public void onFailure(Call<PatientProfileData> call, Throwable t) {
                Toast.makeText(PatientProfileActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        imageButton10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define your back button behavior here
                finish(); // Close the current activity
            }
        });
        TextView textView63 = findViewById(R.id.textView63);
        if (textView63 != null) {
            textView63.setOnClickListener(view -> openDoctorProfileUpdateWithAnimation());
        }
    }

    private void openDoctorProfileUpdateWithAnimation() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST_CODE);

        // Apply slide-up animation using ViewPropertyAnimator
        ImageButton imageButtonProfile = findViewById(R.id.imageButton10);
        if (imageButtonProfile != null) {
            imageButtonProfile.animate().translationY(0).setDuration(500);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            if(selectedImageUri!=null) {
                try {

                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                    SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                    int id = sharedPreferences.getInt("patientId", -1);
                    RequestBody name = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(id));
                    patientImage.setImageBitmap(bitmap);
                    Call<ApprovedStatusResponse> responseCall=RetrofitClient.getInstance()
                            .patientImageUpload(uploadVideo(getPath(selectedImageUri)),name);
                    responseCall.enqueue(new Callback<ApprovedStatusResponse>() {
                        @Override
                        public void onResponse(Call<ApprovedStatusResponse> call, Response<ApprovedStatusResponse> response) {
                            if(response.isSuccessful()){
                                if(response.body().getStatus().equalsIgnoreCase("success")){
                                    Toast.makeText(PatientProfileActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(PatientProfileActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ApprovedStatusResponse> call, Throwable t) {
                            Toast.makeText(PatientProfileActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
                    // Set the bitmap as the profile photo
                    // For example, if you have an ImageView with id imageViewProfilePhoto:
                    // ImageView imageViewProfilePhoto = findViewById(R.id.imageViewProfilePhoto);
                    // imageViewProfilePhoto.setImageBitmap(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private  MultipartBody.Part uploadVideo(String str)
    {
        File imageFile = new File(str);
        RequestBody videoBody = RequestBody.create(MediaType.parse("image/*"), imageFile);
        MultipartBody.Part vFile = MultipartBody.Part.createFormData("profile_photo", imageFile.getName(), videoBody);
        return vFile;
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
}
