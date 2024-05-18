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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.orthoflexhip.api.ApiService;
import com.example.orthoflexhip.api.RetrofitClient;
import com.example.orthoflexhip.apiresponse.ApprovedStatusResponse;
import com.example.orthoflexhip.apiresponse.Constant;
import com.example.orthoflexhip.dataClass.PatientXrayData;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewXRayActivity extends AppCompatActivity {
    private static final int PICK_PRE_IMAGE_REQUEST_CODE = 1;
    private static final int PICK_POST_IMAGE_REQUEST_CODE = 2;
    ImageView imagePreXray,imagePostXray;
// DocumentActivity.openDocument(this, R.raw.sample)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.view_x_ray);
        ImageButton imageButton8 = findViewById(R.id.imageButton8);
        imageButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define your back button behavior here
                finish(); // Close the current activity
            }
        });
        Button button9 = findViewById(R.id.button9); // Replace with your Button9 ID
        Button button10 = findViewById(R.id.button10); // Replace with your Button10 ID
         imagePreXray=findViewById(R.id.imageView6);
         imagePostXray=findViewById(R.id.imageView7);

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImagePickerForButton9();
            }
        });

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImagePickerForButton10();
            }
        });
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String patientId = sharedPreferences.getString(Constant.VIEW_PATIENT_ID_FOR_MEDICAL_DETAILS, null);

        ApiService apiService = RetrofitClient.getInstance();
        apiService.patientXrayImage(Integer.parseInt(patientId)).enqueue(new Callback<PatientXrayData>() {
            @Override
            public void onResponse(Call<PatientXrayData> call, Response<PatientXrayData> response) {
                if (response.isSuccessful()) {
                    Picasso.get().load(RetrofitClient.BASE_URL+response.body().getData()
                            .getPreXrayImage()).into(imagePreXray);
                    Picasso.get().load(RetrofitClient.BASE_URL+response.body().getData()
                            .getPostXrayImage()).into(imagePostXray);
                }
            }

            @Override
            public void onFailure(Call<PatientXrayData> call, Throwable t) {
                Toast.makeText(ViewXRayActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openImagePickerForButton9() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_PRE_IMAGE_REQUEST_CODE);
    }

    private void openImagePickerForButton10() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_POST_IMAGE_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_PRE_IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            if(selectedImageUri!=null){

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                    // Set the bitmap to your ImageView or perform other operations
                    SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                    String id = sharedPreferences.getString(Constant.VIEW_PATIENT_ID_FOR_MEDICAL_DETAILS, null);
                    RequestBody name = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(id));
                    imagePreXray.setImageBitmap(bitmap);
                    Call<ApprovedStatusResponse> responseCall=RetrofitClient.getInstance()
                            .preXray(uploadVideo(getPath(selectedImageUri),"pre_xray_image"),name);
                    responseCall.enqueue(new Callback<ApprovedStatusResponse>() {
                        @Override
                        public void onResponse(Call<ApprovedStatusResponse> call, Response<ApprovedStatusResponse> response) {
                            if(response.isSuccessful()){
                                if(response.body().getStatus().equalsIgnoreCase("success")){
                                    Toast.makeText(ViewXRayActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(ViewXRayActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ApprovedStatusResponse> call, Throwable t) {
                            Toast.makeText(ViewXRayActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (requestCode == PICK_POST_IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            if(selectedImageUri!=null){

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                    // Set the bitmap to your ImageView or perform other operations
                    SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                    String id = sharedPreferences.getString(Constant.VIEW_PATIENT_ID_FOR_MEDICAL_DETAILS, null);
                    RequestBody name = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(id));
                    imagePostXray.setImageBitmap(bitmap);
                    Call<ApprovedStatusResponse> responseCall=RetrofitClient.getInstance()
                            .postXray(uploadVideo(getPath(selectedImageUri),"post_xray_image"),name);
                    responseCall.enqueue(new Callback<ApprovedStatusResponse>() {
                        @Override
                        public void onResponse(Call<ApprovedStatusResponse> call, Response<ApprovedStatusResponse> response) {
                            if(response.isSuccessful()){
                                if(response.body().getStatus().equalsIgnoreCase("success")){
                                    Toast.makeText(ViewXRayActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(ViewXRayActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ApprovedStatusResponse> call, Throwable t) {
                            Toast.makeText(ViewXRayActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private  MultipartBody.Part uploadVideo(String str,String xray)
    {
        File imageFile = new File(str);
        RequestBody videoBody = RequestBody.create(MediaType.parse("image/*"), imageFile);
        MultipartBody.Part vFile = MultipartBody.Part.createFormData(xray, imageFile.getName(), videoBody);
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
