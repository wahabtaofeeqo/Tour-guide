package com.example.tourguide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.List;

public class TourAdapter extends RecyclerView.Adapter<TourAdapter.TourViewHolder> {

    private Context ctx;
    private List<Place> tourList;
    private MainActivity mainActivity;

    public TourAdapter(Context context, List<Place> tours, MainActivity activity) {
        ctx = context;
        tourList = tours;
        mainActivity = activity;
    }

    class TourViewHolder extends RecyclerView.ViewHolder {

        public MaterialButton button;
        public AppCompatImageView logo;
        public TextView location;
        public TextView name;

        public TourViewHolder(View view) {
            super(view);

            button = (MaterialButton) view.findViewById(R.id.view);
            logo   = (AppCompatImageView) view.findViewById(R.id.logo);
            name = (TextView) view.findViewById(R.id.name);
        }
    }

    @NonNull
    @Override
    public TourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tour_layout, parent, false);
        return new TourViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TourViewHolder holder, int position) {

        final Place place = tourList.get(position);

        holder.logo.setImageResource(place.getLogo());
        holder.name.setText(place.getName());

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.startDetails(place.getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return tourList.size();
    }
}