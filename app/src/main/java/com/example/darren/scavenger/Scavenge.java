package com.example.darren.scavenger;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;

public class Scavenge extends Activity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{
    protected static final String TAG = "Scavenge";

    static GoogleMap map; // Might be null if Google Play services APK is not available.
    Location currLocation;
    GoogleApiClient googleApiClient;
    LocationRequest locationRequest;

    public static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;
    public static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS
            = UPDATE_INTERVAL_IN_MILLISECONDS/2;

    protected static final float ZOOM_LEVEL = 19;
    double lat;
    double lng;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scavenge);
        context = getApplicationContext();
        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        map.setMyLocationEnabled(true);
        buildGoogleApiClient();
    }

    @Override
    public void onConnectionSuspended(int cause) {
        // The connection to Google Play services was lost for some reason. We call connect() to
        // attempt to re-establish the connection.
        Log.i(TAG, "Connection suspended");
        googleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        // Refer to the javadoc for ConnectionResult to see what error codes might be returned in
        // onConnectionFailed.
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + result.getErrorCode());
    }

    @Override
    public void onStart(){
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    public void onStop(){
        super.onStop();
        if(googleApiClient.isConnected()){
            googleApiClient.disconnect();
        }
    }
    @Override
    public void onConnected(Bundle connectionHint){
        currLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        if(currLocation == null){
            Toast.makeText(context,"No location detected",Toast.LENGTH_SHORT).show();
        }
        else {
            setToLocation(currLocation);
        }
    }

    public synchronized void buildGoogleApiClient(){
        googleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        createLocationRequest();
    }

    public void createLocationRequest(){
        locationRequest = new LocationRequest();
        locationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        locationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    private void setToLocation(Location loc){
        map.setMyLocationEnabled(true);
        map.clear();
        lat =  loc.getLatitude();
        lng = loc.getLongitude();
        LatLng currCoordinates = new LatLng(lat,lng);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(currCoordinates,ZOOM_LEVEL));
    }
}
