package com.example.tourguide;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private TourAdapter adapter;
    private RecyclerView recyclerView;

    private MainActivity activity;
    private Context context;

    private List<Place> places;

    private OnFragmentChangeListener listener;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = (MainActivity) getActivity();
        context  = (Context) getActivity();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        listener = (OnFragmentChangeListener) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        SliderView sliderView = view.findViewById(R.id.imageSlider);

        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();

        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.place4);
        images.add(R.drawable.olumo);
        images.add(R.drawable.adire);
        images.add(R.drawable.park);
        images.add(R.drawable.cemetry);

        SliderAdapter sliderAdapter = new SliderAdapter(getContext(), images);
        sliderView.setSliderAdapter(sliderAdapter);

        recyclerView = (RecyclerView) view.findViewById(R.id.tourRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        places = new ArrayList<>();

        places.add(new Place("Olumo Rock", R.drawable.olumo));
        places.add(new Place("OOPL", R.drawable.oopl));
        places.add(new Place("Centenary Hall", R.drawable.cemetry));
        places.add(new Place("Park Inn", R.drawable.park));
        places.add(new Place("Adire", R.drawable.adire));

        adapter = new TourAdapter(context, places, activity);
        recyclerView.setAdapter(adapter);

        return view;
    }

}