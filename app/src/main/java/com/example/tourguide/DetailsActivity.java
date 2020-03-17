package com.example.tourguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

public class DetailsActivity extends AppCompatActivity implements OnFragmentChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        String name = getIntent().getStringExtra("name");
        if (name != null) {

            //Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
            changeFragment(DetailsFragment.getInstance(name), false);
        }
        else {
            Toast.makeText(this, "Name of place not found", Toast.LENGTH_SHORT).show();
        }
    }

    public void changeFragment(Fragment fragment, boolean backTrack) {

        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(R.id.container, fragment);
        if (backTrack)
            transaction.addToBackStack(null);

        transaction.commit();
    }

    @Override
    public void onFragmentChange(Fragment fragment) {

    }
}