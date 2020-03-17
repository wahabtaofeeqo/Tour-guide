package com.example.tourguide;


import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.button.MaterialButton;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment implements OnMapReadyCallback {


    private VideoView videoView;
    private MediaController mediaController;


    private String name;
    private TextView about;
    private TextView history;
    private TextView phone;
    private LinearLayout historyHolder;
    private LinearLayout phoneHolder;

    private String number;
    private String link;

    private double latitude;
    private double longitude;

    private List<Integer> images;

    private boolean permissionGranted;

    public DetailsFragment() {
        // Required empty public constructor
    }

    public static DetailsFragment getInstance(String name) {

        Bundle bundle = new Bundle();
        bundle.putString("name", name);

        DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle.getString("name") != null) {
            name = bundle.getString("name");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        about = (TextView) view.findViewById(R.id.about);
        history = (TextView) view.findViewById(R.id.history);
        phone = (TextView) view.findViewById(R.id.phone);
        SliderView sliderView = view.findViewById(R.id.imageSlider);

        historyHolder = (LinearLayout) view.findViewById(R.id.historyHolder);
        phoneHolder   = (LinearLayout) view.findViewById(R.id.phoneHolder);

        MaterialButton video = (MaterialButton) view.findViewById(R.id.view);
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), VideoActivity.class);
                intent.putExtra("link", link);

                startActivity(intent);
            }
        });

        MaterialButton call = (MaterialButton) view.findViewById(R.id.call);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeCall(number);
            }
        });

        checkPermission();

        SupportMapFragment mapFragment  = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        images = new ArrayList<>();
        placeDetails(name);

        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(10); //set scroll delay in seconds :
        sliderView.setIndicatorVisibility(false);
        sliderView.startAutoCycle();

        SliderAdapter sliderAdapter = new SliderAdapter(getContext(), images);
        sliderView.setSliderAdapter(sliderAdapter);

        return view;
    }

    private void placeDetails(String name) {
        switch (name.toLowerCase()) {
            case "olumo rock":

                historyHolder.setVisibility(View.VISIBLE);
                phoneHolder.setVisibility(View.VISIBLE);

                about.setText(getContext().getResources().getString(R.string.aboutOlumo));
                history.setText(getContext().getResources().getString(R.string.olumoHistory));
                number = getContext().getResources().getString(R.string.olumoPhone);
                link = getContext().getResources().getString(R.string.olumoLink);
                phone.setText(number);

                images.add(R.drawable.olumo1);
                images.add(R.drawable.olumo2);
                images.add(R.drawable.olumo3);
                images.add(R.drawable.olumo4);

                latitude = Double.parseDouble(getContext().getResources().getString(R.string.olumoLat));
                longitude = Double.parseDouble(getContext().getResources().getString(R.string.olumoLong));
                break;

            case "park inn":
                about.setText(getContext().getResources().getString(R.string.aboutPark));
                link = getContext().getResources().getString(R.string.olumoLink);

                images.add(R.drawable.park1);
                images.add(R.drawable.park2);
                images.add(R.drawable.park3);
                images.add(R.drawable.park4);
                break;

            case "centenary hall":
                historyHolder.setVisibility(View.VISIBLE);
                about.setText(getContext().getResources().getString(R.string.aboutCentenary));
                history.setText(getContext().getResources().getString(R.string.centenaryHistory));
                link = getContext().getResources().getString(R.string.centenaryLink);

                images.add(R.drawable.cent1);
                images.add(R.drawable.cent2);
                images.add(R.drawable.cent3);
                images.add(R.drawable.cent4);

                latitude = Double.parseDouble(getContext().getResources().getString(R.string.centenaryLat));
                longitude = Double.parseDouble(getContext().getResources().getString(R.string.centenaryLong));
                break;

            case "oopl":
                historyHolder.setVisibility(View.VISIBLE);
                phoneHolder.setVisibility(View.VISIBLE);
                about.setText(getContext().getResources().getString(R.string.aboutOOPL));
                history.setText(getContext().getResources().getString(R.string.OOPLHistory));
                number = getContext().getResources().getString(R.string.OOPLPhone);
                link = getContext().getResources().getString(R.string.ooplLink);
                phone.setText(number);
                images.add(R.drawable.oopl1);
                images.add(R.drawable.oopl2);
                images.add(R.drawable.oopl3);
                images.add(R.drawable.oopl4);

                latitude = Double.parseDouble(getContext().getResources().getString(R.string.OOPLLat));
                longitude = Double.parseDouble(getContext().getResources().getString(R.string.OOPLLong));
                break;


            case "adire":
                about.setText(getContext().getResources().getString(R.string.aboutAdire));
                link = getContext().getResources().getString(R.string.adireLink);

                images.add(R.drawable.adire1);
                images.add(R.drawable.adire2);
                images.add(R.drawable.adire3);
                images.add(R.drawable.adire4);
                break;
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (permissionGranted) {

            googleMap.setMinZoomPreference(15f);
            googleMap.setMyLocationEnabled(true);

            if (latitude != 0 && longitude != 0) {
                MarkerOptions options = new MarkerOptions();
                options.position(new LatLng(latitude, longitude));
                options.title(name);
                googleMap.addMarker(options);
            }
        }
    }

    private void makeCall(final String number) {

        if (number != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage("Place a call to this advertiser?");
            builder.setPositiveButton("Call", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + number));
                    startActivity(intent);
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            builder.show();
        }
    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            requestPermission();
        else
            permissionGranted = true;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 20);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 20) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                permissionGranted = true;
            else
                permissionGranted = false;
        }
    }
}