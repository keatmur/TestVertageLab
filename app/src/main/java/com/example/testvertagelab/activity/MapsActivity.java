package com.example.testvertagelab.activity;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.testvertagelab.R;
import com.example.testvertagelab.api.RestClient;
import com.example.testvertagelab.fragment.ListMarkersFragment;
import com.example.testvertagelab.model.Marker;
import com.example.testvertagelab.model.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.testvertagelab.Constants.PUT_EMAIL;
import static com.example.testvertagelab.Constants.TAG;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Toolbar toolbar;
    ArrayList<Marker> markers = new ArrayList<>();
    Fragment fragment = new ListMarkersFragment();
    FragmentTransaction transaction;
    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        String titel = getIntent().getStringExtra(PUT_EMAIL);
        initToolbarWithNavigation(titel);

    }

    @Override
    protected void onStart() {
        getDateFromApi();
        super.onStart();

    }

    public void initToolbarWithNavigation(String title) {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });


    }

    private void getDateFromApi() {
        RestClient.getsInstance().getService().getDate().enqueue(new Callback<Places>() {
            @Override
            public void onResponse(Call<Places> call, Response<Places> response) {
                if (response.isSuccessful()) {
                    markers.addAll(response.body().getPlaces());
                    upMarkers();

                } else {
                    Log.e(TAG, String.format("Error response: %d %s", response.code(), response.message()));
                    ResponseBody errorBody = response.errorBody();
                    if (errorBody != null) {
                        try {
                            Log.e(TAG, "Error body:\n" + errorBody.string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }


            @Override
            public void onFailure(Call<Places> call, Throwable t) {
                Log.e("TESTVL", t.toString());

            }
        });
    }

    private void upMarkers() {
        bundle.putParcelableArrayList("test", markers);
        fragment.setArguments(bundle);
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_list, fragment, "TAG").commit();

        for (int i = 0; i < markers.size(); i++) {
            LatLng place = new LatLng(markers.get(i).getLat(), markers.get(i).getLng());
            mMap.addMarker(new MarkerOptions().position(place).title(markers.get(i).getName()));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place, 12));


        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }
}