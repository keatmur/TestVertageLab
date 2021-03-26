package com.example.testvertagelab.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.testvertagelab.api.RestClient;
import com.example.testvertagelab.model.Places;
import com.example.testvertagelab.utils.MarkersAdapter;
import com.example.testvertagelab.R;
import com.example.testvertagelab.model.Marker;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListMarkersFragment extends Fragment {
    RecyclerView listMarkers ;
    MarkersAdapter markersAdapter;
    ArrayList<Marker> markers = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.fragment_list, container, false);
        listMarkers = view.findViewById(R.id.rv_listMarkers);
        markersAdapter= new MarkersAdapter(markers);
        listMarkers.setAdapter(markersAdapter);
        listMarkers.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            markers = getArguments().getParcelableArrayList("test");
        }

    }
}