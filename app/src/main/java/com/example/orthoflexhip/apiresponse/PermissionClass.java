package com.example.orthoflexhip.apiresponse;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.content.pm.PackageManager;
import androidx.core.content.ContextCompat;



public class PermissionClass
{
    public static boolean isPermissionGranted (Context context)
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
        {
            return Environment.isExternalStorageManager();
        }
        else
        {
            int readExtStorage = ContextCompat.checkSelfPermission(context, android.Manifest.permission.READ_EXTERNAL_STORAGE);
            return readExtStorage == PackageManager.PERMISSION_GRANTED;
        }
    }
}