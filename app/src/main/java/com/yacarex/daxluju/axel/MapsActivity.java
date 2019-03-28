package com.yacarex.daxluju.axel;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.firebase.FirebaseApp;
import com.yacarex.daxluju.R;

import static com.yacarex.daxluju.axel.MapsActPresenter.PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private MapsActPresenter mapsActPresenter;

    private Button saveLocationBtn, putMarkers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        FirebaseApp.initializeApp(this);
        mapsActPresenter = new MapsActPresenter();

        saveLocationBtn = findViewById(R.id.save_location);
        putMarkers = findViewById(R.id.put_markers);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        setUpViews();

        mapsActPresenter.initializePresenter(googleMap, (LocationManager) getSystemService(Context.LOCATION_SERVICE), this);
        mapsActPresenter.getLocationPermission(this);
        mapsActPresenter.checkForLocalPermision(this);

    }

    private void setUpViews() {
        putMarkers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mapsActPresenter.putMarkers();
            }
        });
        saveLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mapsActPresenter.saveCurrenLocation(MapsActivity.this);
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mapsActPresenter.setmLocationPermissionGranted(false);
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mapsActPresenter.setmLocationPermissionGranted(true);
                    mapsActPresenter.centerCameraOnCurrentPosition(this);
                } else {
                    Toast.makeText(getBaseContext(), "You Rejected the Required permission", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }

    }

}
