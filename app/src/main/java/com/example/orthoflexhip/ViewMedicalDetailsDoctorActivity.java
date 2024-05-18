package com.example.orthoflexhip;

import static com.example.orthoflexhip.AddPatientsActivity.PICK_FILE_REQUEST_CODE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.pdf.PdfRenderer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.orthoflexhip.api.RetrofitClient;
import com.example.orthoflexhip.apiresponse.ApprovedStatusResponse;
import com.example.orthoflexhip.apiresponse.Constant;
import com.example.orthoflexhip.apiresponse.FileUtils;
import com.example.orthoflexhip.apiresponse.PermissionClass;
import com.example.orthoflexhip.apiresponse.ViewPatientDetailsDataResponse;

import org.intellij.lang.annotations.JdkConstants;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.regex.Pattern;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ViewMedicalDetailsDoctorActivity extends AppCompatActivity {

    // Define the request code for file picker
    private static final int PICK_FILE_REQUEST_CODE = 100;
    private static final int PERMISSION_REQUEST_CODE =200;
    private static final int EXTERNAL_STORAGE_PERMISSION_CODE =5;
    private static final int PICK_FILE=99;
    TextView hospitalId,loginId,password,name,contactNo,age,gender,height,weight,problem,
    admittedDate,dischargeDate,score,condition;

    EditText feedback;
    ActivityResultLauncher<Intent> resultLauncher;
    String patientId;
    private void textViewValue(){
        hospitalId=findViewById(R.id.textView2);
        loginId=findViewById(R.id.textView4);
        password=findViewById(R.id.textView6);
        name=findViewById(R.id.textView04);
        contactNo=findViewById(R.id.textView25);
        age=findViewById(R.id.textView26);
        gender=findViewById(R.id.textView27);
        height=findViewById(R.id.textView28);
        weight=findViewById(R.id.textView29);
        problem=findViewById(R.id.textView210);
        admittedDate=findViewById(R.id.textView211);
        dischargeDate=findViewById(R.id.textView212);
        score=findViewById(R.id.textView213);
        condition=findViewById(R.id.textView214);
        feedback=findViewById(R.id.editTextText);
    }
    private void apiCarr(String id){
        Call<ViewPatientDetailsDataResponse> responseCall= RetrofitClient.getInstance().getPatientDetails(id);
        responseCall.enqueue(new Callback<ViewPatientDetailsDataResponse>() {
            @Override
            public void onResponse(Call<ViewPatientDetailsDataResponse> call, Response<ViewPatientDetailsDataResponse> response) {
                if(response.isSuccessful()){
                    ViewPatientDetailsDataResponse.Data data=response.body().getData();
                    name.setText(response.body().getData().getName());
                    loginId.setText(response.body().getData().getUsername());
                    hospitalId.setText(response.body().getData().getHospitalID());
                    gender.setText(response.body().getData().getGender());
                    age.setText(response.body().getData().getAge());
                    contactNo.setText(response.body().getData().getMobile());
                    password.setText(response.body().getData().getPassword());
                    height.setText(response.body().getData().getHeight());
                    weight.setText(response.body().getData().getWeight());
                    problem.setText(response.body().getData().getProblem());
                    admittedDate.setText(response.body().getData().getAdmittedDate());
                    dischargeDate.setText(response.body().getData().getDischargeDate());
                    score.setText(response.body().getData().getScore());
                    condition.setText(response.body().getData().getScoreResult());
                    feedback.setText(response.body().getData().getFeedback());
                }
            }

            @Override
            public void onFailure(Call<ViewPatientDetailsDataResponse> call, Throwable t) {
                Toast.makeText(ViewMedicalDetailsDoctorActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.view_medical_details_doctor);
        textViewValue();
        buttonClick();
        Button button = findViewById(R.id.button14);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewMedicalDetailsDoctorActivity.this, AddMedicationActivity.class);
                startActivity(intent);
            }
        });
        SharedPreferences sf=getSharedPreferences(Constant.SF_NAME,MODE_PRIVATE);
        apiCarr(sf.getString(Constant.VIEW_PATIENT_ID_FOR_MEDICAL_DETAILS,""));
    }

    private void buttonClick(){
        Button button5 = findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewMedicalDetailsDoctorActivity.this, ViewXRayActivity.class);
                startActivity(intent);
            }
        });

        Button button9 = findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button button6 = findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewMedicalDetailsDoctorActivity.this, HarrisHipScoreActivity.class);
                startActivity(intent);
            }
        });

        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewMedicalDetailsDoctorActivity.this, MedicationsActivity.class);
                startActivity(intent);
            }
        });

        Button button224 = findViewById(R.id.button224);
        button224.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewMedicalDetailsDoctorActivity.this, DischargeSummaryActivity.class);
                startActivity(intent);
            }
        });

        ImageButton backButton = findViewById(R.id.imageButton7);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close the current activity
            }
        });

        Button openStorageButton = findViewById(R.id.button24);
    openStorageButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        openFilePicker();

        }
    });

    }
    @SuppressLint("IntentReset")
    private void openFilePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, Uri.parse(MediaStore.Files.FileColumns.DISPLAY_NAME));
        intent.setType("*/*"); // Set MIME type to filter videos
        startActivityForResult(Intent.createChooser(intent, "Select PDF"), PICK_FILE);
    }
    private static String[] PERMISSIONS_STORAGE = {
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == EXTERNAL_STORAGE_PERMISSION_CODE) {
            // Check if permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, open file picker
                openFilePicker();
            } else {
                // Permission denied, handle accordingly (e.g., show a message)
                Toast.makeText(ViewMedicalDetailsDoctorActivity.this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_FILE && resultCode == RESULT_OK) {
            if (data != null) {
                Uri uri =data.getData();
                try {
                    SharedPreferences sf=getSharedPreferences(Constant.SF_NAME,MODE_PRIVATE);
                    String id=sf.getString(Constant.VIEW_PATIENT_ID_FOR_MEDICAL_DETAILS,null);
                    RequestBody name=RequestBody.create(MediaType.parse("text/plain"),id);
                   Call<ApprovedStatusResponse> responseCall=RetrofitClient.getInstance()
                           .dischargeSummaryUpload(uploadVideo(getPath1(uri)),name);
                   responseCall.enqueue(new Callback<ApprovedStatusResponse>() {
                       @Override
                       public void onResponse(Call<ApprovedStatusResponse> call, Response<ApprovedStatusResponse> response) {
                           if(response.isSuccessful()){
                               if(response.body().getStatus().equalsIgnoreCase("success")){
                                   Toast.makeText(ViewMedicalDetailsDoctorActivity.this,
                                           response.body().getMessage(), Toast.LENGTH_SHORT).show();
                               }
                               else{
                                   Toast.makeText(ViewMedicalDetailsDoctorActivity.this,
                                           response.body().getMessage(), Toast.LENGTH_SHORT).show();
                               }
                           }
                           else{
                               Toast.makeText(ViewMedicalDetailsDoctorActivity.this,
                                       "response failure", Toast.LENGTH_SHORT).show();
                           }
                       }

                       @Override
                       public void onFailure(Call<ApprovedStatusResponse> call, Throwable t) {
                           Log.e("TAG","onFailure "+t.getMessage());
                           Toast.makeText(ViewMedicalDetailsDoctorActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                       }
                   });
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private  MultipartBody.Part uploadVideo(String str)
    {
        File imageFile = new File(str);
        Log.e("TAG","filepath= "+imageFile.getPath());
        RequestBody videoBody = RequestBody.create(MediaType.parse("*/*"), imageFile);
        MultipartBody.Part vFile = MultipartBody.Part.createFormData("discharge_summary_pdf", imageFile.getName(), videoBody);
        return vFile;
    }
    public String getPath1(Uri uri) {
//        final String id = DocumentsContract.getDocumentId(uri);
//        final Uri contentUri = ContentUris.withAppendedId(
//                Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

        String[] projection = {  MediaStore.Files.FileColumns.DISPLAY_NAME };
        String[] image={MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(uri, image, null, null, null);
        if (cursor != null) {
            // HERE YOU WILL GET A NULLPOINTER IF CURSOR IS NULL
            // THIS CAN BE, IF YOU USED OI FILE MANAGER FOR PICKING THE MEDIA
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else
            return null;
    }

    @SuppressLint("Range")
    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Video.Media.DATA };
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
        } else
            return null;
    }

}



//public class ViewMedicalDetailsDoctorActivity extends AppCompatActivity {
//    private static final int REQUEST_READ_STORAGE = 100;
//    private Button btn;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.view_medical_details_doctor);
//
//        btn = findViewById(R.id.button24);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                chooseFile();
//            }
//        });
//    }
//
//    private void chooseFile() {
//        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//            // Permission not granted, request it
//            ActivityCompat.requestPermissions(this,
//                    new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
//                    REQUEST_READ_STORAGE);
//        } else {
//            // Permission granted, open file picker
//            openFilePicker();
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == REQUEST_READ_STORAGE) {
//            // Check if permission is granted
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                // Permission granted, proceed with file picking
//                openFilePicker();
//            } else {
//                // Permission denied, handle accordingly (e.g., show a message or disable functionality)
//                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//    private void openFilePicker() {
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        intent.setType("*/*");
//        String[] mimeTypes = {"image/*", "application/pdf"};
//        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
//        resultLauncher.launch(intent);
//    }
//
//    private ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
//            new ActivityResultContracts.StartActivityForResult(),
//            result -> {
//                if (result.getResultCode() == Activity.RESULT_OK) {
//                    Intent data = result.getData();
//                    if (data != null) {
//                        Uri selectedFileUri = data.getData();
//                        if (selectedFileUri != null) {
//                            // Handle the selected file URI (e.g., display its path or perform further operations)
//                            String filePath = selectedFileUri.getPath();
//                            Toast.makeText(this, "File selected: " + filePath, Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                } else {
//                    // File picking canceled or failed
//                    Toast.makeText(this, "File picking canceled or failed", Toast.LENGTH_SHORT).show();
//                }
//            });
//}
