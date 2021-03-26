package com.example.testvertagelab.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testvertagelab.R;
import com.example.testvertagelab.model.Marker;

import java.util.ArrayList;

public class MarkersAdapter extends RecyclerView.Adapter<MarkersAdapter.ViewHolder> {
    ArrayList<Marker> markers = new ArrayList<>();

    public MarkersAdapter(ArrayList<Marker> markers) {
        this.markers = markers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_marker, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(markers.get(position));
        if (position == markers.size() - 1) {
            holder.divider.setVisibility(View.INVISIBLE);
        } else {
            holder.divider.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public int getItemCount() {
        return markers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        View divider;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            divider = itemView.findViewById(R.id.divider);
        }

        public void bind(Marker marker) {
            name.setText(marker.toString());

        }
    }
}

