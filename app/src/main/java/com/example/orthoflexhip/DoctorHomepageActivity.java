package com.example.orthoflexhip;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.orthoflexhip.api.ApiService;
import com.example.orthoflexhip.api.RetrofitClient;
import com.example.orthoflexhip.dataClass.DoctorProfileData;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorHomepageActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_homepage);

        int doctorId;

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        // Handle ImageButton12 click event
        TextView doctorName = findViewById(R.id.textView6);
        ShapeableImageView doctorImage = findViewById(R.id.imageButton2);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        doctorId = sharedPreferences.getInt("doctorId", -1);

        ImageButton imageButton12 = findViewById(R.id.imageButton12);
        if (imageButton12 != null) {
            imageButton12.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (drawer != null) {
                        drawer.openDrawer(GravityCompat.START);
                    } else {
                        Log.e("DoctorHomepageActivity", "DrawerLayout is null");
                    }
                }
            });
        } else {
            Log.e("DoctorHomepageActivity", "ImageButton12 is null");
        }

        // Handle ImageButton2 click event
        ShapeableImageView imageButton2 = findViewById(R.id.imageButton2);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorHomepageActivity.this, DoctorProfileActivity.class);
                startActivity(intent);
            }
        });

        ApiService apiService = RetrofitClient.getInstance();
        apiService.doctorProfile(doctorId).enqueue(new Callback<DoctorProfileData>() {
            @Override
            public void onResponse(Call<DoctorProfileData> call, Response<DoctorProfileData> response) {
                if (response.isSuccessful()) {
                    doctorName.setText(response.body().getData().getName());
                    Picasso.get().load(RetrofitClient.BASE_URL+response.body().getData().getProfilePhoto()).into(doctorImage);
                }
            }
            @Override
            public void onFailure(Call<DoctorProfileData> call, Throwable t) {
                Toast.makeText(DoctorHomepageActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        // Set up ViewPager and TabLayout
        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tabLayout);
        if (tabLayout != null) {
            CustomPagerAdapter pagerAdapter = new CustomPagerAdapter(getSupportFragmentManager());
            viewPager.setAdapter(pagerAdapter);
            tabLayout.setupWithViewPager(viewPager);
            tabLayout.getTabAt(0).setIcon(R.drawable.home);
            tabLayout.getTabAt(1).setIcon(R.drawable.person);
            tabLayout.getTabAt(2).setIcon(R.drawable.calender);
            tabLayout.getTabAt(3).setIcon(R.drawable.video);

        } else {
            Log.e("DoctorHomepageActivity", "TabLayout is null");
        }

        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    // Handle navigation view item clicks here
                    int id = item.getItemId();

                    if (id == R.id.nav_profile) {
                        // Handle button 1 click
                        Intent intent = new Intent(DoctorHomepageActivity.this, DoctorProfileActivity.class);
                        startActivity(intent);
                    } else if (id == R.id.nav_logout) {
                        // Handle button 2 click
                        Intent intent = new Intent(DoctorHomepageActivity.this, SelectLoginActivity.class);
                        startActivity(intent);
                    }

                    drawer.closeDrawer(GravityCompat.START);
                    return true;
                }
            });
        }
    }
}
