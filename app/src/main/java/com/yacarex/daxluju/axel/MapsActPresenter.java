package com.yacarex.daxluju.axel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.GeoPoint;
import com.yacarex.daxluju.models.axel.Picspot;

import java.util.ArrayList;
import java.util.List;

public class MapsActPresenter {

    private boolean mLocationPermissionGranted = false;
    public static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 123;
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationManager locationManager;
    private Criteria criteria;
    private List<Picspot> list = new ArrayList<>();

    public MapsActPresenter() {
    }

    public void initializePresenter(GoogleMap mMap, LocationManager locationManager, Activity activity){
        this.mMap= mMap;
        this.locationManager= locationManager;
        criteria = new Criteria();
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activity);

        FirestoreManager.getInstance().getPicspots();
    }

    public void getLocationPermission(Activity activity) {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(activity.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(activity,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    public void checkForLocalPermision(Activity activity){
        if(mLocationPermissionGranted){
            centerCameraOnCurrentPosition(activity);
        }
        else {
            getLocationPermission(activity);
        }
    }

    public void centerCameraOnCurrentPosition(Activity activity) {

        updateLocationControls(activity);

        @SuppressLint("MissingPermission")
        Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
        if (location != null) {
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 0));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(location.getLatitude(), location.getLongitude()))      // Sets the center of the map to location user
                    .zoom(12)// Sets the zoom
                    .build();                   // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
    }

    public void updateLocationControls(Activity activity) {
        if (mMap == null) {
            return;
        }
        try {
            if (mLocationPermissionGranted) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                //mLastKnownLocation = null;
                getLocationPermission(activity);
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    @SuppressLint("MissingPermission")
    public void saveCurrenLocation(Activity activity) {

        mFusedLocationClient.getLastLocation().addOnSuccessListener(activity, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    GeoPoint geoPoint= new GeoPoint(location.getLatitude(), location.getLongitude());
                    Picspot picspot= new Picspot(geoPoint, "", "");
                    FirestoreManager.getInstance().addPicspot(picspot);
                    list.add(picspot);
                }
            }
        });

    }

    public void putMarkers() {

        for (Picspot location : list) {
            LatLng position = new LatLng(location.getLocation().getLatitude(), location.getLocation().getLongitude());
            mMap.addMarker(new MarkerOptions().position(position));
        }
    }

    public boolean ismLocationPermissionGranted() {
        return mLocationPermissionGranted;
    }

    public void setmLocationPermissionGranted(boolean mLocationPermissionGranted) {
        this.mLocationPermissionGranted = mLocationPermissionGranted;
    }
}
